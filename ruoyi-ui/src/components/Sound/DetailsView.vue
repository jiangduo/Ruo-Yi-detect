<template>
  <div>
    <el-row>
      <el-col :span="2" :push="6"
        ><div style="display: inline-block">
          <el-select v-model="value" placeholder="请选择" class="input2">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            >
            </el-option>
          </el-select>
        </div>
      </el-col>
      <el-col :span="7" :offset="6"
        ><div>
          <el-input placeholder="请输入零件名称" v-model="queryInfo.query">
            <el-button
              slot="append"
              icon="el-icon-search"
              @click="Searchelement"
            ></el-button
          ></el-input></div
      ></el-col>
      <el-col :span="2" :push="3"
        ><el-button type="info" size="medium">返回上一级</el-button
      ></el-col>
      <el-col> </el-col>
    </el-row>
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
    <el-table :data="showData" border stripe style="width: 100%">
      <el-table-column align="center" prop="ID" label="部件编号">
      </el-table-column>
      <el-table-column align="center" prop="type" label="轴承类型">
      </el-table-column>
      <el-table-column align="center" prop="describe" label="具体故障">
      </el-table-column>
      <el-table-column align="center" sortable prop="reason" label="故障原因">
      </el-table-column>
      <el-table-column align="center" prop="way" label="检测方式">
      </el-table-column>
      <el-table-column align="center" sortable prop="time" label="测试时间">
      </el-table-column>
      <el-table-column align="center" label="实时检测">
        <el-button type="primary" size="medium">实时检测</el-button>
      </el-table-column>
      <el-table-column align="center" label="单据打印">
        <el-button type="primary" size="medium" @click="printfailure"
          >故障单打印</el-button
        >
      </el-table-column>
    </el-table>
    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="queryInfo.pagenum"
      :page-sizes="[2, 5, 10]"
      :page-size="queryInfo.pagesize"
      layout="total, sizes, prev, pager, next, jumper"
      :total="10"
    >
    </el-pagination>
    <el-card class="pic1">
      <h2>实时动态分析：</h2>
      <el-image
        :src="require(`../../assets/images/time_frequent_wave_measure.png`)"
      ></el-image>
    </el-card>
    <el-card>
      <el-row>
        <el-col>
          <h2>时域指标分析</h2>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="6"><h3>均值：-0.14</h3></el-col>
        <el-col :span="6"><h3>方差:32.39</h3></el-col>
        <el-col :span="6"><h3>标准差:3.25</h3></el-col>
        <el-col :span="6"><h3>均方根值:5.69</h3></el-col>
      </el-row>
      <el-row>
        <el-col :span="6"><h3>峰值指标Xp:10.00</h3></el-col>
        <el-col :span="6"><h3>峭度值:-1.17</h3></el-col>
        <el-col :span="6"><h3>偏度值:0.03</h3></el-col>
        <el-col :span="6"><h3>绝对平均值:4.90</h3></el-col>
      </el-row>
      <el-row>
        <el-col :span="6"><h3>方根幅度Xr:4.32</h3></el-col>
        <el-col :span="6"><h3>裕度因子:2.32</h3></el-col>
        <el-col :span="6"><h3>峰值因子:1.76</h3></el-col>
        <el-col :span="6"><h3>波形因子:1.16</h3></el-col>
      </el-row>
      <el-row>
        <el-col :span="6" ><h3>脉冲因子:0.36</h3></el-col>
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
      queryInfo: {
        query: '',
        //当前的页数
        pagenum: 1,
        //当前每页显示多少条数据
        pagesize: 2,
      },
      changedID: '',
      changedtype: '',
      changedtime: '',
      changedes: '',
      changedstate: '',
      List: 1,
      Listnumber: 2,
      change: '',
      namestr: '',
      value: '',
      tableData: [
        {
          ID: 'Carriage',
          time: '2022-4-2 11:00:20',
          type: 'Bearing',
          describe: '部件老化',
          reason: '维护不当',
          continue_time: '140',
          way: '机器检查',
        },
        {
          ID: 'Bearing',
          time: '2022-4-2 12:00:20',
          type: 'Axle',
          describe: '部件损坏',
          reason: '使用寿命过长',
          way: '人工检查',
        },
        {
          ID: 'Link',
          time: '2022-4-2 11:00:20',
          type: 'Bearing',
          describe: '部件损坏',
          reason: '使用寿命过长',
          way: '机器检查',
        },
        {
          ID: 'Admin',
          time: '2022-4-2 11:00:20',
          type: 'Bearing',
          describe: '部件老化',
          reason: '维护不当',
          way: '人工检查',
        },
        {
          ID: 'Collapse',
          time: '2022-4-2 11:00:20',
          type: 'Axle',
          describe: '部件老化',
          reason: '使用寿命过长',
          way: '人工检查',
        },
        {
          ID: 'Spare',
          time: '2022-4-6 12:30:20',
          type: 'Bearing',
          describe: '部件老化',
          reason: '维护不当',
          way: '机器检查',
        },
        {
          ID: 'Memo',
          time: '2022-4-7 16:30:50',
          type: 'Axle',
          describe: '部件损坏',
          reason: '使用寿命过长',
          way: '人工检查',
        },
        {
          ID: 'Dive',
          time: '2022-4-7 19:52:40',
          type: 'Bearing',
          describe: '部件损坏',
          reason: '维护不当',
          way: '人工检查',
        },
        {
          ID: 'Mana',
          time: '2022-4-10 19:45:54',
          type: 'Bearing',
          describe: '部件损坏',
          reason: '使用寿命过长',
          way: '机器检查',
        },
        {
          ID: 'Writter',
          time: '2022-4-10 03:20:20',
          type: 'Axle',
          describe: '部件损坏',
          reason: '使用寿命过长',
          way: '机器检查',
        },
      ],
      showData: [
        {
          ID: 'Carriage',
          time: '2022-4-2 11:00:20',
          type: 'Bearing',
          describe: '部件老化',
          reason: '维护不当',
          way: '机器检查',
        },
        {
          ID: 'Bearing',
          time: '2022-4-2 12:00:20',
          type: 'Axle',
          describe: '部件老化',
          reason: '使用寿命过长',
          way: '人工检查',
        },
      ],
      options: [
        {
          value: '车轴',
          label: '车轴',
        },
        {
          value: '轴承',
          label: '轴承',
        },
      ],
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
    getUserList(a, b) {
      this.showData.length = []
      for (let i = 0; i < a; i++) {
        this.showData.push(this.tableData[i + b])
      }
    },
    handleCurrentChange(newPage) {
      this.queryInfo.pagenum = newPage
      this.List = newPage
      this.showData = []
      if (newPage > 1) {
        this.getUserList(this.Listnumber, (newPage - 1) * this.Listnumber)
      } else {
        this.getUserList(this.Listnumber, 0)
      }
    },
    Searchelement() {
      this.namestr = this.queryInfo.query
      let strlength = this.namestr.length
      this.showData = []
      for (let j = 0; j < this.tableData.length; j++) {
        let samestr = 0
        if (this.namestr.length < this.tableData[j].ID.length) {
          for (let i = 0; i < strlength; i++) {
            if (this.tableData[j].ID[i] === this.namestr[i]) {
              samestr++
            }
          }
        }
        if (samestr === strlength) {
          this.showData.push(this.tableData[j])
        }
      }
      this.$forceUpdate()
    },
    handleSizeChange(newSize) {
      this.queryInfo.pagesize = newSize
      this.Listnumber = newSize
      this.showData = []
      if (this.List > 1) {
        this.getUserList(newSize, (this.List - 1) * newSize)
      } else {
        this.getUserList(newSize, 0)
      }
    },
    quit() {
      this.$router.push('/addsoundelement')
    },
    printfailure() {
      this.$router.push("/printfailure")
    },
  },
  mounted() {
    this.$bus.$on('msgname', (data) => {
      localStorage.setItem('name', data)
      console.log('111')
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
.check {
  padding-top: 10px;
}
.pic1 {
  text-align: center;
}
</style>