import { authApi } from './httpClients';

// 회원가입
export function signup({ email, password, name, region }) {
  return authApi.post('/api/subscribers/register', { email, password, name, region });
}

// 로그인
export async function login({ email, password }) {
  const res = await authApi.post('/api/subscribers/login', { email, password });
  return res.data; // { id: number }
}

// 로그아웃
export function logout(email) {
  return authApi.post('/api/subscribers/logout', { email });
}
