package com.bytatech.ayoos.appointment.client.doctor;



import java.io.IOException;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bytatech.ayoos.appointment.client.ExcludeFromComponentScan;
import com.bytatech.ayoos.appointment.client.TokenRelayRequestInterceptor;
import com.bytatech.ayoos.appointment.security.oauth2.AuthorizationHeaderUtil;

import feign.RequestInterceptor;

@Configuration
@ExcludeFromComponentScan
@EnableConfigurationProperties
public class ClientConfiguration {

	
	 @Bean(name = "oauth2RequestInterceptor")
	    public RequestInterceptor getOAuth2RequestInterceptor(AuthorizationHeaderUtil authorizationHeaderUtil) throws IOException {
	        return new TokenRelayRequestInterceptor(authorizationHeaderUtil);
	    }
}
