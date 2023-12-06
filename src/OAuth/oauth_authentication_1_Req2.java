//oauth_authentication_1_Req2.java

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class oauth_authentication_1_Req2 {
    @GetMapping("/")
    public String getUser(@AuthenticationPrincipal OAuth2User principal) {
        return principal.toString();
    }
}