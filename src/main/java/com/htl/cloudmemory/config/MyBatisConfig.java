package com.htl.cloudmemory.config;

import java.util.Properties;
import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import com.alibaba.druid.pool.DruidDataSourceFactory;

/**
 * @Description: Spring Boot集成Mybatis的基本入口
 * @author hetianlong
 * @since 2018-12-12
 * 
 */
@Configuration
@MapperScan("com.htl.cloudmemory.dao")
public class MyBatisConfig {

	@Autowired
	private Environment env;

	/**
	 * 创建数据源
	 * 
	 * @return
	 */
	@Bean
	public DataSource getDataSource() {
		Properties props = new Properties();
		props.put("driverClass", env.getProperty("spring.datasource.driver-class-name"));
		props.put("url", env.getProperty("spring.datasource.url"));
		props.put("username", env.getProperty("spring.datasource.username"));
		props.put("password", env.getProperty("spring.datasource.password"));
		try {
			return DruidDataSourceFactory.createDataSource(props);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据数据源创建SqlSessionFactory
	 * 
	 * @param ds
	 * @return
	 * @throws Exception
	 */
	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource ds) throws Exception {
		SqlSessionFactoryBean sfb = new SqlSessionFactoryBean();
		sfb.setDataSource(ds);
		// 下边两句仅仅用于*.xml文件，如果整个持久层操作不需要使用到xml文件的话（只用注解就可以搞定），则不加
		sfb.setTypeAliasesPackage(env.getProperty("mybatis.typeAliasesPackage"));
		sfb.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(env.getProperty("mybatis.mapperLocations")));
		return sfb.getObject();
	}
}
