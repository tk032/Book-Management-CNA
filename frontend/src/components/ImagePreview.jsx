function ImagePreview({ imageUrl }) {
  if (!imageUrl) {
    return (
      <div className="w-full h-64 bg-gray-100 flex items-center justify-center border rounded">
        <span className="text-gray-500">이미지를 생성하면 여기에 표시됩니다</span>
      </div>
    );
  }

  return (
    <div className="w-full h-auto border rounded overflow-hidden">
      <img src={imageUrl} alt="생성된 이미지" className="w-full object-contain" />
    </div>
  );
}

export default ImagePreview;
