<template>
  <div class="product-card" @click="$router.push(`/product/${product.id}`)">
    <img :src="product.image" :alt="product.name" />
    <h3>{{ product.name }}</h3>
    <p>{{ product.price }}₫</p>
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
  border: 1px solid #ddd;
  border-radius: 8px;
  padding: 1rem;
  text-align: center;
  cursor: pointer;
  transition: 0.2s;
}
.product-card:hover {
  transform: scale(1.05);
}
.product-card img {
  width: 100%;
  border-radius: 8px;
}
</style>
