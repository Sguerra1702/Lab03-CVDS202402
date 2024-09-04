package edu.eci.cvds.tdd.library;
import edu.eci.cvds.tdd.library.book.Book;
import edu.eci.cvds.tdd.library.loan.Loan;
import edu.eci.cvds.tdd.library.user.User;
import edu.eci.cvds.tdd.library.Library;

import  org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;

public class LibraryTest {
    private static Library libreria;
    private static User user1, user2;
    private static Book book1, book2;

    @BeforeAll
    public static void setUp() {
        libreria = new Library();
        user1 = new User("Juan", "123");
        user2 = new User("Pedro","456");
        book1 = new Book("Clean Code: A Handbook of Agile Software Craftsmanship", "Robert C. Martin ", "9780132350884");
        book2 = new Book("Quantum Computing for Computer Scientists", "Noson S. Yanofsky , Mirco A. Mannucci ", null);
        libreria.addUser(user1);
        libreria.addUser(user2);

    }

    @Test
    public void shouldAddBook() {
        
        assertTrue( libreria.addBook(book1, 13) );
    }

    @Test
    public void shouldNotAddBook()
    {
        assertFalse( libreria.addBook(book2, 15) );
    }
   
    @Test
    public void shouldIncreaseAmountOfBooks(){

        libreria.addBook(book1, 13);
        Integer amountBefore = (Integer) libreria.getBooks().get(book1);
        assertTrue( libreria.addBook(book1, 1) );
        Integer amountAfter = (Integer) libreria.getBooks().get(book1);
        assertTrue(amountAfter > amountBefore);
    }

    @Test
    public void shouldCreateLoan(){

        
        assertNotNull( libreria.loanABook("123", "9780132350884") );
    }

    @Test
    public void shouldNotCreateLoan(){

        assertNull( libreria.loanABook("456", null) );
    }
}
