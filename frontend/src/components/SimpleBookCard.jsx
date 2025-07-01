import React from 'react';
import { Card, CardMedia, Typography, CardActionArea, Box } from '@mui/material';
import { useNavigate } from 'react-router-dom';

function SimpleBookCard({ id, title, imageUrl }) {
  const navigate = useNavigate();

  const handleClick = () => {
    navigate(`/books/${id}`);
  };

  return (
    <Card sx={{ boxShadow: 3, textAlign: 'center', p: 1 }}>
      <CardActionArea onClick={handleClick}>
        <CardMedia
          component="img"
          image={imageUrl}
          alt={title}
          sx={{ width: 180, height: 260, margin: '0 auto', objectFit: 'contain' }}
        />
        <Box mt={1}>
          <Typography variant="subtitle1" fontWeight="bold">
            {title}
          </Typography>
        </Box>
      </CardActionArea>
    </Card>
  );
}

export default SimpleBookCard;
