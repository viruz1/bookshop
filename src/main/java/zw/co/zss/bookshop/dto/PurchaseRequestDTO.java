package zw.co.zss.bookshop.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.HashMap;

@Data
public class PurchaseRequestDTO {

    private String type;

    private String extendedType;

    private BigDecimal amount;

    private String created;

    private CardDTO card;

    private String reference;

    private HashMap<String,Object> additionalData;

    private String narration;

    @Override
    public String toString() {
        return "PurchaseRequestDTO{" +
                "type='" + type + '\'' +
                ", extendedType='" + extendedType + '\'' +
                ", amount=" + amount +
                ", created='" + created + '\'' +
                ", card=" + card.toString() +
                ", reference='" + reference + '\'' +
                ", additionalData=" + additionalData +
                ", narration='" + narration + '\'' +
                '}';
    }
}
