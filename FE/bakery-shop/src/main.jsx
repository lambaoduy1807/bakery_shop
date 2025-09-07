import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min.js';
import "./assets/styles/sign.css"
import "./assets/styles/style.css"
import "./assets/styles/style.scss"
import "./assets/styles/bootstrap.css"
// import "./assets/styles/style.css.map"
import "./assets/styles/responsive.css"
import Home from "./pages/shop/Home.jsx"
import './index.css'
import App from './App.jsx'

createRoot(document.getElementById('root')).render(
  <StrictMode>
    <Home />
  </StrictMode>,
)
