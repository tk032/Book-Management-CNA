// src/components/NavBar.jsx
import React from 'react';
import { AppBar, Toolbar, Typography, Button, Box } from '@mui/material';
import { Link, useNavigate } from 'react-router-dom';

export default function NavBar() {
  const navigate = useNavigate();
  const user = JSON.parse(localStorage.getItem('user'));

  const handleLogout = () => {
    localStorage.removeItem('user');
    navigate('/', { replace: true });
  };

  return (
    <AppBar position="static">
      <Toolbar>
        <Typography
          variant="h6"
          component={Link}
          to="/"
          sx={{ flexGrow: 1, textDecoration: 'none', color: 'inherit' }}
        >
          Book Management
        </Typography>

        {user ? (
          <Box>
            <Button color="inherit" component={Link} to="/mypage">
              마이페이지
            </Button>
            <Button color="inherit" onClick={handleLogout}>
              로그아웃
            </Button>
          </Box>
        ) : (
          <Box>
            <Button color="inherit" component={Link} to="/login">
              로그인
            </Button>
            <Button color="inherit" component={Link} to="/signup">
              회원가입
            </Button>
          </Box>
        )}
      </Toolbar>
    </AppBar>
  );
}
