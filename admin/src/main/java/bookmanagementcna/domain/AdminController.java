package bookmanagementcna.domain;

import bookmanagementcna.dto.ApproveRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/admins")
public class AdminController {

    private final AdminRepository adminRepository;

    @PostMapping("/approve-author")
    public String approveAuthor(@RequestBody ApproveRequest request) {
        Admin admin = new Admin();
        admin.setRequestId(request.getRequestId());
        admin.setTargetId(request.getTargetId());
        admin.setRequestType("AUTHOR");
        admin.setStatus("APPROVED");
        admin.setAdminId(request.getAdminId());
        admin.setMessage(request.getMessage());
        admin.setRequestedAt(new java.util.Date());

        admin.approveAuthor();
        adminRepository.save(admin);
        return "작가 승인 완료";
    }

    @PostMapping("/approve-book")
    public String approveBook(@RequestBody ApproveRequest request) {
        Admin admin = new Admin();
        admin.setRequestId(request.getRequestId());
        admin.setTargetId(request.getTargetId());
        admin.setRequestType("BOOK");
        admin.setStatus("APPROVED");
        admin.setAdminId(request.getAdminId());
        admin.setMessage(request.getMessage());
        admin.setRequestedAt(new java.util.Date());

        admin.approveBook();
        adminRepository.save(admin);
        return "책 승인 완료";
    }

    @PostMapping("/resolve-report")
    public String resolveReport(@RequestBody ApproveRequest request) {
        Admin admin = new Admin();
        admin.setRequestId(request.getRequestId());
        admin.setTargetId(request.getTargetId());
        admin.setRequestType("REPORT");
        admin.setStatus("RESOLVED");
        admin.setAdminId(request.getAdminId());
        admin.setMessage(request.getMessage());
        admin.setRequestedAt(new java.util.Date());

        admin.resolveReport();
        adminRepository.save(admin);
        return "신고 처리 완료";
    }

    @PostMapping("/{id}/approve-login")
    public String approveLogin(@PathVariable Long id) {
        Admin admin = adminRepository.findById(id).orElseThrow();
        admin.approveLogin();
        adminRepository.save(admin);
        return "Login Approved";
    }

    @PostMapping("/{id}/approve-logout")
    public String approveLogout(@PathVariable Long id) {
        Admin admin = adminRepository.findById(id).orElseThrow();
        admin.approveLogout();
        adminRepository.save(admin);
        return "Logout Approved";
    }
}
