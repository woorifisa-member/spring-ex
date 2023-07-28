package com.woorifisa.board.argumentresolver;

import com.woorifisa.board.annotation.Login;
import com.woorifisa.board.common.dto.session.MemberSession;
import com.woorifisa.board.domain.member.exception.ForbiddenException;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Slf4j
public class MemberArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        log.info("====== Argument Resolver supportsParameter ======");
        boolean hasAnnotation = parameter.hasParameterAnnotation(Login.class);
        boolean isAssigned = MemberSession.class.isAssignableFrom(parameter.getParameterType());

        return hasAnnotation && isAssigned;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        log.info("===== Argument Resolver resolveArgument =====");

        HttpServletRequest req = (HttpServletRequest) webRequest.getNativeRequest();
        HttpSession session = req.getSession();

        return Optional.ofNullable(session.getAttribute(MemberSession.KEY))
                       .orElseThrow(ForbiddenException::new);
    }

}
