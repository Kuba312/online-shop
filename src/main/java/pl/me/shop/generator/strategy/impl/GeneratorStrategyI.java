package pl.me.shop.generator.strategy.impl;

import lombok.Getter;
import pl.me.shop.generator.model.FileType;
import pl.me.shop.utils.GenericStrategy;

public abstract class GeneratorStrategyI implements GenericStrategy<FileType> {



    public abstract byte[] generateFile();
}
