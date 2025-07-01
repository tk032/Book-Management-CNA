import axios from 'axios';

const api = axios.create({
  baseURL: 'http://localhost:8080',
  timeout: 5000,
});

// 도서 목록 조회
export const fetchBooks = async () => {
  try {
    const response = await api.get('/books');
    return response.data.data; // 'data' 필드에서 실제 데이터 추출
  } catch (error) {
    console.error('fetchBooks error:', error);
    throw error;
  }
};

// 도서 상세 조회
export const fetchBookById = async (id) => {
  try {
    const response = await api.get(`/books/${id}`);
    console.log(response.data.data);
    return response.data.data;
  } catch (error) {
    console.error('fetchBookById error:', error);
    throw error;
  }
};

// 신규 도서 등록
export const createBook = async (bookData) => {
  try {
    console.log(bookData)
    const response = await api.post('/books', bookData);
    return response.data.data;
  } catch (error) {
    console.error('createBook error:', error);
    throw error;
  }
};

// 도서 정보 수정
export const updateBook = async (id, bookData) => {
  try {
    const response = await api.patch(`/books/${id}`, bookData);
    return response.data.data;
  } catch (error) {
    console.error('updateBook error:', error);
    throw error;
  }
};

// 도서 삭제
export const deleteBook = async (id) => {
  try {
    const response = await api.delete(`/books/${id}`);
    return response.data.data;
  } catch (error) {
    console.error('deleteBook error:', error);
    throw error;
  }
};
