package zw.co.zss.bookshop.service;

import org.springframework.stereotype.Service;
import zw.co.zss.bookshop.dto.BookDTO;
import zw.co.zss.bookshop.dto.BookResponseDTO;
import zw.co.zss.bookshop.dto.CategoryDTO;
import zw.co.zss.bookshop.dto.CategoryResponseDTO;

import java.util.List;


public interface CategoryInfoService {

    CategoryDTO findCategoryByTitle(String title);

    CategoryResponseDTO saveCategory(CategoryDTO categoryDTO);

    List<CategoryDTO> findAllCategories();

}
