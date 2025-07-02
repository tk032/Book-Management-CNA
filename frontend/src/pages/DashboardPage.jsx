import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import {
  getSubscriber,
  addPoints,
  readContent,
  subscribePlan,
  unsubscribePlan
} from '../api/subscriberService';
import { logout } from '../api/authService';
import {
  Container, Typography, Button, TextField,
  Alert, Box, Stack
} from '@mui/material';

export default function DashboardPage() {
  const navigate = useNavigate();
  const id = localStorage.getItem('subscriberId');
  const [user, setUser] = useState(null);
  const [error, setError] = useState(null);
  const [amt, setAmt] = useState(0);
  const [pt, setPt] = useState(0);

  const fetchStatus = async () => {
    try {
      const { data } = await getSubscriber(id);
      setUser(data);
    } catch (err) {
      setError('상태 조회 실패');
    }
  };

  useEffect(() => { fetchStatus(); }, []);

  const handleAdd = async () => {
    try { await addPoints(id, amt); fetchStatus(); }
    catch { setError('포인트 충전 실패'); }
  };
  const handleRead = async () => {
    try { await readContent(id, pt); fetchStatus(); }
    catch { setError('열람 실패'); }
  };
  const handleSub = async () => { try { await subscribePlan(id); fetchStatus(); } catch { setError('가입 실패'); } };
  const handleUnsub = async () => { try { await unsubscribePlan(id); fetchStatus(); } catch { setError('해지 실패'); } };
  const handleLogout = async () => {
    try { await logout(user.email); }
    catch {} finally {
      localStorage.clear();
      navigate('/login');
    }
  };

  if (!user) return <Typography>로딩 중…</Typography>;

  return (
    <Container maxWidth="sm">
      <Box sx={{ mt: 4 }}>
        <Typography variant="h5" gutterBottom>회원 정보</Typography>
        {error && <Alert severity="error" sx={{ mb:2 }}>{error}</Alert>}
        <Typography>이름: {user.name}</Typography>
        <Typography>이메일: {user.email}</Typography>
        <Typography>포인트: {user.points}</Typography>
        <Typography>지역: {user.region}</Typography>
        <Typography>구독: {user.subscribed ? 'O' : 'X'}</Typography>

        <Stack spacing={2} sx={{ mt: 3 }}>
          <TextField label="충전 금액" type="number" value={amt}
                     onChange={e => setAmt(+e.target.value)} />
          <Button variant="contained" onClick={handleAdd}>포인트 충전</Button>

          <TextField label="열람 포인트" type="number" value={pt}
                     onChange={e => setPt(+e.target.value)} />
          <Button variant="contained" onClick={handleRead}>책 열람</Button>

          <Button variant="contained" onClick={handleSub}>요금제 가입</Button>
          <Button variant="outlined" onClick={handleUnsub}>요금제 해지</Button>

          <Button variant="text" color="error" onClick={handleLogout}>로그아웃</Button>
        </Stack>
      </Box>
    </Container>
  );
}