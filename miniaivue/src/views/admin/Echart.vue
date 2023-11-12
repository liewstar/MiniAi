<template>
  <div>
        <div class="w-full p-2">
          <el-card>
            <el-row :gutter="20">
              <el-col :span="6">
                <div>
                  <el-statistic
                      :value="info.allPeople"
                      title="网站总用户"
                  ></el-statistic>
                </div>
              </el-col>
              <el-col :span="6">
                <div>
                  <el-statistic
                      title="消息总记录数"
                      :value="info.allMessage"
                  ></el-statistic>
                </div>
              </el-col>
              <el-col :span="6">
                <div>
                  <el-statistic
                      :value="info.allPay"
                      title="付费用户数"
                  >
                    <template slot="prefix">
                      <i class="el-icon-s-flag" style="color: red"></i>
                    </template>
                    <template slot="suffix">
                      <i class="el-icon-s-flag" style="color: blue"></i>
                    </template>
                  </el-statistic>
                </div>
              </el-col>
              <el-col :span="6">
                <div>
                  <el-statistic :value="info.payPercent"
                                :precision="2"
                                title="付费比例">
                    <template slot="suffix">
                <i
                    class="el-icon-star-on"
                    style="color:red"
                ></i>
                    </template>
                  </el-statistic>
                </div>
              </el-col>
            </el-row>
          </el-card>
        </div>
        <div class="w-full p-2 h-screen">
          <el-card class="h-full">
            <el-date-picker
                class="ml-32 mb-8"
                v-model="date"
                align="right"
                type="date"
                placeholder="选择日期"
                >
            </el-date-picker>
            <line-chart :date="date"></line-chart>
          </el-card>
        </div>
  </div>
</template>

<script>
import api from "@/api";
import LineChart from "@/components/LineChart";
export default {
  // eslint-disable-next-line vue/multi-word-component-names
  name: "Echart",
  components: {LineChart},
  data() {
    return {
      info:
        {
          allPeople: 0,
          allMessage: 0,
          allPay: 0,
          payPercent: 0,
        },
      date:new Date()
    };
  },
  methods:{
    getCount(){
      api.post("/count/countInformation")
      .then(response =>{
        if(response.code === 200) {
          this.info = response.data
        }else {
          this.$message.error("网络错误，请刷新");
        }
      })
    }
  },
  mounted() {
    this.getCount()
    console.log(this.date)
  }
}
</script>

<style scoped>

</style>