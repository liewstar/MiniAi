<template>
<div>
  <div class="ml-64 col-span-2 flex flex-col overflow-y-auto scrollbar scrollbar-content" style="width: 60%;height: 600px">
    <message/>
    <message/>
    <message/>
    <message/>
  </div>

  <div class="w-full absolute left-1/2 transform -translate-x-1/2 bottom-3">
    <div class="mx-auto mb-4 flex w-3/4 justify-center">
      <form name="email-form" method="get" class="relative w-full max-w-[75%]">
        <input type="email" class="rounded-lg h-9 w-full border border-solid border-black bg-white px-3 py-6 text-sm text-[#333333]" placeholder="向MiniAi提问" required="" />
        <input type="submit" value="发送" class="rounded-lg relative right-0 top-[5px] w-full cursor-pointer bg-black px-6 py-2 text-center font-semibold text-white sm:absolute sm:right-[5px] sm:w-auto" />
      </form>
    </div>
  </div>
</div>
</template>

<script>
import Message from "@/components/Message";
export default {
  name: "SendMessage",
  components: {Message}
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