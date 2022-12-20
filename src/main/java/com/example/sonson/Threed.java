package com.example.sonson;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;

import com.iyzipay.Options;
import com.iyzipay.model.Address;
import com.iyzipay.model.BasketItem;
import com.iyzipay.model.BasketItemType;
import com.iyzipay.model.Buyer;
import com.iyzipay.model.Currency;
import com.iyzipay.model.Locale;
import com.iyzipay.model.PaymentCard;
import com.iyzipay.model.PaymentGroup;
import com.iyzipay.model.ThreedsInitialize;
import com.iyzipay.request.CreatePaymentRequest;

public class Threed {

	private Options options;
	// private Request request;
	CreatePaymentRequest request = new CreatePaymentRequest();
	private List<BasketItem> basketItems;

	public Threed() {
		this.options = new Options();
		this.options.setApiKey("ulZGIdysNVA0GP598cFEb4L82V5g1QEW");
		this.options.setSecretKey("0lGZ8MJVWZYtstsVn5MMM1zRcM9oPuDj");
		this.options.setBaseUrl("https://api.iyzipay.com");
		basketItems = new ArrayList<BasketItem>();
	}

	public CreatePaymentRequest setForm(@RequestParam String conversationId, @RequestParam String price,
			@RequestParam String paidPrice, @RequestParam String basketId) {

		this.request.setLocale(Locale.TR.getValue());
		this.request.setConversationId(conversationId);
		this.request.setPrice(new BigDecimal(price));
		this.request.setPaidPrice(new BigDecimal(paidPrice));
		this.request.setCurrency(Currency.TRY.name());
		this.request.setBasketId(basketId);
		this.request.setPaymentGroup(PaymentGroup.PRODUCT.name());
		this.request.setCallbackUrl("http://localhost:8080/callback");
		return request;
	}

	public Buyer setBuyer(@RequestParam String id, @RequestParam String name, @RequestParam String surname,
			@RequestParam String gsmNumber, @RequestParam String email, @RequestParam String idNumber,
			@RequestParam String registrationAddress, @RequestParam String ip, @RequestParam String city,
			@RequestParam String country) {
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

	public Address setShipping(@RequestParam String contactName, @RequestParam String city,
			@RequestParam String country, @RequestParam String address) {

		Address shippingAddress = new Address();
		shippingAddress.setContactName(contactName);
		shippingAddress.setCity(city);
		shippingAddress.setCountry(country);
		shippingAddress.setAddress(address);
		request.setShippingAddress(shippingAddress);
		return shippingAddress;
	}

	public PaymentCard setPaymentCard(@RequestParam String cardHolderName, @RequestParam String cardNumber,
			@RequestParam String expireMonth, @RequestParam String expireYear,@RequestParam String cvc) {
		PaymentCard paymentCard = new PaymentCard();
		paymentCard.setCardHolderName(cardHolderName);
		paymentCard.setCardNumber(cardNumber);
		paymentCard.setExpireMonth(expireMonth);
		paymentCard.setExpireYear(expireYear);
		paymentCard.setCvc(cvc);
		paymentCard.setRegisterCard(0);
		request.setPaymentCard(paymentCard);
		return paymentCard;
	}

	public Address setBilling(@RequestParam String contactName, @RequestParam String city, @RequestParam String country,
			@RequestParam String address) {
		Address billingAddress = new Address();
		billingAddress.setContactName(contactName);
		billingAddress.setCity(city);
		billingAddress.setCountry(country);
		billingAddress.setAddress(address);
		request.setBillingAddress(billingAddress);
		return billingAddress;
	}

	public BasketItem setItems(@RequestParam String id, @RequestParam String name, @RequestParam String category1,
			@RequestParam String category2, @RequestParam String price, @RequestParam String subprice) {
		BasketItem basketItem = new BasketItem();

		basketItem.setId(id);
		basketItem.setName(name);
		basketItem.setCategory1(category1);
		basketItem.setCategory2(category2);
		basketItem.setItemType(BasketItemType.PHYSICAL.name());
		basketItem.setPrice(new BigDecimal(price));
		basketItem.setSubMerchantKey("OEeagHh2BF2ff/cETTxNfuURiiI=");
		basketItem.setSubMerchantPrice(new BigDecimal(subprice));
		basketItems.add(basketItem);

		request.setBasketItems(basketItems);
		return basketItem;
	}

	public ThreedsInitialize paymentForm() {
		ThreedsInitialize threedsInitialize = ThreedsInitialize.create(request, options);
		return threedsInitialize;
	}

}
