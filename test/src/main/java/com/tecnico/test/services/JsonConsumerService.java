package com.tecnico.test.services;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.tecnico.test.domain.Doador;

@Service
public class JsonConsumerService {

	public Doador[] consumeJsonWeb(String url) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Doador[]> response = restTemplate.exchange(url, HttpMethod.GET, null, Doador[].class);
		return response.getBody();
	}
}
