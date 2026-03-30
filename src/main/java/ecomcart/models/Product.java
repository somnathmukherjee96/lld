package ecomcart.models;

import ecomcart.enums.ProductCategory;

public class Product {
    private final String productId;
    private final String productName;
    private final ProductCategory productCategory;
    private double basePrice;
    private int stockQty;

    public Product(String productId, String productName, ProductCategory productCategory, double basePrice, int stockQty) {
        this.productId = productId;
        this.productName = productName;
        this.productCategory = productCategory;
        this.basePrice = basePrice;
        this.stockQty = stockQty;
    }

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public int getStockQty() {
        return stockQty;
    }

    public void setStockQty(int stockQty) {
        this.stockQty = stockQty;
    }
}
