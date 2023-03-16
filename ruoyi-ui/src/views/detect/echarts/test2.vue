<!--<template>-->
<!--  <div>-->
<!--    <div ref="lineChart" style="width: 600px; height: 400px;"></div>-->
<!--  </div>-->
<!--</template>-->

<!--<script>-->
<!--import echarts from 'echarts';-->

<!--export default {-->
<!--  name: 'LineChart',-->
<!--  mounted() {-->
<!--    // 基于准备好的dom，初始化echarts实例-->
<!--    const myChart = echarts.init(this.$refs.lineChart);-->

<!--    // 指定图表的配置项和数据-->
<!--    const option = {-->
<!--      title: {-->
<!--        text: '折线图示例'-->
<!--      },-->
<!--      tooltip: {},-->
<!--      legend: {-->
<!--        data: ['销量']-->
<!--      },-->
<!--      xAxis: {-->
<!--        data: ['周一', '周二', '周三', '周四', '周五', '周六', '周日']-->
<!--      },-->
<!--      yAxis: {},-->
<!--      series: [{-->
<!--        name: '销量',-->
<!--        type: 'line',-->
<!--        data: [5, 20, 36, 10, 10, 20, 7]-->
<!--      }]-->
<!--    };-->

<!--    // 使用刚指定的配置项和数据显示图表。-->
<!--    myChart.setOption(option);-->
<!--  }-->
<!--};-->
<!--</script>-->



<template>
  <div>
    <div ref="chart" style="width: 100%; height: 500px;"></div>
  </div>
</template>

<script>
import echarts from 'echarts'

export default {
  data () {
    return {
      chart: null,
      option: {
        title: {
          text: '运行情况',
          left: 'center'
        },
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            animation: false
          }
        },
        xAxis: {
          type: 'category',
          boundaryGap: false,
          data: []
        },
        yAxis: {
          type: 'value',
          boundaryGap: [0, '100%']
        },
        series: [
          {
            name: '数值',
            type: 'line',
            data: [],
            smooth: true,
            symbol: 'none',
            areaStyle: {}
          }
        ]
      }
    }
  },
  mounted () {
    this.chart = echarts.init(this.$refs.chart)
    this.chart.setOption(this.option)

    // 模拟数据
    const data = []
    for (let i = 0; i < 10; i++) {
      data.push(Math.round(Math.random() * 1000))
    }

    // 动态更新数据
    setInterval(() => {
      const lastData = data[data.length - 1]
      const newData = Math.round(Math.random() * 1000)
      data.shift()
      data.push(newData)
      this.option.xAxis.data.shift()
      this.option.xAxis.data.push(new Date().toLocaleTimeString())
      this.option.series[0].data.shift()
      this.option.series[0].data.push(newData)
      this.chart.setOption(this.option)
    }, 1000)
  }
}
</script>
