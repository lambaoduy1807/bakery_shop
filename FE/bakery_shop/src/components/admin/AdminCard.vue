<template>
  <div class="admin-card">
    <h3>{{ title }}</h3>
    <p>{{ value }}</p>
  </div>
</template>

<script setup>
defineProps({
  title: String,
  value: String
})
</script>

<style scoped>
.admin-card {
  background: white;
  border-radius: 8px;
  padding: 1rem;
  box-shadow: 0 2px 5px rgba(0,0,0,0.1);
  text-align: center;
}
</style>
