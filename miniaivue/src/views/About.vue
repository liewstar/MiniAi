<template>
  <div class="flex flex-col space-y-2">
    <div class="flex items-start">
      <div class="bg-blue-500 text-white rounded-lg p-2">
        <p>Person A: Hello!</p>
      </div>
    </div>
    <div class="flex items-end">
      <div class=" bg-gray-800 text-white rounded-lg p-2">
        <p>Person B: Hi there!</p>
      </div>
    </div>
    <MainPage/>
    <!-- 添加更多消息框 -->
  </div>
</template>

<script>
import MainPage from "@/views/MainPage";
export default {
  // eslint-disable-next-line vue/multi-word-component-names
  name: "About",
  components: {MainPage}
}
</script>

<style scoped>

</style>