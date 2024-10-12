package com.sakshi.inventory.dto;

import com.sakshi.inventory.entity.Invoice;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter

public class InvoiceDTO {

    private  Long invoiceId;

    private  Long customerId;

    private Long receiptNo;

    private  Long invoiceNo;

    private LocalDateTime createdDate = LocalDateTime.now();

    private  String createdBy;

    private  String customerName;

    private String customerContact;

    private  Float discountPercentage;

    private Double discountAmount;

    private Double subTotal;

    private  Float taxes;

    private Long currency_id;




}