package shoppingcart;

import shoppingcart.enums.ProductCategory;
import shoppingcart.models.*;
import shoppingcart.services.*;
import shoppingcart.strategy.FlatDiscountStrategy;
import shoppingcart.strategy.PercentageDiscountStrategy;

import java.time.LocalDate;
import java.util.*;

public class ShoppingCartApp {
    public static void main(String[] args) {
        Map<String, String> userToCartRegistry = new HashMap<>();

        User user1 = new User("somnath", "Somnath Mukherjee", "som@example.com");
        Cart cart1 = new Cart("CART01", user1.getId());

        userToCartRegistry.put(user1.getId(), cart1.getCartId());

        Map<String, Product> productInventory = populateProductRegistry();

        InventoryService inventoryService = new InventoryService();
        inventoryService.addInventory(productInventory.get("P10"), 100);
        inventoryService.addInventory(productInventory.get("P16"), 20);

        ProductService productService = new ProductService(productInventory);

        CartService cartService = new CartService(inventoryService, productService);
        cartService.addCartToTheRegistry(cart1);

        inventoryService.addObserver(cartService);

        cartService.addItem(cart1.getCartId(), "P10", 1);
        cartService.addItem(cart1.getCartId(), "P16", 2);

        double subTotal = cartService.getRawSubtotal(cart1.getCartId());
        System.out.println("Total cart value before applying coupon is " + subTotal);
        Map<String, Discount> discountStrategy = populateDiscountRegistry();

        DiscountService discountService = new DiscountService(discountStrategy, populateCouponRegistry(discountStrategy));
        subTotal = discountService.applyCoupon(cart1, "FASHION1000");
        System.out.println("Total cart value after applying coupon is " + subTotal);

        CheckoutService checkoutService = new CheckoutService(new PricingService(discountService));
        checkoutService.checkout(cart1);
    }

    private static Map<String, Product> populateProductRegistry() {
        Map<String, Product> productRegistry = new HashMap<>();
// ELECTRONICS
        productRegistry.put("P01", new Product("P01", "IPHONE 17 PRO", ProductCategory.ELECTRONICS, 80000.00));
        productRegistry.put("P02", new Product("P02", "SAMSUNG 65\" 4K TV", ProductCategory.ELECTRONICS, 120000.00));
        productRegistry.put("P03", new Product("P03", "SONY WIRELESS HEADPHONES", ProductCategory.ELECTRONICS, 15000.00));

// CLOTHING
        productRegistry.put("P04", new Product("P04", "NIKE RUNNING SHOES", ProductCategory.CLOTHING, 8500.00));
        productRegistry.put("P05", new Product("P05", "ADIDAS SPORTS T-SHIRT", ProductCategory.CLOTHING, 2500.00));
        productRegistry.put("P06", new Product("P06", "LEVIS DENIM JEANS", ProductCategory.CLOTHING, 5500.00));

// GROCERY
        productRegistry.put("P07", new Product("P07", "BASMATI RICE 5KG", ProductCategory.GROCERY, 450.00));
        productRegistry.put("P08", new Product("P08", "WHOLE WHEAT FLOUR 2KG", ProductCategory.GROCERY, 180.00));
        productRegistry.put("P09", new Product("P09", "ORGANIC HONEY 500ML", ProductCategory.GROCERY, 650.00));

// BOOKS
        productRegistry.put("P10", new Product("P10", "CLEAN CODE BY ROBERT MARTIN", ProductCategory.BOOKS, 1200.00));
        productRegistry.put("P11", new Product("P11", "ATOMIC HABITS BY JAMES CLEAR", ProductCategory.BOOKS, 950.00));
        productRegistry.put("P12", new Product("P12", "THE PRAGMATIC PROGRAMMER", ProductCategory.BOOKS, 1400.00));

// HOME_APPLIANCES
        productRegistry.put("P13", new Product("P13", "BOSCH WASHING MACHINE", ProductCategory.HOME_APPLIANCES, 45000.00));
        productRegistry.put("P14", new Product("P14", "PHILIPS MICROWAVE OVEN", ProductCategory.HOME_APPLIANCES, 12000.00));
        productRegistry.put("P15", new Product("P15", "WHIRLPOOL REFRIGERATOR", ProductCategory.HOME_APPLIANCES, 55000.00));

// SPORTS
        productRegistry.put("P16", new Product("P16", "CRICKET BAT ENGLISH WILLOW", ProductCategory.SPORTS, 8000.00));
        productRegistry.put("P17", new Product("P17", "BADMINTON RACKET SET", ProductCategory.SPORTS, 3500.00));
        productRegistry.put("P18", new Product("P18", "YOGA MAT WITH CARRYING STRAP", ProductCategory.SPORTS, 1200.00));

        return productRegistry;
    }

