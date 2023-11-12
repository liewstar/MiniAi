<template>
<div>
  <div class="ml-64 col-span-2 flex flex-col overflow-y-auto scrollbar scrollbar-content" style="width: 60%;height: 600px" ref="messageContainer">
    <div v-for="message in arrMessage" :key="message.indexId" class="w-full" >
      <message :is-user="message.userId" :content="message.content"/>
    </div>
  </div>

  <div class="w-full absolute left-1/2 transform -translate-x-1/2 bottom-3">
    <div class="mx-auto mb-4 flex w-3/4 justify-center">
      <div class="relative w-full max-w-[75%]">
        <input v-model="msg" class="rounded-lg h-9 w-full border border-solid border-black bg-white px-3 py-6 text-sm text-[#333333]" placeholder="向MiniAi提问(按下ctrl+enter发送)" />
        <input type="submit" @click="sendMsg" value="发送" class="rounded-lg relative right-0 top-[5px] w-full cursor-pointer bg-black px-6 py-2 text-center font-semibold text-white sm:absolute sm:right-[5px] sm:w-auto" />
      </div>
    </div>
  </div>
</div>
</template>

<script>
import Message from "@/components/Message";
import { fetchEventSource } from '@microsoft/fetch-event-source';
import api from "@/api";
export default {
  name: "SendMessage",
  components: {Message},
  watch: {
    '$route.query.conversationId': {
      handler(newQuery, oldQuery) {
        console.log("oldQuery:"+oldQuery+" newQuery:"+newQuery)
        this.conversationId = newQuery
        this.getAllMessage(newQuery)
        this.changeSet()
        this.$nextTick(() => {
          const container = this.$refs.messageContainer;
          container.scrollTop = container.scrollHeight;
        });
      },
      deep: true
    }
  },
  methods:{
    // handleKeyDown(event) {
    //   if(event.ctrlKey && event.key == 'enter') {
    //     this.sendMsg()
    //   }
    // },
    sendMsg(){
      if(this.addTitle === 0 && this.msg !== '') {
        //修改标题
        const title = this.msg.substr(0,8)
          api.post("/conversation/changeConversation?conversationId="+this.conversationId+"&title="+title)
        .then(response => {
          if(response.code !== 200) {
            this.$message.error("出错了，请刷新重试")
          }
        })
      }
      //验证携带历史消息数
      //TODO 验证是否有预设，排除预设
      let messageListLength = this.messageBody.messageList.length - this.presetIndex
      if(messageListLength > this.takeMessages * 2) {
        this.messageBody.messageList.splice(this.presetIndex,messageListLength-this.takeMessages*2)
      }
      console.log(this.messageBody.messageList)
      //先查数据库，赋初值
      //user和bot消息顺序都根据id来
      //消息发送的user位置
      let sendBody = {
        id:'',
        role:'user',
        content:this.msg,
        date:''
      }
      this.messageBody.messageList.push(sendBody)
      //发送临时数据到聊天记录数组
      let indexId=0;
      if(this.arrMessage.length === 0) {
        indexId = 0
      }else {
       indexId = this.arrMessage[this.arrMessage.length-1].indexId+1
      }
      const sendMessage = {
        id:0,
        indexId:indexId,
        conversationId:0,
        userId:0,
        content:this.msg,
        timestamp:''
      }
      this.arrMessage.push(sendMessage);
      //清空输入框
      this.msg = ''



      this.messageBody.conversationId = this.conversationId;
      //发送消息，得到流式响应
      localStorage.setItem(new Date(),new Date())
      let botIndex = indexId +1
      const botMessage = {
        id:0,
        indexId:botIndex,
        conversationId:0,
        userId:null,
        content:"正在生成...",
        timestamp:''
      }
      const that = this
      this.arrMessage.push(botMessage)
      let botContent = '';
      that.ctrlAbout = new AbortController();


      //eslint-disable-next-line no-unused-vars
      const eventSource = new fetchEventSource('http://localhost:8081/chat/sse', {
        method: 'POST',
        headers: {
          'Content-Type':'application/json',
          'token':localStorage.getItem('MiniAiToken')
        },
        signal: that.ctrlAbout.signal,
        openWhenHidden: true,
        body: JSON.stringify(this.messageBody),

        onopen(response) {
          console.log('event open' +response)
        },
        onmessage(event) {
            let resData = JSON.parse(event.data)
            botContent += resData.content
            that.arrMessage[botIndex].content = botContent
            console.log(botContent)
          console.log('eventSource msg: ', event.data);
          that.$nextTick(() => {
            const container = that.$refs.messageContainer;
            container.scrollTop = container.scrollHeight;
          });
        },
        onerror(err) {
          that.ctrlAbout.abort()
          that.arrMessage.splice(botIndex,1)
          that.$message.error("出现错误，非会员请检查token和接口后重试")
          console.log('eventSource error: ' + err);
          eventSource.close()
        },
        onclose() {
          that.ctrlAbout.abort()

          let botBody = {
            id:'',
            role:'assistant',
            content:botContent,
            date:''
          }
          that.messageBody.messageList.push(botBody)
          if(botContent.length < 2) {
            that.$message.error("出现错误，非会员请检查token和接口后重试")
          }
          console.log('eventSource close');
        }
      });

      eventSource.catch(error => {
        console.log(""+error)
      })


      // 更新消息列表后滚动到底部
      this.$nextTick(() => {
        const container = this.$refs.messageContainer;
        container.scrollTop = container.scrollHeight;
      });
    },
    //获取聊天记录
    getAllMessage(conversationId) {
      api.post("/message/getMessage?conversationId="+conversationId)
        .then((response) => {
          if(response.code === 200) {
            this.arrMessage = response.data.map((message, index) => {
              return {
                ...message,
                indexId: index
              }
            })
            console.log(this.arrMessage)
          }else {
            this.$message({
              message: '聊天记录获取失败',
              type: "error"
            })
          }

        })
        .catch((error) => {
          console.log(error)
        })
    },
    changeSet() {
      const settingsData = localStorage.getItem("settings")
      const settings = JSON.parse(settingsData)
      if(settings) {
        this.messageBody.token = settings.token;
        this.messageBody.endpoint = settings.endpoint;
        this.messageBody.model = settings.model;
        this.messageBody.maxToken = settings.maxToken;
        this.messageBody.temperature = settings.temperature;
        this.messageBody.presencePenalty = settings.presencePenalty;
        this.messageBody.frequencyPenalty = settings.frequencyPenalty;
        this.takeMessages = settings.takeMessages
      }

      //先清空，后面再赋干净的预设
      this.messageBody.messageList.length = 0

      const presetData = localStorage.getItem("preset")
      const preset = JSON.parse(presetData)
      let copyPreset = preset
      if(copyPreset) {
        this.messageBody.messageList = copyPreset
        this.presetIndex = copyPreset.length
        //删除预设记录
        localStorage.removeItem("preset")
      }
    }
  },
  mounted() {
    this.getAllMessage(this.$route.query.conversationId);
    this.conversationId = this.$route.query.conversationId
    //判断是否加标题
    if(this.arrMessage.length === 0) {
        this.addTitle = 1
    }

  },
  created() {
    this.changeSet()
  },
  data(){
    return{
      takeMessages: 0,
      addTitle:0,
      msg:'',
      presetIndex:0,
      //聊天记录
      arrMessage:[
        {
          id:0,
          conversationId:0,
          userId:"",
          content:'',
          timestamp:''
        }
      ],
      messageBody: {
        userId: localStorage.getItem("MiniAiUserId"),
        conversationId: 0,
        token: "",
        endpoint: "https://ab.nextweb.fun/api/proxy/",
        model: "gpt-3.5-turbo",
        maxToken: 2000,
        temperature: 0.5,
        presencePenalty: 0.0,
        frequencyPenalty: 0.0,
        messageList: []
      }

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