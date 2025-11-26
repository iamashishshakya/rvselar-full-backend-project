package com.newproject.app.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ProductResponseDTO {

    private Long id;
    private String brandName;
    private BigDecimal price;
    private String subCategory;
    private String model;
    private String category;
    private String description;
    private String imageUrl;
    private Boolean isActive;
    private LocalDateTime createdAt;
}
