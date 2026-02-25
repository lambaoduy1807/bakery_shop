<template>
  <div class="banner">
    <img :src="images[current]" alt="banner" />

    <!-- Indicators -->
    <div class="dots">
      <span
        v-for="(img, index) in images"
        :key="index"
        class="dot"
        :class="{ active: index === current }"
        @click="current = index"
      ></span>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, onUnmounted } from 'vue'

const props = defineProps({
  images: {
    type: Array,
    default: () => []
  }
})

const current = ref(0)
let timer = null

onMounted(() => {
  timer = setInterval(() => {
    current.value = (current.value + 1) % props.images.length
  }, 3000)
})

onUnmounted(() => {
  clearInterval(timer)
})
</script>

<style scoped>
.banner {
  position: relative;
  width: 100%;
  height: auto;
  border-radius: 12px;
  overflow: hidden;
}

.banner img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.dots {
  position: absolute;
  bottom: 12px;
  left: 50%;
  transform: translateX(-50%);
  display: flex;
  gap: 6px;
}

.dot {
  width: 10px;
  height: 10px;
  background: #ddd;
  border-radius: 50%;
  cursor: pointer;
}

.dot.active {
  background: #db4444;
}
</style>
