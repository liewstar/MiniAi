<template>
  <div class="ml-64 col-span-2 flex flex-col overflow-y-auto scrollbar scrollbar-content" style="width: 60%;height: 600px">
    <el-table :data="tableData" style="width: 100%">
      <el-table-column prop="name"></el-table-column>
      <el-table-column prop="value">
        <template slot-scope="scope">
          <el-input v-if="scope.row.name === '接口地址'" v-model="scope.row.value"></el-input>
          <el-input type="password" v-else-if="scope.row.name === 'API Key'" v-model="scope.row.value"></el-input>
          <el-select v-else-if="scope.row.name === '模型'" v-model="scope.row.value"></el-select>
          <el-slider :min="-2" :max="2" :step="0.1" v-else-if="scope.row.name === '随机性'" v-model="scope.row.value"></el-slider>
          <el-slider :min="-2" :max="2" :step="0.1" v-else-if="scope.row.name === '话题新鲜度'" v-model="scope.row.value"></el-slider>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
export default {
  // eslint-disable-next-line vue/multi-word-component-names
  name: "Me",
  data() {
    return {
      tableData: [
        { name: '接口地址', value: '' },
        { name: 'API Key', value: '' },
        { name: '模型', value: '' },
        { name: '随机性', value: 0 },
        { name: '话题新鲜度', value: 0 },
      ]
    }
  }
}
</script>

<style scoped>

</style>