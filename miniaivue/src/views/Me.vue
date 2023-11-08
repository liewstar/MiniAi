<template>
  <div class="h-full ml-64 col-span-2 flex flex-col overflow-y-auto scrollbar scrollbar-content" style="width: 40%;">
<!--    <el-table :data="tableData" style="width: 100%">-->
<!--      <el-table-column prop="name"></el-table-column>-->
<!--      <el-table-column prop="value">-->
<!--        <template slot-scope="scope">-->
<!--          <el-input v-if="scope.row.name === '接口地址'" v-model="scope.row.value"></el-input>-->
<!--          <el-input type="password" v-else-if="scope.row.name === 'API Key'" v-model="scope.row.value"></el-input>-->
<!--          <el-select v-else-if="scope.row.name === '模型'" v-model="scope.row.value"></el-select>-->
<!--          <el-slider :min="-2" :max="2" :step="0.1" v-else-if="scope.row.name === '随机性'" v-model="scope.row.value"></el-slider>-->
<!--          <el-slider :min="-2" :max="2" :step="0.1" v-else-if="scope.row.name === '话题新鲜度'" v-model="scope.row.value"></el-slider>-->
<!--        </template>-->
<!--      </el-table-column>-->
<!--    </el-table>-->

    <el-form ref="settingForm" label-width="120px">
      <el-form-item label="接口地址">
        <el-input v-model="tableData.endpoint"></el-input>
      </el-form-item>

      <el-divider></el-divider>

      <el-form-item label="API Key">
        <el-input v-model="tableData.token"></el-input>
      </el-form-item>

      <el-divider></el-divider>

      <el-form-item label="模型">
        <el-select v-model="tableData.model" placeholder="请选择模型">
          <el-option value="gpt-3.5-turbo"></el-option>
          <el-option value="gpt-3.5-turbo-0301"></el-option>
          <el-option value="gpt-4"></el-option>
          <el-option value="gpt-4-0314"></el-option>
          <el-option value="gpt-4-32k"></el-option>
          <el-option value="gpt-4-32k-0314"></el-option>
        </el-select>
      </el-form-item>

      <el-divider></el-divider>

      <el-form-item label="随机性">
        <el-slider :min="0" :max="1" :step="0.1" v-model="tableData.temperature"></el-slider>
      </el-form-item>

      <el-divider></el-divider>

      <el-form-item label="降低重复度">
        <el-slider :min="-2" :max="2" :step="0.1" v-model="tableData.frequencyPenalty"></el-slider>
      </el-form-item>

      <el-divider></el-divider>

      <el-form-item label="话题新鲜度">
        <el-slider :min="-2" :max="2" :step="0.1" v-model="tableData.presencePenalty"></el-slider>
      </el-form-item>

      <el-divider></el-divider>

      <el-form-item label="单次回复限制">
        <el-input v-model="tableData.maxToken"></el-input>
      </el-form-item>

      <el-divider></el-divider>

      <el-form-item label="附带历史消息数">
        <el-slider :min="0" :max="32" :step="1" v-model="tableData.takeMessages"></el-slider>
      </el-form-item>

      <el-divider></el-divider>

      <el-form-item>
        <el-button type="primary" style="background-color: black" @click="saveSettings">保存</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  // eslint-disable-next-line vue/multi-word-component-names
  name: "Me",
  data() {
    return {
      tableData: {
        endpoint:'',
        token:'',
        model:'gpt-3.5-turbo',
        maxToken:2000,
        temperature:0.5,
        presencePenalty:0.0,
        frequencyPenalty:0.0,
        takeMessages:4,
      }
    }
  },
  methods:{
    saveSettings() {
      localStorage.setItem("settings",JSON.stringify(this.tableData))
      this.$message({
        message: '保存成功',
        type: 'success'
      })
    }
  }
}
</script>

<style scoped>

</style>