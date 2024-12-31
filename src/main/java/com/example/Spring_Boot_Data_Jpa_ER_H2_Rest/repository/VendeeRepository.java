package com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.repository;

import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.entity.Vendee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendeeRepository extends JpaRepository<Vendee,Long> {
}
