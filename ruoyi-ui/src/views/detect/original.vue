<template>
  <div :class="className" :style="{ height: height, width: width }" />
</template>

<script>
import * as echarts from 'echarts';
let base = +new Date(2020,1, 1)
let oneDay = 6 * 3600 * 1000
let date = []
let data = [Math.random() *25]
for (let i = 1; i < 20000; i++) {
  var now = new Date((base += oneDay))
  date.push([now.getFullYear(), now.getMonth() + 1, now.getDate()].join('/'))
  data.push(Math.round((Math.random() - 0.5) * 20 + data[i - 1]))
}
export default {
  props: {
    className: {
      type: String,
      default: 'chart',
    },
    width: {
      type: String,
      default: '100%',
    },
    height: {
      type: String,
      default: '300px',
    },
  },
  data() {
    return {
      chart: null,
    }
  },
  mounted() {
    // 在生命周期函数mounted中，下面这个方法调用的意思是，页面加载出来就会自动渲染ECharts图
    // 其实就是调用了下面的initChart()
    // 如果想要触发某一个事件再渲染出ECharts图的话，就将initChart()写入要执行的方法中
    this.$nextTick(() => {
      this.initChart()
    })
  },
  beforeDestroy() {
    if (!this.chart) {
      return
    }
    this.chart.dispose()
    this.chart = null
  },
  methods: {
    initChart() {
      this.chart = echarts.init(this.$el, 'shine')
      this.chart.setOption({
        // 这里面填写echart图的绘制代码
        tooltip: {
          trigger: 'axis',
        },
        title: {
          left: 'center',
          text: '原始数据实时动态统计分析',
        },
        toolbox: {
          feature: {
            dataZoom: {
              yAxisIndex: 'none',
            },
            restore: {},
            saveAsImage: {},
          },
        },
        xAxis: {
          type: 'category',
          boundaryGap: false,
          data: date,
        },
        yAxis: {
          type: 'value',
          boundaryGap: [0, '100%'],
        },
        dataZoom: [
          {
            type: 'inside',
            start: 0,
            end: 10,
          },
          {
            start: 0,
            end: 10,
          },
        ],
        series: [
          {
            name: '车轴',
            type: 'line',
            symbol: 'none',
            sampling: 'lttb',
            itemStyle: {
              color: 'blue',
            },

            data: data,
          },
        ],
      })
    },
  },
}
</script>
