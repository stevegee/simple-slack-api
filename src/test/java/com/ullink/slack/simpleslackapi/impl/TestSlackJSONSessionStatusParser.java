package com.ullink.slack.simpleslackapi.impl;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TestSlackJSONSessionStatusParser
{

    @Test
    public void testParsingSessionDescription() throws Exception
    {
        InputStream stream = getClass().getResourceAsStream("/test_json.json");
        InputStreamReader isReader = new InputStreamReader(stream);
        BufferedReader reader = new BufferedReader(isReader);
        StringBuilder strBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            strBuilder.append(line);
        }
        SlackJSONSessionStatusParser parser = new SlackJSONSessionStatusParser(strBuilder.toString());
        parser.parse();
        Assertions.assertThat(parser.getChannels()).containsOnlyKeys("CHANNELID1","CHANNELID2","CHANNELID3","GROUPID1");
        Assertions.assertThat(parser.getUsers()).containsOnlyKeys("USERID1","USERID2","USERID3","USERID4");
        Assertions.assertThat(parser.getBots()).containsOnlyKeys("BOTID1","BOTID2");
        Assertions.assertThat(parser.getWebSocketURL()).isEqualTo("wss://mywebsocketurl");
    }
}
