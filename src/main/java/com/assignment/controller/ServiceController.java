package com.assignment.controller;

import com.assignment.domain.Book;
import com.assignment.domain.Subject;
import com.assignment.service.DataManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.*;
import java.sql.SQLException;
import java.util.List;

@Controller
public class ServiceController {

    @Autowired
    private DataManagementService dataManagementService;

    @RequestMapping(value = "/captureSubject", method = RequestMethod.GET)
    public String captureSubject(Model model) {
          model.addAttribute("subject",new Subject());
 //       ModelAndView mav = new ModelAndView("addSubject");
 //       mav.addObject("user", new User());
        return "captureSubject";
    }

    @RequestMapping(value = "/searchSubject", method = RequestMethod.GET)
    public String searchSubject(Model model) {
        model.addAttribute("subject",new Subject());
        //       ModelAndView mav = new ModelAndView("addSubject");
        //       mav.addObject("user", new User());
        return "searchSubject";
    }


    @RequestMapping(value = "/captureBook/{subtitle}", method = RequestMethod.GET)
    public String captureBook(Model model, @PathVariable String subtitle) {
        model.addAttribute("book",new Book());
        model.addAttribute("subtitle",subtitle);
        //       ModelAndView mav = new ModelAndView("addSubject");
        //       mav.addObject("user", new User());
        return "captureBook";
    }

    @RequestMapping(value = "/searchBook", method = RequestMethod.GET)
    public String searchBook(Model model) {
        model.addAttribute("subject",new Book());
        //       ModelAndView mav = new ModelAndView("addSubject");
        //       mav.addObject("user", new User());
        return "searchBook";
    }

    @RequestMapping(value = "/viewSubject", method = RequestMethod.POST)
    public String viewSubject(Model model,@ModelAttribute("subject")Subject subject) {

        try {
            if (subject.getSubtitle() != null && !subject.getSubtitle().isEmpty()){
                Subject subject1=dataManagementService.searchSubject(subject.getSubtitle());

                if (subject1!=null){
                    model.addAttribute("subject",subject1);
                    return "viewSubject";
                }

            }
            if (subject.getDurationInHours() >0){

                Subject subject1=dataManagementService.searchSubject(subject.getDurationInHours());

                if (subject1!=null){
                    model.addAttribute("subject",subject1);
                    return "viewSubject";
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        //   ModelAndView mav = new ModelAndView("addSubject");
        //       mav.addObject("user", new User());
        return "searchSubject";
    }


    @RequestMapping(value = "/viewBook", method = RequestMethod.POST)
    public String viewBook(Model model,@ModelAttribute("book")Book book) throws Exception{

        try {
            if (book.getTitle() != null && !book.getTitle().isEmpty()){
                Book book1=dataManagementService.searchBook(book.getTitle());

                if (book1!=null){
                    model.addAttribute("book",book1);
                    return "viewBook";
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        //   ModelAndView mav = new ModelAndView("addSubject");
        //       mav.addObject("user", new User());
        return "searchBook";
    }




    @RequestMapping(value = "/addSubject", method = RequestMethod.POST)
    public String addSubject(@ModelAttribute("subject")Subject subject) {

        try {
            dataManagementService.addSubject(subject);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //   ModelAndView mav = new ModelAndView("addSubject");
        //       mav.addObject("user", new User());
        return "redirect:/listSubject";
    }

    @RequestMapping(value = "/addBook/{subtitle}", method = RequestMethod.POST)
    public String addSubject(@ModelAttribute("book")Book book, @PathVariable String subtitle) {

        try {
            dataManagementService.addBook(book,subtitle);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //   ModelAndView mav = new ModelAndView("addSubject");
        //       mav.addObject("user", new User());
        return "redirect:/listBook/"+subtitle;
    }

    @RequestMapping(value = "/listSubject", method = RequestMethod.GET)
    public String listSubject(Model model) {
        try {

            List<Subject> subjectList=dataManagementService.getSubjectList();
            System.out.println("size" + subjectList.size());
            model.addAttribute("subjectList",subjectList);
            System.out.println(model.containsAttribute("subjectList"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //   ModelAndView mav = new ModelAndView("addSubject");
        //       mav.addObject("user", new User());
        return "listSubject";
    }

    @RequestMapping(value = "/listBook/{subtitle}", method = RequestMethod.GET)
    public String listBook(Model model, @PathVariable String subtitle)  throws Exception{
        try {

            List<Book> bookList=dataManagementService.listBook(subtitle);
            System.out.println("size" + bookList.size());
            model.addAttribute("bookList",bookList);
            model.addAttribute("subtitle",subtitle);
            System.out.println(model.containsAttribute("bookList"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //   ModelAndView mav = new ModelAndView("addSubject");
        //       mav.addObject("user", new User());
        return "listBook";
    }


    @RequestMapping(value = "/deleteSubject/{subTitle}", method = RequestMethod.GET)
    public String deleteSubject(@PathVariable String subTitle) {

        try {
            dataManagementService.deleteSubject(subTitle);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //   ModelAndView mav = new ModelAndView("addSubject");
        //       mav.addObject("user", new User());
        return "redirect:/listSubject";
    }

    @RequestMapping(value = "/deleteBook/{bookName}/{subTitle}", method = RequestMethod.GET)
    public String deleteBook(@PathVariable String bookName,@PathVariable String subTitle)  throws Exception{

        try {
            dataManagementService.deleteBook(bookName,subTitle);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //   ModelAndView mav = new ModelAndView("addSubject");
        //       mav.addObject("user", new User());
        return "redirect:/listBook/"+subTitle;
    }

    @RequestMapping(value = "/addBook", method = RequestMethod.GET)
    public ModelAndView addBook(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("addBook");
        //       mav.addObject("user", new User());
        return mav;
    }

    @RequestMapping(value = "/deleteBook", method = RequestMethod.GET)
    public ModelAndView deleteBook(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("deleteBook");
        //       mav.addObject("user", new User());
        return mav;
    }

    @RequestMapping(value = "/deleteSubject", method = RequestMethod.GET)
    public ModelAndView deleteSubject(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("deleteSubject");
        //       mav.addObject("user", new User());
        return mav;
    }

    @RequestMapping(value = "/viewBook", method = RequestMethod.GET)
    public ModelAndView viewBook(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("viewBook");
        //       mav.addObject("user", new User());
        return mav;
    }

    @RequestMapping(value = "/viewSubject", method = RequestMethod.GET)
    public ModelAndView viewSubject(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("viewSubject");
        //       mav.addObject("user", new User());
        return mav;
    }
}
