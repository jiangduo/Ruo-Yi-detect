<template>
  <div ref="pieChart" style="width: 600px; height: 400px;"></div>
</template>

<script>
export default {
  mounted() {
    const chart = this.$echarts.init(this.$refs.pieChart);
    chart.setOption({
      title: {
        text: '饼图示例',
        left: 'center'
      },
      tooltip: {},
      series: [{
        name: '访问来源',
        type: 'pie',
        radius: '50%',
        data: [
          {value: 335, name: '直接访问'},
          {value: 310, name: '邮件营销'},
          {value: 234, name: '联盟广告'},
          {value: 135, name: '视频广告'},
          {value: 1548, name: '搜索引擎'}
        ]
      }]
    });
  }
}
</script>
