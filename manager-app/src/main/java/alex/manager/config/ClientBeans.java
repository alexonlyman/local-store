package alex.manager.config;

import alex.manager.client.ProductsRestClientImpl;
import alex.manager.security.OAuthClientRequstEnterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.web.client.RestClient;

@Configuration
public class ClientBeans {
    @Bean
    ProductsRestClientImpl productsRestClient(@Value("${local-store.service.catalogue.uri:http://localhost:8081}")
                                              String baseUrl,
                                              ClientRegistrationRepository clientRegistrationRepository,
                                              OAuth2AuthorizedClientRepository authorizedClientRepository,
                                              @Value("${local-store.service.catalogue.registrationId:keycloak}")String registrationId
                                              ) {
        return new ProductsRestClientImpl(RestClient.builder()
                .requestInterceptor(new OAuthClientRequstEnterceptor(new DefaultOAuth2AuthorizedClientManager(
                        clientRegistrationRepository,authorizedClientRepository),registrationId))
                .baseUrl(baseUrl)
                .build());
    }

}
