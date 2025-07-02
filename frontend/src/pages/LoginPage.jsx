import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { login } from '../api/authService';
import {
  Container,
  TextField,
  Button,
  Typography,
  Alert,
  Box
} from '@mui/material';

export default function LoginPage() {
  const navigate = useNavigate();
  const [form, setForm] = useState({ email: '', password: '' });
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(false);

  const handleChange = e => {
    setForm(prev => ({ ...prev, [e.target.name]: e.target.value }));
  };

  const handleLogin = async e => {
    e.preventDefault();
    setLoading(true);
    setError(null);
    try {
      const { id } = await login(form);
      localStorage.setItem('subscriberId', id);
      // 로그인 직후 NavBar 갱신용 이벤트
      window.dispatchEvent(new Event('auth-change'));
      navigate('/');
    } catch (err) {
      setError(err.response?.data?.message || '로그인에 실패했습니다.');
    } finally {
      setLoading(false);
    }
  };

  return (
    <Container maxWidth="xs">
      <Box sx={{ mt: 8 }}>
        <Typography variant="h5" align="center" gutterBottom>
          로그인
        </Typography>
        {error && (
          <Alert severity="error" sx={{ mb: 2 }}>
            {error}
          </Alert>
        )}
        <form onSubmit={handleLogin}>
          <TextField
            label="이메일"
            name="email"
            type="email"
            fullWidth
            required
            value={form.email}
            onChange={handleChange}
            sx={{ mb: 2 }}
          />
          <TextField
            label="비밀번호"
            name="password"
            type="password"
            fullWidth
            required
            value={form.password}
            onChange={handleChange}
            sx={{ mb: 3 }}
          />
          <Button
            type="submit"
            variant="contained"
            fullWidth
            disabled={loading}
          >
            {loading ? '로딩 중…' : '로그인'}
          </Button>
        </form>
      </Box>
    </Container>
  );
}
