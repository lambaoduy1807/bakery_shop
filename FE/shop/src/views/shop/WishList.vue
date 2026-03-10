<template>
  <div class="wishlist-container">

    <!-- Header Wishlist -->
    <div class="wishlist-header">
      <h1 class="wishlist-title">Wishlist ({{ wishlist.length }})</h1>
      <button class="btn-move-all" @click="moveAllToBag">Move all to bag</button>
    </div>

    <!-- Wishlist -->
    <HorizontalProductList :products="wishlist" />

    <!-- Just For You -->
    <h2 class="just-title">Just For You</h2>
    <HorizontalProductList :products="justForYou" />

  </div>
</template>

<script setup>
import HorizontalProductList from '@/components/shop/layout/HorizontalProductList.vue'
import { ref, onMounted } from "vue";
import { mockProducts } from '@/services/ProductServices.js'

// ===== STATE =====
const wishlist = ref([]);
const justForYou = ref([]);

// ===== LOAD FAKE DATA =====
onMounted(() => {
    const products = mockProducts;

    wishlist.value = products.slice(0, 2);
    justForYou.value = products.slice(2);
});

// ===== FUNCTIONS =====

// Xóa 1 item
const removeItem = (id) => {
    wishlist.value = wishlist.value.filter((p) => p.id !== id);
};

// Thêm item
const addToWishlist = (product) => {
    if (!wishlist.value.find((p) => p.id === product.id)) {
        wishlist.value.push(product);
    }
};

// Move all to bag
const moveAllToBag = () => {
    wishlist.value = [];
};

// Format tiền
const formatPrice = (p) =>
    p.toLocaleString("vi-VN", { style: "currency", currency: "VND" });
</script>

<style scoped>
.wishlist-container {
  padding: 20px 40px;
  font-family: Arial, sans-serif;
}

/* ===== Header Wishlist ===== */
.wishlist-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 15px;
}

.wishlist-title {
  font-size: 28px;
  font-weight: bold;
}

/* Move all to bag */
.btn-move-all {
  background: #2563eb;
  color: white;
  padding: 10px 18px;
  border: none;
  border-radius: 8px;
  cursor: pointer;
  font-weight: bold;
  transition: 0.25s;
}

.btn-move-all:hover {
  background: #1e40af;
}

/* Just For You Title */
.just-title {
  margin-top: 30px;
  font-size: 24px;
  font-weight: bold;
}
</style>