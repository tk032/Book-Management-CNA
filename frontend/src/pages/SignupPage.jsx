import React, { useState } from 'react';
import { Box, TextField, Button, Typography, Alert, CircularProgress } from '@mui/material';
import { useNavigate } from 'react-router-dom';
import { signup } from '../api/authService';



export default function SignupPage() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [name, setName] = useState('');
  const [address, setAddress] = useState('');
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    setError(null);
    try {
      await signup({ email, password, name, address });
      navigate('/login');
    } catch (err) {
      setError(err.response?.data?.message || '회원가입에 실패했습니다.');
    } finally {
      setLoading(false);
    }
  };

  return (
    <Box sx={{ maxWidth: 400, mx: 'auto', mt: 8, p: 3, boxShadow: 2, borderRadius: 2 }}>
      <Typography variant="h5" sx={{ mb: 3, textAlign: 'center' }}>
        회원가입
      </Typography>
      {error && <Alert severity="error" sx={{ mb: 2 }}>{error}</Alert>}
      <form onSubmit={handleSubmit}>
        <TextField
          label="이메일"
          type="email"
          fullWidth
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          required
          sx={{ mb: 2 }}
        />
        <TextField
          label="비밀번호"
          type="password"
          fullWidth
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          required
          sx={{ mb: 2 }}
        />
        <TextField
          label="이름"
          type="text"
          fullWidth
          value={name}
          onChange={(e) => setName(e.target.value)}
          required
          sx={{ mb: 2 }}
        />
        <TextField
          label="주소"
          type="text"
          fullWidth
          value={address}
          onChange={(e) => setAddress(e.target.value)}
          required
          sx={{ mb: 3 }}
        />
        <Button type="submit" variant="contained" fullWidth disabled={loading}>
          {loading ? <CircularProgress size={24} color="inherit" /> : '회원가입'}
        </Button>
      </form>
    </Box>
  );
}
