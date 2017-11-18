package com.thoughtworks.demo;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class UserRepository {
  @PersistenceContext
  private EntityManager entityManager;

  public void create(User user) {
    entityManager.persist(user);
  }
}
