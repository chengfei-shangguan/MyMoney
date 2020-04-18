package simu.tech.service.impl;

import com.alibaba.fastjson.JSON;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import simu.tech.dao.ESManagerMapper;
import simu.tech.pojo.TestBean;
import simu.tech.service.SearchService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class SearchServiceImpl implements SearchService {

    @Autowired
    private ElasticsearchTemplate esTemplate;
    @Autowired
    private ESManagerMapper esManagerMapper;

    @Override
    public Iterable<TestBean> serach() {
        Iterable<TestBean> all = esManagerMapper.findAll();
        serachs();
        return all;
    }

    public Map serachs()  {
        HashMap<String, Object> resultMap = new HashMap<>();
        //有条件才查询ES

            //组合条件查询
            BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
            //关键字

            boolQuery.must(QueryBuilders.matchQuery("name", "zhang").operator(Operator.AND));

            //原生搜索实现类
            NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
            nativeSearchQueryBuilder.withQuery(boolQuery);
            //执行查询,返回结果对象
//            AggregatedPage<TestBean> aggregatedPage = esTemplate.queryForPage(nativeSearchQueryBuilder.build(), TestBean.class, new SearchResultMapper() {
            AggregatedPage<TestBean> testBeans = esTemplate.queryForPage(nativeSearchQueryBuilder.build(), TestBean.class);
            return null;
        }

    }
