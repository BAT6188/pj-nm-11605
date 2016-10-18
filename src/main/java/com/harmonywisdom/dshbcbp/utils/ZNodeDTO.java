package com.harmonywisdom.dshbcbp.utils;

import java.util.List;

/**
 * ztree node节点dto
 */
public class ZNodeDTO {

    private String id;

    private String name;

    private List<ZNodeDTO> children;

    public ZNodeDTO(){}

    public ZNodeDTO(String id, String name){
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ZNodeDTO> getChildren() {
        return children;
    }

    public void setChildren(List<ZNodeDTO> children) {
        this.children = children;
    }
}
