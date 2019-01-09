package com.springboot.map.dto;

import lombok.Data;

import java.util.List;

@Data
public class ResponseDto {

    MetaDto metaDto;
    List<DocumentsDto> documents;

}
