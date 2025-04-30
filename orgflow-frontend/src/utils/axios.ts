import axios from 'axios'

// 请求拦截器
axios.interceptors.request.use(
    config => {
        // 优先从sessionStorage获取（临时token）
        let token = sessionStorage.getItem('satoken_session')
        
        // 如果没有临时token，再尝试从localStorage获取（持久token）
        if (!token) {
            token = localStorage.getItem('satoken')
        }
        
        // 如果有token，则添加到请求头
        if (token) {
            config.headers['Authorization'] = token
        }
        return config
    },
    error => {
        return Promise.reject(error)
    }
)

// 响应拦截器
axios.interceptors.response.use(
    response => {
        return response
    },
    error => {
        if (error.response && error.response.status === 401) {
            // Token过期或无效，清除存储并跳转到登录页
            localStorage.removeItem('satoken')
            sessionStorage.removeItem('satoken_session')
            
            // 跳转到登录页，并带上当前路径作为重定向参数
            const currentPath = window.location.pathname
            window.location.href = `/login?redirect=${currentPath}`
        }
        return Promise.reject(error)
    }
)

export default axios;