import axios from "axios";

// 创建 axios 实例
const axiosInstance = axios.create({
  baseURL: "http://localhost:8080",
  timeout: 10000, // 请求超时
  headers: { "Content-Type": "application/json" },
});

// 请求拦截器
axiosInstance.interceptors.request.use(
  (config) => {
    // 在请求发送前做一些处理，例如添加 token
    const token = localStorage.getItem("coj:userToken");
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
  },
  (error) => {
    // 处理请求错误
    return Promise.reject(error);
  },
);

// 响应拦截器
axiosInstance.interceptors.response.use(
  (response) => {
    // 处理响应数据
    return response.data;
  },
  (error) => {
    // 处理响应错误
    if (error.response) {
      // 服务器返回的错误
      const { status, data } = error.response;
      switch (status) {
        case 401:
          break;
        case 403:
          // 禁止访问
          alert("您没有权限访问此资源");
          break;
        case 404:
          // 资源未找到
          alert("请求的资源不存在");
          break;
        case 500:
          // 服务器错误
          alert("服务器内部错误");
          break;
        default:
          alert(data.message || "请求失败");
      }
    } else if (error.request) {
      alert("请求超时，请稍后再试");
    } else {
      alert(`请求失败: ${error.message}`);
    }
    return Promise.reject(error);
  },
);

// 取消请求
const CancelToken = axios.CancelToken;
const source = CancelToken.source();

const cancelRequest = (message) => {
  source.cancel(message);
};

const get = (url, params) => axiosInstance.get(url, { params });
const post = (url, data) => axiosInstance.post(url, data);
const put = (url, data) => axiosInstance.put(url, data);
const del = (url) => axiosInstance.delete(url);

export { axiosInstance, cancelRequest, get, post, put, del };
