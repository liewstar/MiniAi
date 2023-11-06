<template>
  <div class="flex flex-col space-y-2 p-4">
    <div class="flex items-start">
      <div v-if="this.isUser != null" class="bg-blue-500 text-white rounded-lg p-2">
        <p>😃: {{content}}</p>
      </div>
      <div v-else class="bg-gray-800 text-white rounded-lg p-2">
        <p>🤖: {{content}}</p>
      </div>

    </div>
<!--    <div class="flex items-end">-->
<!--      <div class=" bg-gray-800 text-white rounded-lg p-2">-->
<!--        <p>🤖: Hi then B: Hi then B: Hi then en B: Hi then B: Hi then B: Hi then B: Hi then B: Hi then B: en B: Hi then B: Hi then B: Hi then B: Hi then B: Hi then B: en B: Hi then B: Hi then B: Hi then B: Hi then B: Hi then B: en B-->
<!--          : Hi then B: Hi then B: Hi then B: Hi then B: Hi then B: en B: Hi then B: Hi then B: Hi then B: Hi then B: Hi then B: en B: Hi then B: Hi then B: Hi then B: Hi then B: Hi then B: en B: Hi then B: Hi then B: Hi then B: Hi then B: Hi then B: en B: Hi then B: Hi then B: Hi then B: Hi then B: Hi then B: en B: Hi then B: Hi then B: Hi then B: Hi then B: Hi then B: en B: Hi then B: Hi then B: Hi then B: Hi then B: Hi then B: en B: Hi then B: Hi then B: Hi then B: Hi then B: Hi then B: en B: Hi then B: Hi then B: Hi then B: Hi then B: Hi then B: en B: Hi then B: Hi then B: Hi then B: Hi then B: Hi then B: en B: Hi then B: Hi then B: Hi then B: Hi then B: Hi then B: B: Hi then B: Hi then B: Hi then B: Hi then B: Hi then B: Hi then B: Hi then B: Hi then B: Hi then B: Hi then B: Hi then B: Hi then B: Hi then B: Hi there!</p>-->
<!--      </div>-->
<!--    </div>-->
  </div>
</template>

<script>
export default {
  // eslint-disable-next-line vue/multi-word-component-names
  name: "Message",
  props:{
    content: {
      type: String,
      required: true
    },
    isUser: {
      type: String,
      required: true
    },
  }
}
</script>

<style scoped>

</style>