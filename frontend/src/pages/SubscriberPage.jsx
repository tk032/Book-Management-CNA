// src/pages/SubscriberPage.jsx
import React, { useEffect, useState } from 'react';
import { Container, Typography, Box, Button } from '@mui/material';
import { useNavigate } from 'react-router-dom';
import { addPoints } from '../api/api';

export default function SubscriberPage() {
  const navigate = useNavigate();
  const stored = JSON.parse(localStorage.getItem('user'));
  const [user, setUser] = useState(stored);

  // 로그인 정보 없으면 로그인 페이지로
  useEffect(() => {
    if (!stored) {
      navigate('/login', { replace: true });
    }
  }, [stored, navigate]);

  const handleAdd500 = async () => {
    try {
      const updated = await addPoints(user.id, 500);
      setUser(updated);
      localStorage.setItem('user', JSON.stringify(updated));
    } catch (err) {
      console.error(err);
      alert('포인트 적립에 실패했습니다.');
    }
  };

  if (!user) return null;

  return (
    <Container maxWidth="md" sx={{ py: 6 }}>
      <Typography variant="h4" fontWeight="bold" gutterBottom>
        📖 구독자 페이지
      </Typography>
      <Typography variant="subtitle1" color="textSecondary" gutterBottom>
        ID: <strong>{user.id}</strong><br />
        이메일: <strong>{user.email}</strong><br />
        이름: <strong>{user.name}</strong><br />
        포인트: <strong>{user.point}</strong>
      </Typography>
      <Box sx={{ mt: 4 }}>
        <Button variant="contained" onClick={handleAdd500}>
          +500 포인트 적립
        </Button>
      </Box>
    </Container>
  );
}
