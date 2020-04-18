package simu.tech.service.impl;

import com.alibaba.fastjson.JSON;
import org.codehaus.plexus.util.StringUtils;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.*;
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
import simu.tech.config.ESConfigProperties;
import simu.tech.dao.ESManagerMapper;
import simu.tech.entity.Result;
import simu.tech.entity.StatusCode;
import simu.tech.pojo.TestBean;
import simu.tech.service.ESManagerService;
import simu.tech.util.ConvertUtil;
import simu.tech.util.TikaUtil;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;

@Service
@Transactional
public class ESManagerServiceImpl implements ESManagerService {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;
    @Autowired
    private ESConfigProperties esConfigProperties;


    @Autowired
    private ESManagerMapper esManagerMapper;

    //创建索引库和映射关系
    @Override
    public void createIndexAndMapping() {

        //创建索引
        elasticsearchTemplate.createIndex(TestBean.class);
        //创建映射关系
        elasticsearchTemplate.putMapping(TestBean.class);

    }

    //导入全部数据到索引库
    @Override
    public void importAll() throws Exception {
        //模拟数据
//        List<TestBean> testBeans = getList();

//        if (testBeans==null&&testBeans.size()<=0){
//            throw new RuntimeException("当前没有数据被查到,无法导入索引库");
//        }
        // 设置文件路径
        List<String> files = new ArrayList<>();
        files.add("新建1.pdf");
        files.add("新建2.docx");
        files.add("共享.xlsx");
        // 获取Solr客户端
//        HttpSolrClient solr = SolrClient.getClient(solrConfigProperties.getServer());
//        HttpSolrClient solr = new HttpSolrClient(solrConfigProperties.getServer()+solrConfigProperties.getCore())

        String prefix = "";
        for (String fi : files) {
            System.out.println(fi);
            // 取后缀名
            prefix = ConvertUtil.getFileSufix(fi);
            if (prefix.equalsIgnoreCase("txt") ||
                    prefix.equalsIgnoreCase("docx") ||
                    prefix.equalsIgnoreCase("doc") ||
                    prefix.equalsIgnoreCase("pdf") ||
                    prefix.equalsIgnoreCase("xlsx")) {

                String[] fileInfo = fi.split("\\.");
                String content = "";
                // 获取文件流，取出文件内容
                InputStream inputStream = new FileInputStream(esConfigProperties.getDir() + fi);
                if (prefix.equalsIgnoreCase("txt")) {
                    content = TikaUtil.txt2String(inputStream);
                    inputStream.close();
                } else if (prefix.equalsIgnoreCase("docx") ||
                        prefix.equalsIgnoreCase("doc") ||
                        prefix.equalsIgnoreCase("pdf") ||
                        prefix.equalsIgnoreCase("xlsx")) {
                    content = TikaUtil.doc2String(inputStream);
                    inputStream.close();
                } else {
                    inputStream.close();
                }
                // 添加索引
                String formatDate = ConvertUtil.formatDate();
                TestBean testBean = new TestBean();
                testBean.setId(UUID.randomUUID().toString().toUpperCase().replace("-", ""));
                testBean.setContent(content);
                testBean.setUploadtime(formatDate);
                testBean.setName(fileInfo[0]);
                testBean.setAddress(prefix);
                //导入索引库
                esManagerMapper.save(testBean);
            }
        }
    }
    //根据id删除es索引库中相关的数据
    @Override
    public void delDataBySpuId(String id) {
        esManagerMapper.deleteById(id);
    }

    //根据单参数分页查询
    @Override
    public Result selectByParam(String str){
        int currentPage = 1;
        int size = 10;
//        String str = "天气";
        Integer offset = (currentPage-1)*size;
        System.out.println(offset+"---"+size);
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(queryStringQuery(str)).withPageable(new PageRequest(offset,size)).build();
        AggregatedPage<TestBean> testBeans = elasticsearchTemplate.queryForPage(searchQuery, TestBean.class);
        List<TestBean> content = testBeans.getContent();
        return new Result(true, StatusCode.OK,"查询成功",content);
    }

