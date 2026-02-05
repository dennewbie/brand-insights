package com.example.demo.controller;

import com.example.demo.entity.BrandItem;
import com.example.demo.entity.ItemStatus;
import com.example.demo.repository.BrandItemRepository;
import com.example.demo.service.PipelineOrchestrator;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/items")
@RequiredArgsConstructor
public class BrandItemController {
    private final BrandItemRepository repository;
    private final PipelineOrchestrator orchestrator;

    // Endpoint to check what needs to go in revision status
    @GetMapping("/pending-review")
    public List<BrandItem> getPending() {
        return repository.findByStatus(ItemStatus.PENDING_REVIEW);
    }

    @PostMapping("/{id}/approve")
    public BrandItem approve(@PathVariable Long id) {
        BrandItem item = repository.findById(id).orElseThrow();
        item.setStatus(ItemStatus.FINALIZED);
        return repository.save(item);
    }
}