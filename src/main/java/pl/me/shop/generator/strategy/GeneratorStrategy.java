package pl.me.shop.generator.strategy;

import lombok.Getter;
import pl.me.shop.generator.model.FileType;

public abstract class GeneratorStrategy {

    @Getter
    private FileType fileType;

    public GeneratorStrategy(FileType fileType) {
        this.fileType = fileType;
    }

    public abstract byte[] generateFile();
}
