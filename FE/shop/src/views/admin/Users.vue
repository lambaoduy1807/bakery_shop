<template>
  <div>
    <h2>Manage Users</h2>
    <!-- tái sử dụng UserTable -->
    <UserTable :users="users" />
  </div>
</template>

<script setup>
import UserTable from '@/components/admin/table/UserTable.vue';

const users = [
  { id: 1, name: 'Duy', email: 'duy@example.com' },
  { id: 2, name: 'Trang', email: 'trang@example.com' }
]
</script>
