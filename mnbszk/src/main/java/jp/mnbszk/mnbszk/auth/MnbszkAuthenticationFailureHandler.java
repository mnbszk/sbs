package jp.mnbszk.mnbszk.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import jp.mnbszk.framework.util.MnbszkErrorMessageMap;

public class MnbszkAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException exception)
                    throws IOException, ServletException
    {
        String errorId = "";

        // ExceptionからエラーIDをセットする
        if (exception instanceof BadCredentialsException) {
            errorId = MnbszkErrorMessageMap.MSG_ERROR_0001;
        }
        
        // ログイン画面にリダイレクトする
        response.sendRedirect(request.getContextPath() + "/" + request.getParameter("identifier") + "/index?error=" + errorId);
    }

}
