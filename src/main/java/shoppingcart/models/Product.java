package shoppingcart.models;

import shoppingcart.enums.ProductCategory;

public class Product {
    private final String productId;
    private final String productName;
    private final ProductCategory productCategory;
    private double basePrice;

    public Product(String productId, String productName, ProductCategory productCategory, double basePrice) {
        this.productId = productId;
        this.productName = productName;
        this.productCategory = productCategory;
        this.basePrice = basePrice;
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
}
