package com.hsc.authenication.config;

import java.util.Collection;
import java.util.List;

import javax.sql.DataSource;

import org.hibernate.boot.model.naming.ImplicitNamingStrategy;
import org.hibernate.boot.model.naming.PhysicalNamingStrategy;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.autoconfigure.transaction.TransactionManagerCustomizers;
import org.springframework.boot.jdbc.SchemaManagementProvider;
import org.springframework.boot.jdbc.metadata.DataSourcePoolMetadataProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.jta.JtaTransactionManager;

@Configuration
public class HibernateConfiguration extends HibernateJpaAutoConfiguration {

	
	public HibernateConfiguration(DataSource dataSource, JpaProperties jpaProperties,
			ObjectProvider<JtaTransactionManager> jtaTransactionManager,
			ObjectProvider<TransactionManagerCustomizers> transactionManagerCustomizers,
			ObjectProvider<Collection<DataSourcePoolMetadataProvider>> metadataProviders,
			ObjectProvider<List<SchemaManagementProvider>> providers,
			ObjectProvider<PhysicalNamingStrategy> physicalNamingStrategy,
			ObjectProvider<ImplicitNamingStrategy> implicitNamingStrategy,
			ObjectProvider<List<HibernatePropertiesCustomizer>> hibernatePropertiesCustomizers) {		
		super();
	}


}
