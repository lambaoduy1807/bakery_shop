<template>
  <div class="home">
    <div class="list-wrapper">
      <ShopHero></ShopHero>
      <!-- todays  -->
      <BaseSection tag="Today's" title="Flash sales">
        <HorizontalProductList :products="mockProducts" />
      </BaseSection>

      <!-- Category  -->
      <BaseSection tag="Categories" title="Browse by category">
        <HorizontalCategories :categories="categories" />
      </BaseSection>

      <!-- Bestselling  -->
      <BaseSection tag="This month" title="Best selling products">
        <HorizontalProductList :products="mockProducts" />
      </BaseSection>

      <!-- Our Products  -->
      <BaseSection tag="Our Products" title="Explore our Products">
        <GridProducts :products="mockProducts" :rows="2" :cols="4" />
      </BaseSection>

      <!-- Features  -->
      <BaseSection tag="Featured" title="New arrival">
        <div class="feature-grid">
          <!-- Cột trái -->
          <div class="left">
            <CategoryShowcase title="Bakery" info="Fresh cakes & sweet treats" image="/img/background_product.jpg"
              @click="goToCategory" />
          </div>

          <!-- Cột phải -->
          <div class="right">
            <div class="top">
              <CategoryShowcase title="Bakery" info="Fresh cakes & sweet treats" image="/img/background_product.jpg"
                @click="goToCategory" />
            </div>

            <div class="bottom">
              <CategoryShowcase title="Bakery" info="Fresh cakes & sweet treats" image="/img/background_product.jpg"
                @click="goToCategory" />
              <CategoryShowcase title="Bakery" info="Fresh cakes & sweet treats" image="/img/background_product.jpg"
                @click="goToCategory" />
            </div>
          </div>
        </div>
      </BaseSection>

    </div>
  </div>
</template>

<script setup>
import ShopHero from '@/components/shop/layout/ShopHero/HeroSection.vue'
// import CategoryVertical from '@/components/shop/layout/Category/CategoryVertical.vue'
import HorizontalProductList from '@/components/shop/layout/HorizontalProductList.vue'
import GridProducts from '@/components/shop/layout/GridProducts.vue'
import HorizontalCategories from '@/components/shop/layout/Category/HorizontalCategories.vue'
import CategoryShowcase from '@/components/shop/layout/Category/CategoryShowcase.vue'
import BaseSection from '@/components/shop/layout/BaseSection.vue'
import { mockProducts } from '@/services/ProductServices.js'
// Dùng unplugin-icons → import theo dạng component
import IFaShoppingCart from '~icons/fa/shopping-cart'
import IFaHeartO from '~icons/fa/heart-o'
import IMdiShoeSneaker from '~icons/mdi/shoe-sneaker'

const categories = [
  { label: "Thời trang nam", icon: IFaShoppingCart },
  { label: "Phụ kiện", icon: IFaHeartO },
  { label: "Giày dép", icon: IMdiShoeSneaker }
]

</script>
<style scoped>
.list-wrapper {
  padding: 0 16px;
  margin: 0px 32px
}

.feature-grid {
  display: grid;
  grid-template-columns: 1fr 1fr; /* 2 cột */
  gap: 16px;
}

/* Cột trái */
.left {
  height: 100%;
}

.left > * {
  width: 100%;
  height: 100%;
}

/* Cột phải chia 2 phần */
.right {
  display: flex;
  flex-direction: column;
  gap: 16px;
}

/* Card top bên phải */
.top > * {
  width: 100%;
}

/* Hai card dưới chia đôi */
.bottom {
  display: grid;
  grid-template-columns: 1fr 1fr; /* chia 2 */
  gap: 16px;
}

</style>
