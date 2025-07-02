function GenerateImageButton({ onClick, loading = false }) {
  return (
    <button
      onClick={onClick}
      disabled={loading}
      className={`w-full bg-green-600 text-white py-2 px-4 rounded hover:bg-green-700 transition
        ${loading ? 'opacity-50 cursor-not-allowed' : ''}`}
    >
      {loading ? '이미지 생성 중...' : '이미지 생성하기'}
    </button>
  );
}

export default GenerateImageButton;
