package zw.co.zss.bookshop.dto;

import lombok.Data;

@Data
public class CardDTO {

    private String id;

    private String expiry;

    @Override
    public String toString() {
        return "CardDTO{" +
                "id='" + id + '\'' +
                ", expiry='" + expiry + '\'' +
                '}';
    }
}
