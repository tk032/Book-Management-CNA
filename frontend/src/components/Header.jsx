import React, { useState, useEffect } from 'react';
import { AppBar, Toolbar, Box, Link as MuiLink, Button, IconButton, Menu, MenuItem } from '@mui/material';
import { Link, useNavigate } from 'react-router-dom';
import AccountCircle from '@mui/icons-material/AccountCircle';

export default function Header() {
  const navigate = useNavigate();
  const [isLoggedIn, setIsLoggedIn] = useState(!!localStorage.getItem('authToken'));
  const [anchorElAuthor, setAnchorElAuthor] = useState(null);

  useEffect(() => {
    const token = localStorage.getItem('authToken');
    setIsLoggedIn(!!token);
  }, []);

  const handleAuthorMenuOpen = (e) => setAnchorElAuthor(e.currentTarget);
  const handleAuthorMenuClose = () => setAnchorElAuthor(null);

  const handleLogout = () => {
    localStorage.removeItem('authToken');
    setIsLoggedIn(false);
    navigate('/');
  };

  return (
    <AppBar position="static" sx={{ backgroundColor: '#1e3a8a' }}>
      <Toolbar sx={{ minHeight: 70, display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
        <MuiLink component={Link} to="/" underline="none" color="inherit" sx={{ fontWeight: 'bold', fontSize: 20 }}>
          Home
        </MuiLink>
        <Box sx={{ display: 'flex', alignItems: 'center', gap: 2 }}>
          {isLoggedIn ? (
            <>              
              <IconButton color="inherit" onClick={handleAuthorMenuOpen} sx={{ p: 0 }}>
                <AccountCircle />
              </IconButton>
              <Menu
                anchorEl={anchorElAuthor}
                open={Boolean(anchorElAuthor)}
                onClose={handleAuthorMenuClose}
              >
                <MenuItem component={Link} to="/drafts" onClick={handleAuthorMenuClose}>
                  임시 저장
                </MenuItem>
                <MenuItem component={Link} to="/books" onClick={handleAuthorMenuClose}>
                  최종 저장
                </MenuItem>
                <MenuItem component={Link} to="/write" onClick={handleAuthorMenuClose}>
                  출간 요청
                </MenuItem>
              </Menu>
              <Button color="inherit" onClick={handleLogout} sx={{ fontWeight: 'bold' }}>
                로그아웃
              </Button>
            </>
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