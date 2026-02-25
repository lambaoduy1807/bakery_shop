import Product from '@/models/Product'

export const mockProducts = [
  new Product({
    id: 1,
    name: 'iPhone 15 Pro Max',
    price: 28990000,
    image: '/img/SSJ4.jpg',
    description: 'Flagship smartphone with A17 Pro chip and titanium design.',
    category: 'Smartphone',
    save: 2000000,
    rate: 4.9,
  }),

  new Product({
    id: 2,
    name: 'Samsung Galaxy S24 Ultra',
    price: 25990000,
    image:'/img/SSJ4.jpg',
    description: 'Premium Android phone with AI-powered features.',
    category: 'Smartphone',
    save: 1500000,
    rate: 4.8,
  }),

  new Product({
    id: 3,
    name: 'MacBook Air M2 13"',
    price: 24990000,
    image: '/img/SSJ4.jpg',
    description: 'Lightweight laptop powered by Apple M2 chip.',
    category: 'Laptop',
    save: 3000000,
    rate: 4.7,
  }),

  new Product({
    id: 4,
    name: 'Sony WH-1000XM5',
    price: 7900000,
    image: '/img/SSJ4.jpg',
    description: 'High-end noise-canceling wireless headphones.',
    category: 'Headphones',
    save: 1000000,
    rate: 4.8,
  }),

  new Product({
    id: 5,
    name: 'Apple Watch Series 9',
    price: 10990000,
    image: '/img/SSJ4.jpg',
    description: 'Advanced smartwatch with health and fitness tracking.',
    category: 'Smartwatch',
    save: 700000,
    rate: 4.6,
  }),
]


export async function getProducts() {
  return new Promise(resolve => {
    setTimeout(() => {
      resolve(mockProducts)
    }, 200)
  })
}
