package zw.co.zss.bookshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.zss.bookshop.model.CategoryInfo;
import java.util.Optional;

public interface CategoryInfoRepository extends JpaRepository<CategoryInfo,Long> {

    Optional<CategoryInfo> findCategoryInfoByTitle(String title);

}
