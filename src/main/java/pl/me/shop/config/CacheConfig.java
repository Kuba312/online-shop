package pl.me.shop.config;

import com.hazelcast.config.Config;
import com.hazelcast.config.EvictionConfig;
import com.hazelcast.config.EvictionPolicy;
import com.hazelcast.config.MapConfig;
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
                        .setSize(20000)));
    }
}
