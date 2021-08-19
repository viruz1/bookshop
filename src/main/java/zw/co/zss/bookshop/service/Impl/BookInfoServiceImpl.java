package zw.co.zss.bookshop.service.Impl;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import zw.co.zss.bookshop.dto.BookDTO;
import zw.co.zss.bookshop.dto.BookResponseDTO;
import zw.co.zss.bookshop.dto.CategoryDTO;
import zw.co.zss.bookshop.repository.BookInfoRepository;
import zw.co.zss.bookshop.service.BookInfoService;
import zw.co.zss.bookshop.model.BookInfo;
import zw.co.zss.bookshop.service.CategoryInfoService;
import zw.co.zss.bookshop.util.MapperUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookInfoServiceImpl implements BookInfoService {

    @Autowired
    private BookInfoRepository bookInfoRepository;

    @Autowired
    private CategoryInfoService categoryInfoService;

    @Override
    public BookDTO findBookByTitle(String title) {

        if (Strings.isNotBlank(title)) {

            Optional<BookInfo> bookInfo = bookInfoRepository.findBookInfoByTitle(title);

            if (bookInfo.isPresent()) {

                BookDTO bookDTO = MapperUtil.mapBookInfoToBookDTO(bookInfo.get());

                return bookDTO;
            }
        }
        return null;
    }

    @Override
    public BookDTO findBookById(String id) {

        try {
            if (Strings.isNotBlank(id)) {

                Optional<BookInfo> bookInfo = bookInfoRepository.findBookInfoById(Long.valueOf(id));

                if (bookInfo.isPresent()) {

                    BookDTO bookDTO = MapperUtil.mapBookInfoToBookDTO(bookInfo.get());

                    return bookDTO;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<BookDTO> findBookByCategory(String category) {


        List<BookDTO> bookInfoList = new ArrayList<BookDTO>();

        if (Strings.isNotBlank(category)) {

            List<BookInfo> bookInfos = bookInfoRepository.findBookInfoByCategory(category);

            for (BookInfo bookInfo : bookInfos) {

                BookDTO bookDTO = MapperUtil.mapBookInfoToBookDTO(bookInfo);

                bookInfoList.add(bookDTO);

            }
        }
        return bookInfoList;
    }

    @Override
    public BookResponseDTO saveBook(BookDTO bookDTO) {

        BookResponseDTO bookResponseDTO = new BookResponseDTO();


        try {

            CategoryDTO categoryDTO = categoryInfoService.findCategoryByTitle(bookDTO.getCategory());

            if (ObjectUtils.isEmpty(categoryDTO)) {

                bookResponseDTO.setSuccess(false);
                bookResponseDTO.setNarrative("Invalid Category:" + bookDTO.getCategory());
                return bookResponseDTO;
            }

            BookDTO book = findBookByTitle(bookDTO.getTitle());

            if (ObjectUtils.isEmpty(book)) {

                bookDTO = saveBookDTO(bookDTO);
                bookResponseDTO.setBook(bookDTO);
                bookResponseDTO.setSuccess(true);
                bookResponseDTO.setNarrative("Created Book Successfully");

            } else {

                bookResponseDTO.setSuccess(false);
                bookResponseDTO.setNarrative("Book Already Created");
            }

        } catch (Exception e) {

            e.printStackTrace();

            bookResponseDTO.setSuccess(false);
            bookResponseDTO.setNarrative("Failed to Create Book");
        }

        return bookResponseDTO;
    }

    private BookDTO saveBookDTO(BookDTO bookDTO) throws Exception {

        BookInfo bookInfo = new BookInfo();
        bookInfo.setCategory(bookDTO.getCategory());
        bookInfo.setDescription(bookDTO.getDescription());
        bookInfo.setTitle(bookDTO.getTitle());
        bookInfo.setPrice(bookDTO.getPrice());
        bookInfo.setDateCreated(new Date());
        bookInfo = bookInfoRepository.save(bookInfo);
        bookDTO.setBookId(bookInfo.getId());

        return bookDTO;
    }

    @Override
    public List<BookDTO> findAllBooks() {

        List<BookDTO> bookInfoList = new ArrayList<BookDTO>();

        List<BookInfo> bookInfos = bookInfoRepository.findAll();

        for (BookInfo bookInfo : bookInfos) {

            BookDTO bookDTO = MapperUtil.mapBookInfoToBookDTO(bookInfo);

            bookInfoList.add(bookDTO);

        }
        return bookInfoList;
    }


}
