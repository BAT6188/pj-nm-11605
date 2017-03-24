/**
 * GetDynamicAnalysis_Body.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.harmonywisdom.dshbcbp.webservice.noisePortHistory;

public class GetDynamicAnalysis_Body  implements java.io.Serializable {
    private String strPointCode;

    private String strDayTimeStrat;

    private String strDayTimeEnd;

    private String strHour;

    private String strUserName;

    private String strUserPwd;

    public GetDynamicAnalysis_Body() {
    }

    public GetDynamicAnalysis_Body(
           String strPointCode,
           String strDayTimeStrat,
           String strDayTimeEnd,
           String strHour,
           String strUserName,
           String strUserPwd) {
           this.strPointCode = strPointCode;
           this.strDayTimeStrat = strDayTimeStrat;
           this.strDayTimeEnd = strDayTimeEnd;
           this.strHour = strHour;
           this.strUserName = strUserName;
           this.strUserPwd = strUserPwd;
    }


    /**
     * Gets the strPointCode value for this GetDynamicAnalysis_Body.
     *
     * @return strPointCode
     */
    public String getStrPointCode() {
        return strPointCode;
    }


    /**
     * Sets the strPointCode value for this GetDynamicAnalysis_Body.
     *
     * @param strPointCode
     */
    public void setStrPointCode(String strPointCode) {
        this.strPointCode = strPointCode;
    }


    /**
     * Gets the strDayTimeStrat value for this GetDynamicAnalysis_Body.
     *
     * @return strDayTimeStrat
     */
    public String getStrDayTimeStrat() {
        return strDayTimeStrat;
    }


    /**
     * Sets the strDayTimeStrat value for this GetDynamicAnalysis_Body.
     *
     * @param strDayTimeStrat
     */
    public void setStrDayTimeStrat(String strDayTimeStrat) {
        this.strDayTimeStrat = strDayTimeStrat;
    }


    /**
     * Gets the strDayTimeEnd value for this GetDynamicAnalysis_Body.
     *
     * @return strDayTimeEnd
     */
    public String getStrDayTimeEnd() {
        return strDayTimeEnd;
    }


    /**
     * Sets the strDayTimeEnd value for this GetDynamicAnalysis_Body.
     *
     * @param strDayTimeEnd
     */
    public void setStrDayTimeEnd(String strDayTimeEnd) {
        this.strDayTimeEnd = strDayTimeEnd;
    }


    /**
     * Gets the strHour value for this GetDynamicAnalysis_Body.
     *
     * @return strHour
     */
    public String getStrHour() {
        return strHour;
    }


    /**
     * Sets the strHour value for this GetDynamicAnalysis_Body.
     *
     * @param strHour
     */
    public void setStrHour(String strHour) {
        this.strHour = strHour;
    }


    /**
     * Gets the strUserName value for this GetDynamicAnalysis_Body.
     *
     * @return strUserName
     */
    public String getStrUserName() {
        return strUserName;
    }


    /**
     * Sets the strUserName value for this GetDynamicAnalysis_Body.
     *
     * @param strUserName
     */
    public void setStrUserName(String strUserName) {
        this.strUserName = strUserName;
    }


    /**
     * Gets the strUserPwd value for this GetDynamicAnalysis_Body.
     *
     * @return strUserPwd
     */
    public String getStrUserPwd() {
        return strUserPwd;
    }


    /**
     * Sets the strUserPwd value for this GetDynamicAnalysis_Body.
     *
     * @param strUserPwd
     */
    public void setStrUserPwd(String strUserPwd) {
        this.strUserPwd = strUserPwd;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof GetDynamicAnalysis_Body)) return false;
        GetDynamicAnalysis_Body other = (GetDynamicAnalysis_Body) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            ((this.strPointCode==null && other.getStrPointCode()==null) ||
             (this.strPointCode!=null &&
              this.strPointCode.equals(other.getStrPointCode()))) &&
            ((this.strDayTimeStrat==null && other.getStrDayTimeStrat()==null) ||
             (this.strDayTimeStrat!=null &&
              this.strDayTimeStrat.equals(other.getStrDayTimeStrat()))) &&
            ((this.strDayTimeEnd==null && other.getStrDayTimeEnd()==null) ||
             (this.strDayTimeEnd!=null &&
              this.strDayTimeEnd.equals(other.getStrDayTimeEnd()))) &&
            ((this.strHour==null && other.getStrHour()==null) ||
             (this.strHour!=null &&
              this.strHour.equals(other.getStrHour()))) &&
            ((this.strUserName==null && other.getStrUserName()==null) ||
             (this.strUserName!=null &&
              this.strUserName.equals(other.getStrUserName()))) &&
            ((this.strUserPwd==null && other.getStrUserPwd()==null) ||
             (this.strUserPwd!=null &&
              this.strUserPwd.equals(other.getStrUserPwd())));
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
        if (getStrPointCode() != null) {
            _hashCode += getStrPointCode().hashCode();
        }
        if (getStrDayTimeStrat() != null) {
            _hashCode += getStrDayTimeStrat().hashCode();
        }
        if (getStrDayTimeEnd() != null) {
            _hashCode += getStrDayTimeEnd().hashCode();
        }
        if (getStrHour() != null) {
            _hashCode += getStrHour().hashCode();
        }
        if (getStrUserName() != null) {
            _hashCode += getStrUserName().hashCode();
        }
        if (getStrUserPwd() != null) {
            _hashCode += getStrUserPwd().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetDynamicAnalysis_Body.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">GetDynamicAnalysis_Body"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("strPointCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "strPointCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("strDayTimeStrat");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "strDayTimeStrat"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("strDayTimeEnd");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "strDayTimeEnd"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("strHour");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "strHour"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
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
