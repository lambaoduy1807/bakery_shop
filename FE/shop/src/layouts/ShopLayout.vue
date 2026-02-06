<template>
  <div>
    <HeaderShop />
    <router-view />
    <FooterShop />
  </div>
</template>

<script setup>
import HeaderShop from '@/components/shop/layout/HeaderShop.vue'
import FooterShop from '@/components/shop/layout/FooterShop.vue'
</script>
