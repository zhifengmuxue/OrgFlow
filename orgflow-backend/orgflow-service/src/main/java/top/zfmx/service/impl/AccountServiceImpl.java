package top.zfmx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import top.zfmx.dto.LoginDTO;
import top.zfmx.entity.Account;
import top.zfmx.exception.AuthException;
import top.zfmx.mapper.AccountMapper;
import top.zfmx.service.AccountService;

/**
 * @author lima
 * @version 0.0.1
 **/
@Service
public class AccountServiceImpl
        extends ServiceImpl<AccountMapper, Account>
        implements AccountService {

    @Override
    public Long login(LoginDTO loginDTO) {
        String password = loginDTO.getPassword();
        String username = loginDTO.getUsername();

        // 二次校验
        if (StringUtils.isAnyBlank(username, password)) {
            throw new AuthException("用户名和密码不能为空");
        }

        // 查询用户
        Account account = lambdaQuery()
                .eq(Account::getUsername, username)
                .oneOpt()
                .orElseThrow(() -> new AuthException("用户名或密码错误"));

        // TODO 加密
        if (!password.equals(account.getPassword())) {
            throw new AuthException("用户名或密码错误");
        }

        // 检查账户状态
        if (account.getStatus() != 1) {
            throw new AuthException("账户已被禁用，请联系管理员");
        }
        return account.getAccountId();
    }
}
