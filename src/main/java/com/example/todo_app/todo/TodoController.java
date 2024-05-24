package com.example.todo_app.todo;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping(path = "api/v1/todos")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    /// get todo by id
    @GetMapping("/{id}")
	 public Todo getTodo(@PathVariable("id") Long id) {
		return todoService.getTodo(id);
	}

    @GetMapping
	 public List<Todo> getTodos() {
		return todoService.getTodos();
	}

    @PostMapping
    public void addTodo(@RequestBody Todo todo) {
       todoService.addTodo(todo);
   }

    @DeleteMapping("/{id}")
    public void deleteTodo(@PathVariable("id") Long id) {
       todoService.deleteTodo(id);
   }

//     @PutMapping("/{id}")
//     public void updateTodo() {
//        todoService.updateTodo(id);
//    }
}
