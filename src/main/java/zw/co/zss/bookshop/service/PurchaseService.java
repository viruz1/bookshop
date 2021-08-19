package zw.co.zss.bookshop.service;

import org.springframework.stereotype.Service;
import zw.co.zss.bookshop.dto.PurchaseBookRequestDTO;
import zw.co.zss.bookshop.dto.PurchaseBookResponseDTO;


public interface PurchaseService {

   PurchaseBookResponseDTO purchaseBook(PurchaseBookRequestDTO purchaseBookRequestDTO);

}
