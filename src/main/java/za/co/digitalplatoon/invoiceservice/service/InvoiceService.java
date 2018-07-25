package za.co.digitalplatoon.invoiceservice.service;

import za.co.digitalplatoon.invoiceservice.invoice.Invoice;
import za.co.digitalplatoon.invoiceservice.invoice.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceService
{
    private InvoiceRepository invoiceRepository;

    @Autowired
    public InvoiceService(InvoiceRepository invoiceRepository)
    {
        this.invoiceRepository = invoiceRepository;
    }

    public List<Invoice> findAll()
    {
        return invoiceRepository.findAll();
    }

    public Invoice findById(Long invoiceId)
    {
        return invoiceRepository.findOne(invoiceId);
    }

    public Invoice addInvoice(Invoice invoice)
    {
        return invoiceRepository.save(invoice);
    }

}