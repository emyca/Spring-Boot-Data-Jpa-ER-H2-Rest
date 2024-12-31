package com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.service;

import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.dto.RemarkDtoRequest;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.entity.Remark;

import java.util.List;

public interface RemarkService {
    Remark createByArticleId(Long id, RemarkDtoRequest request);
    List<Remark> getAllByArticleId(Long id);
    Remark getById(Long id);
    Remark updateById(Long id, RemarkDtoRequest request);
    boolean deleteById(Long id);
}
