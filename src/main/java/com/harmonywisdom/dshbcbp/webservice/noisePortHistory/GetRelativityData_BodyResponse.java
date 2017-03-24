/**
 * GetRelativityData_BodyResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.harmonywisdom.dshbcbp.webservice.noisePortHistory;

public class GetRelativityData_BodyResponse  implements java.io.Serializable {
    private GetRelativityData_BodyResponseGetRelativityData_BodyResult getRelativityData_BodyResult;

    public GetRelativityData_BodyResponse() {
    }

    public GetRelativityData_BodyResponse(
           GetRelativityData_BodyResponseGetRelativityData_BodyResult getRelativityData_BodyResult) {
           this.getRelativityData_BodyResult = getRelativityData_BodyResult;
    }


    /**
     * Gets the getRelativityData_BodyResult value for this GetRelativityData_BodyResponse.
     * 
     * @return getRelativityData_BodyResult
     */
    public GetRelativityData_BodyResponseGetRelativityData_BodyResult getGetRelativityData_BodyResult() {
        return getRelativityData_BodyResult;
    }


    /**
     * Sets the getRelativityData_BodyResult value for this GetRelativityData_BodyResponse.
     * 
     * @param getRelativityData_BodyResult
     */
    public void setGetRelativityData_BodyResult(GetRelativityData_BodyResponseGetRelativityData_BodyResult getRelativityData_BodyResult) {
        this.getRelativityData_BodyResult = getRelativityData_BodyResult;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof GetRelativityData_BodyResponse)) return false;
        GetRelativityData_BodyResponse other = (GetRelativityData_BodyResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            ((this.getRelativityData_BodyResult==null && other.getGetRelativityData_BodyResult()==null) ||
             (this.getRelativityData_BodyResult!=null &&
              this.getRelativityData_BodyResult.equals(other.getGetRelativityData_BodyResult())));
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
        if (getGetRelativityData_BodyResult() != null) {
            _hashCode += getGetRelativityData_BodyResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetRelativityData_BodyResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">GetRelativityData_BodyResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("getRelativityData_BodyResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "GetRelativityData_BodyResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">>GetRelativityData_BodyResponse>GetRelativityData_BodyResult"));
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
