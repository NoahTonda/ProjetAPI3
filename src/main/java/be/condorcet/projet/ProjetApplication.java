package be.condorcet.projet;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.*;

@SpringBootApplication
public class ProjetApplication  {

    public static void main(String[] args) {
        SpringApplication.run(ProjetApplication.class, args);
    }

}
