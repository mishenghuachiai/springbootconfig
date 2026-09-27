package com.example.demo.utils;

import com.alibaba.dashscope.embeddings.TextEmbedding;
import com.alibaba.dashscope.embeddings.TextEmbeddingParam;
import com.alibaba.dashscope.embeddings.TextEmbeddingResult;
import com.alibaba.dashscope.exception.NoApiKeyException;
import com.alibaba.dashscope.utils.Constants;

import java.util.Collections;

public class TestText2Vector {
    static {
        Constants.baseHttpApiUrl="https://maas.qianwenaiapi.com/api/v1";
    }
    public static void main(String[] args) {
        // 我爱吃苹果
        // 我爱吃Apple
        String inputTexts = "我爱吃苹果";
        try {
            TextEmbeddingParam param = TextEmbeddingParam
                    .builder()
                    .model("qwen3.7-text-embedding")
                    .texts(Collections.singleton(inputTexts))
                    .build();

            TextEmbedding textEmbedding = new TextEmbedding();
            TextEmbeddingResult result = textEmbedding.call(param);
            System.out.println(result);

        } catch (NoApiKeyException e) {
            System.err.println("调用 API 时发生异常: " + e.getMessage());
            System.err.println("请检查 API Key 是否已正确配置。");
            e.printStackTrace();
        }
    }
}