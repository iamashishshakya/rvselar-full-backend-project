package com.newproject.app.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductRequestDTO {

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

    @NotBlank(message = "Description is required")
    private String description;

    @NotBlank(message = "Image URL is required")
    private String imageUrl;

    private Boolean isActive = false;
}
