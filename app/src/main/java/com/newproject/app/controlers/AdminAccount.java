package com.newproject.app.controlers;

import com.newproject.app.dto.AdminDto;
import com.newproject.app.dto.ReqLogDto;
import com.newproject.app.dto.ResLogDto;
import com.newproject.app.reporesatry.AdminEntity;
import com.newproject.app.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AdminAccount {
    private final AdminService adminService;
    private final AdminEntity adminEntity;

    @PostMapping("/signup")
    public ResponseEntity<?> CreateAdmin(@RequestBody AdminDto adminDto) {
        System.out.println(adminDto.getEmail()+adminDto.getUsername());
        adminService.CreateNewAdmin(adminDto);
        return  ResponseEntity.ok("user created success");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody ReqLogDto reqLogDto) {
        try{
            ResLogDto res = adminService.LoginAdmin(reqLogDto);
            return ResponseEntity.status(HttpStatus.OK).body(res);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Email or Password");
        }
    }

}
