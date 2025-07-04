import { useEffect, useState } from 'react';
import {
  Container,
  Typography,
  TextField,
  Button,
  Box,
  CircularProgress,
} from '@mui/material';
import { generateImage } from '../openAiService';
import { useParams, useNavigate } from 'react-router-dom';
import { fetchBookById, updateBook, deleteBook } from '../api/bookservice';

function BookEditPage() {
  const { id } = useParams();
  const navigate = useNavigate();

  const TITLE_LIMIT = 30;
  const CONTENT_LIMIT = 500;

  const [formData, setFormData] = useState({
    title: '',
    content: '',
    createdAt: '',
    updatedAt: '',
    coverImageUrl: '',
  });
  const [loading, setLoading] = useState(false);
  const [apiKey, setApiKey] = useState('');

  useEffect(() => {
    fetchBookById(id)
      .then(book => {// 백엔드 응답 구조에 맞게 수정
        setFormData({
          title: book.title,
          content: book.content,
          createdAt: book.createdAt,
          updatedAt: book.updatedAt,
          coverImageUrl: book.coverImageUrl || '',
        });
      })
      .catch(() => {
        alert('도서 정보를 불러오는 데 실패했습니다.');
        navigate('/books');
      });
  }, [id, navigate]);

  const handleChange = (field, limit) => (event) => {
    const newValue = event.target.value;
    if (newValue.length <= limit) {
      setFormData((prev) => ({ ...prev, [field]: newValue }));
    }
  };

  const handleGenerateCover = async () => {
    setLoading(true);
    try {
      const url = await generateImage(apiKey, formData.title, formData.content);
      setFormData((prev) => ({ ...prev, coverImageUrl: url }));
    } catch (err) {
      alert('이미지 생성 실패: ' + err.message);
    }
    setLoading(false);
  };

  const handleUpdate = async () => {
    try {
      await updateBook(id, formData);
      alert('도서가 수정되었습니다.');
      navigate('/books');
    } catch (err) {
      alert('수정 실패');
    }
  };

  const handleDelete = async () => {
    if (window.confirm('정말 삭제하시겠습니까?')) {
      try {
        await deleteBook(id);
        alert('도서가 삭제되었습니다.');
        navigate('/books');
      } catch (err) {
        alert('삭제 실패');
      }
    }
  };

  return (
    <Container maxWidth="md" sx={{ py: 6 }}>
      <Box sx={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', mb: 3 }}>
        <Typography variant="h5" fontWeight="bold">
          ✏️ {formData.title || '작품 수정'}
        </Typography>
        <Box sx={{ display: 'flex', gap: 1 }}>
          <Button variant="contained" size="small" onClick={handleUpdate}>
            수정
          </Button>
          <Button variant="outlined" size="small" color="error" onClick={handleDelete}>
            삭제
          </Button>
        </Box>
      </Box>

      <TextField
        label={`1. 제목 입력 (${formData.title.length}/${TITLE_LIMIT})`}
        value={formData.title}
        onChange={handleChange('title', TITLE_LIMIT)}
        fullWidth
        multiline
        rows={2}
        margin="normal"
      />

      <TextField
        label={`2. 작품 내용 (${formData.content.length}/${CONTENT_LIMIT})`}
        value={formData.content}
        onChange={handleChange('content', CONTENT_LIMIT)}
        fullWidth
        multiline
        rows={5}
        margin="normal"
      />

      <TextField
        label="3. OpenAI API Key"
        value={apiKey}
        onChange={(e) => setApiKey(e.target.value)}
        fullWidth
        margin="normal"
      />

      <Box sx={{ mt: 2 }}>
        <Button
          variant="contained"
          onClick={handleGenerateCover}
          disabled={loading}
        >
          {loading ? <CircularProgress size={24} /> : '이미지 생성'}
        </Button>
      </Box>

      {formData.coverImageUrl && (
        <Box sx={{ mt: 4, textAlign: 'center' }}>
          <img
            src={formData.coverImageUrl}
            alt="도서 커버 이미지"
            style={{ maxWidth: '100%', maxHeight: '400px' }}
          />
        </Box>
      )}
    </Container>
  );
}

export default BookEditPage;
