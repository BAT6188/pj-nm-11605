/**
 * GetUserInfoDataResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.harmonywisdom.dshbcbp.webservice.noisePortHistory;

public class GetUserInfoDataResponse  implements java.io.Serializable {
    private TSysUserVo getUserInfoDataResult;

    public GetUserInfoDataResponse() {
    }

    public GetUserInfoDataResponse(
           TSysUserVo getUserInfoDataResult) {
           this.getUserInfoDataResult = getUserInfoDataResult;
    }


    /**
     * Gets the getUserInfoDataResult value for this GetUserInfoDataResponse.
     * 
     * @return getUserInfoDataResult
     */
    public TSysUserVo getGetUserInfoDataResult() {
        return getUserInfoDataResult;
    }


    /**
     * Sets the getUserInfoDataResult value for this GetUserInfoDataResponse.
     * 
     * @param getUserInfoDataResult
     */
    public void setGetUserInfoDataResult(TSysUserVo getUserInfoDataResult) {
        this.getUserInfoDataResult = getUserInfoDataResult;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof GetUserInfoDataResponse)) return false;
        GetUserInfoDataResponse other = (GetUserInfoDataResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            ((this.getUserInfoDataResult==null && other.getGetUserInfoDataResult()==null) ||
             (this.getUserInfoDataResult!=null &&
              this.getUserInfoDataResult.equals(other.getGetUserInfoDataResult())));
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
        if (getGetUserInfoDataResult() != null) {
            _hashCode += getGetUserInfoDataResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetUserInfoDataResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">GetUserInfoDataResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("getUserInfoDataResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "GetUserInfoDataResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", "TSysUserVo"));
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
