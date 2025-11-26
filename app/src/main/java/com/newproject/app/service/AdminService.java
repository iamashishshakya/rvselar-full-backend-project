package com.newproject.app.service;

import com.newproject.app.dto.AdminDto;
import com.newproject.app.dto.ProductRequestDTO;
import com.newproject.app.dto.ReqLogDto;
import com.newproject.app.dto.ResLogDto;

import java.util.List;


public interface AdminService {
    void CreateNewAdmin(AdminDto adminDto);

    ResLogDto LoginAdmin(ReqLogDto reqLogDto);

    void AddProduct(ProductRequestDTO product);

}
