
ВЗАЄМОВІДНОСИНИ СУТНОСТЕЙ (Entity Relationships), в
базі даних (БД), надають уявлення як дані взаємопов’язані
в системі (додатку).

Таблиці в реляційній БД є фізичним представленням сутностей.
Рядки певної таблиці є екземплярами цієї певної сутності.

Асоціації (співставлення) є фундаментальною концепцією
в ORM, що дозволяє визначати зв’язки між сутностями.

Існують певні технічни терміни ORM щодо взаємовідносин
сторін (сутностей). Сторона-власник (owning side) ініціює
створення зв'язку. Як правило, це сторона, де знаходиться
зовнішній ключ (foreign key). Інша сторона є зворотною/посилальною
стороною (inverse/referencing side).

Є різні типи співставленнь сутностей через JPA.

Один-до-Одного (One-to-One).
Екземпляр однієї сутності пов’язаний лише з одним екземпляром
іншої сутності. Кожен рядок однієї таблиці пов’язаний з лише
одним рядком іншої таблиці.

Способи співставлення Один-до-Одного в JPA:
- Через зовнішній ключ (foreign key).
- Через спільний первинний ключ (shared primary key).
- Через таблицю об'єднань (join table).

Один-до-Багатьох (One-to-Many).
Сутність пов’язується з одним або багатьма екземплярами
іншої сутності. Один рядок таблиці зіставляється з кількома
рядками в іншій таблиці.

Багато-до-Одного (Many-to-One).
Багато екземплярів однієї сутності пов’язані з одним
екземпляром іншої сутності. Кілька рядків однієї таблиці
пов’язані з одним рядком іншої таблиці.

Багато-до-Багатьох (Many-to-Many).
Багато екземплярів однієї сутності пов’язані з багатьма
екземплярами іншої сутності. Кілька рядків однієї
таблиці пов’язані з кількома рядками в іншій таблиці.

----------------------

СПРЯМОВАНІСТЬ ВІДНОШЕНЬ В JPA.

Односпрямоване зіставлення (Unidirectional Mapping).
Відносини між сутностями, де лише одна сторона відносин
знає іншу. Тобто, одна сутність посилається на іншу сутність,
але сутність, на яку посилаються, не знає про зв’язок.

Двонаправлене зіставлення (Bidirectional Mapping).
Встановлення зв’язків між сутностями в обох напрямках, дозволяючи
навігацію з обох сторін зв’язку.

----------------------

РЕАЛІЗАЦІЯ ЧЕРЕЗ Hibernate.

Анотація @OneToOne використовується для створення зв’язку
Один-до-Одного між двома об’єктами. @OneToOne вказує на те,
що один екземпляр сутності пов’язаний лише з одним екземпляром
іншої сутності. Коли анотуємо поле або метод анотацією @OneToOne,
Hibernate створить зв’язок Один-до-Одного між цими двома сутностями.
Можемо використовувати анотацію @OneToOne з будь-якої сторони
зв’язку залежно від спрямованості зв’язку.
https://jakarta.ee/specifications/persistence/3.1/apidocs/jakarta.persistence/jakarta/persistence/onetoone

Анотація @OneToMany використовується для отримання зв’язків
Oдин-до-Багатьох між двома сутностями. @OneToMany використовується
для зіставлення асоціації зі значенням колекції, де один екземпляр
сутності зіставляється з кількома екземплярами іншої сутності.
https://jakarta.ee/specifications/persistence/3.1/apidocs/jakarta.persistence/jakarta/persistence/onetomany

Анотація @ManyToOne використовується для створення зв’язку
Багато-до-Одного між двома об’єктами. @ManyToOne вказує, що
багато екземплярів однієї сутності можуть бути пов’язані лише
з одним екземпляром іншої сутності. Коли анотуємо поле або метод
анотацією @ManyToOne, Hibernate створить відношення Багато-до-Одного
між цими двома сутностями. @ManyToOne використовується для
зіставлення анотації з одним значенням, де кілька екземплярів
однієї сутності пов’язано з одним екземпляром іншої сутності.
https://jakarta.ee/specifications/persistence/3.1/apidocs/jakarta.persistence/jakarta/persistence/manytoone

