package org.luapp.core.domain;

import org.apache.shiro.authc.UsernamePasswordToken;

public class UPVToken extends UsernamePasswordToken {

    /**
     *
     */
    private static final long serialVersionUID = 7645392123001138172L;

    private String kaptcha;

    public String getKaptcha() {
        return kaptcha;
    }

    public void setKaptcha(String kaptcha) {
        this.kaptcha = kaptcha;
    }

    public UPVToken(final String username, final char[] password, final boolean rememberMe, String host,
                    final String validateCode) {
        super(username, password, rememberMe, host);
        this.kaptcha = kaptcha;
    }

    public UPVToken(final String username, final String password, final boolean rememberMe, final String host,
                    final String validateCode) {
        super(username, password, rememberMe, host);
        this.kaptcha = kaptcha;
    }
}
