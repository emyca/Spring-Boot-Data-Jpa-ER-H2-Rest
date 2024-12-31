package com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.service;

import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.dto.OpinionDtoRequest;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.entity.Opinion;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.exception.ResourceException;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.repository.OpinionRepository;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.repository.StoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("OpinionServiceImpl")
public class OpinionServiceImpl implements OpinionService {

    @Autowired
    private StoryRepository storyRepository;
    @Autowired
    private OpinionRepository opinionRepository;

    @Override
    public Opinion createByStoryId(Long id, OpinionDtoRequest request)
            throws ResourceException {
        return storyRepository.findById(id).map(story -> {
            Opinion opinion = new Opinion();
            opinion.setConsideration(request.consideration());
            opinion.setStory(story);
            return opinionRepository.save(opinion);
        }).orElseThrow(() ->
                new ResourceException(
                        "Story with id %s has not been found!"
                                .formatted(id)));
    }

    @Override
    public List<Opinion> getAllByStoryId(Long storyId)
            throws ResourceException {
        if (!storyRepository.existsById(storyId)) {
            throw new ResourceException(
                    "Story with id %s has not been found!"
                            .formatted(storyId));
        }
        return opinionRepository.findOpinionsByStoryId(storyId);
    }

    @Override
    public Opinion getById(Long id)
            throws ResourceException {
        return opinionRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceException(
                                "Opinion with id %s has not been found!"
                                        .formatted(id)));
    }

    @Override
    public Opinion updateById(Long id, OpinionDtoRequest request)
            throws ResourceException {
        Opinion opinion = opinionRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceException(
                                "Opinion with id %s has not been found!"
                                        .formatted(id)));
        opinion.setConsideration(request.consideration());
        return opinionRepository.save(opinion);
    }

    @Override
    public void deleteById(Long id)
            throws ResourceException {
        opinionRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceException(
                                "Opinion with id %s has not been found!"
                                        .formatted(id)));
        opinionRepository.deleteById(id);
    }
}
