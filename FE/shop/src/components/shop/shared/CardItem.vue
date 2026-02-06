<template>
  <div class="cart-item">
    <img :src="item.image" :alt="item.name" />
    <div class="info">
      <h3>{{ item.name }}</h3>
      <p>{{ item.price }}₫ x {{ item.quantity }}</p>
    </div>
  </div>
</template>

<script setup>
defineProps({
  item: {
    type: Object,
    required: true
  }
})
</script>

<style scoped>
.cart-item {
  display: flex;
  align-items: center;
  margin-bottom: 1rem;
}
.cart-item img {
  width: 80px;
  height: 80px;
  border-radius: 8px;
  margin-right: 1rem;
}
</style>
