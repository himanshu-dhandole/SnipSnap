package himanshu.snipsnap.security;

import himanshu.snipsnap.service.UserDetailsImpl;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class JwtUtil {

    @Value("${jwt.secretKey}")
    private String secret ;
    @Value("${jwt.expiration}")
    private Long expiration ;


    String token = null ;
    String bearerToken = null ;

    public String getJwtToken(HttpServletRequest request) {
        bearerToken = request.getHeader("Authorization") ;
        if (bearerToken!=null && bearerToken.startsWith("Bearer ")){
            token = bearerToken.substring(7).trim() ;
            return token ;
        }
        return null ;
    }

    public String generateToken(UserDetailsImpl userDetails) {
        String name = userDetails.getName();
        String roles = userDetails.getAuthorities().stream()
                .map(authority -> authority.getAuthority())
                .collect(Collectors.joining(","));

        return Jwts
                .builder()
                .setSubject(name)
                .claim("roles", roles)
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + expiration))
                .signWith(key())
                .compact();
    }

    public String getNameFromToken(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(key())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }


    public boolean validateToken(String authToken) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key())
                    .build()
                    .parseClaimsJws(authToken) ;

            return true ;
        } catch (ExpiredJwtException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedJwtException e) {
            throw new RuntimeException(e);
        } catch (MalformedJwtException e) {
            throw new RuntimeException(e);
        } catch (SignatureException e) {
            throw new RuntimeException(e);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        }
    }


    public SecretKey key (){
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8)) ;
    }
}
