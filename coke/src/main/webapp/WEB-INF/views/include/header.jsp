<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JH PROJECT</title>



<style type="text/css">
	
	html{
		height: 100%;
		

	}
	
	body{
		min-height: 100vh;
		display: flex;
		flex-direction: column;
		width: 100%;
		align-items: center;
	}
	
	a{
		text-decoration: none;
	}
	
	ul {
		list-style: none;
	}
	
	p{
		text-align: center;
	}
	
	.footer{
		margin-top: auto;
		width: 100%;
		border-top: 1px solid #e5e7eb;
		margin-top: 5em;
	}
	
	.wrapper{
		display: flex;
		flex-direction: column;
		width: 100%;
	    /*border: 3px solid red;*/
    	min-height: 100vh;
    	width: 950px;
    	min-width: 950px;
		
	}
	
	.header {
		display: flex;
    	flex-direction: column;
    	/*border: 2px solid black;*/
    	width: 100%;
	}
	
	.header_navbar{
		display: flex;
    	flex-direction: row;
    	justify-content: space-between;
    	/*border: 2px solid black;*/
    	height: 50px;
    	width: 100%;
    	align-items: center;
		
	}
	
	.header_sort {
		display: flex;
    	flex-direction: row;
    	margin-left: 105px;
	}
	
	.header_sort_title {
		margin-left: 1.5em;
	}
	.header_login{
		display: flex;
    	flex-direction: row;
    	align-items: center;
	}
	
	.header_search_form{
		display: flex;
		justify-content: end;
		/* border: 1px solid; */
	    margin-top: 0.6em;
	    margin-bottom: 0.6em;
	}
	
	.header_login_btn {
		margin-left: 7em;
	}
	
	.header_signUp_btn {
		margin-left: 1.5em;
	}
	
	
	.header_main_log{
		font-size: 2em;
		color: black;
	}
	/*******************************************/
	
	
	

	
	/******* board content***********/
	
	.board_content{
		width: 100%;
		margin-top: 1.5em;
	}
	
	.board_content_wrapper{
		display: flex;
    	flex-direction: row;
   	    margin-top: 30px;
    	/*border: 2px solid red;*/
    	justify-content: center;
	}
	
	
	
	.board_content_list{
		width: 100%;
	
	}
	
	
	
	.board_paging ul {
		list-style-type: none;
		display: flex;
		justify-content: center;
		padding: 0px;
	}
	

	
	.paginate_button {
		margin-left: 1.2em;
	}
	
	
	
	/******** tag and search *******/
	
	.board_search{
		text-align: center;
	}
	
	.board_top_content {
		display: flex;
    	flex-direction: row;
	}
	
	.board_top_content_register{
    	width: 20%;
	
	}
	
	.board_top_content_tagSort{
		display: flex;
    	flex-direction: row;
    	width: 60%;
    	justify-content: center;
    	
	}
	
	.board_top_content_filter{
    	width: 20%;
		text-align: end;
	}
	
	.tag_btn{
		padding: 0px 10px 0px 10px;
	}
	
	/******************************/
</style>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

	<!-- summernote editor  -->

 <link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
 
  <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
  
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  
  <link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
  
  <script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
	
    
<!-- <link rel="stylesheet" type="text/css" href="https://stackpath.bootstrapcdn.com/bootstrap/4.6.0/css/bootstrap.min.css">

<link rel="stylesheet" type="text/css" href="/resources/css/bootstrap.min.css"> 
    
  <link rel="stylesheet" type="text/css" href="/resources/css/custom.css">
-->
</head>

<body>
	<div class="wrapper">
		<div class="header" >
		
			<div class="header_search_form">
				<form action="#" method="post" class="header_search_form">
					<input type="text">
					<div>
						<button type="submit">SEARCH</button>
					</div>
				</form>
			</div>
			
			<div class="header_navbar">
				<div class="header_main_log">
					<a href="/board/list">MAIN</a>
				</div>
				
				<div class="header_sortAndLogin_warpper">
					<div class="header_sort">
						<div>
							<a href="/board/list">전체글</a>
						</div>
						<div class="header_sort_title">
							<a href="/board/list?bsort=음악">음악</a>
						</div>
						<div class="header_sort_title">
							<a href="/board/list?bsort=지식">지식</a>
						</div>
						<div class="header_sort_title">
							<a href="/board/list?bsort=컴퓨터">컴퓨터</a>
						</div>
						<div class="header_sort_title">
							<a href="/board/list?bsort=커뮤니티">커뮤니티</a>
						</div>
						<div class="header_sort_title">
							<a href="/board/list?bsort=질문">Q&A</a>
						</div>
						<div class="header_sort_title">
							<a href="#">공지사항</a>
						</div>
					</div>
				</div>
				<div class="header_login">
				
					<div class="header_login_btn">
						<a href="#">로그인</a>
					</div>
					<div class="header_signUp_btn">
						<a href="#">회원가입</a>
					</div>
				</div> <!-- header_login -->
				
			</div> <!-- header_navbar -->
			
			
			
		</div> <!-- header -->



