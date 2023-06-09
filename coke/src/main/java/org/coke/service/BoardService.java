package org.coke.service;

import java.util.List;

import org.coke.domain.BoardAttachVO;
import org.coke.domain.BoardVO;
import org.coke.domain.Criteria;

public interface BoardService {
	
	public List<BoardVO> getBoardList(Criteria cri);
	
	public void writeBoard(BoardVO boardVO);
	
	public BoardVO readBoard(long bno);
	
	public boolean modifyBoard(BoardVO boardVO);
	
	public boolean removeBoard(long bno);

	public int getTotalAmount(Criteria cri);
	
	public void getHitCount(long bno);
	
	public List<BoardAttachVO> getAttachLsit(long bno);
	
	public List<String> getTagList(Criteria cri);
	
	public List<BoardVO> getTowriterList();
	
	public List<BoardVO> getViewList();
	
	public List<BoardVO> getMostReplyList();
	
	public List<BoardVO> getMusicList();
	
	public List<BoardVO> getKnowList();
	
	public List<BoardVO> getComList();
	
	public List<BoardVO> getComuList();
	
	public List<BoardVO> getMemberBoardList(String userid);
	
}
