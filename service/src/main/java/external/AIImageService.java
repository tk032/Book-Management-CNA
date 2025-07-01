package bookmanagementcna.external;

import com.theokanning.openai.OpenAiService;
import com.theokanning.openai.image.CreateImageRequest;
import com.theokanning.openai.image.ImageResult;
import org.springframework.stereotype.Service;

@Service
public class AIImageService {

    private static final String OPENAI_API_KEY = "sk-xxxx";

    public String generateCover(String title, String description) {
        OpenAiService service = new OpenAiService(OPENAI_API_KEY);

        CreateImageRequest request = CreateImageRequest.builder()
                .prompt("Book cover for: " + title + ". " + description)
                .n(1)
                .size("512x512")
                .build();

        ImageResult result = service.createImage(request);
        return result.getData().get(0).getUrl(); // 생성된 이미지 URL 반환
    }
}

