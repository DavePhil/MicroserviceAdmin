package com.foft.microserviceadmin.service;

import lombok.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.foft.microserviceadmin.modele.Admin;
import com.foft.microserviceadmin.repository.AdminRepository;

import java.io.IOException;
import java.util.Base64;
import java.util.Optional;
 
@Data
@Service
public class AdminService {
    public Admin getByEmail;
    @Autowired
    private AdminRepository adminRepository;



    public Optional<Admin> getadmin(Integer id){
        return adminRepository.findById(id);
    }

    public Iterable<Admin> getAdmins(){
        return adminRepository.findAll();
    }

    public void deleteAdmin (Integer id){
 
        try {
            adminRepository.deleteById(id);
        } catch (Exception e)   
        {}
    }

    public Admin saveAdmin (Admin admin){
        Admin saved = adminRepository.save(admin);
        return saved;
    }


    /*******************************------> FAT <-------********************************/
    public Admin findAdminByEmailAndPassword(String mail, String password){
        return adminRepository.findAdminByEmailAndPassword(mail,password);
    }
    /*******************************------> FAT <-------********************************/
    public void  saveAdminToDB(MultipartFile file, String name, String mail, String password)
    {
        Admin admin = new Admin();
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        if(fileName.contains(".."))
        {
            System.out.println("not a a valid file");
        }
        try {
            admin.setPhoto(Base64.getEncoder().encodeToString(file.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }

       admin.setNom(name);
       admin.setPassword(password);
       admin.setEmail(mail);

        adminRepository.save(admin);
    }




public void updateinformation(int id,MultipartFile photo, String name, String email,String password)
{
    Admin del=new Admin();
    del=adminRepository.findById(id).get();
    del.setPhoto(String.valueOf(photo));
    del.setNom(name);
    del.setEmail(email);
    del.setPassword(password);
    adminRepository.save(del);
}

}
