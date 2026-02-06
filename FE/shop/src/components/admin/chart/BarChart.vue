<template>
  <div class="chart-wrapper">
    <canvas ref="chartCanvas"></canvas>
  </div>
</template>

<script setup>
import { ref, onMounted, nextTick } from 'vue'
import { Chart as ChartJS, Title, Tooltip, Legend, BarElement, CategoryScale, LinearScale, BarController } from 'chart.js'

ChartJS.register(Title, Tooltip, Legend, BarElement, CategoryScale, LinearScale, BarController)

const chartCanvas = ref(null)

const chartData = {
  labels: ['2018', '2019', '2020', '2021', '2022'],
  datasets: [
    { label: 'USA', data: [30, 50, 40, 60, 80], backgroundColor: '#ff5252' },
    { label: 'UK', data: [20, 40, 30, 50, 70], backgroundColor: '#ffb74d' },
    { label: 'AU', data: [25, 35, 45, 55, 65], backgroundColor: '#7c4dff' }
  ]
}

const chartOptions = {
  responsive: true,
  maintainAspectRatio: false,
  plugins: {
    legend: { labels: { color: '#fff' } },
    title: { display: true, text: 'Sales by Country', color: '#fff' }
  },
  scales: {
    x: { ticks: { color: '#fff' }, grid: { color: '#444' } },
    y: { ticks: { color: '#fff' }, grid: { color: '#444' }, beginAtZero: true }
  }
}

let chartInstance = null

onMounted(() => {
  nextTick(() => {
    if (chartCanvas.value) {
      chartInstance = new ChartJS(chartCanvas.value.getContext('2d'), {
        type: 'bar',
        data: chartData,
        options: chartOptions
      })
    }
  })
})
</script>

<style scoped>
.chart-wrapper {
  width: 100%;
  height: 400px;
  background-color: #2a2a2a;
  border-radius: 12px;
  padding: 10px;
}

.chart-wrapper canvas {
  width: 100% !important;
  height: 100% !important;
}
</style>
