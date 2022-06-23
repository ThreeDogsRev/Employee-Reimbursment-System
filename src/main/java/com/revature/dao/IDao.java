package com.revature.dao;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.revature.utils.SessionHelper;


public interface IDao<T> {
  // Create
  /**
   * 
   * @param entity, the item to insert into the database
   * @return the item that was inserted into the DB
   */
  default Integer insert(T entity) {
    try {
      Session session = SessionHelper.getSession();
      session.beginTransaction();
      Integer id = (Integer) session.save(entity);
      session.getTransaction().commit();
      return id;
    } catch (HibernateException e) {
      e.printStackTrace();
    }
    return 0;
  }

  // Read
  
  /**
   * @return a list of all items in the DB
   */
  List<T> selectAll();

  /**
   * @param id, the id of the item to select
   * @return the item with the id that was passed in
   */
  T selectById(int id);

  // Update

  /**
   * @param entity, the item to update in the DB
   * @return the item that was updated in the DB
   */
  default T update(T entity){
      try {
        Session session = SessionHelper.getSession();
        session.beginTransaction();
        session.update(entity);
        session.getTransaction().commit();
        return entity;
      } catch (HibernateException e) {
        e.printStackTrace();
      }
      return null;
    }

  // Delete
  /**
   * @param entity, the item to delete from the DB
   * @return the item that was deleted from the DB
   */
  default T delete(T entity){
      try {
        Session session = SessionHelper.getSession();
        session.beginTransaction();
        session.delete(entity);
        session.getTransaction().commit();
        return entity;
      } catch (HibernateException e) {
        e.printStackTrace();
      }
      return null;
    }
  }