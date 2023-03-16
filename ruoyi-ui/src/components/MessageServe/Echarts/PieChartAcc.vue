<template>
  <div :class="className" :style="{height:height,width:width}" />
</template>

<script>
import * as echarts from "echarts";


export default {
  props: {
    className: {
      type: String,
      default: 'chart'
    },
    width: {
      type: String,
      default: '100%'
    },
    height: {
      type: String,
      default: '300px'
    }
  },
  data() {
    return {
      chart: null
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
            title: {
    text: '各检测方法准确度比较',
    left: 'center'
  },
  tooltip: {
    trigger: 'item'
  },
  legend: {
    orient: 'vertical',
    left: 'left'
  },
  series: [
    {
      name: '检测方式',
      type: 'pie',
      radius: '50%',
      data: [
        { value: 75, name: '传统检测方式' },
        { value: 90, name: 'DBN检测方式' },
        { value: 80, name: 'LSTM检测方式' },
        { value: 88, name: 'CNN检测方式' },
      ],
      emphasis: {
        itemStyle: {
          shadowBlur: 10,
          shadowOffsetX: 0,
          shadowColor: 'rgba(0, 0, 0, 0.5)'
        }
      }
    }
  ]
		})
    }
  }
}
</script>
