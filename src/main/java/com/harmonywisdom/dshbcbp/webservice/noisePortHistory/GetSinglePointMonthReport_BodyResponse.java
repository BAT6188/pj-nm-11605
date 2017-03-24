/**
 * GetSinglePointMonthReport_BodyResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.harmonywisdom.dshbcbp.webservice.noisePortHistory;

public class GetSinglePointMonthReport_BodyResponse  implements java.io.Serializable {
    private GetSinglePointMonthReport_BodyResponseGetSinglePointMonthReport_BodyResult getSinglePointMonthReport_BodyResult;

    public GetSinglePointMonthReport_BodyResponse() {
    }

    public GetSinglePointMonthReport_BodyResponse(
           GetSinglePointMonthReport_BodyResponseGetSinglePointMonthReport_BodyResult getSinglePointMonthReport_BodyResult) {
           this.getSinglePointMonthReport_BodyResult = getSinglePointMonthReport_BodyResult;
    }


    /**
     * Gets the getSinglePointMonthReport_BodyResult value for this GetSinglePointMonthReport_BodyResponse.
     * 
     * @return getSinglePointMonthReport_BodyResult
     */
    public GetSinglePointMonthReport_BodyResponseGetSinglePointMonthReport_BodyResult getGetSinglePointMonthReport_BodyResult() {
        return getSinglePointMonthReport_BodyResult;
    }


    /**
     * Sets the getSinglePointMonthReport_BodyResult value for this GetSinglePointMonthReport_BodyResponse.
     * 
     * @param getSinglePointMonthReport_BodyResult
     */
    public void setGetSinglePointMonthReport_BodyResult(GetSinglePointMonthReport_BodyResponseGetSinglePointMonthReport_BodyResult getSinglePointMonthReport_BodyResult) {
        this.getSinglePointMonthReport_BodyResult = getSinglePointMonthReport_BodyResult;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof GetSinglePointMonthReport_BodyResponse)) return false;
        GetSinglePointMonthReport_BodyResponse other = (GetSinglePointMonthReport_BodyResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            ((this.getSinglePointMonthReport_BodyResult==null && other.getGetSinglePointMonthReport_BodyResult()==null) ||
             (this.getSinglePointMonthReport_BodyResult!=null &&
              this.getSinglePointMonthReport_BodyResult.equals(other.getGetSinglePointMonthReport_BodyResult())));
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
        if (getGetSinglePointMonthReport_BodyResult() != null) {
            _hashCode += getGetSinglePointMonthReport_BodyResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetSinglePointMonthReport_BodyResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">GetSinglePointMonthReport_BodyResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("getSinglePointMonthReport_BodyResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "GetSinglePointMonthReport_BodyResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">>GetSinglePointMonthReport_BodyResponse>GetSinglePointMonthReport_BodyResult"));
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
