<template>
  <div ref="barChart" style="width: 600px; height: 400px;"></div>
</template>

<script>
export default {
  mounted() {
    const chart = this.$echarts.init(this.$refs.barChart);
    chart.setOption({
      title: {
        text: '柱状图示例'
      },
      tooltip: {},
      xAxis: {
        data: ['星期一', '星期二', '星期三', '星期四', '星期五', '星期六', '星期日']
      },
      yAxis: {},
      series: [{
        name: '销量',
        type: 'bar',
        data: [5, 20, 36, 10, 10, 20, 5]
      }]
    });
  }
}
</script>
