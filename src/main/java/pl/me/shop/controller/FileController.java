package pl.me.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.me.shop.generator.GeneratorFactory;
import pl.me.shop.generator.model.FileType;
import pl.me.shop.generator.strategy.impl.GeneratorStrategyI;
import pl.me.shop.utils.GenericFactory;

@RestController
@RequestMapping("/api/file")
@RequiredArgsConstructor
public class FileController {

    private final GeneratorFactory generatorFactory;
    private final GenericFactory<FileType, GeneratorStrategyI> genericFactory;
    
    @GetMapping
    public void generateFile(@RequestParam FileType fileType) {
        genericFactory.getByKey(fileType).generateFile();
    }

}
