package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 角色
 *
 * @author lima
 * @version 0.0.1
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    private Long roleId;
    private String roleName;
    private String description;
    private Integer status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long createdBy;
    private Long updatedBy;
}
