package pl.me.shop.generator.strategy.impl;

import pl.me.shop.generator.model.FileType;

public class DocGeneratorI extends GeneratorStrategyI {


    @Override
    public byte[] generateFile() {
        System.out.println("DOC");
        return new byte[0];
    }

    @Override
    public FileType getType() {
        return FileType.DOC;
    }
}
