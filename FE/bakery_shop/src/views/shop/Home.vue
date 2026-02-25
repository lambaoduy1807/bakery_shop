<template>
  <div class="home">
    <h2>Welcome to Bakery Shop</h2>
    <div class="product-list">
      <ProductCard v-for="p in products" :key="p.id" :product="p" />
    </div>
  </div>
</template>

<script setup>
import ProductCard from '@/components/shop/ProductCard.vue'

const products = [
  { id: 1, name: 'Chocolate Cake', price: 120000, image: '/cake1.jpg' },
  { id: 2, name: 'Strawberry Tart', price: 95000, image: '/cake2.jpg' },
  { id: 3, name: 'Croissant', price: 35000, image: '/cake3.jpg' }
]
</script>

<style scoped>
.product-list {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(220px, 1fr));
  gap: 1rem;
  margin-top: 1rem;
}
</style>
