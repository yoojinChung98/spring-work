package com.spring.myweb.freeboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;

import com.spring.myweb.freeboard.dto.FreeContentResponseDTO;
import com.spring.myweb.freeboard.dto.FreeRegistRequestDTO;
import com.spring.myweb.freeboard.dto.FreeUpdateRequestDTO;
import com.spring.myweb.freeboard.service.IFreeBoardService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/freeboard")
@RequiredArgsConstructor
public class FreeBoardController {

	private final IFreeBoardService service;
	
	@GetMapping("/freeList")
	public void freeList(Model model) {
		System.out.println("/freeboard/freeList: GET");
		model.addAttribute("boardList",service.getList());
	}
	
	//글쓰기 페이지 열어주는 메서드
	@GetMapping("/freeRegist")
	public void regist() {}
	
	
	//글 등록 처리
	@PostMapping("/freeRegist")
	public String regist(FreeRegistRequestDTO dto) {
		System.out.println("/freeboard/freeRegist: POST");
		service.regist(dto);
		return "redirect:/freeboard/freeList";
	}
	
	//글 상세보기 처리
	@GetMapping("/content")
	public String getContent(int bno, Model model) {
		model.addAttribute("article",service.getContent(bno));
		return "freeboard/freeDetail";
	}
	
	//글 수정페이지 이동요청 (DB 가지 않고 입력받은 값을 바로 페이지로 보낼 것임)
	@PostMapping("/modPage")
	public String modPage(@ModelAttribute("article") FreeUpdateRequestDTO dto) {
		return "freeboard/freeModify";
	}
	
	//글 수정 요청
	@PostMapping("/modify")
	public String update(FreeUpdateRequestDTO dto, Model model) {
		service.update(dto);
		//model.addAttribute("article", service.getContent(dto.getBno()));
		//return "freeboard/freeDetail";
		return "redirect:/freeboard/content?bno="+dto.getBno();
	}

	
	//글 삭제 요청
	@PostMapping("/delete")
	public String delete(int bno) {
		service.delete(bno);
		return "redirect:/freeboard/freeList";
	}


	
	
	
}
