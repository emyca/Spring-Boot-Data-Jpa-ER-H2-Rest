package com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.repository;

import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.entity.Habitation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HabitationRepository extends JpaRepository<Habitation,Long> {
}
