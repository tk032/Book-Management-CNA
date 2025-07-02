import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import Header from '../components/Header';
import ImagePreview from '../components/ImagePreview';
import GenerateImageButton from '../components/GenerateImageButton';
import NextButton from '../components/NextButton';

function GenerateImagePage() {
  const [imageUrl, setImageUrl] = useState('');
  const [apiKey, setApiKey] = useState('');
  const [loading, setLoading] = useState(false);
  const navigate = useNavigate();

  const bookContent = ''; // 예시 작품 내용

  const handleGenerate = async () => {
    setLoading(true);
    setTimeout(() => {
      setImageUrl('https://via.placeholder.com/512x300?text=AI+Image');
      setLoading(false);
    }, 2000);
  };

  const handleRegister = () => {
    console.log('작품 등록 완료!');
    console.log('API KEY:', apiKey);
    console.log('Image URL:', imageUrl);
    navigate('/books');
  };

  return (
    <div>
      <Header />
      <main className="p-6 space-y-6 max-w-3xl mx-auto">
        <h2 className="text-2xl font-bold">🎨 AI 이미지 생성</h2>

        <ImagePreview imageUrl={imageUrl} />

        <div>
          <label className="block font-semibold mb-1">작품 내용</label>
          <div className="border p-4 rounded bg-gray-50 whitespace-pre-wrap text-sm">
            {bookContent}
          </div>
        </div>

        <div>
          <label className="block font-semibold mb-1">API Key 입력</label>
          <input
            type="text"
            value={apiKey}
            onChange={(e) => setApiKey(e.target.value)}
            placeholder="OpenAI API Key 입력"
            className="w-full border p-2 rounded"
          />
        </div>

        <GenerateImageButton onClick={handleGenerate} loading={loading} />

        <NextButton onClick={handleRegister}>작품 등록</NextButton>
      </main>
    </div>
  );
}

export default GenerateImagePage;