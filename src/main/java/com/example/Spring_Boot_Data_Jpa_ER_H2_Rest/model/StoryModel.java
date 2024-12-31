package com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.model;

import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.entity.Story;
import lombok.Data;

@Data
public class StoryModel {
    private Long id;
    private String title;
    private String content;

    public static StoryModel getModel(Story story) {
        StoryModel model = new StoryModel();
        model.setId(story.getId());
        model.setTitle(story.getTitle());
        model.setContent(story.getContent());
        return model;
    }
}
