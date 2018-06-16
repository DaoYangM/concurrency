package top.daoyang.concurrency.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.daoyang.concurrency.utils.RequestHolder;

@RestController
@RequestMapping("/threadLocal")
public class ThreadController {

    @GetMapping("/id")
    public Long getId() {
        return RequestHolder.get();
    }
}
