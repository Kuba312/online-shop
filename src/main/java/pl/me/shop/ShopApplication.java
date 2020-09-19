package pl.me.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;


//Pozwala uruchamiać metody o określonej porze dnia, lub co podany interwał
@EnableScheduling

//Bardzo szybki dostep do danych, ponieważ pobiera ConcurrentHashMap, co pozwala nam pobierać odrazu z pamięci Jvm, a nie z bazy danych
//Używam HazelCast, który wspiera replikacje danych pomiędzy Node-ami(dwie instancje tej samej aplikacji = 2 Nody)
@EnableCaching
@SpringBootApplication
public class ShopApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShopApplication.class, args);
    }

}
