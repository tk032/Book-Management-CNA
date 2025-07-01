package bookmanagementcna.external;

import org.springframework.stereotype.Service;

@Service
public class AICoverImageService {
    public String generate(String title, String description) {
        return "https://image-server.com/ai-cover/123.png";  // 실제 GPT 기반 이미지 URL
    }
}
