<template>
  <div class="admin-layout">
    <SidebarAdmin :is-open="isSidebarOpen" />
    <div class="content " :class="{ 'content-shift': isSidebarOpen }">
      <NavbarAdmin @toggle-sidebar="toggleSidebar" />
      <router-view />
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import SidebarAdmin from '@/components/admin/SidebarAdmin.vue'
import NavbarAdmin from '@/components/admin/NavbarAdmin.vue'

const isSidebarOpen = ref(false)

function toggleSidebar() {
  // alert('click on side bar')
  isSidebarOpen.value = !isSidebarOpen.value
}
</script>

<style scoped>
.admin-layout {
  display: flex;
   color:var(--color-text-main-admin);
}

/* Content sẽ dịch sang phải khi sidebar mở */
.content {
  flex: 1;
  background: var(--color-bg-main-admin);
  min-height: 100vh;
  transition: margin-left 0.3s ease;
  margin-left: 0;
}
.content-shift {
  margin-left: 220px; /* bằng width của sidebar */
}
</style>
