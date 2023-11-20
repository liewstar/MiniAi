import Vue from 'vue'
import App from './App.vue'
import router from './router'
import AdminLayout from "@/views/admin/AdminLayout";
import UserLayout from "@/views/UserLayout";
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import 'highlight.js/styles/atom-one-dark.css'
import * as echarts from 'echarts'

Vue.prototype.$echarts = echarts

Vue.use(ElementUI);

Vue.component('AdminLayout', AdminLayout)
Vue.component('UserLayout', UserLayout)
Vue.config.productionTip = false

new Vue({
  router,
  render: h => h(App),
}).$mount('#app')
