package com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.service;

import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.dto.StoryDtoRequest;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.entity.Story;

import java.util.List;

public interface StoryService {
    Story create(StoryDtoRequest request);
    List<Story> getAll();
    Story getById(Long id);
    Story updateById(Long id, StoryDtoRequest request);
    void deleteById(Long id);
}
