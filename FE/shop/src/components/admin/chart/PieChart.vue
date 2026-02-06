<template>
  <div class="chart-wrapper">
    <canvas ref="chartCanvas"></canvas>
  </div>
</template>

<script setup>
import { ref, onMounted, nextTick } from 'vue'
import { Chart as ChartJS, Title, Tooltip, Legend, ArcElement, PieController } from 'chart.js'

ChartJS.register(Title, Tooltip, Legend, ArcElement, PieController)

const chartCanvas = ref(null)

const chartData = {
  labels: ['Cakes', 'Cupcakes', 'Cookies', 'Bread'],
  datasets: [
    {
      backgroundColor: ['#41B883', '#E46651', '#00D8FF', '#DD1B16'],
      data: [40, 20, 80, 10]
    }
  ]
}

const chartOptions = {
  responsive: true,
  maintainAspectRatio: false,
  plugins: {
    legend: { labels: { color: '#fff' } },
    title: { display: true, text: 'Product Categories', color: '#fff' }
  }
}

let chartInstance = null

onMounted(() => {
  nextTick(() => {
    if (chartCanvas.value) {
      chartInstance = new ChartJS(chartCanvas.value.getContext('2d'), {
        type: 'pie',
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
