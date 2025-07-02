import { useState, useEffect } from 'react';

export function useAuth() {
  const [isLoggedIn, setIsLoggedIn] = useState(
    !!localStorage.getItem('subscriberId')
  );

  // 로그인/로그아웃 시 auth-change 이벤트를 듣고 상태 동기화
  useEffect(() => {
    const sync = () => {
      setIsLoggedIn(!!localStorage.getItem('subscriberId'));
    };
    window.addEventListener('auth-change', sync);
    return () => window.removeEventListener('auth-change', sync);
  }, []);

  function logout() {
    localStorage.removeItem('subscriberId');
    // 로그아웃 직후 NavBar 갱신용 이벤트
    window.dispatchEvent(new Event('auth-change'));
    // 홈으로 리다이렉트
    window.location.href = '/';
  }

  return { isLoggedIn, logout };
}
