package ecomcart.models;

import ecomcart.enums.DiscountType;
import ecomcart.enums.ProductCategory;

import java.time.LocalDate;
import java.util.Set;

public class Discount {
    private final String discountId;
    private final String description;
    private final DiscountType discountType;
    private final double value;
    private final int buyX;
    private final int minOrderValue;
    private final double maxDiscountCap;
    private final Set<ProductCategory> applicableCategories;
    private final LocalDate expiryDate;

    public Discount(String discountId, String description, DiscountType discountType, double value, int buyX, int minOrderValue, double maxDiscountCap, Set<ProductCategory> applicableCategories, LocalDate expiryDate) {
        this.discountId = discountId;
        this.description = description;
        this.discountType = discountType;
        this.value = value;
        this.buyX = buyX;
        this.minOrderValue = minOrderValue;
        this.maxDiscountCap = maxDiscountCap;
        this.applicableCategories = applicableCategories;
        this.expiryDate = expiryDate;
    }

    public boolean isExpired() {
        return LocalDate.now().isAfter(expiryDate);
    }

    public boolean isApplicableToCategory(ProductCategory cat) {
        return applicableCategories.isEmpty() || applicableCategories.contains(cat);
    }

    public String getDiscountId() {
        return discountId;
    }

    public String getDescription() {
        return description;
    }

    public DiscountType getDiscountType() {
        return discountType;
    }

    public double getValue() {
        return value;
    }

    public int getBuyX() {
        return buyX;
    }

    public int getMinOrderValue() {
        return minOrderValue;
    }

    public double getMaxDiscountCap() {
        return maxDiscountCap;
    }

    public Set<ProductCategory> getApplicableCategories() {
        return applicableCategories;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }
}
