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
@Entity(name = "consumers")
public class Consumer {
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
    // Тобто щоразу, коли маніпулюємо сутністю Consumer, також
    // відповідно маніпулюємо сутністю Domicile.
    // https://jakarta.ee/specifications/persistence/3.1/apidocs/jakarta.persistence/jakarta/persistence/cascadetype
    @OneToOne(cascade = CascadeType.ALL)
    // @MapsId спрощує зв’язки Один-до-Одного, дозволяючи двом
    // об’єктам спільно використовувати один первинний ключ.
    // Це допомагає реалізувати стратегію спільного первинного ключа,
    // повідомляючи Hibernate зіставляти первинний ключ дочірньої
    // сутності з первинним ключем пов’язаної з нею батьківської сутності.
    // @OneToOne та @MapsId вказують на те, що і вихідна, і цільова сутності
    // мають спільні значення первинного ключа.
    // Сутність Domicile не містить жодних посилань на Consumer,
    // оскільки це односпрямований зв’язок (від Consumer до Domicile).
    // https://jakarta.ee/specifications/persistence/3.1/apidocs/jakarta.persistence/jakarta/persistence/mapsid
    @MapsId
    private Domicile domicile;
}
