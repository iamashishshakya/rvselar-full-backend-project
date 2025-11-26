package com.newproject.app.service.imp;

import com.newproject.app.dto.AdminDto;
import com.newproject.app.dto.ProductRequestDTO;
import com.newproject.app.dto.ReqLogDto;
import com.newproject.app.dto.ResLogDto;
import com.newproject.app.models.Admin;
import com.newproject.app.models.Product;
import com.newproject.app.reporesatry.AdminEntity;
import com.newproject.app.reporesatry.ProductEntity;
import com.newproject.app.security.JwtService;
import com.newproject.app.service.AdminService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminServiceImp implements AdminService {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final AdminEntity entity;
    private final ModelMapper modelmapper;
    private final ProductEntity productEntety;

    @Override
    public void CreateNewAdmin(AdminDto adminDto) {
        if(entity.existsByEmail(adminDto.getEmail())){
            throw new RuntimeException("Email already exists");
        }
        try {
            Admin newAdmin = modelmapper.map(adminDto, Admin.class);
            newAdmin.setPassword(passwordEncoder.encode(newAdmin.getPassword()));
            newAdmin.setEmail(newAdmin.getEmail().toLowerCase());
            entity.save(newAdmin);
        } catch (Exception e) {
            throw new RuntimeException("User creation failed: " + e.getMessage());
        }
    }

    @Override
    public ResLogDto LoginAdmin(ReqLogDto reqLogDto) {
            String email = reqLogDto.getEmail();
            String password = reqLogDto.getPassword();
            UsernamePasswordAuthenticationToken authInputToken =
                    new UsernamePasswordAuthenticationToken(email, password);
            Authentication authentication = authenticationManager.authenticate(authInputToken);
            String authenticatedEmail = authentication.getName();
            String jwt = jwtService.generateToken(authenticatedEmail);

            ResLogDto newRes = new ResLogDto();
            newRes.setToken(jwt);
            newRes.setMessage("Login Successful");
            return newRes;
        }

    @Override
    public void AddProduct(ProductRequestDTO productDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated() ||
                authentication.getPrincipal().equals("anonymousUser")) {

            throw new RuntimeException("User must be logged in to add product");
        }

        Product newProduct = modelmapper.map(productDto, Product.class);
        productEntety.save(newProduct);
    }
}
