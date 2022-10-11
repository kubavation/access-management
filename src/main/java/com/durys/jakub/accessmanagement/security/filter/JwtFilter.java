package com.durys.jakub.accessmanagement.security.filter;

import com.durys.jakub.accessmanagement.security.util.JwtSecurityUtils;
import com.durys.jakub.accessmanagement.user.model.util.AmUserDetails;
import com.durys.jakub.accessmanagement.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private static final String AUTH_HEADER = "Authorization";
    private static final String BEARER = "Bearer ";

    private final UserService userService;
    private final JwtSecurityUtils jwtSecurityUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        final String authHeader = request.getHeader(AUTH_HEADER);

        if (!Objects.isNull(authHeader) && authHeader.startsWith(BEARER)) {

            String token = authHeader.substring(7);
            String username = jwtSecurityUtils.getUsername(token);


            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                AmUserDetails userDetails = UserService.toAmUserDetails(userService.findByUsername(username));

                if (jwtSecurityUtils.isTokenValid(token, userDetails)) {

                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken
                            .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }

            }


        }

        filterChain.doFilter(request, response);
    }
}
