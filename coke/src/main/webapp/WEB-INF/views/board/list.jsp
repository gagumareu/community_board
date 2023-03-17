<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ include file="../include/header.jsp" %>

	<style type="text/css">
		.dto_list{
			padding: 10px;
			justify-content: space-between;
			display: flex;
			flex-direction: row;
			border-bottom: 1px solid #e5e7eb;
			height: 90px;
		}

		.board_list_title_wrapper{
			display: flex;
    		flex-direction: column;
    		gap: 0.2em;
		}
		
		.board_list_nickName_wrapper{
			display: flex;
	    	flex-direction: row;
		}
		
		.board_list_nickNameDiv{
			margin-right: 0.7em;
		}
		.move{
			font-size: 1.3em;
		}
		
		.board_list_hit_wrapper{
			display: flex;
	   		flex-direction: row;
	    	align-items: center;
		}
		
		.board_list_view{
		    margin-left: 20px;
			color: #00a3d2;
			display: flex;
    		flex-direction: row;
		}
		
		.board_list_view img{
			width: 25px;
		}
		
		.board_list_hit{
		    display: flex;
   			flex-direction: row;
   			color: #00a3d2;	
		}
		
		.board_list_hit img {
			width: 25px;
		}
		
		.hitCounting{
			align-self: center;
		
		}
		
		.board_list_viewCounting{
			align-self: center;
			margin-left: 5px;
		}
		
		
	</style>
		
		
		<div class="board_content">
			<div class="board_top_content">
				<div class="board_top_content_register">
					<a href="/board/writeBoard?bsort=${pageMaker.cri.bsort }">글 작성하기</a>
				</div>
				<div class="board_top_content_tagSort">
					<div class="tag_btn">
						<a href="/board/list?tag=일상">일상</a>
					</div>
					<div class="tag_btn">
						<a href="/board/list?tag=유머">유머</a>
					</div>
					<div class="tag_btn">
						<a href="/board/list?tag=모임">모임</a>
					</div>
				</div>
				<div class="board_top_content_filter">
					필터
				</div>
				
			</div>
			<div class="board_search">
				<form id="searchForm" action="/board/list" method="get">
					<select name="type">
						<option value="" <c:out value="${pageMaker.cri.type == null?'selected':'' }"/>>-----</option>
						<option value="T" <c:out value="${pageMaer.cri.type eq 'T'?'selected':'' }" />>제목</option>
						<option value="C" <c:out value="${pageMaker.cri.type eq 'C'?'selected':'' }" />>내용</option>
						<option value="N" <c:out value="${pageMaker.cri.type eq 'N'?'selected':'' }" />>작성자</option>
						<option value="TC" <c:out value="${pageMaker.cri.type eq 'TC'?'selected':'' }" />>제목+내용</option>	
						<option value="TN" <c:out value="${pageMaker.cri.type eq 'TN'?'selected':'' }" />>제목+작성자</option>	
						<option value="TCN" <c:out value="${pageMaker.cri.type eq 'TCN'?'selected':'' }" />>제목+내용+작성자</option>	
					</select>
					<input type="text" name="keyword" value="${pageMaker.cri.keyword }" placeholder="검색어 입력">
					<button>SEARCH</button>
					<input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum }">
					<input type="hidden" name="amount" value="${pageMaker.cri.amount }">
				</form >
			</div>
			
			<div class="board_content_wrapper">
			
				
				
				<div class="board_content_list">
						
					<c:forEach items="${boardList }" var="dto">
						<div class="dto_list">
							<div class="board_list_title_wrapper">
								<div class="board_list_nickName_wrapper">
									<div class="board_list_nickNameDiv"><a href="#">${dto.nickname }</a></div>
									<div class="board_list_date" >
										<fmt:formatDate value="${dto.bregdate }" pattern="yyyy-MM-dd"/>
									</div>
								</div>
								<a class="move" href="${dto.bno }">${dto.btitle }</a>
								<c:if test="${empty bsort }">
									<div><a href="#">${dto.bsort }</a></div>
								</c:if>
								<c:if test="${!empty bsort }">
									<div><a href="#">${dto.btag }</a></div>
								</c:if>							
							</div> <!-- board_list_title_wrapper -->
								
							<div class="board_list_hit_wrapper">
								<div class="board_list_hit">
									<img src="/resources/upload/icon/viewIcon.png">
									<div class="hitCounting" >${dto.bhit }</div>	
								</div>
								<div class="board_list_view">
									<img src="/resources/upload/icon/replyIcon.png"> 
									<div class="board_list_viewCounting">
										${dto.replycnt }
									</div>
								</div>
							</div>
						</div> <!-- dto_list -->
	
					</c:forEach>
					<div class="board_paging">
						<ul>
							<c:if test="${pageMaker.prev }">
								<li class="paginate_button previous">
									<a href="${pageMaker.startPage -1 }">Previous</a>
								</li>
							</c:if>
							
							<c:forEach begin="${pageMaker.startPage }" end="${pageMaker.endPage }" var="num" >
								<li class="paginate_button ${pageMaker.cri.pageNum == num ? "active":"" }">
									<a href="${num }">
										${num }
									</a>
								</li>
							</c:forEach>
							
							<c:if test="${pageMaker.next }">
								<li class="paginate_button next">
									<a href="${pageMaker.endPage + 1 }">
										Next
									</a>
								</li>
							</c:if>
						</ul>
					</div> <!-- board_paging -->
					
					<form id="actionForm" action="/board/list" method="get">
						<input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum }">
						<input type="hidden" name="amount" value="${pageMaker.cri.amount }">
						<input type="hidden" name="keyword" value="${pageMaker.cri.keyword }">
						<input type="hidden" name="type" value="${pageMaker.cri.type }">
					</form>
					
				</div> <!-- board_content_list -->
				
			
			</div> <!-- board_content_wrapper -->
		</div> <!-- board_content -->
	
	
		<div class="footer">
			<p>
			<c:forEach items="${memberList }" var="member">
				<div>${member.mname }</div>
				<div>${member.email }</div>
			</c:forEach>
			</p>
		</div> <!-- footer -->
		
	</div> <!-- wrapper -->
	
	<script type="text/javascript">
	
	$(function(){
		
		var actionForm = $("#actionForm");
		
		
		$(".paginate_button a").on("click", function(e){
			
			e.preventDefault();
	
			console.log('click');
	
			actionForm.find("input[name='pageNum']")
					.val($(this).attr("href"));
			actionForm.submit();
		});
			
		
		$(".move").click(function(e){
			e.preventDefault();
			actionForm.append("<input type='hidden' name='bno' value='"+ $(this).attr("href") +"' >");
			actionForm.attr("action", "/board/read_board");
			actionForm.submit();
			
		});
		
		var searchForm = $("#searchForm");
		
		$("#searchForm button").click(function(e){
			
			if(!searchForm.find("option:selected").val()){
				alert("검색종류를 선택하세요");
				return false;
			}
			
			if(!searchForm.find("input[name='keyword']").val()){
				alert("키워드를 입력하세요");
				return false;
			}
			
			searchForm.find("input[name='pageNum']").val("1");
			e.preventDefault();
			
			searchForm.submit();
			
		});
	});
	
	
	
	</script>	
	
	
</body>
</html>

					
						
						
						
				

	