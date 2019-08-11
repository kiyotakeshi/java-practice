<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>社員一覧</title>
</head>

<body>
	<p>
		<!-- <a href="/JavaEmpManageSystem/login">ログアウト</a> -->
		<!--  ./ はプロジェクトのトップディレクトリを指す-->
		<a href="./login">ログアウト</a>
	</p>
	<h1>社員一覧</h1>
	<a href="./detail">新規登録</a>

	<table>
		<tr>
			<th>No</th>
			<th>会社</th>
			<th>事業部</th>
			<th>氏名</th>
			<th>氏名(ひらがな)</th>
			<th>年齢</th>
			<th>担当管理営業</th>
			<th>入社日</th>
			<th>稼働状況</th>
			<th>詳細</th>
			<th>削除</th>
		</tr>
		<c:forEach var="list" items="${detailList}">

			<tr class="detailList">
				<td>${list.employeeId}</td>
				<td><c:forEach var="cmpList" items="${cmpList}">
						<c:if test="${cmpList.companyId == list.companyInfoId}">
                            ${cmpList.abbreviation}
                        </c:if>
					</c:forEach></td>
				<!-- departmentのenumとdetailListのdepartmentのidが一致するか調べる -->
				<td><c:forEach var="dpt" items="${depart}">
						<c:if test="${list.department == dpt.id}">
                			${dpt.name}
                		</c:if>
					</c:forEach></td>
				<td>${list.name}</td>
				<td>${list.nameHiragana}</td>
				<td>${list.age}</td>
				<td>${list.businessManager}</td>
				<td>${list.strEnterDate}</td>
				<td><c:forEach var="comStatus" items="${comStatus}">
						<c:if test="${list.commissioningStatus == comStatus.id }">
                			${comStatus.name}
                		</c:if>
					</c:forEach></td>
				<td><a href="./detail?empId=${list.employeeId}">詳細</a></td>
				<td>
					<a href="./delete?empId=${list.employeeId}" onclick="return confirm('${list.abbreviation} ${list.name} を削除しますか？')">削除
					</a>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>