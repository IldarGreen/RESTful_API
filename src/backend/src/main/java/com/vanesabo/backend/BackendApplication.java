package com.vanesabo.backend;

import com.vanesabo.backend.model.Book;
import com.vanesabo.backend.model.AddressEntity;
import com.vanesabo.backend.model.ClientEntity;
import com.vanesabo.backend.repository.AddressRepository;
import com.vanesabo.backend.repository.BookRepository;
import com.vanesabo.backend.repository.ClientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class BackendApplication {

    private static final Logger log = LoggerFactory.getLogger(BackendApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    ClientRepository clientRepository;

    // Run this if app.db.init.enabled = true
    @Bean
    @ConditionalOnProperty(prefix = "app", name = "db.init.enabled", havingValue = "true")
    public CommandLineRunner demoCommandLineRunner() {
        return args -> {

            System.out.println("Running.....");

            Book b1 = new Book("Book A",
                    BigDecimal.valueOf(9.99),
                    LocalDate.of(2023, 8, 31));
            Book b2 = new Book("Book B",
                    BigDecimal.valueOf(19.99),
                    LocalDate.of(2023, 7, 31));

            bookRepository.saveAll(List.of(b1, b2));


			AddressEntity a1 = new AddressEntity("Франция", "Леон", "Вьен");
            AddressEntity a2 = new AddressEntity("Россия", "Сакт-Петербург", "Лунная");

            addressRepository.saveAll(List.of(a1, a2));

            ClientEntity cl1 = new ClientEntity("Кэлен", "Амнелл", "2000-04-05",
                                                    "F", "2014-05-06", a1);
            ClientEntity cl2 = new ClientEntity("Йон", "Тихий", "1940-01-02",
                                                    "M", "2024-05-07", a2);
            ClientEntity cl3 = new ClientEntity("Йон2", "Тихий", "1940-01-02",
                    "M", "2024-05-07", a2);
            ClientEntity cl4 = new ClientEntity("Йо3", "Тихий", "1940-01-02",
                    "M", "2024-05-07", a2);
            ClientEntity cl5 = new ClientEntity("Йон4", "Тихий", "1940-01-02",
                    "M", "2024-05-07", a2);
            ClientEntity cl6 = new ClientEntity("Йон5", "Тихий", "1940-01-02",
                    "M", "2024-05-07", a2);

            clientRepository.saveAll(List.of(cl1, cl2, cl3, cl4, cl5, cl6));

        };
    }

}