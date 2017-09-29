package com.dopsonbr.springbenchmarks.kotlinspringsync157

import org.apache.commons.lang3.concurrent.BasicThreadFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate

import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.Executor
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

@Configuration
class OrderConfiguration {

    @Bean
    fun restTemplate(builder: RestTemplateBuilder): RestTemplate {
        return builder.build()
    }

    @Bean
    @Autowired
    fun workPool(@Value("\${workPool.coreSize:100}") coreSize: Int,
                 @Value("\${workPool.maxSize:200}") maxSize: Int,
                 @Value("\${workPool.idleThreadTTLinMS:60000}") idleThreadTTL: Long,
                 @Value("\${workPool.queueDepth:1}") queueDepth: Int): Executor {
        return ThreadPoolExecutor(
                coreSize, maxSize, idleThreadTTL, TimeUnit.MILLISECONDS,
                ArrayBlockingQueue(queueDepth, true),
                BasicThreadFactory.Builder()
                        .namingPattern("workPoolThread-%d")
                        .daemon(true)
                        .build(),
                ThreadPoolExecutor.CallerRunsPolicy())
    }

}
