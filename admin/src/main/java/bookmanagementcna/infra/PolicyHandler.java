package bookmanagementcna.infra;

import bookmanagementcna.config.kafka.KafkaProcessor;
import bookmanagementcna.domain.*;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PolicyHandler {

    @Autowired
    AdminRepository adminRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='RegistrationRequested'"
    )
    public void wheneverRegistrationRequested(@Payload RegistrationRequested event) {
        System.out.println("\n##### listener RequestRegister : " + event + "\n");

        Admin admin = new Admin();
        admin.setRequestId(event.getId());
        admin.setRequestType("AUTHOR");
        admin.setStatus("APPROVED");
        admin.setRequestedAt(new java.util.Date());

        adminRepository.save(admin);
        admin.approveAuthor();
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='Login'"
    )
    public void wheneverLoginEvent(@Payload Login event) {
        System.out.println("\n##### listener LoginEvent : " + event + "\n");

        Admin admin = new Admin();
        admin.setRequestId(event.getId());
        admin.setRequestType("LOGIN");
        admin.setStatus("APPROVED");
        admin.setRequestedAt(new java.util.Date());

        adminRepository.save(admin);

        admin.approveLogin();
        // 별도로 Login 이벤트 발행 원하면 추가 가능
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='Logout'"
    )
    public void wheneverLogoutEvent(@Payload Logout event) {
        System.out.println("\n##### listener LogoutEvent : " + event + "\n");

        Admin admin = new Admin();
        admin.setRequestId(event.getId());
        admin.setRequestType("LOGOUT");
        admin.setStatus("APPROVED");
        admin.setRequestedAt(new java.util.Date());

        adminRepository.save(admin);

        admin.approveLogout();
    }
}
