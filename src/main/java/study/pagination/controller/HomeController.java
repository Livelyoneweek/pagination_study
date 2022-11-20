package study.pagination.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import study.pagination.domain.AuditLog;
import study.pagination.repository.AuditLogRepository;

@Controller
@Slf4j
@RequiredArgsConstructor
public class HomeController {

    private final AuditLogRepository auditLogRepository;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/2")
    public String home2(Model model) {
        System.out.println("test");
        return "index3";
    }


    @GetMapping("/board/list")
    public String home4(Model model, @RequestParam("page") int page,
                        @RequestParam("size") int size,
                        @RequestParam("sortBy") String sortBy,
                        @RequestParam("isAsc") boolean isAsc) {

        Sort.Direction direction = isAsc ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction, sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<AuditLog> auditLogs = auditLogRepository.findAll(pageable);
        log.info("auditLogs.getTotalPages()={}", auditLogs.getTotalPages());
        log.info("auditLogs.getNumber()={}", auditLogs.getNumber());
        log.info("auditLogs.getTotalElements()={}", auditLogs.getTotalElements());
        log.info("auditLogs.isFirst()={}", auditLogs.isFirst());
        log.info("auditLogs.hasNext()={}", auditLogs.hasNext());
        log.info("auditLogs.getSize()={}", auditLogs.getSize());

        model.addAttribute("auditLogs", auditLogs);
        model.addAttribute("maxPage", 10);

        return "index4";
    }

    // http://localhost:8080/board/list?page=1&size=10&sortBy=auditLogLogId&isAsc=true

    @GetMapping("/test")
    @ResponseBody
    public String test() {
        for (int i = 0; i < 150; i++) {
            AuditLog auditLog = new AuditLog("partnerClientId" + i,
                    "authDestination", "signature" + i, "brokerTxId" + i);
            auditLog.setAuthDestination("NAVER");
            auditLogRepository.save(auditLog);
        }
        
        log.info("test data 삽입");
        return "ok";
    }
}
