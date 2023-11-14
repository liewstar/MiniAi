<template>
  <div>
    <div class="w-full p-2">
      <el-card>
        <el-form label-width="80px">
          <div class="flex">
            <el-form-item label="用户名">
              <el-input v-model="username"></el-input>
            </el-form-item>

            <el-form-item label="下单日期">
              <el-date-picker
                  type="date"
                  placeholder="选择日期">
              </el-date-picker>
            </el-form-item>

            <el-form-item label="套餐等级">
              <el-select>
                <el-option>基础版</el-option>
                <el-option>进阶版</el-option>
                <el-option>专业版</el-option>
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
        <el-pagination
            background
            layout="prev, pager, next"
            :total="1000">
        </el-pagination>
      </el-card>
    </div>
  </div>
</template>

<script>
export default {
  // eslint-disable-next-line vue/multi-word-component-names
  name: "Orders"
}
</script>

<style scoped>

</style>