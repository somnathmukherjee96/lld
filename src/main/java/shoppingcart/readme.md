
---

**Final class skeleton**
```
Enums
CartStatus          — ACTIVE, CHECKED_OUT, ABANDONED
ProductCategory     — ELECTRONICS, CLOTHING, FOOD

Models
User                — id, name, email
Product             — id, name, price, category
CartItem            — Product (live ref), quantity
Cart                — id, userId, List<CartItem>, Coupon, CartStatus
Coupon              — code, DiscountStrategy, minCartValue, expiryDate, isActive
Order (Builder)     — id, userId, List<OrderItem>, totalAmount, couponCode, createdAt
OrderItem           — productId, productName, priceAtCheckout, quantity

Strategy
DiscountStrategy          — double apply(double cartTotal)
PercentageDiscountStrategy
FlatDiscountStrategy

Chain of Responsibility
CouponValidator           — abstract, has next
ExpiryValidator
ActiveStatusValidator
MinCartValueValidator

Observer
InventoryObservable       — on stock change
InventoryObserver         — CartService, NotificationService

Services
InventoryService    — productId→stock, thread-safe, notifies observers
CartService         — userId→cart registry, add/remove items
CouponService       — builds validator chain, applies coupon
PricingService      — calculates total with discounts
CheckoutService     — cart→order conversion