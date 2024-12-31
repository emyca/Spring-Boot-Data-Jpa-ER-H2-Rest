package com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// @JsonIgnoreProperties — анотація на рівні класу,
// яка позначає властивість або список властивостей,
// які Jackson ігноруватиме.
// @JsonIgnoreProperties допомогає уникнути помилки
// при створенні об'єкту цього типу якщо якесь поле
// є null
@JsonIgnoreProperties(ignoreUnknown = true)
public record OpinionDtoRequest(
        Long id,
        String consideration) {
}
