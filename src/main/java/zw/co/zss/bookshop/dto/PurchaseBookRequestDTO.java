package zw.co.zss.bookshop.dto;

import lombok.Data;

@Data
public class PurchaseBookRequestDTO {

    private Long bookId;

    private int quantity;

    private CardDTO card;

}
