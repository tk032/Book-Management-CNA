//  상품 자동 등록 API 
package bookmanagementcna.api;

import bookmanagementcna.domain.EBookServiceCommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product-registration")
public class ProductAutomaticallyRegisteredController {

    @Autowired
    private EBookServiceCommandHandler commandHandler;

    @PostMapping
    public void register(@RequestParam Long publicationId) {
        // 예시용: handler.handleProductRegister(publicationId); 구현 필요
    }
}
