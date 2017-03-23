/**
 * GetSinglePointMonthReport_HeadResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.harmonywisdom.dshbcbp.webservice.noisePortHistory;

public class GetSinglePointMonthReport_HeadResponse  implements java.io.Serializable {
    private GetSinglePointMonthReport_HeadResponseGetSinglePointMonthReport_HeadResult getSinglePointMonthReport_HeadResult;

    public GetSinglePointMonthReport_HeadResponse() {
    }

    public GetSinglePointMonthReport_HeadResponse(
           GetSinglePointMonthReport_HeadResponseGetSinglePointMonthReport_HeadResult getSinglePointMonthReport_HeadResult) {
           this.getSinglePointMonthReport_HeadResult = getSinglePointMonthReport_HeadResult;
    }


    /**
     * Gets the getSinglePointMonthReport_HeadResult value for this GetSinglePointMonthReport_HeadResponse.
     * 
     * @return getSinglePointMonthReport_HeadResult
     */
    public GetSinglePointMonthReport_HeadResponseGetSinglePointMonthReport_HeadResult getGetSinglePointMonthReport_HeadResult() {
        return getSinglePointMonthReport_HeadResult;
    }


    /**
     * Sets the getSinglePointMonthReport_HeadResult value for this GetSinglePointMonthReport_HeadResponse.
     * 
     * @param getSinglePointMonthReport_HeadResult
     */
    public void setGetSinglePointMonthReport_HeadResult(GetSinglePointMonthReport_HeadResponseGetSinglePointMonthReport_HeadResult getSinglePointMonthReport_HeadResult) {
        this.getSinglePointMonthReport_HeadResult = getSinglePointMonthReport_HeadResult;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof GetSinglePointMonthReport_HeadResponse)) return false;
        GetSinglePointMonthReport_HeadResponse other = (GetSinglePointMonthReport_HeadResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            ((this.getSinglePointMonthReport_HeadResult==null && other.getGetSinglePointMonthReport_HeadResult()==null) ||
             (this.getSinglePointMonthReport_HeadResult!=null &&
              this.getSinglePointMonthReport_HeadResult.equals(other.getGetSinglePointMonthReport_HeadResult())));
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
        if (getGetSinglePointMonthReport_HeadResult() != null) {
            _hashCode += getGetSinglePointMonthReport_HeadResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetSinglePointMonthReport_HeadResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">GetSinglePointMonthReport_HeadResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("getSinglePointMonthReport_HeadResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "GetSinglePointMonthReport_HeadResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">>GetSinglePointMonthReport_HeadResponse>GetSinglePointMonthReport_HeadResult"));
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
