package com.revanwang.ly.essearch;

import com.revan.ly.essearch.ElasticSearchApplication;
import com.revan.ly.essearch.dao.ItemRepository;
import com.revan.ly.essearch.pojo.Item;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = ElasticSearchApplication.class)
@RunWith(SpringRunner.class)
public class IndexTest {
    @Autowired
    private ElasticsearchTemplate template;

    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void createTest() {
        // 创建索引，会根据Item类的@Document注解信息来创建
        boolean index = this.template.createIndex(Item.class);
        // 配置映射，会根据Item类的@Id、@Field等注解来完成映射
        this.template.putMapping(Item.class);
        System.out.println(index);
    }


    @Test
    public void deleteTest() {
        this.template.deleteIndex(Item.class);
    }

    @Test
    public void saveTest() {
        Item item = new Item(1L, "小米手机7", " 手机",
                "小米", 3499.00, "http://image.leyou.com/13123.jpg");
        Item save = this.itemRepository.save(item);
        System.out.println(save);
    }

    @Test
    public void saveListTest() {
        List<Item> list = new ArrayList<>();
//        list.add(new Item(2L, "坚果手机R1", " 手机", "锤子", 3699.00, "http://image.leyou.com/123.jpg"));
        list.add(new Item(4L, "华为METAP30", " 手机", "华为", 7999.00, "http://image.leyou.com/3.jpg"));

        Iterable<Item> items = this.itemRepository.saveAll(list);
        System.out.println(items);
    }

    @Test
    public void findTest() {
        Iterable<Item> items = this.itemRepository.findAll(Sort.by(Sort.Direction.DESC, "price"));
        System.out.println(items);
    }

    @Test
    public void queryTest() {
        //词条查询
        MatchQueryBuilder builder = QueryBuilders.matchQuery("title", "小米");
        Iterable<Item> items = this.itemRepository.search(builder);
        items.forEach(System.out::println);
    }


    @Test
    public void nativeQueryTest() {
        //词条查询
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        //添加基本的分词查询
        queryBuilder.withQuery(QueryBuilders.matchQuery("title", "华为"));
        //执行搜索，获取结果
        Page<Item> search = this.itemRepository.search(queryBuilder.build());
        search.forEach(System.out::println);
    }

    @Test
    public void aggTest() {
        //词条查询
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();

        queryBuilder.addAggregation(AggregationBuilders.terms("brands").field("brand"));

        //执行搜索，获取结果
        AggregatedPage<Item> search = (AggregatedPage<Item>) this.itemRepository.search(queryBuilder.build());
        Aggregations agg = search.getAggregations();

    }
}
