<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- 社員一覧画面  -->
 <!-- List.javaからフォワードされる -->
<!DOCTYPE html>
<html>

<head>
<meta charset=UTF-8">
<!--  ブラウザのタブに表示されるタイトル -->
<title>社員一覧</title>
</head>

<body>
	<p>
		<!--  ./ はプロジェクトのトップディレクトリを指す-->
		<a href="./login">ログアウト</a>
	</p>
	<h1>社員一覧</h1>
	<a href="./detail">新規登録</a>

	<table>
		<!-- 表の横一列を定義  -->
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

		<!-- JSTLでセッションスコープから従業員データの配列を取得  -->
		<c:forEach var="list" items="${detailList}">
			<tr class="detailList">

				<!-- No -->
				<td>${list.employeeId}</td>

				<!-- 会社 -->
				<td>
					<!-- セッションスコープから会社データの配列を取得 -->
					<c:forEach var="cmpList" items="${cmpList}">

						<!-- セッションスコープのcomany_idの値が等しければ
								company_infohにある会社の略称(abbreviation)を表示する -->
						<c:if test="${cmpList.companyId == list.companyInfoId}">
                            ${cmpList.abbreviation}
                        </c:if>
					</c:forEach>
				</td>

				<!-- 事業部 -->
				<td>
					<!-- DBには事業部IDしかないためenumのIDと一致した場合
							enumにある事業部名を表示 -->
					<c:forEach var="dpt" items="${depart}">
						<c:if test="${list.department == dpt.id}">
                			${dpt.name}
                		</c:if>
					</c:forEach>
				</td>

				<!-- 氏名 -->
				<td>${list.name}</td>
				<!-- 氏名（ひらがな） -->
				<td>${list.nameHiragana}</td>
				<!-- 年齢 -->
				<td>${list.age}</td>
				<!-- 担当管理営業 -->
				<td>${list.businessManager}</td>
				<!-- 入社日 -->
				<td>${list.strEnterDate}</td>

				<!-- 稼働状況 -->
				<td>
					<!-- DBには稼働状況を表す　IDしかないためenumのIDと一致した場合
							enumにある稼働状況を表示 -->
					<c:forEach var="comStatus" items="${comStatus}">
						<c:if test="${list.commissioningStatus == comStatus.id }">
                			${comStatus.name}
	               		</c:if>
					</c:forEach>
				</td>

				<!-- 詳細 -->
				<!-- empIdをリクエストパラメータに入れる -->
				<td><a href="./detail?empId=${list.employeeId}">詳細</a></td>

				 <!-- 削除 -->
				<!-- empIdをリクエストパラメータに入れる -->
				<td>
					<!-- 削除のリンクを押すと確認のメソッドが呼ばれる -->
					<a href="./delete?empId=${list.employeeId}" onclick="return confirm('${list.abbreviation} ${list.name} を削除しますか？')">削除
					</a>
				</td>

			</tr>
		</c:forEach>
	</table>
</body>
</html>