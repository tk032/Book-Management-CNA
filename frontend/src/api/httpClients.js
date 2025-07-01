import axios from 'axios';

export const subscriberApi = axios.create({
  baseURL: process.env.REACT_APP_SUBSCRIBER_API || 'http://localhost:8083',
});

export const bookApi = axios.create({
  baseURL: process.env.REACT_APP_BOOK_API || 'http://localhost:8082',
});
export const authApi = axios.create({
  baseURL: process.env.REACT_APP_AUTH_API || 'https://8083-odsyjr2-bookmanagementc-ts13i6duskv.ws-us120.gitpod.io/api',
});
