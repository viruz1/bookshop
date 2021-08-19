package zw.co.zss.bookshop.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BookDTO {

    private Long bookId;

    private String title;

    private String description;

    private BigDecimal price;

    private String category;

}
