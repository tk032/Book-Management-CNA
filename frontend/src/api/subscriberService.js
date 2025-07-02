import { authApi } from './httpClients';

// 모든 요청에서 하드코딩된 "1" 사용

// 상태 조회
export function getSubscriber() {
  return authApi.get('/subscribers/1');
}

// 포인트 충전
export function addPoints(amount) {
  return authApi.post('/subscribers/1/addPoints', { amount });
}

// 책 열람
export function readContent(point) {
  return authApi.post('/subscribers/1/readContent', { point });
}

// 요금제 가입
export function subscribePlan() {
  return authApi.post('/subscribers/1/subscribe');
}

// 요금제 해지
export function unsubscribePlan() {
  return authApi.post('/subscribers/1/unsubscribe');
}
