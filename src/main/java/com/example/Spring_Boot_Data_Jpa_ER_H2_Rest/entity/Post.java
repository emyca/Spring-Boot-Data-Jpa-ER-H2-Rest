package com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity(name = "posts")
public class Post {
    @Id
    // GenerationType.IDENTITY покладається на IdentityGenerator,
    // який очікує значення, згенеровані стовпцем ідентичності в БД.
    // Ці значення автоматично збільшуються.
    // https://jakarta.ee/specifications/persistence/3.1/apidocs/jakarta.persistence/jakarta/persistence/generationtype
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "content")
    private String content;
    // @OneToMany визначає багатозначну асоціацію
    // з кратністю один-до-багатьох.
    // https://jakarta.ee/specifications/persistence/3.1/apidocs/jakarta.persistence/jakarta/persistence/onetomany
    // CascadeType.ALL є каскадним типом у Hibernate, який визначає,
    // що всі переходи між станами (створення, оновлення, видалення)
    // повинні каскадуватися від батьківської сутності до дочірніх
    // сутностей.
    // https://jakarta.ee/specifications/persistence/3.1/apidocs/jakarta.persistence/jakarta/persistence/cascadetype
    // fetch = FetchType.LAZY - (опціонально) чи асоціація повинна завантажуватися
    // ліниво чи має бути охоче (EAGER) завантажена.
    // https://jakarta.ee/specifications/persistence/3.1/apidocs/jakarta.persistence/jakarta/persistence/onetomany#fetch()
    // orphanRemoval = true допомагає видалити дочірню сутність (Comment запис) із БД,
    // якщо видаляємо її з батьківської колекції (List<Comment> comments). Опціонально.
    // https://jakarta.ee/specifications/persistence/3.1/apidocs/jakarta.persistence/jakarta/persistence/onetomany#orphanRemoval()
    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true)
    // @JoinColumn використовується на стороні власника асоціації
    // для визначення імені стовпця зовнішнього ключа та інших атрибутів,
    // пов’язаних зі стовпцем об’єднання.
    // https://jakarta.ee/specifications/persistence/3.1/apidocs/jakarta.persistence/jakarta/persistence/joincolumn
    @JoinColumn(name = "post_id")
    private List<Comment> comments;
}
