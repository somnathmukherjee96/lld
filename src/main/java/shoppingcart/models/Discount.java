package shoppingcart.models;

import shoppingcart.enums.ProductCategory;
import shoppingcart.strategy.DiscountStrategy;

import java.time.LocalDate;
import java.util.Set;

public record Discount(String discountId, String description, DiscountStrategy discountType, double value,
                       int minOrderValue, double maxDiscountCap, Set<ProductCategory> applicableCategories,
                       LocalDate expiryDate) {

    public boolean isExpired() {
        return LocalDate.now().isAfter(expiryDate);
    }

    public boolean isApplicableToCategory(ProductCategory cat) {
        return applicableCategories.isEmpty() || applicableCategories.contains(cat);
    }
}
