package br.com.quantati.clienteweb.conf;

import org.hibernate.boot.model.TypeContributions;
import org.hibernate.dialect.PostgreSQL94Dialect;
import org.hibernate.service.ServiceRegistry;

/**
 * Created by carlos on 06/06/2017.
 */
public class CustomPostgreSQLDialect extends PostgreSQL94Dialect {

    @Override
    public void contributeTypes(TypeContributions typeContributions, ServiceRegistry serviceRegistry) {
        super.contributeTypes(typeContributions, serviceRegistry);
        typeContributions.contributeType(new CustomByteType());
    }
}
