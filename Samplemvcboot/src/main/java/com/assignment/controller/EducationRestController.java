package com.assignment.controller;

import com.assignment.domain.Book;
import com.assignment.exception.NotFoundException;
import com.assignment.service.DataManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/bookController")
public class EducationRestController {


    @Autowired
    private DataManagementService dataManagementService;


    @RequestMapping(value = "/addBook/{subtitle}", method = RequestMethod.POST,headers = "Accept=application/json",produces= MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Boolean> addBook(@RequestBody Book book, @PathVariable String subtitle) {

        if (book == null || book.getTitle() == null){
            return  new ResponseEntity<Boolean>(true,HttpStatus.NOT_FOUND);
        }
        try {
            dataManagementService.addBook(book,subtitle);
        } catch (SQLException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (ClassNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Boolean>(true,HttpStatus.OK);

    }

    @RequestMapping(value = "/updateBook/{subtitle}", method = RequestMethod.PUT,produces= MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Boolean> updateBook(@RequestBody Book book, @PathVariable String subtitle) {
        System.out.println("update book");
        try {
            dataManagementService.updateBook(book,subtitle);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (SQLException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }


    @RequestMapping(value = "/viewBook/{bookTitle}", method = RequestMethod.GET,produces= MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Book> viewBook(@PathVariable String bookTitle){

        Book book1= null;
        try {
            book1 = dataManagementService.searchBook(bookTitle);
        } catch (SQLException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(book1,HttpStatus.OK);


    }


    @RequestMapping(value = "/listBooks/{subtitle}", method = RequestMethod.GET,produces= MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<Book>> listBooks(@PathVariable String subtitle) {
        List<Book> bookList=null;
        try {

            bookList=dataManagementService.listBook(subtitle);

        } catch (SQLException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(bookList,HttpStatus.OK);
    }

    @RequestMapping(value = "/deleteBook/{bookName}/{subTitle}", method = RequestMethod.DELETE,produces= MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Boolean> deleteBook(@PathVariable String bookName,@PathVariable String subTitle) {

        try {
            dataManagementService.deleteBook(bookName,subTitle);
            } catch (SQLException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);

    }

}