@OneToMany vs @ManyToOne.
Необхідно мати можливість повідомити Hibernate, який об’єкт
є дочірнім/багатьма частинами зв’язку, а який об’єкт —
батьківським/одною стороною зв’язку.
Через @OneToMany повідомляємо Hibernate, який об’єкт є батьківським,
а через @ManyToOne повідомляємо Hibernate, який об’єкт є дочірнім.

Анотація @ManyToMany використовується для отримання зв’язків
Багато-до-Багатьох між двома сутностями. Це дозволяє створити
двонаправлений зв’язок між двома сутностями, де кожна сутність
може бути пов’язана з іншою сутністю через кілька екземплярів.
https://jakarta.ee/specifications/persistence/3.1/apidocs/jakarta.persistence/jakarta/persistence/manytomany

----------------------

ТЕХСТЕК

Spring Boot
https://spring.io/projects/spring-boot

Spring Data JPA
https://spring.io/projects/spring-data-jpa

H2 Database Engine
https://www.h2database.com/html/main.html

Lombok
https://projectlombok.org/

----------------------

JAVA ПРОЕКТ

УВАГА. Демо-проект зроблено у вигляді RESTful додатку.
Фокусується, здебільшого, на взаємовідносинах сутностей.

(1) Переходимо до Spring Initializr
https://start.spring.io/

(2) Конфігурація проекту
(цифрові позначення версій можуть
змінюватись з розвитком фреймворку):
  (a) Project: Maven
  (b) Spring Boot: обираємо стабільну версію
  (c) Project Metadata:
    Group: залишаємо так
    Artifact: Spring-Boot-Data-Jpa-ER-H2-Rest
    Name: AppDemo
    Description: залишаємо так
    Packaging name: залишаємо так
    Packaging: Jar
    Java: 21
  (d) Dependencies:
    Spring Boot DevTools
    Lombok
    Spring Web
    Spring Data JPA
    H2 Database

(3) Перевіряємо. Клікаємо GENERATE.

(4) Зформований zip-файл розпакуємо,
переміщуємо в папку наших проектів.

(5) Відкриваємо проект в IDE.
Досліджуємо
	src/main/
    pom.xml

(6) В src/main формуємо та структуруємо
необхідний контент.

-----------------------

ТЕСТУВАННЯ ПРОГРАМИ

В IDE через AppDemoApplication запускаємо програму.
В консолі IDE має бути відповідна інформація.

В Web-браузері запускаємо H2 Database Engine
http://localhost:8080/h2-console

Конектимося до БД.

Тестуємо функціонал програми через API платформу.

-----------------------

РЕСУРСИ

https://www.baeldung.com/spring-boot-devtools
https://www.baeldung.com/the-persistence-layer-with-spring-data-jpa
https://www.baeldung.com/spring-data-repositories
https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/http/ResponseEntity.html
https://www.baeldung.com/spring-response-entity
https://www.baeldung.com/hibernate-identifiers
https://www.baeldung.com/jpa-hibernate-associations
https://www.baeldung.com/jpa-one-to-one
https://www.baeldung.com/hibernate-one-to-many
https://www.baeldung.com/jpa-many-to-many
https://www.baeldung.com/jpa-join-column
https://www.baeldung.com/jpa-joincolumn-vs-mappedby
https://github.com/FasterXML/jackson-annotations/wiki/Jackson-Annotations
https://www.baeldung.com/jackson-annotations
https://www.baeldung.com/jackson-ignore-null-fields
https://www.baeldung.com/jpa-cascade-types
https://www.freecodecamp.org/news/crows-foot-notation-relationship-symbols-and-how-to-read-diagrams/
https://medium.com/@marcifey/using-crows-foot-notation-in-an-erd-2910fff5dd05
https://www.baeldung.com/java-jpa-join-vs-primarykeyjoin
https://www.baeldung.com/hibernate-mapsid-annotation
https://www.baeldung.com/java-serial-version-uid
https://vladmihalcea.com/the-best-way-to-use-the-manytomany-annotation-with-jpa-and-hibernate/
https://vladmihalcea.com/a-beginners-guide-to-jpa-and-hibernate-cascade-types/
https://vladmihalcea.com/jpa-hibernate-synchronize-bidirectional-entity-associations/

