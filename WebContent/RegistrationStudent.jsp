<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<script type="text/javascript">
function alphanumeric(inputtxt)
{ 
	inputtxt.value=inputtxt.value.replace(/[^\a-zA-Z0-9]/ig, "");
}

function numericFilter(txb) {
	   txb.value = txb.value.replace(/[^\0-9]/ig, "");
}
</script>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<!-- <script src="check-letters-numbers.js"></script> -->
</head>
<body>
	<form action="ViewServlet" method="post" name="form1">
		<table>
			<tr>
				<td colspan=3>Please enter details to Register Student</td>
			</tr>
			<tr>
				<td>Name</td>
				<td>:</td>
				<td><input type="text" name="t1" onKeyPress="alphanumeric(document.form1.t1);"></td>
			</tr>
			<tr>
				<td>Mathematics</td>
				<td>:</td>
				<td><input type="text" name="t2" onKeyUp="numericFilter(document.form1.t2);"></td>
			</tr>
			<tr>
				<td>Physics</td>
				<td>:</td>
				<td><input type="text" name="t3"></td>
			</tr>
			<tr>
				<td>Chemistry</td>
				<td>:</td>
				<td><input type="text" name="t4"></td>
			</tr>
			<tr>
				<td>English</td>
				<td>:</td>
				<td><input type="text" name="t5"></td>
			</tr>
			<tr>
				<td colspan=3><input type="submit" value="add" ></td>			
			</tr>
			
			<tr>
				<td colspan=3><a href="StudentGrid">click here to View
						Student details</a>
			</tr>
		</table>
	</form>
</body>
</html>