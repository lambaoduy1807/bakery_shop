<template>
  <div>
    <h2>Dashboard</h2>
    <div class="cards">
      <AdminCard title="Total Sales" value="₫15,200,000" />
      <AdminCard title="Users" value="245" />
      <AdminCard title="Products" value="32" />
    </div>
  </div>
</template>

<script setup>
import AdminCard from '@/components/admin/AdminCard.vue'
</script>

<style scoped>
.cards {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(220px, 1fr));
  gap: 1rem;
  margin-top: 1rem;
}
</style>
