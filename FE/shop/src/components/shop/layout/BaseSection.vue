<template>
  <section class="base-section">

    <!-- Header -->
    <div class="section-header">
      <div v-if="tag" class="tag-box">{{ tag }}</div>
      <h2 class="section-title">{{ title }}</h2>
    </div>

    <!-- Content nhận từ slot -->
    <div class="content-area">
      <slot />   
    </div>

  </section>
</template>

<script setup>
defineProps({
  title: String,
  tag: String
})
</script>

<style scoped>
.base-section {
  width: 100%;
  padding: 1rem;
  display: flex;
  flex-direction: column;
  gap: 1rem;
  padding: 0 32px;
  border-bottom: 2px solid #e5e5e5;
  margin-bottom: 64px;
}

.section-header {
  display: flex;
  text-align: left;
  flex-direction: column;
  gap: 0.4rem;
}

.tag-box {
  display: inline-block;
  color: #db4444;
  padding: 0.2rem 0.6rem;
  border-radius: 8px;
  font-size: 0.85rem;
  font-weight: 600;
}

.section-title {
  font-size: 1.4rem;
  font-weight: 700;
}

.content-area {
  flex: 1;
}
</style>
