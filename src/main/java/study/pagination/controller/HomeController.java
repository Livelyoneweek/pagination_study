package study.pagination.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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

    @GetMapping("/5")
    public String home5() {
        System.out.println("test");
        return "index5";
    }


//    @RequestParam("page") int page,
//    @RequestParam("size") int size,
//    @RequestParam("sortBy") String sortBy,
//    @RequestParam("isAsc") boolean isAsc

    @GetMapping("/board/list")
    public String home4(Model model,@PageableDefault(page=0,size=10,sort="auditLogLogId",direction=Sort.Direction.ASC)Pageable pageable,
                        @RequestParam(value = "searchKeyword" , required = false) String searchKeyword) {

        Page<AuditLog> auditLogs= null;
        if (searchKeyword == null) {
            auditLogs = auditLogRepository.findAll(pageable);
        } else {
            auditLogs = auditLogRepository.findBySignatureContaining(searchKeyword,pageable);
        }

        log.info("auditLogs.getTotalPages()={}", auditLogs.getTotalPages());
        log.info("auditLogs.getNumber()={}", auditLogs.getNumber());
        log.info("auditLogs.getTotalElements()={}", auditLogs.getTotalElements());
        log.info("auditLogs.isFirst()={}", auditLogs.isFirst());
        log.info("auditLogs.hasNext()={}", auditLogs.hasNext());
        log.info("auditLogs.getSize()={}", auditLogs.getSize());

        model.addAttribute("auditLogs", auditLogs);
        model.addAttribute("maxPage", 10);

//        return "index4";
        return "/layout/home";
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
