<template>
  <div>
    <h1>Admin Page</h1>

    <nav>
      <router-link to="/admin/dashboard">Dashboard</router-link> |
      <router-link to="/admin/products">Products</router-link> |
      <router-link to="/admin/users">Users</router-link>
    </nav>

    <hr />
    <router-view />
  </div>
</template>
