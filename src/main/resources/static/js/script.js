//pament order



const pamentStart=()=>{

	console.log("pament Started....");

	let amount=$("#pament_field").val();

	console.log(amount);


	if(amount==""|| amount==null){
		alert("amount is Blank");
		return;
	}



	$.ajax(
		{
			url:'/pament/create_oder',
			data:JSON.stringify({amount:amount,info:'oder_request'}),
			contentType:'application/json',
			type:'POST',
			dataType:'json',
            success:function(response){
                console.log(response)
				if(response.status =="created"){

					let options={
						key:'rzp_test_z5ecg7cMwOXtbC',
						amount:response.amount,
						currency:'INR',
						name:'Reserve.com',
						description:'Pay',
						image :'https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/f/47da9947-38e3-4132-b697-4981d8946007/df4bal4-2273533a-2a6e-456a-8408-d159242a557b.jpg/v1/fill/w_1280,h_976,q_75,strp/letter_p___search___3d_logo_design_by_logoshahin_by_logoshahin_df4bal4-fullview.jpg?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1cm46YXBwOjdlMGQxODg5ODIyNjQzNzNhNWYwZDQxNWVhMGQyNmUwIiwiaXNzIjoidXJuOmFwcDo3ZTBkMTg4OTgyMjY0MzczYTVmMGQ0MTVlYTBkMjZlMCIsIm9iaiI6W1t7ImhlaWdodCI6Ijw9OTc2IiwicGF0aCI6IlwvZlwvNDdkYTk5NDctMzhlMy00MTMyLWI2OTctNDk4MWQ4OTQ2MDA3XC9kZjRiYWw0LTIyNzM1MzNhLTJhNmUtNDU2YS04NDA4LWQxNTkyNDJhNTU3Yi5qcGciLCJ3aWR0aCI6Ijw9MTI4MCJ9XV0sImF1ZCI6WyJ1cm46c2VydmljZTppbWFnZS5vcGVyYXRpb25zIl19.H7f72L9t9N2vFPiw-mMQcf-5dL756FY9nU_LiOaXZC8',
                        order_id:response.id,
                      
						handler:function(response){
							console.log(response.razorpay_payement_id);
							console.log(response.razorpay_order_id);
							console.log(response.razorpay_signature);
							console.log("payment successfull ..");

                            updatePaymentOnServer(
								response.razorpay_payement_id,
								response.razorpay_order_id,
								"paid"
								);

		
						},
						prefill:{
							name:"",
							email:"",
							contact:"",
						},
						notes:{
                            address:"Reserve.com"
						},
						theme:{
							color:"#3399cc",
						},
					};


					let rzp=new Razorpay(options);


					rzp.on("payment.failed",function(response){

						console.log(response.error,code);
					    console.log(response.error.description);
						console.log(response.error.source);
						console.log(response.error.step);
						console.log(response.error.reason);
						console.log(response.error.metadata.order_id);
						console.log(response.error.metadata.payement_id);
						
					});

					rzp.open();

				}
			} ,
			error:function(error){

				console.log(error)
				alert("somthing went wrong !!")
			}, 
		}
	)
};

 function updatePaymentOnServer(payement_id,order_id,status){

	$.ajax({
		
			url:"/pament/update_oder",
			data:JSON.stringify({
				payement_id:payement_id,
				order_id:order_id,
				status:status
			}),
			contentType:'application/json',
			type:'POST',
			dataType:'json',
			success:function(response){
				alert("Yes! payment successfull ..");
			},
			error:function(error){
				alert("oops payment failed !!");
			},
		});
}

//---------------------------------------------------------------------------

