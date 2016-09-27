<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script type="text/javascript"
	src="<c:url value="/resources/js/index.js" />"></script>

<div class='table-div-borrow'>
	<table class='table-borrow row-border compact' id='table-borrow'>
		<thead style="text-align: center;">
			<tr>
				<th></th>
				<th>ID</th>
				<th>Author</th>
				<th>Book title</th>
				<th>Category</th>
				<th>Publisher</th>
				<th>Language</th>
				<th>Date to pay</th>
				<th style="display: none;">Status</th>
				<th></th>
			</tr>
		</thead>
		<tbody class='table_body'>
			<c:forEach var='book' items='${borrowedBooks}' varStatus='status'>
				<tr>
					<td>${status.index +1}</td>
					<td class='id'>${book.id}</td>
					<td>${book.author}</td>
					<td>${book.name}</td>
					<td>${book.category.name}</td>
					<td>${book.information.publisher}</td>
					<td>${book.information.language}</td>
					<td>${book.borrowBook.pay_date}</td>
					<td class='status' style="display: none;">${book.borrowBook.status}</td>

					<td>
						<button type='button' class='pay-button btn btn-info'
							style="text-weight: bold;">Pay</button>
					</td>

				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>