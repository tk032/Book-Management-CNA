import BookCard from '../components/BookCard';
import { Typography, Grid, Box } from '@mui/material';
import LibraryBooksIcon from '@mui/icons-material/LibraryBooks';
import { useEffect, useState } from 'react';
import { fetchBooks } from '../api/bookservice';
import sampleCover from '../img/SampleCover.png';

function BookListPage() {
  const [books, setBooks] = useState([]);

  useEffect(() => {
    fetchBooks()
      .then((data) => setBooks(data))
      .catch((err) => console.error(err));
  }, []);

  return (
    <div>
      {/* 전체 콘텐츠 박스 */}
      <Box sx={{ py: 5, textAlign: 'center' }}>
        {/* 제목 (아이콘 + 텍스트) */}
        <Box sx={{ display: 'flex', justifyContent: 'center', alignItems: 'center', gap: 1, mb: 6 }}>
          <LibraryBooksIcon sx={{ fontSize: 36, color: 'primary.main' }} />
          <Typography variant="h4" fontWeight="bold">
            작품 목록
          </Typography>
        </Box>

        {/* 카드 묶음 컨테이너 */}
        <Box sx={{ display: 'flex', justifyContent: 'center' }}>
          <Box sx={{ maxWidth: '1000px', width: '100%' }}>
            <Grid container spacing={4}>
              {books.map((book) => (
                <Grid item xs={12} sm={6} md={4} key={book.id}>
                  <BookCard
                    id={book.id}
                    title={book.title}
                    imageUrl={book.coverImageUrl || sampleCover}
                    showTitleFirst={true}
                  />
                </Grid>
              ))}
            </Grid>
          </Box>
        </Box>
      </Box>
    </div>
  );
}

export default BookListPage;
