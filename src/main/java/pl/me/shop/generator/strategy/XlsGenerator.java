package pl.me.shop.generator.strategy;

import pl.me.shop.generator.model.FileType;

public class XlsGenerator extends GeneratorStrategy {
    public XlsGenerator() {
        super(FileType.XLS);
    }

    @Override
    public byte[] generateFile() {
        System.out.println("XLS");
        return new byte[0];
    }
}
