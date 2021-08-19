package zw.co.zss.bookshop.dto;

import lombok.Data;

@Data
public class BookResponseDTO {

    private boolean success;

    private String narrative;

    private BookDTO book;
}
