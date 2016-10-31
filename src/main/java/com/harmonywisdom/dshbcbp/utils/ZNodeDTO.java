package com.harmonywisdom.dshbcbp.utils;

import java.util.List;

/**
 * ztree node节点dto
 */
public class ZNodeDTO {

    private String id;

    private String name;

    private String type;

    private List<ZNodeDTO> children;

    public ZNodeDTO(){}

    public ZNodeDTO(String id, String name){
        this.id = id;
        this.name = name;
    }
    public ZNodeDTO(String id, String name,String type){
        this(id,name);
        this.type = type;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<ZNodeDTO> getChildren() {
        return children;
    }

    public void setChildren(List<ZNodeDTO> children) {
        this.children = children;
    }
}
