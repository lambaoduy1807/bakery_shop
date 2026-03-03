<template>
  <div class="cart-container">

    <!-- Title Row -->
    <div class="cart-header">
      <span>Product</span>
      <span>Price</span>
      <span>Quantity</span>
      <span>Subtotal</span>
    </div>

    <!-- Product List -->
    <div v-for="item in cartItems" :key="item.id" class="cart-item">
      <!-- Product -->
      <div class="product-info">
        <img :src="item.image" alt="" />
        <p>{{ item.name }}</p>
      </div>

      <!-- Price -->
      <div class="price">{{ formatPrice(item.price) }}</div>

      <!-- Quantity -->
      <div class="quantity">
        <button @click="decrease(item)">-</button>
        <input type="number" v-model.number="item.qty" min="1" />
        <button @click="increase(item)">+</button>
      </div>

      <!-- Subtotal -->
      <div class="subtotal">
        {{ formatPrice(item.price * item.qty) }}
      </div>
    </div>

    <!-- Bottom Action Buttons -->
    <div class="cart-actions">
      <button class="white-btn">Return to Shop</button>
      <button class="white-btn">Update Cart</button>
    </div>

    <!-- Coupon + Bill -->
    <div class="cart-footer">

      <!-- Coupon Section -->
      <div class="coupon-box">
        <input v-model="couponCode" type="text" placeholder="Enter coupon code" />
        <button class="red-btn">Apply Coupon</button>
      </div>

      <!-- Bill -->
      <div class="cart-summary">
        <h3>Cart Total</h3>

        <div class="summary-row">
          <span>Subtotal:</span>
          <span>{{ formatPrice(totalPrice) }}</span>
        </div>

        <div class="summary-row">
          <span>Discount:</span>
          <span>{{ formatPrice(discount) }}</span>
        </div>

        <div class="summary-row total">
          <span>Total:</span>
          <span>{{ formatPrice(totalPrice - discount) }}</span>
        </div>
        <a href="/checkout"class="red-btn">   Process to Checkout</a>
      </div>

    </div>

  </div>
</template>

<script setup>
import { ref, onMounted, computed } from "vue";
import { getProducts } from "@/services/ProductServices";

const cartItems = ref([]);
const couponCode = ref("");
const discount = ref(0);

onMounted(async () => {
  const products = await getProducts();
  cartItems.value = products.map(p => ({
    ...p,
    qty: 1,
  }));
});

function increase(item) {
  item.qty++;
}

function decrease(item) {
  if (item.qty > 1) item.qty--;
}

function formatPrice(value) {
  return value.toLocaleString("vi-VN") + "₫";
}

const totalPrice = computed(() =>
  cartItems.value.reduce((sum, item) => sum + item.price * item.qty, 0)
);
</script>

<style scoped>
.cart-container {
  width: 90%;
  margin: 20px auto;
  font-family: sans-serif;
}

/* HEADER */
.cart-header {
  display: grid;
  grid-template-columns: 40% 20% 20% 20%;
  font-weight: bold;
  padding: 15px 0;
  border-bottom: 2px solid #ddd;
}

/* PRODUCT ITEM */
.cart-item {
  display: grid;
  grid-template-columns: 40% 20% 20% 20%;
  align-items: center;
  padding: 18px 0;
  border-bottom: 1px solid #eee;
}

.product-info {
  display: flex;
  align-items: center;
  gap: 12px;
}

.product-info img {
  width: 70px;
  height: 70px;
  object-fit: cover;
  border-radius: 8px;
}

.quantity {
  display: flex;
  align-items: center;
  gap: 6px;
}

.quantity button {
  padding: 5px 8px;
  cursor: pointer;
}

.quantity input {
  width: 50px;
  text-align: center;
}

/* ACTION BUTTONS */
.cart-actions {
  display: flex;
  justify-content: space-between;
  margin-top: 25px;
}


.white-btn {
  border-radius: 3px;
  padding: 10px 20px;
}

.red-btn {
  border-radius: 3px;
  padding: 10px 20px;
}


/* COUPON + BILL */
.cart-footer {
  margin-top: 35px;
  display: flex;
  justify-content: space-between;
}

.coupon-box {
  width: 45%;
  display: flex;
  gap: 10px;
  height: fit-content;
}

.coupon-box input {
  flex: 1;
  padding: 10px;
}



/* SUMMARY */
.cart-summary {
  width: 45%;
  border: 1px solid #ddd;
  padding: 20px;
  border-radius: 10px;
}

.summary-row {
  display: flex;
  justify-content: space-between;
  margin: 12px 0;
}

.summary-row.total {
  font-weight: bold;
  font-size: 18px;
}
</style>