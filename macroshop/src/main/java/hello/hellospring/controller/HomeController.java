package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // 요청이 오면 관련 컨트롤러에 있는 매핑되어 있는 걸 찾고, 없으면 static index.html을
    // 띄우는데 지금은 있으므로 index.html이 무시된다.
    @GetMapping("/")
    public String home() {
        return "home";
    }
}
