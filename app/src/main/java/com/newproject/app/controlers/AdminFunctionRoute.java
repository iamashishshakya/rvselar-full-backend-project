package com.newproject.app.controlers;

import com.newproject.app.dto.ProductRequestDTO;
import com.newproject.app.reporesatry.ProductEntity;
import com.newproject.app.service.AdminService;
import com.newproject.app.service.Products;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/public")
public class AdminFunctionRoute {

    private final ProductEntity productEntity;
    private final Products products;
    private final AdminService adminService;

    @PostMapping("/addproduct")
    public ResponseEntity<?> StoreProduct(@RequestBody ProductRequestDTO product) {
        adminService.AddProduct(product);
        return ResponseEntity.ok("product added");
    }

    @GetMapping()
    public ResponseEntity<?> nearByProducts() {
        return ResponseEntity.ok(productEntity.findAll());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        boolean res = products.DeletePro(id);
        return res
                ? ResponseEntity.ok("Delete Success")
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product Not Found");
    }

    @GetMapping("/activate/{id}")
    public ResponseEntity<String> ActivateProduct(@PathVariable Long id) {
        boolean res = products.Activate(id);
        return res
                ? ResponseEntity.ok("Activate Success")
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product Not Activate");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable Long id, @RequestBody ProductRequestDTO dto) {
        boolean updated = products.updateProduct(id, dto);
        if (updated) {
            return ResponseEntity.ok("Product updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Product not found");
        }
    }
}
