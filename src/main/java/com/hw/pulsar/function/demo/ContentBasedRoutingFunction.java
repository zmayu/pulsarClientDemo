package com.hw.pulsar.function.demo;

import org.apache.pulsar.functions.api.Context;
import org.apache.pulsar.functions.api.Function;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContentBasedRoutingFunction implements Function<String, String> {

    @Override
    public String process(String input, Context context) {
        String regex = context.getUserConfigValue("regex").get().toString();
        String matchedTopic = context.getUserConfigValue("matched-topic").get().toString();
        String unmatchedTopic = context.getUserConfigValue("unmatched-topic").get().toString();


        // check if the input contains "hello" in it.
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        if (matcher.matches()) {
            context.publish(matchedTopic, input);
        } else {
            context.publish(unmatchedTopic, input);
        }

        return null;
    }
}
