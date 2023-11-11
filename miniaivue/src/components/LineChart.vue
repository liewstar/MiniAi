<template>
  <div>
    <div class=" w-screen" style="height:700px">
      <div id="echart"></div>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {};
  },
  // 页面初始化挂载dom
  mounted() {
    this.getLoadEcharts();
  },
  methods: {
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
          data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
        },
        yAxis: {
          type: 'value'
        },
        series: [
          {
            data: [820, 932, 901, 934, 1290, 1330, 1320],
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