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
             <el-button @click="newChat" class="right-20" style="background-color: black" ><i class="el-icon-circle-plus"></i> <span style="color: white">新的聊天</span></el-button>
        </form>

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
    newChat(){
      this.$router.push("/chat/sendMessage")
    },
    getAllConversation() {
      const userId = 1;
      api.post("/conversation/getConversation?userId=1",)
        .then((response) => {
          this.allConversations = response
        })
        .catch((error) => {
          console.log(userId)
          console.log(error)
        })
    }
  },
  created() {
    this.getAllConversation();
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