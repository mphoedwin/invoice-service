package za.co.digitalplatoon.invoiceservice.invoice;

import za.co.digitalplatoon.invoiceservice.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/invoices")
public class InvoiceController
{
    private InvoiceService invoiceService;

    @Autowired
    public InvoiceController(InvoiceService invoiceService)
    {
        this.invoiceService = invoiceService;
    }

    @PutMapping
    @ResponseBody
    public Invoice addInvoice(@RequestBody Invoice invoice)
    {
        return invoiceService.addInvoice(invoice);
    }

    @GetMapping
    public List<Invoice> viewAllInvoices()
    {
        return invoiceService.findAll();
    }

    @GetMapping("/{invoiceId}")
    public Invoice viewInvoice(@PathVariable Long invoiceId)
    {
        return invoiceService.findById(invoiceId);
    }

}