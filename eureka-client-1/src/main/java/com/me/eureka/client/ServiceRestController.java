package com.me.eureka.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceRestController {
	
	@Autowired
	private DiscoveryClient discoveryClient;
	
	@RequestMapping("/service-instances/{appName}")
	public List<ServiceInstance> serviceInstancesByAppName(@PathVariable final String appName){
		System.out.println("App requested : " + appName);
		return this.discoveryClient.getInstances(appName);
	}
	
	@RequestMapping("/service-instances/{appName}/getHello")
	public String getHello(@PathVariable final String appName){
		
		return "Welcome !, This is Client 1";
	}
}
