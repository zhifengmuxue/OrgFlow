package top.zfmx.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lima
 * @version 0.0.1
 **/
@RestController
public class DemoController {

    @GetMapping("/login")
    public SaResult login(@RequestParam("name") String username,@RequestParam("pwd") String password){
        if ("zfmx".equals(username) && "123456".equals(password)){
            StpUtil.login(1001);
            SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
            return SaResult.ok(tokenInfo.toString());
        }
        return SaResult.error("<UNK>");
    }

    @GetMapping("/isLogin")
    public SaResult isLogin(){
        return SaResult.ok("是否登录：" + StpUtil.isLogin());
    }
}
