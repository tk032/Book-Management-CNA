import React, { useState } from 'react';
import { AppBar, Toolbar, Box, Link as MuiLink, Button } from '@mui/material';
import { Link, useNavigate } from 'react-router-dom';

export default function Header() {
  const navigate = useNavigate();
  const [isLoggedIn, setIsLoggedIn] = useState(!!localStorage.getItem('authToken'));

  const handleLogout = () => {
    localStorage.removeItem('authToken');
    setIsLoggedIn(false);
    navigate('/');
  };

  return (
    <AppBar position="static" sx={{ backgroundColor: '#1e3a8a' }}>
      <Toolbar sx={{ minHeight: 70, display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
        <MuiLink
          component={Link}
          to="/"
          underline="none"
          color="inherit"
          sx={{ fontWeight: 'bold', fontSize: 20 }}
        >
          Home
        </MuiLink>
        <Box sx={{ display: 'flex', alignItems: 'center', gap: 2 }}>
          {isLoggedIn ? (
            <Button color="inherit" onClick={handleLogout} sx={{ fontWeight: 'bold' }}>
              로그아웃
            </Button>
          ) : (
            <>
              <MuiLink component={Link} to="/login" underline="none" color="inherit" sx={{ fontWeight: 'bold' }}>
                로그인
              </MuiLink>
              <MuiLink component={Link} to="/signup" underline="none" color="inherit" sx={{ fontWeight: 'bold' }}>
                회원가입
              </MuiLink>
            </>
          )}
          <MuiLink component={Link} to="/books" underline="none" color="inherit" sx={{ fontWeight: 'bold' }}>
            작품목록
          </MuiLink>
          <MuiLink component={Link} to="/write" underline="none" color="inherit" sx={{ fontWeight: 'bold' }}>
            작품등록
          </MuiLink>
        </Box>
      </Toolbar>
    </AppBar>
  );
}
