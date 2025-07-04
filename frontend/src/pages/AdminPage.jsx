import React from 'react';
import { Container, Typography, Box } from '@mui/material';
import { useParams } from 'react-router-dom';

export default function AdminPage() {
  const { id } = useParams();

  return (
    <Container maxWidth="md" sx={{ py: 6 }}>
      <Typography variant="h4" fontWeight="bold" gutterBottom>
        🛠 관리자 페이지
      </Typography>
      <Typography variant="subtitle1" color="textSecondary">
        관리자 ID: <strong>{id}</strong>
      </Typography>
      <Box sx={{ mt: 4 }}>
        <Typography>여기에 관리자 전용 기능을 구현하세요.</Typography>
      </Box>
    </Container>
  );
}
