package com.example.sonson;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;

import com.iyzipay.Options;
import com.iyzipay.Request;
import com.iyzipay.model.Address;
import com.iyzipay.model.BasketItem;
import com.iyzipay.model.BasketItemType;
import com.iyzipay.model.Buyer;
import com.iyzipay.model.CheckoutFormInitialize;
import com.iyzipay.model.Currency;
import com.iyzipay.model.Locale;
import com.iyzipay.model.PaymentGroup;
import com.iyzipay.request.CreateCheckoutFormInitializeRequest;

public class Iyzico {
	
	private Options options;
	//private Request request;
	CreateCheckoutFormInitializeRequest request = new CreateCheckoutFormInitializeRequest();
	private List<BasketItem> basketItems;
	public Iyzico(){
		this.options = new Options();
		this.options.setApiKey("sandbox-zCUOOTp8b1gj2GpryC6TVHGJwFtue772");
		this.options.setSecretKey("sandbox-pCgicvmee36ULbvf1f8Zgm1iSYXXf7Rr");
		this.options.setBaseUrl("https://sandbox-api.iyzipay.com");
		basketItems = new ArrayList<BasketItem>();
	}
	
	public CreateCheckoutFormInitializeRequest setForm(@RequestParam String conversationId,@RequestParam String price,@RequestParam String paidPrice, @RequestParam String basketId) {
		
		this.request.setLocale(Locale.TR.getValue());
		this.request.setConversationId(conversationId);
		this.request.setPrice(new BigDecimal(price));
		this.request.setPaidPrice(new BigDecimal(paidPrice));
		this.request.setCurrency(Currency.TRY.name());
		this.request.setBasketId(basketId);
		this.request.setPaymentGroup(PaymentGroup.PRODUCT.name());
		this.request.setCallbackUrl("localhost:8080");
		return request;
	}
	
	public Buyer setBuyer(@RequestParam String id,@RequestParam String name,@RequestParam String surname, @RequestParam String gsmNumber
			,@RequestParam String email,@RequestParam String idNumber,@RequestParam String registrationAddress, @RequestParam String ip
			,@RequestParam String city,@RequestParam String country) {
		Buyer buyer = new Buyer();
		buyer.setId(id);
		buyer.setName(name);
		buyer.setSurname(surname);
		buyer.setGsmNumber(gsmNumber);
		buyer.setEmail(email);
		buyer.setIdentityNumber(idNumber);
		buyer.setRegistrationAddress(registrationAddress);
		buyer.setIp(ip);
		buyer.setCity(city);
		buyer.setCountry(country);
		this.request.setBuyer(buyer);
		return buyer;
	}
	
	public Address setShipping(@RequestParam String contactName,@RequestParam String city,@RequestParam String country, @RequestParam String address) {

		Address shippingAddress = new Address();
		shippingAddress.setContactName(contactName);
		shippingAddress.setCity(city);
		shippingAddress.setCountry(country);
		shippingAddress.setAddress(address);
		request.setShippingAddress(shippingAddress);
		return shippingAddress;
	}
	
	public Address setBilling(@RequestParam String contactName,@RequestParam String city,@RequestParam String country, @RequestParam String address) {
		Address billingAddress = new Address();
		billingAddress.setContactName(contactName);
		billingAddress.setCity(city);
		billingAddress.setCountry(country);
		billingAddress.setAddress(address);
		request.setBillingAddress(billingAddress);
		return billingAddress;
	}
	
	public BasketItem setItems(@RequestParam String id, @RequestParam String name, @RequestParam String category1, @RequestParam String category2
			,@RequestParam String price) {
		BasketItem basketItem = new BasketItem();
		
			basketItem.setId(id);
			basketItem.setName(name);
			basketItem.setCategory1(category1);
			basketItem.setCategory2(category2);
			basketItem.setItemType(BasketItemType.PHYSICAL.name());
			basketItem.setPrice(new BigDecimal(price));
			basketItems.add(basketItem);
		
		request.setBasketItems(basketItems);
		return basketItem;
	}
	
	public CheckoutFormInitialize paymentForm() {
		CheckoutFormInitialize checkoutFormInitialize = CheckoutFormInitialize.create(request, options);
		return checkoutFormInitialize;
	}
	

}
