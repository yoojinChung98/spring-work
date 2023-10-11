package com.spring.myweb.freeboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;

import com.spring.myweb.freeboard.dto.FreeContentResponseDTO;
import com.spring.myweb.freeboard.dto.FreeRegistRequestDTO;
import com.spring.myweb.freeboard.service.IFreeBoardService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/freeboard")
@RequiredArgsConstructor
public class FreeBoardController {

	private final IFreeBoardService service;
	
	@GetMapping("/freeList")
	public void freeList(Model model) {
		System.out.println("/freboard/freeList: GET");
		model.addAttribute("boardList",service.getList());
	}
	
	
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
		model.addAttribute("Freeboard",service.getContent(bno));
		return "freeboard/freeDetail";
	}
	

	
	
	
}
