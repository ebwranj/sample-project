package com.assignment.service;

import com.assignment.dao.DataManagementDao;
import com.assignment.domain.Book;
import com.assignment.domain.Subject;
import com.assignment.exception.NotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DataManagementService.class},properties = {"locations=classpath:testApplicationContext.xml" })
public class DataManagementServiceTest {

    @MockBean
    DataManagementDao dataManagementDao;

    @Autowired
    DataManagementService dataManagementService;

    @Test
    public void testAddSubject() throws Exception {
        Subject subject=new Subject();
        dataManagementService.addSubject(subject);
        verify(dataManagementDao, times(1)).addSubject(any());
    }

    @Test
    public void testAddBook() throws Exception {
        Subject subject=new Subject();
        subject.setSubtitle("test");
        Book book= new Book();
        book.setTitle("test");
        when(dataManagementDao.getSubject("test")).thenReturn(subject);
        dataManagementService.addBook(book,"test");
        verify(dataManagementDao, times(1)).updateSubject(any());
    }

    @Test
    public void testSearchSubject() throws Exception {
        Subject subject=new Subject();
        subject.setSubtitle("test");
        when(dataManagementDao.getSubject("test")).thenReturn(subject);
        Subject subject1=dataManagementService.searchSubject("test");
        assertEquals(subject1.getSubtitle(),subject.getSubtitle());
    }

    @Test
    public void testSearchbook() throws Exception {
        Book book= new Book();
        book.setTitle("test");
        when(dataManagementDao.searchBook("test")).thenReturn(book);
        Book book1=dataManagementService.searchBook("test");
        assertEquals(book1.getTitle(),book.getTitle());
    }

    @Test(expected =NotFoundException.class)
    public void testSearchBookNotFound() throws Exception {
        Book book= new Book();
        book.setTitle("test");
        when(dataManagementDao.searchBook("test")).thenReturn(null);
        Book book1=dataManagementService.searchBook("test");
    }


    @Test
    public void testUpdatebook() throws Exception {
        Subject subject=new Subject();
        subject.setSubtitle("test");
        Book book= new Book();
        book.setTitle("test");
        book.setId(1);
        subject.getBooks().add(book);
        when(dataManagementDao.getSubject("test")).thenReturn(subject);
        when(dataManagementDao.searchBook("test")).thenReturn(book);
        dataManagementService.updateBook(book,"test");
        verify(dataManagementDao, times(1)).updateSubject(any());
    }

    @Test(expected =NotFoundException.class)
    public void testUpdatebookNotFound() throws Exception {
        Subject subject=new Subject();
        subject.setSubtitle("test");
        when(dataManagementDao.getSubject("test")).thenReturn(null);
        Book book= new Book();
        book.setTitle("test");
        when(dataManagementDao.searchBook("test")).thenReturn(book);
        dataManagementService.updateBook(book,"test");
        verify(dataManagementDao, times(1)).updateBook(any());
    }

    @Test
    public void testDeleteSubject() throws Exception {
        Subject subject=new Subject();
        subject.setSubtitle("test");
        when(dataManagementDao.getSubject("test")).thenReturn(subject);

        dataManagementService.deleteSubject("test");
        verify(dataManagementDao, times(1)).deleteSubject(any());
    }

    @Test
    public void testdeletebook() throws Exception {
        Subject subject=new Subject();
        subject.setSubtitle("test");
        Book book= new Book();
        book.setTitle("test");
        subject.getBooks().add(book);
        when(dataManagementDao.getSubject("test")).thenReturn(subject);
        dataManagementService.deleteBook("test","test");
        verify(dataManagementDao, times(1)).updateSubject(any());
    }

}
