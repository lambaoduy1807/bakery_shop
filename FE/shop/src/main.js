import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import { createPinia } from 'pinia'
import './style.css'
import '@/assets/styles/theme.css'
import '@/assets/styles/admin_themes.css'
import '@/assets/styles/shop.css'
// --- Font Awesome ---
// import { library } from '@fortawesome/fontawesome-svg-core'
// import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'

// // import các bộ icon bạn cần
// import { faUser, faEnvelope, faTrash, faEdit, faPlus,faBars,faBell, faCaretDown} from '@fortawesome/free-solid-svg-icons'
// import { faHeart } from '@fortawesome/free-regular-svg-icons'
// import { faFacebook, faTwitter } from '@fortawesome/free-brands-svg-icons'

// thêm icon vào thư viện toàn cục
// library.add(faUser, faEnvelope, faTrash, faEdit, faPlus, faHeart, faFacebook, faTwitter,faBars,faBell,faCaretDown)

// --- Tạo app 1 lần duy nhất ---
const app = createApp(App)

app.use(createPinia())
app.use(router)

// đăng ký global component <font-awesome-icon>
// app.component('font-awesome-icon', FontAwesomeIcon)

app.mount('#app')
