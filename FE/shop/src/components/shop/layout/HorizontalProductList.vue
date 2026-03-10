<template>
  <section class="product-section">

    <!-- Danh sách sản phẩm -->
    <div class="horizontal-list">
      <ProductCard v-for="item in products" :key="item.id" :product="item" />
    </div>
    <button class="red-btn">View all product</button>

  </section>
</template>

<script setup>
import { isVNode } from 'vue'
import ProductCard from '@/components/shop/shared/ProductCard.vue'

const props = defineProps({
  title: {
    type: [String, Object, Function],
    default: ''
  },
  tag: {
    type: [String, Object, Function],
    default: ''
  },
  products: {
    type: Array,
    default: () => []
  }
})

// Hàm kiểm tra prop có phải là component không
function isComponent(value) {
  return typeof value === 'object' || typeof value === 'function'
}
</script>

<style scoped>
.product-section {
  display: flex;
  flex-direction: column;
  gap: 1rem;
  padding: 0.5rem 0;
}

.tag-box {
  display: inline-block;
  color: #db4444;
  padding: 0.2rem 0.6rem;
  border-radius: 8px;
  font-size: 0.85rem;
  width: fit-content;
  font-weight: 600;
}

.section-title {
  font-size: 1.4rem;
  font-weight: 600;
  text-align: left;
}

.horizontal-list {
  display: flex;
  gap: 1rem;
  overflow-x: auto;
  padding-bottom: 0.5rem;
  scrollbar-width: none;
}

.horizontal-list::-webkit-scrollbar {
  display: none;
}

.view-all-btn {
  padding: 0.6rem 1.2rem;
  background-color: #db4444;
  color: white;
  border: none;
  border-radius: 8px;
  font-size: 1rem;
  font-weight: 600;
  cursor: pointer;
  width: fit-content;
  transition: 0.2s ease-in-out;
  margin: 0 auto;
}

.view-all-btn:hover {
  background-color: #b63737;
}
</style>
