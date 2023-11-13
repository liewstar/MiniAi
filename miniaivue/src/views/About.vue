<template>
  <div class="p-8 flex justify-center items-center">

    <el-timeline class="w-1/2">
      <el-skeleton :rows="15" :loading="loading" animated />
        <el-timeline-item :timestamp="commit.commit.author.date.substring(0,10)" placement="top" v-for="(commit, index) in commits" :key="index">
          <div @click="toGithub(commit.html_url)">
            <el-card>
              <h4>{{commit.commit.message}}</h4>
              <div style="display: flex; align-items: center;" class="mt-1">
                <el-avatar :size="30" :src="commit.author.avatar_url"></el-avatar>
                <p class="ml-1">{{commit.author.login}} 提交于 {{commit.commit.author.date}}</p>
              </div>
            </el-card>
          </div>

        </el-timeline-item>
    </el-timeline>
  </div>
</template>

<script>
import axios from "axios";
export default {
  // eslint-disable-next-line vue/multi-word-component-names
  name: "About",
  data() {
    return{
      content: '# 123',
      commits:[],
      loading:true,
    }
  },
  methods:{
    toGithub(url) {
      window.open(url)
    }
  },
  mounted() {
    axios.get("https://api.github.com/repos/liewstar/MiniAi/commits?page=1&per_page=200",{
      headers:{
        "Accept":'application/vnd.github+json',
        "Authorization":process.env.VUE_APP_GITHUB_KEY,
        "X-GitHub-Api-Version":'2022-11-28'
      }
    })
    .then(response => {
      if(response.status === 200) {
        console.log(response)
        this.loading = false
        this.commits = response.data
        for(let i=0;i<this.commits.length;i++) {
          let utcTime = new Date(this.commits[i].commit.author.date);
          let beijingTime = utcTime.toLocaleString('zh-CN', { timeZone: 'Asia/Shanghai' });
          this.commits[i].commit.author.date = beijingTime
        }
      }else {
        this.$message.error("网络错误")
      }
    })
    .catch(error => {
      this.$message.error(error)
    })
  }
}
</script>

<style scoped>

</style>