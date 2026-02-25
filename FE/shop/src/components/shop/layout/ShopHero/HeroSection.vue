<template>
  <section class="hero-section">
    
    <!-- Category bên trái -->
    <CategoryVertical :categories="categoryList" />

    <!-- Banner bên phải -->
    <AutoBanner :images="bannerImages" />
  </section>
</template>

<script setup>
import CategoryVertical from '../Category/CategoryVertical.vue'
import AutoBanner from './AutoBanner.vue'

const categoryList = [
  "Men Fashion",
  "Women Fashion",
  "Shoes",
  "Laptop",
  "Smartphone",
  "Accessory",
  "Gaming Gear",
  "Tablet",
  "Makeup",
  "Home Appliance"
]

const bannerImages = [
  "https://picsum.photos/id/1015/900/350",
  "https://picsum.photos/id/1025/900/350",
  "https://picsum.photos/id/1035/900/350"
]
</script>

<style scoped>
.hero-section {
  display: flex;
  padding: 16px;
}
</style>
