package xin.fcxl9876.common.util;

import xin.fcxl9876.common.entity.basEntity.Project;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 因子信息解析工具-单例模式-启动从数据库加载
 * 修改数据库后需重新加载
 * @author shaolay
 * @date 2023/2/20 10:13
 */
public class ProjectUtil {

    private static ProjectUtil instance;

    private Map<String, Project> map;

    public static ProjectUtil getInstance() {
        if(instance == null) {
            synchronized (ProjectUtil.class) {
                if(instance ==null) {
                    instance = new ProjectUtil();
                }
            }
        }
        return instance;
    }

    /**
     * 根据因子编码获取因子信息
     * @param projectCode 因子编码
     * @return
     */
    public Project getProjectByCode(String projectCode){
        Project project = map.get(projectCode);

        if (project == null){
            project = new Project().setProjectCode(projectCode)
                    .setProjectUnit("mg/L")
                    .setProjectName(projectCode + "(系统未收录)");
        }

        return project;
    }

    public void setMap(List<Project> projectList){
        this.map = projectList.stream().collect(Collectors.toMap(Project::getProjectCode, l -> l));
    }

}
