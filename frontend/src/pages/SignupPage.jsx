import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { signup } from '../api/authService';
import { Container, TextField, Button, Typography, Alert, Box } from '@mui/material';

export default function SignupPage() {
  const navigate = useNavigate();
  const [form, setForm] = useState({ email: '', password: '', name: '', region: '' });
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(false);

  const handleChange = e => setForm(prev => ({ ...prev, [e.target.name]: e.target.value }));

  const handleSubmit = async e => {
    e.preventDefault();
    setLoading(true);
    setError(null);
    try {
      await signup(form);
      navigate('/login');
    } catch (err) {
      setError(err.response?.data?.message || '회원가입에 실패했습니다.');
    } finally {
      setLoading(false);
    }
  };

  return (
    <Container maxWidth="xs">
      <Box sx={{ mt: 8 }}>
        <Typography variant="h5" align="center" gutterBottom>회원가입</Typography>
        {error && <Alert severity="error" sx={{ mb: 2 }}>{error}</Alert>}
        <form onSubmit={handleSubmit}>
          <TextField label="이메일" name="email" type="email" fullWidth required
                     value={form.email} onChange={handleChange} sx={{ mb: 2 }} />
          <TextField label="비밀번호" name="password" type="password" fullWidth required
                     value={form.password} onChange={handleChange} sx={{ mb: 2 }} />
          <TextField label="이름" name="name" type="text" fullWidth required
                     value={form.name} onChange={handleChange} sx={{ mb: 2 }} />
          <TextField label="지역" name="region" type="text" fullWidth required
                     value={form.region} onChange={handleChange} sx={{ mb: 3 }} />
          <Button type="submit" variant="contained" fullWidth disabled={loading}>
            {loading ? '로딩 중…' : '회원가입'}
          </Button>
        </form>
      </Box>
    </Container>
  );
}