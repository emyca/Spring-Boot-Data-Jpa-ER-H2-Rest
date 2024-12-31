package com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity(name = "addresses")
public class Address {
    @Id
    // GenerationType.IDENTITY покладається на IdentityGenerator,
    // який очікує значення, згенеровані стовпцем ідентичності в БД.
    // Ці значення автоматично збільшуються.
    // https://jakarta.ee/specifications/persistence/3.1/apidocs/jakarta.persistence/jakarta/persistence/generationtype
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "city")
    private String city;
    @Column(name = "street")
    private String street;
    @Column(name = "building")
    private String building;
    @Column(name = "apartment")
    private String apartment;
    // @OneToOne визначає однозначну асоціацію з іншою сутністю,
    // яка має кратність один-до-одного.
    // https://jakarta.ee/specifications/persistence/3.1/apidocs/jakarta.persistence/jakarta/persistence/onetoone
    // Атрибут mappedBy використовується для визначення сторони посилання
    // (сторони, яка не є власником, non-owning side) зв’язку/асоціації.
    // https://jakarta.ee/specifications/persistence/3.1/apidocs/jakarta.persistence/jakarta/persistence/onetoone#mappedBy()
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "address")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Buyer buyer;
}
