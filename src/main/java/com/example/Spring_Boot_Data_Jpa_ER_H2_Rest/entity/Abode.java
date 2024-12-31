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
@Entity(name = "abodes")
public class Abode {
    @Id
    // УВАГА. @GeneratedValue(strategy = GenerationType.IDENTITY) з @Id
    // не використовуємо, оскільки не потрібно генерувати ідентифікатор
    // для цієї сутності.
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
    @OneToOne
    // @JoinColumn позначає стовпець як стовпець об’єднання
    // для асоціації сутності або колекції елементів.
    // https://jakarta.ee/specifications/persistence/3.1/apidocs/jakarta.persistence/jakarta/persistence/joincolumn
    @JoinColumn(name = "id")
    // @MapsId спрощує зв’язки Один-до-Одного, дозволяючи двом
    // об’єктам спільно використовувати один первинний ключ.
    // Це допомагає реалізувати стратегію спільного первинного ключа,
    // повідомляючи Hibernate зіставляти первинний ключ дочірньої
    // сутності з первинним ключем пов’язаної з нею батьківської сутності.
    // @OneToOne та @MapsId вказують на те, що і вихідна, і цільова сутності
    // мають спільні значення первинного ключа.
    // https://jakarta.ee/specifications/persistence/3.1/apidocs/jakarta.persistence/jakarta/persistence/mapsid
    @MapsId
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Vendee vendee;
}
