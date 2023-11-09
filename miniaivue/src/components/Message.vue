<template>
  <div class="flex flex-col space-y-2 p-4">
    <div class="flex items-start">
      <div v-if="this.isUser !== null" class="bg-blue-500 text-white rounded-lg p-2">
        <p>😃: {{content}}</p>
      </div>
      <div v-else class="bg-gray-800 text-white rounded-lg p-2">
        <p>🤖: {{content}}</p>
      </div>



<!--      <div v-if="this.isUser == null" class="bg-blue-500 text-white rounded-lg p-2">-->
<!--        <p v-html="renderedMessage"></p>-->
<!--      </div>-->
<!--      <div v-else class="bg-gray-800 text-white rounded-lg p-2">-->
<!--        <p v-html="renderedMessage"></p>-->
<!--      </div>-->
    </div>
  </div>
</template>
<script>
import MarkdownIt from 'markdown-it';
import hljs from 'highlight.js';
export default {
  // eslint-disable-next-line vue/multi-word-component-names
  name: "Message",
  props:{
    content: {
      type: String,
      required: true
    },
    isUser: {
      type: null,
      required: true
    },
  },
  methods: {
    renderedMessage() {
      if(this.content) {
        console.log("=======")
        return this.md.render(this.content);
      }
      console.log("======")
      return '';
    }
  },
  mounted() {
    const  md = new MarkdownIt({
      highlight: function (str, lang) {
        if (lang && hljs.getLanguage(lang)) {
          try {
            return hljs.highlight(lang, str).value;
          }catch (__) {console.log('111')}
        }
        return '';
      }
    });
    this.md = md;
  },
  computed() {
    this.renderedMessage();
  }

}
</script>

<style scoped>

</style>