package top.zfmx.controller.auth;

import cn.dev33.satoken.stp.StpUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.zfmx.dto.LoginDTO;
import top.zfmx.service.AccountService;
import top.zfmx.response.Result;

/**
 * @author lima
 * @version 0.0.1
 **/
@Slf4j
@Tag(name = "鉴权接口组")
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Resource
    private AccountService accountService;

    @PostMapping("/login")
    @Operation(description = "登录接口")
    public Result<String> doLogin(@Validated @RequestBody LoginDTO loginDTO) {
        log.info("用户登录：{}",loginDTO.getUsername());
        Long userId = accountService.login(loginDTO);
        StpUtil.login(userId);
        String tokenValue = StpUtil.getTokenValue();
        return Result.ok("登录成功",tokenValue);
    }

    @PostMapping("/logout")
    @Operation(description = "退出接口")
    public Result<String> doLogout(){
        if(StpUtil.isLogin()){
            StpUtil.logout();
            log.info("用户[{}]已注销", StpUtil.getLoginId());
        }
        return Result.ok("注销成功");
    }


}
