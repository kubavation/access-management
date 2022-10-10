package com.durys.jakub.accessmanagement.security.filter;

import com.durys.jakub.accessmanagement.user.model.entity.User;
import com.durys.jakub.accessmanagement.user.service.UserService;
import lombok.RequiredArgsConstructor;
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

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        final String authHeader = request.getHeader(AUTH_HEADER);

        if (!Objects.isNull(authHeader) && authHeader.startsWith(BEARER)) {

            String token = authHeader.substring(7);
            String username = "todo";

            //TODO
            //if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            User user = userService.findByUsername(username);

//            if (jwtUtils.validateToken(token, userDetails)) {
//
//                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
//                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//                usernamePasswordAuthenticationToken
//                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
//                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//            }

        }

        filterChain.doFilter(request, response);
    }
}
