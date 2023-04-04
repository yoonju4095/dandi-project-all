package com.kh.dandi.web;

import com.kh.dandi.dao.Notice;
import com.kh.dandi.svc.NoticeSVC;
import com.kh.dandi.web.form.notice.DetailForm;
import com.kh.dandi.web.form.notice.SaveForm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/notice")
@RequiredArgsConstructor
public class NoticeController {

  private final NoticeSVC noticeSVC;

  // 등록양식
  @GetMapping("/add")
  public String saveForm(Model model){
    model.addAttribute("saveForm", new SaveForm());
    return "notice/noticeAdd";
  }

  // 등록처리
  @PostMapping("/add")
  public String save(
      @Valid @ModelAttribute SaveForm saveForm,
      BindingResult bindingResult,
      RedirectAttributes redirectAttributes
    ){
    log.info("saveForm={}", saveForm);

    // 데이터 검증
    // 어노테이션 기반 검증
    if (bindingResult.hasErrors()){
      log.info("bindingResult={}", bindingResult);
      return "notice/noticeAdd";
    }

    // 등록
    Notice notice = new Notice();
//    notice.setNoticeId(saveForm.getNoticeId());
    notice.setTitle(saveForm.getTitle());
    notice.setContent(saveForm.getContent());
//    notice.setAuthor(saveForm.getAuthor());
//    notice.setHit(saveForm.getHit());
//    notice.setCDate(saveForm.getCDate());
//    notice.setUDate(saveForm.getUDate());

//    noticeSVC.save(notice);
    Long saveId = noticeSVC.save(notice);
    redirectAttributes.addAttribute("id", saveId);
    return "notice/detail";
  }

  // 조회
  @GetMapping("/{id}/detail")
  public String findById(
          @PathVariable("id") Long id,
          Model model
  ){
    Optional<Notice> findedNotice = noticeSVC.findById(id);
    Notice notice = findedNotice.orElseThrow();

    DetailForm detailForm = new DetailForm();
    detailForm.setId(notice.getId());
    detailForm.setTitle(notice.getTitle());
    detailForm.setContent(notice.getContent());
    detailForm.setAuthor(notice.getAuthor());
    detailForm.setCDate(notice.getCDate());
    detailForm.setUDate(notice.getUDate());

    model.addAttribute("detailForm", detailForm);
    return "notice/detailForm";
  }

//   수정양식
//  @GetMapping("/{noticeId/edit}")
//  public String updateForm(
//          @PathVariable("noticeId") Long noticeId,
//          Model model
//  ){
//    Optional<Notice> findedNotice = noticeSVC.findById(noticeId);
//    Notice notice = findedNotice.orElseThrow();
//
//    return null;
//  }


  // 수정

  // 삭제

  // 목록

}