    @Override
    public Result selectByParams(String str1, String str2){
        QueryBuilder queryBuilder = QueryBuilders.boolQuery().must(QueryBuilders.termQuery("name", str2));
//        FuzzyQueryBuilder content = QueryBuilders.fuzzyQuery("content", str2);

        return new Result(true,StatusCode.OK,"查询成功",queryBuilder);
//        //组合条件查询
//        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
//        //文件名
////        if (!StringUtils.isEmpty(str1)) {
////            //matchQuery：单个匹配
////            //must：必须匹配这些条件才能被包含进来。
////            boolQuery.must(QueryBuilders.matchQuery("name", str1).operator(Operator.AND));
////        }
//        //文件内容
//        if (!StringUtils.isEmpty(str2)) {
//            //filter：必须 匹配
//            //termQuery：条件组合查询
//            boolQuery.must(QueryBuilders.termQuery("content", str2));
//        }
//
//        //原生搜索实现类
//        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
//        nativeSearchQueryBuilder.withQuery(boolQuery);
//
//        //执行查询,返回结果对象
//        AggregatedPage<TestBean> aggregatedPage = elasticsearchTemplate.queryForPage(nativeSearchQueryBuilder.build(), TestBean.class, new SearchResultMapper() {
//            @Override
//            public <T> AggregatedPage<T> mapResults(SearchResponse searchResponse, Class<T> aClass, Pageable pageable) {
//                List<T> list = new ArrayList<>();
//                SearchHits hits = searchResponse.getHits();
//                if (null != hits) {
//                    for (SearchHit hit : hits) {
//                        TestBean testBean = JSON.parseObject(hit.getSourceAsString(), TestBean.class);
//                        list.add((T) testBean);
//                    }
//                }
//                return new AggregatedPageImpl<T>(list, pageable, hits.getTotalHits(), searchResponse.getAggregations());
//            }
//        });
//
//        //总条数
////        resultMap.put("total", aggregatedPage.getTotalElements());
//        //总页数
////        resultMap.put("totalPages", aggregatedPage.getTotalPages());
//        //查询结果集合
////        resultMap.put("rows", aggregatedPage.getContent());
////        //获取品牌聚合结果
////        StringTerms brandTerms = (StringTerms) aggregatedPage.getAggregation(skuBrand);
////        List<String> brandList = brandTerms.getBuckets().stream().map(bucket -> bucket.getKeyAsString()).collect(Collectors.toList());
////        resultMap.put("brandList", brandList);
////        //获取规格聚合结果
////        StringTerms specTerms = (StringTerms) aggregatedPage.getAggregation(skuSpec);
////        List<String> specList = specTerms.getBuckets().stream().map(bucket -> bucket.getKeyAsString()).collect(Collectors.toList());
////        resultMap.put("specList", specList(specList));
////
////        //16. 返回当前页
////        resultMap.put("pageNum", pageNum);
//
//        //返回数据
//        return new Result(true,StatusCode.OK,"查询成功",aggregatedPage);
    }


    //根据spuId导入数据到索引库
    @Override
    public void importDataToESBySpuId(String spuId) {
        List<TestBean> skuList = new ArrayList<TestBean>();
        //模拟数据
        if (skuList == null && skuList.size() < 0) {
            throw new RuntimeException("当前没有数据被查到,无法导入索引库");
        }
        //skuList转换为json
        String skuListJson = JSON.toJSONString(skuList);
        //将json转换成映射对象skuinfo
        List<TestBean> skuInfoList = JSON.parseArray(skuListJson, TestBean.class);

        //导入索引库
        esManagerMapper.saveAll(skuInfoList);

    }



    //模拟数据
    public List<TestBean> getList() {
        List<TestBean> list = new ArrayList<TestBean>();
        TestBean testBean = null;
        for (int i = 0; i < 3; i++) {
            testBean = new TestBean();
            String str = Integer.toString(i);
//            testBean.setId((long) i);
            testBean.setName("zhangsan" + str);
            testBean.setAddress("xian" + str);
            list.add(testBean);
        }
        return list;
    }
}