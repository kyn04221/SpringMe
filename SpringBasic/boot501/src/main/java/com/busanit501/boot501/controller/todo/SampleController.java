package com.busanit501.boot501.controller.todo;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;


@Controller // 1)화면 제공 2) 데이터 제공
@Log4j2
@RequestMapping("/todo")
public class SampleController {

    @GetMapping("/list")
    public void hello(Model model) {
        model.addAttribute("msg", "list 화면 입니다");
    }

    @GetMapping("/register")
    public void ex1(Model model) {
        model.addAttribute("rgs", "register 화면 입니다");
        List<String> list = Arrays.asList("a", "b", "c");
        model.addAttribute("list", list);

    }
//
//    @GetMapping("/ex/ex2")
//    public void ex2(Model model) {
//        List<String> strList = IntStream.range(1, 10)
//                .mapToObj(i -> "임시 데이터 " +i)
//                .collect(Collectors.toList());
//        model.addAttribute("strList", strList);
//
//        Map<String,String> map = new HashMap<>();
//        map.put("a", "aaa");
//        map.put("b", "bbb");
//        model.addAttribute("map", map);
//
//        SampleDTO sampleDTO = SampleDTO.builder()
//                .p1("테스트 p1")
//                .p2("테스트 p2")
//                .p3("테스트 p3")
//                .p4("테스트 p4")
//                .build();
//        model.addAttribute("sampleDTO", sampleDTO);
//
//    }
//
//    @GetMapping("/ex/ex3")
//    public void ex3(Model model) {
//        List<String> list = Arrays.asList("a", "b", "c");
//        model.addAttribute("list", list);
//    }

}
