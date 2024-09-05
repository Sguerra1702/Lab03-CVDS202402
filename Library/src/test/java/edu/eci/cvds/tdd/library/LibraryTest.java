package edu.eci.cvds.tdd.library;
import edu.eci.cvds.tdd.library.book.Book;
import edu.eci.cvds.tdd.library.loan.Loan;
import edu.eci.cvds.tdd.library.user.User;
import edu.eci.cvds.tdd.library.loan.LoanStatus;
import edu.eci.cvds.tdd.library.Library;

import org.junit.jupiter.api.Order;
import  org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;

public class LibraryTest {
    private static Library libreria;
    private User user1, user2;
    private Book book1, book2;

    @BeforeAll
    public static void setUp() {
        libreria = new Library();
    }

    @Test
    @Order(1)
    public void shouldAddBook() {
        book1 = new Book("Clean Code: A Handbook of Agile Software Craftsmanship", "Robert C. Martin ", "9780132350884");
        assertTrue( libreria.addBook(book1, 13) );
    }

    @Test
    @Order(2)
    public void shouldNotAddBook()
    {
        book2 = new Book("Quantum Computing for Computer Scientists", "Noson S. Yanofsky , Mirco A. Mannucci ", null);
        assertFalse( libreria.addBook(book2, 15) );
    }
   
    @Test
    @Order(3)
    public void shouldIncreaseAmountOfBooks(){
        book1 = new Book("Clean Code: A Handbook of Agile Software Craftsmanship", "Robert C. Martin ", "9780132350884");
        libreria.addBook(book1, 13);
        Integer amountBefore = (Integer) libreria.getBooks().get(book1);
        assertTrue( libreria.addBook(book1, 1) );
        Integer amountAfter = (Integer) libreria.getBooks().get(book1);
        assertTrue(amountAfter > amountBefore);
    }

    @Test
    @Order(4)
    public void shouldCreateLoan(){
        Library libreria = new Library();
        user1 = new User("Juan", "123");
        libreria.addUser(user1);
        book1 = new Book("Clean Code: A Handbook of Agile Software Craftsmanship", "Robert C. Martin ", "9780132350884");
        libreria.addBook(book1, 13);
        Loan loan = libreria.loanABook("123", "9780132350884");
        assertNotNull( loan );
    }

    @Test
    @Order(5)
    public void shouldNotCreateLoan(){
        user2 = new User("Pedro","456");
        libreria.addUser(user2);
        assertNull( libreria.loanABook("456", null) );
    }

    @Test
    @Order(6)
    public void shouldNotLoanWhenUserHasLoan(){
        Library libreria = new Library();
        user1 = new User("Juan", "123");
        libreria.addUser(user1);
        book1 = new Book("Clean Code: A Handbook of Agile Software Craftsmanship", "Robert C. Martin ", "9780132350884");
        libreria.addBook(book1, 13);
        libreria.loanABook("123", "9780132350884");
        assertNull( libreria.loanABook("123", "9780132350884") );
    }

    @Test
    @Order(7)
    public void shouldReturnLoan(){
        Library libreria = new Library();
        user1 = new User("Juan", "123");
        libreria.addUser(user1);
        book1 = new Book("Clean Code: A Handbook of Agile Software Craftsmanship", "Robert C. Martin ", "9780132350884");
        libreria.addBook(book1, 13);
        Loan loan = libreria.loanABook("123", "9780132350884");
        libreria.returnLoan(loan);
        assertSame(loan.getStatus(), LoanStatus.RETURNED);
    }

    @Test
    @Order(8)
    public void shouldNotReturnLoan(){
        user1 = new User("Juan", "123");
        libreria.addUser(user1);
        book1 = new Book("Clean Code: A Handbook of Agile Software Craftsmanship", "Robert C. Martin ", "9780132350884");
        libreria.addBook(book1, 13);
        Loan loan = libreria.loanABook("123", "9780132350884");
        Loan updatedLoan = libreria.returnLoan(loan);
        Loan failLoan = libreria.returnLoan(updatedLoan);
        assertNull(failLoan);
    }

}
