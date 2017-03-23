/**
 * GetDynamicAnalysis_BodyResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.harmonywisdom.dshbcbp.webservice.noisePortHistory;

public class GetDynamicAnalysis_BodyResponse  implements java.io.Serializable {
    private GetDynamicAnalysis_BodyResponseGetDynamicAnalysis_BodyResult getDynamicAnalysis_BodyResult;

    public GetDynamicAnalysis_BodyResponse() {
    }

    public GetDynamicAnalysis_BodyResponse(
           GetDynamicAnalysis_BodyResponseGetDynamicAnalysis_BodyResult getDynamicAnalysis_BodyResult) {
           this.getDynamicAnalysis_BodyResult = getDynamicAnalysis_BodyResult;
    }


    /**
     * Gets the getDynamicAnalysis_BodyResult value for this GetDynamicAnalysis_BodyResponse.
     * 
     * @return getDynamicAnalysis_BodyResult
     */
    public GetDynamicAnalysis_BodyResponseGetDynamicAnalysis_BodyResult getGetDynamicAnalysis_BodyResult() {
        return getDynamicAnalysis_BodyResult;
    }


    /**
     * Sets the getDynamicAnalysis_BodyResult value for this GetDynamicAnalysis_BodyResponse.
     * 
     * @param getDynamicAnalysis_BodyResult
     */
    public void setGetDynamicAnalysis_BodyResult(GetDynamicAnalysis_BodyResponseGetDynamicAnalysis_BodyResult getDynamicAnalysis_BodyResult) {
        this.getDynamicAnalysis_BodyResult = getDynamicAnalysis_BodyResult;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof GetDynamicAnalysis_BodyResponse)) return false;
        GetDynamicAnalysis_BodyResponse other = (GetDynamicAnalysis_BodyResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            ((this.getDynamicAnalysis_BodyResult==null && other.getGetDynamicAnalysis_BodyResult()==null) ||
             (this.getDynamicAnalysis_BodyResult!=null &&
              this.getDynamicAnalysis_BodyResult.equals(other.getGetDynamicAnalysis_BodyResult())));
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
        if (getGetDynamicAnalysis_BodyResult() != null) {
            _hashCode += getGetDynamicAnalysis_BodyResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetDynamicAnalysis_BodyResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">GetDynamicAnalysis_BodyResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("getDynamicAnalysis_BodyResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "GetDynamicAnalysis_BodyResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">>GetDynamicAnalysis_BodyResponse>GetDynamicAnalysis_BodyResult"));
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
