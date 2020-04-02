/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.javamovies;

import com.mycompany.javamovies.exceptions.NonexistentEntityException;
import com.mycompany.javamovies.exceptions.PreexistingEntityException;
import java.io.Serializable;
import java.util.List;
import javax.jws.WebMethod;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author dishi
 */
public class MoviestvshowJpaController implements Serializable {

    public MoviestvshowJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Moviestvshow moviestvshow) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(moviestvshow);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findMoviestvshow(moviestvshow.getContentId()) != null) {
                throw new PreexistingEntityException("Moviestvshow " + moviestvshow + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Moviestvshow moviestvshow) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            moviestvshow = em.merge(moviestvshow);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = moviestvshow.getContentId();
                if (findMoviestvshow(id) == null) {
                    throw new NonexistentEntityException("The moviestvshow with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Moviestvshow moviestvshow;
            try {
                moviestvshow = em.getReference(Moviestvshow.class, id);
                moviestvshow.getContentId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The moviestvshow with id " + id + " no longer exists.", enfe);
            }
            em.remove(moviestvshow);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Moviestvshow> findMoviestvshowEntities() {
        return findMoviestvshowEntities(true, -1, -1);
    }

    public List<Moviestvshow> findMoviestvshowEntities(int maxResults, int firstResult) {
        return findMoviestvshowEntities(false, maxResults, firstResult);
    }

    private List<Moviestvshow> findMoviestvshowEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Moviestvshow.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Moviestvshow findMoviestvshow(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Moviestvshow.class, id);
        } finally {
            em.close();
        }
    }
    
    public int getMoviestvshowCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Moviestvshow> rt = cq.from(Moviestvshow.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
