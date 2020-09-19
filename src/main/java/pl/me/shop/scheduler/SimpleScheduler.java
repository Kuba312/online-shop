package pl.me.shop.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SimpleScheduler {


    @Scheduled(cron = "0 0 2 ? * *")
    public void interval() {
        System.out.println("interval");
    }

}
