/**
 * GetPointInfoDataResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.harmonywisdom.dshbcbp.webservice.noisePortHistory;

public class GetPointInfoDataResponse  implements java.io.Serializable {
    private TSysStationPointVo getPointInfoDataResult;

    public GetPointInfoDataResponse() {
    }

    public GetPointInfoDataResponse(
           TSysStationPointVo getPointInfoDataResult) {
           this.getPointInfoDataResult = getPointInfoDataResult;
    }


    /**
     * Gets the getPointInfoDataResult value for this GetPointInfoDataResponse.
     * 
     * @return getPointInfoDataResult
     */
    public TSysStationPointVo getGetPointInfoDataResult() {
        return getPointInfoDataResult;
    }


    /**
     * Sets the getPointInfoDataResult value for this GetPointInfoDataResponse.
     * 
     * @param getPointInfoDataResult
     */
    public void setGetPointInfoDataResult(TSysStationPointVo getPointInfoDataResult) {
        this.getPointInfoDataResult = getPointInfoDataResult;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof GetPointInfoDataResponse)) return false;
        GetPointInfoDataResponse other = (GetPointInfoDataResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            ((this.getPointInfoDataResult==null && other.getGetPointInfoDataResult()==null) ||
             (this.getPointInfoDataResult!=null &&
              this.getPointInfoDataResult.equals(other.getGetPointInfoDataResult())));
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
        if (getGetPointInfoDataResult() != null) {
            _hashCode += getGetPointInfoDataResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetPointInfoDataResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">GetPointInfoDataResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("getPointInfoDataResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "GetPointInfoDataResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", "TSysStationPointVo"));
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
