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
          <el-button @click="choosePreset">选择场景</el-button>
          <el-button @click="newChat">直接开始</el-button>
          <span slot="footer" class="dialog-footer">
          </span>
        </el-dialog>

        <el-dialog
            :visible.sync="presetDialogvisiable"
            width="20%"
            >
          <el-row>
            <el-col v-for="(preset, index) in presets" :key="index">
              <el-button class="w-full text-center mt-4" :value="preset.name" @click="chooseOnePreset(index)">
                {{preset.name}}
              </el-button>
            </el-col>
          </el-row>

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
          id:0,
          title:'',
          userId:'',
          createdTime:''
        }
      ],
      dialogVisible: false,
      presets:[],
      selectPreset:'',
      presetDialogvisiable: false
    }
  },
  mounted() {
    setTimeout(() => {
      // 在此处访问已经赋值的数组
      this.getMaxConversationId()
      // 执行其他逻辑
    }, 1000);
  },
  methods:{
    getMaxConversationId() {
      let maxId = 0;
      for (let i = 0; i < this.allConversations.length; i++) {
          const conversation = this.allConversations[i];
          if(conversation.id > maxId) {
            maxId = conversation.id
          }
      }
      this.clickConversation(maxId)
    },
    chooseOnePreset(index) {
      this.presetDialogvisiable = false;
      //设置预设，新建预设的会话（新建一个表，关联对话表和预设index）
      localStorage.setItem("preset",JSON.stringify(this.presets[index].context))
      this.newChat()
    },
    choosePreset() {
      this.dialogVisible = false
      this.presetDialogvisiable = true;
    },
    clickConversation(id){
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
          })
          .catch(error => {
            console.error('Error:', error);
          });
      this.$router.push("/chat/sendMessage")
    },
    newChat() {
      const userId = localStorage.getItem("MiniAiUserId")
      //新建会话
      api.post("/conversation/addConversation",null,{
        params: {
          userId: userId,
          title: '新的聊天'
        }
      }).then((response) => {
        if(response.code === 200) {
          //刷新会话
          this.getAllConversation();
          this.dialogVisible = false;
          //进入了新的会话
          this.clickConversation(response.data.id)
        }
      })
    },
    getAllConversation() {
      const userId = localStorage.getItem("MiniAiUserId");
      api.post("/conversation/getConversation?userId="+userId)
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