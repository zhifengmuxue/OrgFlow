package top.zfmx.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lima
 * @version 0.0.1
 **/
@RestController
@Tag(name = "测试端口")
public class DemoController {

    @GetMapping("/login")
    @Operation(description = "登录接口")
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
