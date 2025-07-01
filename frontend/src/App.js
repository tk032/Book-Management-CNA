import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';

import MainPage from './pages/MainPage';
import NewBook from './pages/NewBook';
import GenerateImage from './pages/GenerateImage';
import BookListPage from './pages/BookListPage';
import BookDetailPage from './pages/BookDetailPage';
import BookEditPage from './pages/BookEditPage';
import BookSubmitPage from './pages/BookSubmitPage';
import Header from './components/Header';
import LoginPage from './pages/LoginPage';
import SignupPage from './pages/SignupPage';


function App() {
  return (
    <Router>
      <Header/>
      <Routes>
        <Route path="/" element={<MainPage />} />
        <Route path="/new-book" element={<NewBook />} />
        <Route path="/generate-image" element={<GenerateImage />} />
        <Route path="/books" element={<BookListPage />} />
        <Route path="/books/:id" element={<BookDetailPage />} />
        <Route path="/books/:id/edit" element={<BookEditPage />} />
        <Route path="/write" element={<BookSubmitPage />} />
        <Route path="/login" element={<LoginPage />} />
        <Route path="/signup" element={<SignupPage />} />
       </Routes>
    </Router>
  );
  
}

export default App;