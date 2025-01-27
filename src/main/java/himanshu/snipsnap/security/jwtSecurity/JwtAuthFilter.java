package himanshu.snipsnap.security.jwtSecurity;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtUtil jwtHelper ;
    private final UserDetailsService userDetailsService ;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        try{
            // get jwt token from request header
            String token = jwtHelper.getJwtToken(request) ;

            // validate token
            if (token != null && jwtHelper.validateToken(token)) {

                // get username from token
                String name = jwtHelper.getNameFromToken(token) ;

                // load username
                UserDetails userDetails = userDetailsService.loadUserByUsername(name);

                if (userDetails != null ) {
                    UsernamePasswordAuthenticationToken auth = new
                            UsernamePasswordAuthenticationToken(userDetails , null , userDetails.getAuthorities()) ;
                    auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    // set authentication in the security context
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        filterChain.doFilter(request, response);
    }

}