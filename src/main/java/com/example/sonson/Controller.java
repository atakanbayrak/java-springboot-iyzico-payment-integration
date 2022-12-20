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

	Iyzico iyzipay_checkout_form;
	Payment payment;
	PaymentCard paymentCard = new PaymentCard();
	Threed threed = new Threed();
	@RequestMapping(value="/pay", method = RequestMethod.GET)
	public String payment(ModelMap model,@RequestParam(required = false) String conversationId, @RequestParam String price,
			@RequestParam String paidPrice, @RequestParam String basketId,
			
			@RequestParam String id, @RequestParam String name, @RequestParam String surname,
			@RequestParam String gsmNumber, @RequestParam String email, @RequestParam String idNumber,
			@RequestParam String registrationAddress, @RequestParam String ip, @RequestParam String city,
			@RequestParam String country,
			
			@RequestParam String contactName,@RequestParam String address,
			
			@RequestParam String cardHolderName, @RequestParam String cardNumber,
			@RequestParam String expireMonth, @RequestParam String expireYear,@RequestParam String cvc,
			
			@RequestParam String itemId, @RequestParam String itemName, @RequestParam String category1,
			@RequestParam String category2, @RequestParam String subprice
			) 
	{
		threed.setForm(conversationId, price, paidPrice, basketId);
		threed.setBuyer(id, name, surname, gsmNumber, email, idNumber, registrationAddress, ip, city, country);
		threed.setShipping(contactName, city, country, address);
		threed.setBilling(contactName, city, country, address);
		threed.setItems(itemId, itemName, category1,category2, price,subprice);
		threed.setPaymentCard(cardHolderName, cardNumber, expireMonth, expireYear, cvc);
		
		String content = threed.paymentForm().getHtmlContent();
		model.put("content", content);
		//System.out.println(threed.paymentForm().getHtmlContent());
		return "payment";
	}
	
	@RequestMapping(value="/callback")
	public String callback(ModelMap model) 
	{
		String contentCallBackStatus = threed.paymentForm().getStatus();
		String contentCallBackConversationId = threed.paymentForm().getConversationId();
		String contentCallBackError = threed.paymentForm().getErrorMessage();
		String contentCallBackErrorCode = threed.paymentForm().getErrorCode();
		String contentCallBackErrorGroup = threed.paymentForm().getErrorGroup();
		System.out.println(contentCallBackStatus);
		model.put("contentCallBackError",contentCallBackError);
		model.put("contentCallBackStatus",contentCallBackStatus);
		model.put("contentCallBackConversationId",contentCallBackConversationId);
		model.put("contentCallBackErrorCode",contentCallBackErrorCode);
		model.put("contentCallBackErrorGroup",contentCallBackErrorGroup);
		//System.out.println(threed.paymentForm().getHtmlContent());
		return "callback";
	}
	
	
}

/*
 * 
 * threed.setForm(conversationId, price, paidPrice, basketId);
		threed.setBuyer(id, name, surname, gsmNumber, email, idNumber, registrationAddress, ip, city, country);
		threed.setShipping(contactName, city, country, address);
		threed.setBilling(contactName, city, country, address);
		threed.setItems(itemId, itemName, category1,category2, price,subprice);
		threed.setPaymentCard(cardHolderName, cardNumber, expireMonth, expireYear, cvc);
threed.setForm("123456789", "1.0", "1.0", "SPT123456");
threed.setBuyer("123", "Ahmet", "Buyer", "05075077575", "atakan@hotmail.com", "1234567901", "Alici Adresi Istanbul", "192.169.02.01","Istanbul", "Turkiye");
threed.setShipping("Veli Kısabacak", "Ankara", "Türkiye", "Kargonun adresi ile ayni");
threed.setBilling("Veli Kısabacak", "Ankara", "Türkiye", "Kargonun adresi ile ayni");
threed.setItems("8749", "Ayakkabi", "Erkek Ayakkabi","Gunluk Ayakkabi", "1.0","0.70");
threed.setPaymentCard("Atakan Bayrak", "5168880000000002", "08", "23", "373");
System.out.println(threed.paymentForm().getConversationId());

*
*http://localhost:8080/pay?conversationId=123456789&price=1&paidPrice=1&basketId=SPT123456&id=123&name=Ahmet&surname=Buyer&gsmNumber=05075077575&email=atakan@hotmail.com&idNumber=1234567901&registrationAddress=Alici Adresi Istanbul&ip=192.169.02.01&city=Istanbul&country=Turkiye&contactName=Veli Kısabacak&address=Ankara&cardHolderName=Atakan Bayrak&cardNumber=5168880000000002&expireMonth=08&expireYear=23&cvc=373&itemId=8749&itemName=Ayakkabi&category1=Erkek Ayakkabi&category2=Gunluk Ayakkabi&subprice=0.70
*/
