  import java.io.IOException;
import java.util.List;
import java.util.UUID;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CloneBookServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        List<Book> attribute = (List<Book>) getServletContext().getAttribute("books");
		List<Book> list = attribute;
		List<Book> books = list;
        
        if (books != null) {
            for (Book book : books) {
                if (book.getId().equals(id)) {
                    try {
                        Book clonedBook = (Book) book.clone();
                        clonedBook.setId(UUID.randomUUID().toString()); // Assign a new unique ID to the clone
                        books.add(clonedBook);
                        break;
                    } catch (CloneNotSupportedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        getServletContext().setAttribute("books", books);
        response.sendRedirect("listBooks.jsp");
    }
}
