import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import NavBar from './components/NavBar';
import SignupPage from './pages/SignupPage';
import LoginPage from './pages/LoginPage';
import DashboardPage from './pages/DashboardPage';
import MyPage from './pages/MyPage';

export default function App() {
  return (
    <Router>
      <NavBar />
      <Routes>
        <Route path="/signup" element={<SignupPage />} />
        <Route path="/login" element={<LoginPage />} />
        <Route path="/mypage" element={<MyPage />} />
        <Route path="/" element={<DashboardPage />} />
      </Routes>
    </Router>
  );
}
