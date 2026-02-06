<template>
  <div class="admin-card">
    <div class="icon">
      <slot name="icon" />
    </div>

    <div class="content">
      <h3>{{ title }}</h3>
      <p>{{ value }}</p>
    </div>
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
  color:var(--color-text-main-admin);
  display: flex;
  align-items: center;
  gap: 1rem;
  background: var(--color-bg-card-admin, #fff);
  border-radius: 8px;
  padding: 1rem;
  box-shadow: 0 2px 5px rgba(0,0,0,0.1);
}

.icon {
  font-size: 2rem;
  color: var(--color-primary-admin, #42b883);
}

.content h3 {
  margin: 0;
  font-size: 0.9rem;
  color: var(--color-text-muted-admin, #666);
}

.content p {
  margin: 0;
  font-size: 1.4rem;
  font-weight: bold;
}
</style>
