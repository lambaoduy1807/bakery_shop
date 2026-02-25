<template>
  <div class="category-showcase" @click="$emit('click')">
    <div class="bg-image" :style="{ backgroundImage: `url(${image})` }"></div>

    <div class="content">
      <h3 class="title">{{ title }}</h3>
      <p class="info">{{ info }}</p>

      <button class="shop-btn">
        Shop Now
      </button>
    </div>
  </div>
</template>

<script setup>
defineProps({
  title: { type: String, required: true },
  info: { type: String, default: '' },
  image: { type: String, required: true }
})
</script>

<style scoped>
.category-showcase {
  width: 280px;
  height: 180px;
  position: relative;
  overflow: hidden;
  border-radius: 14px;
  cursor: pointer;
  transition: 0.3s ease;
}

.category-showcase:hover {
  transform: translateY(-4px);
  box-shadow: 0 6px 18px rgba(0,0,0,0.15);
}

.bg-image {
  width: 100%;
  height: 100%;
  background-size: cover;
  background-position: center;
  filter: brightness(0.7);
  transition: 0.3s ease;
}

.category-showcase:hover .bg-image {
  filter: brightness(0.55);
}

.content {
  position: absolute;
  bottom: 14px;
  left: 14px;
  right: 14px;
  color: #fff;
  z-index: 2;
}

.title {
  font-size: 20px;
  font-weight: 700;
  margin: 0 0 4px 0;
}

.info {
  font-size: 14px;
  opacity: 0.85;
  margin-bottom: 10px;
}

.shop-btn {
  background: #fff;
  color: #333;
  padding: 6px 14px;
  border-radius: 20px;
  border: none;
  font-size: 14px;
  font-weight: 600;
  cursor: pointer;
  transition: 0.25s;
}

.shop-btn:hover {
  background: #f1f1f1;
}
</style>
