package pl.me.shop.generator.strategy;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import pl.me.shop.generator.model.FileType;

@Component
public class JsonGenerator extends GeneratorStrategy {

    public JsonGenerator() {
        super(FileType.JSON);
    }

    @Override
    public byte[] generateFile() {
        System.out.println("JSON");
        return new byte[0];
    }
}
