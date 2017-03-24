/**
 * TSysStationPointVo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.harmonywisdom.dshbcbp.webservice.noisePortHistory;

public class TSysStationPointVo  extends ObjectBase implements java.io.Serializable {
    private String ID;

    private String POINT_CODE;

    private String POINT_NAME;

    private String POINT_ALIAS;

    private String DYNA_ATTR_TYPE;

    private String DEPT_CODE;

    private String AREA_CODE;

    private String SERVICE_CODE;

    private String POINT_TYPE;

    private String POINT_CATEGORY;

    private String CONTROL_LEVEL;

    private String MONITOR_STANDARD;

    private String ENTERID;

    private String ADDRESS;

    private String LONGITUDE;

    private String LATITUDE;

    private String ENGINEER_ID;

    private String CREATE_ID;

    private String CREATE_TIME;

    private String DESCRIPTION;

    private String REMARK;

    private String REMARK1;

    private String REMARK2;

    private String REMARK3;

    private String REMARK4;

    private String REMARK5;

    public TSysStationPointVo() {
    }

    public TSysStationPointVo(
           String SORT_TYPE,
           String SORT_FIELD,
           String ROWNO,
           String ID,
           String POINT_CODE,
           String POINT_NAME,
           String POINT_ALIAS,
           String DYNA_ATTR_TYPE,
           String DEPT_CODE,
           String AREA_CODE,
           String SERVICE_CODE,
           String POINT_TYPE,
           String POINT_CATEGORY,
           String CONTROL_LEVEL,
           String MONITOR_STANDARD,
           String ENTERID,
           String ADDRESS,
           String LONGITUDE,
           String LATITUDE,
           String ENGINEER_ID,
           String CREATE_ID,
           String CREATE_TIME,
           String DESCRIPTION,
           String REMARK,
           String REMARK1,
           String REMARK2,
           String REMARK3,
           String REMARK4,
           String REMARK5) {
        super(
            SORT_TYPE,
            SORT_FIELD,
            ROWNO);
        this.ID = ID;
        this.POINT_CODE = POINT_CODE;
        this.POINT_NAME = POINT_NAME;
        this.POINT_ALIAS = POINT_ALIAS;
        this.DYNA_ATTR_TYPE = DYNA_ATTR_TYPE;
        this.DEPT_CODE = DEPT_CODE;
        this.AREA_CODE = AREA_CODE;
        this.SERVICE_CODE = SERVICE_CODE;
        this.POINT_TYPE = POINT_TYPE;
        this.POINT_CATEGORY = POINT_CATEGORY;
        this.CONTROL_LEVEL = CONTROL_LEVEL;
        this.MONITOR_STANDARD = MONITOR_STANDARD;
        this.ENTERID = ENTERID;
        this.ADDRESS = ADDRESS;
        this.LONGITUDE = LONGITUDE;
        this.LATITUDE = LATITUDE;
        this.ENGINEER_ID = ENGINEER_ID;
        this.CREATE_ID = CREATE_ID;
        this.CREATE_TIME = CREATE_TIME;
        this.DESCRIPTION = DESCRIPTION;
        this.REMARK = REMARK;
        this.REMARK1 = REMARK1;
        this.REMARK2 = REMARK2;
        this.REMARK3 = REMARK3;
        this.REMARK4 = REMARK4;
        this.REMARK5 = REMARK5;
    }


    /**
     * Gets the ID value for this TSysStationPointVo.
     *
     * @return ID
     */
    public String getID() {
        return ID;
    }


    /**
     * Sets the ID value for this TSysStationPointVo.
     *
     * @param ID
     */
    public void setID(String ID) {
        this.ID = ID;
    }


    /**
     * Gets the POINT_CODE value for this TSysStationPointVo.
     *
     * @return POINT_CODE
     */
    public String getPOINT_CODE() {
        return POINT_CODE;
    }


    /**
     * Sets the POINT_CODE value for this TSysStationPointVo.
     *
     * @param POINT_CODE
     */
    public void setPOINT_CODE(String POINT_CODE) {
        this.POINT_CODE = POINT_CODE;
    }


    /**
     * Gets the POINT_NAME value for this TSysStationPointVo.
     *
     * @return POINT_NAME
     */
    public String getPOINT_NAME() {
        return POINT_NAME;
    }


    /**
     * Sets the POINT_NAME value for this TSysStationPointVo.
     *
     * @param POINT_NAME
     */
    public void setPOINT_NAME(String POINT_NAME) {
        this.POINT_NAME = POINT_NAME;
    }


    /**
     * Gets the POINT_ALIAS value for this TSysStationPointVo.
     *
     * @return POINT_ALIAS
     */
    public String getPOINT_ALIAS() {
        return POINT_ALIAS;
    }


    /**
     * Sets the POINT_ALIAS value for this TSysStationPointVo.
     *
     * @param POINT_ALIAS
     */
    public void setPOINT_ALIAS(String POINT_ALIAS) {
        this.POINT_ALIAS = POINT_ALIAS;
    }


    /**
     * Gets the DYNA_ATTR_TYPE value for this TSysStationPointVo.
     *
     * @return DYNA_ATTR_TYPE
     */
    public String getDYNA_ATTR_TYPE() {
        return DYNA_ATTR_TYPE;
    }


    /**
     * Sets the DYNA_ATTR_TYPE value for this TSysStationPointVo.
     *
     * @param DYNA_ATTR_TYPE
     */
    public void setDYNA_ATTR_TYPE(String DYNA_ATTR_TYPE) {
        this.DYNA_ATTR_TYPE = DYNA_ATTR_TYPE;
    }


    /**
     * Gets the DEPT_CODE value for this TSysStationPointVo.
     *
     * @return DEPT_CODE
     */
    public String getDEPT_CODE() {
        return DEPT_CODE;
    }


    /**
     * Sets the DEPT_CODE value for this TSysStationPointVo.
     *
     * @param DEPT_CODE
     */
    public void setDEPT_CODE(String DEPT_CODE) {
        this.DEPT_CODE = DEPT_CODE;
    }


    /**
     * Gets the AREA_CODE value for this TSysStationPointVo.
     *
     * @return AREA_CODE
     */
    public String getAREA_CODE() {
        return AREA_CODE;
    }


    /**
     * Sets the AREA_CODE value for this TSysStationPointVo.
     *
     * @param AREA_CODE
     */
    public void setAREA_CODE(String AREA_CODE) {
        this.AREA_CODE = AREA_CODE;
    }


    /**
     * Gets the SERVICE_CODE value for this TSysStationPointVo.
     *
     * @return SERVICE_CODE
     */
    public String getSERVICE_CODE() {
        return SERVICE_CODE;
    }


    /**
     * Sets the SERVICE_CODE value for this TSysStationPointVo.
     *
     * @param SERVICE_CODE
     */
    public void setSERVICE_CODE(String SERVICE_CODE) {
        this.SERVICE_CODE = SERVICE_CODE;
    }


    /**
     * Gets the POINT_TYPE value for this TSysStationPointVo.
     *
     * @return POINT_TYPE
     */
    public String getPOINT_TYPE() {
        return POINT_TYPE;
    }


    /**
     * Sets the POINT_TYPE value for this TSysStationPointVo.
     *
     * @param POINT_TYPE
     */
    public void setPOINT_TYPE(String POINT_TYPE) {
        this.POINT_TYPE = POINT_TYPE;
    }


    /**
     * Gets the POINT_CATEGORY value for this TSysStationPointVo.
     *
     * @return POINT_CATEGORY
     */
    public String getPOINT_CATEGORY() {
        return POINT_CATEGORY;
    }


    /**
     * Sets the POINT_CATEGORY value for this TSysStationPointVo.
     *
     * @param POINT_CATEGORY
     */
    public void setPOINT_CATEGORY(String POINT_CATEGORY) {
        this.POINT_CATEGORY = POINT_CATEGORY;
    }


    /**
     * Gets the CONTROL_LEVEL value for this TSysStationPointVo.
     *
     * @return CONTROL_LEVEL
     */
    public String getCONTROL_LEVEL() {
        return CONTROL_LEVEL;
    }


    /**
     * Sets the CONTROL_LEVEL value for this TSysStationPointVo.
     *
     * @param CONTROL_LEVEL
     */
    public void setCONTROL_LEVEL(String CONTROL_LEVEL) {
        this.CONTROL_LEVEL = CONTROL_LEVEL;
    }


    /**
     * Gets the MONITOR_STANDARD value for this TSysStationPointVo.
     *
     * @return MONITOR_STANDARD
     */
    public String getMONITOR_STANDARD() {
        return MONITOR_STANDARD;
    }


    /**
     * Sets the MONITOR_STANDARD value for this TSysStationPointVo.
     *
     * @param MONITOR_STANDARD
     */
    public void setMONITOR_STANDARD(String MONITOR_STANDARD) {
        this.MONITOR_STANDARD = MONITOR_STANDARD;
    }


    /**
     * Gets the ENTERID value for this TSysStationPointVo.
     *
     * @return ENTERID
     */
    public String getENTERID() {
        return ENTERID;
    }


    /**
     * Sets the ENTERID value for this TSysStationPointVo.
     *
     * @param ENTERID
     */
    public void setENTERID(String ENTERID) {
        this.ENTERID = ENTERID;
    }


    /**
     * Gets the ADDRESS value for this TSysStationPointVo.
     *
     * @return ADDRESS
     */
    public String getADDRESS() {
        return ADDRESS;
    }


    /**
     * Sets the ADDRESS value for this TSysStationPointVo.
     *
     * @param ADDRESS
     */
    public void setADDRESS(String ADDRESS) {
        this.ADDRESS = ADDRESS;
    }


    /**
     * Gets the LONGITUDE value for this TSysStationPointVo.
     *
     * @return LONGITUDE
     */
    public String getLONGITUDE() {
        return LONGITUDE;
    }


    /**
     * Sets the LONGITUDE value for this TSysStationPointVo.
     *
     * @param LONGITUDE
     */
    public void setLONGITUDE(String LONGITUDE) {
        this.LONGITUDE = LONGITUDE;
    }


    /**
     * Gets the LATITUDE value for this TSysStationPointVo.
     *
     * @return LATITUDE
     */
    public String getLATITUDE() {
        return LATITUDE;
    }


    /**
     * Sets the LATITUDE value for this TSysStationPointVo.
     *
     * @param LATITUDE
     */
    public void setLATITUDE(String LATITUDE) {
        this.LATITUDE = LATITUDE;
    }


    /**
     * Gets the ENGINEER_ID value for this TSysStationPointVo.
     *
     * @return ENGINEER_ID
     */
    public String getENGINEER_ID() {
        return ENGINEER_ID;
    }


    /**
     * Sets the ENGINEER_ID value for this TSysStationPointVo.
     *
     * @param ENGINEER_ID
     */
    public void setENGINEER_ID(String ENGINEER_ID) {
        this.ENGINEER_ID = ENGINEER_ID;
    }


    /**
     * Gets the CREATE_ID value for this TSysStationPointVo.
     *
     * @return CREATE_ID
     */
    public String getCREATE_ID() {
        return CREATE_ID;
    }


    /**
     * Sets the CREATE_ID value for this TSysStationPointVo.
     *
     * @param CREATE_ID
     */
    public void setCREATE_ID(String CREATE_ID) {
        this.CREATE_ID = CREATE_ID;
    }


    /**
     * Gets the CREATE_TIME value for this TSysStationPointVo.
     *
     * @return CREATE_TIME
     */
    public String getCREATE_TIME() {
        return CREATE_TIME;
    }


    /**
     * Sets the CREATE_TIME value for this TSysStationPointVo.
     *
     * @param CREATE_TIME
     */
    public void setCREATE_TIME(String CREATE_TIME) {
        this.CREATE_TIME = CREATE_TIME;
    }


    /**
     * Gets the DESCRIPTION value for this TSysStationPointVo.
     *
     * @return DESCRIPTION
     */
    public String getDESCRIPTION() {
        return DESCRIPTION;
    }


    /**
     * Sets the DESCRIPTION value for this TSysStationPointVo.
     *
     * @param DESCRIPTION
     */
    public void setDESCRIPTION(String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;
    }


    /**
     * Gets the REMARK value for this TSysStationPointVo.
     *
     * @return REMARK
     */
    public String getREMARK() {
        return REMARK;
    }


    /**
     * Sets the REMARK value for this TSysStationPointVo.
     *
     * @param REMARK
     */
    public void setREMARK(String REMARK) {
        this.REMARK = REMARK;
    }


    /**
     * Gets the REMARK1 value for this TSysStationPointVo.
     *
     * @return REMARK1
     */
    public String getREMARK1() {
        return REMARK1;
    }


    /**
     * Sets the REMARK1 value for this TSysStationPointVo.
     *
     * @param REMARK1
     */
    public void setREMARK1(String REMARK1) {
        this.REMARK1 = REMARK1;
    }


    /**
     * Gets the REMARK2 value for this TSysStationPointVo.
     *
     * @return REMARK2
     */
    public String getREMARK2() {
        return REMARK2;
    }


    /**
     * Sets the REMARK2 value for this TSysStationPointVo.
     *
     * @param REMARK2
     */
    public void setREMARK2(String REMARK2) {
        this.REMARK2 = REMARK2;
    }


    /**
     * Gets the REMARK3 value for this TSysStationPointVo.
     *
     * @return REMARK3
     */
    public String getREMARK3() {
        return REMARK3;
    }


    /**
     * Sets the REMARK3 value for this TSysStationPointVo.
     *
     * @param REMARK3
     */
    public void setREMARK3(String REMARK3) {
        this.REMARK3 = REMARK3;
    }


    /**
     * Gets the REMARK4 value for this TSysStationPointVo.
     *
     * @return REMARK4
     */
    public String getREMARK4() {
        return REMARK4;
    }


    /**
     * Sets the REMARK4 value for this TSysStationPointVo.
     *
     * @param REMARK4
     */
    public void setREMARK4(String REMARK4) {
        this.REMARK4 = REMARK4;
    }


    /**
     * Gets the REMARK5 value for this TSysStationPointVo.
     *
     * @return REMARK5
     */
    public String getREMARK5() {
        return REMARK5;
    }


    /**
     * Sets the REMARK5 value for this TSysStationPointVo.
     *
     * @param REMARK5
     */
    public void setREMARK5(String REMARK5) {
        this.REMARK5 = REMARK5;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof TSysStationPointVo)) return false;
        TSysStationPointVo other = (TSysStationPointVo) obj;
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
            ((this.POINT_CODE==null && other.getPOINT_CODE()==null) ||
             (this.POINT_CODE!=null &&
              this.POINT_CODE.equals(other.getPOINT_CODE()))) &&
            ((this.POINT_NAME==null && other.getPOINT_NAME()==null) ||
             (this.POINT_NAME!=null &&
              this.POINT_NAME.equals(other.getPOINT_NAME()))) &&
            ((this.POINT_ALIAS==null && other.getPOINT_ALIAS()==null) ||
             (this.POINT_ALIAS!=null &&
              this.POINT_ALIAS.equals(other.getPOINT_ALIAS()))) &&
            ((this.DYNA_ATTR_TYPE==null && other.getDYNA_ATTR_TYPE()==null) ||
             (this.DYNA_ATTR_TYPE!=null &&
              this.DYNA_ATTR_TYPE.equals(other.getDYNA_ATTR_TYPE()))) &&
            ((this.DEPT_CODE==null && other.getDEPT_CODE()==null) ||
             (this.DEPT_CODE!=null &&
              this.DEPT_CODE.equals(other.getDEPT_CODE()))) &&
            ((this.AREA_CODE==null && other.getAREA_CODE()==null) ||
             (this.AREA_CODE!=null &&
              this.AREA_CODE.equals(other.getAREA_CODE()))) &&
            ((this.SERVICE_CODE==null && other.getSERVICE_CODE()==null) ||
             (this.SERVICE_CODE!=null &&
              this.SERVICE_CODE.equals(other.getSERVICE_CODE()))) &&
            ((this.POINT_TYPE==null && other.getPOINT_TYPE()==null) ||
             (this.POINT_TYPE!=null &&
              this.POINT_TYPE.equals(other.getPOINT_TYPE()))) &&
            ((this.POINT_CATEGORY==null && other.getPOINT_CATEGORY()==null) ||
             (this.POINT_CATEGORY!=null &&
              this.POINT_CATEGORY.equals(other.getPOINT_CATEGORY()))) &&
            ((this.CONTROL_LEVEL==null && other.getCONTROL_LEVEL()==null) ||
             (this.CONTROL_LEVEL!=null &&
              this.CONTROL_LEVEL.equals(other.getCONTROL_LEVEL()))) &&
            ((this.MONITOR_STANDARD==null && other.getMONITOR_STANDARD()==null) ||
             (this.MONITOR_STANDARD!=null &&
              this.MONITOR_STANDARD.equals(other.getMONITOR_STANDARD()))) &&
            ((this.ENTERID==null && other.getENTERID()==null) ||
             (this.ENTERID!=null &&
              this.ENTERID.equals(other.getENTERID()))) &&
            ((this.ADDRESS==null && other.getADDRESS()==null) ||
             (this.ADDRESS!=null &&
              this.ADDRESS.equals(other.getADDRESS()))) &&
            ((this.LONGITUDE==null && other.getLONGITUDE()==null) ||
             (this.LONGITUDE!=null &&
              this.LONGITUDE.equals(other.getLONGITUDE()))) &&
            ((this.LATITUDE==null && other.getLATITUDE()==null) ||
             (this.LATITUDE!=null &&
              this.LATITUDE.equals(other.getLATITUDE()))) &&
            ((this.ENGINEER_ID==null && other.getENGINEER_ID()==null) ||
             (this.ENGINEER_ID!=null &&
              this.ENGINEER_ID.equals(other.getENGINEER_ID()))) &&
            ((this.CREATE_ID==null && other.getCREATE_ID()==null) ||
             (this.CREATE_ID!=null &&
              this.CREATE_ID.equals(other.getCREATE_ID()))) &&
            ((this.CREATE_TIME==null && other.getCREATE_TIME()==null) ||
             (this.CREATE_TIME!=null &&
              this.CREATE_TIME.equals(other.getCREATE_TIME()))) &&
            ((this.DESCRIPTION==null && other.getDESCRIPTION()==null) ||
             (this.DESCRIPTION!=null &&
              this.DESCRIPTION.equals(other.getDESCRIPTION()))) &&
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
              this.REMARK3.equals(other.getREMARK3()))) &&
            ((this.REMARK4==null && other.getREMARK4()==null) ||
             (this.REMARK4!=null &&
              this.REMARK4.equals(other.getREMARK4()))) &&
            ((this.REMARK5==null && other.getREMARK5()==null) ||
             (this.REMARK5!=null &&
              this.REMARK5.equals(other.getREMARK5())));
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
        if (getPOINT_CODE() != null) {
            _hashCode += getPOINT_CODE().hashCode();
        }
        if (getPOINT_NAME() != null) {
            _hashCode += getPOINT_NAME().hashCode();
        }
        if (getPOINT_ALIAS() != null) {
            _hashCode += getPOINT_ALIAS().hashCode();
        }
        if (getDYNA_ATTR_TYPE() != null) {
            _hashCode += getDYNA_ATTR_TYPE().hashCode();
        }
        if (getDEPT_CODE() != null) {
            _hashCode += getDEPT_CODE().hashCode();
        }
        if (getAREA_CODE() != null) {
            _hashCode += getAREA_CODE().hashCode();
        }
        if (getSERVICE_CODE() != null) {
            _hashCode += getSERVICE_CODE().hashCode();
        }
        if (getPOINT_TYPE() != null) {
            _hashCode += getPOINT_TYPE().hashCode();
        }
        if (getPOINT_CATEGORY() != null) {
            _hashCode += getPOINT_CATEGORY().hashCode();
        }
        if (getCONTROL_LEVEL() != null) {
            _hashCode += getCONTROL_LEVEL().hashCode();
        }
        if (getMONITOR_STANDARD() != null) {
            _hashCode += getMONITOR_STANDARD().hashCode();
        }
        if (getENTERID() != null) {
            _hashCode += getENTERID().hashCode();
        }
        if (getADDRESS() != null) {
            _hashCode += getADDRESS().hashCode();
        }
        if (getLONGITUDE() != null) {
            _hashCode += getLONGITUDE().hashCode();
        }
        if (getLATITUDE() != null) {
            _hashCode += getLATITUDE().hashCode();
        }
        if (getENGINEER_ID() != null) {
            _hashCode += getENGINEER_ID().hashCode();
        }
        if (getCREATE_ID() != null) {
            _hashCode += getCREATE_ID().hashCode();
        }
        if (getCREATE_TIME() != null) {
            _hashCode += getCREATE_TIME().hashCode();
        }
        if (getDESCRIPTION() != null) {
            _hashCode += getDESCRIPTION().hashCode();
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
        if (getREMARK4() != null) {
            _hashCode += getREMARK4().hashCode();
        }
        if (getREMARK5() != null) {
            _hashCode += getREMARK5().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TSysStationPointVo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", "TSysStationPointVo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "ID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("POINT_CODE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "POINT_CODE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("POINT_NAME");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "POINT_NAME"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("POINT_ALIAS");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "POINT_ALIAS"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DYNA_ATTR_TYPE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "DYNA_ATTR_TYPE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DEPT_CODE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "DEPT_CODE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("AREA_CODE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "AREA_CODE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SERVICE_CODE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "SERVICE_CODE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("POINT_TYPE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "POINT_TYPE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("POINT_CATEGORY");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "POINT_CATEGORY"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CONTROL_LEVEL");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "CONTROL_LEVEL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MONITOR_STANDARD");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "MONITOR_STANDARD"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ENTERID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "ENTERID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ADDRESS");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "ADDRESS"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("LONGITUDE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "LONGITUDE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("LATITUDE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "LATITUDE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ENGINEER_ID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "ENGINEER_ID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CREATE_ID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "CREATE_ID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CREATE_TIME");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "CREATE_TIME"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DESCRIPTION");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "DESCRIPTION"));
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
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("REMARK4");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "REMARK4"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("REMARK5");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "REMARK5"));
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
