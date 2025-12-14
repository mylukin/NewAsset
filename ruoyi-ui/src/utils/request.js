import axios from 'axios'
import { Message, MessageBox } from 'element-ui'
import NProgress from 'nprogress'
import 'nprogress/nprogress.css'

// Create axios instance
const service = axios.create({
  baseURL: '/api',
  timeout: 30000,
  headers: {
    'Content-Type': 'application/json;charset=utf-8'
  }
})

// Request interceptor
service.interceptors.request.use(
  config => {
    NProgress.start()
    // Add token if exists
    const token = localStorage.getItem('token')
    if (token) {
      config.headers['Authorization'] = 'Bearer ' + token
    }
    return config
  },
  error => {
    NProgress.done()
    console.error('Request error:', error)
    return Promise.reject(error)
  }
)

// Response interceptor
service.interceptors.response.use(
  response => {
    NProgress.done()
    const res = response.data

    // Handle standard RuoYi response format
    if (res.code !== undefined && res.code !== 200 && res.code !== 0) {
      Message.error(res.msg || 'Request failed')

      // Handle specific error codes
      if (res.code === 401) {
        MessageBox.confirm(
          'Session expired. Please login again.',
          'Warning',
          {
            confirmButtonText: 'Login',
            cancelButtonText: 'Cancel',
            type: 'warning'
          }
        ).then(() => {
          localStorage.removeItem('token')
          window.location.href = '/login'
        })
      }
      return Promise.reject(new Error(res.msg || 'Error'))
    }
    return res
  },
  error => {
    NProgress.done()
    console.error('Response error:', error)
    const message = error.response?.data?.msg || error.message || 'Network error'
    Message.error(message)
    return Promise.reject(error)
  }
)

export default service
