<template>
  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页 </el-breadcrumb-item>
      <el-breadcrumb-item>声发射信号检测</el-breadcrumb-item>
      <el-breadcrumb-item>实时检测</el-breadcrumb-item>
      <el-breadcrumb-item>结果分析</el-breadcrumb-item>
    </el-breadcrumb>
    <el-row>
      <el-col :span="2" :push="6"
        >
        <div >
          <el-select v-model="value" placeholder="请选择" class="selectInput2">
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
      <el-col :span="6" :offset="6"
        ><div>
          <el-input placeholder="请输入零件名称" v-model="queryInfo.query">
            <el-button
              slot="append"
              icon="el-icon-search"
              @click="Searchelement"
            ></el-button
          ></el-input></div
      ></el-col>
    </el-row>
    <el-radio-group v-model="radio1">
      <el-radio-button label="通道1"></el-radio-button>
      <el-radio-button label="通道2"></el-radio-button>
      <el-radio-button label="通道3"></el-radio-button>
      <el-radio-button label="通道4"></el-radio-button>
    </el-radio-group>
    <el-table
      :data="showData"
      border:stripe
      style="width: 100%"
      :default-sort="{ prop: 'time', order: 'descending' }"
    >
      <el-table-column
        align="center"
        type="index"
        label="序号"
        width="60px"
      ></el-table-column>
      <el-table-column
        align="center"
        prop="name"
        label="零件名称"
      ></el-table-column>
      <el-table-column align="center" prop="time" sortable label="到达时间">
      </el-table-column>
      <el-table-column align="center" prop="risetime" sortable label="上升时间">
      </el-table-column>
      <el-table-column
        align="center"
        prop="risecount"
        sortable
        label="上升计数"
      >
      </el-table-column>
      <el-table-column align="center" prop="energy" sortable label="能量">
      </el-table-column>
      <el-table-column
        align="center"
        prop="continue_time"
        sortable
        label="持续时间"
      >
      </el-table-column>
      <el-table-column align="center" prop="range" sortable label="幅度">
      </el-table-column>
       <el-table-column align="center" prop="style"  label="部件类型">
      </el-table-column>
      <el-table-column align="center" fixed="right" label="数据操作">
        <template slot-scope="scope">
          <el-button 
            type="primary"
            size="medium"
            @click="detailsview(scope.row)"
          >
            查看详情
          </el-button>
        </template>
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
    </div>
