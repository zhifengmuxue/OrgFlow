package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 员工
 *
 * @author lima
 * @version 0.0.1
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    private Long employeeId;
    private Long deptId;
    private String gender;
    private String idNumber;
    private LocalDateTime entryDate;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private Long createBy;
    private Long updateBy;
}
