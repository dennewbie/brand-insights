package com.example.demo.pipeline;

import com.example.demo.entity.BrandItem;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class ImageAnalysisEnricher implements ItemEnricher {
    @Override
    public void enrich(BrandItem item) {
        // Simulating detecting brand in the images
        if (item.getImageUrl() != null) {
            item.setDetectedEntities("Brand_Logo_XYZ");
        }
    }
}