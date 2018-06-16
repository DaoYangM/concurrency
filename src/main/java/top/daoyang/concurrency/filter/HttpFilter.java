package top.daoyang.concurrency.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import top.daoyang.concurrency.utils.RequestHolder;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
@Component
public class HttpFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Long tId = Thread.currentThread().getId();
        RequestHolder.add(tId);
        log.info("HttpFilter; thread ID: {} Request address: {}", tId, ((HttpServletRequest)servletRequest).getRequestURL().toString());

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
