package com.winston.practice;

import com.winston.practice.entity.Email;
import com.winston.practice.entity.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cglib.core.DebuggingClassWriter;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Arrays;


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
		//System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "cglib");
		ConfigurableApplicationContext run = SpringApplication.run(SpringBootPracticeApplication.class, args);
		EntityConfig bean = run.getBean(EntityConfig.class);
		System.out.println(bean);

		User hehe = bean.hehe();
		System.out.println(hehe.getEmail().hashCode());

		User bean1 = run.getBean(User.class);
		System.out.println(bean1.getEmail().hashCode());

		Email email = run.getBean(Email.class);
		System.out.println(email.hashCode());
		System.out.println(bean1.getEmail()==email);
		String[] beanNamesForType = run.getBeanNamesForType(User.class);
		Arrays.stream(beanNamesForType).forEach(System.out::println);

//		User user = run.getBean(User.class);
//		System.out.println(user);
	}

}
