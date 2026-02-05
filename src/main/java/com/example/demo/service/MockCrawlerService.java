package com.example.demo.service;

import com.example.demo.entity.BrandItem;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Random;

@Service
public class MockCrawlerService {
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(MockCrawlerService.class);

    private final PipelineOrchestrator orchestrator;
    private final Random random = new Random();

    public MockCrawlerService(PipelineOrchestrator orchestrator) {
        this.orchestrator = orchestrator;
    }

    // Generates a new brand mention every 5 seconds
    @Scheduled(fixedRate = 5000)
    public void ingestMockData() {
        BrandItem item = new BrandItem();
        item.setTimestamp(LocalDateTime.now());
        item.setUrl("https://social-media.com/post/" + random.nextInt(1000));

        // Randomly pick a positive or negative scenario
        if (random.nextBoolean()) {
            item.setTitle("I love this brand!");
            item.setContent("The service was very good and fast.");
        } else {
            item.setTitle("Terrible experience");
            item.setContent("I am very upset, the product is broken.");
        }

        logger.info("Crawler: Ingesting new item... " + item.getTitle());
        orchestrator.processItem(item);
    }
}