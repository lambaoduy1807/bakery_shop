<template>
  <div class="category-horizontal">
    <CategoryCard
      v-for="(item, index) in categories"
      :key="index"
      :label="item.label"
      :icon="item.icon"
    />
  </div>
</template>

<script setup>
import CategoryCard from './CategoryCard.vue'

defineProps({
  categories: {
    type: Array,
    default: () => []
  }
})
</script>

<style scoped>
.category-horizontal {
  display: flex;
  flex-direction: row;      
  align-items: center;
  gap: 12px;
  background: #fff;
  padding: 12px;
  overflow-x: auto;          
  scrollbar-width: thin;

}
</style>
