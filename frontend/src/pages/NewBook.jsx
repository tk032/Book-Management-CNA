import { useState } from 'react';
import { useNavigate } from 'react-router-dom';

import Header from '../components/Header';
import WritingForm from '../components/WritingForm';
import NextButton from '../components/NextButton';

function NewBook() {
  const [title, setTitle] = useState('');
  const [description, setDescription] = useState('');
  const [content, setContent] = useState('');
  const navigate = useNavigate();

  const handleNext = () => {
    const formData = { title, description, content };
    console.log('입력된 책 데이터:', formData);
    navigate('/generate-image');
  };

  return (
    <div>
      <Header />
      <main className="p-6 space-y-6 max-w-3xl mx-auto">
        <h2 className="text-2xl font-bold">📘 작품 등록</h2>

        <WritingForm
          label="1. 제목 입력"
          value={title}
          onChange={setTitle}
          rows={2}
        />

        <WritingForm
          label="2. 작품 소개"
          value={description}
          onChange={setDescription}
          rows={3}
        />

        <WritingForm
          label="3. 작품 내용"
          value={content}
          onChange={setContent}
          rows={5}
        />

        <NextButton onClick={handleNext} />
      </main>
    </div>
  );
}

export default NewBook;
