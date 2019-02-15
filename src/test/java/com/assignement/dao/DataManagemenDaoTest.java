
package com.assignement.dao;

import com.assignment.config.AppConfig;
import com.assignment.dao.DataManagementDao;
import com.assignment.domain.Book;
import com.assignment.domain.Subject;
import com.assignment.exception.NotFoundException;
import com.assignment.service.DataManagementService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.Transactional;
import org.springframework.context.annotation.Bean;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@RunWith(SpringRunner.class)
//@AutoConfigureTestDatabase(replace=NONE)
@SpringBootTest
@ContextConfiguration(classes= AppConfig.class,locations={"classpath:/testApplicationContext.xml"})

//@DataJpaTest
public class DataManagemenDaoTest {


    @Autowired
    DataManagementDao dataManagementDao;



    @Test
    @Rollback(false)
    @Transactional
    public void testSubject1() throws Exception {
        Subject subject=new Subject();
        subject.setSubtitle("test2");
        subject.setDurationInHours(10);
        dataManagementDao.addSubject(subject);
        Subject subject0=dataManagementDao.getSubject("test2");
        assertEquals(subject0.getDurationInHours(),10);
    }

    @Test
    @Rollback(false)
    @Transactional
    public void testSubject2() throws Exception {
        Subject subject0=dataManagementDao.getSubject("test2");
        subject0.setDurationInHours(15);
        dataManagementDao.updateSubject(subject0);
        Subject subject1=dataManagementDao.getSubject("test2");
        assertEquals(subject1.getDurationInHours(),15);
    }

    @Test
    @Rollback(false)
    @Transactional
    public void testSubject3() throws Exception {

        Subject subject1=dataManagementDao.getSubject("test2");
        System.out.println(subject1.getId());
        System.out.println(subject1.getDurationInHours());
        dataManagementDao.deleteSubject(subject1);
        assertNull(dataManagementDao.getSubject("test2"));
    }


}
