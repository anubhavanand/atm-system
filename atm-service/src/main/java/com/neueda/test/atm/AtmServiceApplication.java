package com.neueda.test.atm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.neueda.test.atm.dispenser.CurrencyDispenser;
import com.neueda.test.atm.dispenser.FiftyCurrencyDispenser;
import com.neueda.test.atm.dispenser.FiveCurrencyDispenser;
import com.neueda.test.atm.dispenser.TenCurrencyDispenser;
import com.neueda.test.atm.dispenser.TwentyCurrencyDispenser;

@SpringBootApplication
public class AtmServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AtmServiceApplication.class, args);
	}
	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	@Bean
	public CurrencyDispenser currencyDispenser() {
		final CurrencyDispenser fiftyCurrencyDispenser = new FiftyCurrencyDispenser();
		final CurrencyDispenser twentyCurrencyDispenser = new TwentyCurrencyDispenser();
		final CurrencyDispenser tenCurrencyDispenser = new TenCurrencyDispenser();
		final CurrencyDispenser fiveCurrencyDispenser = new FiveCurrencyDispenser();
		
		fiftyCurrencyDispenser.setNextDispenser(twentyCurrencyDispenser);
		twentyCurrencyDispenser.setNextDispenser(tenCurrencyDispenser);
		tenCurrencyDispenser.setNextDispenser(fiveCurrencyDispenser);
		
		return fiftyCurrencyDispenser;
	}
	
}
