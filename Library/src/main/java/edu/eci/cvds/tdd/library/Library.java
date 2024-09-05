package edu.eci.cvds.tdd.library;

import edu.eci.cvds.tdd.library.book.Book;
import edu.eci.cvds.tdd.library.loan.Loan;
import edu.eci.cvds.tdd.library.loan.LoanStatus;
import edu.eci.cvds.tdd.library.user.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Library responsible for manage the loans and the users.
 */
public class Library {

    private final List<User> users;
    private final Map<Book, Integer> books;
    private final List<Loan> loans;

    public Library() {
        users = new ArrayList<>();
        books = new HashMap<>();
        loans = new ArrayList<>();
    }

    /**
     * Adds a new {@link edu.eci.cvds.tdd.library.book.Book} into the system, the book is store in a Map that contains
     * the {@link edu.eci.cvds.tdd.library.book.Book} and the amount of books available, if the book already exist the
     * amount should increase by 1 and if the book is new the amount should be 1, this method returns true if the
     * operation is successful false otherwise.
     *
     * @param book The book to store in the map.
     *
     * @return true if the book was stored false otherwise.
     */
    public boolean addBook(Book book, int amount) {
        if (books.containsKey(book)) {
            books.put(book, books.get(book) + 1);
            return true;
        }
        else if (book.getIsbn() == null || book.getAuthor() == null || book.gettitle() == null) {
            return false;
        }
        else {
            books.put(book, amount);
            return true;
        }
        //TODO Implement the logic to add a new book into the map.
        
    }

    /**
     * This method creates a new loan with for the User identify by the userId and the book identify by the isbn,
     * the loan should be store in the list of loans, to successfully create a loan is required to validate that the
     * book is available, that the user exist and the same user could not have a loan for the same book
     * {@link edu.eci.cvds.tdd.library.loan.LoanStatus#ACTIVE}, once these requirements are meet the amount of books is
     * decreased and the loan should be created with {@link edu.eci.cvds.tdd.library.loan.LoanStatus#ACTIVE} status and
     * the loan date should be the current date.
     *
     * @param userId id of the user.
     * @param isbn book identification.
     *
     * @return The new created loan.
     */
    public Loan loanABook(String userId, String isbn) {
        User userToAdd = null;
        Book bookToAdd = null;
        // Verificar si ya existe un préstamo para este usuario y libro
        for (Loan loan : loans) {
            if (loan.getBook().getIsbn().equals(isbn) && loan.getUser().getId().equals(userId)) {
                return null;  // El usuario ya tiene un préstamo activo para este libro
            }
        }
    
        // Buscar al usuario
        for (User user : users) {
            if (user.getId().equals(userId)) {
                userToAdd = user;
                break;  // Usuario encontrado, salir del bucle
            }
        }
    
        // Buscar el libro disponible
        for (Book book : books.keySet()) {
            if (book.getIsbn().equals(isbn) && books.get(book) > 0) {
                bookToAdd = book;
                break;  // Libro encontrado, salir del bucle
            }
        }
    
        // Verificar si el usuario o libro no se encontraron
        if (userToAdd == null || bookToAdd == null) {
            return null;
        }
        else {
            Loan newLoan = new Loan(bookToAdd, userToAdd, LocalDateTime.now(), LoanStatus.ACTIVE, null);
            loans.add(newLoan);
            books.put(bookToAdd, books.get(bookToAdd) - 1);  // Restar una copia del libro
            return newLoan;
        }
    }
    

    /**
     * This method return a loan, meaning that the amount of books should be increased by 1, the status of the Loan
     * in the loan list should be {@link edu.eci.cvds.tdd.library.loan.LoanStatus#RETURNED} and the loan return
     * date should be the current date, validate that the loan exist.
     *
     * @param loan loan to return.
     *
     * @return the loan with the RETURNED status.
     */
    public Loan returnLoan(Loan loan) {
        if (loan.getStatus() == LoanStatus.RETURNED) {
            return null;
        }
        else {
            loan.setStatus(LoanStatus.RETURNED);
            Book bookToReturn = loan.getBook();
            books.put(bookToReturn, books.get(bookToReturn) + 1);
            loan.setReturnDate(LocalDateTime.now());
        }
        return loan;
    }

    public boolean addUser(User user) {
        return users.add(user);
    }

    public Map getBooks(){
        return books;
    }

}