package com.example.todo_app.todo;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;


@RestController
@RequestMapping(path = "api/v1/todos")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    /// get todo by id
    @GetMapping
	 public Todo getTodo(@RequestParam(name = "id") Long id) {
		return todoService.getTodo(id).get();
	}

    @GetMapping("/all")
	 public List<Todo> getAllTodos() {
		return todoService.getTodos();
	}

    @GetMapping("/me")
    public ResponseEntity<List<Todo>> getTodos(Authentication authentication) throws Exception{
        // String username = authentication.getName();
        // System.out.println("The User: "+authentication.getDetails());
        List<Todo> todos = todoService.getTodosByUser(authentication);
        return ResponseEntity.ok(todos);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addTodo(@Valid @RequestBody Todo todo, Authentication authentication) {
       todoService.addTodo(todo, authentication);
   }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping
    public void deleteTodo(@RequestParam(name = "id") Long id)  {
       todoService.deleteTodo(id);
   }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping
    public void updateTodo( @Valid @RequestBody Todo todo) {
       todoService.updateTodo( todo);
   }
}
