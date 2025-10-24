<template>
  <div>
    <h2>Manage Products</h2>
    <ul>
      <li v-for="p in products" :key="p.id">
        {{ p.name }} - {{ p.price }}₫
      </li>
    </ul>
  </div>
</template>

<script setup>
const products = [
  { id: 1, name: 'Chocolate Cake', price: 120000 },
  { id: 2, name: 'Croissant', price: 35000 }
]
</script>
