package com.example.demo.repository;

import com.example.demo.entity.BrandItem;
import com.example.demo.entity.ItemStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BrandItemRepository extends JpaRepository<BrandItem, Long> {
    List<BrandItem> findByStatus(ItemStatus status);

    List<BrandItem> findBySentiment(String sentiment);
}
