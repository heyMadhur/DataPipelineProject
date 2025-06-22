package com.learning.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WikiChangeDTO {
    private String title;
    private String user;
    private String domain;
    private boolean bot;
    private String comment;
    private String server_url;
    private String full_url;
}
