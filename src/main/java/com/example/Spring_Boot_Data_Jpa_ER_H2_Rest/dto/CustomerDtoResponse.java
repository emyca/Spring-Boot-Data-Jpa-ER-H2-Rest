package com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.dto;

// @JsonInclude: анотація, яка використовується для визначення того,
// чи певні «не-значення» (null або порожні значення) не слід включати
// під час серіалізації; може використовуватися як для кожної властивості,
// так і за замовчуванням для класу (для всіх властивостей класу).
// https://github.com/FasterXML/jackson-annotations/wiki/Jackson-Annotations
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.model.CustomerModel;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.util.List;

public record CustomerDtoResponse(
        int status,
        String reasonPhrase,
        boolean success,
        String message,
        @JsonInclude(Include.NON_NULL)
        CustomerModel customer,
        @JsonInclude(Include.NON_NULL)
        List<CustomerModel> customerList) {

    private CustomerDtoResponse(Builder builder) {
        this(
                builder.status,
                builder.reasonPhrase,
                builder.success,
                builder.message,
                builder.customer,
                builder.customerList
        );
    }

    public static class Builder {
        int status;
        String reasonPhrase;
        boolean success;
        String message;
        CustomerModel customer;
        List<CustomerModel> customerList;

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

        public Builder customer(CustomerModel customer) {
            this.customer = customer;
            return this;
        }

        public Builder customerList(List<CustomerModel> customerList) {
            this.customerList = customerList;
            return this;
        }

        public CustomerDtoResponse build() {
            return new CustomerDtoResponse(this);
        }
    }

    public enum Message {

        SUCCESS_CREATE_MSG("Customer has been created successfully."),
        FAILURE_CREATE_MSG("Customer has not been created!"),
        SUCCESS_GET_LIST_MSG("Customer list has been fetched successfully."),
        FAILURE_GET_LIST_MSG("Customer list has not been found!"),
        SUCCESS_GET_BY_ID_MSG("Customer with id %s has been fetched successfully."),
        FAILURE_GET_BY_ID_MSG("Customer with id %s has not been found!"),
        SUCCESS_UPDATE_BY_ID_MSG("Customer with id %s has been updated successfully."),
        SUCCESS_DELETE_BY_ID_MSG("Customer with id %s has been deleted successfully.");

        private final String message;

        Message(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }
}
