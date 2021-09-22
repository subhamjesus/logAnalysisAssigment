package com.creditsuisse.assignment;

import com.creditsuisse.assignment.service.FileReaderService;
import org.hsqldb.util.DatabaseManagerSwing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class AssignmentApplication implements CommandLineRunner   {

    final static Logger logger = LoggerFactory.getLogger(AssignmentApplication.class);
    @Autowired
    private FileReaderService fileReaderService;
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(AssignmentApplication.class);
        app.run(args);
    }


    @Override
    public void run(String... args) throws Exception {
       // fileReaderService.readFile(args[0]);
        fileReaderService.readFile("logfile.txt");
       // getDbManager();
    }

   /* public void getDbManager(){
        DatabaseManagerSwing.main(
                new String[] { "--url", "jdbc:hsqldb:mem:testdb", "--user", "sa", "--password", ""});
    }*/

}
