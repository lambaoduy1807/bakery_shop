<template>
  <aside :class="['sidebar', { 'sidebar-closed': !isOpen }]">
    <h2 class="title">Admin</h2>
    <ul>
      <li>
        <router-link to="/admin/dashboard">
          <i-fa-solid-chart-bar class="icon" />
          <span>Dashboard</span>
        </router-link>
      </li>
      <li>
        <router-link to="/admin/products">
          <i-fa-solid-store class="icon" />
          <span>Products</span>
        </router-link>
      </li>
      <li>
        <router-link to="/admin/users">
          <i-fa-solid-users class="icon" />
          <span>Users</span>
        </router-link>
      </li>
    </ul>
  </aside>
</template>

<script setup>
const props = defineProps({
  isOpen: Boolean
})
</script>

<style scoped>
.sidebar {
  width: 220px;
  background: #111827;
  color: #eb1616;
  height: 100vh;
  display: flex;
  flex-direction: column;
  gap: 1rem;

  position: fixed;
  top: 0;
  left: 0;

  transform: translateX(0);
  transition: transform 0.3s ease;
  z-index: 100;
}

.sidebar-closed {
  transform: translateX(-100%);
}

.title {
  color: #eb1616;
  font-size: 1.5rem;
  text-align: center;
  margin: 1rem;
}

.sidebar ul {
  list-style: none;
  padding: 0;
  margin: 0;
}

.sidebar a {
  color: #9ca3af;
  text-decoration: none;
  display: flex;
  align-items: center;
  gap: 10px;
  padding: 1rem 0.8rem;
  border-radius: 0 20px 20px 0;
  transition: all 0.2s ease;
}

.sidebar a:hover {
  background: #000;
  color: #eb1616;
}

.sidebar a.router-link-active {
  color: #eb1616;
  font-weight: 600;
  background: #000;
}

.icon {
  font-size: 18px;
  color: inherit;
  vertical-align: middle;
}

.sidebar-closed span {
  display: none;
}
</style>
