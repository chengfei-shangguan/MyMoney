package simu.tech.dao;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import simu.tech.pojo.TestBean;

public interface ESManagerMapper extends ElasticsearchRepository<TestBean,String> {
}
