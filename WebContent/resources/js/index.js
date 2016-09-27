$(document).ready(function(){

	$('.list-books').click(function(){
		$('.table_div').load(' .table', function(){ 
			var table1 = $('#table').DataTable({"lengthMenu": [[5, 10, 20, -1], [5, 10, 20, "All"]],
				"drawCallback": function(){
					//alert("Here list-book");
					borrowBook();
				}}); }); });

	$('.list-borrow-books').click(function(){
		var warning_fun = true;
		$('.table_div').load('list-borrow-books .table-div-borrow', function(){
			var table2 = $('.table-borrow').DataTable({"lengthMenu": [[5, 10, 20, -1], [5, 10, 20, "All"]], 
				"drawCallback": function(){
					/*if(($('.status').text().indexOf('over') >= 0) && ($(document).hasClass('div-add')==false )) {
						$('.table-div-borrow').before('<div class="div-add"><p style="color:red;font-weight:bold;">Exist books out of date to pay</p></div>')
						$('.status').css({'color':'blue','font-weightt':'bold','font-size':"30px"});
					};*/
					if ($('.status').text().indexOf('over') >= 0){
						if (warning_fun == true) $('.table-div-borrow').before('<div class="div-add"><p style="color:red;font-weight:bold;">Exist books out of date to pay</p></div>');
						warning_fun = false;
					}
						$('.table_body .status').each(function(){//alert($(this).text());
						if($(this).text() == 'over') {//alert($(this).text());
							//$(this).css({'color':'blue','font-weightt':'bold'});
							$(this).parents('tr').css({'background-color':'#ffcc66'});
						}
					})
					//alert($('.status').text());
					
					$('.pay-button').unbind().click( function(e){
						if (confirm("You want to pay this book ?")) {
							var id = $(this).parents('tr').children('td').eq(0).text().trim();
							$(this).parents('tr').addClass("deleted");
							table2.row('.deleted').remove().draw(false);
							if($('.status').text().indexOf('over') < 0) {
								$('.div-add').remove();
							}
							$(window).load('pay-book', 'id='+id);
							
							//$('.table-borrow').load('pay-book .table-borrow', 'id='+id, function(){
							//use $('.table-borrow').load() luon bi loi browser: Uncaught TypeError: Cannot read property
							/*$(window).load('pay-book', 'id='+id, function(data) {
								$('.table-borrow').text(data);
							})*/
						}
					});
				}});});});

	var table = $('.table').DataTable({	"lengthMenu": [[5, 10, 20, -1], [5, 10, 20, "All"]],
		"drawCallback": function(){
			borrowBook();
		}});

	function borrowBook() {
		$('.borrow-button').unbind().click(function(){
			var id = $(this).parents().children().eq(2).text();
			$('.finish-add').unbind().click(function(){
				$(window).load('add-borrow-book','id='+id +'&time='+ $('.return-time').val());
			});	});
	};

	function addStatusColor(status) {alert("OK");
		/*if (status.text() == 'over') {
			status.css('color', 'yellow');
		};*/};
})