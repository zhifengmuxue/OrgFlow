package top.zfmx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.zfmx.entity.Account;

/**
 * @author lima
 * @version 0.0.1
 **/
@Mapper
public interface AccountMapper
        extends BaseMapper<Account> {
}
