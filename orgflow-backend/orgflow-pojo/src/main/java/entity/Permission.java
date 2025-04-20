package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 权限
 *
 * @author lima
 * @version 0.0.1
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Permission {
    private Long permissionId;
    private String permissionCode;
    private String permissionName;
    private Integer sortOrder;
    private Integer status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long createdBy;
    private Long updatedBy;
}
