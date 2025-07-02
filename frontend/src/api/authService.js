import { authApi } from './httpClients';

export const login = async ({ email }) => {
  const res = await authApi.post('/subscribers/login', {email});
  return res.data;
};

export const signup = async ({ email, name }) => {
  const res = await authApi.post('/subscribers/register', {email,name});
  return res.data;
};