<template>
  <div>
  
  <el-card>
    <el-row>
      <el-col :span="12"><Hisrogramspeed width="500px" height="400px" /></el-col>
      <el-col :span="12"><PieChartspeed width="500px" height="500px" /></el-col>
      <el-col :span="12"><HistogramAcc width="500px" height="500px" /></el-col>
      <el-col :span="12"><PieChartAcc width="500px" height="500px" /></el-col>
    </el-row>
  </el-card>
  <hr>
  <el-card></el-card>
  </div>
</template>

<script>
import Hisrogramspeed from './Echarts/HistogramSpeed'
import PieChartspeed from './Echarts/PieChartSpeed'
import HistogramAcc from "./Echarts/HistogramAcc"
import PieChartAcc from  "./Echarts/PieChartAcc"
export default {
  name: 'MethodsRange',
  components: {
    Hisrogramspeed,
    PieChartspeed,
    HistogramAcc,
    PieChartAcc
  },
}
</script>

<style lang="less" scoped>
</style>