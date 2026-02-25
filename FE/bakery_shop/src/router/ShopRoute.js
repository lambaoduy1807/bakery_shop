import ShopLayout from '@/layouts/AdminLayout.vue'
import Home from '@/views/shop/Home.vue'
import ProductDetail from '@/views/shop/ProductDetail.vue'
import Cart from '@/views/shop/Cart.vue'

export default [
  {
    path: '/',
    component: ShopLayout,
    children: [
      { path: '', name: 'home', component: Home },
      { path: 'product/:id', name: 'product-detail', component: ProductDetail },
      { path: 'cart', name: 'cart', component: Cart },
    ],
  },
]
