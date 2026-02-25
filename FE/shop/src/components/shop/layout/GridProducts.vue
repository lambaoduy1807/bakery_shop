<template>
  <div
    class="grid-wrapper"
    :style="{
      gridTemplateColumns: `repeat(${cols}, 1fr)`,
      gridTemplateRows: rows ? `repeat(${rows}, auto)` : 'auto'
    }"
  >
    <ProductCard
      v-for="(item, index) in limitedProducts"
      :key="index"
      :product="item"
    />
  </div>
</template>

<script setup>
import ProductCard from '../shared/ProductCard.vue'
import { computed } from 'vue'
const props = defineProps({
  products: {
    type: Array,
    default: () => []
  },
  rows: {
    type: Number,
    default: 2
  },
  cols: {
    type: Number,
    default: 4
  }
})

// Tính số product hiển thị đúng rows * cols
const limitedProducts = computed(() =>
  props.products.slice(0, props.rows * props.cols)
)
</script>

<style scoped>
.grid-wrapper {
  display: grid;
  gap: 16px;
  width: 100%;
}
</style>
