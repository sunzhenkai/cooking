package pub.wii.cook.springboot.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.*;
import java.io.IOException;

@Configuration
public class FilterConfig implements WebMvcConfigurer {
    @Bean
    public FilterRegistrationBean<SampleFilter> registerWizardProjectTenantFilter() {
        FilterRegistrationBean<SampleFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new SampleFilter());
        registration.addUrlPatterns("/*");
        registration.setName("sampleFilter");
        registration.setOrder(3);
        return registration;
    }

    public static class SampleFilter implements Filter {

        @Override
        public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                             FilterChain filterChain) throws ServletException, IOException {
            servletRequest.setAttribute("foo", "bar");
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
