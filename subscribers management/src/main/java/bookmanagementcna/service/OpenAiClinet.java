package bookmanagementcna.service;

import org.springframework.stereotype.Component;

@Component

public class OpenAiClinet {
    
    private final WebClient webClient;

    public OpenAiClient() {
        this.webClient = WebClient.builder()
                .baseUrl("https://api.openai.com/v1")
                .defaultHeader("Authorization", "Bearer YOUR_API_KEY")
                .build();
    }
    public String generateSummary(String content) {
        // 실제 OpenAI API 요청 구성은 라이브러리 or REST 사용
        // 아래는 간단한 예시
    String prompt = "다음 내용을 한국어로 500자 이내로 요약해줘:\n" + content;

        // 예시 응답 반환
        return "요약된 내용 예시...";
    }
    
}
