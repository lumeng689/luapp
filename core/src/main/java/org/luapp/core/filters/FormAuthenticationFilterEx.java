package org.luapp.core.filters;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.luapp.core.domain.UPVToken;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.util.Collection;
import java.util.LinkedHashMap;

/**
 * Created by lum on 2015/5/6.
 */
public class FormAuthenticationFilterEx extends FormAuthenticationFilter {
    public static final String DEFAULT_VALIDATECODE_PARAM = "validateCode";

    protected String getValidateCode(ServletRequest request) {
        return WebUtils.getCleanParam(request, DEFAULT_VALIDATECODE_PARAM);
    }

    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {

        String username = getUsername(request);
        String password = getPassword(request);
        String validateCode = getValidateCode(request);
        boolean rememberMe = isRememberMe(request);
        String host = getHost(request);

        return new UPVToken(username, password, rememberMe, host, validateCode);
    }

    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request,
                                     ServletResponse response) {

        request.setAttribute("message", e.getMessage());

        return super.onLoginFailure(token, e, request, response);
    }

    /**
     * 登录成功后重置session，防止 session fixation攻击
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        AuthenticationToken token = createToken(request, response);
        if (token == null) {
            String msg = "createToken method implementation returned null. A valid non-null AuthenticationToken " +
                    "must be created in order to execute a login attempt.";
            throw new IllegalStateException(msg);
        }
        try {
            Subject subject = getSubject(request, response);
            //获取session数据
            Session session = subject.getSession();
            final LinkedHashMap<Object, Object> attributes = new LinkedHashMap<Object, Object>();
            final Collection<Object> keys = session.getAttributeKeys();
            for (Object key : keys) {
                final Object value = session.getAttribute(key);
                if (value != null) {
                    attributes.put(key, value);
                }
            }
            session.stop();
            subject.login(token);
            // 登录成功后复制session数据
            session = subject.getSession();
            for (final Object key : attributes.keySet()) {
                session.setAttribute(key, attributes.get(key));
            }
            return onLoginSuccess(token, subject, request, response);
        } catch (AuthenticationException e) {
            return onLoginFailure(token, e, request, response);
        }
    }
}
