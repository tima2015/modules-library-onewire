package ru.funnydwarf.iot.ml;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OneWireConfiguration {

    @Bean("one-wire-module-group")
    public ModuleGroup getOneWireModuleGroup(){
        return new ModuleGroup("OneWire",
                "Интерфейс разработанный компанией Dallas Semiconductor." +
                        "Для передачи данных используется один провод",//todo стоит вынести в файл с ресурсами
                new Initializer() {
                    @Override
                    public void initialize(ModuleGroup group) throws Exception {
                        //todo проверять, готов ли драйвер к использованию
                    }
                });
    }

}
