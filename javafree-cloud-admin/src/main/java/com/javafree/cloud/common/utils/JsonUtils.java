package com.javafree.cloud.common.utils;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.javafree.cloud.common.exception.JavafreeException;
import com.javafree.cloud.common.exception.JavafreeExceptionType;
import org.msgpack.jackson.dataformat.MessagePackFactory;
import org.springframework.util.StringUtils;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 处理JSON的工具类
 * @version V1.0
 * @Description:
 * @Author gwz  gwz126@126.com
 * @Date 2021/11/30 15:18
 */

public class JsonUtils {
    private static ObjectMapper objectMapper = new ObjectMapper();

    //对象序列化
    private static ObjectMapper objectMapperMessagePack = new ObjectMapper(new MessagePackFactory());

    // 日期格式化
    private static final String STANDARD_FORMAT = "yyyy-MM-dd HH:mm:ss";

    static {
        //忽略 在json字符串中存在，但是在java对象中不存在对应属性的情况。防止错误
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //对象值不为空的字段列入
        objectMapper.setSerializationInclusion(Include.NON_NULL);
        //对象的所有字段全部列入
        //objectMapper.setSerializationInclusion(Include.ALWAYS);
        //取消默认转换timestamps形式
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        //忽略空Bean转json的错误
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        //所有的日期格式都统一为以下的样式，即yyyy-MM-dd HH:mm:ss
        objectMapper.setDateFormat(new SimpleDateFormat(STANDARD_FORMAT));

        objectMapperMessagePack.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapperMessagePack.setSerializationInclusion(Include.NON_NULL);
        //所有的日期格式都统一为以下的样式，即yyyy-MM-dd HH:mm:ss
        objectMapperMessagePack.setDateFormat(new SimpleDateFormat(STANDARD_FORMAT));

    }

    private StringWriter writer;

    private JsonUtils() {
    }

    public static String getJsonStringFromObject(Object obj) {
        StringWriter writer = new StringWriter();
        try {
            objectMapper.writeValue(writer, obj);
        } catch (IOException e) {
            e.printStackTrace();
            throw new JavafreeException(JavafreeExceptionType.SERVER_ERROR, "对象转为JSON发生错误!");
        }
        return writer.toString();
    }

    public static <T> T getObjectFromJsonString(Class<T> clazz, String requestJson,
                                                String callingMethod) {
        T t = null;

        try {
            InputStream is = new ByteArrayInputStream(requestJson.getBytes("UTF-8"));
            t = (T) objectMapper.readValue(is, clazz);
        } catch (IOException e) {
            e.printStackTrace();
            throw new JavafreeException(JavafreeExceptionType.SERVER_ERROR, "从JSON转为对象发生错误!");
        }
        return t;
    }

    public static <T> T getObjectFromJsonString(String json, Class<T> className) {
        return getObjectFromJsonString(className, json, "null");
    }

    public static <T> Object getObjectFromJsonString(String json, TypeReference<T> typeRef) {

        try {
            InputStream is = new ByteArrayInputStream(json.getBytes("UTF-8"));
            return objectMapper.readValue(is, typeRef);
        } catch (IOException e) {
            e.printStackTrace();
            throw new JavafreeException(JavafreeExceptionType.SERVER_ERROR, "从JSON转为对象发生错误!");
        }
    }

    public static JsonNode convertObjectToJsonNode(Object object) {
        JsonNode jsonAdObject = objectMapper.valueToTree(object);
        return jsonAdObject;
    }

    public static <T> Object getObjectFromFile(String fileName, Class<T> className) {
        T obj = null;

        try {
            File file = new File(fileName);
            obj = objectMapper.readValue(file, className);
        } catch (IOException e) {
            e.printStackTrace();
            throw new JavafreeException(JavafreeExceptionType.SERVER_ERROR, "从文件转为对象发生错误!");
        }
        return obj;
    }

    public static JsonNode getJsonNodeFromQueryParamsMap(Map<String, String[]> queryParams) {
        if (queryParams == null) {
            return null;
        }
        Map<String, String> queryParamsMap = new HashMap<String, String>();

        for (Map.Entry<String, String[]> entry : queryParams.entrySet()) {

            queryParamsMap.put(entry.getKey(), entry.getValue()[0]);

        }
        JsonNode jsonObject = objectMapper.valueToTree(queryParamsMap);

        return jsonObject;

    }

    public static JsonNode getFirstParamNodeFromQueryParams(Map<String, String[]> queryParams) {
        if (queryParams == null) {
            return null;
        }
        ObjectNode node = objectMapper.createObjectNode();
        for (String key : queryParams.keySet()) {
            node.put(key, queryParams.get(key)[0]);
        }
        return node;
    }

    public static <T> T getObjectFromJsonNode(JsonNode node, Class<T> className)
            throws JsonProcessingException {
        return objectMapper.treeToValue(node, className);
    }

    public static JsonNode getJsonNodeFromObject(Object object) {
        return objectMapper.valueToTree(object);
    }

    public static JsonNode getJsonNodeFromJsonString(String jsonString) {
        JsonNode retval = null;
        if (StringUtils.hasText(jsonString)) {
            try {
                retval = objectMapper.readValue(jsonString, JsonNode.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                throw new JavafreeException(JavafreeExceptionType.SERVER_ERROR, "从JSON字符串转为JsonNode发生错误!");
            }
        }
        return retval;
    }

    public static ArrayNode createArrayNode() {
        return objectMapper.createArrayNode();
    }

    public static ObjectNode createObjectNode() {
        return objectMapper.createObjectNode();
    }

    public static byte[] messagePackSerialize(Object obj) {
        try {
            return objectMapperMessagePack.writeValueAsBytes(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new JavafreeException(JavafreeExceptionType.SERVER_ERROR, "对象序列化时发生错误!");
        }
    }

    public static <T> Object messagePackDeserialize(byte[] b, Class<T> className) {
        try {
            return objectMapperMessagePack.readValue(b, className);
        } catch (IOException e) {
            e.printStackTrace();
            throw new JavafreeException(JavafreeExceptionType.SERVER_ERROR, "对象序列化时发生错误!");
        }
    }

    public static List<String> getSortedArrayNode(ArrayNode arrayNode) {
        List<String> list = new ArrayList<String>();
        Iterator<JsonNode> it = arrayNode.iterator();
        while (it.hasNext()) {
            list.add(it.next().asText());
        }
        Collections.sort(list);
        return list;
    }
}
