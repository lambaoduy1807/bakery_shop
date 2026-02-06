<template>
  <div class="home">
   
    <HorizontalProductList 
    title="Best Seller"
    :products="mockProducts"
  />
  </div>
</template>

<script setup>

import HorizontalProductList from '@/components/shop/layout/HorizontalProductList.vue'
import { mockProducts } from '@/services/ProductServices.js'
</script>

<style scoped>
.product-list {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(220px, 1fr));
  gap: 1rem;
  margin-top: 1rem;
}
</style>
