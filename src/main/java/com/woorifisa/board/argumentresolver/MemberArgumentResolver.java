package com.woorifisa.board.argumentresolver;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.woorifisa.board.annotation.Login;
import com.woorifisa.board.common.dto.session.MemberSession;
import com.woorifisa.board.domain.member.exception.ForbiddenException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MemberArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		log.info("====== Argument Resolver supportsParameter ======");

		// (@Login MemberSession memberSession)

		// 파라미터의 어노테이션 정보 (@Login 이 붙어있나)
		boolean hasAnnotation = parameter.hasParameterAnnotation(Login.class);
		// Login parameterAnnotation = parameter.getParameterAnnotation(Login.class);
		// String role = parameterAnnotation.role();
		//
		// if (!role.equals("ADMIN")){
		// 	throw new Exception();
		// }

		// 파라미터의 타입 정보 (MemberSession 클래스인가)
		boolean isAssigned = MemberSession.class.isAssignableFrom(parameter.getParameterType());

		// return hasAnnotation && isAssigned;
		return isAssigned;
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
		NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

		log.info("===== Argument Resolver resolveArgument =====");

		HttpServletRequest req = (HttpServletRequest)webRequest.getNativeRequest();
		HttpSession session = req.getSession();

		MemberSession attribute = (MemberSession)session.getAttribute(MemberSession.KEY);
		Optional<MemberSession> opt = Optional.ofNullable(attribute);
		MemberSession memberSession = opt.orElseThrow(ForbiddenException::new);

		// return Optional.ofNullable(session.getAttribute(MemberSession.KEY))
		//		.orElseThrow(ForbiddenException::new);

		return memberSession;
	}

}
