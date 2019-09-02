package es;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.Test;

/**
 * @author 胡说
 * @data 2019-07-30 13:32
 * TODO
 */
public class EsTest {
    @Test
    public void test() {
        try (
                RestHighLevelClient restHighLevelClient = new RestHighLevelClient(
                        RestClient.builder(new HttpHost("localhost", 9200, "http"))
                );
        ) {
            System.out.println(restHighLevelClient.cluster());
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
