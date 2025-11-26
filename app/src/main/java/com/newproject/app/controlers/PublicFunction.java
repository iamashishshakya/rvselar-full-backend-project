package com.newproject.app.controlers;


import com.newproject.app.dto.ProductResponseDTO;
import com.newproject.app.service.Products;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class PublicFunction {

    private final Products products;

    @GetMapping("/mobile/{data}")
    public ResponseEntity<?> Mobiles(@PathVariable String data) {
        List<ProductResponseDTO> res = products.Mobile(data);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/pro/{id}")
    public ResponseEntity<?> getProduct(@PathVariable Long id) {
        ProductResponseDTO res = products.Searchproduct(id);
        if (res == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        return ResponseEntity.ok(res);
    }


}
