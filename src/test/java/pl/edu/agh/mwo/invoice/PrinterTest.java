package pl.edu.agh.mwo.invoice;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import pl.edu.agh.mwo.invoice.product.Product;
import pl.edu.agh.mwo.invoice.product.TaxFreeProduct;

import java.math.BigDecimal;

public class PrinterTest {

    @Test
    public void testPrinterReturnsCorrectStringWhenInvoiceIsEmpty() {
       Invoice.nextNumber = 0;
       Invoice invoice = new Invoice();
       Printer printer = new Printer();
       String expected = "Invoice: 1\nIn total: 0 products";
       String actual = printer.print(invoice);
       Assert.assertThat(actual, Matchers.comparesEqualTo(expected));
    }

    @Test
    public void testPrinterReturnsCorrectStringWhenInvoiceHasOneProduct() {
        Invoice.nextNumber = 7;
        Invoice invoice = new Invoice();
        Printer printer = new Printer();
        Product onions = new TaxFreeProduct("Warzywa", new BigDecimal("10"));
        invoice.addProduct(onions);
        String expected = "Invoice: 8\n1. Warzywa, count: 1, price: 10.\nIn total: 1 products";
        String actual = printer.print(invoice);
        Assert.assertThat(actual, Matchers.comparesEqualTo(expected));
    }

    @Test
    public void testPrinterReturnsCorrectStringWhenInvoiceHasManyProducts() {
        Invoice.nextNumber = 9;
        Invoice invoice = new Invoice();
        Printer printer = new Printer();
        Product onion = new TaxFreeProduct("Warzywa", new BigDecimal("10"));
        invoice.addProduct(onion);
        Product apples = new TaxFreeProduct("Owoce", new BigDecimal("10"));
        invoice.addProduct(apples, 2);
        Product butter = new TaxFreeProduct("Maslo", new BigDecimal("10"));
        invoice.addProduct(butter);
        String expected = "Invoice: 10\n" +
                "1. Maslo, count: 1, price: 10.\n" +
                "2. Owoce, count: 2, price: 20.\n" +
                "3. Warzywa, count: 1, price: 10.\n" +
                "In total: 3 products";
        String actual = printer.print(invoice);
        Assert.assertThat(actual, Matchers.comparesEqualTo(expected));
    }
}

// Invoice: 1\n1. Maslo, count: 1, price: 10.\n2. Owoce, count: 2, price: 20.\n3. Warzywa, count: 1, price: 10.\nIn total: 3 products
//Invoice: 1\n1. Maslo, count: 1, price: 10.\n1. Owoce, count: 2, price: 20.\n1. Warzywa, count: 1, price: 10.\nIn total: 3 products