/**
 * GetInitPointInfoAll.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.harmonywisdom.dshbcbp.webservice.noisePortHistory;

public class GetInitPointInfoAll  implements java.io.Serializable {
    private String strUserName;

    private String strUserPwd;

    private String strPtName;

    private String strPtCateGory;

    private String strPtService;

    public GetInitPointInfoAll() {
    }

    public GetInitPointInfoAll(
           String strUserName,
           String strUserPwd,
           String strPtName,
           String strPtCateGory,
           String strPtService) {
           this.strUserName = strUserName;
           this.strUserPwd = strUserPwd;
           this.strPtName = strPtName;
           this.strPtCateGory = strPtCateGory;
           this.strPtService = strPtService;
    }


    /**
     * Gets the strUserName value for this GetInitPointInfoAll.
     *
     * @return strUserName
     */
    public String getStrUserName() {
        return strUserName;
    }


    /**
     * Sets the strUserName value for this GetInitPointInfoAll.
     *
     * @param strUserName
     */
    public void setStrUserName(String strUserName) {
        this.strUserName = strUserName;
    }


    /**
     * Gets the strUserPwd value for this GetInitPointInfoAll.
     *
     * @return strUserPwd
     */
    public String getStrUserPwd() {
        return strUserPwd;
    }


    /**
     * Sets the strUserPwd value for this GetInitPointInfoAll.
     *
     * @param strUserPwd
     */
    public void setStrUserPwd(String strUserPwd) {
        this.strUserPwd = strUserPwd;
    }


    /**
     * Gets the strPtName value for this GetInitPointInfoAll.
     *
     * @return strPtName
     */
    public String getStrPtName() {
        return strPtName;
    }


    /**
     * Sets the strPtName value for this GetInitPointInfoAll.
     *
     * @param strPtName
     */
    public void setStrPtName(String strPtName) {
        this.strPtName = strPtName;
    }


    /**
     * Gets the strPtCateGory value for this GetInitPointInfoAll.
     *
     * @return strPtCateGory
     */
    public String getStrPtCateGory() {
        return strPtCateGory;
    }


    /**
     * Sets the strPtCateGory value for this GetInitPointInfoAll.
     *
     * @param strPtCateGory
     */
    public void setStrPtCateGory(String strPtCateGory) {
        this.strPtCateGory = strPtCateGory;
    }


    /**
     * Gets the strPtService value for this GetInitPointInfoAll.
     *
     * @return strPtService
     */
    public String getStrPtService() {
        return strPtService;
    }


    /**
     * Sets the strPtService value for this GetInitPointInfoAll.
     *
     * @param strPtService
     */
    public void setStrPtService(String strPtService) {
        this.strPtService = strPtService;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof GetInitPointInfoAll)) return false;
        GetInitPointInfoAll other = (GetInitPointInfoAll) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            ((this.strUserName==null && other.getStrUserName()==null) ||
             (this.strUserName!=null &&
              this.strUserName.equals(other.getStrUserName()))) &&
            ((this.strUserPwd==null && other.getStrUserPwd()==null) ||
             (this.strUserPwd!=null &&
              this.strUserPwd.equals(other.getStrUserPwd()))) &&
            ((this.strPtName==null && other.getStrPtName()==null) ||
             (this.strPtName!=null &&
              this.strPtName.equals(other.getStrPtName()))) &&
            ((this.strPtCateGory==null && other.getStrPtCateGory()==null) ||
             (this.strPtCateGory!=null &&
              this.strPtCateGory.equals(other.getStrPtCateGory()))) &&
            ((this.strPtService==null && other.getStrPtService()==null) ||
             (this.strPtService!=null &&
              this.strPtService.equals(other.getStrPtService())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getStrUserName() != null) {
            _hashCode += getStrUserName().hashCode();
        }
        if (getStrUserPwd() != null) {
            _hashCode += getStrUserPwd().hashCode();
        }
        if (getStrPtName() != null) {
            _hashCode += getStrPtName().hashCode();
        }
        if (getStrPtCateGory() != null) {
            _hashCode += getStrPtCateGory().hashCode();
        }
        if (getStrPtService() != null) {
            _hashCode += getStrPtService().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetInitPointInfoAll.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">GetInitPointInfoAll"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("strUserName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "strUserName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("strUserPwd");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "strUserPwd"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("strPtName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "strPtName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("strPtCateGory");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "strPtCateGory"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("strPtService");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "strPtService"));
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
