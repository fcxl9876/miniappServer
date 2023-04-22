//package xin.fcxl9876.miniappserver.conf;
//
//import java.sql.SQLException;
//import javax.sql.DataSource;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.boot.web.servlet.ServletRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//import com.alibaba.druid.pool.DruidDataSource;
//import com.alibaba.druid.support.http.StatViewServlet;
//import com.alibaba.druid.support.http.WebStatFilter;
//
//@Configuration
//@EnableTransactionManagement
///**
// * Druid的DataResource配置类
// *
// * @author zzh
// *
// *         凡是被Spring管理的类，实现接口 EnvironmentAware 重写方法 setEnvironment 可以在工程启动时，
// *         获取到系统环境变量和application配置文件中的变量。 还有一种方式是采用注解的方式获取 @value("${变量的key值}")
// *         ：获取application配置文件中的变量。 这里采用第一种要方便些
// *
// */
//public class DruidDataSourceConfig{
//
//	private static final Logger logger = LoggerFactory.getLogger(DruidDataSourceConfig.class);
//
//	@Value("${spring.datasource.db1.url}")
//    private String dbUrl;
//
//    @Value("${spring.datasource.username}")
//    private String username;
//
//    @Value("${spring.datasource.password}")
//    private String password;
//
//    @Value("${spring.datasource.driver-class-name}")
//    private String driverClassName;
//
//    @Value("${spring.datasource.druid.initialSize}")
//    private int initialSize;
//
//    @Value("${spring.datasource.druid.minIdle}")
//    private int minIdle;
//
//    @Value("${spring.datasource.druid.maxActive}")
//    private int maxActive;
//
//    @Value("${spring.datasource.druid.maxWait}")
//    private int maxWait;
//
//    @Value("${spring.datasource.druid.timeBetweenEvictionRunsMillis}")
//    private int timeBetweenEvictionRunsMillis;
//
//    @Value("${spring.datasource.druid.minEvictableIdleTimeMillis}")
//    private int minEvictableIdleTimeMillis;
//
//    @Value("${spring.datasource.druid.validationQuery}")
//    private String validationQuery;
//
//    @Value("${spring.datasource.druid.testWhileIdle}")
//    private boolean testWhileIdle;
//
//    @Value("${spring.datasource.druid.testOnBorrow}")
//    private boolean testOnBorrow;
//
//    @Value("${spring.datasource.druid.testOnReturn}")
//    private boolean testOnReturn;
//
//    @Value("${spring.datasource.druid.filters}")
//    private String filters;
//
//    @Value("${spring.datasource.druid.logSlowSql}")
//    private String logSlowSql;
//
//    /**
//     * druid数据源状态监控
//     */
//    @Bean
//    public ServletRegistrationBean<StatViewServlet> druidServlet() {
//        ServletRegistrationBean<StatViewServlet> reg = new ServletRegistrationBean<StatViewServlet>();
//        reg.setServlet(new StatViewServlet());
//        reg.addUrlMappings("/druid/*");
//        reg.addInitParameter("loginUsername", username);
//        reg.addInitParameter("loginPassword", password);
//        reg.addInitParameter("logSlowSql", logSlowSql);
//        return reg;
//    }
//    /**
//     * druid过滤器
//     */
//    @Bean
//    public FilterRegistrationBean<WebStatFilter> filterRegistrationBean() {
//        FilterRegistrationBean<WebStatFilter> filterRegistrationBean = new FilterRegistrationBean<WebStatFilter>();
//        filterRegistrationBean.setFilter(new WebStatFilter());
//        filterRegistrationBean.addUrlPatterns("/*");
//        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
//        filterRegistrationBean.addInitParameter("profileEnable", "true");
//        return filterRegistrationBean;
//    }
//
//    /**
//     * 连接池
//     */
//    @Bean
//    public DataSource druidDataSource() {
//        DruidDataSource datasource = new DruidDataSource();
//        datasource.setUrl(dbUrl);
//        datasource.setUsername(username);
//        datasource.setPassword(password);
//        datasource.setDriverClassName(driverClassName);
//        datasource.setInitialSize(initialSize);
//        datasource.setMinIdle(minIdle);
//        datasource.setMaxActive(maxActive);
//        datasource.setMaxWait(maxWait);
//        datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
//        datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
//        datasource.setValidationQuery(validationQuery);
//        datasource.setTestWhileIdle(testWhileIdle);
//        datasource.setTestOnBorrow(testOnBorrow);
//        datasource.setTestOnReturn(testOnReturn);
//        try {
//            datasource.setFilters(filters);
//        } catch (SQLException e) {
//        	logger.error("dataSource set filters failure...");
//        }
//        return datasource;
//    }
//}
