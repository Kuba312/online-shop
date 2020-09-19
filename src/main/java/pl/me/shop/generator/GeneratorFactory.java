package pl.me.shop.generator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.me.shop.generator.model.FileType;
import pl.me.shop.generator.strategy.GeneratorStrategy;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GeneratorFactory {

    private final List<GeneratorStrategy> generatorStrategyList;

    private Map<FileType, GeneratorStrategy> strategyMap = new HashMap<>();


    @PostConstruct
    public void init() {
        strategyMap = generatorStrategyList.stream()
                .collect(Collectors.toMap(GeneratorStrategy::getFileType, generatorStrategy -> generatorStrategy));

    }

    public GeneratorStrategy getByKey(FileType fileType) {
        return strategyMap.get(fileType);
    }
}
