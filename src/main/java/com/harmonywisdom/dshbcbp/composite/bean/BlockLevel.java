package com.harmonywisdom.dshbcbp.composite.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * 网格级别
 */
@Entity
@Table(name = "HW_BLOCK_LEVEL")
public class BlockLevel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(length = 32)
    private String id;
    /**
     *级别
     */
    @Column(name = "LEVEL",length = 100)
    private Integer level;
    /**
     *级别名称
     */
    @Column(name = "NAME",length = 100)
    private String name;

    @Transient
    public List<BlockFirst> getNodes;

    public List<BlockFirst> getGetNodes() {
        return getNodes;
    }

    public void setGetNodes(List<BlockFirst> getNodes) {
        this.getNodes = getNodes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}