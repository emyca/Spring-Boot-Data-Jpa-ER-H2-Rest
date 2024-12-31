package com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.service;

import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.dto.StoryDtoRequest;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.entity.Story;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.exception.ResourceException;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.mapper.StoryMapper;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.repository.StoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("StoryServiceImpl")
public class StoryServiceImpl implements StoryService {

    @Autowired
    private StoryMapper storyMapper;
    @Autowired
    private StoryRepository storyRepository;

    @Override
    public Story create(StoryDtoRequest request) {
        return storyRepository.save(
                storyMapper.dtoCreateToEntity(request));
    }

    @Override
    public List<Story> getAll() {
        return storyRepository.findAll();
    }

    @Override
    public Story getById(Long id)
            throws ResourceException {
        return storyRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceException(
                                "Story with id %s has not been found!"
                                        .formatted(id)));
    }

    @Override
    public Story updateById(Long id, StoryDtoRequest request)
            throws ResourceException {
        Story story = storyRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceException(
                                "Story with id %s has not been found!"
                                        .formatted(id)));
        return storyRepository.save(storyMapper
                .dtoUpdateByIdToEntity(request, story));
    }

    @Override
    public void deleteById(Long id)
            throws ResourceException,
            DataIntegrityViolationException {
        storyRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceException(
                                "Story with id %s has not been found!"
                                        .formatted(id)));
        // Can throw DataIntegrityViolationException
        storyRepository.deleteById(id);
    }
}
