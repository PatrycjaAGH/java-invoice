package pl.edu.agh.mwo.invoice;

import pl.edu.agh.mwo.invoice.product.Product;

import java.math.BigDecimal;
import java.util.Map;

public class Printer {
    public String print(Invoice invoice) {
        String results = "Invoice: " + invoice.getNumber();
        int lp = 1;
        for (Map.Entry<Product, Integer> p : invoice.getProducts()) {
            Product product = p.getKey();
            int count = p.getValue();
            BigDecimal price = BigDecimal.valueOf(count).multiply(product.getPrice());
            results += "\n" + lp + ". " + product.getName() + ", count: " + count
                    + ", price: " + price + ".";
            lp++;
        }
        results += "\nIn total: " + invoice.getNumberOfProducts() + " products";
        return results;
    }

}
