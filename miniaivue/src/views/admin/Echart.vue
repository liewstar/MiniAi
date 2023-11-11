<template>
  <div>
        <div class="w-full p-2">
          <el-card>
            <el-row :gutter="20">
              <el-col :span="6">
                <div>
                  <el-statistic
                      group-separator=","
                      :precision="2"
                      :value="value2"
                      title="网站总用户"
                  ></el-statistic>
                </div>
              </el-col>
              <el-col :span="6">
                <div>
                  <el-statistic title="消息总记录数">
                    <template slot="formatter">
                      456/2
                    </template>
                  </el-statistic>
                </div>
              </el-col>
              <el-col :span="6">
                <div>
                  <el-statistic
                      group-separator=","
                      :precision="2"
                      decimal-separator="."
                      :value="value1"
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
                  <el-statistic :value="like ? 521 : 520" title="付费比例">
                    <template slot="suffix">
              <span @click="like = !like" class="like">
                <i
                    class="el-icon-star-on"
                    style="color:red"
                    v-show="!!like"
                ></i>
                <i class="el-icon-star-off" v-show="!like"></i>
              </span>
                    </template>
                  </el-statistic>
                </div>
              </el-col>
            </el-row>
          </el-card>
        </div>
        <div class="w-full p-2 h-screen">
          <el-card class="h-full">
            <line-chart></line-chart>
          </el-card>
        </div>
  </div>
</template>

<script>
import LineChart from "@/components/LineChart";
export default {
  // eslint-disable-next-line vue/multi-word-component-names
  name: "Echart",
  components: {LineChart},
  data() {
    return {
      like: true,
      value1: 4154.564,
      value2: 1314,
      title: "增长人数",
    };
  },
}
</script>

<style scoped>
.like {
  cursor: pointer;
  font-size: 25px;
  display: inline-block;
}
</style>