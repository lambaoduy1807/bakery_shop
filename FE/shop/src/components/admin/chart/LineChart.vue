<template>
  <div class="chart-wrapper">
    <canvas ref="chartCanvas"></canvas>
  </div>
</template>

<script setup>
import { ref, onMounted, nextTick } from 'vue'
import { Chart as ChartJS, Title, Tooltip, Legend, LineElement, PointElement, CategoryScale, LinearScale, LineController } from 'chart.js'

ChartJS.register(Title, Tooltip, Legend, LineElement, PointElement, CategoryScale, LinearScale, LineController)

const chartCanvas = ref(null)

const chartData = {
  labels: ['Jan','Feb','Mar','Apr','May','Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'],
  datasets: [
    {
      label: 'Monthly Sales',
      data: [65, 59, 80, 81, 56, 55, 40, 60, 75, 85, 90, 100],
      fill: false,
      borderColor: '#42b883',
      tension: 0.1
    }
  ]
}

const chartOptions = {
  responsive: true,
  maintainAspectRatio: false,
  plugins: {
    legend: { labels: { color: '#fff' } },
    title: { display: true, text: 'Monthly Sales Trend', color: '#fff' }
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
        type: 'line',
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
