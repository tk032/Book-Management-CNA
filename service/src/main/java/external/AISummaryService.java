package bookmanagementcna.external;

import org.springframework.stereotype.Service;

@Service
public class AISummaryService {
    public String summarize(String bookContent) {
        return "요약된 전자책 내용입니다.";  // 실제 GPT 요약 결과 리턴
    }
}
