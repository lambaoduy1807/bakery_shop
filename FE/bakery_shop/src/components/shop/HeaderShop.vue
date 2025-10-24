<template>
  <header class="shop-header">
    <h1>Bakery Shop</h1>
    <nav>
      <router-link to="/">Home</router-link>
      <router-link to="/cart">Cart</router-link>
    </nav>
  </header>
</template>

<style scoped>
.shop-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  background: #f4b183;
  padding: 1rem 2rem;
  color: white;
}
nav a {
  margin-left: 1rem;
  color: white;
  text-decoration: none;
  font-weight: bold;
}
nav a.router-link-active {
  text-decoration: underline;
}
</style>
