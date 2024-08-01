package alex.manager.security;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;

import java.io.IOException;

@RequiredArgsConstructor
public class OAuthClientRequstEnterceptor implements ClientHttpRequestInterceptor {

    private final OAuth2AuthorizedClientManager authorizedClientManager;
    private final String registrationId;
    private final SecurityContextHolderStrategy strategy = SecurityContextHolder.getContextHolderStrategy();

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        if (!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
            OAuth2AuthorizedClient client = this.authorizedClientManager.authorize(OAuth2AuthorizeRequest.withClientRegistrationId(this.registrationId)
                    .principal(strategy.getContext().getAuthentication())
                    .build());
            request.getHeaders().setBearerAuth(client.getAccessToken().getTokenValue());
        }
        return execution.execute(request, body);
    }
}
