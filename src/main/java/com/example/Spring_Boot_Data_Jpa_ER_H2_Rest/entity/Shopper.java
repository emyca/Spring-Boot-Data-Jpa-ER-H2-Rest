package com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity(name = "shoppers")
public class Shopper {
    @Id
    // GenerationType.IDENTITY покладається на IdentityGenerator,
    // який очікує значення, згенеровані стовпцем ідентичності в БД.
    // Ці значення автоматично збільшуються.
    // https://jakarta.ee/specifications/persistence/3.1/apidocs/jakarta.persistence/jakarta/persistence/generationtype
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private String email;
    // @OneToOne визначає однозначну асоціацію з іншою сутністю,
    // яка має кратність один-до-одного.
    // https://jakarta.ee/specifications/persistence/3.1/apidocs/jakarta.persistence/jakarta/persistence/onetoone
    // CascadeType.ALL є каскадним типом у Hibernate, який визначає,
    // що всі переходи між станами (створення, оновлення, видалення)
    // повинні каскадуватися від батьківської сутності до дочірніх
    // сутностей.
    // https://jakarta.ee/specifications/persistence/3.1/apidocs/jakarta.persistence/jakarta/persistence/cascadetype
    @OneToOne(cascade = CascadeType.ALL)
    // @JoinTable визначає деталі таблиці об’єднання (Join table)
    // між сутностями Shopper та Place.
    // Визначає назву таблиці об’єднання (shopper_place) та імена стовпців
    // зовнішнього ключа (foreign key) в таблиці об’єднання (shopper_id та place_id).
    // https://jakarta.ee/specifications/persistence/3.1/apidocs/jakarta.persistence/jakarta/persistence/jointable
    // @JoinColumn позначає стовпець як стовпець об’єднання
    // для асоціації сутності або колекції елементів.
    // joinColumns використовується для встановлення посилання
    // на сутність Shopper, а inverseJoinColumns посилається
    // на сутність Place.
    // З точки зору Shopper, стовпець id таблиці shoppers матиме
    // зв’язок зовнішнього ключа зі стовпцем shopper_id таблиці shopper_place.
    // Атрибут inverseJoinColumns(place_id) посилається на іншу сторону Shopper,
    // яка є сутністю Place.
    // https://jakarta.ee/specifications/persistence/3.1/apidocs/jakarta.persistence/jakarta/persistence/joincolumn
    @JoinTable(
            // Найменування таблиці об’єднання (Join table).
            name = "shopper_place",
            joinColumns = {@JoinColumn(
                    // Стовпчик таблиці shopper_place
                    name = "shopper_id",
                    // Стовпчик таблиці shoppers
                    referencedColumnName = "id")
            },
            inverseJoinColumns = {@JoinColumn(
                    // Стовпчик таблиці shopper_place
                    name = "place_id",
                    // Стовпчик таблиці places
                    referencedColumnName = "id")
            }
    )
    private Place place;
}
