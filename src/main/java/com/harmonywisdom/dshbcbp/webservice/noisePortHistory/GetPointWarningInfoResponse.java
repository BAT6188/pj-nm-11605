/**
 * GetPointWarningInfoResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.harmonywisdom.dshbcbp.webservice.noisePortHistory;

public class GetPointWarningInfoResponse  implements java.io.Serializable {
    private GetPointWarningInfoResponseGetPointWarningInfoResult getPointWarningInfoResult;

    public GetPointWarningInfoResponse() {
    }

    public GetPointWarningInfoResponse(
           GetPointWarningInfoResponseGetPointWarningInfoResult getPointWarningInfoResult) {
           this.getPointWarningInfoResult = getPointWarningInfoResult;
    }


    /**
     * Gets the getPointWarningInfoResult value for this GetPointWarningInfoResponse.
     * 
     * @return getPointWarningInfoResult
     */
    public GetPointWarningInfoResponseGetPointWarningInfoResult getGetPointWarningInfoResult() {
        return getPointWarningInfoResult;
    }


    /**
     * Sets the getPointWarningInfoResult value for this GetPointWarningInfoResponse.
     * 
     * @param getPointWarningInfoResult
     */
    public void setGetPointWarningInfoResult(GetPointWarningInfoResponseGetPointWarningInfoResult getPointWarningInfoResult) {
        this.getPointWarningInfoResult = getPointWarningInfoResult;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof GetPointWarningInfoResponse)) return false;
        GetPointWarningInfoResponse other = (GetPointWarningInfoResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            ((this.getPointWarningInfoResult==null && other.getGetPointWarningInfoResult()==null) ||
             (this.getPointWarningInfoResult!=null &&
              this.getPointWarningInfoResult.equals(other.getGetPointWarningInfoResult())));
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
        if (getGetPointWarningInfoResult() != null) {
            _hashCode += getGetPointWarningInfoResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetPointWarningInfoResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">GetPointWarningInfoResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("getPointWarningInfoResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "GetPointWarningInfoResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">>GetPointWarningInfoResponse>GetPointWarningInfoResult"));
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
