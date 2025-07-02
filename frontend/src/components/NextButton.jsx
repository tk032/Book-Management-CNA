function NextButton({ onClick, disabled = false, children = "다음 단계" }) {
  return (
    <button
      onClick={onClick}
      disabled={disabled}
      className={`w-full bg-blue-800 text-white py-2 px-4 rounded hover:bg-blue-900 transition
        ${disabled ? 'opacity-50 cursor-not-allowed' : ''}`}
    >
      {children}
    </button>
  );
}

export default NextButton;
