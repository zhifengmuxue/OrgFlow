package top.zfmx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.zfmx.dto.LoginDTO;
import top.zfmx.entity.Account;

/**
 * @author lima
 * @version 0.0.1
 **/
public interface AccountService
        extends IService<Account> {
    Long login(LoginDTO loginDTO);
}
