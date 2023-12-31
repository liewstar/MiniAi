import axios from 'axios';
// 创建一个axios实例
const api = axios.create({
    baseURL: 'http://localhost:8081', // 设置接口的基础URL
});

// 请求拦截器
api.interceptors.request.use(
    (config) => {
        // 在发送请求添加token
        config.headers['token'] = localStorage.getItem("MiniAiToken");
        return config;
    },
    (error) => {
        // 处理请求错误
        console.log("error")
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
        return Promise.reject(error);
    }
);

export default api;
