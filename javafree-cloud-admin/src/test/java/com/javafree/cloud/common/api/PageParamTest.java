package com.javafree.cloud.common.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * @version V1.0
 * @Description:
 * @Author gwz  gwz126@126.com
 * @Date 2021/11/30 10:31
 */

class PageParamTest {
    public static void main(String[] args) {

        PageParam pageParam=new PageParam();
        List<SortParam> sortParamList=new ArrayList<>();
        SortParam sp1=new SortParam();
        sp1.setProperty("name");
        sp1.setDirection("ASC");

        SortParam sp2=new SortParam();
        sp2.setProperty("age");
        sp2.setDirection("DESC");

        sortParamList.add(sp1);
        sortParamList.add(sp2);

        pageParam.setSorts(sortParamList);

        ObjectMapper mapper = new ObjectMapper();

        String jsonInString = null;
        try {

            String jsonString = mapper.writeValueAsString(pageParam);
            System.out.println(jsonString);

            //格式化后的json 串
            jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(pageParam);
            System.out.println(jsonInString);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }


    }
}