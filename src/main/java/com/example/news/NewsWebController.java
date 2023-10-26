package com.example.news;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/news")
public class NewsWebController {
  final NewsDAO dao;

  @Value("${news.imgdir}")
  String imgDir;

  @Autowired
  public NewsWebController(NewsDAO dao) {
    this.dao = dao;
  }


//  목록보기
  @GetMapping("/list")
  public String list(Model m) {
    List<News> newsList;
    try {
      newsList = dao.getAll();
      m.addAttribute("newsList", newsList);
      System.out.println("리스트 받아오기 완료");
    } catch (SQLException e) {
      e.printStackTrace();
      log.info("뉴스 목록 가져오기에서 문제 발생");
      m.addAttribute("error", "뉴스 목록 보기가 정상적으로 처리되지 않았습니다.");
    }
    return "news/newsList";
  }





//  등록하기
  @PostMapping("/add")
  public String addNews(@RequestParam("title") String title,
                        @RequestParam("content") String content,
                        @RequestParam("img")MultipartFile img,
                        Model m) {
    try {
    File dest = new File(imgDir + img.getOriginalFilename());
    News news = new News();
    news.setTitle(title);
    news.setContent(content);
    news.setImg("/img/"+dest.getName());
    int res = dao.addNews(news);
      if(res == 1) img.transferTo(dest);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "redirect:/news/list";
  }

//  삭제하기
//  상세보기
@GetMapping("")
public String getId(@RequestParam("aid") int aid, Model m) {
  System.out.println("아이디는 "+aid);
  News news = null;
  try {
    news = dao.getNews(aid);
    m.addAttribute(news);
  } catch (SQLException e) {
    e.printStackTrace();
  }
  return "news/newsView";
}
}
