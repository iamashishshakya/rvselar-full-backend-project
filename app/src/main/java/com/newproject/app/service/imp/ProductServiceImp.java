package com.newproject.app.service.imp;

import com.newproject.app.dto.ProductRequestDTO;
import com.newproject.app.dto.ProductResponseDTO;
import com.newproject.app.models.Product;
import com.newproject.app.reporesatry.ProductEntity;
import com.newproject.app.service.Products;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImp implements Products {

    private final ProductEntity productRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<ProductResponseDTO> Mobile(String data) {
        List<Product> mobileProducts = productRepository.findByCategoryIgnoreCase(data);
        return mobileProducts.stream()
                .map(product -> modelMapper.map(product, ProductResponseDTO.class))
                .toList();
    }

    @Override
    public boolean DeletePro(Long id) {
        if (!productRepository.existsById(id)) return false;
            productRepository.deleteById(id);
            return true;
    }

    @Override
    public boolean Activate(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty()) return false;
        Product product = optionalProduct.get();
        product.setIsActive(!product.getIsActive());
        productRepository.save(product);
        return true;
    }
    @Override
    public boolean updateProduct(Long id, ProductRequestDTO dto) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty()) return false;
        Product existingProduct = optionalProduct.get();
        modelMapper.map(dto, existingProduct);
        productRepository.save(existingProduct);
        return true;
    }

    @Override
    public ProductResponseDTO Searchproduct(Long id) {
        return productRepository.findById(id)
               .map(product -> modelMapper.map(product, ProductResponseDTO.class))
                .orElse(null);
    }
}
