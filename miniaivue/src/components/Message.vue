<template>
  <div class="p-4">
    <div class="flex items-start">
      <div v-if="this.isUser !== null" class="bg-blue-500 text-white rounded-lg p-2">
        <pre style="white-space: pre-wrap;" v-html="'😀'+renderedMarkdown"></pre>
      </div>
      <div v-else class="bg-gray-500 text-white rounded-lg p-2">
        <pre style="white-space: pre-wrap;" v-html="'🤖'+renderedMarkdown"></pre>
      </div>
    </div>
  </div>
</template>
<script>
import MarkdownIt from 'markdown-it';
import hljs from 'highlight.js'
import markdownItHighlight from 'markdown-it-highlightjs'
import 'highlight.js/styles/github.css'
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
  computed: {
    renderedMarkdown() {
      const md = new MarkdownIt().use(markdownItHighlight)

      md.set({
        highlight: function(code, lang) {
          return hljs.highlightAuto(code, [lang]).value
        }
      })

      return md.render(this.content)
    }
  }

}
</script>

<style scoped>

</style>