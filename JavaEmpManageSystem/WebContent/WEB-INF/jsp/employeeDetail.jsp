<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>社員詳細</title>
</head>
<body>
	<h1>社員詳細</h1>
	<form action="./detail" method="post">

	<input type="hidden" name="userId" value="${emp.employeeId}">

	<table>
		<tr>
			<th>氏名</th>
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
				<c:forEach var="sex" items="${sex}">
					<label><input type="radio" value="${sex.id}" name="sex"
						required <c:if test="${emp.sex == sex.id}">checked</c:if>/>
						${sex.name}</label>
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
					<c:forEach var="cmp" items="${cmpList}">
						<option value="${cmp.companyId}"
							<c:if test="${cmp.companyId == emp.companyInfoId}">selected </c:if>>
							${cmp.abbreviation}
						</option>
					</c:forEach>
				</select>
			</td>
		</tr>

		<tr>
			<th>稼働状況</th>
			<td><input type="text" value="${emp.nameHiragana}" name="nameHiragana" required /></td>
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

						<!-- すでに登録されている社員の場合、その人の事業部が選択された状態で画面に出す-->
						<option value="{depart.id}"
							<c:if test="${emp.department == depart.id}">selected</c:if>>
							${depart.name}
						</option>
					</c:forEach>
				</select>
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
							<c:if test="${emp.status == status.id}">selected</c:if>>
							${state.name}
						</option>
					</c:forEach>
				</select>
			</td>
		</tr>

	</table>

	<input class="register" type="submit" value="${btn}" />
		<a class="return" href="./list">戻る</a>
	</form>

</body>
</html>