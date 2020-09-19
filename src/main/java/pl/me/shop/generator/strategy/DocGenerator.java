package pl.me.shop.generator.strategy;

import pl.me.shop.generator.model.FileType;

public class DocGenerator extends GeneratorStrategy {
    public DocGenerator() {
        super(FileType.DOC);
    }

    @Override
    public byte[] generateFile() {
        System.out.println("DOC");
        return new byte[0];
    }
}
