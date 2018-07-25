package za.co.digitalplatoon.invoiceservice.invoice;

import za.co.digitalplatoon.invoiceservice.util.CurrencyHelper;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Invoice
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String client;
    private Long vatRate;
    private Date invoiceDate;

    @OneToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinColumn(name = "id")
    private List<LineItem> lineItems;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getClient()
    {
        return client;
    }

    public void setClient(String client)
    {
        this.client = client;
    }

    public Long getVatRate()
    {
        return vatRate;
    }

    public void setVatRate(Long vatRate)
    {
        this.vatRate = vatRate;
    }

    public Date getInvoiceDate()
    {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate)
    {
        this.invoiceDate = invoiceDate;
    }

    public List<LineItem> getLineItems() {

        if(this.lineItems==null)
        {
            this.lineItems= new ArrayList<>();
        }
        return this.lineItems;
    }

    public void setLineItems(List<LineItem> lineItems) {
        this.lineItems = lineItems;
    }

    public BigDecimal getSubTotal()
    {
        BigDecimal subTotal = getTotal().subtract(getVat());
        return CurrencyHelper.roundAndScale(subTotal);
    }

    public BigDecimal getVat()
    {
        BigDecimal  effectiveTax = new BigDecimal(this.getVatRate().longValue()).divide(new BigDecimal("100"));
        BigDecimal vat = this.getTotal().multiply(effectiveTax);
        return CurrencyHelper.roundAndScale(vat);
    }

    public BigDecimal getTotal()
    {
        BigDecimal total = new BigDecimal("0");
        for(LineItem lineItem:this.getLineItems())
        {
            total = total.add(lineItem.getLineItemTotal());
        }
        return CurrencyHelper.roundAndScale(total);
    }
}