import SimpleBookCard from '../components/SimpleBookCard';
import { Container, Typography, Grid, Box } from '@mui/material';
import { useEffect, useState } from 'react';
import { fetchBooks } from '../api/bookservice';
import sampleCover from "../img/SampleCover.png";

function MainPage() {
  const [books, setBooks] = useState([]);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await fetchBooks();
        if (Array.isArray(response)) {
          setBooks(response.slice(0, 3));
        }
      } catch (error) {
        console.error('도서 불러오기 실패:', error);
      }
    };
    fetchData();
  }, []);

  return (
    <div>
      <Container maxWidth="lg" sx={{ py: 5 }}>
        <Box textAlign="center" mb={4}>
          <Typography variant="h4" fontWeight="bold">
            이달의 책 👑
          </Typography>
        </Box>
        <Grid container spacing={4} justifyContent="center">
          {books.map((book) => (
            <Grid item key={book.id}>
              <SimpleBookCard
                id={book.id}
                title={book.title}
                imageUrl={book.coverImageUrl || sampleCover}
              />
            </Grid>
          ))}
        </Grid>
      </Container>
    </div>
  );
}

export default MainPage;