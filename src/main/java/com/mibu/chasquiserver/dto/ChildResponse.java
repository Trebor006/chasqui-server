package com.mibu.chasquiserver.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChildResponse {

    private String _index;
    private String _id;
    private String _score;
    private ProductDto _source;
}
