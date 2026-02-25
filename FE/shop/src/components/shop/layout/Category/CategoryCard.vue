<template>
  <div class="category-card">
    <div class="icon-wrapper">
      <component :is="icon" class="icon" />
    </div>
    <p class="label">{{ label }}</p>
  </div>
</template>

<script setup>
defineProps({
  icon: {
    type: Object, // nhận component icon
    required: true
  },
  label: String
})
</script>


<style scoped>
.category-card {
  width: 110px;
  height: 110px;
  border-radius: 4px;
  background: #fff;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  gap: 6px;
  cursor: pointer;
  transition: 0.25s ease;
  border: 1px solid #e5e5e5;
}

.category-card:hover {
  border-color: #db4444;
  color: #db4444;
}

.icon-wrapper {
  font-size: 32px;
  display: flex;
  align-items: center;
  justify-content: center;
}

.icon {
  width: 32px;
  height: 32px;
  transition: 0.25s ease;
}

.category-card:hover .icon {
  color: #db4444;
}

.label {
  font-size: 0.9rem;
  font-weight: 600;
  transition: 0.25s;
}
</style>
