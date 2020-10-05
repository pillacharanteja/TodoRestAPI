/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soulpage.dao;

import com.soulpage.dto.Todo;
import java.util.List;

/**
 *
 * @author Charan Teja
 */
public interface TodoDAO {

    public List<Todo> getAllTodos();

    public Todo getTodo(int todoId);

    public Todo save(Todo todo);

    public boolean update(Todo todo);

    public boolean delete(int todoId);

    public boolean updateFavourite(int id, boolean isFavourite);

}
