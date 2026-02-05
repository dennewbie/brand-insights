package com.example.demo.pipeline;

import com.example.demo.entity.BrandItem;

public interface ItemEnricher {
    void enrich(BrandItem item);
}