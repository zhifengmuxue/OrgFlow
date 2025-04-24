package top.zfmx.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 部门
 *
 * @author lima
 * @version 0.0.1
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Department {
    @TableId(type = IdType.AUTO)
    private Long deptId;
    private String deptName;
    private String deptDescription;
    private Long parentId;
    private Long leaderId;
    private Integer status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long createdBy;
    private Long updatedBy;
}
