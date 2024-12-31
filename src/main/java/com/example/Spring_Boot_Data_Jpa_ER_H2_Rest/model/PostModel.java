package com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.model;

import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.entity.Comment;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.entity.Post;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PostModel {
    private Long id;
    private String title;
    private String content;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Comment> comments;

    public static PostModel getModel(Post post) {
        PostModel model = new PostModel();
        model.setId(post.getId());
        model.setTitle(post.getTitle());
        model.setContent(post.getContent());
        List<Comment> commentList = new ArrayList<>();
        model.setComments(commentList);
        List<Comment> _commentList = post.getComments();
        if (_commentList != null) {
            for (Comment comment : _commentList) {
                Comment _comment = new Comment();
                _comment.setId(comment.getId());
                _comment.setConsideration(comment.getConsideration());
                model.getComments().add(_comment);
            }
            model.setComments(model.getComments());
        }
        return model;
    }
}
