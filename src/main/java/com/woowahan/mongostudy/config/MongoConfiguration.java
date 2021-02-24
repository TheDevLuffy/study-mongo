package com.woowahan.mongostudy.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;

import javax.annotation.PostConstruct;

/**
 * <p>해당 설정이 없으면 아래와 같이 저장된다.</p>
 * <pre><code>
 * {
 *     "_id": {
 *         "$oid": "60363b542d551b7194826cb0"
 *     },
 *     "slug": "product1",
 *     "sku": "10000",
 *     "_class": "com.woowahan.mongostudy.document.product.Product"
 * }
 * </code></pre>
 *
 * <p>해당 설정이 있으면, _class 프로퍼티를 제거할 수 있다.</p>
 * <pre><code>
 * {
 *     "_id": {
 *         "$oid": "60363da863c98b0dc4f137a7"
 *     },
 *     "slug": "product1",
 *     "sku": "10000"
 * }
 * </code></pre>
 *
 * <p>
 *     DefaultMongoTypeMapper 를 보면 default 생성자에 typeKey 가 기본으로 "_class"가 들어있는데,
 *     해당 DefaultMongoTypeMapper 의 typeKey 를 null 로 지정하여 MappingMongoConverter 의
 *     typeMapper 필드를 다시 set 함으로써 _class 필드를 생성하지 않도록 할 수 있다.
 * </p>
 * @see org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper
 */
@Configuration
public class MongoConfiguration {

    private final MappingMongoConverter converter;

    public MongoConfiguration(MappingMongoConverter converter) {
        this.converter = converter;
    }

    @PostConstruct
    public void removeClassProperties() {
        converter.setTypeMapper(new DefaultMongoTypeMapper(null));
    }
}
