/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soulpage.service;

import com.soulpage.dao.TodoDAO;
import com.soulpage.dto.Todo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Charan Teja
 */
@Component
@Transactional
public class TodoServiceImpl implements TodoService {

    @Autowired
    private TodoDAO todoDao;

    @Override
    public List<Todo> getAllTodos() {
        List<Todo> list = new ArrayList<>();
        Map<Integer, List<Todo>> map = new HashMap<>();
        try {
            System.out.println(todoDao.getAllTodos().size());
            for (Todo item : todoDao.getAllTodos()) {
                if (item.getParent_id() != 0) {
                    if (!map.containsKey(item.getParent_id())) {
                        List<Todo> tempList = new ArrayList<>();
                        tempList.add(item);
                        map.put(item.getParent_id(), tempList);
                    } else {
                        map.get(item.getParent_id()).add(item);
                    }

                } else {
                    list.add(item);
                }
            }
            for (Todo item : list) {
                if (map.containsKey(item.getId())) {
                    item.setSubTodoList(map.get(item.getId()));
                }
            }
        } catch (Exception e) {
            System.out.println("Exception 2.." + e);
        }

        return list;
    }

    @Override
    public Todo getTodo(int todoId
    ) {
        return todoDao.getTodo(todoId);
    }

    @Override
    public Todo save(Todo todo
    ) {
        return todoDao.save(todo);
    }

    @Override
    public boolean update(Todo todo
    ) {
        return todoDao.update(todo);
    }

    @Override
    public boolean delete(int todoId
    ) {
        return todoDao.delete(todoId);
    }

    @Override
    public boolean updateFavourite(int id, boolean isFavourite) {
        return todoDao.updateFavourite(id, isFavourite);
    }

}
