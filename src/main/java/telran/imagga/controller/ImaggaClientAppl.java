package telran.imagga.controller;

import java.util.Arrays;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import telran.imagga.dto.ResponseDto;
import telran.imagga.dto.Tag;

public class ImaggaClientAppl {

	public static void main(String[] args) {
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization","Basic YWNjX2IzNGQyY2FhMGVkMzdlNTplZWE0ZjRhZjkzYzFjYjM4MmFkZGRjZjg0OGRkNWNkZQ==");
		HttpEntity<String> reguestEntity = new HttpEntity<>(headers);
		
		String url = "http://api.imagga.com/v1/tagging";
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
				.queryParam("url", "https://image.tmdb.org/t/p/w533_and_h300_bestv2/87mvGYiKkV4PDNu9m0NIr0OPBrY.jpg")
				.queryParam("language", "ru");
		//String endpoints = "?url=https://image.tmdb.org/t/p/w533_and_h300_bestv2/87mvGYiKkV4PDNu9m0NIr0OPBrY.jpg&language=ru";
		ResponseEntity<ResponseDto> response = restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.GET,reguestEntity,ResponseDto.class);
		//parametru zaprosa + tip zaprosa,metod ,zagolovki i telo ,pomesti v klas takoi to(
		displayTags(response.getBody().getResults()[0].getTags());

	}

	private static void displayTags(Tag[] tags) {
		Arrays.stream(tags).limit(3).forEach(t -> System.out.println(t.getTag() + "->" + t.getConfidence()));
		
	}

}
