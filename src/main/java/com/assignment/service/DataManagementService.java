package com.assignment.service;

import com.assignment.dao.DataManagementDao;
import com.assignment.domain.Book;
import com.assignment.domain.Subject;
import com.assignment.exception.NotFoundException;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DataManagementService {

    Connection conn = null;
    Statement stmt = null;
    static final String USER = "root";
    static final String PASS = "Root1234";
    @Autowired
    DataManagementDao dataManagementDao;

    public DataManagementService(){



    }

    @Transactional
    public void addSubject(Subject subject) throws SQLException,ClassNotFoundException {

        dataManagementDao.addSubject(subject);

        System.out.println("Subject "+subject.getSubtitle() + " successfully added");

    }


    @Transactional
    public void addBook(Book book,String subjectName) throws SQLException,ClassNotFoundException{

        Subject subject=getSubject(subjectName);
        subject.getBooks().add(book);
        dataManagementDao.updateSubject(subject);
       // book.setSubject(subject);
       // dataManagementDao.addBook(book);


        System.out.println("Book "+book.getTitle() + " successfully added");


    }

    @Transactional
    public void updateBook(Book book,String subjectName) throws SQLException,  NotFoundException {

        Subject subject=getSubject(subjectName);
        if (subject != null){
            Optional<Book> book2=subject.getBooks().stream().filter(book1 -> book1.getId().equals(book.getId())).findFirst();

            if (book2 !=null &&book2.isPresent()){
                subject.setBooks(subject.getBooks().stream().filter(book1 -> !book.getId().equals(book.getId())).collect(Collectors.toList()));
                subject.getBooks().add(book);
                dataManagementDao.updateSubject(subject);
                System.out.println("Book "+book.getTitle() + " successfully updated");
                return;

            }
        }
        throw new NotFoundException();

      //  book.setSubject(subject);






    }

    @Transactional
    public void deleteBook(String bookName,String subjectName ) throws SQLException,NotFoundException{

        Subject subject = getSubject(subjectName);

        if (subject!= null && subject.getBooks().size() > 0) {

            Transaction tx = null;
            Integer userIdSaved = null;
            Optional<Book> book = subject.getBooks().stream().filter(bk -> bk.getTitle().equals(bookName)).findFirst();
            if (book!= null&& book.isPresent()){
                subject.setBooks(subject.getBooks().stream().filter(bk -> !bk.getTitle().equals(bookName)).collect(Collectors.toList()));
                dataManagementDao.updateSubject(subject);
                System.out.println("Book "+bookName + " successfully deleted");
                return;
            }

        }

        throw new NotFoundException();

    }

    @Transactional
    public void deleteSubject(String subjectName) throws SQLException{
        Subject subject = getSubject(subjectName);
        dataManagementDao.deleteSubject(subject);
        System.out.println("Subject "+subjectName + " successfully deleted");

    }
    @Transactional
    public Book searchBook(String title) throws SQLException,NotFoundException {


        Book book= dataManagementDao.searchBook(title);
        if (book != null){
            return book;
        }


    throw new NotFoundException();

    }

    @Transactional
    public Book searchBook(String bookName, String subjectName) throws SQLException,NotFoundException {

        Subject subject = getSubject(subjectName);
        Book book=null;

        if (subject.getBooks().size() > 0){
            book= subject.getBooks().stream().filter(bk->bk.getTitle().equals(bookName)).findFirst().get();
            if (book != null){
                return book;
            }

        }
        throw new NotFoundException();

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
        return dataManagementDao.searchSubject(durationInHours);

    }

    @Transactional
    private Subject getSubject(String subjectName) throws SQLException {


       return dataManagementDao.getSubject(subjectName);

    }
    @Transactional
    public List<Subject> getSubjectList() throws SQLException {

        return dataManagementDao.getSubjectList();


    }
    @Transactional
    public List<Book> listBook(String subName) throws SQLException,NotFoundException {

        List<Book> books=dataManagementDao.listBook(subName);
        if (books != null)
        {
            return books;
        }
        throw new NotFoundException();
    }
}

