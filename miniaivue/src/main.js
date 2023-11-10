import Vue from 'vue'
import App from './App.vue'
import router from './router'
import AdminLayout from "@/views/admin/AdminLayout";
import UserLayout from "@/views/UserLayout";
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import MarkdownIt from 'markdown-it';
import hljs from 'highlight.js';
import 'highlight.js/styles/atom-one-dark.css'

Vue.directive('markdown', {
  bind(el, binding) {
    const md = new MarkdownIt({
      highlight: (code, language) => {
        const highlighted = hljs.highlightAuto(code, [language]).value;
        return `<pre><code class="hljs ${language}">${highlighted}</code></pre>`;
      }
    });
    const updateMarkdown = () => {
      const markdownText = binding.value;
      const html = md.render(markdownText);
      el.innerHTML = html;
    };
    updateMarkdown(); // 初始化时渲染Markdown内容

    // 在update钩子中更新Markdown内容
    el.__markdownUpdate = updateMarkdown;
  },
  // eslint-disable-next-line no-unused-vars
  update(el, binding) {
    // 调用updateMarkdown方法更新Markdown内容
    el.__markdownUpdate();
  },
  unbind(el) {
    // 清除事件监听
    delete el.__markdownUpdate;
  }
});



Vue.use(ElementUI);

Vue.component('AdminLayout', AdminLayout)
Vue.component('UserLayout', UserLayout)
Vue.config.productionTip = false

new Vue({
  router,
  render: h => h(App),
}).$mount('#app')
