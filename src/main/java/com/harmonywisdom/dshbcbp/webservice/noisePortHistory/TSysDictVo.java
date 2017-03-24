/**
 * TSysDictVo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.harmonywisdom.dshbcbp.webservice.noisePortHistory;

public class TSysDictVo  extends ObjectBase implements java.io.Serializable {
    private String ID;

    private String DICT_TYPE;

    private String DICT_TEXT;

    private String DICT_CODE;

    private String DICT_GROUP;

    private String PARENT_TYPE;

    private String PARENT_CODE;

    private String RELATION_TYPE;

    private String GROUP_CODE;

    private String ORDER_ID;

    private String AUTO_LOAD;

    private String EXTENDION;

    private String EXTENDION_CODE;

    private String REMARK;

    private String REMARK1;

    private String REMARK2;

    private String REMARK3;

    public TSysDictVo() {
    }

    public TSysDictVo(
           String SORT_TYPE,
           String SORT_FIELD,
           String ROWNO,
           String ID,
           String DICT_TYPE,
           String DICT_TEXT,
           String DICT_CODE,
           String DICT_GROUP,
           String PARENT_TYPE,
           String PARENT_CODE,
           String RELATION_TYPE,
           String GROUP_CODE,
           String ORDER_ID,
           String AUTO_LOAD,
           String EXTENDION,
           String EXTENDION_CODE,
           String REMARK,
           String REMARK1,
           String REMARK2,
           String REMARK3) {
        super(
            SORT_TYPE,
            SORT_FIELD,
            ROWNO);
        this.ID = ID;
        this.DICT_TYPE = DICT_TYPE;
        this.DICT_TEXT = DICT_TEXT;
        this.DICT_CODE = DICT_CODE;
        this.DICT_GROUP = DICT_GROUP;
        this.PARENT_TYPE = PARENT_TYPE;
        this.PARENT_CODE = PARENT_CODE;
        this.RELATION_TYPE = RELATION_TYPE;
        this.GROUP_CODE = GROUP_CODE;
        this.ORDER_ID = ORDER_ID;
        this.AUTO_LOAD = AUTO_LOAD;
        this.EXTENDION = EXTENDION;
        this.EXTENDION_CODE = EXTENDION_CODE;
        this.REMARK = REMARK;
        this.REMARK1 = REMARK1;
        this.REMARK2 = REMARK2;
        this.REMARK3 = REMARK3;
    }


    /**
     * Gets the ID value for this TSysDictVo.
     *
     * @return ID
     */
    public String getID() {
        return ID;
    }


    /**
     * Sets the ID value for this TSysDictVo.
     *
     * @param ID
     */
    public void setID(String ID) {
        this.ID = ID;
    }


    /**
     * Gets the DICT_TYPE value for this TSysDictVo.
     *
     * @return DICT_TYPE
     */
    public String getDICT_TYPE() {
        return DICT_TYPE;
    }


    /**
     * Sets the DICT_TYPE value for this TSysDictVo.
     *
     * @param DICT_TYPE
     */
    public void setDICT_TYPE(String DICT_TYPE) {
        this.DICT_TYPE = DICT_TYPE;
    }


    /**
     * Gets the DICT_TEXT value for this TSysDictVo.
     *
     * @return DICT_TEXT
     */
    public String getDICT_TEXT() {
        return DICT_TEXT;
    }


    /**
     * Sets the DICT_TEXT value for this TSysDictVo.
     *
     * @param DICT_TEXT
     */
    public void setDICT_TEXT(String DICT_TEXT) {
        this.DICT_TEXT = DICT_TEXT;
    }


    /**
     * Gets the DICT_CODE value for this TSysDictVo.
     *
     * @return DICT_CODE
     */
    public String getDICT_CODE() {
        return DICT_CODE;
    }


    /**
     * Sets the DICT_CODE value for this TSysDictVo.
     *
     * @param DICT_CODE
     */
    public void setDICT_CODE(String DICT_CODE) {
        this.DICT_CODE = DICT_CODE;
    }


    /**
     * Gets the DICT_GROUP value for this TSysDictVo.
     *
     * @return DICT_GROUP
     */
    public String getDICT_GROUP() {
        return DICT_GROUP;
    }


    /**
     * Sets the DICT_GROUP value for this TSysDictVo.
     *
     * @param DICT_GROUP
     */
    public void setDICT_GROUP(String DICT_GROUP) {
        this.DICT_GROUP = DICT_GROUP;
    }


    /**
     * Gets the PARENT_TYPE value for this TSysDictVo.
     *
     * @return PARENT_TYPE
     */
    public String getPARENT_TYPE() {
        return PARENT_TYPE;
    }


    /**
     * Sets the PARENT_TYPE value for this TSysDictVo.
     *
     * @param PARENT_TYPE
     */
    public void setPARENT_TYPE(String PARENT_TYPE) {
        this.PARENT_TYPE = PARENT_TYPE;
    }


    /**
     * Gets the PARENT_CODE value for this TSysDictVo.
     *
     * @return PARENT_CODE
     */
    public String getPARENT_CODE() {
        return PARENT_CODE;
    }


    /**
     * Sets the PARENT_CODE value for this TSysDictVo.
     *
     * @param PARENT_CODE
     */
    public void setPARENT_CODE(String PARENT_CODE) {
        this.PARENT_CODE = PARENT_CODE;
    }


    /**
     * Gets the RELATION_TYPE value for this TSysDictVo.
     *
     * @return RELATION_TYPE
     */
    public String getRELATION_TYPE() {
        return RELATION_TYPE;
    }


    /**
     * Sets the RELATION_TYPE value for this TSysDictVo.
     *
     * @param RELATION_TYPE
     */
    public void setRELATION_TYPE(String RELATION_TYPE) {
        this.RELATION_TYPE = RELATION_TYPE;
    }


    /**
     * Gets the GROUP_CODE value for this TSysDictVo.
     *
     * @return GROUP_CODE
     */
    public String getGROUP_CODE() {
        return GROUP_CODE;
    }


    /**
     * Sets the GROUP_CODE value for this TSysDictVo.
     *
     * @param GROUP_CODE
     */
    public void setGROUP_CODE(String GROUP_CODE) {
        this.GROUP_CODE = GROUP_CODE;
    }


    /**
     * Gets the ORDER_ID value for this TSysDictVo.
     *
     * @return ORDER_ID
     */
    public String getORDER_ID() {
        return ORDER_ID;
    }


    /**
     * Sets the ORDER_ID value for this TSysDictVo.
     *
     * @param ORDER_ID
     */
    public void setORDER_ID(String ORDER_ID) {
        this.ORDER_ID = ORDER_ID;
    }


    /**
     * Gets the AUTO_LOAD value for this TSysDictVo.
     *
     * @return AUTO_LOAD
     */
    public String getAUTO_LOAD() {
        return AUTO_LOAD;
    }


    /**
     * Sets the AUTO_LOAD value for this TSysDictVo.
     *
     * @param AUTO_LOAD
     */
    public void setAUTO_LOAD(String AUTO_LOAD) {
        this.AUTO_LOAD = AUTO_LOAD;
    }


    /**
     * Gets the EXTENDION value for this TSysDictVo.
     *
     * @return EXTENDION
     */
    public String getEXTENDION() {
        return EXTENDION;
    }


    /**
     * Sets the EXTENDION value for this TSysDictVo.
     *
     * @param EXTENDION
     */
    public void setEXTENDION(String EXTENDION) {
        this.EXTENDION = EXTENDION;
    }


    /**
     * Gets the EXTENDION_CODE value for this TSysDictVo.
     *
     * @return EXTENDION_CODE
     */
    public String getEXTENDION_CODE() {
        return EXTENDION_CODE;
    }


    /**
     * Sets the EXTENDION_CODE value for this TSysDictVo.
     *
     * @param EXTENDION_CODE
     */
    public void setEXTENDION_CODE(String EXTENDION_CODE) {
        this.EXTENDION_CODE = EXTENDION_CODE;
    }


    /**
     * Gets the REMARK value for this TSysDictVo.
     *
     * @return REMARK
     */
    public String getREMARK() {
        return REMARK;
    }


    /**
     * Sets the REMARK value for this TSysDictVo.
     *
     * @param REMARK
     */
    public void setREMARK(String REMARK) {
        this.REMARK = REMARK;
    }


    /**
     * Gets the REMARK1 value for this TSysDictVo.
     *
     * @return REMARK1
     */
    public String getREMARK1() {
        return REMARK1;
    }


    /**
     * Sets the REMARK1 value for this TSysDictVo.
     *
     * @param REMARK1
     */
    public void setREMARK1(String REMARK1) {
        this.REMARK1 = REMARK1;
    }


    /**
     * Gets the REMARK2 value for this TSysDictVo.
     *
     * @return REMARK2
     */
    public String getREMARK2() {
        return REMARK2;
    }


    /**
     * Sets the REMARK2 value for this TSysDictVo.
     *
     * @param REMARK2
     */
    public void setREMARK2(String REMARK2) {
        this.REMARK2 = REMARK2;
    }


    /**
     * Gets the REMARK3 value for this TSysDictVo.
     *
     * @return REMARK3
     */
    public String getREMARK3() {
        return REMARK3;
    }


    /**
     * Sets the REMARK3 value for this TSysDictVo.
     *
     * @param REMARK3
     */
    public void setREMARK3(String REMARK3) {
        this.REMARK3 = REMARK3;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof TSysDictVo)) return false;
        TSysDictVo other = (TSysDictVo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) &&
            ((this.ID==null && other.getID()==null) ||
             (this.ID!=null &&
              this.ID.equals(other.getID()))) &&
            ((this.DICT_TYPE==null && other.getDICT_TYPE()==null) ||
             (this.DICT_TYPE!=null &&
              this.DICT_TYPE.equals(other.getDICT_TYPE()))) &&
            ((this.DICT_TEXT==null && other.getDICT_TEXT()==null) ||
             (this.DICT_TEXT!=null &&
              this.DICT_TEXT.equals(other.getDICT_TEXT()))) &&
            ((this.DICT_CODE==null && other.getDICT_CODE()==null) ||
             (this.DICT_CODE!=null &&
              this.DICT_CODE.equals(other.getDICT_CODE()))) &&
            ((this.DICT_GROUP==null && other.getDICT_GROUP()==null) ||
             (this.DICT_GROUP!=null &&
              this.DICT_GROUP.equals(other.getDICT_GROUP()))) &&
            ((this.PARENT_TYPE==null && other.getPARENT_TYPE()==null) ||
             (this.PARENT_TYPE!=null &&
              this.PARENT_TYPE.equals(other.getPARENT_TYPE()))) &&
            ((this.PARENT_CODE==null && other.getPARENT_CODE()==null) ||
             (this.PARENT_CODE!=null &&
              this.PARENT_CODE.equals(other.getPARENT_CODE()))) &&
            ((this.RELATION_TYPE==null && other.getRELATION_TYPE()==null) ||
             (this.RELATION_TYPE!=null &&
              this.RELATION_TYPE.equals(other.getRELATION_TYPE()))) &&
            ((this.GROUP_CODE==null && other.getGROUP_CODE()==null) ||
             (this.GROUP_CODE!=null &&
              this.GROUP_CODE.equals(other.getGROUP_CODE()))) &&
            ((this.ORDER_ID==null && other.getORDER_ID()==null) ||
             (this.ORDER_ID!=null &&
              this.ORDER_ID.equals(other.getORDER_ID()))) &&
            ((this.AUTO_LOAD==null && other.getAUTO_LOAD()==null) ||
             (this.AUTO_LOAD!=null &&
              this.AUTO_LOAD.equals(other.getAUTO_LOAD()))) &&
            ((this.EXTENDION==null && other.getEXTENDION()==null) ||
             (this.EXTENDION!=null &&
              this.EXTENDION.equals(other.getEXTENDION()))) &&
            ((this.EXTENDION_CODE==null && other.getEXTENDION_CODE()==null) ||
             (this.EXTENDION_CODE!=null &&
              this.EXTENDION_CODE.equals(other.getEXTENDION_CODE()))) &&
            ((this.REMARK==null && other.getREMARK()==null) ||
             (this.REMARK!=null &&
              this.REMARK.equals(other.getREMARK()))) &&
            ((this.REMARK1==null && other.getREMARK1()==null) ||
             (this.REMARK1!=null &&
              this.REMARK1.equals(other.getREMARK1()))) &&
            ((this.REMARK2==null && other.getREMARK2()==null) ||
             (this.REMARK2!=null &&
              this.REMARK2.equals(other.getREMARK2()))) &&
            ((this.REMARK3==null && other.getREMARK3()==null) ||
             (this.REMARK3!=null &&
              this.REMARK3.equals(other.getREMARK3())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = super.hashCode();
        if (getID() != null) {
            _hashCode += getID().hashCode();
        }
        if (getDICT_TYPE() != null) {
            _hashCode += getDICT_TYPE().hashCode();
        }
        if (getDICT_TEXT() != null) {
            _hashCode += getDICT_TEXT().hashCode();
        }
        if (getDICT_CODE() != null) {
            _hashCode += getDICT_CODE().hashCode();
        }
        if (getDICT_GROUP() != null) {
            _hashCode += getDICT_GROUP().hashCode();
        }
        if (getPARENT_TYPE() != null) {
            _hashCode += getPARENT_TYPE().hashCode();
        }
        if (getPARENT_CODE() != null) {
            _hashCode += getPARENT_CODE().hashCode();
        }
        if (getRELATION_TYPE() != null) {
            _hashCode += getRELATION_TYPE().hashCode();
        }
        if (getGROUP_CODE() != null) {
            _hashCode += getGROUP_CODE().hashCode();
        }
        if (getORDER_ID() != null) {
            _hashCode += getORDER_ID().hashCode();
        }
        if (getAUTO_LOAD() != null) {
            _hashCode += getAUTO_LOAD().hashCode();
        }
        if (getEXTENDION() != null) {
            _hashCode += getEXTENDION().hashCode();
        }
        if (getEXTENDION_CODE() != null) {
            _hashCode += getEXTENDION_CODE().hashCode();
        }
        if (getREMARK() != null) {
            _hashCode += getREMARK().hashCode();
        }
        if (getREMARK1() != null) {
            _hashCode += getREMARK1().hashCode();
        }
        if (getREMARK2() != null) {
            _hashCode += getREMARK2().hashCode();
        }
        if (getREMARK3() != null) {
            _hashCode += getREMARK3().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TSysDictVo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", "TSysDictVo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "ID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DICT_TYPE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "DICT_TYPE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DICT_TEXT");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "DICT_TEXT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DICT_CODE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "DICT_CODE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DICT_GROUP");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "DICT_GROUP"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PARENT_TYPE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "PARENT_TYPE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PARENT_CODE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "PARENT_CODE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("RELATION_TYPE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "RELATION_TYPE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("GROUP_CODE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "GROUP_CODE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ORDER_ID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "ORDER_ID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("AUTO_LOAD");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "AUTO_LOAD"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("EXTENDION");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "EXTENDION"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("EXTENDION_CODE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "EXTENDION_CODE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("REMARK");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "REMARK"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("REMARK1");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "REMARK1"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("REMARK2");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "REMARK2"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("REMARK3");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "REMARK3"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           String mechType,
           Class _javaType,
           javax.xml.namespace.QName _xmlType) {
        return
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           String mechType,
           Class _javaType,
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
