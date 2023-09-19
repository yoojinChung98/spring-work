package com.spring.basic.score.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.basic.score.dto.ScoreRequestDTO;
import com.spring.basic.score.service.ScoreService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/score")
@RequiredArgsConstructor //: fianl 필드가 존재한다면 그것은(꼭 필요로하는 값은) 초기화 해주는 생성자.
							//+ 해당 필드에 @Autowired를 붙여줌.
public class ScoreController {
	
	
	private final ScoreService service;
	// 만약에 클래스의 생성자가 단 1개라면 자동으로 @Autowired를 작성해줌 (빈에 등록 된 객체를 자동으로 주입해줌)
	// 근데 나는 service 를 빈에 등록한 기억이 없는걸...? 아
	 // ScoreSerivce 에서 @Service 로 빈등록 했네...?^^
	

//	@Autowired
//	public ScoreController(ScoreService scoreService) {
//		this.service = scoreService;
//	}
	
	
	//1. 성적 등록 화면 띄우기 + 정보 목록 조회
	@GetMapping("/list")
	public String list() {
		
		return "score/score-list";
	}
	
	//2. 성적 정보 등록 처리 요청.
	@PostMapping("/register")
	public String register(ScoreRequestDTO dto) {
		//단순 입력데이터 읽기
		System.out.println("score/register: POST! - " + dto);
		
		//서비스에게 성적정보 등록 + 합계평균 계산 일 시키기
		service.insertScore(dto);
		
		return null;
	}
	
}
