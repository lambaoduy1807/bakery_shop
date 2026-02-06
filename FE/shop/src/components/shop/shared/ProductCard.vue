<template>
  <div class="product-card" @click="$router.push(`/product/${product.id}`)">
    <div class="img-wrapper">
      <img :src="product.image" :alt="product.name" />
      <span v-if="product.save > 0" class="tag-sale">-{{ product.save }}₫</span>
    </div>

    <h3>{{ product.name }}</h3>
    <p class="price">{{ product.price.toLocaleString() }}₫</p>
    <p class="rate">⭐ {{ product.rate }}</p>
  </div>
</template>

<script setup>
defineProps({
  product: {
    type: Object,
    required: true
  }
})
</script>

<style scoped>
.product-card {
  width: 200px; /* cố định chiều rộng card */
  border: 1px solid #ddd;
  border-radius: 8px;
  padding: 0.5rem;
  text-align: center;
  cursor: pointer;
  transition: 0.2s;
}

.product-card:hover {
  transform: scale(1.05);
}

.img-wrapper {
  width: 180px;       /* cố định size ảnh */
  height: 180px;
  overflow: hidden;   /* tránh ảnh bị lệch */
  border-radius: 8px;
  position: relative;
  margin: 0 auto;
}

.img-wrapper img {
  width: 100%;
  height: 100%;
  object-fit: cover; /* cắt ảnh để lấp đầy khung nhưng không méo */
}

/* Tag giảm giá */
.tag-sale {
  position: absolute;
  top: 8px;
  left: 8px;
  background: red;
  color: #fff;
  padding: 3px 6px;
  font-size: 0.75rem;
  border-radius: 4px;
}

.price {
  font-weight: bold;
  margin-top: 0.5rem;
}

.rate {
  font-size: 0.9rem;
  color: #888;
}
</style>
