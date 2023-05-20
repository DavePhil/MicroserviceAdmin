package com.foft.microserviceadmin.repository;

import com.foft.microserviceadmin.modele.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin,Integer> {
}
