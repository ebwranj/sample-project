package com.assignment.dao;

import com.assignment.domain.Book;
import com.assignment.domain.Subject;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Repository
public class DataManagementDao {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/education?allowPublicKeyRetrieval=true&useSSL=FALSE";
    Connection conn = null;
    Statement stmt = null;
    static final String USER = "root";
    static final String PASS = "Root1234";
    @PersistenceContext
    private EntityManager em;



    public DataManagementDao(){

/*
        System.out.println("Connecting to database...");
        Configuration config=new  Configuration().configure();
        config.addAnnotatedClass(Subject.class);
        config.addAnnotatedClass(Book.class);
        factory = config.buildSessionFactory();
*/


    }

    @Transactional
    public void addSubject(Subject subject) throws SQLException,ClassNotFoundException {

        em.persist(subject);



    }

    @Transactional
    public void updateSubject(Subject subject) {

        em.merge(subject);



    }


    @Transactional
    public void addBook(Book book) throws SQLException,ClassNotFoundException{


        em.persist(book);

    }

    @Transactional
    public void updateBook(Book book) throws SQLException,ClassNotFoundException{


        em.merge(book);



    }


    @Transactional
    public void deleteBook(Book book) throws SQLException{

        em.remove(book);

    }

    @Transactional
    public void deleteSubject(Subject subject) throws SQLException{

        em.remove(subject);
    }

    @Transactional
    public Book searchBook(String title) throws SQLException {

        List<Book> queryResult=null;

        Query query= em.createQuery("from Book where title = :title");
        query.setParameter("title",title);
        queryResult =query.getResultList();
        if (queryResult.size() >0){
            return queryResult.get(0);
        }
        return null;
    }

    @Transactional
    public Book searchBook(String bookName, String subjectName) throws SQLException {

        Subject subject = getSubject(subjectName);
        Book book=null;

        if (subject.getBooks().size() > 0){
            book= subject.getBooks().stream().filter(bk->bk.getTitle().equals(bookName)).findFirst().get();
        }

        return book;
    }

    @Transactional
    public Subject searchSubject(String subjectName) throws SQLException {
        //       System.out.println(subjectName);

        Subject subject=getSubject(subjectName);

        return subject;
    }

    @Transactional
    public Subject searchSubject(int durationInHours) throws SQLException {
        //       System.out.println(subjectName);
        List<Subject> queryResult=null;

        Query query= em.createQuery("from Subject where durationInHours = :durationInHours");
        query.setParameter("durationInHours",durationInHours);
        queryResult =query.getResultList();
        if (queryResult.size() >0){
            return queryResult.get(0);
        }
        return null;

    }

    @Transactional
    public Subject getSubject(String subjectName) throws SQLException {


        List<Subject> queryResult=null;
        Query query= em.createQuery("from Subject where subtitle = :subTitle");
        query.setParameter("subTitle",subjectName);
        queryResult =query.getResultList();
        if (queryResult.size() >0){
            return queryResult.get(0);
        }
        return null;

    }
    @Transactional
    public List<Subject> getSubjectList() throws SQLException {

        List<Subject> queryResult=null;
        Query query= em.createQuery("from Subject");

        queryResult =query.getResultList();
        return queryResult;


    }
    @Transactional
    public List<Book> listBook(String subName) throws SQLException {


        Subject subject = getSubject(subName);
        return subject.getBooks();

    }

    public void setEm(EntityManager em) {
        this.em = em;
    }
}