    private static Map<String, Discount> populateDiscountRegistry() {
        Map<String, Discount> discountRegistry = new HashMap<>();

        discountRegistry.put("D01", new Discount(
                "D01",
                "Electronics Mega Sale - 15% off",
                new PercentageDiscountStrategy(15.0),
                15.0,
                50000,
                12000.00,
                Set.of(ProductCategory.ELECTRONICS),
                LocalDate.of(2026, 6, 30)
        ));

        discountRegistry.put("D02", new Discount(
                "D02",
                "Clothing Festival - Flat 1000 off",
                new FlatDiscountStrategy(1000.0),
                1000.0,
                5000,
                2000.00,
                Set.of(ProductCategory.CLOTHING),
                LocalDate.of(2026, 5, 15)
        ));

        discountRegistry.put("D03", new Discount(
                "D03",
                "Grocery Bundle - 10% off",
                new PercentageDiscountStrategy(10.0),
                10.0,
                2000,
                500.00,
                Set.of(ProductCategory.GROCERY),
                LocalDate.of(2026, 4, 30)
        ));

        // Converted BOGO → Flat approximation
        discountRegistry.put("D04", new Discount(
                "D04",
                "Books Bonanza - Flat 50% equivalent",
                new PercentageDiscountStrategy(50.0),
                50.0,
                1000,
                5000.00,
                Set.of(ProductCategory.BOOKS),
                LocalDate.of(2026, 7, 31)
        ));

        discountRegistry.put("D05", new Discount(
                "D05",
                "Home Appliances Clearance - 20% off",
                new PercentageDiscountStrategy(20.0),
                20.0,
                40000,
                15000.00,
                Set.of(ProductCategory.HOME_APPLIANCES),
                LocalDate.of(2026, 5, 31)
        ));

        discountRegistry.put("D06", new Discount(
                "D06",
                "Sports Extravaganza - Flat 800 off",
                new FlatDiscountStrategy(800.0),
                800.0,
                3000,
                1500.00,
                Set.of(ProductCategory.SPORTS),
                LocalDate.of(2026, 6, 15)
        ));

        discountRegistry.put("D07", new Discount(
                "D07",
                "Tech & Home - 12% off",
                new PercentageDiscountStrategy(12.0),
                12.0,
                30000,
                8000.00,
                Set.of(ProductCategory.ELECTRONICS, ProductCategory.HOME_APPLIANCES),
                LocalDate.of(2026, 8, 31)
        ));

        // Converted Tiered → Single max percentage (simplified)
        discountRegistry.put("D08", new Discount(
                "D08",
                "Mega Sale - 25% off",
                new PercentageDiscountStrategy(25.0),
                25.0,
                10000,
                20000.00,
                Set.of(ProductCategory.ELECTRONICS, ProductCategory.CLOTHING, ProductCategory.HOME_APPLIANCES),
                LocalDate.of(2026, 9, 30)
        ));

        return discountRegistry;
    }

    // COUPON REGISTRY POPULATION
    private static Map<String, Coupon> populateCouponRegistry(Map<String, Discount> discountRegistry) {
        Map<String, Coupon> couponRegistry = new HashMap<>();

        couponRegistry.put("SAVE15TECH", new Coupon("SAVE15TECH", discountRegistry.get("D01"), 100, 50000));
        couponRegistry.put("FASHION1000", new Coupon("FASHION1000", discountRegistry.get("D02"), 150, 5000));
        couponRegistry.put("GROCERY10", new Coupon("GROCERY10", discountRegistry.get("D03"), 200, 2000));
        couponRegistry.put("BOOKBOGO", new Coupon("BOOKBOGO", discountRegistry.get("D04"), 75, 1000));
        couponRegistry.put("HOME20OFF", new Coupon("HOME20OFF", discountRegistry.get("D05"), 120, 40000));
        couponRegistry.put("SPORTS800", new Coupon("SPORTS800", discountRegistry.get("D06"), 90, 3000));
        couponRegistry.put("TECHHOME12", new Coupon("TECHHOME12", discountRegistry.get("D07"), 110, 30000));
        couponRegistry.put("MEGASAVE25", new Coupon("MEGASAVE25", discountRegistry.get("D08"), 50, 10000));

        // Special variants
        couponRegistry.put("VIP2026", new Coupon("VIP2026", discountRegistry.get("D01"), 500, 50000));
        couponRegistry.put("FLASH50", new Coupon("FLASH50", discountRegistry.get("D02"), 25, 5000));

        return couponRegistry;
    }
}
