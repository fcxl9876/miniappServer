package xin.fcxl9876.miniappserver.datasource;

import com.zaxxer.hikari.HikariDataSource;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spring.datasource.hikari")
@Data
public class DBProperties {
    private HikariDataSource master;
    private HikariDataSource slave;
}