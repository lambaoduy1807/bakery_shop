<template>
  <section class="product-section">
    <!-- Tiêu đề -->
    <h2 class="section-title">{{ title }}</h2>

    <!-- Danh sách sản phẩm -->
    <div class="horizontal-list">
      <ProductCard
        v-for="item in products"
        :key="item.id"
        :product="item"
      />
    </div>
  </section>
</template>

<script setup>
import ProductCard from '@/components/shop/shared/ProductCard.vue'

defineProps({
  title: {
    type: String,
    default: ''
  },
  products: {
    type: Array,
    default: () => []
  }
})
</script>

<style scoped>
.product-section {
  display: flex;
  flex-direction: column;
  gap: 1rem;
  padding: 0.5rem 0;
}

.section-title {
  font-size: 1.4rem;
  font-weight: 600;
}

.horizontal-list {
  display: flex;
  gap: 1rem;
  overflow-x: auto;
  padding-bottom: 0.5rem;

  /* Ẩn thanh scroll trên Firefox */
  scrollbar-width: none;
}

/* Ẩn thanh scroll trên Chrome, Safari */
.horizontal-list::-webkit-scrollbar {
  display: none;
}
</style>
