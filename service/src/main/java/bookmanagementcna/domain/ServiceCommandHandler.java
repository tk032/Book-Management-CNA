package your.bookmanagementcna.name.application;

import your.bookmanagementcna.name.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ServiceCommandHandler {

    private final ServiceRepository serviceRepository;

    public ServiceCommandHandler(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    public void handlePublishRequest(Long id) {
        Service service = serviceRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Service not found"));

        service.requestPublish();
        serviceRepository.save(service);
    }

    public void handleBestSellerIdentify(Long id) {
        Service service = serviceRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Service not found"));

        service.markAsBestSeller();
        serviceRepository.save(service);
    }

    public void handleEbookSummary(Long id, String summary) {
        Service service = serviceRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Service not found"));

        service.summarizeEbook(summary);
        serviceRepository.save(service);
    }

    public void handleAiCoverImage(Long id, String imageUrl) {
        Service service = serviceRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Service not found"));

        service.updateCoverImage(imageUrl);
        serviceRepository.save(service);
    }
}
