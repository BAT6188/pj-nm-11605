/**
 * GetSinglePointDayReport_HeadResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.harmonywisdom.dshbcbp.webservice.noisePortHistory;

public class GetSinglePointDayReport_HeadResponse  implements java.io.Serializable {
    private GetSinglePointDayReport_HeadResponseGetSinglePointDayReport_HeadResult getSinglePointDayReport_HeadResult;

    public GetSinglePointDayReport_HeadResponse() {
    }

    public GetSinglePointDayReport_HeadResponse(
           GetSinglePointDayReport_HeadResponseGetSinglePointDayReport_HeadResult getSinglePointDayReport_HeadResult) {
           this.getSinglePointDayReport_HeadResult = getSinglePointDayReport_HeadResult;
    }


    /**
     * Gets the getSinglePointDayReport_HeadResult value for this GetSinglePointDayReport_HeadResponse.
     * 
     * @return getSinglePointDayReport_HeadResult
     */
    public GetSinglePointDayReport_HeadResponseGetSinglePointDayReport_HeadResult getGetSinglePointDayReport_HeadResult() {
        return getSinglePointDayReport_HeadResult;
    }


    /**
     * Sets the getSinglePointDayReport_HeadResult value for this GetSinglePointDayReport_HeadResponse.
     * 
     * @param getSinglePointDayReport_HeadResult
     */
    public void setGetSinglePointDayReport_HeadResult(GetSinglePointDayReport_HeadResponseGetSinglePointDayReport_HeadResult getSinglePointDayReport_HeadResult) {
        this.getSinglePointDayReport_HeadResult = getSinglePointDayReport_HeadResult;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof GetSinglePointDayReport_HeadResponse)) return false;
        GetSinglePointDayReport_HeadResponse other = (GetSinglePointDayReport_HeadResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            ((this.getSinglePointDayReport_HeadResult==null && other.getGetSinglePointDayReport_HeadResult()==null) ||
             (this.getSinglePointDayReport_HeadResult!=null &&
              this.getSinglePointDayReport_HeadResult.equals(other.getGetSinglePointDayReport_HeadResult())));
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
        if (getGetSinglePointDayReport_HeadResult() != null) {
            _hashCode += getGetSinglePointDayReport_HeadResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetSinglePointDayReport_HeadResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">GetSinglePointDayReport_HeadResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("getSinglePointDayReport_HeadResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "GetSinglePointDayReport_HeadResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">>GetSinglePointDayReport_HeadResponse>GetSinglePointDayReport_HeadResult"));
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
