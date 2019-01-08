package com.springboot.map.entity;

import lombok.Data;
import net.bytebuddy.agent.builder.AgentBuilder;

import java.util.List;
import java.util.Map;

@Data
public class Response {

    Meta meta;
    List<Documents> documents;

}
