package com.dopsonbr.benchmark.springsync157;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Configuration
public class OrderConfiguration {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    @Autowired
    public Executor workPool(@Value("${workPool.coreSize:100}") int coreSize,
                             @Value("${workPool.maxSize:200}") int maxSize,
                             @Value("${workPool.idleThreadTTLinMS:60000}") long idleThreadTTL,
                             @Value("${workPool.queueDepth:1}") int queueDepth) {
        return new ThreadPoolExecutor(
            coreSize, maxSize, idleThreadTTL, TimeUnit.MILLISECONDS,
            new ArrayBlockingQueue<>(queueDepth, true),
            new BasicThreadFactory.Builder()
                .namingPattern("workPoolThread-%d")
                .daemon(true)
                .build(),
            new ThreadPoolExecutor.CallerRunsPolicy());
    }

}
