package com.hw.pulsar.function.demo;

import org.apache.pulsar.functions.api.Context;
import org.apache.pulsar.functions.api.Function;

import java.util.Arrays;
import java.util.HashMap;

public class WordCountFunction implements Function<String, String> {
    @Override
    public String process(String input, Context context) {
        HashMap<String, Long> result = new HashMap<String, Long>();
        /**
         * 错误信息：
         *      java.lang.IllegalStateException: State my-tenant/test-namespace/myfunc is not enabled.
         *
         * 这里使用调用了context.incrCounter有状态接口
         * 那么该function就是一个Stateful-Functions，需要在bookeeper中启用streamStorage服务，进行状态存储，否则就会报上面所示的错误
         */
        Arrays.asList(input.split(" ")).forEach(word -> {
            String contentKey = word.toLowerCase();
            context.incrCounter(contentKey, 1);
            result.put(contentKey, context.getCounter(contentKey));
        });

        return String.format("%s", result.toString());
    }
}
