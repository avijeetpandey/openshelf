package com.avijeet.openshelf.config;

import com.avijeet.openshelf.entities.Member;
import com.avijeet.openshelf.repository.MemberRepository;
import com.avijeet.openshelf.utils.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final JwtUtils jwtUtils;
    private final MemberRepository memberRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(7);
        if (jwtUtils.validateToken(token)) {
            String email = jwtUtils.getEmailFromToken(token);

            // Ensure we aren't re-authenticating if already set in context
            if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                Member member = memberRepository.findByEmail(email);

                if (member != null) {
                    // Create authorities list from the user's role
                    UsernamePasswordAuthenticationToken authToken = getUsernamePasswordAuthenticationToken(member);

                    // Set details like IP address, session ID if needed
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    // Finalize: Set the user as 'Authenticated' for this request
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
        }

        filterChain.doFilter(request, response);
    }

    private static UsernamePasswordAuthenticationToken getUsernamePasswordAuthenticationToken(Member member) {
        List<SimpleGrantedAuthority> authorities = List.of(
                new SimpleGrantedAuthority("ROLE_" + member.getRole().name())
        );

        // Create the Authentication Token
        return new UsernamePasswordAuthenticationToken(
                member.getEmail(),
                null, // Password isn't needed for a stateless token
                authorities
        );
    }
}