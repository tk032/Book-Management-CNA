package bookmanagementcna.external;

import org.springframework.stereotype.Service;

@Service
public class AISummaryService {
    public String summarize(String content) {
        return "요약된 전자책 내용입니다.";
    }
}

