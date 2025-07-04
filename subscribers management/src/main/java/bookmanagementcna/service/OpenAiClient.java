package bookmanagementcna.service;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class OpenAiClient {

    private final WebClient webClient;

    public OpenAiClient() {
        this.webClient = WebClient.builder()
                .baseUrl("https://api.openai.com/v1")
                .defaultHeader("Authorization", "Bearer YOUR_API_KEY")
                .build();
    }

    public String generateSummary(String content) {
        String prompt = "다음 내용을 한국어로 500자 이내로 요약해줘:\n" + content;
        return "요약된 내용 예시...";
    }
}
