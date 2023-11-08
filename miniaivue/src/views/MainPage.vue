<template>
  <div class="mx-auto px-4 py-8" style="height: 830px;">
    <div class="grid grid-cols-12 gap-4" style="height: 100%;">
      <div class="col-span-2">
        <!-- 左侧历史消息对话框 -->
          <div style="height: 720px" class="bg-black rounded-lg p-4 shadow text-white overflow-y-auto scrollbar scrollbar-content">
            <div v-for="conversation in allConversations" :key="conversation.id" @click="clickConversation(conversation.id)">
              <Conversation :time="conversation.createdTime" :title="conversation.title"/>
            </div>
          </div>

        <form name="email-form" method="get" class="relative w-full mt-4">
             <el-button @click="toPersonal" style=" background-color: black;"><i class="el-icon-s-tools"></i></el-button>
             <el-button @click="dialogVisible = true" class="right-20" style="background-color: black" ><i class="el-icon-circle-plus"></i> <span style="color: white">新的聊天</span></el-button>
        </form>

        <el-dialog
            :visible.sync="dialogVisible"
            width="15%"

            >

<!--          <el-select v-model="selectPreset" class="p-4" lable="选择场景预设" >-->
<!--            <el-option v-for="(preset, index) in presets" :key="index" :value="preset.name" :lable="preset.name"></el-option>-->
<!--          </el-select>-->
          <el-button>选择场景</el-button>
          <el-button @click="newChat">直接开始</el-button>
          <span slot="footer" class="dialog-footer">
<!--    <el-button @click="dialogVisible = false">取 消</el-button>-->
<!--    <el-button type="primary" @click="dialogVisible = false">确 定</el-button>-->
  </span>
        </el-dialog>

      </div>
      <div style="height: 100%" class="col-span-10">
        <div class="h-full w-full bg-white rounded-lg p-4 shadow relative">
          <router-view/>
        </div>
      </div>
    </div>
  </div>

</template>

<script>
import Conversation from "@/components/Conversation";
import api from "@/api";
import axios from "axios";
export default {
  name: "MainPage",

  components: {Conversation},
  data() {
    return {
      allConversations:[
        {
          id:'',
          title:'',
          userId:'',
          createdTime:''
        }
      ],
      dialogVisible: false,
      presets:[],
      selectPreset:'',
    }
  },
  methods:{
    clickConversation(id){
      console.log(id)
      this.$router.push({
        path: "/chat/sendMessage",
        query:{
          conversationId: id
        }
      })
    },
    toPersonal(){
      this.$router.push("/chat/me")
    },
    readJson(){
      //读取预设文件到localStroge
      axios.get('/masks.json')
          .then(response => {
            // 解析后的 JSON 数据
            const data = response.data;
            // 在 Vue 组件中使用解析后的数据
            this.presets = data;
            console.log(this.presets)
          })
          .catch(error => {
            console.error('Error:', error);
          });
      this.$router.push("/chat/sendMessage")
    },
    newChat() {
      //新建会话
      api.post("/conversation/addConversation",null,{
        params: {
          userId: 1,
          title: '新的聊天'
        }
      }).then((response) => {
        console.log(response.status+"status")
        if(response.code === 200) {
          console.log("add success")
          this.dialogVisible = false;
          this.clickConversation(response.data.id)
        }
      })
      //进入新建的会话
    },
    getAllConversation() {
      const userId = 1;
      api.post("/conversation/getConversation?userId=1")
        .then((response) => {
          if(response.code === 200) {
            this.allConversations = response.data
          }else {
            this.$message({
              message: '网络错误，请刷新网页',
              type: "error"
            })
          }

        })
        .catch((error) => {
          console.log(userId)
          console.log(error)
        })
    }
  },
  created() {
    this.getAllConversation();
    this.readJson();
  }
}
</script>

<style scoped>
/* 模拟滚动条样式 */
.scrollbar {
  position: relative;
  overflow: hidden;
}

.scrollbar-content {
  height: 100%;
  overflow-y: scroll;
  scrollbar-width: thin;
  scrollbar-color: transparent transparent;
}

.scrollbar::-webkit-scrollbar {
  width: 4px; /* 调整滚动条宽度 */
}

.scrollbar::-webkit-scrollbar-track {
  background-color: #f1f1f1;
}

.scrollbar::-webkit-scrollbar-thumb {
  background-color: #888;
  border-radius: 0px; /* 调整滚动条圆角 */
}

.scrollbar::-webkit-scrollbar-thumb:hover {
  background-color: #555;
}
.el-button i {
  color: white;
}


</style>