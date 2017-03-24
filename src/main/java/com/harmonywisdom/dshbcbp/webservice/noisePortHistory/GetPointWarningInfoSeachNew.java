/**
 * GetPointWarningInfoSeachNew.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.harmonywisdom.dshbcbp.webservice.noisePortHistory;

public class GetPointWarningInfoSeachNew  implements java.io.Serializable {
    private String strPointCodes;

    private String strDayTimeStrat;

    private String strDayTimeEnd;

    private int iPage;

    private int iRows;

    private String strUserName;

    private String strUserPwd;

    public GetPointWarningInfoSeachNew() {
    }

    public GetPointWarningInfoSeachNew(
           String strPointCodes,
           String strDayTimeStrat,
           String strDayTimeEnd,
           int iPage,
           int iRows,
           String strUserName,
           String strUserPwd) {
           this.strPointCodes = strPointCodes;
           this.strDayTimeStrat = strDayTimeStrat;
           this.strDayTimeEnd = strDayTimeEnd;
           this.iPage = iPage;
           this.iRows = iRows;
           this.strUserName = strUserName;
           this.strUserPwd = strUserPwd;
    }


    /**
     * Gets the strPointCodes value for this GetPointWarningInfoSeachNew.
     *
     * @return strPointCodes
     */
    public String getStrPointCodes() {
        return strPointCodes;
    }


    /**
     * Sets the strPointCodes value for this GetPointWarningInfoSeachNew.
     *
     * @param strPointCodes
     */
    public void setStrPointCodes(String strPointCodes) {
        this.strPointCodes = strPointCodes;
    }


    /**
     * Gets the strDayTimeStrat value for this GetPointWarningInfoSeachNew.
     *
     * @return strDayTimeStrat
     */
    public String getStrDayTimeStrat() {
        return strDayTimeStrat;
    }


    /**
     * Sets the strDayTimeStrat value for this GetPointWarningInfoSeachNew.
     *
     * @param strDayTimeStrat
     */
    public void setStrDayTimeStrat(String strDayTimeStrat) {
        this.strDayTimeStrat = strDayTimeStrat;
    }


    /**
     * Gets the strDayTimeEnd value for this GetPointWarningInfoSeachNew.
     *
     * @return strDayTimeEnd
     */
    public String getStrDayTimeEnd() {
        return strDayTimeEnd;
    }


    /**
     * Sets the strDayTimeEnd value for this GetPointWarningInfoSeachNew.
     *
     * @param strDayTimeEnd
     */
    public void setStrDayTimeEnd(String strDayTimeEnd) {
        this.strDayTimeEnd = strDayTimeEnd;
    }


    /**
     * Gets the iPage value for this GetPointWarningInfoSeachNew.
     *
     * @return iPage
     */
    public int getIPage() {
        return iPage;
    }


    /**
     * Sets the iPage value for this GetPointWarningInfoSeachNew.
     *
     * @param iPage
     */
    public void setIPage(int iPage) {
        this.iPage = iPage;
    }


    /**
     * Gets the iRows value for this GetPointWarningInfoSeachNew.
     *
     * @return iRows
     */
    public int getIRows() {
        return iRows;
    }


    /**
     * Sets the iRows value for this GetPointWarningInfoSeachNew.
     *
     * @param iRows
     */
    public void setIRows(int iRows) {
        this.iRows = iRows;
    }


    /**
     * Gets the strUserName value for this GetPointWarningInfoSeachNew.
     *
     * @return strUserName
     */
    public String getStrUserName() {
        return strUserName;
    }


    /**
     * Sets the strUserName value for this GetPointWarningInfoSeachNew.
     *
     * @param strUserName
     */
    public void setStrUserName(String strUserName) {
        this.strUserName = strUserName;
    }


    /**
     * Gets the strUserPwd value for this GetPointWarningInfoSeachNew.
     *
     * @return strUserPwd
     */
    public String getStrUserPwd() {
        return strUserPwd;
    }


    /**
     * Sets the strUserPwd value for this GetPointWarningInfoSeachNew.
     *
     * @param strUserPwd
     */
    public void setStrUserPwd(String strUserPwd) {
        this.strUserPwd = strUserPwd;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof GetPointWarningInfoSeachNew)) return false;
        GetPointWarningInfoSeachNew other = (GetPointWarningInfoSeachNew) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            ((this.strPointCodes==null && other.getStrPointCodes()==null) ||
             (this.strPointCodes!=null &&
              this.strPointCodes.equals(other.getStrPointCodes()))) &&
            ((this.strDayTimeStrat==null && other.getStrDayTimeStrat()==null) ||
             (this.strDayTimeStrat!=null &&
              this.strDayTimeStrat.equals(other.getStrDayTimeStrat()))) &&
            ((this.strDayTimeEnd==null && other.getStrDayTimeEnd()==null) ||
             (this.strDayTimeEnd!=null &&
              this.strDayTimeEnd.equals(other.getStrDayTimeEnd()))) &&
            this.iPage == other.getIPage() &&
            this.iRows == other.getIRows() &&
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
        if (getStrPointCodes() != null) {
            _hashCode += getStrPointCodes().hashCode();
        }
        if (getStrDayTimeStrat() != null) {
            _hashCode += getStrDayTimeStrat().hashCode();
        }
        if (getStrDayTimeEnd() != null) {
            _hashCode += getStrDayTimeEnd().hashCode();
        }
        _hashCode += getIPage();
        _hashCode += getIRows();
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
        new org.apache.axis.description.TypeDesc(GetPointWarningInfoSeachNew.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">GetPointWarningInfoSeachNew"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("strPointCodes");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "strPointCodes"));
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
        elemField.setFieldName("IPage");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "iPage"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("IRows");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "iRows"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
