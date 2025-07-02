import React, { useEffect, useState } from 'react';
import { Container, Typography, Box, Chip, CircularProgress, Alert } from '@mui/material';
import { getSubscriber } from '../api/subscriberService';

export default function MyPage() {
  const [joinStatus, setJoinStatus] = useState(null);
  const [point, setPoint] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    async function fetchData() {
      try {
        const response = await getSubscriber();        // GET /subscribers/1
        const data = response.data;
        setJoinStatus(data.joinStatus);
        setPoint(data.point);
      } catch (err) {
        setError('정보 조회에 실패했습니다.');
      } finally {
        setLoading(false);
      }
    }
    fetchData();
  }, []);

  if (loading) {
    return (
      <Container sx={{ mt: 4, textAlign: 'center' }}>
        <CircularProgress />
      </Container>
    );
  }

  if (error) {
    return (
      <Container sx={{ mt: 4 }}>
        <Alert severity="error">{error}</Alert>
      </Container>
    );
  }

  return (
    <Container sx={{ mt: 4 }}>
      <Typography variant="h4" gutterBottom>
        내 정보
      </Typography>

      <Box sx={{ mb: 2 }}>
        <Chip
          label={joinStatus ? '가입됨' : '미가입'}
          color={joinStatus ? 'success' : 'default'}
        />
      </Box>

      <Box>
        <Typography variant="h6">보유 포인트</Typography>
        <Typography variant="h3">
          {point.toLocaleString()} P
        </Typography>
      </Box>
    </Container>
  );
}
