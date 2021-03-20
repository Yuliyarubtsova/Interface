package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Ticket;
import ru.netology.repository.TicketsRepository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

class ManagerTicketsTest {
    private TicketsRepository repository = new TicketsRepository();
    ManagerTickets manager = new ManagerTickets(repository);
    private Ticket first = new Ticket(1, 2000, "DME", "KRR", 230);
    private Ticket second = new Ticket(2, 4000, "DME", "KUF", 110);
    private Ticket third = new Ticket(3, 1500, "DME", "GOJ", 80);
    private Ticket fourth = new Ticket(4, 5000, "KUF", "DME", 110);
    private Ticket fifth = new Ticket(5, 6500, "DME", "KRR", 220);
    private Ticket sixth = new Ticket(6, 3500, "LED", "DME", 60);
    private Ticket seventh = new Ticket(7, 4000, "KUF", "KRR", 110);
    private Ticket eighth = new Ticket(8, 4500, "KRR", "DME", 220);


    @BeforeEach
    public void setUp() {
        repository.save(first);
        repository.save(second);
        repository.save(third);
        repository.save(fourth);
        repository.save(fifth);
        repository.save(sixth);
        repository.save(seventh);
        repository.save(eighth);
    }

    @Test
    void shouldRemoveById() {
        int id = 1;
        repository.removeById(id);
        Ticket[] actual = repository.findAll();
        Ticket[] expected = new Ticket[]{third, sixth, second, seventh, eighth, fourth, fifth};

        Arrays.sort(actual);

        assertArrayEquals(actual, expected);
    }

    @Test
    void shouldSearchTicketsByFromAndToMoreOne() {
        Ticket[] actual = manager.searchBy("KUF", "KRR");
        Ticket[] expected = new Ticket[]{seventh};

        Arrays.sort(actual);
        assertArrayEquals(actual, expected);
    }

    @Test
    void shouldSearchTicketsByFromAndToOne() {
        Ticket[] actual = manager.searchBy("DME", "KRR");
        Ticket[] expected = new Ticket[]{first, fifth};

        Arrays.sort(actual);
        assertArrayEquals(actual, expected);
    }

    @Test
    void shouldSearchTicketsByFromAndToNo() {
        Ticket[] actual = manager.searchBy("KRR", "GOJ");
        Ticket[] expected = new Ticket[]{};

        Arrays.sort(actual);
        assertArrayEquals(actual, expected);
    }
}