import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import "@/assets/css/global.css"
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
Vue.config.productionTip = false
import locale from 'element-ui/lib/locale/lang/zh-CN'

Vue.use(ElementUI, { locale,size:'small' })


new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')


