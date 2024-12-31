package com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.repository;

import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location,Long> {
}
