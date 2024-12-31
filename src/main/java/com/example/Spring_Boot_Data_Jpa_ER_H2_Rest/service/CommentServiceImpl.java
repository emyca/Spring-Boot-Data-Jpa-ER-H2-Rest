package com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.service;

import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.dto.CommentDtoRequest;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.entity.Comment;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.entity.Post;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.exception.ResourceException;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.repository.CommentRepository;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("CommentServiceImpl")
public class CommentServiceImpl implements CommentService {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CommentRepository commentRepository;

    @Override
    public Comment createByPostId(Long id, CommentDtoRequest request)
            throws ResourceException {
        return postRepository.findById(id).map(post -> {
            Comment comment = new Comment();
            comment.setConsideration(request.consideration());
            post.getComments().add(comment);
            return commentRepository.save(comment);
        }).orElseThrow(() ->
                new ResourceException(
                        "Post with id %s has not been found!"
                                .formatted(id)));
    }

    @Override
    public List<Comment> getAllByPostId(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceException(
                                "Post with id %s has not been found!"
                                        .formatted(id)));
        return new ArrayList<>(post.getComments());
    }

    @Override
    public Comment getById(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceException(
                                "Comment with id %s has not been found!"
                                        .formatted(id)));
    }

    @Override
    public Comment updateById(Long id, CommentDtoRequest request) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceException(
                                "Comment with id %s has not been found!"
                                        .formatted(id)));
        comment.setConsideration(request.consideration());
        return commentRepository.save(comment);
    }

    @Override
    public boolean deleteById(Long id) {
        if (commentRepository.findById(id).isPresent()) {
            commentRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
