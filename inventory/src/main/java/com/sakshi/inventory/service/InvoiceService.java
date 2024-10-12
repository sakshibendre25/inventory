package com.sakshi.inventory.service;

import com.sakshi.inventory.dto.InvoiceDTO;
import com.sakshi.inventory.entity.Invoice;
import com.sakshi.inventory.errorcodes.ErrorCodes;
import com.sakshi.inventory.exception.NotFoundException;
import com.sakshi.inventory.repository.InvoiceRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final ModelMapper modelMapper;

    public InvoiceService(InvoiceRepository invoiceRepository, ModelMapper modelMapper) {
        this.invoiceRepository = invoiceRepository;
        this.modelMapper = modelMapper;
    }

    public Class<InvoiceDTO> getEntityDTOClass() {
        return InvoiceDTO.class;
    }

    public Class<Invoice> getEntityClass() {
        return Invoice.class;

    }
        public InvoiceDTO save(InvoiceDTO invoiceDTO) {


        Invoice invoice = modelMapper.map(invoiceDTO, Invoice.class);
        Invoice savedInvoice = invoiceRepository.save(invoice);
        return modelMapper.map(savedInvoice, InvoiceDTO.class);
    }


    public List<InvoiceDTO> findAll() {


        return invoiceRepository.findAll().stream()
                .map(entity -> modelMapper.map(entity, InvoiceDTO.class)).toList();

    }


    public void delete(InvoiceDTO invoiceDTO) {


        if(invoiceDTO.getInvoiceId() == null || !invoiceRepository.existsById(invoiceDTO.getInvoiceId())) {
            throw  new NotFoundException(ErrorCodes.INVOICE_NOT_FOUND,"Invoice not found");
        }

        invoiceRepository.deleteById(invoiceDTO.getInvoiceId());
    }



    public InvoiceDTO update(InvoiceDTO invoiceDTO) {

        if (invoiceDTO.getInvoiceId() == null) {
            throw new NotFoundException(ErrorCodes.INVOICE_NOT_FOUND, "Invoice id not found.");
        }

        Invoice existingInvoice = findEntityById(invoiceDTO.getInvoiceId());
        modelMapper.map(invoiceDTO, existingInvoice);
        Invoice updatedInvoice = invoiceRepository.save(existingInvoice);
        return modelMapper.map(updatedInvoice, InvoiceDTO.class);
}

    public InvoiceDTO findById(Long id) {
            Invoice invoice = findEntityById(id);
            return modelMapper.map(invoice, InvoiceDTO.class);
        }

        public Invoice findEntityById(Long id) {
            return invoiceRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException(ErrorCodes.INVOICE_NOT_FOUND, "Invoice not found!"));
        }



    }










