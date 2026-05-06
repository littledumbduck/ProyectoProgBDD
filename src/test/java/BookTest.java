import entities.Book;
import entities.BookDAO;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class BookTest {
    @Test
    public void testBook() throws SQLException {
        Book temp = new Book(1, "30238456B", 13, "1996-11-28", "1996-11-30", 'd');
        BookDAO temp2 = new BookDAO(temp);

        temp2.addBook();
    }
}
