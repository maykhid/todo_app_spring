package com.example.todo_app.todo;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// @Entity maps our Class/Object to our database (Hibernate)
// @Table maps to a table
@Entity
@Table
@Data // Lombok annotation to generate getters, setters, toString, equals, and hashCode
@NoArgsConstructor // Lombok annotation to generate a no-args constructor
@AllArgsConstructor // Lombok annotation to generate an all-args constructor
// @RequiredArgsConstructor // Lombok annotation to generate a constructor for final fields
public class Todo {

    @Id
    @SequenceGenerator(name = "todo_sequence", sequenceName = "todo_sequence", allocationSize = 1)
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "todo_sequence"
    )

    private Long id;

    @NotEmpty
    private String title;

    @NotEmpty
    private String content;

    @DateTimeFormat
    private LocalDate date;

    // @ManyToOne
    // @JoinColumn(
    // name = "logger_id", 
    // referencedColumnName = "user_id")
    private Long userId;

    public Todo(String title, String content, LocalDate date) {
        this.title = title;
        this.content = content;
        this.date = date;
       
    }

    public Todo(String title, String content, LocalDate date, Long userId) {
        this.title = title;
        this.content = content;
        this.date = date;
        this.userId = userId;
    }

}
