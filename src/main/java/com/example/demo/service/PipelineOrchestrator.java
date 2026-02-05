package com.example.demo.service;

import com.example.demo.entity.ItemStatus;
import com.example.demo.pipeline.ItemEnricher;
import com.example.demo.entity.BrandItem;
import com.example.demo.repository.BrandItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PipelineOrchestrator {
    private final List<ItemEnricher> enrichers;
    private final BrandItemRepository repository;

    public void processItem(BrandItem item) {
        // 1. Execute all enrichers
        enrichers.forEach(enricher -> enricher.enrich(item));

        // 2. Routing logic for manual revision
        if ("NEGATIVE".equals(item.getSentiment())) {
            item.setStatus(ItemStatus.PENDING_REVIEW);
        } else {
            item.setStatus(ItemStatus.FINALIZED);
        }

        // 3. Saving in the database
        repository.save(item);
    }
}