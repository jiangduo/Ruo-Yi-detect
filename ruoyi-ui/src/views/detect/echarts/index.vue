<template>
  <div class="echarts-container">
    <div class="chart-header">ECharts Example</div>
    <div class="chart-content">
      <div class="chart-left">
        <div class="chart-title">chat2</div>
        <div class="chart-wrapper">
<!--          <echarts :options="chart1Options"></echarts>-->
          <test></test>
        </div>
      </div>
      <div class="chart-right">
        <div class="chart-title">Chart 2</div>
        <div class="chart-wrapper">
<!--          <echarts :options="chart2Options"></echarts>-->
          <LineChart></LineChart>
        </div>
      </div>
    </div>
  </div>
</template>


<!--<template>-->
<!--  <div id="app">-->
<!--    &lt;!&ndash;    <bar-chart></bar-chart>&ndash;&gt;-->
<!--    <el-row :gutter="100">-->
<!--      <el-col :sm="100" :lg="100" style="padding-left: 30px">-->
<!--        <test></test>-->
<!--      </el-col>-->
<!--      <el-col :sm="24" :lg="12" style="padding-left: 20px">-->
<!--        <LineChart></LineChart>-->
<!--      </el-col>-->
<!--    </el-row>-->
<!--    &lt;!&ndash;    <pie-chart></pie-chart>&ndash;&gt;-->
<!--  </div>-->
<!--</template>-->

<script>
// import BarChart from 'BarChart.vue'
// import PieChart from 'PieChart.vue'
import test from "@/views/detect/echarts/test.vue";
import LineChart from '@/views/detect/echarts/test2.vue';

export default {
  name: 'App',
  components: {
    LineChart,
    // BarChart,
    test,
  }
};

</script>


<style scoped lang="scss">
.echarts-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%;
  height: 100%;
}

.chart-header {
  font-size: 24px;
  font-weight: bold;
  margin-top: 20px;
  margin-bottom: 30px;
}

.chart-content {
  display: flex;
  justify-content: center;
  align-items: flex-start;
  width: 100%;
}

.chart-left, .chart-right {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 50%;
}

.chart-title {
  font-size: 20px;
  font-weight: bold;
  margin-bottom: 10px;
}

.chart-wrapper {
  width: 100%;
  height: 400px;
  border: 1px solid #ccc;
}
.home {
  blockquote {
    padding: 10px 20px;
    margin: 0 0 20px;
    font-size: 17.5px;
    border-left: 5px solid #eee;
  }
  hr {
    margin-top: 20px;
    margin-bottom: 20px;
    border: 0;
    border-top: 1px solid #eee;
  }
  .col-item {
    margin-bottom: 20px;
  }

  ul {
    padding: 0;
    margin: 0;
  }

  font-family: "open sans", "Helvetica Neue", Helvetica, Arial, sans-serif;
  font-size: 13px;
  color: #676a6c;
  overflow-x: hidden;

  ul {
    list-style-type: none;
  }

  h4 {
    margin-top: 0px;
  }

  h2 {
    margin-top: 10px;
    font-size: 26px;
    font-weight: 100;
  }

  p {
    margin-top: 10px;

    b {
      font-weight: 700;
    }
  }

  .update-log {
    ol {
      display: block;
      list-style-type: decimal;
      margin-block-start: 1em;
      margin-block-end: 1em;
      margin-inline-start: 0;
      margin-inline-end: 0;
      padding-inline-start: 40px;
    }
  }
}
</style>
