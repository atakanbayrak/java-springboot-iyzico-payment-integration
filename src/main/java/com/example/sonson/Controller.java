package com.example.sonson;

import java.util.ArrayList;
import java.util.List;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iyzipay.model.BasketItem;
import com.iyzipay.model.CheckoutFormInitialize;
import com.iyzipay.model.Payment;
import com.iyzipay.model.PaymentCard;
import com.iyzipay.request.CreateCheckoutFormInitializeRequest;

@org.springframework.stereotype.Controller
public class Controller {
	List<BasketItem> items = new ArrayList<BasketItem>();
	Iyzico iyzico;
	Threed threed;
	Iyzico iyzipay_checkout_form;
	Payment payment;
	PaymentCard paymentCard = new PaymentCard();
	
	@RequestMapping(value="/pay", method = RequestMethod.GET)
	public String payment(ModelMap model) 
	{
		threed = new Threed();
		threed.setForm("123456789", "120.0", "126.0", "SPT123456");
		threed.setBuyer("123", "Ahmet", "Buyer", "05075077575", "atakan@hotmail.com", "1234567901", "Alici Adresi Istanbul", "192.169.02.01","Istanbul", "Turkiye");
		threed.setShipping("Veli Kısabacak", "Ankara", "Türkiye", "Kargonun adresi ile ayni");
		threed.setBilling("Veli Kısabacak", "Ankara", "Türkiye", "Kargonun adresi ile ayni");
		threed.setItems("8749", "Ayakkabi", "Erkek Ayakkabi","Gunluk Ayakkabi", "120");
		threed.setPaymentCard("Atakan Bayrak", "4603450000000000", "10", "25", "425");
		
		
		String content = threed.paymentForm().getHtmlContent();
		model.put("content", content);
		System.out.println(threed.paymentForm().getHtmlContent());
		return "payment";
	}
	
	
}
