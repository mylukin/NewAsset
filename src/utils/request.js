import axios from 'axios';
import { Message } from 'element-ui';
import router from '@/router';

// Create axios instance
const service = axios.create({
  baseURL: process.env.VUE_APP_BASE_API || '/asset',
  timeout: 10000,
});

// Request interceptor
service.interceptors.request.use(
  config => {
    // Add token if available
    const token = localStorage.getItem('token');
    if (token) {
      config.headers['Authorization'] = 'Bearer ' + token;
    }
    return config;
  },
  error => {
    console.error('Request error:', error);
    return Promise.reject(error);
  }
);

// Response interceptor
service.interceptors.response.use(
  response => {
    const res = response.data;

    // Success response
    if (res.code === 200) {
      return res;
    }

    // Handle business errors
    Message({
      message: res.message || 'Error',
      type: 'error',
      duration: 5 * 1000,
    });

    return Promise.reject(new Error(res.message || 'Error'));
  },
  error => {
    console.error('Response error:', error);

    const { response } = error;

    if (response && response.status === 401) {
      // Redirect to login
      router.push('/login');
      Message({
        message: '登录已过期，请重新登录',
        type: 'error',
      });
    } else {
      Message({
        message: error.message || 'Network error',
        type: 'error',
        duration: 5 * 1000,
      });
    }

    return Promise.reject(error);
  }
);

export default service;
