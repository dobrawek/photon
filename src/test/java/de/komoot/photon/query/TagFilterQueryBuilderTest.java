
package de.komoot.photon.query;



import static com.google.common.collect.Maps.newHashMap;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.elasticsearch.common.bytes.BytesReference;
import org.elasticsearch.common.xcontent.ToXContent;
import org.elasticsearch.common.xcontent.json.JsonXContent;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.elasticsearch.index.query.functionscore.ScriptScoreFunctionBuilder;
import org.elasticsearch.script.Script;
import org.elasticsearch.script.ScriptService;
import org.elasticsearch.script.ScriptType;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;

import de.komoot.photon.ReflectionTestUtil;
import de.komoot.photon.utils.QueryToJson;



public class TagFilterQueryBuilderTest
{

    @Test
    public void testConstructor() throws IOException
    {
        TagFilterQueryBuilder photonQueryBuilder = PhotonQueryBuilder.builder("berlin", "en");
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("json_queries/test_base_query.json");
        String expectedJsonString = IOUtils.toString(resourceAsStream);

        String actualJsonString = new QueryToJson().convert(photonQueryBuilder.buildQuery());
        JsonNode actualJson = this.readJson(actualJsonString);
        JsonNode expectedJson = this.readJson(expectedJsonString);
        Assert.assertEquals(expectedJson, actualJson);
    }



    @Test
    public void testFrenchConstructor() throws IOException
    {
        TagFilterQueryBuilder photonQueryBuilder = PhotonQueryBuilder.builder("berlin", "fr");
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("json_queries/test_base_query_fr.json");
        String expectedJsonString = IOUtils.toString(resourceAsStream);

        String actualJsonString = new QueryToJson().convert(photonQueryBuilder.buildQuery());
        JsonNode actualJson = this.readJson(actualJsonString);
        JsonNode expectedJson = this.readJson(expectedJsonString);
        Assert.assertEquals(expectedJson, actualJson);
    }



//    @Test
//    public void testWithLocation() throws IOException
//    {
//        TagFilterQueryBuilder berlinQuery = PhotonQueryBuilder.builder("berlin", "en");
//        Map<String, Object> params = newHashMap();
//        params.put("lon", 10);
//        params.put("lat", 80);
//        
//        ScriptScoreFunctionBuilder expectedLocationBiasSubQueryBuilder =
//                ScoreFunctionBuilders.scriptFunction(new Script(ScriptType.FILE, "groovy", "location-biased-score", Collections.emptyMap(), params));
//        
//        
//        FunctionScoreQueryBuilder mockFunctionScoreQueryBuilder = Mockito.mock(FunctionScoreQueryBuilder.class);
//        ReflectionTestUtil.setFieldValue(berlinQuery, "queryBuilder", mockFunctionScoreQueryBuilder);
//        ArgumentCaptor<ScriptScoreFunctionBuilder> locationBiasSubQueryArgumentCaptor = ArgumentCaptor.forClass(ScriptScoreFunctionBuilder.class);
//        berlinQuery.withLocationBias(new GeometryFactory().createPoint(new Coordinate(10, 80)));
//        Mockito.verify(mockFunctionScoreQueryBuilder, Mockito.times(1)).add(locationBiasSubQueryArgumentCaptor.capture());
//        ScriptScoreFunctionBuilder actualLocationBiasSubQueryBuilder = locationBiasSubQueryArgumentCaptor.getValue();
//        Assert.assertEquals(this.readJson(expectedLocationBiasSubQueryBuilder), this.readJson(actualLocationBiasSubQueryBuilder));
//    }



    private JsonNode readJson(ToXContent queryBuilder) throws IOException
    {
        return this.readJson(queryBuilder.toXContent(JsonXContent.contentBuilder(), new ToXContent.MapParams(null)).bytes());
    }



    private JsonNode readJson(BytesReference jsonStringBytes) throws IOException
    {
        return this.readJson(jsonStringBytes.utf8ToString());
    }



    private JsonNode readJson(String jsonString) throws IOException
    {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationConfig.Feature.INDENT_OUTPUT, true);
        return objectMapper.readTree(jsonString);
    }
}
