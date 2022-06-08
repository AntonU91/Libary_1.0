package first_project.service;

import first_project.models.Book;
import first_project.models.Person;

import java.util.List;
import java.util.Optional;

public interface BookService {
    public Book getBookById (int id);
    public List<Book> getAllBooks ();
    public void addBook (Book book);
    public void updateBook (int id, Book updatedBook);
    public void deleteBook (int id);
    //public Optional<Book> getPersonByFullName (String fullName);
    public void setPerson ( int bookId, Person person);
}
