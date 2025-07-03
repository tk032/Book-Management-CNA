// src/pages/MainPage.jsx
import React from 'react';
import { Container, Typography, Button, Box } from '@mui/material';
import { useNavigate } from 'react-router-dom';

export default function MainPage() {
  const navigate = useNavigate();

  return (
    <Container maxWidth="sm" sx={{ textAlign: 'center', py: 6 }}>
      <Typography variant="h3" gutterBottom>
        메뉴를 선택해주세요
      </Typography>
      <Box sx={{ display: 'flex', justifyContent: 'center', gap: 2, mt: 4 }}>
        <Button variant="contained" onClick={() => navigate('/reader')}>
          독자 메뉴
        </Button>
        <Button variant="contained" onClick={() => navigate('/author')}>
          작가 메뉴
        </Button>
        <Button variant="contained" onClick={() => navigate('/admin')}>
          관리자 메뉴
        </Button>
      </Box>
    </Container>
  );
}
