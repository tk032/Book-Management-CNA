import axios from 'axios';

const api = axios.create({
  baseURL: process.env.REACT_APP_API_BASE_URL || 'http://localhost:8080',
  timeout: 5000,
});

// 로그인
export const login = async ({ email, password }) => {
  try {
    const response = await api.post('/auth/login', { email, password });
    return response.data.data; // { token, ... }
  } catch (error) {
    console.error('login error:', error);
    throw error;
  }
};

export const signup = async ({ email, password, name, address }) => {
  try {
    const response = await api.post('/auth/signup', { email, password, name, address });
    return response.data.data;
  } catch (error) {
    console.error('signup error:', error);
    throw error;
  }
};