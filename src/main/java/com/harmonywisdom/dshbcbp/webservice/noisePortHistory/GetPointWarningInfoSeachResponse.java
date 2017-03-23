/**
 * GetPointWarningInfoSeachResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.harmonywisdom.dshbcbp.webservice.noisePortHistory;

public class GetPointWarningInfoSeachResponse  implements java.io.Serializable {
    private GetPointWarningInfoSeachResponseGetPointWarningInfoSeachResult getPointWarningInfoSeachResult;

    public GetPointWarningInfoSeachResponse() {
    }

    public GetPointWarningInfoSeachResponse(
           GetPointWarningInfoSeachResponseGetPointWarningInfoSeachResult getPointWarningInfoSeachResult) {
           this.getPointWarningInfoSeachResult = getPointWarningInfoSeachResult;
    }


    /**
     * Gets the getPointWarningInfoSeachResult value for this GetPointWarningInfoSeachResponse.
     * 
     * @return getPointWarningInfoSeachResult
     */
    public GetPointWarningInfoSeachResponseGetPointWarningInfoSeachResult getGetPointWarningInfoSeachResult() {
        return getPointWarningInfoSeachResult;
    }


    /**
     * Sets the getPointWarningInfoSeachResult value for this GetPointWarningInfoSeachResponse.
     * 
     * @param getPointWarningInfoSeachResult
     */
    public void setGetPointWarningInfoSeachResult(GetPointWarningInfoSeachResponseGetPointWarningInfoSeachResult getPointWarningInfoSeachResult) {
        this.getPointWarningInfoSeachResult = getPointWarningInfoSeachResult;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof GetPointWarningInfoSeachResponse)) return false;
        GetPointWarningInfoSeachResponse other = (GetPointWarningInfoSeachResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            ((this.getPointWarningInfoSeachResult==null && other.getGetPointWarningInfoSeachResult()==null) ||
             (this.getPointWarningInfoSeachResult!=null &&
              this.getPointWarningInfoSeachResult.equals(other.getGetPointWarningInfoSeachResult())));
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
        if (getGetPointWarningInfoSeachResult() != null) {
            _hashCode += getGetPointWarningInfoSeachResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetPointWarningInfoSeachResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">GetPointWarningInfoSeachResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("getPointWarningInfoSeachResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "GetPointWarningInfoSeachResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">>GetPointWarningInfoSeachResponse>GetPointWarningInfoSeachResult"));
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
