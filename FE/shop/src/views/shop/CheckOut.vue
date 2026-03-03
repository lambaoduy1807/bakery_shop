<template>
  <div class="checkout-container">

    <!-- LEFT: Billing Details -->
    <div class="billing-box">
      <h2>Billing Details</h2>

      <div class="form-group">
        <label>First Name</label>
        <input v-model="billing.firstName" type="text" placeholder="Enter first name" />
      </div>

      <div class="form-group">
        <label>Phone Number</label>
        <input v-model="billing.phone" type="text" placeholder="Phone number" />
      </div>

      <div class="form-group">
        <label>Email</label>
        <input v-model="billing.email" type="email" placeholder="Email address" />
      </div>

      <div class="form-group">
        <label>Street Address</label>
        <input v-model="billing.address" type="text" placeholder="Street address" />
      </div>

      <!-- Save Info -->
      <div class="save-info">
        <input type="checkbox" v-model="billing.save" />
        <label>Save this information for faster checkout next time</label>
      </div>
    </div>

    <!-- RIGHT: ORDER SUMMARY -->
    <div class="order-box">
      <h3>Your Order</h3>

      <!-- Product List -->
      <div v-for="item in cartItems" :key="item.id" class="order-item">
        <img :src="item.image" />
        <div class="item-info">
          <p>{{ item.name }}</p>
          <span>{{ formatPrice(item.price * item.qty) }}</span>
        </div>
      </div>

      <!-- Summary -->
      <div class="summary-row">
        <span>Subtotal:</span>
        <span>{{ formatPrice(totalPrice) }}</span>
      </div>

      <div class="summary-row">
        <span>Shipping:</span>
        <span>{{ formatPrice(shipping) }}</span>
      </div>

      <div class="summary-row total">
        <span>Total:</span>
        <span>{{ formatPrice(totalPrice + shipping) }}</span>
      </div>

      <!-- Payment Method -->
      <h4>Payment Method</h4>
      <div class="payment-row">
        <input type="radio" value="cod" v-model="paymentMethod" />
        <label>Cash on Delivery</label>
      </div>

      <div class="payment-row">
        <input type="radio" value="bank" v-model="paymentMethod" />
        <label>Bank Transfer</label>
      </div>

      <!-- Coupon -->
      <div class="coupon-row">
        <input v-model="coupon" type="text" placeholder="Enter coupon" />
        <button class="apply-btn">Apply</button>
      </div>

      <!-- Pay Button -->
      <button class="pay-btn">Complete Payment</button>
    </div>

  </div>
</template>

<script setup>
import { ref, computed } from "vue";

const cartItems = ref([
  { id: 1, name: "Product A", price: 200000, qty: 1, image: "https://via.placeholder.com/80" },
  { id: 2, name: "Product B", price: 300000, qty: 2, image: "https://via.placeholder.com/80" }
]);

const coupon = ref("");
const paymentMethod = ref("cod");

const billing = ref({
  firstName: "",
  phone: "",
  email: "",
  address: "",
  save: false
});

const shipping = 30000; // phí vận chuyển

const totalPrice = computed(() =>
  cartItems.value.reduce((sum, i) => sum + i.price * i.qty, 0)
);

function formatPrice(value) {
  return value.toLocaleString("vi-VN") + "₫";
}
</script>

<style scoped>
.checkout-container {
  width: 90%;
  margin: 40px auto;
  display: flex;
  justify-content: space-between;
  gap: 40px;
  font-family: sans-serif;
}

/* LEFT SIDE */
.billing-box {
  width: 55%;
}

.form-group {
  display: flex;
  flex-direction: column;
  margin: 15px 0;
}

.form-group input {
  padding: 12px;
  border-radius: 6px;
  border: 1px solid #ddd;
}

.save-info {
  display: flex;
  align-items: center;
  gap: 8px;
  margin-top: 12px;
}

/* RIGHT SIDE */
.order-box {
  width: 40%;
  border: 1px solid #ddd;
  padding: 20px;
  border-radius: 12px;
}

.order-item {
  display: flex;
  align-items: center;
  gap: 12px;
  margin-bottom: 15px;
}

.order-item img {
  width: 60px;
  height: 60px;
  border-radius: 6px;
  object-fit: cover;
}

.item-info {
  display: flex;
  width: 100%;
  justify-content: space-between;
}

.summary-row {
  display: flex;
  justify-content: space-between;
  margin-top: 10px;
}

.total {
  font-weight: bold;
  font-size: 18px;
  margin-top: 10px;
}

/* Payment method */
.payment-row {
  display: flex;
  align-items: center;
  gap: 8px;
  margin: 10px 0;
}

/* Coupon */
.coupon-row {
  display: flex;
  gap: 10px;
  margin-top: 20px;
}

.coupon-row input {
  flex: 1;
  padding: 10px;
  border-radius: 6px;
  border: 1px solid #ccc;
}

.apply-btn {
  padding: 10px 16px;
  background: #db4444;
  color: white;
  border: none;
  border-radius: 6px;
  cursor: pointer;
}

.pay-btn {
  width: 100%;
  margin-top: 20px;
  padding: 14px 0;
  background: #db4444;
  color: white;
  border: none;
  border-radius: 8px;
  font-size: 16px;
  cursor: pointer;
}
</style>