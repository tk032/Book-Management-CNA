import React from 'react';
import { Container, Typography, Button, Box } from '@mui/material';
import { useNavigate } from 'react-router-dom';

export default function AuthorPage() {
  const navigate = useNavigate();

  return (
    <Container maxWidth="md" sx={{ py: 6 }}>
      <Typography variant="h4" fontWeight="bold" gutterBottom>
        📑 작가 페이지
      </Typography>
      <Box sx={{ display: 'flex', gap: 2, mt: 4 }}>
        <Button
          variant="contained"
          onClick={() => navigate('/drafts')}
        >
          임시 저장
        </Button>
        <Button
          variant="contained"
          onClick={() => navigate('/books')}
        >
          최종 저장
        </Button>
        <Button
          variant="contained"
          onClick={() => navigate('/write')}
        >
          출간 요청
        </Button>
      </Box>
    </Container>
  )
}