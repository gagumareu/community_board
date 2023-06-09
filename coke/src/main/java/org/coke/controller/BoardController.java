package org.coke.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.coke.domain.BoardAttachVO;
import org.coke.domain.BoardVO;
import org.coke.domain.Criteria;
import org.coke.domain.MemberVO;
import org.coke.domain.PageDTO;
import org.coke.domain.ReplyAttachVO;
import org.coke.mapper.BoardAttachMapper;
import org.coke.mapper.MemberMapper;
import org.coke.mapper.ReplyMapper;
import org.coke.security.domain.CustomUser;
import org.coke.service.BoardService;
import org.coke.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/board/*")
@Log4j
@AllArgsConstructor
public class BoardController {
	
	private BoardService boardService;
	
	private MemberService memberService;
	
	private void deleteFiles(List<BoardAttachVO> attachList) {
		
		if(attachList == null || attachList.size() == 0) {
			return;
		}
		
		log.info("delete file");
		log.info(attachList);
		
		attachList.forEach(attach -> {
						
			try {
				Path files = Paths.get("C:\\workspace\\upload\\coke\\" + attach.getUploadPath() + "\\" + attach.getUuid() + "_" + attach.getFileName());
				
				Files.deleteIfExists(files);
				
			} catch (Exception e) {
				log.error("delete file error" + e.getMessage());
			}			
		});
		
	}
	
	
	@GetMapping("/list")
	public void empList(Model model, Criteria cri, @RequestParam(value = "bsort", required = false) String bsort, 
			@RequestParam(value = "btag", required = false) String btag) {
		
		cri.setBsort(bsort);
		cri.setBtag(btag);
		
		log.info("--------------------------------");
		log.info("boardList:  " + cri);
		log.info("sort: " + bsort);
		log.info("btag: " + btag);
		log.info("pageNum: " + cri.getPageNum());
		log.info("sort by cri: " + cri.getBsort());
		log.info("tag by cri: " + cri.getBtag());
		
		int total = boardService.getTotalAmount(cri);

		if(cri.getBsort() != null || cri.getBtag() != null) {
			log.info("cri sort: " + cri.getBsort());
			log.info("cri tag: " + cri.getBtag());
			model.addAttribute("tagList", boardService.getTagList(cri));
			
		}
		
		model.addAttribute("boardList", boardService.getBoardList(cri));
		model.addAttribute("pageMaker", new PageDTO(cri, total));
		model.addAttribute("topwriterList", boardService.getTowriterList());
		model.addAttribute("mostViewList", boardService.getViewList());
		model.addAttribute("mostReplyList", boardService.getMostReplyList());
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/writeBoard")
	public void writeBoard(@RequestParam(value = "bsort", required = false) String bsort, Model model, 
			@RequestParam(value = "btag", required = false) String btag, @ModelAttribute("cri") Criteria cri, 
			@RequestParam("userid") String userid) {
		
		log.info("---------------------------");
		log.info("sort on writeBoard: " + bsort);
		log.info("tag on writeBoard: " + btag);
		log.info("page: " + cri.getPageNum());
		log.info("amount: " + cri.getAmount());
		log.info("userid: " + userid);
		
		MemberVO vo = memberService.getMember(userid);
		log.info("memberVO: " + vo);
		
		model.addAttribute("userName", vo.getUserName());
		log.info("userName: " + vo.getUserName());
		
		if(bsort != null) {			
			log.info("sort: " + bsort);			
			model.addAttribute("bsort", bsort);
		}else if(btag != null) {			
			log.info("tag: " + btag);		
			model.addAttribute("btag", btag);
		}
		
	}
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/register_board")
	public String registerBoard(BoardVO boardVO, RedirectAttributes rttr, Model model) {
		
		log.info("========================");
		
		log.info("register board: " + boardVO);
		
		if(boardVO.getGetAttachList() != null) {
			boardVO.getGetAttachList().forEach(attach -> log.info(attach));
		}
						
		boardService.writeBoard(boardVO);
								
		Criteria cri = new Criteria();
		cri.setBsort(boardVO.getBsort());
		
		rttr.addFlashAttribute("success", boardVO.getBno());
		rttr.addAttribute("bsort", cri.getBsort());
		
		return "redirect:/board/list";
		
	}	
	
	
	@GetMapping("/read_board")
	public void readBoard(@RequestParam("bno") long bno, Model model, @ModelAttribute("cri") Criteria cri) {
		
		log.info("=======================");
		log.info("readBoard :" + bno);
		boardService.getHitCount(bno);
		model.addAttribute("BoardVO", boardService.readBoard(bno));
		//model.addAttribute("replyList", replyMapper.getListWithPaging(cri, bno));
	}
	
	@GetMapping("/modify")
	public void modifyBoard(@RequestParam("bno") long bno, Model model, @ModelAttribute("cri") Criteria cri) {
		
		log.info("--------------------------");
		
		log.info("modify :" + bno);
		log.info(cri);
		model.addAttribute("boardDto", boardService.readBoard(bno));

	}
	
	@PreAuthorize("principal.username == #boardVO.userid")
	@PostMapping("/modify_board")
	public String modifyBoard(BoardVO boardVO, @ModelAttribute("cri") Criteria cri, 
			RedirectAttributes rttr,
			Model model) {
		
		log.info("bno: " + boardVO.getBno());
		log.info("modify board: " + boardVO);
		
		if(boardService.modifyBoard(boardVO)) {
			rttr.addFlashAttribute("result", "success");
			
		}
		
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
		rttr.addAttribute("type", cri.getType());
		rttr.addAttribute("keyword", cri.getKeyword());
		rttr.addAttribute("bsort", cri.getBsort());
		
		return "redirect:/board/list";
	}
	
	@PreAuthorize("principal.username == #userid")
	@PostMapping("/delete")
	public String deleteBoard(BoardVO boardVO, RedirectAttributes rttr, @ModelAttribute("cri") Criteria cri, String userid) {
		
		log.info("userid on board_delete_controller: " + userid);
		
		log.info("delete board NO.: " + boardVO.getBno());
		
		List<BoardAttachVO> list = boardService.getAttachLsit(boardVO.getBno());
		
		if(boardService.removeBoard(boardVO.getBno())) {
			
			deleteFiles(list);
			
			rttr.addFlashAttribute("result", "success");
		}
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
		rttr.addAttribute("type", cri.getType());
		rttr.addAttribute("keyword", cri.getKeyword());
		
		return "redirect:/board/list";
	}
	
	 
	@GetMapping(value = "/getAttachList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<BoardAttachVO>> getAttachList(long bno){
		
		
		return new ResponseEntity<>(boardService.getAttachLsit(bno), HttpStatus.OK);
	}

	@GetMapping("/notice")
	public void getNotice() {
		
	}
	
	
	
	
}
