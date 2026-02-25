import { createRouter, createWebHistory } from 'vue-router'
import shopRoutes from './ShopRoute'
import adminRoutes from './AdminRoute'

const routes = [
  ...shopRoutes,
  ...adminRoutes,
  // Route 404
  { path: '/:pathMatch(.*)*', redirect: '/' },
]

const router = createRouter({
  history: createWebHistory(),
  routes,
})

export default router
