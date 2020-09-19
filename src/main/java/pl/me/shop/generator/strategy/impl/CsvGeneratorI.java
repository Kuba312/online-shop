package pl.me.shop.generator.strategy.impl;

import org.springframework.stereotype.Component;
import pl.me.shop.generator.model.FileType;

@Component
public class CsvGeneratorI extends GeneratorStrategyI {



    @Override
    public byte[] generateFile() {
        System.out.println("CSV");
        return new byte[0];
    }

    @Override
    public FileType getType() {
        return FileType.CSV;

    }
}
