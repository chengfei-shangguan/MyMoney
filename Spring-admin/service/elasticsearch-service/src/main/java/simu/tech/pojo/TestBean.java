package simu.tech.pojo;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

/**
 * （1）indexName：对应索引库名称；
 *
 * （2）type：对应在索引库中的类型；
 *
 * （3）shards：分片数
 *
 * （4）replicas：副本数；
 */
@Data
@Document(indexName = "testdoct", type = "testbean")
public class TestBean implements Serializable {
    //FieldType.Keyword存储字符串数据时，不会建立索引；而FieldType.Text在存储字符串数据的时候，会自动建立索引，也会占用部分空间资源。
    /**
     * （1）@Id：作用在成员变量，标记一个字段为id主键；一般id字段或是域不需要存储也不需要分词；
     *
     * （2）type：字段的类型，取值是枚举，FieldType；
     *
     * （3）index：是否索引，布尔值类型，默认是true；
     *
     * （4）store：是否存储，布尔值类型，默认值是false；
     *
     * （5）analyzer：分词器名称
     */
    @Id
//    @Field(index = true, store = true, type = FieldType.Keyword)
    @Field(index = true, store = true, type = FieldType.Keyword, analyzer = "ik_smart")
    private String id;
    @Field(index = true, store = true, type = FieldType.Text, analyzer = "ik_smart")
    private String name;
    @Field(index = true, store = true, type = FieldType.Text, analyzer = "ik_smart")
    private String address;
    @Field(index = true, store = true, type = FieldType.Text, analyzer = "ik_smart")
    private String content;
    @Field(index = true, store = true, type = FieldType.Text, analyzer = "ik_smart")
    private String uploadtime;
}