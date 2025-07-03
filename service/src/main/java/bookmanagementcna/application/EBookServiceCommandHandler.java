package bookmanagementcna.domain;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EBookServiceCommandHandler {

    private final ServiceRepository serviceRepository;

    public EBookServiceCommandHandler(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    public void handlePublishRequest(Long id) {
        EBookService ebookService = serviceRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("EBookService not found"));

        ebookService.requestPublish();
        serviceRepository.save(ebookService);
    }

    public void handleBestSellerIdentify(Long id) {
        EBookService ebookService = serviceRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("EBookService not found"));

        ebookService.markAsBestSeller();
        serviceRepository.save(ebookService);
    }

    public void handleEbookSummary(Long id, String summary) {
        EBookService ebookService = serviceRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("EBookService not found"));

        ebookService.summarizeEbook(summary);
        serviceRepository.save(ebookService);
    }

    public void handleAiCoverImage(Long id, String imageUrl) {
        EBookService ebookService = serviceRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("EBookService not found"));

        ebookService.updateCoverImage(imageUrl);
        serviceRepository.save(ebookService);
    }
}
