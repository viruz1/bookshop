package zw.co.zss.bookshop.util;

import zw.co.zss.bookshop.dto.BookDTO;
import zw.co.zss.bookshop.dto.CategoryDTO;
import zw.co.zss.bookshop.model.BookInfo;
import zw.co.zss.bookshop.model.CategoryInfo;

public class MapperUtil {

    public  static BookDTO mapBookInfoToBookDTO(BookInfo bookInfo){

        BookDTO bookDTO = new BookDTO();

        bookDTO.setTitle(bookInfo.getTitle());
        bookDTO.setCategory(bookInfo.getCategory());
        bookDTO.setDescription(bookInfo.getDescription());
        bookDTO.setPrice(bookInfo.getPrice());
        bookDTO.setBookId(bookInfo.getId());

        return bookDTO;

    }


    public  static CategoryDTO mapCategoryInfoInfoToCategoryDTO(CategoryInfo categoryInfo){

        CategoryDTO categoryDTO = new CategoryDTO();

        categoryDTO.setTitle(categoryInfo.getTitle());

        return categoryDTO;

    }
}
