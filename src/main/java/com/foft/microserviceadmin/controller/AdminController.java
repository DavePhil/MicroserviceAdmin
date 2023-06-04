package com.foft.microserviceadmin.controller;


// import lombok.Data;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.foft.microserviceadmin.exception.AdminNotFoundException;
import com.foft.microserviceadmin.modele.Admin;
import com.foft.microserviceadmin.repository.AdminRepository;
// import com.foft.microserviceadmin.repository.AdminRepository;
import com.foft.microserviceadmin.service.AdminService;
@RestController
public class AdminController {

    @Autowired
    private AdminService adminService; 
    @Autowired
    private AdminRepository adminRepository;


 

    @GetMapping("/Admin/{id}")
    public Optional<Admin> getAdmin(@PathVariable("id") final Integer id ){
        Optional<Admin> admin = adminRepository.findById(id);
        if(admin.isPresent()) {
            throw new AdminNotFoundException("L'admin correspondant à l'id " + id + " n'existe pas");
        } 
            return admin;
        
    }

    @GetMapping("/Admin")
    public Iterable<Admin> getAdmins() {
        return adminService.getAdmins();
    }



  

    @PostMapping("/CreateAdmin")
    public String saveAdmin(@RequestParam("file") MultipartFile file,
                              @RequestParam("nom") String nom,
                              @RequestParam("password") String password,
                              @RequestParam("mail") String mail)
    {
        adminService.saveAdminToDB(file,nom,mail,password);
          return "redirect:/Admin";
    }


    @PostMapping("/UpdateAdmin/{id}")
    public String upadteAdmin(@PathVariable("id") int id,
                              @RequestParam("file") MultipartFile file,
                              @RequestParam("nom") String nom,
                              @RequestParam("password") String password,
                              @RequestParam("mail") String mail)
    {
        adminService.updateinformation(id,file,nom,mail,password);
          return "redirect:/Admin";
    }




  


    @GetMapping("/deleteAdmin/{id}")
    public String deleteAdmin(@PathVariable("id") int id)
    {

        adminService.deleteAdmin(id);
        return "redirect:/Admin";
    }









}
