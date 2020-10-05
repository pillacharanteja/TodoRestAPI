/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soulpage.dao;

import com.soulpage.dto.Todo;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

/**
 * Below class uses Criteria API for persisting and retrieving of data.
 *
 * @author Charan Teja
 */
@Repository //will indicate our Spring context that we want to add a Dao bean
public class TodoDaoImpl implements TodoDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Todo> getAllTodos() {

        try {

            CriteriaBuilder builder = entityManager.getCriteriaBuilder();

            CriteriaQuery<Todo> criteria = builder.createQuery(Todo.class);

            Root<Todo> root = criteria.from(Todo.class);//Roots define the basis from which all joins, paths and attributes are available in the servicesQ.
//            Predicate paretnIdCheck = builder.equal(root.get("parent_id"), 0); // Checking for only parent Todo
//            criteria.where(paretnIdCheck);
            criteria.select(root);

            return entityManager.createQuery(criteria).getResultList();

        } catch (Exception e) {
            System.out.println("Exception in getAllTodos().." + e);
            return null;
        }
    }

    @Override
    public Todo getTodo(int todoId) {
        try {
            return entityManager.find(Todo.class, todoId);
        } catch (Exception e) {
            System.out.println("Exception in getTodo().." + e);
            return null;
        }
    }

    @Override
    public Todo save(Todo todo) {
        try {
            entityManager.persist(todo);
            return todo;

        } catch (Exception e) {
            System.out.println("Exception in save().." + e);
        }
        return null;
    }

    @Override
    public boolean update(Todo todo) {
        try {

            Query query = entityManager.createNativeQuery("update todo set description =?, name=?, parent_id=?, isFavourite= ? where id=?");
            query.setParameter(1, todo.getDescription());
            query.setParameter(2, todo.getName());
            query.setParameter(3, todo.getParent_id());
            query.setParameter(4, todo.isIsFavourite());
            query.setParameter(5, todo.getId());

            if (query.executeUpdate() > 0) {
                return true;
            }

        } catch (Exception e) {
            System.out.println("Exception in update().." + e);
        }
        return false;
    }

    @Override
    public boolean delete(int todoId) {
        try {

            CriteriaBuilder builder = entityManager.getCriteriaBuilder();

            CriteriaDelete<Todo> delete = builder.createCriteriaDelete(Todo.class);

            Root<Todo> root = delete.from(Todo.class);

            Predicate condition = builder.equal(root.get("id"), todoId);

            delete.where(condition);

            int rowsEffected = entityManager.createQuery(delete).executeUpdate();

            if (rowsEffected > 0) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("Exception in delete().." + e);
        }
        return false;
    }

    @Override
    public boolean updateFavourite(int id, boolean isFavourite) {
        try {
            System.out.println("updateFavourite()..id.." + id + "..isFavourite.." + isFavourite);
            Query query = entityManager.createNativeQuery("update todo set isFavourite= ? where id=?");
            query.setParameter(1, isFavourite ? 1 : 0);
            query.setParameter(2, id);

            if (query.executeUpdate() > 0) {
                return true;
            }

        } catch (Exception e) {
            System.out.println("Exceptio in updateFavourite().." + e);
        }
        return false;
    }

}
