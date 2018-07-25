package za.co.digitalplatoon.invoiceservice.util;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class CurrencyHelper {

    private CurrencyHelper(){}

    public static BigDecimal roundAndScale(BigDecimal amount)
    {
        return amount!=null ? new BigDecimal(amount.longValue(), new MathContext(4, RoundingMode.HALF_UP)) : null;
    }

}