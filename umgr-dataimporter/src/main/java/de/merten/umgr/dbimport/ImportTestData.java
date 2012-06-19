package de.merten.umgr.dbimport;

import java.io.IOException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import de.merten.umgr.backend.dao.UserDao;
import de.merten.umgr.backend.service.UserService;

/**
 * This class is for importing TestData into Database. 
 * See applicationContext.xml for database properties
 * @author merten
 *
 */
public class ImportTestData {

    /**
     * @param args
     * @throws IOException 
     * @throws InterruptedException 
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        
        
        UserService service = (UserService)context.getBean("userService");
        service.findAll();
       
       UserFactory accountImporter = UserFactory.getInstance();
      // dao.persistAll(accountImporter.getUserList());
       service.saveAll(accountImporter.getUserList());
      // dao.flush();
    }

}
