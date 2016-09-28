package com.harmonywisdom.dshbcbp.port.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "HW_DSHBCBP_FUMES_PORT_HISTORY")
public class FumesPortHistory implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(length = 32)
    private String id;
}