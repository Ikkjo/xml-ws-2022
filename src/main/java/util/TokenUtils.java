package util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class TokenUtils {
    public static String SECRET = "salty_secret";
    private final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS512;
    @Value("Authorization")
    private String AUTH_HEADER;

    public String getRoleFromHeader(HttpServletRequest request) {

        String token = getTokenFromHeader(request).substring(7);

        try {

            final Claims claims = this.getAllClaimsFromToken(token);
            return claims.get("role", String.class);

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    private String getTokenFromHeader(HttpServletRequest request) {
        return request.getHeader(AUTH_HEADER);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();
    }
}
