package com.sakshi.inventory.controller;

import com.sakshi.inventory.dto.InvoiceDTO;
import com.sakshi.inventory.entity.Invoice;
import com.sakshi.inventory.service.InvoiceService;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("invoice/v1")
public class InvoiceController {

   private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }


    @PostMapping(path = "/save")
    public ResponseEntity<InvoiceDTO> createInvoice(@RequestBody InvoiceDTO invoiceDTO) {
        InvoiceDTO savedInvoice = invoiceService.save(invoiceDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedInvoice);
    }

    @GetMapping(path = "/by-invoice")
    public ResponseEntity<List<InvoiceDTO>> findAll() {
        return ResponseEntity.ok(invoiceService.findAll());

    }

    @GetMapping("/by-invoice/{id}")
    public ResponseEntity<InvoiceDTO> getInvoiceById(@PathVariable Long id) {
        InvoiceDTO invoiceDTO = invoiceService.findById(id);

        return ResponseEntity.ok(invoiceDTO);

    }


    @DeleteMapping("/by-invoice/{id}")
    public  ResponseEntity<Void> deleteInvoice(@PathVariable Long id) throws ChangeSetPersister.NotFoundException {
        InvoiceDTO invoiceDTO = new InvoiceDTO();
        invoiceDTO.setInvoiceId(id);
        invoiceService.delete(invoiceDTO);
        return ResponseEntity.noContent().build();
    }


    @PutMapping("/by-invoice/{id}")
    public ResponseEntity<InvoiceDTO> updatedInvoice(@PathVariable Long id,@RequestBody InvoiceDTO invoiceDTO) {
        invoiceDTO.setInvoiceId(id);
        InvoiceDTO updatedInvoice = invoiceService.update(invoiceDTO);
        return ResponseEntity.ok(updatedInvoice);
    }




}