package com.winston.practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;


/**
 * @Target(ElementType.TYPE)
 * @Retention(RetentionPolicy.RUNTIME)
 * @Documented
 * @Inherited
 * @SpringBootConfiguration
 * @EnableAutoConfiguration
 * @ComponentScan(excludeFilters = { @Filter(type = FilterType.CUSTOM, classes = TypeExcludeFilter.class),
 * @Filter(type = FilterType.CUSTOM, classes = AutoConfigurationExcludeFilter.class) })
 * public @interface SpringBootApplication {
 * <p>
 * SpringBootApplication 注解是一个组合注解，里面包含了 EnableAutoConfiguration 这个注解 表示启动自动配置功能
 * <p>
 * EnableAutoConfiguration 注解里面 导入了 AutoConfigurationImportSelector
 * AutoConfigurationImportSelector 会调用 SpringFactoriesLoader.loadFactoryNames(EnableAutoConfiguration.class,classLoader)；来加载类路径下面的所有META-INF/spring.factories配置文件
 * META-INF/spring.factories 配置文件里面  会配置一个key为org.springframework.boot.autoconfigure.EnableAutoConfiguration的项，他的值 就是spring帮我们实现了的一系列自动配置类 XXXAutoConfiguration.java
 * # Auto Configure
 * org.springframework.boot.autoconfigure.EnableAutoConfiguration=\
 */
@SpringBootApplication
@ServletComponentScan(basePackages = "com.winston.practice.web.servlet")
public class SpringBootPracticeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootPracticeApplication.class, args);
	}

}
