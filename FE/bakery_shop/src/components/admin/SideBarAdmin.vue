<template>
  <aside class="sidebar">
    <h2>Admin Panel</h2>
    <ul>
      <li><router-link to="/admin">Dashboard</router-link></li>
      <li><router-link to="/admin/products">Products</router-link></li>
      <li><router-link to="/admin/users">Users</router-link></li>
    </ul>
  </aside>
</template>

<style scoped>
.sidebar {
  width: 220px;
  background: #1f2937;
  color: white;
  padding: 1rem;
  height: 100vh;
}
.sidebar ul {
  list-style: none;
  padding: 0;
}
.sidebar li {
  margin: 1rem 0;
}
.sidebar a {
  color: white;
  text-decoration: none;
}
.sidebar a.router-link-active {
  font-weight: bold;
  text-decoration: underline;
}
</style>
