package top.zfmx.service.impl;

import cn.dev33.satoken.secure.SaSecureUtil;
import org.junit.jupiter.api.Test;

/**
 * @author lima
 * @version 0.0.1
 **/
public class AccountServiceTest {
    @Test
    public void passwordEncrypt() {
        String s = SaSecureUtil.md5("123456");
        System.out.println(s);
    }
}
