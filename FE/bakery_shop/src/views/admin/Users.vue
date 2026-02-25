<template>
  <div>
    <h2>Manage Users</h2>
    <table>
      <tr><th>ID</th><th>Name</th><th>Email</th></tr>
      <tr v-for="u in users" :key="u.id">
        <td>{{ u.id }}</td>
        <td>{{ u.name }}</td>
        <td>{{ u.email }}</td>
      </tr>
    </table>
  </div>
</template>

<script setup>
const users = [
  { id: 1, name: 'Duy', email: 'duy@example.com' },
  { id: 2, name: 'Trang', email: 'trang@example.com' }
]
</script>

<style scoped>
table {
  border-collapse: collapse;
  width: 100%;
  margin-top: 1rem;
}
th, td {
  border: 1px solid #ccc;
  padding: 0.5rem;
}
</style>
