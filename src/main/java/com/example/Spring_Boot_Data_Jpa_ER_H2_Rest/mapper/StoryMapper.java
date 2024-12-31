package com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.mapper;

import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.dto.StoryDtoRequest;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.entity.Story;
import org.springframework.stereotype.Component;

@Component
public class StoryMapper {

    public Story dtoCreateToEntity(StoryDtoRequest request) {
        Story story = new Story();
        story.setId(request.id());
        story.setTitle(request.title());
        story.setContent(request.content());
        return story;
    }

    public Story dtoUpdateByIdToEntity(StoryDtoRequest request,
                                       Story storyToUpdate) {
        storyToUpdate.setTitle(request.title());
        storyToUpdate.setContent(request.content());
        return storyToUpdate;
    }
}
