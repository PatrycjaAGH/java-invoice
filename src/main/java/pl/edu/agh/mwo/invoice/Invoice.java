package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {
    //private Collection<Product> products;

    private Map<Product, Integer> products=new HashMap<>();

    public void addProduct(Product product) {
        if (product == null) throw new IllegalArgumentException();
        this.products.put(product, 1);
    }

    public void addProduct(Product product, Integer quantity) {
        if (quantity <= 0) throw new IllegalArgumentException();
        if (product == null) throw new IllegalArgumentException();
        this.products.put(product, quantity);
    }

    public BigDecimal getNetTotal() {
        BigDecimal result = BigDecimal.ZERO;
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            int quantity = entry.getValue();
            Product product = entry.getKey();

            result = result.add(product.getPrice().multiply(BigDecimal.valueOf(quantity)));
        }
        return result;
    }

    public BigDecimal getTax() {
        BigDecimal result = BigDecimal.ZERO;
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            int quantity = entry.getValue();
            Product product = entry.getKey();
            BigDecimal quantityBD = BigDecimal.valueOf(quantity);
            BigDecimal price = product.getPrice();
            BigDecimal taxPer = product.getTaxPercent();
            BigDecimal tax = price.multiply(taxPer);
            BigDecimal totalTax = tax.multiply(quantityBD);
            result = result.add(totalTax);
        }
        return result;
    }

    public BigDecimal getTotal() {
        return getNetTotal().add(getTax());
    }
}
