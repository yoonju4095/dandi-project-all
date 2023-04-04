package com.kh.dandi.web.form.notice;

import lombok.Data;

@Data
public class DetailForm {
  private Long id;      // 공지아이디
  private String title;       // 제목
  private String content;    // 본문
  private String author;      // 작성자
  private Long hit;           // 조회수
  private Long cDate;         // 생성일시
  private Long uDate;         // 변경일시
}
