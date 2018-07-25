package za.co.digitalplatoon.invoiceservice.invoice;

import za.co.digitalplatoon.invoiceservice.util.CurrencyHelper;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class LineItem
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long quantity;
    private String description;
    @Column(scale = 2)
    private BigDecimal unitPrice;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getQuantity()
    {
        return quantity;
    }

    public void setQuantity(Long quantity)
    {
        this.quantity = quantity;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public BigDecimal getUnitPrice()
    {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice)
    {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getLineItemTotal()
    {
        BigDecimal itemTotal = this.getUnitPrice().multiply(new BigDecimal(this.getQuantity().longValue()));
        return CurrencyHelper.roundAndScale(itemTotal);
    }
}
