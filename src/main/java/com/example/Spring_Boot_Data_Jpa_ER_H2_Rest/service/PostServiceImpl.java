package com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.service;

import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.dto.PostDtoRequest;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.entity.Post;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.mapper.PostMapper;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("PostServiceImpl")
public class PostServiceImpl implements PostService {

    @Autowired
    private PostMapper postMapper;
    @Autowired
    private PostRepository postRepository;

    @Override
    public Post create(PostDtoRequest request) {
        return postRepository.save(
                postMapper.dtoCreateToEntity(request));
    }

    @Override
    public List<Post> getAll() {
        return postRepository.findAll();
    }

    @Override
    public Post getById(Long id) {
        return postRepository.findById(id)
                .orElse(null);
    }

    @Override
    public Post updateById(Long id, PostDtoRequest request) {
        Optional<Post> postOptional = postRepository.findById(id);
        return postOptional.map(post ->
                        postRepository.saveAndFlush(
                                postMapper.dtoUpdateByIdToEntity(
                                        id, request, post)))
                .orElse(null);
    }

    @Override
    public boolean deleteById(Long id) {
        if (postRepository.findById(id).isPresent()) {
            postRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
