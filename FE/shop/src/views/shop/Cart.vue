<template>
  <div>
    <h2>Your Cart</h2>
    <!-- <CartItem v-for="item in cart" :key="item.id" :item="item" /> -->
  </div>
</template>

<script setup>
// import CartItem from '@/components/shop/CardItem.vue'
const cart = [
  { id: 1, name: 'Chocolate Cake', price: 120000, quantity: 1, image: '/cake1.jpg' },
  { id: 2, name: 'Croissant', price: 35000, quantity: 2, image: '/cake3.jpg' }
]
</script>
