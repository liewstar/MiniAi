<template>
<div>
  <div class="ml-64 col-span-2 flex flex-col overflow-y-auto scrollbar scrollbar-content" style="width: 60%;height: 600px" ref="messageContainer">
    <div v-for="message in arrMessage" :key="message.id"  >
      <message :content="message.content"/>
    </div>

  </div>

  <div class="w-full absolute left-1/2 transform -translate-x-1/2 bottom-3">
    <div class="mx-auto mb-4 flex w-3/4 justify-center">
      <div class="relative w-full max-w-[75%]">
        <input v-model="msg" @keyup.enter="sendMsg" class="rounded-lg h-9 w-full border border-solid border-black bg-white px-3 py-6 text-sm text-[#333333]" placeholder="向MiniAi提问" />
        <input type="submit" @click="sendMsg" value="发送" class="rounded-lg relative right-0 top-[5px] w-full cursor-pointer bg-black px-6 py-2 text-center font-semibold text-white sm:absolute sm:right-[5px] sm:w-auto" />
      </div>
    </div>
  </div>
</div>
</template>

<script>
import Message from "@/components/Message";
export default {
  name: "SendMessage",
  components: {Message},
  methods:{
    sendMsg(){
      //准备消息体
      const message = {
        id: this.arrMessage.length+1,
        content: this.msg,
      };
      //发送消息体到数组
      this.arrMessage.push(message);
      //清空输入框
      this.msg = ''
      // 更新消息列表后滚动到底部
      this.$nextTick(() => {
        const container = this.$refs.messageContainer;
        container.scrollTop = container.scrollHeight;
      });
    },
  },
  data(){
    return{
      msg:'',
      arrMessage: [],
    }
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


</style>