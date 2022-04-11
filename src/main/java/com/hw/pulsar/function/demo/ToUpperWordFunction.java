package com.hw.pulsar.function.demo;

import org.apache.pulsar.functions.api.Context;
import org.apache.pulsar.functions.api.Function;

/**
 * @author zmayu
 * 将字符串转化为大写字符
 */
public class ToUpperWordFunction implements Function<String, String> {
    @Override
    public String process(String input, Context context) {


        return String.format("%s", input.toUpperCase());
    }
}
