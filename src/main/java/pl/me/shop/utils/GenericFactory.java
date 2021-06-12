package pl.me.shop.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import pl.me.shop.generator.model.FileType;
import pl.me.shop.generator.strategy.GeneratorStrategy;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Qualifier
@Component
@RequiredArgsConstructor
public class GenericFactory<K, V extends GenericStrategy<K>> {

    private final List<V> genericStrategies;

    private Map<K, V> strategyMap;


    @PostConstruct
    public void init() {
        strategyMap = genericStrategies.stream()
                .collect(Collectors.toMap(GenericStrategy::getType, genericStrategies -> genericStrategies));
    }

    public V getByKey(K k) {
        return strategyMap.get(k);
    }
}
