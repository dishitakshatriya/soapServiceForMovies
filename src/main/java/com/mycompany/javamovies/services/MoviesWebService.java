/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.javamovies.services;

import com.mycompany.javamovies.Moviestvshow;
import com.mycompany.javamovies.MoviestvshowJpaController;
import com.mycompany.javamovies.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import static java.util.Comparator.comparing;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.HandlerChain;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.soap.MTOM;

/**
 *
 * @author dishi
 */
@WebService(serviceName = "MoviesWebService")
@MTOM(enabled=true, threshold=100000)
@HandlerChain(file = "/MoviesWebService_handler.xml")
public class MoviesWebService {
    
    @WebMethod(operationName = "addContent")
    public void addContent(Moviestvshow obj) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("db");
        MoviestvshowJpaController repo = new MoviestvshowJpaController(emf);
        try {
            obj.setContentId(getNextId());
            repo.create(obj);  
        } catch (Exception ex) {
            Logger.getLogger(MoviesWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @WebMethod(operationName = "deleteContent")
    public void deleteContent(int contentId) throws NonexistentEntityException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("db");
        MoviestvshowJpaController repo = new MoviestvshowJpaController(emf);
        Moviestvshow dbObject = repo.findMoviestvshow(contentId);
        if (dbObject != null) {
            repo.destroy(contentId);
        }
    }
    @WebMethod(operationName = "findContent")
    public Moviestvshow findContent(int contentId) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("db");
        MoviestvshowJpaController repo = new MoviestvshowJpaController(emf);
        Moviestvshow dbObject = repo.findMoviestvshow(contentId);
        return dbObject;
    }
    @WebMethod(operationName = "updateContent")
    public void updateContent(Moviestvshow guiObject) throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("db");
        MoviestvshowJpaController repo = new MoviestvshowJpaController(emf);
        repo.edit(guiObject);
    }
    @WebMethod(operationName = "findAll")
    public List<Moviestvshow> findAll() throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("db");
        MoviestvshowJpaController repo = new MoviestvshowJpaController(emf);
        List<Moviestvshow> movieList = new ArrayList<>();
        movieList = repo.findMoviestvshowEntities();
        
        return movieList;
    }
    @WebMethod(exclude = true)
    public int getNextId() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("db");
        MoviestvshowJpaController repo = new MoviestvshowJpaController(emf);
        List<Moviestvshow> movieList = repo.findMoviestvshowEntities();
        if (movieList.size()!=0) {
            Moviestvshow movie = movieList.stream().max(comparing(Moviestvshow::getContentId)).get();
            int curr = movie.getContentId();
            return curr+1;
        } else {
            return 1;
        }
        
    }
}
