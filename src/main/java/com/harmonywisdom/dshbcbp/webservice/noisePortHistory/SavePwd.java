/**
 * SavePwd.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.harmonywisdom.dshbcbp.webservice.noisePortHistory;

public class SavePwd  implements java.io.Serializable {
    private String strUserName;

    private String strOldPwd;

    private String strNewPwd;

    public SavePwd() {
    }

    public SavePwd(
           String strUserName,
           String strOldPwd,
           String strNewPwd) {
           this.strUserName = strUserName;
           this.strOldPwd = strOldPwd;
           this.strNewPwd = strNewPwd;
    }


    /**
     * Gets the strUserName value for this SavePwd.
     *
     * @return strUserName
     */
    public String getStrUserName() {
        return strUserName;
    }


    /**
     * Sets the strUserName value for this SavePwd.
     *
     * @param strUserName
     */
    public void setStrUserName(String strUserName) {
        this.strUserName = strUserName;
    }


    /**
     * Gets the strOldPwd value for this SavePwd.
     *
     * @return strOldPwd
     */
    public String getStrOldPwd() {
        return strOldPwd;
    }


    /**
     * Sets the strOldPwd value for this SavePwd.
     *
     * @param strOldPwd
     */
    public void setStrOldPwd(String strOldPwd) {
        this.strOldPwd = strOldPwd;
    }


    /**
     * Gets the strNewPwd value for this SavePwd.
     *
     * @return strNewPwd
     */
    public String getStrNewPwd() {
        return strNewPwd;
    }


    /**
     * Sets the strNewPwd value for this SavePwd.
     *
     * @param strNewPwd
     */
    public void setStrNewPwd(String strNewPwd) {
        this.strNewPwd = strNewPwd;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof SavePwd)) return false;
        SavePwd other = (SavePwd) obj;
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
            ((this.strOldPwd==null && other.getStrOldPwd()==null) ||
             (this.strOldPwd!=null &&
              this.strOldPwd.equals(other.getStrOldPwd()))) &&
            ((this.strNewPwd==null && other.getStrNewPwd()==null) ||
             (this.strNewPwd!=null &&
              this.strNewPwd.equals(other.getStrNewPwd())));
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
        if (getStrOldPwd() != null) {
            _hashCode += getStrOldPwd().hashCode();
        }
        if (getStrNewPwd() != null) {
            _hashCode += getStrNewPwd().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SavePwd.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">SavePwd"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("strUserName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "strUserName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("strOldPwd");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "strOldPwd"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("strNewPwd");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "strNewPwd"));
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
