package study.pagination.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class TemplateController {

    @GetMapping("/layout/home")
    public String layoutHome() {
        log.info("come");
        return "/layout/home";
    }
}
