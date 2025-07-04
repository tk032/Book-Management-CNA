import axios from 'axios';

export const authApi = axios.create({
  baseURL: 'https://8083-odsyjr2-bookmanagementc-m6d8rp2vise.ws-us120.gitpod.io',
  headers: { 'Content-Type': 'application/json' }
});

authApi.interceptors.request.use(
  config => {
    window.dispatchEvent(new CustomEvent('api-log', { detail: {
      type: 'request', method: config.method,
      url: config.baseURL + config.url,
      data: config.data, timestamp: Date.now()
    }}));
    return config;
  },
  error => {
    window.dispatchEvent(new CustomEvent('api-log', { detail: {
      type: 'error', method: error.config?.method || 'UNKNOWN',
      url: error.config ? (error.config.baseURL + error.config.url) : 'UNKNOWN',
      data: error.message, timestamp: Date.now()
    }}));
    return Promise.reject(error);
  }
);

authApi.interceptors.response.use(
  response => {
    window.dispatchEvent(new CustomEvent('api-log', { detail: {
      type: 'response', method: response.config.method,
      url: response.config.baseURL + response.config.url,
      data: response.data, timestamp: Date.now()
    }}));
    return response;
  },
  error => {
    const detail = {
      type: 'error', method: error.config?.method || 'UNKNOWN',
      url: error.config ? (error.config.baseURL + error.config.url) : 'UNKNOWN',
      data: error.response?.data ?? error.message, timestamp: Date.now()
    };
    window.dispatchEvent(new CustomEvent('api-log', { detail }));
    return Promise.reject(error);
  }
);