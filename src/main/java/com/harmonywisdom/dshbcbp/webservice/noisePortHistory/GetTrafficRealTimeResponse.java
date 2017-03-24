/**
 * GetTrafficRealTimeResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.harmonywisdom.dshbcbp.webservice.noisePortHistory;

public class GetTrafficRealTimeResponse  implements java.io.Serializable {
    private GetTrafficRealTimeResponseGetTrafficRealTimeResult getTrafficRealTimeResult;

    public GetTrafficRealTimeResponse() {
    }

    public GetTrafficRealTimeResponse(
           GetTrafficRealTimeResponseGetTrafficRealTimeResult getTrafficRealTimeResult) {
           this.getTrafficRealTimeResult = getTrafficRealTimeResult;
    }


    /**
     * Gets the getTrafficRealTimeResult value for this GetTrafficRealTimeResponse.
     * 
     * @return getTrafficRealTimeResult
     */
    public GetTrafficRealTimeResponseGetTrafficRealTimeResult getGetTrafficRealTimeResult() {
        return getTrafficRealTimeResult;
    }


    /**
     * Sets the getTrafficRealTimeResult value for this GetTrafficRealTimeResponse.
     * 
     * @param getTrafficRealTimeResult
     */
    public void setGetTrafficRealTimeResult(GetTrafficRealTimeResponseGetTrafficRealTimeResult getTrafficRealTimeResult) {
        this.getTrafficRealTimeResult = getTrafficRealTimeResult;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof GetTrafficRealTimeResponse)) return false;
        GetTrafficRealTimeResponse other = (GetTrafficRealTimeResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            ((this.getTrafficRealTimeResult==null && other.getGetTrafficRealTimeResult()==null) ||
             (this.getTrafficRealTimeResult!=null &&
              this.getTrafficRealTimeResult.equals(other.getGetTrafficRealTimeResult())));
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
        if (getGetTrafficRealTimeResult() != null) {
            _hashCode += getGetTrafficRealTimeResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetTrafficRealTimeResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">GetTrafficRealTimeResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("getTrafficRealTimeResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "GetTrafficRealTimeResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">>GetTrafficRealTimeResponse>GetTrafficRealTimeResult"));
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
