<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Book store</title>

<script type="text/javascript" charset="UTF-8"
	src="//code.jquery.com/jquery-1.12.3.min.js"></script>
<script type="text/javascript" charset="UTF-8"
	src="//cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="//cdn.datatables.net/1.10.12/css/jquery.dataTables.min.css">
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script type="text/javascript"
	src="<c:url value="/resources/js/index.js" />"></script>
<link href="<c:url value="/resources/css/index.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/test.css" />" rel="stylesheet">

<script>
	function set(str) {
		$(".header-category").val(str);
	}
	function setA(str) {
		$(".body-a").val(str);
	}
</script>
</head>
<body>
	<div class='container'>
		<br> <br>
		<div id='header' style="text-align: center;">
			<button class='btn btn-success list-books' type='button'>List
				books</button>
			<button class='btn btn-success list-borrow-books' type='button'>List
				borrowed books</button>
		</div>
		<br> <br> <br>

		<div class='table_div'>
			<table id='table' class='table'>
				<thead style="text-align: center;">
					<tr>
						<th><!-- style="display:none" --></th>
						<th>ID</th>
						<th>Author</th>
						<th>Book title</th>
						<th>Category</th>
						<th>Publisher</th>
						<th>Language</th>
						<th></th>
					</tr>
				</thead>
				<tbody class='table_body'>
					<c:forEach var='book' items='${books}' varStatus='status'>
						<tr>
							<td>${status.index +1}</td>
							<td class='id'>${book.id}</td>
							<td>${book.author}</td>
							<td>${book.name}</td>
							<td>${book.category.name}</td>
							<td>${book.information.publisher}</td>
							<td>${book.information.language}</td>
							<td>
								<button type='button' class='borrow-button btn btn-info'
									data-toggle="modal" data-target="#myModal"
									style="text-weight: bold;">Borrow</button>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>

		<!-- Modal -->
		<div class="modal fade " id="myModal" role="dialog">
			<div class="modal-dialog modal-sm">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">Choose time to borrow book</h4>
					</div>
					<div class="modal-body">
						Borrowed time: <select class="return-time">
							<option value="0" selected>1 week</option>
							<option value="1">1 month</option>
							<option value="2">2 months</option>
							<option value="3">3 months</option>
						</select>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default finish-add"
							data-dismiss="modal">Finish</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>