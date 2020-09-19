package pl.me.shop.generator.strategy;

import pl.me.shop.generator.model.FileType;

public class PdfGenerator extends GeneratorStrategy {


    public PdfGenerator() {
        super(FileType.PDF);
    }

    @Override
    public byte[] generateFile() {
        System.out.println("PDF");
        return new byte[0];
    }
}
