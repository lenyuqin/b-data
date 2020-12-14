package com.site.bdata.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lenyuqin
 * @data 2020/10/30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private String remember;
    private String username;
    private String password;
}
