import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import "@/assets/css/global.css"
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import locale from 'element-ui/lib/locale/lang/zh-CN'
import VCharts from 'v-charts'

Vue.use(ElementUI, { locale,size:'small' })
Vue.config.productionTip = false
Vue.use(VCharts)
new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
