/**
 * 
 */

$(function(){
		//정렬 방식 변경 이벤트
		$("#array").change(function(){
			var url="cart.do?list_id="+$(this).val();
			$(location).attr('href', url);
		})
		
		var list_id=$('input[name="listId"]').val();
		if(list_id=="") list_id="0";
		$("#array option:selected").removeAttr("selected"); 
 		$("#array").val(list_id).attr("selected", "selected");
		
		$(".checkAll_sc").click(function(){
			if($(".checkAll_sc").prop("checked")){
				$(".checkBook_sc").each(function(){
					$(this).prop("checked",true);
				});
			}else{
				$(".checkBook_sc").each(function(){
					$(this).prop("checked",false);
				});
			}
		});
		
		//수량 조절 관련 이벤트
		$(".quantity_hy").each(function() {
			$(this).children(".quantity_up_jm").click(function() {
				var value=$(this).parent().parent().children('input[name="cart_amount"]').val();
				$(this).parent().parent().children('input[name="cart_amount"]').val(Number(value)+1);
				
				var price= $(this).parent().parent().siblings('input[name="isbn_price"]').val();
				$(this).parent().parent().next().next().text(((Number(value)+1)*Number(price)) + "원");
				$(this).parent().parent().next().next().next().val((Number(value)+1)*Number(price));
				
				var total_price=Number($(this).parents().find('#total_price').next().val());
				if($(this).parents().find('.checkBook_sc').prop("checked")){
					total_price+=Number(price);
					$(this).parents().find('#total_price').text(Number(total_price).toLocaleString('en')+"원");
					$(this).parents().find('#total_price').next().val(total_price);
				}
				
				
			})
			
			$(this).children(".quantity_down_jm").click(function() {
				var value=$(this).parent().parent().children('input[name="cart_amount"]').val();
				var price= $(this).parent().parent().siblings('input[name="isbn_price"]').val();
				if(value==1){
					alert("최소 수량입니다.")
					$(this).parent().parent().children('input[name="cart_amount"]').val(value);
				}else{				
					$(this).parent().parent().children('input[name="cart_amount"]').val(Number(value)-1);
					$(this).parent().parent().next().next().text(((Number(value)-1)*Number(price)) + "원");
					$(this).parent().parent().next().next().next().val((Number(value)-1)*Number(price));
					
					var total_price=Number($(this).parents().find('#total_price').next().val());
					if($(this).parents().find('.checkBook_sc').prop("checked")){
						total_price-=Number(price);
						$(this).parents().find('#total_price').text(Number(total_price).toLocaleString('en')+"원");
						$(this).parents().find('#total_price').next().val(total_price);
					}
				}
			})
		})
	
		//주문하기 버튼 클릭시 이벤트
		$("#order").click(function() {
			var isbnList="";
			var amountList="";
			$(".checkBook_sc:checked").each(function() {
				/*var isbn="";
				var amount="";*/
				var amount=$(this).parent().parent().children().find('input[name="cart_amount"]').val();
				amountList+=amount+",";
				
				var isbn=$(this).parent().parent().children().find(".quantity_up_jm").children("input").val();
				isbnList+=isbn+",";
			})
			if(isbnList==""){
				alert("선택된 상품이 없습니다.");
				return false;
			}else{
				alert(isbnList);
				alert(amountList);
				var url="payment.do?isbn="+isbnList+"&quantity="+amountList;
				$(location).attr('href', url);
			}
		})
		
		//하단 결제표 이벤트
		$(".con_hy").change(function() {
			var total_price=0;
			$(".checkBook_sc:checked").each(function() {
				total_price+=Number($(this).parent().parent().children('input[name="price"]').val());
				if(total_price >10000){
					$(this).parents().find('#delivery_price').text(Number(0).toLocaleString('en')+"원");
				}else if(total_price<10000){
					$(this).parents().find('#delivery_price').text(Number(3000).toLocaleString('en')+"원");
				}
			})
			var delivery_price=0;
			if(total_price >10000){
				delivery_price=0;
				$(this).parents().find('#delivery_price').text(Number(delivery_price).toLocaleString('en')+"원");
			}else if(total_price<10000){
				delivery_price=3000;
				$(this).parents().find('#delivery_price').text(Number(delivery_price).toLocaleString('en')+"원");
			}
			$(this).parents().find('#total_price').text(Number(total_price).toLocaleString('en')+"원");
			$(this).parents().find('#total_price').next().val(total_price);
			$(this).parents().find('#payment_price').text(Number(total_price + delivery_price).toLocaleString('en')+"원");
			
		})
	
		
		$("#cart_delete").click(function(){
			var isbn=$(this).parents().find(".quantity_up_jm").children().val();
			var list_id=$('input[name="listId"]').val();
			var cart_pageNumber=$('input[name="cart_pageNumber"]').val();
			
			var url="cartListDelete.do?isbnList="+isbn+"&list_id="+list_id+"&cart_pageNumber="+cart_pageNumber;
			$(location).attr('href', url);
		})
		
		$("#selectDelete").click(function() {
			var isbnList="";
			$(".checkBook_sc:checked").each(function(){
				var isbn=$(this).parents().find('input[name="isbn"]').val();
				isbnList+=isbn;
			});
			var list_id=$('input[name="listId"]').val();
			var cart_pageNumber=$('input[name="cart_pageNumber"]').val();
			
			var url="cartListDelete.do?isbnList="+isbnList+"&list_id="+list_id+"&cart_pageNumber="+cart_pageNumber;
			$(location).attr('href', url);
		})
		
		
		
	});


	
		