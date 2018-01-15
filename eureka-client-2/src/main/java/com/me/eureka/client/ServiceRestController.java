package com.me.eureka.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ServiceRestController {
	
	private String client1HelloPath = "/service-instances/service-client-1/getHello";
	
	@Autowired
	private DiscoveryClient discoveryClient;
	
	RestTemplate restTemplate = new RestTemplate();
	
	@RequestMapping("/service-instances/{appName}")
	public List<ServiceInstance> serviceInstancesByAppName(@PathVariable final String appName){
		System.out.println("App requested : " + appName);
		return this.discoveryClient.getInstances(appName);
	}
	
	@RequestMapping("/service-instances/{appName}/getHello")
	public String getHello(@PathVariable final String appName){
		
		return "Welcome !, This is Client 2";
	}
	
	@RequestMapping("/service-instances/askForHello")
	public String getHelloFromOtherClient(){
		ResponseEntity response = this.restTemplate.exchange(
				this.discoveryClient.getInstances("service-client-1").get(0).getUri().toString()+this.client1HelloPath, HttpMethod.GET,
				null, new ParameterizedTypeReference<String>() {}, ""
				); 
		
		return response.getBody().toString();// this is from client 1 via client 2
	}
}
