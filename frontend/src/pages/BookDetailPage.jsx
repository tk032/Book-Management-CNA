import React, { useEffect, useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import {
  Box,
  Typography,
  CardMedia,
  Container,
  Button,
  Divider
} from '@mui/material';
import { fetchBookById } from '../api/bookservice';
import dayjs from "dayjs";
import sampleCover from "../img/SampleCover.png";

function BookDetailPage() {
  const { id } = useParams();
  const navigate = useNavigate();
  const [book, setBook] = useState(null);

  useEffect(() => {
    fetchBookById(id)
      .then(data => setBook(data))
      .catch(err => console.error(err));
  }, [id]);

  if (!book) return <Typography>Loading...</Typography>;

  return (
    <Container sx={{ py: 5 }}>
      <Box sx={{ display: 'flex', justifyContent: 'flex-end', mb: 2 }}>
        <Button variant="outlined" onClick={() => navigate(`/books/${id}/edit`)}>작품 수정</Button>
      </Box>
      <Box sx={{ display: 'flex', gap: 4 }}>
        <CardMedia
          component="img"
          image={book.coverImageUrl || sampleCover}
          alt={book.title}
          sx={{ width: 200, height: 280, objectFit: 'contain' }}
        />
        <Box sx={{ flex: 1 }}>
          <Typography variant="h4" fontWeight="bold" sx={{ mb: 3 }}>{book.title}</Typography>
          <Typography variant="subtitle1" gutterBottom>
            작성일: {dayjs(book.createdAt).format('YYYY년 MM월 DD일 HH시 mm분')}
          </Typography>
          {book.updatedAt && book.updatedAt !== book.createdAt && (
              <Typography variant="subtitle1" gutterBottom>
                수정일: {dayjs(book.updatedAt).format('YYYY-MM-DD HH:mm:ss')}
              </Typography>
          )}
          <Divider sx={{ my: 2 }} />
          <Typography variant="body1" > 줄거리 </Typography>
          <Typography
              variant="body1"
              sx={{
                mt: 2,
                backgroundColor: '#f5f5f5',
                borderRadius: 2,
                padding: 2,
                lineHeight: 1.6,
              }}
          >
            {book.content}
          </Typography>
        </Box>
      </Box>
    </Container>
  );
}

export default BookDetailPage;
