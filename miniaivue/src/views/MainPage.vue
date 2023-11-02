<template>
  <div class="m-h-screen mx-auto px-4 py-8">
    <div class="grid grid-cols-12 gap-4">
      <div class="col-span-3">
        <!-- 左侧历史消息对话框 -->
        <div class="h-screen bg-white rounded-lg p-4 shadow">
          <Conversation class="border border-slate-800" :time="time" :title="title"/>
          <Conversation class="border border-slate-800" :time="time" :title="title"/>
        </div>


      </div>
      <div class="col-span-9">
        <!-- 右侧内容 -->
        <div class="bg-white rounded-lg p-4 shadow">
          <!-- 右侧内容 -->
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Conversation from "@/components/Conversation";
export default {
  name: "MainPage",
  components: {Conversation},
  data() {
    return {
      time: "2023-12-11",
      title: "title"

    }
  }
}
</script>

<style scoped>

</style>