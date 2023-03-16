<!--<template>-->
<!--  <div id="app">-->
<!--    <div ref="chart" style="width: 100%; height: 300px;"></div>-->

<!--  </div>-->
<!--</template>-->

<!--<script>-->
<!--import * as echarts from 'echarts';-->


<!--export default {-->
<!--  mounted() {-->
<!--    // 基于准备好的dom，初始化echarts实例-->
<!--    const myChart = echarts.init(this.$refs.chart);-->

<!--    // 指定图表的配置项和数据-->
<!--    const option = {-->
<!--      xAxis: {-->
<!--        type: 'category',-->
<!--        data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']-->
<!--      },-->
<!--      yAxis: {-->
<!--        type: 'value'-->
<!--      },-->
<!--      series: [{-->
<!--        data: [120, 200, 150, 80, 70, 110, 130],-->
<!--        type: 'line'-->
<!--      }]-->
<!--    };-->

<!--    // 使用刚指定的配置项和数据显示图表。-->
<!--    myChart.setOption(option);-->
<!--  }-->
<!--}-->
<!--</script>-->




<template>
  <div>
    <div ref="chart" style="height: 400px;"></div>
  </div>
</template>

<script>
import * as echarts from 'echarts';

export default {
  name: 'DynamicLineChart',
  data() {
    return {
      chartData: [], // 折线图数据
      chartInstance: null, // Echarts实例
    }
  },
  mounted() {
    // 创建Echarts实例
    this.chartInstance = echarts.init(this.$refs.chart);

    // 定义折线图配置项
    const option = {
      title: {
        text: '实时变化',
      },
      tooltip: {
        trigger: 'axis',
        axisPointer: {
          animation: false,
        },
      },
      xAxis: {
        type: 'category',
        boundaryGap: false,
        data: [],
      },
      yAxis: {
        type: 'value',
        boundaryGap: [0, '100%'],
      },
      series: [{
        name: '折线图',
        type: 'line',
        data: [],
        animation: false,
        areaStyle: {},
        markLine: {
          data: [{
            type: 'average',
            name: '平均值',
          }],
        },
      }],
    };

    // 设置折线图配置项
    this.chartInstance.setOption(option);

    // 初始化折线图数据
    this.initChartData();

    // 启动动态更新折线图数据
    setInterval(() => {
      this.updateChartData();
    }, 2000);
  },
  methods: {
    initChartData() {
      // 初始化折线图数据，此处假设x轴为时间，y轴为随机数
      const xAxisData = [];
      const seriesData = [];
      for (let i = 0; i < 10; i++) {
        xAxisData.push(new Date().toLocaleTimeString());
        seriesData.push(Math.round(Math.random() * 100));
      }
      this.chartInstance.setOption({
        xAxis: {
          data: xAxisData,
        },
        series: [{
          data: seriesData,
        }],
      });
      this.chartData = seriesData;
    },
    updateChartData() {
      // 动态更新折线图数据，此处假设x轴为时间，y轴为随机数
      const xAxisData = new Date().toLocaleTimeString();
      const newYData = Math.round(Math.random() * 100);
      this.chartData.push(newYData);
      this.chartData.shift();
      this.chartInstance.setOption({
        xAxis: {
          data: this.getNewXData(),
        },
        series: [{
          data: this.chartData,
        }],
      });
    },
    getNewXData() {
      // 获取最新的x轴数据
      const time = new Date().toLocaleTimeString();
      const xAxisData = this.chartInstance.getOption().xAxis[0].data;
      const newTime = time;
      if (xAxisData.length >= 10) {
        xAxisData.shift();
      }
      xAxisData.push(newTime);
      return xAxisData;
    },
  },
};
</script>
