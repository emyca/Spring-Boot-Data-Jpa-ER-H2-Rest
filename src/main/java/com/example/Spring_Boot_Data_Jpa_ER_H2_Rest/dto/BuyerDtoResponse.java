package com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.dto;

// @JsonInclude: анотація, яка використовується для визначення того,
// чи певні «не-значення» (null або порожні значення) не слід включати
// під час серіалізації; може використовуватися як для кожної властивості,
// так і за замовчуванням для класу (для всіх властивостей класу).
// https://github.com/FasterXML/jackson-annotations/wiki/Jackson-Annotations
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.model.BuyerModel;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.util.List;

public record BuyerDtoResponse(
        int status,
        String reasonPhrase,
        boolean success,
        String message,
        @JsonInclude(Include.NON_NULL)
        BuyerModel buyer,
        @JsonInclude(Include.NON_NULL)
        List<BuyerModel> buyerList) {

    private BuyerDtoResponse(Builder builder) {
        this(
                builder.status,
                builder.reasonPhrase,
                builder.success,
                builder.message,
                builder.buyer,
                builder.buyerList
        );
    }

    public static class Builder {
        int status;
        String reasonPhrase;
        boolean success;
        String message;
        BuyerModel buyer;
        List<BuyerModel> buyerList;

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

        public Builder buyer(BuyerModel buyer) {
            this.buyer = buyer;
            return this;
        }

        public Builder buyerList(List<BuyerModel> buyerList) {
            this.buyerList = buyerList;
            return this;
        }

        public BuyerDtoResponse build() {
            return new BuyerDtoResponse(this);
        }
    }

    public enum Message {

        SUCCESS_CREATE_MSG("Buyer has been created successfully."),
        FAILURE_CREATE_MSG("Buyer has not been created."),
        SUCCESS_GET_LIST_MSG("Buyer list has been fetched successfully."),
        FAILURE_GET_LIST_MSG("Buyer list has not been found!"),
        SUCCESS_GET_BY_ID_MSG("Buyer with id %s has been fetched successfully."),
        FAILURE_GET_BY_ID_MSG("Buyer with id %s has not been found!"),
        SUCCESS_UPDATE_BY_ID_MSG("Buyer with id %s has been updated successfully."),
        SUCCESS_DELETE_BY_ID_MSG("Buyer with id %s has been deleted successfully.");

        private final String message;

        Message(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }
}

