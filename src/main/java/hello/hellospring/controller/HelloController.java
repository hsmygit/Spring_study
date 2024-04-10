package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data","hello!!");
        return "hello";
    }
//MVC 실습. (웹 브라우져에 렌더링한 HTML을 넘겨주는 방식.)
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value = "name") String name, Model model){
        model.addAttribute("name", name);
        return "hello-template";
    }
//API방식 실습. (데이터로 바로 내려보내기.문자열을 바로 보낸 형식 - 거의 사용 x)
    @GetMapping("hello-string")
    @ResponseBody // http 통신 프로토콜의 응답body부분에 return 데이터를 직접 넣어 준다는 뜻.
    public String helloString(@RequestParam("name") String name){
        return "hello " + name;
    }
//API방식의 데이터를 보내는 방식 (이걸 주로 사용.)
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    } // spring 기본 세팅: 객체를 반환하고 @ResponseBody를 해 놓으면 json 형식으로 반환 하도록 되어 있다.
/**mvc방식의 웹브라우져로 부터 요청이 들어오면 viewResolver에게 넘겨서 맞는 템플릿을 찾아서 돌려주는 방식과 달리
    API방식은 view없이 HTTP의 응답에 그냥 그대로 이 데이터를 넘기는 방식으로 동작을 한다.
    (문자가 온다면 : 문자를 그대로 http응답에 반환(StringConverter 동작), 
    객체가 오면 : 데이터를 json형식으로 만들어 http응답에 반환.(JsonConverter 동작))**/
    static class Hello{
        private String name;//key : name value : 위에 String name

        public String getName() {
            return name;
        }

        public void setName(String name){
            this.name = name;
        }
    }// getter, setter 단축키 : ALT + Insert
}
