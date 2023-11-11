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
export default {
  name: "AdminMain"
}
</script>

<style scoped>

</style>