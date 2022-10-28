package ru.funnydwarf.iot.ml.sensor.reader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.funnydwarf.iot.ml.OneWireModuleGroup;
import ru.funnydwarf.iot.ml.sensor.MeasurementData;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Date;
import java.util.List;

@Component
public class DS18B20Reader implements Reader{

    private static final Logger log = LoggerFactory.getLogger(DS18B20Reader.class);

    @Value("${Modules.Sensors.DS18B20.unitName:°C}")
    private String unitName;

    @Value("${Modules.Sensors.DS18B20.measurementName:Temperature}")
    private String measurementName;

    @Override
    public MeasurementData[] read(Object address) {
        File measurementFile = new File(OneWireModuleGroup.PATH + '/' + address + "/w1_slave");
        String check;
        String temperature;
        try {
            List<String> lines = Files.readAllLines(measurementFile.toPath());
            check = lines.get(0);
            if (!check.contains("YES")){
                throw new RuntimeException("CRC check failed! (Data invalid)");
            }
            temperature = lines.get(1);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }

        temperature = temperature.substring(temperature.indexOf("t=") + 2).trim();
        return new MeasurementData[] {
                MeasurementData.createToCurrentDate(Double.parseDouble(temperature), unitName, measurementName)
        };
    }

    @Override
    public MeasurementData[] getTemplateRead() {
        return new MeasurementData[] {
                new MeasurementData(Double.MIN_VALUE, unitName, measurementName, new Date(1))
        };
    }
}
