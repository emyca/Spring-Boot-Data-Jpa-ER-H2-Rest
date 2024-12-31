package com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.service;

import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.dto.OpinionDtoRequest;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.entity.Opinion;

import java.util.List;

public interface OpinionService {
    Opinion createByStoryId(Long id, OpinionDtoRequest request);
    List<Opinion> getAllByStoryId(Long id);
    Opinion getById(Long id);
    Opinion updateById(Long id, OpinionDtoRequest request);
    void deleteById(Long id);
}
