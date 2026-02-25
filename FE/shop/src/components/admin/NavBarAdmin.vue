<template>
  <nav class="admin-navbar">
    <!-- Left section -->
    <div class="left_nav">
      <button class="btn-menu" @click="$emit('toggle-sidebar')">
        <i-fa-solid-bars />
      </button>
      <input type="text" placeholder="Search..." />
    </div>

    <!-- Right section -->
    <div class="right_nav">
      <div v-for="item in dropdownItems" :key="item.name" class="dropdown" @click.stop="toggleDropdown(item.name)">
        <button class="dropdown-btn">
          <component :is="getIcon(item.icon)" class="icon" />
          {{ item.label }}
          <i-fa-solid-caret-down class="icon" />
        </button>
        <!-- Dropdown with transition -->
        <Transition name="fade">
          <ul v-if="activeDropdown === item.name" class="dropdown-menu">
            <li v-for="option in item.options" :key="option" @click="option === 'Logout' ? logout() : null">
              {{ option }}
            </li>
          </ul>
        </Transition>
      </div>
    </div>
  </nav>
</template>

<script setup>
import IconEnvelope from '~icons/fa-solid/envelope'
import IconBell from '~icons/fa-solid/bell'
import IconUser from '~icons/fa-solid/user'

const icons = {
  envelope: IconEnvelope,
  bell: IconBell,
  user: IconUser
}

const getIcon = (name) => icons[name]
import { ref, onMounted, onBeforeUnmount } from 'vue'

const activeDropdown = ref(null)

const dropdownItems = [
  { name: 'message', label: 'Message', icon: 'envelope', options: ['New messages (2)', 'Sent', 'Archived'] },
  { name: 'notification', label: 'Notification', icon: 'bell', options: ['System alerts', 'Updates', 'Warnings'] },
  { name: 'account', label: 'Account', icon: 'user', options: ['Profile', 'Settings', 'Logout'] }
]

function toggleDropdown(name) {
  activeDropdown.value = activeDropdown.value === name ? null : name
}

function handleClickOutside() {
  activeDropdown.value = null
}

function logout() {
  alert('You have logged out!')
}
// đóng mở side bar
defineEmits(['toggle-sidebar'])

onMounted(() => document.addEventListener('click', handleClickOutside))
onBeforeUnmount(() => document.removeEventListener('click', handleClickOutside))
</script>


<style scoped>
/* ===== Layout ===== */
.admin-navbar {
  display: flex;
  justify-content: space-between;
  align-items: center;
  background: #111827;
  padding: 1rem;
}

.left_nav,
.right_nav {
  display: flex;
  align-items: center;
  gap: 1rem;
}

/* ===== Common styles ===== */
.admin-navbar * {
  font-size: 18px;
  color: #6c7285;
}

button {
  background: transparent;
  border: none;
  cursor: pointer;
  transition: color 0.3s, transform 0.2s;
}

button:hover {
  color: #c81313;
  transform: scale(1.05);
}

input {
  background: #1f2937;
  border: 1px solid #374151;
  border-radius: 6px;
  padding: 0.4rem 0.6rem;
  color: #d1d5db;
  outline: none;
}

input::placeholder {
  color: #6b7280;
}

.btn-menu {
  background: transparent;
  border: none;
  cursor: pointer;
  color: #c81313 !important;
}

.btn-menu :deep(svg),
.btn-menu :deep(path) {
  color: #c81313 !important;
  fill: #c81313 !important;
}

/* ===== Dropdown ===== */
.dropdown {
  position: relative;
}

.dropdown-btn {
  display: flex;
  align-items: center;
  gap: 0.5rem;
  color: #617293;
  transition: color 0.3s;
}

.dropdown-btn:hover {
  color: #c81313;
}

/* Khi hover, icon cũng đổi màu theo */
.dropdown-btn:hover :deep(svg),
.dropdown-btn:hover :deep(path) {
  color: #c81313 !important;
  fill: #c81313 !important;
  transition: color 0.3s, fill 0.1s;
}

/* icon kế thừa màu cha */
.dropdown-btn :deep(.icon) {
  color: inherit;
  transition: color 0.3s;
}

/* ===== Dropdown menu ===== */
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
  transition: background 0.2s;
}

.dropdown-menu li:hover {
  background: #374151;
}

/* ===== Animation ===== */
.fade-enter-active,
.fade-leave-active {
  transition: all 0.25s ease;
}

.fade-enter-from,
.fade-leave-to {
  opacity: 0;
  transform: translateY(-6px);
}
</style>
