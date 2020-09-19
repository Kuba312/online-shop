package pl.me.shop.generator.strategy;

import org.springframework.stereotype.Component;
import pl.me.shop.generator.model.FileType;

import java.io.File;

@Component
public class CsvGenerator extends GeneratorStrategy {

    public CsvGenerator() {
        super(FileType.CSV);
    }

    @Override
    public byte[] generateFile() {
        System.out.println("CSV");
        return new byte[0];
    }
}
