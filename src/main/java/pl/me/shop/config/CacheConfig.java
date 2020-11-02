package pl.me.shop.config;

import com.hazelcast.config.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CacheConfig {

    @Bean
    public Config hazelcastConfig() {
        return new Config().addMapConfig(new MapConfig()
                .setName("product")
                .setEvictionConfig(new EvictionConfig()
                        .setEvictionPolicy(EvictionPolicy.LFU)
                        .setMaxSizePolicy(MaxSizePolicy.FREE_HEAP_SIZE)
                        .setSize(20000)));
    }
}
