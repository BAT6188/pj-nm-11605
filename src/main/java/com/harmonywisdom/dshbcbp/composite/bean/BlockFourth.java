package com.harmonywisdom.dshbcbp.composite.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 四级网格
 */
@Entity
@Table(name = "HW_BLOCK_FOURTH")
public class BlockFourth implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(length = 32)
    private String id;
    /**
     *网格级别id
     */
    @Column(name = "BLOCK_LEVEL_ID",length = 100)
    private int blockLevelId;
    /**
     *网格级别名称
     */
    @Column(name = "BLOCK_LEVEL_NAME",length = 100)
    private int blockLevelName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getBlockLevelId() {
        return blockLevelId;
    }

    public void setBlockLevelId(int blockLevelId) {
        this.blockLevelId = blockLevelId;
    }

    public int getBlockLevelName() {
        return blockLevelName;
    }

    public void setBlockLevelName(int blockLevelName) {
        this.blockLevelName = blockLevelName;
    }
}