<template>
  <div class="dashboard-container">
    <div class="cards">
      <AdminCard title="Today Sales" value="₫15,200,000">
        <template #icon>
          <i-fa-solid-chart-bar class="icon" />
        </template>
      </AdminCard>
      <AdminCard title="Total Sales" value="245">
        <template #icon>
          <i-fa-solid-chart-bar class="icon" />
        </template>
      </AdminCard>
      <AdminCard title="Today Revenue" value="32">
        <template #icon>
          <i-fa-solid-chart-bar class="icon" />
        </template>
      </AdminCard>
      <AdminCard title="Total Revenue" value="32">
        <template #icon>
          <i-fa-solid-chart-bar class="icon" />
        </template>
      </AdminCard>
    </div>

    <div class="charts-grid">
      <BarChart />
      <LineChart />
      <PieChart />
    </div>
  </div>
</template>


<script setup>
import AdminCard from '@/components/admin/AdminCard.vue'
import BarChart from '@/components/admin/chart/BarChart.vue';
import LineChart from '@/components/admin/chart/LineChart.vue';
import PieChart from '@/components/admin/chart/PieChart.vue';
</script>

<style scoped>
.dashboard-container {
  max-width: 1200px;
  margin: 0 auto;
  padding: 1rem;
}

.cards {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(220px, 1fr));
  gap: 1rem;
  margin-top: 1rem;
  margin-bottom: 2rem;
}

.charts-grid {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 1.5rem;
  margin-top: 1rem;
}

/* Cho PieChart chiếm nguyên hàng */
.charts-grid> :nth-child(3) {
  grid-column: 1 / -1;
}
</style>
