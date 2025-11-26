package com.newproject.app.service;

import com.newproject.app.dto.ProductRequestDTO;
import com.newproject.app.dto.ProductResponseDTO;
import java.util.List;

public interface Products {

    List<ProductResponseDTO> Mobile(String data);

    boolean DeletePro(Long id);

    boolean Activate(Long id);

    boolean updateProduct(Long id, ProductRequestDTO dto);

    ProductResponseDTO Searchproduct(Long id);
}
