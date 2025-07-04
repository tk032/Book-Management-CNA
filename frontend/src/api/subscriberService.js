import { authApi } from './httpClients';

/**
 * 저장된 subscriberId를 꺼내고,
 * 없으면 에러를 던집니다.
 */
function getSubscriberId() {
  const id = localStorage.getItem('subscriberId');
  if (!id) {
    throw new Error('로그인 또는 가입이 필요합니다.');
  }
  return id;
}

// 회원 상세 조회
export function getSubscriber() {
  const id = getSubscriberId();
  return authApi.get(`/api/subscribers/${id}`);
}

// 포인트 충전 (amount는 쿼리 파라미터)
export function addPoints(amount) {
  const id = getSubscriberId();
  return authApi.post(
    `/api/subscribers/${id}/addPoints`,
    null,
    { params: { amount } }
  );
}

// 책 열람 (point는 쿼리 파라미터)
export function readContent(point) {
  const id = getSubscriberId();
  return authApi.post(
    `/api/subscribers/${id}/readContent`,
    null,
    { params: { point } }
  );
}

// 무제한 요금제 가입
export function subscribePlan() {
  const id = getSubscriberId();
  return authApi.post(`/api/subscribers/${id}/subscribe`);
}

// 무제한 요금제 해지
export function unsubscribePlan() {
  const id = getSubscriberId();
  return authApi.post(`/api/subscribers/${id}/unsubscribe`);
}

// KT 인증
export function ktAuth() {
  const id = getSubscriberId();
  return authApi.post(`/api/subscribers/${id}/ktauth`);
}
