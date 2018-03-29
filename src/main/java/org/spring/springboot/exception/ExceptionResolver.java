package org.spring.springboot.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class ExceptionResolver extends AbstractHandlerExceptionResolver {
    final Logger log = LoggerFactory.getLogger(ExceptionResolver.class);
    @Autowired
    private MappingJackson2JsonView jsonView;

    public ExceptionResolver() {
    }

    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        this.log.info("custom ExceptionResolver.doResolveException (getAuthType={} getPathInfo={}, getRequestURL={} getRemoteUser={} getQueryString={} getMethod={} errorMessage = {} )", request.getAuthType(), request.getPathInfo(), request.getRequestURL(), request.getRemoteUser(), request.getQueryString(), request.getMethod(), ex.getMessage());
        ModelAndView view = new ModelAndView(this.jsonView);
        InetAddress inet = null;

        try {
            inet = InetAddress.getByName(request.getRemoteAddr());
        } catch (UnknownHostException var8) {
            this.log.info("could not get remote client ip");
        }

        if(inet != null) {
            this.log.info("custom ExceptionResolver.doResolveException(requestIP={})", inet.getHostAddress());
            //view.addObject("clientIp", inet.getHostAddress());
        }

        if(ex instanceof MyException) {
            MyException e = (MyException)ex;
            //view.addObject("error", Boolean.valueOf(true));
            //view.addObject("errorType", e.getErrorType());
            view.addObject("code", e.getErrorCode().toString());
            view.addObject("message", e.getMessage());
            if(e.getErrorData() != null) {
                view.addObject("content", e.getErrorData());
            }
        } else if(ex instanceof MissingServletRequestParameterException) {
           // view.addObject("error", Boolean.valueOf(true));
            //view.addObject("errorType", MyExceptionType.BUSINESS);
            view.addObject("code", MyExceptionCode.PARAM_REQUIRED_EXCEPTION.toString());
            view.addObject("message", ex.getMessage());
        } else {
            //view.addObject("error", Boolean.valueOf(true));
           //view.addObject("errorType", MyExceptionType.SYSTEM);
            view.addObject("code", MyExceptionCode.SYSTEM_EXCEPTION.toString());
            view.addObject("message", ex.getMessage());
            this.log.error("", ex);
        }

        return view;
    }
}
