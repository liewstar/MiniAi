<template>



  <div>
    <div>
      <div class="w-full p-2">
        <el-card>
          <el-form :label-position="labelPosition" label-width="80px" :model="formLabelAlign">
            <el-form-item label="名称">
              <el-input ></el-input>
            </el-form-item>
          </el-form>
        </el-card>
      </div>
      <div class="w-full p-2 h-screen">
        <el-card class="h-full">123</el-card>
      </div>

    </div>
  </div>

<!--  <div class="flex justify-center items-center h-screen p-4">-->
<!--    <el-card class="w-full h-full">-->
<!--      <div>-->
<!--        <el-row :gutter="20">-->
<!--          <el-col :span="6">-->
<!--            <div class="grid-content bg-purple">-->
<!--              <el-row :gutter="8">-->
<!--                <el-col :span="5">-->
<!--                  用户姓名-->
<!--                </el-col>-->
<!--                <el-col :span="12">-->
<!--                  <el-input></el-input>-->
<!--                </el-col>-->
<!--                <el-col :span="6">-->
<!--                  <el-button>查询</el-button>-->
<!--                </el-col>-->
<!--              </el-row>-->
<!--            </div>-->
<!--          </el-col>-->
<!--          <el-col :span="6"><div class="grid-content bg-purple"></div></el-col>-->
<!--          <el-col :span="6"><div class="grid-content bg-purple"></div></el-col>-->
<!--          <el-col :span="6"><div class="grid-content bg-purple"></div></el-col>-->
<!--        </el-row>-->

<!--        <div>-->
<!--          <el-table-->
<!--              :data="tableData"-->
<!--              style="width: 100%">-->
<!--            <el-table-column-->
<!--                label="日期"-->
<!--                width="180">-->
<!--              <template slot-scope="scope">-->
<!--                <i class="el-icon-time"></i>-->
<!--                <span style="margin-left: 10px">{{ scope.row.date }}</span>-->
<!--              </template>-->
<!--            </el-table-column>-->
<!--            <el-table-column-->
<!--                label="姓名"-->
<!--                width="180">-->
<!--              <template slot-scope="scope">-->
<!--                <el-popover trigger="hover" placement="top">-->
<!--                  <p>姓名: {{ scope.row.name }}</p>-->
<!--                  <p>住址: {{ scope.row.address }}</p>-->
<!--                  <div slot="reference" class="name-wrapper">-->
<!--                    <el-tag size="medium">{{ scope.row.name }}</el-tag>-->
<!--                  </div>-->
<!--                </el-popover>-->
<!--              </template>-->
<!--            </el-table-column>-->
<!--            <el-table-column label="操作">-->
<!--              <template slot-scope="scope">-->
<!--                <el-button-->
<!--                    size="mini"-->
<!--                    @click="handleEdit(scope.$index, scope.row)">编辑</el-button>-->
<!--                <el-button-->
<!--                    size="mini"-->
<!--                    type="danger"-->
<!--                    @click="handleDelete(scope.$index, scope.row)">删除</el-button>-->
<!--              </template>-->
<!--            </el-table-column>-->
<!--          </el-table>-->
<!--        </div>-->
<!--      </div>-->
<!--    </el-card>-->
<!--  </div>-->

</template>

<script>
export default {
  // eslint-disable-next-line vue/multi-word-component-names
  name: "Users",
  data() {
    return {
      tableData: [{
        date: '2016-05-02',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
      }, {
        date: '2016-05-04',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1517 弄'
      }, {
        date: '2016-05-01',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1519 弄'
      }, {
        date: '2016-05-03',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1516 弄'
      }]
    }
  },
  methods: {
    handleEdit(index, row) {
      console.log(index, row);
    },
    handleDelete(index, row) {
      console.log(index, row);
    }
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