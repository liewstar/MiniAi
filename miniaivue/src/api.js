import axios from 'axios';

// 创建一个axios实例
const api = axios.create({
    baseURL: 'http://localhost:8081', // 设置接口的基础URL
    timeout: 10000, // 设置请求超时时间
});

// 请求拦截器
api.interceptors.request.use(
    (config) => {
        // 在发送请求之前做些什么，例如添加token等
        return config;
    },
    (error) => {
        // 处理请求错误
        return Promise.reject(error);
    }
);

// 响应拦截器
api.interceptors.response.use(
    (response) => {
        // 对响应数据做点什么，例如处理错误码等
        return response.data;
    },
    (error) => {
        // 处理响应错误
        return Promise.reject(error);
    }
);

export default api;
