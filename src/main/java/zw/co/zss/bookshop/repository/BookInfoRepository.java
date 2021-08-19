package zw.co.zss.bookshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.zss.bookshop.model.BookInfo;

import java.util.List;
import java.util.Optional;

public interface BookInfoRepository extends JpaRepository<BookInfo, Long> {

    Optional<BookInfo> findBookInfoByTitle(String title);

    Optional<BookInfo> findBookInfoById(Long id);

    List<BookInfo> findBookInfoByCategory(String category);

}
