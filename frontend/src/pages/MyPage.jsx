import React, { useState, useEffect } from 'react';
import { Container, Typography, Box, CircularProgress } from '@mui/material';
import { useNavigate } from 'react-router-dom';
import { getSubscriber } from '../api/api';

export default function MyPage() {
  const navigate = useNavigate();
  const stored = JSON.parse(localStorage.getItem('user'));
  const [userInfo, setUserInfo] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    if (!stored) {
      navigate('/login', { replace: true });
      return;
    }
    (async () => {
      try {
        const data = await getSubscriber(stored.id);
        setUserInfo(data);
      } catch (err) {
        console.error(err);
        alert('마이페이지 정보를 불러오지 못했습니다.');
      } finally {
        setLoading(false);
      }
    })();
  }, [stored, navigate]);

  if (loading) {
    return (
      <Container maxWidth="sm" sx={{ textAlign: 'center', py: 6 }}>
        <CircularProgress />
      </Container>
    );
  }

  return (
    <Container maxWidth="sm" sx={{ py: 6 }}>
      <Typography variant="h4" gutterBottom>
        내 정보
      </Typography>
      <Box sx={{ mt: 2 }}>
        <Typography>이메일: {userInfo.email}</Typography>
        <Typography>이름: {userInfo.name}</Typography>
        <Typography>주소: {userInfo.address || '정보 없음'}</Typography>
        <Typography>포인트: {userInfo.point}</Typography>
        <Typography>회원 상태: {userInfo.joinStatus ? '활성' : '비활성'}</Typography>
        <Typography>무제한 플랜: {userInfo.unlimitedPlan ? '구독 중' : '미구독'}</Typography>
      </Box>
    </Container>
  );
}
