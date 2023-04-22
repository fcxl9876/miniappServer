package xin.fcxl9876.miniappserver.conf;

import com.baomidou.mybatisplus.entity.GlobalConfiguration;
import xin.fcxl9876.miniappserver.datasource.DBProperties;
import xin.fcxl9876.miniappserver.datasource.DynamicDataSource;
import xin.fcxl9876.miniappserver.fw.BaseMapper;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.MybatisConfiguration;
import com.baomidou.mybatisplus.spring.MybatisSqlSessionFactoryBean;
import xin.fcxl9876.miniappserver.handler.MyMetaObjectHandler;
import org.apache.ibatis.logging.slf4j.Slf4jImpl;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import tk.mybatis.mapper.entity.Config;
import tk.mybatis.mapper.mapperhelper.MapperHelper;
import tk.mybatis.spring.annotation.MapperScan;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 定时任务数据源配置
 *
 * @author huhailang
 */
@Configuration
@MapperScan(value = "tk.mybatis.mapper.annotation",
        markerInterface = BaseMapper.class,
        mapperHelperRef = "systemMapperHelper",
        sqlSessionTemplateRef = "systemSqlSessionTemplate"
)
public class DataSourceConfig {

    @Autowired
    private DBProperties properties;

    @Bean(name = "dynamicSource")
    public DynamicDataSource dataSource() {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        System.out.println("ZZZ" + JSONUtil.toJsonStr(properties));
        // 添加数据源
        Map<Object, Object> dsMap = new HashMap<>(5);
        dsMap.put("master", properties.getMaster());
//        dsMap.put("slave", properties.getSlave());
        dynamicDataSource.setTargetDataSources(dsMap);
        dynamicDataSource.setDefaultTargetDataSource(properties.getMaster());
        return dynamicDataSource;
    }

    @Bean(name = "systemTransactionManager")
    public DataSourceTransactionManager setTransactionManager(@Qualifier("dynamicSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "systemSqlSessionFactory")
    @Primary
    public SqlSessionFactory setSqlSessionFactory(@Qualifier("dynamicSource") DataSource dataSource) throws Exception {
//        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();//mybatis使用
        MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();//mybatis-plush使用
        GlobalConfiguration globalConfig = new GlobalConfiguration();
        globalConfig.setMetaObjectHandler(new MyMetaObjectHandler());
        bean.setGlobalConfig(globalConfig);
        bean.setDataSource(dataSource);
        //   bean.setTypeAliasesPackage("cn.comtom.quartz.model.dbo");
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/*.xml"));
//        org.apache.ibatis.session.Configuration conf = new org.apache.ibatis.session.Configuration();//mybatis使用
        MybatisConfiguration conf = new MybatisConfiguration();//mybatis-plush使用
        conf.setLogImpl(Slf4jImpl.class);
        conf.setJdbcTypeForNull(JdbcType.NULL);
        conf.setMapUnderscoreToCamelCase(true);
        conf.setCacheEnabled(false);
        bean.setConfiguration(conf);
        return bean.getObject();
    }

    @Bean(name = "systemSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate setSqlSessionTemplate(@Qualifier("systemSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean
    public MapperHelper systemMapperHelper() {
        Config config = new Config();
        List<Class> mappers = new ArrayList<>();
        config.setMappers(mappers);
        MapperHelper mapperHelper = new MapperHelper();
        mapperHelper.setConfig(config);
        return mapperHelper;
    }

}