import ShopLayout from '@/layouts/ShopLayout.vue'
import Home from '@/views/shop/Home.vue'
import ProductDetail from '@/views/shop/ProductDetail.vue'
import Cart from '@/views/shop/Cart.vue'
import SignUP from '@/views/shop/SignUP.vue'
import WishList from '@/views/shop/WishList.vue'
import CheckOut from '@/views/shop/CheckOut.vue'

export default [
  {
    path: '/',
    component: ShopLayout,
    children: [
      { path: '', name: 'home', component: Home },
      { path: 'product/:id', name: 'product-detail', component: ProductDetail },
      { path: 'cart', name: 'cart', component: Cart },
      { path: 'signup', name: 'signup', component: SignUP },
      { path: 'wishlist', name: 'wishlist', component: WishList },
        { path: 'checkout', name: 'checkout', component: CheckOut },
    ],
  },
]
