import React from 'react';
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import NavBar from './components/NavBar';

import LoginPage from './pages/LoginPage';
import SignupPage from './pages/SignupPage';
import MainPage from './pages/MainPage';
import SubscriberPage from './pages/SubscriberPage';
import AuthorPage from './pages/AuthorPage';
import AdminPage from './pages/AdminPage';
import MyPage from './pages/MyPage';

export default function App() {
  return (
    <Router>
      <NavBar />
      <Routes>
        {/* 로그인/회원가입 */}
        <Route path="/login" element={<LoginPage />} />
        <Route path="/signup" element={<SignupPage />} />

        {/* 역할 선택 */}
        <Route path="/" element={<MainPage />} />

        {/* 독자 메뉴: 로그인 필요 */}
        <Route path="/reader" element={<SubscriberPage />} />

        {/* 작가·관리자 메뉴: 로그인 불필요 */}
        <Route path="/author" element={<AuthorPage />} />
        <Route path="/admin" element={<AdminPage />} />

        {/* 내 정보 보기 */}
        <Route path="/mypage" element={<MyPage />} />

        {/* 그 외 잘못된 경로는 홈으로 */}
        <Route path="*" element={<Navigate to="/" replace />} />
      </Routes>
    </Router>
  );
}
