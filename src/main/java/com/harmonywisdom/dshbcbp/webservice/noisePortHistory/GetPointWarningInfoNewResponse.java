/**
 * GetPointWarningInfoNewResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.harmonywisdom.dshbcbp.webservice.noisePortHistory;

public class GetPointWarningInfoNewResponse  implements java.io.Serializable {
    private GetPointWarningInfoNewResponseGetPointWarningInfoNewResult getPointWarningInfoNewResult;

    public GetPointWarningInfoNewResponse() {
    }

    public GetPointWarningInfoNewResponse(
           GetPointWarningInfoNewResponseGetPointWarningInfoNewResult getPointWarningInfoNewResult) {
           this.getPointWarningInfoNewResult = getPointWarningInfoNewResult;
    }


    /**
     * Gets the getPointWarningInfoNewResult value for this GetPointWarningInfoNewResponse.
     * 
     * @return getPointWarningInfoNewResult
     */
    public GetPointWarningInfoNewResponseGetPointWarningInfoNewResult getGetPointWarningInfoNewResult() {
        return getPointWarningInfoNewResult;
    }


    /**
     * Sets the getPointWarningInfoNewResult value for this GetPointWarningInfoNewResponse.
     * 
     * @param getPointWarningInfoNewResult
     */
    public void setGetPointWarningInfoNewResult(GetPointWarningInfoNewResponseGetPointWarningInfoNewResult getPointWarningInfoNewResult) {
        this.getPointWarningInfoNewResult = getPointWarningInfoNewResult;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof GetPointWarningInfoNewResponse)) return false;
        GetPointWarningInfoNewResponse other = (GetPointWarningInfoNewResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            ((this.getPointWarningInfoNewResult==null && other.getGetPointWarningInfoNewResult()==null) ||
             (this.getPointWarningInfoNewResult!=null &&
              this.getPointWarningInfoNewResult.equals(other.getGetPointWarningInfoNewResult())));
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
        if (getGetPointWarningInfoNewResult() != null) {
            _hashCode += getGetPointWarningInfoNewResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetPointWarningInfoNewResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">GetPointWarningInfoNewResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("getPointWarningInfoNewResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "GetPointWarningInfoNewResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">>GetPointWarningInfoNewResponse>GetPointWarningInfoNewResult"));
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
