import axios from "axios";

// AI 이미지 생성
export const generateImage = async (apiKey, title, content) => { 
  
  // PROMPT
  const prompt = `Create a book cover illustration based on the following content: "${content}". 
  The image should visually represent the mood and themes of the book. 
  Include the book title "${title}" as text on the cover.`;

  const response = await axios.post(
    "https://api.openai.com/v1/images/generations",
    {
      model: "dall-e-3",
      prompt,
      n: 1,
      size: "1024x1024",
    },
    {
      headers: {
        Authorization: `Bearer ${apiKey}`,   //Bearer 토큰 방식으로 API 키 인증
        "Content-Type": "application/json",  //JSON 형식식
      },
    }
  );

  return response.data.data[0].url;
};