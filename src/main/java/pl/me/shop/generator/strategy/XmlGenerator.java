package pl.me.shop.generator.strategy;

import pl.me.shop.generator.model.FileType;

public class XmlGenerator extends GeneratorStrategy  {
    public XmlGenerator() {
        super(FileType.XML);
    }

    @Override
    public byte[] generateFile() {
        System.out.println("XML");
        return new byte[0];
    }
}
