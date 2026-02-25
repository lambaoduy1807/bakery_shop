<template>
  <div class="admin-layout">
    <SidebarAdmin />
    <div class="content">
      <NavbarAdmin />
      <router-view />
    </div>
  </div>
</template>

<script setup>
import SidebarAdmin from '@/components/admin/SidebarAdmin.vue'
import NavbarAdmin from '@/components/admin/NavbarAdmin.vue'
</script>

<style scoped>
.admin-layout {
  display: flex;
}
.content {
  flex: 1;
  background: #f9fafb;
  min-height: 100vh;
}
</style>
