<template>
  <div class="signup-container">
    <!-- Banner trái -->
    <div class="signup-banner">
      <h1>{{ isLogin ? "Welcome Back!" : "Welcome!" }}</h1>
      <p>
        {{ isLogin ? "Đăng nhập để tiếp tục." : "Tạo tài khoản để bắt đầu trải nghiệm." }}
      </p>
    </div>

    <!-- Form phải -->
    <div class="signup-form">
      <h2>{{ isLogin ? "Đăng nhập" : "Tạo tài khoản" }}</h2>

      <!-- Chỉ hiển thị 1 form -->
      <LoginForm v-if="isLogin" />
      <RegisterForm v-else />

      <!-- Google button -->
      <button class="btn-google">
        <img src="https://www.svgrepo.com/show/475656/google-color.svg" />
        Tiếp tục với Google
      </button>

      <!-- Toggle text -->
      <p class="login-text">
        <template v-if="isLogin">
          Chưa có tài khoản?
          <a href="#" @click.prevent="isLogin = false">Đăng ký</a>
        </template>

        <template v-else>
          Already have an account?
          <a href="#" @click.prevent="isLogin = true">Login</a>
        </template>
      </p>
    </div>
  </div>
</template>

<script setup>
import { ref } from "vue";
import LoginForm from "../../components/shop/signup/LoginForm.vue";
import RegisterForm from "../../components/shop/signup/RegisterForm.vue";

const isLogin = ref(false);
</script>

<style>
/* Layout tổng */
.signup-container {
  display: flex;
  height: 100vh;
  font-family: Arial, sans-serif;
}

/* Banner trái */
.signup-banner {
  flex: 1;
  background: #2563eb;
  color: white;
  display: flex;
  flex-direction: column;
  justify-content: center;
  padding: 60px;
}

.signup-banner h1 {
  font-size: 42px;
  font-weight: bold;
  margin-bottom: 10px;
}

.signup-banner p {
  font-size: 18px;
  opacity: 0.9;
}

/* Form phải */
.signup-form {
  flex: 1;
  padding: 60px;
  display: flex;
  flex-direction: column;
  justify-content: center;
}

.signup-form h2 {
  font-size: 32px;
  font-weight: bold;
  margin-bottom: 30px;
}

/* Google button */
.btn-google {
  width: 100%;
  padding: 12px;
  border: 1px solid #ccc;
  background: white;
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 10px;
  border-radius: 8px;
  cursor: pointer;
  margin: 20px 0;
  transition: 0.25s;
  font-weight: 600;
}

.btn-google:hover {
  background: #f5f5f5;
}

.btn-google img {
  width: 20px;
  height: 20px;
}

/* Login text */
.login-text {
  text-align: center;
  margin-top: 10px;
}

.login-text a {
  color: #2563eb;
  font-weight: bold;
  text-decoration: none;
}

.login-text a:hover {
  text-decoration: underline;
}
/* Input group */
.input-group {
  margin-bottom: 20px;
}

.input-group input {
  width: 100%;
  padding: 12px;
  border: 1px solid #ccc;
  border-radius: 8px;
  outline: none;
  transition: 0.25s;
}

.input-group input:focus {
  border-color: #2563eb;
  box-shadow: 0 0 0 3px rgba(37, 99, 235, 0.2);
}

</style>