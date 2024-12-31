package com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.dto;

import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.model.OpinionModel;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

public record OpinionDtoResponse(
        int status,
        String reasonPhrase,
        boolean success,
        String message,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        Long storyId,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        OpinionModel opinion,
        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        List<OpinionModel> opinions) {

    private OpinionDtoResponse(Builder builder) {
        this(
                builder.status,
                builder.reasonPhrase,
                builder.success,
                builder.message,
                builder.storyId,
                builder.opinion,
                builder.opinions
        );
    }

    public static class Builder {
        int status;
        String reasonPhrase;
        boolean success;
        String message;
        Long storyId;
        OpinionModel opinion;
        List<OpinionModel> opinions;

        public Builder status(int status) {
            this.status = status;
            return this;
        }

        public Builder reasonPhrase(String reasonPhrase) {
            this.reasonPhrase = reasonPhrase;
            return this;
        }

        public Builder success(boolean success) {
            this.success = success;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder storyId(Long storyId) {
            this.storyId = storyId;
            return this;
        }

        public Builder opinion(OpinionModel opinion) {
            this.opinion = opinion;
            return this;
        }

        public Builder opinions(List<OpinionModel> opinions) {
            this.opinions = opinions;
            return this;
        }

        public OpinionDtoResponse build() {
            return new OpinionDtoResponse(this);
        }
    }

    public enum Message {

        SUCCESS_CREATE_MSG("Opinion has been created successfully."),
        FAILURE_CREATE_MSG("Opinion has not been created."),
        SUCCESS_GET_LIST_MSG("Opinions have been fetched successfully."),
        FAILURE_GET_LIST_MSG("Opinions have not been found!"),
        SUCCESS_GET_BY_ID_MSG("Opinion with id %s has been fetched successfully."),
        FAILURE_GET_BY_ID_MSG("Opinion with id %s has not been found!"),
        SUCCESS_UPDATE_BY_ID_MSG("Opinion with id %s has been updated successfully."),
        SUCCESS_DELETE_BY_ID_MSG("Opinion with id %s has been deleted successfully.");

        private final String message;

        Message(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }
}
