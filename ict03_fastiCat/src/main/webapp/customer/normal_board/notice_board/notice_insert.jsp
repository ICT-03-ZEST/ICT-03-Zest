<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/setting.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<style>
/* 페이지 상단 여백 추가 */
.page-container {
    margin-top: 50px; /* 페이지 상단에 50px 여백 추가 */
    padding: 20px; /* 내부 여백 추가 */
    border: 2px solid #FFA500; /* 오렌지 색상의 테두리 */
    border-radius: 8px; /* 테두리 모서리 둥글게 */
    box-shadow: 0 4px 8px rgba(255, 165, 0, 0.3); /* 오렌지 색상에 맞춘 그림자 */
    width: 80%; /* 페이지 너비 조정 */
    max-width: 1200px; /* 최대 너비 설정 */
    margin: 50px auto; /* 페이지 중앙 정렬 및 상단 여백 추가 */
}

/* th 태그에 대한 스타일 */
.table-header {
    background-color: #FFA500; /* 오렌지 색상 */
    color: #ffffff; /* 흰색 텍스트 */
    padding: 15px; /* 내부 여백 추가 */
    border: 2px solid #FFA500; /* 오렌지 색상 테두리 */
    border-radius: 8px; /* 테두리 모서리 둥글게 */
    text-align: center; /* 텍스트 가운데 정렬 */
    vertical-align: middle; /* 수직 정렬 가운데 */
}

/* td 태그에 대한 스타일 */
.table-cell {
    padding: 10px; /* 내부 여백 추가 */
    vertical-align: middle; /* 수직 정렬 가운데 */
}

/* 제목 입력 필드 */
.notice-title {
    width: 100%; /* 너비를 부모 요소에 맞게 설정 */
    padding: 15px; /* 패딩을 th 태그와 동일하게 설정 */
    border: 2px solid #FFA500; /* 오렌지 색상 */
    border-radius: 8px; /* 테두리 모서리 둥글게 */
    background-color: #FFF8E1; /* 오렌지 색상에 맞춘 밝은 배경색 */
    box-sizing: border-box;
}

/* 작성자 입력 필드 */
.notice-writer {
    width: 100%; /* 너비를 부모 요소에 맞게 설정 */
    padding: 15px; /* 패딩을 th 태그와 동일하게 설정 */
    border: 2px solid #FFA500; /* 오렌지 색상 */
    border-radius: 8px; /* 테두리 모서리 둥글게 */
    background-color: #FFF8E1; /* 오렌지 색상에 맞춘 밝은 배경색 */
    box-sizing: border-box;
}

/* 내용 입력 필드 */
.notice-content {
    width: 100%; /* 너비를 부모 요소에 맞게 설정 */
    padding: 15px; /* 패딩을 th 태그와 동일하게 설정 */
    border: 2px solid #FFA500; /* 오렌지 색상 */
    border-radius: 8px; /* 테두리 모서리 둥글게 */
    background-color: #FFF8E1; /* 오렌지 색상에 맞춘 밝은 배경색 */
    box-sizing: border-box;
}

/* 버튼 스타일 */
.notice-button {
    padding: 10px 20px;
    margin: 10px 5px;
    border: none;
    border-radius: 4px;
    background-color: #FFA500; /* 오렌지 색상 */
    color: #ffffff; /* 흰색 텍스트 */
    font-size: 16px;
    cursor: pointer;
    transition: background-color 0.3s ease;
    margin-top: 20px; /* 위쪽 여백 추가 */
}

.notice-button:hover {
    background-color: #FF8C00; /* 오렌지 색상보다 약간 어두운 색상 */
}

/* 포커스 스타일 */
.notice-title:focus, .notice-writer:focus, .notice-content:focus {
    border-color: #FFA500; /* 오렌지 색상 */
    outline: none;
    box-shadow: 0 0 5px rgba(255, 165, 0, 0.5); /* 오렌지 색상에 맞춘 그림자 */
}
</style>
<title>공지사항 작성페이지</title>
<script>
	$(function() {
		$('#btnSave').click(function() {
			const title = $('#title').val();
			const content = $('#content').val();

			if (title == "") {
				alert("게시글 제목을 입력하세요!");
				$('#title').focus();
				return false;
			}

			if (content == "") {
				alert("게시글 내용을 입력하세요!");
				$('#content').focus();
				return false;
			}
			document.insertForm.action = "${path}/notice_insertAction.nb";
			document.insertForm.submit();
		});
	});
</script>

</head>
<body>
	<!-- header 시작-->
	<%@ include file="/common/header.jsp"%>
	<!-- header 끝-->

	<!-- 컨텐츠 시작 -->
	<div class="page-container">
		<div align="center" id="table">
			<form name="insertForm" method="post">
				<table>
					<tr>
						<th class="table-header">공지사항 제목</th>
						<td colspan="3" class="table-cell"><input type="text"
							class="notice-title" name="n_Title" id="title" size="60"
							placeholder="글제목 입력" required></td>

						<th class="table-header">작성자</th>
						<td colspan="3" class="table-cell"><input type="text"
							class="notice-writer" name="n_Writer" id="writer" size="20"
							placeholder="작성자이름" required></td>
					</tr>

					<tr>
						<th class="table-header">글내용</th>
						<td colspan="5" class="table-cell"><textarea rows="5"
								cols="93" class="notice-content" name="n_Content" id="content"
								placeholder="본문을 입력하세요"></textarea></td>
					</tr>
				</table>
				<input class="notice-button" type="button" value="저장" id="btnSave">
				<input class="notice-button" type="reset" value="초기화">
			</form>
		</div>
	</div>

	<!-- footer 시작-->
	<%@ include file="/common/footer.jsp"%>
	<!-- footer 끝-->
</body>
</html>