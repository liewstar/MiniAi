<template>
  <div class="message-box text-white rounded-lg p-2">
    <h2 class="message-title text-lg font-bold text-gray-500">{{ title }}</h2>
    <p class="message-time text-sm text-gray-500">{{ time }}</p>
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

}
</script>

<style scoped>

</style>