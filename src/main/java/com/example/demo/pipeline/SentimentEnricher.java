package com.example.demo.pipeline;

import com.example.demo.entity.BrandItem;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class SentimentEnricher implements ItemEnricher {
    @Override
    public void enrich(BrandItem item) {
        if (item.getContent().toLowerCase().contains("very good")) {
            item.setSentiment("POSITIVE");
        } else if (item.getContent().toLowerCase().contains("broken")) {
            item.setSentiment("NEGATIVE");
        } else {
            item.setSentiment("NEUTRAL");
        }
    }
}