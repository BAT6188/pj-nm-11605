/**
 * GetPointWarningInfoNumResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.harmonywisdom.dshbcbp.webservice.noisePortHistory;

public class GetPointWarningInfoNumResponse  implements java.io.Serializable {
    private int getPointWarningInfoNumResult;

    public GetPointWarningInfoNumResponse() {
    }

    public GetPointWarningInfoNumResponse(
           int getPointWarningInfoNumResult) {
           this.getPointWarningInfoNumResult = getPointWarningInfoNumResult;
    }


    /**
     * Gets the getPointWarningInfoNumResult value for this GetPointWarningInfoNumResponse.
     * 
     * @return getPointWarningInfoNumResult
     */
    public int getGetPointWarningInfoNumResult() {
        return getPointWarningInfoNumResult;
    }


    /**
     * Sets the getPointWarningInfoNumResult value for this GetPointWarningInfoNumResponse.
     * 
     * @param getPointWarningInfoNumResult
     */
    public void setGetPointWarningInfoNumResult(int getPointWarningInfoNumResult) {
        this.getPointWarningInfoNumResult = getPointWarningInfoNumResult;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof GetPointWarningInfoNumResponse)) return false;
        GetPointWarningInfoNumResponse other = (GetPointWarningInfoNumResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            this.getPointWarningInfoNumResult == other.getGetPointWarningInfoNumResult();
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
        _hashCode += getGetPointWarningInfoNumResult();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetPointWarningInfoNumResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">GetPointWarningInfoNumResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("getPointWarningInfoNumResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "GetPointWarningInfoNumResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
