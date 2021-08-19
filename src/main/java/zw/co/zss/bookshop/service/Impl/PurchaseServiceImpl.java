package zw.co.zss.bookshop.service.Impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;
import zw.co.zss.bookshop.dto.*;
import zw.co.zss.bookshop.service.BookInfoService;
import zw.co.zss.bookshop.service.PurchaseService;
import zw.co.zss.bookshop.util.DateUtil;
import zw.co.zss.bookshop.util.GenerateKey;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    private static Logger log = LoggerFactory.getLogger(PurchaseService.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private BookInfoService bookInfoService;

    @Value("${purchase.url}")
    private String purchaseUrl;

    @Value("${purchase.key}")
    private String purchaseToken;

    @Override
    public PurchaseBookResponseDTO purchaseBook(PurchaseBookRequestDTO purchaseBookRequestDTO) {
        return postPurchaseBook(purchaseBookRequestDTO);
    }


    private PurchaseRequestDTO mapDetailsForPurchase(PurchaseBookRequestDTO purchaseBookRequestDTO,BookDTO bookDTO) {

        PurchaseRequestDTO purchaseRequestDTO = new PurchaseRequestDTO();
        purchaseRequestDTO.setCreated(DateUtil.convertToXMLGregorianCalendar(new Date()).toString());
        purchaseRequestDTO.setCard(purchaseBookRequestDTO.getCard());
        purchaseRequestDTO.setType("PURCHASE");
        purchaseRequestDTO.setExtendedType("NONE");
        purchaseRequestDTO.setReference(GenerateKey.generateReference());
        purchaseRequestDTO.setNarration("Pos Purchase for Book:"+bookDTO.getTitle());
        purchaseRequestDTO.setAmount(bookDTO.getPrice().multiply(new BigDecimal(purchaseBookRequestDTO.getQuantity())));

        return purchaseRequestDTO;

    }

    private PurchaseBookResponseDTO postPurchaseBook(PurchaseBookRequestDTO purchaseBookRequestDTO) {

        PurchaseBookResponseDTO purchaseBookResponseDTO = new PurchaseBookResponseDTO();

        BookDTO bookDTO = bookInfoService.findBookById(String.valueOf(purchaseBookRequestDTO.getBookId()));

        if(ObjectUtils.isEmpty(bookDTO)){

            purchaseBookResponseDTO.setResponseCode("404");
            purchaseBookResponseDTO.setNarrative("Book Not Found:"+purchaseBookRequestDTO.getBookId());
            return  purchaseBookResponseDTO;
        }
        PurchaseRequestDTO purchaseRequestDTO = mapDetailsForPurchase(purchaseBookRequestDTO,bookDTO);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));


        headers.add("Authorization", "Basic " + purchaseToken);

        System.out.println(purchaseRequestDTO.toString());
        HttpEntity<PurchaseRequestDTO> entity = new HttpEntity<>(purchaseRequestDTO, headers);


        ResponseEntity<PurchaseResponseDTO> responseEntity = restTemplate.exchange(purchaseUrl, HttpMethod.POST, entity, PurchaseResponseDTO.class);

        log.info("Message Recevied:" + responseEntity.getBody().getResponseCode() + ":" + responseEntity.getBody().getResponseDescription());


        PurchaseResponseDTO purchaseResponseDTO = responseEntity.getBody();

        purchaseBookResponseDTO.setNarrative(purchaseResponseDTO.getResponseDescription());
        purchaseBookResponseDTO.setResponseCode(purchaseResponseDTO.getResponseCode());
        purchaseBookResponseDTO.setReference(purchaseResponseDTO.getReference());
        purchaseResponseDTO.setDebitReference(purchaseResponseDTO.getDebitReference());

        return purchaseBookResponseDTO;

    }
}
