<template>
  <div class="category-vertical">
    <div
      v-for="(item, index) in categories"
      :key="index"
      class="category-item"
    >
      {{ item }}
    </div>
  </div>
</template>

<script setup>
defineProps({
  categories: {
    type: Array,
    default: () => []
  }
})
</script>

<style scoped>
.category-vertical {
  width: 240px;
  background: #fff;
  padding: 12px;
  display: flex;
  flex-direction: column;
  gap: 10px;
  border-right: 2px solid #e5e5e5;
  margin-right: 32px;
}

.category-item {
  padding: 10px 12px;
  border-radius: 8px;
  font-weight: 600;
  cursor: pointer;
  transition: 0.25s ease;
  text-align: left;
}

.category-item:hover {
  border-color: #db4444;
  color: #db4444;
}
</style>
