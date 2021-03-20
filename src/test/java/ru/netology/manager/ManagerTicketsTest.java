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
    private Ticket first = new Ticket(1, 2000, "DME", "LED", 60);
    private Ticket second = new Ticket(2, 4000, "DME", "KUF", 110);
    private Ticket third = new Ticket(3, 1500, "DME", "GOJ", 80);
    private Ticket fourth = new Ticket(4, 5000, "KUF", "DME", 110);

    @BeforeEach
    public void setUp() {
        repository.save(first);
        repository.save(second);
        repository.save(third);
        repository.save(fourth);
    }

    @Test
    void shouldRemoveById() {
        int id = 1;
        repository.removeById(id);
        Ticket[] actual = repository.findAll();
        Ticket[] expected = new Ticket[]{third, second, fourth};

        Arrays.sort(actual);

        assertArrayEquals(actual, expected);
    }

    @Test
    void shouldSearchTicketsByFromAndTo() {
        Ticket[] actual = manager.searchBy("DME", "GOJ");
        Ticket[] expected = new Ticket[]{third};

        assertArrayEquals(actual, expected);
    }
}