package jun.vbytkov.decorator;

import org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration;
import org.springframework.cache.Cache;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;

@Configuration
public class DecoratorBeans {

    @Bean
    public DataSource getDatasource() {
        return new SimpleDriverDataSource();
    }

    @Bean
    public Cache getCache() {
        return new ConcurrentMapCache("");
    }

    @Bean
    public FindTaskByIdSpi findTaskByIdSpi(DataSource dataSource, Cache cache) {
        return new SpringCachingFindTaskByIdDecorator(
                new FindTaskByIdSpiMappingSqlQuery(dataSource),
                cache);
    }

}
