import axios from 'axios';

const API_BASE = 'https://8083-odsyjr2-bookmanagementc-m6d8rp2vise.ws-us120.gitpod.io';

const api = axios.create({
  baseURL: `${API_BASE}/api`,
});

// 로그인: { email, password } 보내면 { id, email, name, ... } 받음
export const login = async (email, password) => {
  const res = await api.post('/subscribers/login', { email, password });
  return res.data;
};

// 포인트 적립: /subscribers/{userId}/addPoints 에 { amount } body로 전송
export const addPoints = async (userId, amount) => {
  const res = await api.post(`/subscribers/${userId}/addPoints`, { amount });
  return res.data;
};

export const getSubscriber = async (userId) => {
  const res = await api.get(`/subscribers/${userId}`);
  return res.data;
};

export default api;
