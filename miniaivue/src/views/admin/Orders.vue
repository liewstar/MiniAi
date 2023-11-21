<template>
  <div>
    <div class="w-full p-2">
      <el-card>
        <el-form label-width="80px" v-model="searchData">
          <div class="flex">
            <el-form-item label="用户名">
              <el-input v-model="searchData.username"></el-input>
            </el-form-item>

            <el-form-item label="下单日期">
              <el-date-picker
                  v-model="searchData.orderDate"
                  type="date"
                  placeholder="选择日期">
              </el-date-picker>
            </el-form-item>

            <el-form-item label="套餐等级">
              <el-select v-model="searchData.orderAmount">
                <el-option label="基础版" value="99"></el-option>
                <el-option label="进阶版" value="199"></el-option>
                <el-option label="专业版" value="299"></el-option>
              </el-select>
            </el-form-item>
            <div class="ml-4">
              <el-button class="" type="primary" @click="getData">查找</el-button>
            </div>
          </div>

        </el-form>

      </el-card>
    </div>
    <div class="w-full p-2 h-screen" >
      <el-card class="h-full" style="overflow: auto;">
        <el-table
            :data="tableData"
            style="width: 100%">
          <el-table-column
              prop="username"
              label="用户名"
              width="180">
          </el-table-column>
          <el-table-column
              prop="orderDate"
              label="下单日期"
              :formatter="formateOrderDate"
              width="180">
          </el-table-column>
          <el-table-column
              prop="orderAmount"
              width="180"
              :formatter="formatOrderAmount"
              label="套餐等级">
          </el-table-column>
          <el-table-column
              prop="endDate"
              width="180"
              :formatter="formateEndDate"
              label="到期时间">
          </el-table-column>
          <el-table-column label="操作">
            <template slot-scope="scope">
<!--              <el-button type="primary" @click="handleEdit(scope.row)">修改信息</el-button>-->
              <el-button type="danger" @click="handleDelete(scope.row)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
<!--        <el-pagination-->
<!--            background-->
<!--            layout="prev, pager, next"-->
<!--            :total="1000">-->
<!--        </el-pagination>-->
      </el-card>
    </div>
  </div>
</template>

<script>
import api from '@/api.js'
import {MessageBox} from "element-ui";
export default {
  // eslint-disable-next-line vue/multi-word-component-names
  name: "Orders",
  data() {
    return{
      searchData:{
        username:'',
        orderDate:'',
        orderAmount:''
      },
      tableData:[
        {
          id:'',
          username:'',
          orderDate:'',
          orderAmount:'',
          endDate:'',
        }
      ]
    }
  },
  methods:{
    handleEdit(index) {
      console.log(index)
    },
    handleDelete(index) {
      console.log(index.id)
      MessageBox.confirm('确定要删除吗？','提示',{
        confirmButtonText:'确定',
        cancelButtonText:'取消',
        type: 'warning'
      }).then(() => {
        //删除用户
        api.post("/order/delete?orderId="+index.id)
            .then(response => {
              if(response.code === 200) {
                this.$message.success("删除成功")
                this.getData()
              }else {
                this.$message.error("获取数据失败")
              }
            }).catch(error => {
          this.$message.error("网络错误"+error)
        })
      }).catch(() => {
        this.$message.info("取消删除")
      })




    },
    formatOrderAmount(row) {
      const orderAmount = row.orderAmount
      let displayText = '';
      switch (orderAmount) {
        case 99:
          displayText = '基础版';
          break;
        case 199:
          displayText = '进阶版';
          break;
        case 299:
          displayText = '专业版';
          break;
      }
      return displayText
    },
    formateOrderDate(row) {
      const date = row.orderDate
      return date.toLocaleString('zh-CN', { timeZone: 'Asia/Shanghai' }).toString().substring(0,10)

    },
    formateEndDate(row) {
      const date = row.endDate
      return date.toLocaleString('zh-CN', { timeZone: 'Asia/Shanghai' }).toString().substring(0,10)

    },
    getData() {
      api.post("/order/selectOrder",this.searchData,{
        headers: {
          'Content-Type':'application/json'
        }
      }).then(response => {
        if(response.code === 200) {
          this.tableData = response.data
        }else {
          this.$message.error("获取数据失败")
        }
      }).catch(error => {
        this.$message.error("网络错误"+error)
      })
    }
  },
  mounted() {
    this.getData()
  }
}
</script>

<style scoped>

</style>