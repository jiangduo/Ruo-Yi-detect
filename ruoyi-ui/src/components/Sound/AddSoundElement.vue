<template>
  <div>
    <!-- 面包屑导航区 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页 </el-breadcrumb-item>
      <el-breadcrumb-item>声发射信号监测</el-breadcrumb-item>
      <el-breadcrumb-item>部件配置</el-breadcrumb-item>
    </el-breadcrumb>
    <!-- 卡片视图区域 -->
    <el-card>
      <!-- 搜索与添加区域 -->
      <el-row :gutter="20">
        <el-col :span="10">
          <el-input placeholder="请输入查找内容" v-model="queryInfo.query">
            <el-button
              slot="append"
              icon="el-icon-search"
              @click="SearchUser"
            ></el-button>
          </el-input>
        </el-col>
        <el-col :span="4"> </el-col>
      </el-row>
    </el-card>
    <el-table :data="showData" border stripe style="width: 100%">
      <el-table-column
        align="center"
        type="index"
        label="编号"
        width="60px"
        :default-sort="{ prop: 'time', order: 'descending' }"
      ></el-table-column>
      <el-table-column align="center" prop="ID" label="部件编号">
      </el-table-column>
      <el-table-column align="center" prop="type" label="轴承类型">
      </el-table-column>
      <el-table-column align="center" prop="describe" label="部件描述">
      </el-table-column>
      <el-table-column align="center" sortable prop="time" label="创建时间">
      </el-table-column>
      <el-table-column align="center" prop="state" label="状态">
      </el-table-column>
      <el-table-column align="center" prop="style" label="部件类型">
      </el-table-column>
      <el-table-column align="center"  label="数据修改">
        <template slot-scope="scope">
          <el-popover placement="right" width="400" trigger="click">
            <input
              type="text"
              placeholder="请输入部件号"
              style="width: 200px; height: 20px; margin: 5px 30px 10px 30px"
              v-model="changedID"
            />
            <el-button type="primary" size="mini" @click="IDchange(scope.row)"
              >确认修改</el-button
            >
            <input
              type="text"
              placeholder="请输入轴承类型"
              style="width: 200px; height: 20px; margin: 0 30px 10px 30px"
              v-model="changedtype"
            />
            <el-button type="primary" size="mini" @click="typechange(scope.row)"
              >确认修改</el-button
            >
            <input
              type="text"
              placeholder="请输入部件描述"
              style="width: 200px; height: 20px; margin: 0 30px 10px 30px"
              v-model="changedes"
            />
            <el-button
              type="primary"
              size="mini"
              @click="powerchange(scope.row)"
              >确认修改</el-button
            >
            <input
              type="text"
              placeholder="请输入创建时间"
              style="width: 200px; height: 20px; margin: 0 30px 10px 30px"
              v-model="changedtime"
            />
            <el-button type="primary" size="mini" @click="timechange(scope.row)"
              >确认修改</el-button
            >
            <input
              type="text"
              placeholder="请输入轴承类型"
              style="width: 200px; height: 20px; margin: 0 30px 10px 30px"
              v-model="changedstate"
            />
            <el-button
              type="primary"
              size="mini"
              @click="statechange(scope.row)"
              >确认修改</el-button
            >
            <el-button slot="reference" type="primary" style="margin: 0 15px"
              >部件修改</el-button
            >
          </el-popover>
        </template>
      </el-table-column>
      <el-table-column align="center"  label="部件查看">
        <template slot-scope="scope">
            <el-button slot="reference" type="primary" style="margin: 0 15px"
               @click="Sendmsg(scope.row)"
              >部件查看</el-button
            >
        </template>
      </el-table-column>
      <el-table-column align="center" fixed="right" label="数据删除">
        <template slot-scope="scope">
          <el-button
            @click.native.prevent="deleteRow(scope.$index, showData)"
            type="danger"
            size="medium"
          >
            部件删除
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
  name: 'AddSoundElement',
  data() {
    return {
      //获取用户列表的参数对象
      changedID: '',
      changedtype: '',
      changedtime: '',
      changedes: '',
      changedstate: '',
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
      tableData: [
        {
          ID: 'admin',
          type: 'Bearing',
          describe: '一号工厂生产',
          time: '2021-10-2',
          state: 'normal',
          style:"Axle"
        },
        {
          ID: 'yzw9931',
          type: 'Bearing',
          describe: '三号工厂生产',
          time: '2022-3-2',
          state: 'damaged',
          style:"Bearing"
        },
        {
          ID: 'yzw',
          type: 'Bearing',
          describe: '二号工厂生产',
          time: '2011-2-2',
          state: 'normal',
          style:"Axle"
        },
        {
          ID: 'yzw9931',
          type: 'Bearing',
          describe: '一号工厂生产',
          time: '2014-6-2',
          state: 'damaged',
          style:"Bearing"
        },
        {
          ID: 'yuxin',
          type: 'Bearing',
          describe: '四号工厂生产',
          time: '2017-9-2',
          state: 'normal',
          style:"Axle"
        },
        {
          ID: 'tinglan',
          type: 'Bearing',
          describe: '一号工厂生产',
          time: '2021-1-2',
          state: 'damaged',
          style:"Bearing"
        },
        {
          ID: 'cjx0902',
          type: 'Bearing',
          describe: '六号工厂生产',
          time: '2020-10-2',
          state: 'damaged',
          style:"Axle"
        },
        {
          ID: 'yzw9931',
          type: 'Bearing',
          describe: '二号工厂生产',
          time: '2020-1-2',
          state: 'damaged',
          style:"Bearing"
        },
        {
          ID: 'gjj20',
          type: 'Bearing',
          describe: '七号工厂生产',
          time: '2020-3-1',
          state: 'damaged',
          style:"Bearing"
        },
        {
          ID: 'jnx01010',
          type: 'Bearing',
          describe: '四号工厂生产',
          time: '2021-09-02',
          state: 'normal',
          style:"Axle"
        },
      ],
      showData: [
        {
          ID: 'admin',
          type: 'Bearing',
          describe: '一号工厂生产',
          time: '2021-10-2',
          state: 'normal',
          style:"Bearing"
        },
        {
          ID: 'yzw9931',
          type: 'Bearing',
          describe: '三号工厂生产',
          time: '2022-3-2',
          state: 'damaged',
          style:"Axle"
        },
      ],
    }
  },
  methods: {
    getUserList(a, b) {
      this.showData.length = []
      for (let i = 0; i < a; i++) {
        this.showData.push(this.tableData[i + b])
      }
    },
    SearchUser() {
      this.namestr = this.queryInfo.query
      let strlength = this.namestr.length
      this.showData = []
      console.log(this.showData)
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
     Sendmsg(row){
      this.$bus.$emit("msgname",row.name)
      this.$bus.$emit("msgstyle",row.style)
      this.$bus.$emit("msgtime",row.time)
      this.$router.push({
        path:"/componentview"
      })
    },
    //监听pagesize改变
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
    //监听页码值改变的时间
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
    deleteRow(index, rows) {
      rows.splice(index, 1)
    },
    IDchange(row) {
      row.ID = this.changedID
    },
    typechange(row) {
      row.type = this.changedtype
    },
    powerchange(row) {
      row.describe = this.changedes
    },
    timechange(row) {
      row.time = this.changedtime
    },
    statechange(row) {
      row.state = this.changedstate
    },
  },
}
</script>

<style lang="less" scoped>
</style>