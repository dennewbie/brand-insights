package com.example.demo.entity;

import com.example.demo.entity.ItemStatus;
import jakarta.persistence.*; // Usa questo per @Id
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class BrandItem {
    @jakarta.persistence.Id // Corretto per JPA
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;
    private String url;
    private LocalDateTime timestamp;
    private String imageUrl;

    private String sentiment;
    private String detectedEntities;

    @Enumerated(EnumType.STRING)
    private ItemStatus status = ItemStatus.PROCESSING;
}