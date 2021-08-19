package zw.co.zss.bookshop.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import zw.co.zss.bookshop.dto.*;
import zw.co.zss.bookshop.service.BookInfoService;
import zw.co.zss.bookshop.service.CategoryInfoService;
import zw.co.zss.bookshop.service.PurchaseService;

import java.util.List;

@RestController
@RequestMapping("bookShop-api")
public class BookShopRestController {

    @Autowired
    private BookInfoService bookInfoService;

    @Autowired
    private CategoryInfoService categoryInfoService;

    @Autowired
    private PurchaseService purchaseService;

    @GetMapping(value = "health")
    public String health() {
        return "bookShop-api Online";
    }

    @PostMapping(value = "/saveBook")
    public BookResponseDTO saveBook(@RequestBody BookDTO bookDTO){

        return bookInfoService.saveBook(bookDTO);

    }

    @PostMapping(value = "/purchaseBook")
    public PurchaseBookResponseDTO purchaseBook(@RequestBody PurchaseBookRequestDTO purchaseBookRequestDTO){

        return purchaseService.purchaseBook(purchaseBookRequestDTO);

    }

    @GetMapping(value = "/getAllBooks")
    public List<BookDTO> getAllBooks(){

        return bookInfoService.findAllBooks();

    }

    @GetMapping(value = "/getBooksByCategory/{category}")
    public List<BookDTO> getBooksByCategory(@PathVariable("category")String category){

        return bookInfoService.findBookByCategory(category);

    }

    @PostMapping(value = "/saveCategory")
    public CategoryResponseDTO saveCategory(@RequestBody CategoryDTO categoryDTO){

        return categoryInfoService.saveCategory(categoryDTO);

    }
}
