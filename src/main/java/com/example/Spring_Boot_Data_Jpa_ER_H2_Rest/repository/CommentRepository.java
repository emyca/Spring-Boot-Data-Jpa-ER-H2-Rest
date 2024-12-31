package com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.repository;

import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {
}
