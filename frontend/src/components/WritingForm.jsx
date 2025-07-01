function WritingForm({ label, value, onChange, maxLength = 500, rows = 3 }) {
  return (
    <div className="mb-6">
      <label className="block font-semibold mb-1">{label}</label>
      <textarea
        className="w-full border p-2 rounded"
        value={value}
        onChange={(e) => onChange(e.target.value)}
        maxLength={maxLength}
        rows={rows}
      />
      <div className="text-sm text-right text-gray-600">
        {value.length}/{maxLength}
      </div>
    </div>
  );
}

export default WritingForm;