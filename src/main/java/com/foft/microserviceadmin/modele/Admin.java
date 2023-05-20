package com.foft.microserviceadmin.modele;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import java.util.List;
import java.util.Set;

@Data
@Entity
@DynamicUpdate
public class Admin {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        private String nom;
        private String email;
        private String password;
        private String photo;

        public Admin(){
                super();
                password = "1234";
        }






}
