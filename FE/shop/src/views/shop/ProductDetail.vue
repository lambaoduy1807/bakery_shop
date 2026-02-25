<template>
  <div class="product-detail">
    <img :src="product.image" alt="product" />
    <div>
      <h2>{{ product.name }}</h2>
      <p>{{ product.price }}₫</p>
      <button @click="addToCart">Add to cart</button>
    </div>
  </div>
</template>

<script setup>
import { useRoute } from 'vue-router'

const route = useRoute()
const id = route.params.id

const product = {
  id,
  name: 'Sample Product',
  price: 100000,
  image: '/cake1.jpg'
}

function addToCart() {
  alert(`${product.name} added to cart!`)
}
</script>

<style scoped>
.product-detail {
  display: flex;
  gap: 2rem;
  padding: 2rem;
}
.product-detail img {
  width: 300px;
  border-radius: 10px;
}
</style>
