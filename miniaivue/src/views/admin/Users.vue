<template>
  <div>
      <div class="w-full p-2">
        <el-card>
          <el-form label-width="80px">
            <div class="flex">
              <el-form-item label="用户名">
                <el-input v-model="username"></el-input>
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
                label="姓名"
                width="180">
            </el-table-column>
            <el-table-column
                prop="email"
                label="邮箱"
                width="180">
            </el-table-column>
            <el-table-column
                prop="balance"
                label="余额">
            </el-table-column>
            <el-table-column label="操作">
              <template slot-scope="scope">
                <el-button type="primary" @click="handleEdit(scope.row)">修改信息</el-button>
                <el-button type="danger" @click="handleDelete(scope.row)">删除</el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-card>
      </div>
  </div>

</template>

<script>
import api from "@/api";

export default {
  // eslint-disable-next-line vue/multi-word-component-names
  name: "Users",
  data() {
    return {
      username:'',
      tableData: [
        {
          id:1,
          email: '2935437378@qq.com',
          username: '王小虎',
          balance: 562
        },
      ]
    }
  },
  methods: {
    handleEdit(index, row) {
      console.log(index, row);
    },
    handleDelete(index, row) {
      console.log(index, row);
    },
    getData() {
      api.post("/users/selectUsers?username="+this.username)
      .then(response => {
        if(response.code === 200) {
          this.tableData = response.data
          for(let i =0; i< this.tableData.length; i++) {
            if(this.tableData[i].balance === null) {
              this.tableData[i].balance = 0
            }
            if(this.tableData[i].email === null) {
              this.tableData[i].email = "暂未设置"
            }
          }
        }else {
          this.$message.error("网络错误")
        }
      })
      .catch(error => {
        this.$message.error(error)
      })
    }
  },
  mounted() {
    this.getData()
  }
}
</script>

<style scoped>
.top-card {
  background-color: #f0f0f0;
}

.bottom-card {
  background-color: #e0e0e0;
}
</style>