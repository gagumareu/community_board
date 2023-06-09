package org.coke.service;

import java.nio.file.Files;
import java.util.List;

import org.coke.domain.AttachFileDTO;
import org.coke.domain.BoardAttachVO;
import org.coke.domain.BoardVO;
import org.coke.domain.Criteria;
import org.coke.mapper.BoardAttachMapper;
import org.coke.mapper.BoardMapper;
import org.coke.mapper.ReplyAttachMapper;
import org.coke.mapper.ReplyMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
@Log4j
@Service
@AllArgsConstructor
public class BoardServiceImpl implements BoardService{
	
	private BoardMapper boardMapper;
	
	private BoardAttachMapper attachMapper;
	
	private ReplyMapper replyMapper;
	
	private ReplyAttachMapper replyAttachMapper;
		
	@Override
	public List<BoardVO> getBoardList(Criteria cri) {
		log.info("get boardList with criteria" + cri);
		return boardMapper.getListWithPage(cri);
	}

	@Transactional
	@Override
	public void writeBoard(BoardVO boardVO) {
		
		log.info("------boardServiceImpl---------");
		log.info("insert Board :" + boardVO);
		log.info("insert Board :" + boardVO.getGetAttachList());
		System.out.println("bno: " + boardVO.getBno());
		
		boardMapper.insert(boardVO);
		
		System.out.println("bno2: " + boardVO.getBno());
		
		if(boardVO.getGetAttachList() == null || boardVO.getGetAttachList().size() <= 0 ) {			
			return;		
		}
		
		System.out.println("************************************");
		System.out.println("bno3: " + boardVO.getBno());
		boardVO.getGetAttachList().forEach(attach ->{
			
			attach.setBno(boardVO.getBno());
			attachMapper.insert(attach);
			
		});
		
	}

	@Transactional
	@Override
	public boolean modifyBoard(BoardVO boardVO) {
		
		log.info("modify board: " + boardVO);
		
		attachMapper.deleteAll(boardVO.getBno());
		
		boolean modifyResult = boardMapper.update(boardVO) == 1;
			
		if(modifyResult && boardVO.getGetAttachList() != null && 
				boardVO.getGetAttachList().size() > 0) {
			
			boardVO.getGetAttachList().forEach(attach ->{
				attach.setBno(boardVO.getBno());
				attachMapper.insert(attach);
			});
				
		}
		return modifyResult;
	}

	
	@Override
	public BoardVO readBoard(long bno) {
		log.info("read board: " + bno);
		
		return boardMapper.read(bno);
	}
	
	@Transactional
	@Override
	public boolean removeBoard(long bno) {
		log.info("delete board : " + bno);
		attachMapper.deleteAll(bno);
		replyMapper.deleteAll(bno);
		replyAttachMapper.deleteFindBybno(bno);
		return boardMapper.delete(bno) == 1;
	}

	@Override
	public int getTotalAmount(Criteria cri) {
		log.info("get total amount");
		return boardMapper.getTotal(cri);
	}

	@Override
	public void getHitCount(long bno) {
		log.info("get hit counting" + bno);
		boardMapper.getHit(bno);
	}

	@Override
	public List<BoardAttachVO> getAttachLsit(long bno) {
		log.info("bno: " + bno);
		return attachMapper.findByBno(bno);
	}

	@Override
	public List<String> getTagList(Criteria cri) {
		
		return boardMapper.getTagList(cri);
	}

	@Override
	public List<BoardVO> getTowriterList() {
		
		return boardMapper.getTopwriter();
	}

	@Override
	public List<BoardVO> getViewList() {
		
		return boardMapper.getMostViewList();
	}

	@Override
	public List<BoardVO> getMostReplyList() {
		
		return boardMapper.getMostReply();
	}

	@Override
	public List<BoardVO> getMusicList() {
		return boardMapper.getMusicList();
	}

	@Override
	public List<BoardVO> getKnowList() {
		return boardMapper.getKnowList();
	}

	@Override
	public List<BoardVO> getComList() {
		return boardMapper.getCompList();
	}

	@Override
	public List<BoardVO> getComuList() {
		return boardMapper.getComuniList();
	}

	@Override
	public List<BoardVO> getMemberBoardList(String userid) {
		
		return boardMapper.getMyBoard(userid);
	}

	
	

	

	

	
	
	

}
