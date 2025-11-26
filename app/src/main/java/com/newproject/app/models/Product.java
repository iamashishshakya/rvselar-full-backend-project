package com.newproject.app.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Brand name is required")
    private String brandName;

    @NotNull(message = "Price is required")
    private BigDecimal price;

    @NotBlank(message = "Sub category is required")
    private String subCategory;

    @NotBlank(message = "Model is required")
    private String model;

    @NotBlank(message = "Category is required")
    private String category;

    @NotBlank(message = "Product description is required")
    @Column(columnDefinition = "TEXT")
    private String description;

    @NotBlank(message = "Image is required")
    @Column(columnDefinition = "TEXT")
    private String imageUrl;

    private Boolean isActive = false;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
