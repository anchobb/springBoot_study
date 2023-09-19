package com.example.demo.interceptor;

import com.example.demo.config.auth.PrincipalDetails;
import com.example.demo.domain.entity.Board;
import com.example.demo.domain.repository.BoardRepository;
import com.nimbusds.jose.proc.SecurityContext;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BoardInterceptor implements HandlerInterceptor {

    private final BoardRepository boardRepository;


    public BoardInterceptor(BoardRepository boardRepository){
        this.boardRepository = boardRepository;
    }



    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //게시물작성 사용자 정보
        Long no = Long.parseLong(request.getParameter("no"));
        Board board = boardRepository.findById(no).get();
        System.out.println("[Interceptor] Board Update Interceptor..." + board);
        String boardUsername = board.getUsername();
        //접속중인 사용자 정보
        PrincipalDetails principalDetails = (PrincipalDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String authUsername = principalDetails.getUsername();

        if(!boardUsername.equals(authUsername))
        {
            response.sendRedirect("/board/error");
            return false;
        }

        return true; //계정정보 일치 시 update 페이지로 진입
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
