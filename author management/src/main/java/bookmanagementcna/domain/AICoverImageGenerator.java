package bookmanagementcna.domain;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;
import org.json.JSONObject;

@Service
public class AICoverImageGenerator {

    private final String OPENAI_API_KEY = "your_OPENAI_API_KEY";
    private final String IMAGE_URL = "https://api.openai.com/v1/images/generations";

    public String generateCover(String title, String content, String keyword) {
        RestTemplate restTemplate = new RestTemplate();

        String prompt = "Create a book cover for a book titled '" + title +
                        "' with the summary: '" + content +
                        "' and the keyword: '" + keyword + "'.";

        JSONObject json = new JSONObject();
        json.put("prompt", prompt);
        json.put("n", 1);
        json.put("size", "512x512");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + OPENAI_API_KEY);
        

        HttpEntity<String> request = new HttpEntity<>(json.toString(), headers);
        ResponseEntity<String> response = restTemplate.postForEntity(IMAGE_URL, request, String.class);

        JSONObject body = new JSONObject(response.getBody());
        return body.getJSONArray("data").getJSONObject(0).getString("url");
    }
}
