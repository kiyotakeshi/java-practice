<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- 社員詳細画面  -->
 <!-- Detail.javaからフォワードされる -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>社員詳細</title>
</head>

<body>

	<h1>社員詳細</h1>

	<!-- form内で入力フォームの設定を記述 -->
	<!--  Postリクエストを Detail.java に送る -->
	<form action="./detail" method="post">

	<!-- empはリクエストスコープ に格納されている、empDto  -->
	<!-- ブラウザに表示する必要がないためhiddenにする -->
	<!-- 更新処理の場合、リクエストスコープ(userId)に値が格納される -->
	<input type="hidden" name="userId" value="${emp.employeeId}">

	<table>
		<tr>
			<th>氏名</th>

			<!-- requiredは入力が必須の項目 -->
			<td><input type="text" value="${emp.name}" name="name" required /></td>
		</tr>

		<tr>
			<th>氏名(ひらがな)</th>
			<td><input type="text" value="${emp.nameHiragana}" name="nameHiragana" required /></td>
		</tr>

		<tr>
			<th>生年月日</th>
			<td><input type="text" value="${emp.strBirthday}" name="birthday" required /></td>
		</tr>

		<tr>
			<th>性別</th>
			<td>
				<!-- セッションスコープに格納されているenumの値 -->
				<c:forEach var="sex" items="${sex}">
					<label>
						<!--  DBから取得したsexのIDをラジオボタンの選択状態にする -->
						<input type="radio" value="${sex.id}" name="sex" required
							 <c:if test="${emp.sex == sex.id}">checked </c:if>/>
								<!-- 「男」と「女」をラジオボタンに表示 -->
								${sex.name}
					</label>
				</c:forEach>
			</td>
		</tr>

		<tr>
			<th>メールアドレス</th>
			<td><input type="text" value="${emp.mailAddress}" name="mail" required /></td>
		</tr>

		<tr>
			<th>電話番号</th>
			<td><input type="text" value="${emp.telephoneNumber}" name="phone" required /></td>
		</tr>

		<tr>
			<th>所属会社</th>
			<td>
				<select name="company" required>
					<!-- プルダウンの初期値を空白にするため -->
					<option value=""></option>

					<!-- cmpListはセッションスコープに格納されている会社情報の配列 -->
					<c:forEach var="cmp" items="${cmpList}">

						<!-- すでに登録されている会社情報をプルダウンで選択された状態で表示  -->
						<option value="${cmp.companyId}"
							<c:if test="${cmp.companyId == emp.companyInfoId}">selected </c:if>>
							${cmp.abbreviation}
						</option>
					</c:forEach>
				</select>
			</td>
		</tr>

		<tr>
			<th>担当管理営業</th>
			<td><input type="text" value="${emp.businessManager}" name="manager" required /></td>
		</tr>

		<tr>
			<th>事業部</th>
			<td>
				<select name="department" required>
					<option value=""></option>
					<c:forEach var="depart" items="${depart}">

						<!-- すでに登録されている社員の場合、その人の事業部が選択された状態で表示-->
						<option value="${depart.id}"
							<c:if test="${emp.department == depart.id}">selected</c:if>>
							${depart.name}
						</option>
					</c:forEach>
				</select>
			</td>
		</tr>

		<tr>
			<th>稼働状況</th>
			<td>
				<c:forEach var="comissionState" items="${comissionState}">
				<label>
					<!-- すでに登録されている性別を選択した状態にで画面に表示　 -->
					<input type="radio" value="${comissionState.id}" name="run" required
					<c:if test="${emp.commissioningStatus == comissionState.id}">checked</c:if> />
						${comissionState.name}
				</label>
				</c:forEach>
			</td>
		</tr>


		<tr>
			<th>入社日</th>
			<td><input type="text" value="${emp.strEnterDate}" name="enterDay" required /></td>
		</tr>

		<tr>
			<th>退職日</th>
			<td><input type="text" value="${emp.strRetireDate}" name="retireDay" /></td>
		</tr>

		<tr>
			<th>ステータス</th>
			<td>
				<select name="status" required>
					<option value=""></option>
					<c:forEach var="state" items="${state}">
						<option value="${state.id}"
							<c:if test="${emp.status == state.id}">selected</c:if>>
							${state.name}
						</option>
					</c:forEach>
				</select>
			</td>
		</tr>

	</table>

	<!-- リクエストスコープ に格納されている「登録」か「更新」を表示 -->
	<input class="register" type="submit" value="${btn}" />
		<a class="return" href="./list">戻る</a>
	</form>

</body>
</html>