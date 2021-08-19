package zw.co.zss.bookshop.service.Impl;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import zw.co.zss.bookshop.dto.CategoryDTO;
import zw.co.zss.bookshop.dto.CategoryResponseDTO;
import zw.co.zss.bookshop.model.CategoryInfo;
import zw.co.zss.bookshop.repository.CategoryInfoRepository;
import zw.co.zss.bookshop.service.CategoryInfoService;
import zw.co.zss.bookshop.util.MapperUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryInfoServiceImpl implements CategoryInfoService {

    @Autowired
    private CategoryInfoRepository categoryInfoRepository;

    @Override
    public CategoryDTO findCategoryByTitle(String title) {

        if (Strings.isNotBlank(title)) {

            Optional<CategoryInfo> categoryInfo = categoryInfoRepository.findCategoryInfoByTitle(title);

            if (categoryInfo.isPresent()) {

                CategoryDTO categoryDTO = MapperUtil.mapCategoryInfoInfoToCategoryDTO(categoryInfo.get());

                return categoryDTO;
            }
        }
        return null;
    }

    @Override
    public CategoryResponseDTO saveCategory(CategoryDTO categoryDTO) {

        CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO();


        try {

            CategoryDTO category = findCategoryByTitle(categoryDTO.getTitle());

            if (ObjectUtils.isEmpty(category)) {

                CategoryInfo categoryInfo = new CategoryInfo();
                categoryInfo.setDateCreated(new Date());
                categoryInfo.setTitle(categoryDTO.getTitle());

                categoryInfo = categoryInfoRepository.save(categoryInfo);


                categoryResponseDTO.setCategory(categoryDTO);

                categoryResponseDTO.setSuccess(true);
                categoryResponseDTO.setNarrative("Created Category Successfully");
            } else {

                categoryResponseDTO.setSuccess(false);
                categoryResponseDTO.setNarrative("Category Already Created ");
            }

        } catch (Exception e) {

            e.printStackTrace();
            categoryResponseDTO.setSuccess(false);
            categoryResponseDTO.setNarrative("Failed to Create Category");
        }


        return categoryResponseDTO;
    }


    @Override
    public List<CategoryDTO> findAllCategories() {

        List<CategoryDTO> categories = new ArrayList<CategoryDTO>();

        List<CategoryInfo> categoryInfos = categoryInfoRepository.findAll();

        for (CategoryInfo categoryInfo : categoryInfos) {

            CategoryDTO categoryDTO = MapperUtil.mapCategoryInfoInfoToCategoryDTO(categoryInfo);

            categories.add(categoryDTO);
        }

        return categories;
    }
}
