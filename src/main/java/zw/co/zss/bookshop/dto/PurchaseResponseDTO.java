package zw.co.zss.bookshop.dto;

import lombok.Data;

@Data
public class PurchaseResponseDTO {

    private String updated;

    private String responseCode;

    private String responseDescription;

    private String reference;

    private String debitReference;


}
