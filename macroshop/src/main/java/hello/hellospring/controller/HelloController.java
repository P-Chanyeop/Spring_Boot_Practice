package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class HelloController {

    @GetMapping("hello")
    public String Hello(Model model){
        model.addAttribute("data", "hello!!");
        return "hello";
    }

    @GetMapping("hello-mvc")
    // 파라미터 정보보기 단축키 = ctrl + p
    public String helloMvc(@RequestParam(name = "name") String name, Model model) {
        model.addAttribute("name", name);

        return "hello-template";
    }

    @GetMapping("hello-string")
    // 응답 body 부에 직접 데이터를 넣어 보내겠다.
    // view가 존재하지 않음.
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
        return "hello" + name;  // "hello {name}"
    }

    // 데이터를 전달할 때,
    @GetMapping("hello-api")
    // HttpMessageConverter가 작동하여,
    // 문자라면 StringConverter, 객체라면 JsonConverter를 구분하여 처리.
    // 문자처리 : StringHttpMessageConverter, 객체처리 : MappingJackson2HttpMessageConverter(객체를 JSON 화 시키는 라이브러리)
    // 문자가 아니고 객체를 리턴해야하면
    // JSON 방식으로 데이터를 만들어 http 응답 수행.
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName("박찬엽");

        // JSON 데이터로 반환이된다. JavaScript Object Notation.
        // 키-값의 쌍으로 이루어진 데이터.
        // 레거시의 경우 xml을 사용할 수도 있음.

        return hello;
    }

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
