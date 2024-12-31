package com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity(name = "students")
public class Student {
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
    // @ManyToMany визначає багатозначну асоціацію
    // з множинністю багато-до-багатьох.
    // https://jakarta.ee/specifications/persistence/3.1/apidocs/jakarta.persistence/jakarta/persistence/manytomany
    // FetchType визначає стратегії отримання даних із бази даних.
    // https://jakarta.ee/specifications/persistence/3.1/apidocs/jakarta.persistence/jakarta/persistence/fetchtype
    // CascadeType визначає набір каскадних операцій,
    // які поширюються на пов’язану сутність.
    // https://jakarta.ee/specifications/persistence/3.1/apidocs/jakarta.persistence/jakarta/persistence/cascadetype
    // УВАГА. Для цієї сутності визначено лише типи каскаду
    // PERSIST і MERGE. Перехід стану сутності REMOVE не має сенсу
    // для асоціації @ManyToMany, оскільки вона може спровокувати
    // видалення ланцюжка, яке в кінцевому підсумку стерло б обидві
    // сторони асоціації.
    // https://vladmihalcea.com/the-best-way-to-use-the-manytomany-annotation-with-jpa-and-hibernate/
    // https://vladmihalcea.com/a-beginners-guide-to-jpa-and-hibernate-cascade-types/
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            // Найменування таблиці об’єднання (Join table).
            // Буде створюватись програмою.
            name = "student_course",
            joinColumns = @JoinColumn(
                    // Стовпчик таблиці student_course
                    name = "student_id"
            ),
            inverseJoinColumns = @JoinColumn(
                    // Стовпчик таблиці student_course
                    name = "course_id"
            )
    )
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    Set<Course> courses = new HashSet<>();

    // УВАГА. Утилітні методи додавання/видалення є обов’язковими,
    // при двонаправленій (bidirectional) асоціації, щоб переконатися,
    // що обидві сторони асоціації синхронізовані.
    // https://vladmihalcea.com/the-best-way-to-use-the-manytomany-annotation-with-jpa-and-hibernate/
    // https://vladmihalcea.com/jpa-hibernate-synchronize-bidirectional-entity-associations/

    public void addCourse(Course course) {
        this.courses.add(course);
        course.getStudents().add(this);
    }

    public void removeCourse(long courseId) {
        Course course =
                this.courses.stream()
                        .filter(_course ->
                                _course.getId() == courseId)
                        .findFirst().orElse(null);
        if (course != null) {
            this.courses.remove(course);
            course.getStudents().remove(this);
        }
    }
}
