package xin.fcxl9876.miniappserver;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
@EnableSwagger2
//@EnableEurekaClient
@Slf4j
//@EnableFeignClients
public class MiniappServiceApplication {

	public static void main(String[] args) throws UnknownHostException {
		ConfigurableApplicationContext application = SpringApplication.run(MiniappServiceApplication.class, args);
		Environment env = application.getEnvironment();
		String ip = InetAddress.getLocalHost().getHostAddress();
		String port = env.getProperty("server.port");
		String path = env.getProperty("server.servlet.context-path")==null? "":env.getProperty("server.servlet.context-path");
		log.info("\n----------------------------------------------------------\n" +
				"Application service is running! Access URLs:\n" +
				"Swagger文档: \thttp://" + ip + ":" + port + path + "/swagger-ui.html\n" +
				"----------------------------------------------------------");
		System.out.println("服务启动成功！");

	}
}

