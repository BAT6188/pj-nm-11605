package com.harmonywisdom.dshbcbp.utils;

import java.util.List;

/**
 * ztree node节点dto
 */
public class ZNodeDTO {

    private String id;

    private String name;

    private Boolean isParent;

    private Boolean checked;

    private String icon;

    private String type;

    private Boolean open;

    private List<ZNodeDTO> children;

    public ZNodeDTO(){}

    public ZNodeDTO(String id, String name){
        this.id = id;
        this.name = name;
    }
    public ZNodeDTO(String id, String name,Boolean isParent){
        this(id,name);
        this.isParent = isParent;
    }
    public ZNodeDTO(String id, String name,Boolean isParent, Boolean checked){
        this(id,name, isParent);
        this.checked = checked;
    }
    public ZNodeDTO(String id, String name,Boolean isParent, Boolean checked,String type){
        this(id,name, isParent, checked);
        this.type = type;
    }
    public ZNodeDTO(String id, String name,String type){
        this(id,name);
        this.type = type;
    }
    public ZNodeDTO(String id, String name,Boolean isParent,String type){
        this(id,name,isParent);
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

    public Boolean getIsParent() {
        return isParent;
    }

    public void setIsParent(Boolean isParent) {
        this.isParent = isParent;
    }

    public Boolean getParent() {
        return isParent;
    }

    public void setParent(Boolean parent) {
        isParent = parent;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }
}
