/**
 * GetAllPointStataResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.harmonywisdom.dshbcbp.webservice.noisePortHistory;

public class GetAllPointStataResponse  implements java.io.Serializable {
    private GetAllPointStataResponseGetAllPointStataResult getAllPointStataResult;

    public GetAllPointStataResponse() {
    }

    public GetAllPointStataResponse(
           GetAllPointStataResponseGetAllPointStataResult getAllPointStataResult) {
           this.getAllPointStataResult = getAllPointStataResult;
    }


    /**
     * Gets the getAllPointStataResult value for this GetAllPointStataResponse.
     * 
     * @return getAllPointStataResult
     */
    public GetAllPointStataResponseGetAllPointStataResult getGetAllPointStataResult() {
        return getAllPointStataResult;
    }


    /**
     * Sets the getAllPointStataResult value for this GetAllPointStataResponse.
     * 
     * @param getAllPointStataResult
     */
    public void setGetAllPointStataResult(GetAllPointStataResponseGetAllPointStataResult getAllPointStataResult) {
        this.getAllPointStataResult = getAllPointStataResult;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof GetAllPointStataResponse)) return false;
        GetAllPointStataResponse other = (GetAllPointStataResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            ((this.getAllPointStataResult==null && other.getGetAllPointStataResult()==null) ||
             (this.getAllPointStataResult!=null &&
              this.getAllPointStataResult.equals(other.getGetAllPointStataResult())));
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
        if (getGetAllPointStataResult() != null) {
            _hashCode += getGetAllPointStataResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetAllPointStataResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">GetAllPointStataResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("getAllPointStataResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "GetAllPointStataResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">>GetAllPointStataResponse>GetAllPointStataResult"));
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
