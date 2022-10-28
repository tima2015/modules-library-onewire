package ru.funnydwarf.iot.ml;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class OneWireModuleGroup extends ModuleGroup {

    private static final Logger log = LoggerFactory.getLogger(OneWireModuleGroup.class);

    public static final String PATH = "/sys/bus/w1/devices";

    public OneWireModuleGroup() {
        super("OneWire", "Интерфейс разработанный компанией Dallas Semiconductor." +
                "Для передачи данных используется один провод");
    }

    @Override
    protected State initialize() throws Exception {
        File oneWireDriverDir = new File(PATH);
        if (!oneWireDriverDir.exists() || !oneWireDriverDir.isDirectory()) {
            log.warn("Driver folder was not found! Driver not initialized!");
            return State.INITIALIZATION_ERROR;
        }
        return State.OK;
    }
}
