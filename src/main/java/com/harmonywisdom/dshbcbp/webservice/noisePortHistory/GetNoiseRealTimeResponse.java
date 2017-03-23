/**
 * GetNoiseRealTimeResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.harmonywisdom.dshbcbp.webservice.noisePortHistory;

public class GetNoiseRealTimeResponse  implements java.io.Serializable {
    private GetNoiseRealTimeResponseGetNoiseRealTimeResult getNoiseRealTimeResult;

    public GetNoiseRealTimeResponse() {
    }

    public GetNoiseRealTimeResponse(
           GetNoiseRealTimeResponseGetNoiseRealTimeResult getNoiseRealTimeResult) {
           this.getNoiseRealTimeResult = getNoiseRealTimeResult;
    }


    /**
     * Gets the getNoiseRealTimeResult value for this GetNoiseRealTimeResponse.
     * 
     * @return getNoiseRealTimeResult
     */
    public GetNoiseRealTimeResponseGetNoiseRealTimeResult getGetNoiseRealTimeResult() {
        return getNoiseRealTimeResult;
    }


    /**
     * Sets the getNoiseRealTimeResult value for this GetNoiseRealTimeResponse.
     * 
     * @param getNoiseRealTimeResult
     */
    public void setGetNoiseRealTimeResult(GetNoiseRealTimeResponseGetNoiseRealTimeResult getNoiseRealTimeResult) {
        this.getNoiseRealTimeResult = getNoiseRealTimeResult;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof GetNoiseRealTimeResponse)) return false;
        GetNoiseRealTimeResponse other = (GetNoiseRealTimeResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            ((this.getNoiseRealTimeResult==null && other.getGetNoiseRealTimeResult()==null) ||
             (this.getNoiseRealTimeResult!=null &&
              this.getNoiseRealTimeResult.equals(other.getGetNoiseRealTimeResult())));
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
        if (getGetNoiseRealTimeResult() != null) {
            _hashCode += getGetNoiseRealTimeResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetNoiseRealTimeResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">GetNoiseRealTimeResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("getNoiseRealTimeResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "GetNoiseRealTimeResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">>GetNoiseRealTimeResponse>GetNoiseRealTimeResult"));
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
