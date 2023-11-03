import Vue from 'vue'
import App from './App.vue'
import router from './router'
import AdminLayout from "@/views/admin/AdminLayout";
import UserLayout from "@/views/UserLayout";
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
Vue.use(ElementUI);

Vue.component('AdminLayout', AdminLayout)
Vue.component('UserLayout', UserLayout)
Vue.config.productionTip = false

new Vue({
  router,
  render: h => h(App),
}).$mount('#app')
