package com.spring.libraryMngSys.repository;

import com.spring.libraryMngSys.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
}
