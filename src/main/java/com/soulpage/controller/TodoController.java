/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soulpage.controller;

import com.soulpage.dto.Todo;
import com.soulpage.service.TodoService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @RestController indicates combination of @Controller and @ResponseBody
 * annotation(indicates the response will be XML or JSON not HTML view).
 * @CrossOrigin : To enable cross-origin requests
 * @author charan
 */
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TodoController {

    @Autowired
    private TodoService todoService;

    private static final Logger LOGGER = LogManager.getLogger();

    @RequestMapping(value = "/hello")
    public String hello() {
        return "Hello";
    }

    @GetMapping("/todos")
    public ResponseEntity<List<Todo>> getAllTodos() {
        try {
            System.out.println("In getAllTodos()..");
            List<Todo> todoList = todoService.getAllTodos();
            return new ResponseEntity<>(todoList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/todos/{id}")
    public ResponseEntity<Todo> getTodoById(@PathVariable(value = "id") int todoId) {
        try {
            System.out.println("In getTodoById()..");
            Todo todo = todoService.getTodo(todoId);
            return new ResponseEntity<>(todo, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/todos")
    public ResponseEntity<Todo> saveTodo(@RequestBody Todo todo) {
        try {
            System.out.println("In saveTodo()..");
            Todo todoWithId = todoService.save(todo);
            return new ResponseEntity<>(todoWithId, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/todos/{id}")
    public ResponseEntity<Boolean> updateTodo(@PathVariable(value = "id") int todoId,
            @RequestBody Todo todo) {
        try {
            boolean result = false;
            System.out.println("In updateTodo()..");
//            We can also get DTO by Id and call save method.
            Todo isFound = todoService.getTodo(todoId);
            if (isFound != null) {
                result = todoService.update(todo);
            }

            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/todos/{id}")
    public ResponseEntity<Boolean> deleteTodo(@PathVariable(value = "id") int todoId) {
        try {
            System.out.println("In deleteTodo()..");
//            We can also get DTO by Id and call save method.
            boolean result = todoService.delete(todoId);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/todos/isFav/{id}")
    public ResponseEntity<Boolean> updateFavourite(@PathVariable(value = "id") int todoId, HttpServletRequest request) {
        try {
            System.out.println("In updateFavourite()..");
//            We can also get DTO by Id and call save method.
            boolean result = todoService.updateFavourite(todoId, Boolean.parseBoolean(request.getParameter("isFavourite")));
            System.out.println("in updateFacourite...resukt" + result);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
