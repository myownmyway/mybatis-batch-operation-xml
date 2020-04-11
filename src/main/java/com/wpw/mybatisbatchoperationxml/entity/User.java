package com.wpw.mybatisbatchoperationxml.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author wpw
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Accessors(chain = true)
public class User implements Serializable {
    private Integer id;
    private String userName;
    private String passWord;
    private Integer age;
    private List<Integer> idList;
    private Integer pageNumber;
    private Integer pageSize;
    private Integer[] ids;
}
