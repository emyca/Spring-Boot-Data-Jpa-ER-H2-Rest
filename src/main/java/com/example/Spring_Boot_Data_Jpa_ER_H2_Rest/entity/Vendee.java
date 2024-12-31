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
@Entity(name = "vendees")
public class Vendee {
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
    // яка має кратність один до одного.
    // https://jakarta.ee/specifications/persistence/3.1/apidocs/jakarta.persistence/jakarta/persistence/onetoone
    // Атрибут mappedBy використовується для визначення сторони посилання
    // (сторони, яка не є власником, non-owning side) зв’язку/асоціації.
    // https://jakarta.ee/specifications/persistence/3.1/apidocs/jakarta.persistence/jakarta/persistence/onetoone#mappedBy()
    // CascadeType.ALL є каскадним типом у Hibernate, який визначає,
    // що всі переходи між станами (створення, оновлення, видалення)
    // повинні каскадуватися від батьківської сутності до дочірніх
    // сутностей.
    // Тобто щоразу, коли маніпулюємо сутністю Vendee, також
    // відповідно маніпулюємо сутністю Abode.
    // https://jakarta.ee/specifications/persistence/3.1/apidocs/jakarta.persistence/jakarta/persistence/cascadetype
    @OneToOne(mappedBy = "vendee", cascade = CascadeType.ALL)
    private Abode abode;
}
