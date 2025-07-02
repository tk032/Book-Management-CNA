import React from 'react';
import { AppBar, Toolbar, Button, Box } from '@mui/material';
import { useNavigate } from 'react-router-dom';
import { useAuth } from '../hooks/useAuth';

export default function NavBar() {
  const navigate = useNavigate();
  const { isLoggedIn, logout } = useAuth();

  const handleMyPageClick = () => {
    // MyPage 컴포넌트에 데이터 요청 트리거
    window.dispatchEvent(new Event('fetch-user'));
    navigate('/mypage');
  };

  return (
    <AppBar position="static" color="primary">
      <Toolbar>
        <Box sx={{ flexGrow: 1 }}>
          <Button color="inherit" onClick={() => navigate('/')}>Home</Button>
        </Box>
        {isLoggedIn ? (
          <>
            <Button color="inherit" onClick={handleMyPageClick}>마이페이지</Button>
            <Button color="inherit" onClick={logout}>로그아웃</Button>
          </>
        ) : (
          <>
            <Button color="inherit" onClick={() => navigate('/login')}>로그인</Button>
            <Button color="inherit" onClick={() => navigate('/signup')}>회원가입</Button>
          </>
        )}
      </Toolbar>
    </AppBar>
  );
}