package com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.repository;

import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.entity.Story;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoryRepository extends JpaRepository<Story,Long> {
}
