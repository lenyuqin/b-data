package com.site.bdata.controller.common;


import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;



@Slf4j
@Api(tags = "公共跳转控制层")
@Controller
public class CommonController {

    @GetMapping({"/", "/index", "index.html"})
    public String index() {
        return "x-data/index";
    }

    @GetMapping("/welcome")
    public String welcome() {
        return "x-data/welcome";
    }

    @GetMapping("/test")
    public String test() {
        return "x-data/ranklist";
    }

    @GetMapping("/welcome1")
    public String welcome1() {
        return "x-data/welcome1";
    }

}
