import Vue from 'vue';
import App from './App.vue';
import router from './router';
import store from './store';

// Element UI
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';

// Global styles
import './styles/index.scss';

// Axios
import axios from 'axios';
import VueAxios from 'vue-axios';

// Cookie
import Cookies from 'js-cookie';

Vue.config.productionTip = false;

// Configure axios
axios.defaults.baseURL = process.env.VUE_APP_API_BASE_URL || '/';
axios.defaults.timeout = 10000;

// Request interceptor
axios.interceptors.request.use(
  config => {
    const token = Cookies.get('token');
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
  },
  error => {
    return Promise.reject(error);
  }
);

// Response interceptor
axios.interceptors.response.use(
  response => {
    return response;
  },
  error => {
    if (error.response?.status === 401) {
      // Handle unauthorized
      Cookies.remove('token');
      router.push('/login');
    }
    return Promise.reject(error);
  }
);

Vue.use(VueAxios, axios);
Vue.use(ElementUI);

// Make Cookies available globally (js-cookie is not a Vue plugin)
Vue.prototype.$cookies = Cookies;

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app');
