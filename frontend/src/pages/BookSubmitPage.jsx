import { useState } from 'react';
import {
  Container,
  Typography,
  TextField,
  Button,
  Box,
  CircularProgress,
} from '@mui/material';
import { generateImage } from '../openAiService';
import { useLocation, useNavigate } from 'react-router-dom';
import {createBook} from '../api/bookservice';

function BookSubmitPage() {
  const [apiKey, setApiKey] = useState('');
  const navigate = useNavigate();
  const location = useLocation();
  const { title, content } = location.state || {};

  const TITLE_LIMIT = 30;
  const CONTENT_LIMIT = 500;

  const [formData, setFormData] = useState({
    title: title || '',
    content: content || '',
    coverImageUrl: '',
  });

  const [loading, setLoading] = useState(false);

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

  const handleSubmit = async () => {
    try {
      if (formData.title.length > TITLE_LIMIT || formData.content.length > CONTENT_LIMIT) {
        alert('제한된 글자 수를 초과했습니다.');
        return;
      }

      await createBook(formData);
      alert('도서 등록 성공!');
      navigate('/books');
      setFormData({ title: '', content: '', coverImageUrl: '' });
    } catch {
      alert('도서 등록 실패');
    }
  };

  return (
    <div>
      <Container maxWidth="md" sx={{ py: 6 }}>
        <Typography variant="h5" fontWeight="bold" gutterBottom>
          📘 작품 등록
        </Typography>

        <TextField
          label={`1. 제목 입력 (${formData.title.length}/${TITLE_LIMIT})`}
          value={formData.title}
          onChange={handleChange('title', TITLE_LIMIT)}
          fullWidth
          multiline
          rows={2}
          margin="dense"
        />

        <TextField
          label={`2. 작품 내용 (${formData.content.length}/${CONTENT_LIMIT})`}
          value={formData.content}
          onChange={handleChange('content', CONTENT_LIMIT)}
          fullWidth
          multiline
          rows={5}
          margin="dense"
        />

        <TextField
          label="3. OpenAI API Key"
          value={apiKey}
          onChange={(e) => setApiKey(e.target.value)}
          fullWidth
          margin="dense"
        />

        <Box sx={{ mt: 1 }}>
          <Button
            variant="contained"
            onClick={handleGenerateCover}
            disabled={loading}
          >
            {loading ? <CircularProgress size={24} /> : '이미지 생성'}
          </Button>
        </Box>

        <Box sx={{ mt: 4, minHeight: 200, textAlign: 'center', border: '1px dashed #ccc', borderRadius: 2, p: 2 }}>
          {formData.coverImageUrl ? (
            <>
              <Typography variant="subtitle1" gutterBottom>
                🖼️ 생성된 커버 이미지 미리보기
              </Typography>
              <img
                src={formData.coverImageUrl}
                alt="도서 커버 이미지"
                style={{ maxWidth: '100%', maxHeight: '400px', borderRadius: '8px' }}
              />
            </>
          ) : (
            <Typography variant="body2" color="text.secondary" sx={{ mt: 5 }}>
              생성된 이미지가 없습니다. <br />
              위의 버튼을 눌러 도서 커버를 생성해보세요.
            </Typography>
          )}
        </Box>

        <Box sx={{ mt: 4 }}>
          <Button
            variant="contained"
            fullWidth
            size="large"
            onClick={handleSubmit}
          >
            작품 등록
          </Button>
        </Box>
      </Container>
    </div>
  );
}

export default BookSubmitPage;