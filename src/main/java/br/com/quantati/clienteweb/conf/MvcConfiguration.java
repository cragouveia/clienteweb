package br.com.quantati.clienteweb.conf;

import com.google.common.cache.CacheBuilder;
import org.springframework.cache.CacheManager;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by carlos on 05/06/2017.
 */
@Configuration
public class MvcConfiguration {

    @Bean
    public CacheManager cacheManager() {
        CacheBuilder<Object, Object> builder = CacheBuilder.newBuilder()
                .maximumSize(100)
                .expireAfterAccess(5, TimeUnit.MINUTES);
        GuavaCacheManager manager = new GuavaCacheManager();
        manager.setCacheBuilder(builder);

        return manager;
    }

    @Bean
    public ByteArrayHttpMessageConverter byteArrayHttpMessageConverter() {
        ByteArrayHttpMessageConverter arrayHttpMessageConverter = new ByteArrayHttpMessageConverter();
        arrayHttpMessageConverter.setSupportedMediaTypes(getSupportedMediaTypes());
        return arrayHttpMessageConverter;
    }

    private List<MediaType> getSupportedMediaTypes() {
        List<MediaType> list = new ArrayList<>();
        list.add(MediaType.IMAGE_JPEG);
        list.add(MediaType.IMAGE_PNG);
        list.add(MediaType.APPLICATION_OCTET_STREAM);
        return list;
    }


}
