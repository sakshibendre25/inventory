package com.sakshi.inventory.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private  Long invoiceId;

    @Column
    private  Long customerId;

    @Column
    private Long receiptNo;

    @Column
    private  Long invoiceNo;

    @Column
    private LocalDateTime createdDate = LocalDateTime.now();

    @Column
    private  String createdBy;

    @Column
    private  String customerName;

    @Column
    private String customerContact;

    @Column
    private  Float discountPercentage;

    @Column
    private Double discountAmount;

    @Column
    private Double subTotal;

    @Column
    private  Float taxes;

    @Column
    private Long currency_id;



}
