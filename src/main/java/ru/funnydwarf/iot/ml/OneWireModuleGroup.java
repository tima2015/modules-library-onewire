package ru.funnydwarf.iot.ml;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class OneWireModuleGroup extends ModuleGroup{

    private static final Logger log = LoggerFactory.getLogger(OneWireModuleGroup.class);

    public static final String PATH = "/sys/bus/w1/devices";

    public OneWireModuleGroup(String name, String description) {
        super(name, description);
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