</template>
<script>
export default {
  name: 'Paramonitor',
  data() {
    return {
      queryInfo: {
        query: '',
        //当前的页数
        pagenum: 1,
        //当前每页显示多少条数据
        pagesize: 2,
      },
      List: 1,
      Listnumber: 2,
      change: '',
      namestr: '',
      value: '',
      value2: '',
      value1: '',
      datevalue: '',
      radio1: '通道1',
      radio2: '通道2',
      radio3: '通道3',
      radio4: '通道4',
      pickerOptions: {
        disabledDate(time) {
          return time.getTime() > Date.now()
        },
        shortcuts: [
          {
            text: '今天',
            onClick(picker) {
              picker.$emit('pick', new Date())
            },
          },
          {
            text: '昨天',
            onClick(picker) {
              const date = new Date()
              date.setTime(date.getTime() - 3600 * 1000 * 24)
              picker.$emit('pick', date)
            },
          },
          {
            text: '一周前',
            onClick(picker) {
              const date = new Date()
              date.setTime(date.getTime() - 3600 * 1000 * 24 * 7)
              picker.$emit('pick', date)
            },
          },
        ],
      },
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
      tableData: [
        {
          name: 'Carriage',
          time: '2022-4-2 11:00:20',
          risetime: '160',
          risecount: '150',
          energy: '540.31',
          continue_time: '140',
          range: '54',
          style:"Bearing"
        },
        {
          name: 'Bearing',
          time: '2022-4-2 12:00:20',
          risetime: '170',
          risecount: '159',
          energy: '560.31',
          continue_time: '152',
          range: '65',
          style:"Axle"
        },
        {
          name: 'Link',
          time: '2022-4-2 11:00:20',
          risetime: '130',
          risecount: '143',
          energy: '544.31',
          continue_time: '168',
          range: '52',
          style:"Bearing"
        },
        {
          name: 'Admin',
          time: '2022-4-2 11:00:20',
          risetime: '126',
          risecount: '155',
          energy: '546.32',
          continue_time: '178',
          range: '37',
          style:"Axle"
        },
        {
          name: 'Collapse',
          time: '2022-4-2 11:00:20',
          risetime: '164',
          risecount: '124',
          energy: '552.31',
          continue_time: '142',
          range: '77',
          style:"Axle"
        },
        {
          name: 'Spare',
          time: '2022-4-6 12:30:20',
          risetime: '145',
          risecount: '152',
          energy: '560.31',
          continue_time: '160',
          range: '59',
          style:"Bearing"
        },
        {
          name: 'Memo',
          time: '2022-4-7 16:30:50',
          risetime: '180',
          risecount: '149',
          energy: '591.23',
          continue_time: '190',
          range: '70',
          style:"Axle"
        },
        {
          name: 'Dive',
          time: '2022-4-7 19:52:40',
          risetime: '139',
          risecount: '161',
          energy: '579.48',
          continue_time: '188',
          range: '61',
          style:"Bearing"
        },
        {
          name: 'Mana',
          time: '2022-4-10 19:45:54',
          risetime: '159',
          risecount: '140',
          energy: '548.91',
          continue_time: '174',
          range: '71',
          style:"Axle"
        },
        {
          name: 'Writter',
          time: '2022-4-10 03:20:20',
          risetime: '180',
          risecount: '172',
          energy: '530.45',
          continue_time: '182',
          range: '82',
          style:"Bearing"
        },
      ],
      showData: [
        {
          name: 'Carriage',
          time: '2022-4-2 11:00:20',
          risetime: '160',
          risecount: '150',
          energy: '540.31',
          continue_time: '140',
          range: '54',
          style:"Axle"
        },
        {
          name: 'Bearing',
          time: '2022-4-2 12:00:20',
          risetime: '170',
          risecount: '159',
          energy: '560.31',
          continue_time: '152',
          range: '65',
          style:"Axle"
        },
      ],
      finalData: [],
      options2: [
        {
          value: '零件1',
          label: '零件1',
        },
        {
          value: '零件2',
          label: '零件2',
        },
        {
          value: '零件3',
          label: '零件3',
        },
        {
          value: '零件4',
          label: '零件4',
        },
        {
          value: '零件5',
          label: '零件5',
        },
      ],
    }
  },
  methods: {
    getUserList(a, b) {
      this.showData.length = 0
      for (let i = 0; i < a; i++) {
        this.showData.push(this.tableData[i + b])
      }
    },
    detailsview(row){
      this.$bus.$emit("msgname",row.name)
      this.$bus.$emit("msgstyle",row.style)
      this.$bus.$emit("msgtime",row.time)
      this.$router.push('/detailsview')
    },
    Searchelement() {
      this.namestr = this.queryInfo.query

      let strlength = this.namestr.length
      this.showData = []
      for (let j = 0; j < this.tableData.length; j++) {
        let samestr = 0
        if (this.namestr.length < this.tableData[j].name.length) {
          for (let i = 0; i < strlength; i++) {
            if (this.tableData[j].name[i] === this.namestr[i]) {
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
    //监听pagesize改变
    handleSizeChange(newSize) {
      this.queryInfo.pagesize = newSize
      this.Listnumber = newSize
      if (this.List > 1) {
        this.getUserList(newSize, (this.List - 1) * newSize)
      } else {
        this.getUserList(newSize, 0)
      }
    },
    //监听页码值改变的时间
    handleCurrentChange(newPage) {
      this.queryInfo.pagenum = newPage
      this.List = newPage
      if (newPage > 1) {
        this.getUserList(this.Listnumber, (newPage - 1) * this.Listnumber)
      } else {
        this.getUserList(this.Listnumber, 0)
      }
    },
    deleteRow(index, rows) {
      rows.splice(index, 1)
    },
    open() {
      this.$message({
        message: '该部件运行良好',
        type: 'success',
      })
    },
  },
  watch: {
    value: {
      handler(newVal, oldVal) {
        if (newVal === '车轴' && !oldVal) {
          this.finalData = this.showData
          this.showData = []
          this.showData = this.finalData
          this.finalData = []
        }
        if (newVal === '车轴' && oldVal) {
          this.showData = []
          for (let i = 0; i < this.Listnumber; i++) {
            this.showData[i] = this.tableData[i]
          }
        }
        if (newVal === '轴承' && !oldVal) {
          this.showData = []
          this.showData = this.tableData
        }
        if (newVal === '轴承' && oldVal) {
          this.showData = []
          this.showData = this.tableData
        }
        immediate: true
      },
    },
  },
  mounted(){
   
  }
}
</script>

<style lang="less" scoped>


</style>
