package cn.edu.sdut.softlab.hibernate.tutorial;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


/**
 * Client to show JPA.
 * @author subaochen
 */
public class Client {

  /**
   * main method.
   * @param args args
   */
  public static void main(String[] args) {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("hibernate.tutorial.jpa");
    // create a couple of articles...
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    entityManager.getTransaction().begin();
    entityManager.persist(new Article("Our very first article by hibernate entityManager provider!", new Date()));
    entityManager.persist(new Article("A follow up article by hibernate entityManager provider", new Date()));
    entityManager.getTransaction().commit();
    entityManager.close();

    // now lets pull articles from the database and list them
    entityManager = entityManagerFactory.createEntityManager();
    entityManager.getTransaction().begin();
    List<Article> result = entityManager.createQuery("from Article", Article.class).getResultList();
    for (Article article : result) {
      System.out.println(article);
    }
    entityManager.getTransaction().commit();
    entityManager.close();

    if (entityManagerFactory != null) {
      entityManagerFactory.close();
    }
  }
}
