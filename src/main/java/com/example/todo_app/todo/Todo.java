package com.example.todo_app.todo;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

// @Entity maps our Class/Object to our database (Hibernate)
// @Table maps to a table

@Entity
@Table
public class Todo {

    // Using @SequenceGenerator
    // This generator can be used to automatically generate primary keys in entity classes. 
    // Additionally, it's often combined with the @GeneratedValue annotation to specify the strategy for generating primary key values.
    @Id
    @SequenceGenerator(name = "todo_sequence", sequenceName = "todo_sequence", allocationSize = 1)
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "todo_sequence"
    )

    private Long id;
    private String title;
    private String content;
    private LocalDate date;
    
    public Todo() {
    }

    public Todo(Long id, String title, String content, LocalDate date) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.date = date;
    }

    public Todo(String title, String content, LocalDate date) {
        this.title = title;
        this.content = content;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Todo [id=" + id + ", title=" + title + ", content=" + content + ", date=" + date + "]";
    }
}
