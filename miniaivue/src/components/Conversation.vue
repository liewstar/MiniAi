<template>
  <div class="message-box  rounded-lg p-2">
    <h2 class="message-title font-inter">{{ title }}</h2>
    <p class="message-time text-sm text-gray-500">{{ formattedTime }}</p>
  </div>
</template>

<script>
export default {
  // eslint-disable-next-line vue/multi-word-component-names
  name: "Conversation",
  props: {
    title: {
      type: String,
      required: true
    },
    time: {
      type: String,
      required: true
    }
  },
  computed:{
    formattedTime() {
      const date = new Date(this.time);
      const options = { year: 'numeric', month: 'long', day: 'numeric', hour: 'numeric', minute: 'numeric', second: 'numeric', timeZone: 'Asia/Shanghai' };
      return date.toLocaleString('zh-CN', options);
    }
  }

}
</script>

<style scoped>

</style>