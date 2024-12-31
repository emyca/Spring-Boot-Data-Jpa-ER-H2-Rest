package com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.service;

import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.dto.CommentDtoRequest;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.entity.Comment;

import java.util.List;

public interface CommentService {
    Comment createByPostId(Long id, CommentDtoRequest request);
    List<Comment> getAllByPostId(Long id);
    Comment getById(Long id);
    Comment updateById(Long id, CommentDtoRequest request);
    boolean deleteById(Long id);
}
