import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookServiceTest {
    @Mock
    private BookRepository mockBookRepository;
    private BookService mockBookService;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        mockBookService = new BookService(mockBookRepository);
    }

    @Test
    void findBookById() {
        Book book = new Book("1", "New book", "New author");
        when(mockBookRepository.findById("1")).thenReturn(book);
        Book result = mockBookService.findBookById("1");
        assertEquals(book, result, "Method findBookById works incorrect");
        verify(mockBookRepository, times(1)).findById("1");
    }

    @Test
    void findAllBooks() {
        List<Book> expected = new ArrayList<>(Arrays.asList(
                new Book("1", "New book", "New author"),
                new Book("2", "New book", "New author"),
                new Book("3", "New book", "New author")
        ));
        when(mockBookRepository.findAll())
                .thenReturn(expected);
        List<Book> result = mockBookService.findAllBooks();

        verify(mockBookRepository, times(1)).findAll();
        assertEquals(expected, result,
                "Method findAllBooks works incorrect, provides not correct list of books");
    }
}