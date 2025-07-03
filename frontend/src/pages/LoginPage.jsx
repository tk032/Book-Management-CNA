// src/pages/LoginPage.jsx
import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { Container, TextField, Button, Typography, Box } from '@mui/material';
import { login } from '../api/api';

export default function LoginPage() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const user = await login(email, password);
      // 예시 응답: { id: 1, email: "...", name: "...", ... }
      localStorage.setItem('user', JSON.stringify(user));
      navigate('/reader');
    } catch (err) {
      console.error(err);
      alert('로그인에 실패했습니다.');
    }
  };

  return (
    <Container maxWidth="xs" sx={{ py: 6 }}>
      <Typography variant="h5" gutterBottom>로그인</Typography>
      <Box component="form" onSubmit={handleSubmit} sx={{ display: 'grid', gap: 2 }}>
        <TextField
          label="이메일"
          type="email"
          required
          value={email}
          onChange={e => setEmail(e.target.value)}
        />
        <TextField
          label="비밀번호"
          type="password"
          required
          value={password}
          onChange={e => setPassword(e.target.value)}
        />
        <Button type="submit" variant="contained">로그인</Button>
      </Box>
    </Container>
  );
}
