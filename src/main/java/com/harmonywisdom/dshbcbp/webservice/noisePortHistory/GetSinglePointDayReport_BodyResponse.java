/**
 * GetSinglePointDayReport_BodyResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.harmonywisdom.dshbcbp.webservice.noisePortHistory;

public class GetSinglePointDayReport_BodyResponse  implements java.io.Serializable {
    private GetSinglePointDayReport_BodyResponseGetSinglePointDayReport_BodyResult getSinglePointDayReport_BodyResult;

    public GetSinglePointDayReport_BodyResponse() {
    }

    public GetSinglePointDayReport_BodyResponse(
           GetSinglePointDayReport_BodyResponseGetSinglePointDayReport_BodyResult getSinglePointDayReport_BodyResult) {
           this.getSinglePointDayReport_BodyResult = getSinglePointDayReport_BodyResult;
    }


    /**
     * Gets the getSinglePointDayReport_BodyResult value for this GetSinglePointDayReport_BodyResponse.
     * 
     * @return getSinglePointDayReport_BodyResult
     */
    public GetSinglePointDayReport_BodyResponseGetSinglePointDayReport_BodyResult getGetSinglePointDayReport_BodyResult() {
        return getSinglePointDayReport_BodyResult;
    }


    /**
     * Sets the getSinglePointDayReport_BodyResult value for this GetSinglePointDayReport_BodyResponse.
     * 
     * @param getSinglePointDayReport_BodyResult
     */
    public void setGetSinglePointDayReport_BodyResult(GetSinglePointDayReport_BodyResponseGetSinglePointDayReport_BodyResult getSinglePointDayReport_BodyResult) {
        this.getSinglePointDayReport_BodyResult = getSinglePointDayReport_BodyResult;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof GetSinglePointDayReport_BodyResponse)) return false;
        GetSinglePointDayReport_BodyResponse other = (GetSinglePointDayReport_BodyResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            ((this.getSinglePointDayReport_BodyResult==null && other.getGetSinglePointDayReport_BodyResult()==null) ||
             (this.getSinglePointDayReport_BodyResult!=null &&
              this.getSinglePointDayReport_BodyResult.equals(other.getGetSinglePointDayReport_BodyResult())));
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
        if (getGetSinglePointDayReport_BodyResult() != null) {
            _hashCode += getGetSinglePointDayReport_BodyResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetSinglePointDayReport_BodyResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">GetSinglePointDayReport_BodyResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("getSinglePointDayReport_BodyResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "GetSinglePointDayReport_BodyResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">>GetSinglePointDayReport_BodyResponse>GetSinglePointDayReport_BodyResult"));
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
