package org.coke.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.coke.domain.BoardVO;
import org.coke.domain.Criteria;

public interface BoardMapper {
	
	public List<BoardVO> getList();
	
	public void insert(BoardVO boardVO);
	
	public BoardVO read(long bno);
	
	public int update(BoardVO boardVO);
	
	public int delete(long bno);
	
	public List<BoardVO> getListWithPage(Criteria cri);
	
	public int getTotal(Criteria cri);
	
	public void getHit(long bno);
	
	public void updateReplyCnt(@Param("bno") long bno, @Param("amount") int amount);

}