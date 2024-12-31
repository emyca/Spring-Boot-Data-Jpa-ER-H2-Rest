package com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.service;

import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.dto.PostDtoRequest;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.entity.Post;

import java.util.List;

public interface PostService {
    Post create(PostDtoRequest request);
    List<Post> getAll();
    Post getById(Long id);
    Post updateById(Long id, PostDtoRequest request);
    boolean deleteById(Long id);
}
