<template>
  <div>
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span style="text-align: center; display: block">属性预览</span>
        <el-button style="float: right; padding: 3px 0" type="text"></el-button>
      </div>
      <h1 align="center" style="font-size: 25px">基本属性</h1>
      <el-row :gutter="20">
        <el-col :span="8"
          ><h2 align="center">部件编号:{{ getname() }}</h2></el-col
        >
        <el-col :span="8"
          ><h2 align="center">轴承类型:{{ getstyle() }}</h2></el-col
        >
        <el-col :span="8" align="center"
          ><h2>到达时间:{{ gettime() }}</h2></el-col
        >
      </el-row>
      <el-row :gutter="20">
        <el-col :span="8">
          <h2 align="center">信号类型：声信号</h2>
        </el-col>
        <el-col :span="8">
          <h2 align="center">具体配置：圆柱滚珠轴承</h2>
        </el-col>
        <el-col :span="8">
          <h2 align="center">创建时间:2020-12-14 9:20</h2>
        </el-col>
      </el-row>
      <el-row :gutter="20">
        <el-col :push="5" :span="8">
         <h2>部件检测方式:</h2>
        </el-col>
        <el-col :span="10" class="check">
          <el-checkbox-group v-model="checkList">
            <el-checkbox
              :border="true"
              label="CNN检测方式"
              style=""
            ></el-checkbox>
            <el-checkbox :border="true" label="LSTM检测方式"></el-checkbox>
            <el-checkbox :border="true" label="DBN检测方式"></el-checkbox>
          </el-checkbox-group>
        </el-col>
      </el-row>
      <el-row :gutter="20" >
        <el-col :offset="9" :span="6">
          <h2 align="center">
            部件描述：
            <el-input
              type="textarea"
              placeholder="请输入部件描述"
              :rows="5"
            ></el-input>
          </h2>
        </el-col>
      </el-row>
      <el-row>
        <el-col :offset="18" :span="6">
          <el-button type="primary">修改</el-button>
          <el-button type="info" @click="quit">退出</el-button>
        </el-col>
      </el-row>
    </el-card>
  </div>
</template>

<script>
export default {
  name: 'ComponentView',
  data() {
    return {
      checkList: [],
    }
  },
  methods: {
    getname() {
      return localStorage.getItem('name')
    },
    getstyle() {
      return localStorage.getItem('style')
    },
    gettime() {
      return localStorage.getItem('time')
    },
    quit(){
      this.$router.push('/addsoundelement')
    }
  },
  mounted() {
    this.$bus.$on('msgname', (data) => {
      localStorage.setItem('name', data)
    })
    this.$bus.$on('msgstyle', (data) => {
      localStorage.setItem('style', data)
    })
    this.$bus.$on('msgtime', (data) => {
      localStorage.setItem('time', data)
    })
  },
}
</script>

<style lang="less" scoped>
.text {
  font-size: 20px;
}

.item {
  margin-bottom: 18px;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: '';
  span {
    text-align: center;
  }
}
.clearfix:after {
  clear: both;
}
.box-card {
  width: 100%;
  height: 1000px;
}
span {
  font-size: 25px;
  color: #333744;
}
.span1 {
  padding-left: 10px;
}
h1 {
  padding-bottom: 20px;
  margin: 0;
}
h2 {
  font-size: 20px;
  padding: 0;
}
inputwidth /deep/ .el-input {
  width: 120px;
}
.check{
  padding-top: 10px;
}
</style>