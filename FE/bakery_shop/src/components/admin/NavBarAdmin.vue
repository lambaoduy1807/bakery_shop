<template>
  <nav class="admin-navbar">
    <div class="left_nav">
      <button class="icon icon_red">
        <font-awesome-icon :icon="['fas', 'bars']" />
      </button>
      <input type="text" />
    </div>

    <div class="right_nav">
      <!-- Message -->
      <div class="dropdown" @click.stop="toggleDropdown('message')">
        <button class="dropdown-btn">
          <font-awesome-icon :icon="['fas', 'envelope']" />
          Message
        </button>
        <ul v-if="activeDropdown === 'message'" class="dropdown-menu">
          <li>New messages (2)</li>
          <li>Sent</li>
          <li>Archived</li>
        </ul>
      </div>

      <!-- Notification -->
      <div class="dropdown" @click.stop="toggleDropdown('notification')">
        <button class="dropdown-btn">
          <font-awesome-icon :icon="['fas', 'bell']" />
          Notification
        </button>
        <ul v-if="activeDropdown === 'notification'" class="dropdown-menu">
          <li>System alerts</li>
          <li>Updates</li>
          <li>Warnings</li>
        </ul>
      </div>

      <!-- Account -->
      <div class="dropdown" @click.stop="toggleDropdown('account')">
        <button class="dropdown-btn">
          <font-awesome-icon :icon="['fas', 'user']" />
          Account
        </button>
        <ul v-if="activeDropdown === 'account'" class="dropdown-menu">
          <li>Profile</li>
          <li>Settings</li>
          <li @click="logout">Logout</li>
        </ul>
      </div>
    </div>
  </nav>
</template>

<script setup>
import { ref, onMounted, onBeforeUnmount } from 'vue'

const activeDropdown = ref(null)

function toggleDropdown(name) {
  activeDropdown.value = activeDropdown.value === name ? null : name
}

function handleClickOutside() {
  activeDropdown.value = null
}

function logout() {
  alert('You have logged out!')
}

onMounted(() => {
  document.addEventListener('click', handleClickOutside)
})
onBeforeUnmount(() => {
  document.removeEventListener('click', handleClickOutside)
})
</script>

<style scoped>
.admin-navbar {
  display: flex;
  justify-content: space-between;
  align-items: center;
  background: #111827;
  color: white;
  padding: 1rem;
}

.admin-navbar * {
  font-size: 18px;
}

.left_nav,
.right_nav {
  display: flex;
  align-items: center;
  gap: 1rem;
}

.icon {
  padding: 5%;
  border-radius: 50%;
}

button {
  background: #000;
  color: #617293;
  border: none;
  padding: 0.5rem 1rem;
  border-radius: 5px;
  cursor: pointer;
}

.icon_red {
  color: #dc2626;
}

.icon_red:hover {
  opacity: 0.7;
}

button:hover {
  color: #dc2626;
}

/* Dropdown container */
.dropdown {
  position: relative;
}

/* Dropdown button */
.dropdown-btn {
  background: transparent;
  color: white;
  border: none;
  font-weight: bold;
  cursor: pointer;
  display: flex;
  align-items: center;
  gap: 0.5rem;
}

.dropdown-btn:hover {
  color: #f87171;
}

/* Dropdown menu */
.dropdown-menu {
  position: absolute;
  top: 120%;
  right: 0;
  background: #1f2937;
  list-style: none;
  padding: 0.5rem 0;
  border-radius: 8px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.4);
  width: 180px;
  z-index: 10;
}

.dropdown-menu li {
  padding: 0.5rem 1rem;
  cursor: pointer;
}

.dropdown-menu li:hover {
  background: #374151;
  color: #f87171;
}
</style>
