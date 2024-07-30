// 用户 API 模块
import { post } from "./axiosInstance";

export function userLogin(username, password) {
  return post("/user/login", { username, password });
}
