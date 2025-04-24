package top.zfmx.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 账号
 *
 * @author lima
 * @version 0.0.1
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    @TableId(type = IdType.AUTO)
    private Long accountId;
    private Long employeeId;
    private String username;
    private String password;
    private Integer status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long createdBy;
    private Long updatedBy;
}
