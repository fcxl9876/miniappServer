package xin.fcxl9876.common.entity.basEntity;

import xin.fcxl9876.common.entity.primaryKey.ProjectRatingKey;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author shaolay
 * @date 2023/2/20 13:22
 */
@Entity
@Data
@Table(name = "t_bas_project_rating", catalog = "db_water_subject")
@IdClass(ProjectRatingKey.class)
public class ProjectRating implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "project_code")
    private String projectCode;

    @Id
    @Column(name = "water_level_num")
    private Integer waterLevelNum;

    @Column(name = "water_level")
    private String waterLevel;

    @Id
    @Column(name = "upper_limit")
    private String upperLimit;

    @Column(name = "upper_equal")
    private Integer upperEqual;

    @Id
    @Column(name = "lower_limit")
    private String lowerLimit;

    @Column(name = "lower_equal")
    private Integer lowerEqual;

    @Column(name = "delete_status")
    private Integer deleteStatus;
}
