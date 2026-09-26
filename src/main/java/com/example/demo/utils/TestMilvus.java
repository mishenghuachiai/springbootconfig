package com.example.demo.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.milvus.v2.client.ConnectConfig;
import io.milvus.v2.client.MilvusClientV2;
import io.milvus.v2.common.DataType;
import io.milvus.v2.common.IndexParam;
import io.milvus.v2.service.collection.request.AddFieldReq;
import io.milvus.v2.service.collection.request.CreateCollectionReq;
import io.milvus.v2.service.index.request.CreateIndexReq;
import io.milvus.v2.service.vector.request.InsertReq;
import io.milvus.v2.service.vector.request.data.FloatVec;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class TestMilvus {
    private static final String dbName = "devlop";
    private static final String collectionName = "computer";
    private static final Integer DIM = 4;
    public static void main(String[] args) throws InterruptedException {
        ConnectConfig config = ConnectConfig.builder()
                .uri("http://localhost:19530")
                .username("root")
                .password("Milvus")
                .build();
        MilvusClientV2 client = new MilvusClientV2(config);
        System.out.println("milvus数据库是否启动正常: "+client.checkHealth().getIsHealthy());
        /**
         * 3.写入字段数据
         * 4。查询数据
         * 5.修改数据
         * 6.清除数据，清除collections，清除schema
         */
        //0.创建数据库dev库
//        CreateDatabaseReq createDatabaseReq = CreateDatabaseReq.builder().databaseName(dbName).build();
//        client.createDatabase(createDatabaseReq);
        client.useDatabase(dbName);
        //1.创建schema
        CreateCollectionReq.CollectionSchema collectionSchema = client.CreateSchema();
                collectionSchema.addField(
                AddFieldReq.builder()
                        .fieldName("id")
                        .dataType(DataType.Int64)
                        .isPrimaryKey(true)
                        .autoID(true)
                        .build()
        );

        collectionSchema.addField(
                AddFieldReq.builder()
                        .fieldName("price")
                        .dataType(DataType.Float)
                        .build()
        );

        collectionSchema.addField(
                AddFieldReq.builder()
                        .fieldName("screen_size")
                        .dataType(DataType.Float)
                        .build()
        );

        collectionSchema.addField(
                AddFieldReq.builder()
                        .fieldName("brand")
                        .dataType(DataType.VarChar)
                        .maxLength(100)
                        .build()
        );

        collectionSchema.addField(
                AddFieldReq.builder()
                        .fieldName("vector")
                        .dataType(DataType.FloatVector)
                        .dimension(DIM)
                        .build()
        );

        System.out.println(collectionSchema.getFieldSchemaList().get(1));
        System.out.println(collectionSchema.getFunctionList());
        System.out.println(collectionSchema.getStructFields());
        System.out.println(collectionSchema.isEnableDynamicField());

        //2.创建collections
        CreateCollectionReq build = CreateCollectionReq.builder()
                .collectionName(collectionName)
                .collectionSchema(collectionSchema)
                .numShards(2)
                .databaseName(dbName)
                .build();
        client.createCollection(build);

        //创建索引
        IndexParam indexParam = IndexParam.builder()
                .fieldName("vector")
                .indexType(IndexParam.IndexType.IVF_FLAT)
                .metricType(IndexParam.MetricType.L2)
                .extraParams(Map.of("nlist", "128"))
                .build();

        client.createIndex(CreateIndexReq.builder()
                .collectionName(collectionName)
                .indexParams(Collections.singletonList(indexParam))
                .build());

        //插入数据
        // 5. 插入数据
        List<Float> vector1 = Arrays.asList(5000f, 15.6f, 1f, 0f); // Dell
        List<Float> vector2 = Arrays.asList(7000f, 16.0f, 0f, 1f); // HP

        Map<String, Object> obj2 =  Map.of(
                "price", 5000f,
                "screen_size", 15.6f,
                "brand", "Dell",
                "vector", new FloatVec(vector1)
        );
        JsonObject jsonObject1 = new JsonObject();
        JsonObject jsonObject2 = new JsonObject();
        Gson gson = new Gson();//参考文档: https://www.runoob.com/java/java-gson-lib.html
        jsonObject1.addProperty("price",7000f);
        jsonObject1.addProperty("screen_size",16.0f);
        jsonObject1.addProperty("brand","HP");
        jsonObject1.add("vector",gson.toJsonTree(vector1));
        jsonObject2.addProperty("price",5000f);
        jsonObject2.addProperty("screen_size",15.6f);
        jsonObject2.addProperty("brand","Dell");
        jsonObject2.add("vector",gson.toJsonTree(vector2));

        InsertReq insertReq = InsertReq.builder()
                .collectionName(collectionName)
                .data(Arrays.asList(jsonObject1,jsonObject2))
                .build();
        client.insert(insertReq);
        System.out.println("✅ Data inserted");
    }

}
