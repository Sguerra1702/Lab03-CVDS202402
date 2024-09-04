package edu.eci.cvds.tdd.library;
import edu.eci.cvds.tdd.library.book.Book;
import edu.eci.cvds.tdd.library.loan.Loan;
import edu.eci.cvds.tdd.library.user.User;
import edu.eci.cvds.tdd.library.Library;

import  org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class LibraryTest {
    private static Library libreria;

    @BeforeAll
    public static void setUp() {
        libreria = new Library();
    }

    @Test
    public void shouldAddBook()
    {
        assertTrue( libreria.addBook(new Book("Clean Code: A Handbook of Agile Software Craftsmanship", "Robert C. Martin ", "9780132350884"), 13) );
    }

    @Test
    public void shouldNotAddBook()
    {
        assertFalse( libreria.addBook(new Book("Clean Code: A Handbook of Agile Software Craftsmanship", "Robert C. Martin ", null), 13) );
    }
}
