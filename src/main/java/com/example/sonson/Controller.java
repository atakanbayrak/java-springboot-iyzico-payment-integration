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
	CreateCheckoutFormInitializeRequest request = new CreateCheckoutFormInitializeRequest();
	List<BasketItem> items = new ArrayList<BasketItem>();
	Iyzico iyzico;
	Threed threed;
	Iyzico iyzipay_checkout_form;
	Payment payment;
	PaymentCard paymentCard = new PaymentCard();
	@RequestMapping(value="/pay", method = RequestMethod.GET)
	public String payment(ModelMap model) 
	{
		iyzico = new Iyzico();
		iyzico.setForm("123456789", "120.0", "126.0", "SPT123456");
		iyzico.setBuyer("123", "Ahmet", "Buyer", "05075077575", "atakan@hotmail.com", "1234567901", "Alici Adresi Istanbul", "192.169.02.01","Istanbul", "Turkiye");
		iyzico.setShipping("Veli Kısabacak", "Ankara", "Türkiye", "Kargonun adresi ile ayni");
		iyzico.setBilling("Veli Kısabacak", "Ankara", "Türkiye", "Kargonun adresi ile ayni");
		iyzico.setItems("8749", "Ayakkabi", "Erkek Ayakkabi","Gunluk Ayakkabi", "120");
		
		
		String content = iyzico.paymentForm().getCheckoutFormContent();
		model.put("content", iyzico.paymentForm().getCheckoutFormContent());
		
		return "payment";
	}
	
	@RequestMapping(value="/pay", method=RequestMethod.PUT)
	public String threeD(ModelMap model,@RequestParam String cardHolderName, @RequestParam String cardNumber
			,@RequestParam String expireDate,@RequestParam String cvc)
	{
		System.out.println(cardHolderName);
		paymentCard.setCardHolderName(cardHolderName);
		paymentCard.setCardNumber(cardNumber);
		paymentCard.setExpireMonth(expireDate);
		paymentCard.setCvc(cvc);
		String contentThree = threed.paymentForm().getHtmlContent();
		model.put("contentThree", threed.paymentForm().getHtmlContent());
		System.out.println(contentThree);
		return "threed";
		
	}
}
