package com.example.sample;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/api")
public class TestRestController {

  @GetMapping("/hello")
  public String sayHello() {
    return "hello";
  }

  @GetMapping("/hello2")
  public String sayHello2(@RequestParam(value = "msg", required = false) String msg) {
    return msg;
  }

  @PostMapping("/hello3/{id}")
  public String sayHello3(@PathVariable("id") int id) {
    return "hello " + id;
  }

//  # view를 사용하지 않기 때문에 필요없는 예제임
//  @GetMapping("/hello4")
//  public String sayHello4(@RequestParam("msg") String msg, Model model) {
//    model.addAttribute("msg", msg);
//    return "hello";
//  }

  @GetMapping("/hello4")
  public HashMap<String, String> hello4() {
    HashMap<String, String> map = new HashMap<>();
    map.put("id", "hong");
    map.put("name", "홍길동");
    map.put("nationality", "Korea");
    return map;
  }
}
