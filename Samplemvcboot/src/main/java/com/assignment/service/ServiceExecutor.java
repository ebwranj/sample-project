package com.assignment.service;

import com.assignment.service.DataManagementService;
import com.assignment.domain.Book;
import com.assignment.domain.Subject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class ServiceExecutor {

    public static void main1(String[] args) {
        Boolean status=false;
        do {
            ApplicationContext context = new ClassPathXmlApplicationContext(
                    "applicationContext.xml");

            DataManagementService service = (DataManagementService) context
                    .getBean("dataManagementService");

            Scanner sc = new Scanner(System.in);
            {

                System.out.println("a. Add a Subject");
                System.out.println("b. Add a Book");
                System.out.println("c. Delete a Subject");
                System.out.println("d. Delete a book");
                System.out.println("e. Search for a book");
                System.out.println("f. Search for a subject");
                char choice = sc.next().charAt(0);

                switch (choice) {
                    case 'a':
                        System.out.println("Enter title: ");
                        String subTitle = sc.next();
                        System.out.println("Enter duration (hours): ");
                        int duration = sc.nextInt();
                        Subject subject = new Subject(subTitle, duration);

                        try {
                            service.addSubject(subject);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;

                    case 'b':
                        System.out.println("Enter title: ");
                        String title = sc.next();
                        System.out.println("Enter cost (hours): ");
                        double price = sc.nextDouble();
                        System.out.println("Enter volume): ");
                        int volume = sc.nextInt();
                        System.out.println("Enter publish date: ");
                        String dateString = sc.next().trim();
//                        System.out.println(dateString);
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        LocalDate publishDate = LocalDate.from(LocalDate.parse(dateString, formatter));
                        System.out.println("Enter subject title: ");
                        String subjectName = sc.next();


                        Book book = new Book(title, price, volume, publishDate);

                        try {
                            service.addBook(book, subjectName);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;

                    case 'c':
                        System.out.println("Enter Subject to be deleted: ");
                        String subName = sc.next();
                        try {
                            service.deleteSubject(subName);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case 'd':
                        System.out.println("Enter book name to be deleted: ");
                        String bookName = sc.next();
                        System.out.println("Enter Subject of book to be deleted: ");
                        String subBook = sc.next();
                        try {
                            service.deleteBook(bookName, subBook);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        break;
                    case 'e':
                        System.out.println("Enter Book to be found: ");
                        String bookSName = sc.next();
                        System.out.println("Enter Subject of book to be searched: ");
                        String subSBook = sc.next();
                        Book book1 = null;
                        try {
                            book1 = service.searchBook(bookSName, subSBook);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        System.out.println("Book found:" + book1.getTitle() +" cost:" +book1.getPrice()+" volume:" +book1.getVolume() +" published date:" +book1.getPublishDate());
                        break;
                    case 'f':
                        System.out.println("Enter Subject to be found: ");
                        String subSName = sc.next();
                        Subject subject1 = null;
                        try {
                            subject1 = service.searchSubject(subSName);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        System.out.println("Subject found:" + subject1.getSubtitle() +" Duration:"+ subject1.getDurationInHours());
                        break;
                    case 'g':
                        status=true;
                }
            }

        }
        while (status==false);
    }

}
