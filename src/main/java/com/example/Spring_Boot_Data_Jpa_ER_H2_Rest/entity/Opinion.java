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
@Entity(name = "opinions")
public class Opinion {
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
    // @ManyToOne визначає однозначну асоціацію
    // з іншим класом сутності, який має кратність
    // багато-до-одного.
    // https://jakarta.ee/specifications/persistence/3.1/apidocs/jakarta.persistence/jakarta/persistence/manytoone
    @ManyToOne
    // @JoinColumn використовується на стороні власника асоціації
    // для визначення імені стовпця зовнішнього ключа та інших атрибутів,
    // пов’язаних зі стовпцем об’єднання.
    // https://jakarta.ee/specifications/persistence/3.1/apidocs/jakarta.persistence/jakarta/persistence/joincolumn
    @JoinColumn(name = "story_id")
    private Story story;
}
