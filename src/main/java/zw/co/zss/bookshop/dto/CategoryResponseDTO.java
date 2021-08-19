package zw.co.zss.bookshop.dto;

import lombok.Data;

@Data
public class CategoryResponseDTO {

    private boolean success;

    private String narrative;

    private CategoryDTO category;
}
