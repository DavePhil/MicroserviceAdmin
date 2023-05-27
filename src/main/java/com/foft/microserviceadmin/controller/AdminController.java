package com.foft.microserviceadmin.controller;


// import lombok.Data;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.foft.microserviceadmin.modele.Admin;
// import com.foft.microserviceadmin.repository.AdminRepository;
import com.foft.microserviceadmin.service.AdminService;
@RestController
public class AdminController {

    @Autowired
    private AdminService adminService;


    @PostMapping("/Admin")
    public Admin createAdmin(@RequestBody Admin admin){
        return adminService.saveAdmin(admin);
    }


    @GetMapping("/Admin/{id}")
    public Admin getAdmin(@PathVariable("id") final Integer id ){
        Optional<Admin> admin = adminService.getadmin(id);
        if(admin.isPresent()) {
            return admin.get();
        } else {
            return null;
        }
    }

    @GetMapping("/Admin")
    public Iterable<Admin> getAdmins() {
        return adminService.getAdmins();
    }



    @PostMapping("/Admin/{id}")
    public Admin updateAdmin(@PathVariable("id") final int id, @RequestBody Admin admin) {
        Optional<Admin> e = adminService.getadmin(id);
        if(e.isPresent()) {
            Admin current = e.get();

            String nom = admin.getNom();
            if(nom != null) current.setNom(nom);
            String mail = admin.getEmail();
            if(mail != null) current.setEmail(mail);
            String password = admin.getPassword();
            if(password != null) current.setPassword(password);;
            adminService.saveAdmin(current);
            return current;
        } else {
            return null;
        }
    }



    @GetMapping("/")
    public  String getAdminByEmailAndPassWord()
    {
            return "login.html";
    }
    @PostMapping("/login")
    public  String getAdminByEmailAndPassWord(Model model,@RequestParam("password") String password,@RequestParam("mail") String mail){
        //int id = adminService.findAdminByEmailAndPassword(mail,password).getId();
        Admin admin =new Admin();
        admin =adminService.findAdminByEmailAndPassword(mail, password);
        if(adminService.findAdminByEmailAndPassword(mail,password)==null)
            return "redirect:/";
        else
            //List<Admin>ens =adminRepository.findAll();

//          int idAdmin = adminService.findAdminByEmailAndPassword(mail,password);
             admin.getId();
        model.addAttribute("userid",admin.getId());

            return "redirect:/HomePage";
    }


    @PostMapping("/CreateAdmin")
    public String saveAdmin(@RequestParam("file") MultipartFile file,
                              @RequestParam("nom") String nom,
                              @RequestParam("password") String password,
                              @RequestParam("mail") String mail)
    {
        adminService.saveAdminToDB(file,nom,mail,password);
          return "redirect:/";
    }


    @PostMapping("/UpdateAdmin/{id}")
    public String upadteAdmin(@PathVariable("id") int id,
                              @RequestParam("file") MultipartFile file,
                              @RequestParam("nom") String nom,
                              @RequestParam("password") String password,
                              @RequestParam("mail") String mail)
    {
        adminService.updateinformation(id,file,nom,mail,password);
          return "redirect:/";
    }




  


    @GetMapping("/deleteAdmin/{id}")
    public String deleteAdmin(@PathVariable("id") int id)
    {

        adminService.deleteAdmin(id);
        return "redirect:/Admin";
    }









}
