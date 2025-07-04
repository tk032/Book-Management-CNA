import React from 'react';
import { Card, CardMedia, Typography, CardActionArea } from '@mui/material';
import { useNavigate } from 'react-router-dom';

function BookCard({ id, title, imageUrl, showTitleFirst = true }) {
  const navigate = useNavigate();

  const handleClick = () => {
    navigate(`/books/${id}`);
  };

  return (
    <Card sx={{ textAlign: 'center', boxShadow: 3, p: 1 }}>
      <CardActionArea onClick={handleClick}>
        {showTitleFirst && (
          <Typography variant="subtitle1" sx={{ fontWeight: 'bold', mb: 1 }}>
            {title}
          </Typography>
        )}
        <CardMedia
          component="img"
          image={imageUrl}
          alt={title}
          sx={{ width: 150, height: 220, margin: '0 auto', objectFit: 'contain' }}
        />
        {!showTitleFirst && (
          <Typography variant="subtitle1" sx={{ fontWeight: 'bold', mt: 1 }}>
            {title}
          </Typography>
        )}
      </CardActionArea>
    </Card>
  );
}

export default BookCard;
