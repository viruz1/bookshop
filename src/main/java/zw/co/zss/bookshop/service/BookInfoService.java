package zw.co.zss.bookshop.service;

import org.springframework.stereotype.Service;
import zw.co.zss.bookshop.dto.BookDTO;
import zw.co.zss.bookshop.dto.BookResponseDTO;
import java.util.List;


public interface BookInfoService {

    BookDTO findBookByTitle(String title);

    BookDTO findBookById(String id);

    List<BookDTO> findBookByCategory(String category);

    BookResponseDTO saveBook(BookDTO bookDTO);

    List<BookDTO> findAllBooks();

}
