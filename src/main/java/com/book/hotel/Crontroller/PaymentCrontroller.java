package com.book.hotel.Crontroller;

import java.security.Principal;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.book.hotel.Entity.Payment;
import com.book.hotel.Repository.PaymentRepository;
import com.book.hotel.Repository.UserRepository;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

@Controller
@RequestMapping("/pament")
public class PaymentCrontroller {
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private UserRepository userRepository;
	

	@PostMapping("/create_oder")
	@ResponseBody
	public String creatOder(@RequestBody Map<String, Object> data,Principal principal) throws RazorpayException {
		
		int amt = Integer.parseInt(data.get("amount").toString());
		
		RazorpayClient client = new RazorpayClient("rzp_test_z5ecg7cMwOXtbC", "URaFlbBCUKfHglAjI2vUKkFo");
		
		JSONObject ob=new JSONObject();
		ob.put("amount",amt*100);
		ob.put("currency","INR");
		ob.put("receipt","txn_235425");
		
		//creating new order
		Order order = client.Orders.create(ob);
		System.out.println(order);
		
		//save DataBase
		
		Payment payment = new Payment();
		payment.setAmount(order.get("amount")+"");
		payment.setAmount(order.get("id"));
		payment.setPaymentId(null);
		payment.setStatus("created");
		payment.setUser(this.userRepository.getUserByUserName(principal.getName()));
		payment.setReceipt(order.get("receipt"));
		
		this.paymentRepository.save(payment);
		
		return order.toString();
	}
	
	@PostMapping("/update_oder")
	public ResponseEntity<?> update_oder(@RequestBody Map<String, Object> data){
		
		Payment pay = this.paymentRepository.findByOrderId(data.get("order_id").toString());
		
		pay.setPaymentId(data.get("payment_id").toString());
		pay.setStatus(data.get("status").toString());
		
		this.paymentRepository.save(pay);
		
		System.out.println(data);
		return ResponseEntity.ok(Map.of("msg","update"));
	}
}
