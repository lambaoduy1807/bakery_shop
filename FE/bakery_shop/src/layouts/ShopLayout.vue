<template>
  <div>
    <HeaderShop />
    <router-view />
    <FooterShop />
  </div>
</template>

<script setup>
import HeaderShop from '@/components/shop/HeaderShop.vue'
import FooterShop from '@/components/shop/FooterShop.vue'
</script>
