import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'

// Element UI
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'

// Global styles
import './styles/index.scss'

// Plugins
import { modal } from './plugins/modal'
import { parseTime, resetForm, addDateRange, selectDictLabel } from './utils/ruoyi'

// Use plugins
Vue.use(ElementUI, { size: 'small' })

// Global properties
Vue.prototype.$modal = modal
Vue.prototype.parseTime = parseTime
Vue.prototype.resetForm = resetForm
Vue.prototype.addDateRange = addDateRange
Vue.prototype.selectDictLabel = selectDictLabel

Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
