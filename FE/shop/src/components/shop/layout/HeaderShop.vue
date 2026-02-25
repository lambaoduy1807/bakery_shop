<template>
  <header class="shop-header">
    <h1>Logo Shop</h1>

    <nav class="nav-links">
      <router-link to="/">Home</router-link>
      <router-link to="/cart">Contact</router-link>
      <router-link to="/cart">About</router-link>
      <router-link to="/signup">Sign Up</router-link>
    </nav>

    <!-- SEARCH BOX -->
    <div class="search-box">
      <input type="text" placeholder="What are you looking for?" />
      <button class="search-btn">
        <i-mdi-magnify />
      </button>
    </div>

    <!-- ICONS (heart + cart) -->
    <nav class="icon-group">
      <router-link to="/favorites">
        <i-fa-heart-o />
      </router-link>

      <router-link to="/cart">
        <i-mdi-cart-outline />
      </router-link>
    </nav>
  </header>
</template>


<style scoped>
.shop-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  background: #fff;
  padding: 1rem 2rem;
  color: #000;
  gap: 2rem;
  
  border-bottom: 1px solid #e0e0e0;
}


.nav-links {
  display: flex;
  gap: 1.2rem;
  /* Các nút navigation cách đều */
}

.nav-links a {
  color: #000;
  text-decoration: none;
}

.nav-links a.router-link-exact-active {
  text-decoration: underline;
}

/* Search box */
.search-box {
  display: flex;
  align-items: center;
  border: 1px solid #ccc;
  padding: 0.4rem 0.6rem;
  border-radius: 6px;
  gap: 0.5rem;
}

.search-box input {
  border: none;
  outline: none;
}

.search-btn {
  background: transparent;
  border: none;
  cursor: pointer;
}

/* Heart + Cart đều nhau */
.icon-group {
  display: flex;
  align-items: center;
  gap: 1.5rem;
  /* khoảng cách đều giữa input – heart – cart */
}

.icon-group a {
  color: #000;
}
</style>
