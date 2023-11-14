<template>
  <div>
    <div class=" w-screen" style="height:600px">
      <div id="echart"></div>
    </div>
  </div>
</template>
<script>

import api from "@/api";

export default {
  data() {
    return {
      x:[],
      y:[]
    };
  },
  props:{
    date:{
      type:Date,
      required:true
    }
  },

  watch: {
    date(newValue, oldValue) {
      // 执行更新操作
      console.log('old:'+oldValue+" new:"+newValue)
      this.x.length = 0
      this.y.length = 0
      this.getEchart()
    }
  },
  // 页面初始化挂载dom
  mounted() {
    console.log(this.date+"componet")
    this.getEchart()

  },
  methods: {
    getEchart() {
      api.post("/message/statistics?date="+this.date.toLocaleString('zh-CN', { timeZone: 'Asia/Shanghai' }).toString().substring(0,10))
          .then(response => {
            if(response.code === 200) {
              let data = response.data;
              for(let i =0; i< data.length;i++) {
                this.x.push(data[i].hour);
                this.y.push(data[i].message_count);
                this.getLoadEcharts();
              }
            }else {
              this.$message.error("网络错误")
            }
          })
    },
    getLoadEcharts() {
      var myChart = this.$echarts.init(
          document.getElementById("echart")
      );
      var option = {
        title: {
          text: '每小时访问量'
        },
        xAxis: {
          type: 'category',
          data: this.x
        },
        yAxis: {
          type: 'value'
        },
        series: [
          {
            data: this.y,
            type: 'line',
            smooth: true,
          }
        ]
      };
      myChart.setOption(option);
    }
  }
};
</script>
<style scoped>
.container{
  width: 300px;
  height: 300px;
  margin-left: 30px;
}
#echart{
  width: 100%;
  height: 100%;
}
</style>