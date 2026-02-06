import AdminLayout from '@/layouts/AdminLayout.vue'
import Dashboard from '@/views/admin/Dashboard.vue'
import Products from '@/views/admin/Products.vue'
import Users from '@/views/admin/Users.vue'

export default [
  {
    path: '/admin',
    component: AdminLayout,
    children: [
      { path: '', redirect: 'dashboard' },
      { path: 'dashboard', name: 'dashboard', component: Dashboard },
      { path: 'products', name: 'admin-products', component: Products },
      { path: 'users', name: 'admin-users', component: Users },
    ],
  },
]
