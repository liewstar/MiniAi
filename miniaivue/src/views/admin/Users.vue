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


    <el-dialog title="修改信息" :visible.sync="dialogFormVisible">
      <el-form :model="form">
        <el-form-item label="用户名" :label-width="formLabelWidth">
          <el-input v-model="form.username" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="邮箱" :label-width="formLabelWidth">
          <el-input v-model="form.email" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="余额" :label-width="formLabelWidth">
          <el-input v-model="form.balance" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="密码" :label-width="formLabelWidth">
          <el-input type="password" v-model="form.password" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="makeSure">确 定</el-button>
      </div>
    </el-dialog>
  </div>

</template>

<script>
import api from "@/api";
import { MessageBox } from 'element-ui';

export default {
  // eslint-disable-next-line vue/multi-word-component-names
  name: "Users",
  data() {
    return {
      form: {
        id:0,
        username: '',
        email:'',
        balance:0,
        password:''
      },
      formLabelWidth: '120px',
      username:'',
      dialogFormVisible: false,
      tableData: [
        {
          id:0,
          email: '',
          username: '',
          balance: 0
        },
      ]
    }
  },
  methods: {
    makeSure() {
      this.dialogFormVisible = false
      api.post("/users/changeInfo",this.form,{
        headers: {
          'Content-Type':'application/json'
        }
      }).then(response => {
        if(response.code === 200) {
          this.$message.success("信息修改成功")
          this.getData()
        }else {
          this.$message.error("信息修改失败，用户名已经存")
        }
      }).catch(error => {
        this.$message.error("网络错误，请联系管理员"+error)
      })
    },
    handleEdit(index) {
      this.dialogFormVisible = true
      if(index.email === '暂未设置') {
        this.form.email = ''
      }else {
        this.form.email = index.email
      }
      this.form.password = index.password
      this.form.balance = index.balance
      this.form.username = index.username
      this.form.id = index.id



    },
    handleDelete(index) {
      var that = this
      MessageBox.confirm('确定要删除吗？','提示',{
        confirmButtonText:'确定',
        cancelButtonText:'取消',
        type: 'warning'
      }).then(() => {
        //删除用户
        api.post("/users/delete?userId="+index.id)
        .then(response => {
          if(response.code === 200) {
            this.$message.success("删除成功")
            this.getData()
          }else {
            this.$message.error("删除失败")
          }
        })
        .catch(error => {
          that.$message.error("发生错误，请联系管理员"+error)
        })
      }).catch(() => {
        this.$message.info("取消删除")
      })
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