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
@Entity(name = "remarks")
public class Remark {
    @Id
    // GenerationType.IDENTITY покладається на IdentityGenerator,
    // який очікує значення, згенеровані стовпцем ідентичності в БД.
    // Ці значення автоматично збільшуються.
    // https://jakarta.ee/specifications/persistence/3.1/apidocs/jakarta.persistence/jakarta/persistence/generationtype
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "consideration")
    private String consideration;
    // @ManyToOne визначає однозначну асоціацію з іншим класом сутності,
    // який має кратність багато-до-одного. Якщо зв’язок є двонаправленим,
    // сторона сутності OneToMany, яка не є власником, повинна використовувати
    // елемент mappedBy для визначення поля зв’язку або властивості сутності,
    // яка є власником зв’язку.
    // https://jakarta.ee/specifications/persistence/3.1/apidocs/jakarta.persistence/jakarta/persistence/manytoone
    // fetch = FetchType.LAZY - (опціонально) чи асоціація повинна завантажуватися
    // ліниво чи має бути охоче (EAGER) завантажена.
    // https://jakarta.ee/specifications/persistence/3.1/apidocs/jakarta.persistence/jakarta/persistence/manytoone#fetch()
    @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn визначає стовпець для приєднання до
    // асоціації сутності або колекції елементів.
    // Визначає стовпець зовнішнього ключа.
    // https://jakarta.ee/specifications/persistence/3.1/apidocs/jakarta.persistence/jakarta/persistence/joincolumn
    @JoinColumn(
            // Ім'я стовпця зовнішнього ключа.
            name = "article_id",
            // Ім’я стовпця, на який посилається стовпець
            // зовнішнього ключа.
            referencedColumnName = "id"
    )
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Article article;
}
