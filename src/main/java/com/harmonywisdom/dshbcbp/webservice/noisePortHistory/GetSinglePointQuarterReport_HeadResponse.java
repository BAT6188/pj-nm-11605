/**
 * GetSinglePointQuarterReport_HeadResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.harmonywisdom.dshbcbp.webservice.noisePortHistory;

public class GetSinglePointQuarterReport_HeadResponse  implements java.io.Serializable {
    private GetSinglePointQuarterReport_HeadResponseGetSinglePointQuarterReport_HeadResult getSinglePointQuarterReport_HeadResult;

    public GetSinglePointQuarterReport_HeadResponse() {
    }

    public GetSinglePointQuarterReport_HeadResponse(
           GetSinglePointQuarterReport_HeadResponseGetSinglePointQuarterReport_HeadResult getSinglePointQuarterReport_HeadResult) {
           this.getSinglePointQuarterReport_HeadResult = getSinglePointQuarterReport_HeadResult;
    }


    /**
     * Gets the getSinglePointQuarterReport_HeadResult value for this GetSinglePointQuarterReport_HeadResponse.
     * 
     * @return getSinglePointQuarterReport_HeadResult
     */
    public GetSinglePointQuarterReport_HeadResponseGetSinglePointQuarterReport_HeadResult getGetSinglePointQuarterReport_HeadResult() {
        return getSinglePointQuarterReport_HeadResult;
    }


    /**
     * Sets the getSinglePointQuarterReport_HeadResult value for this GetSinglePointQuarterReport_HeadResponse.
     * 
     * @param getSinglePointQuarterReport_HeadResult
     */
    public void setGetSinglePointQuarterReport_HeadResult(GetSinglePointQuarterReport_HeadResponseGetSinglePointQuarterReport_HeadResult getSinglePointQuarterReport_HeadResult) {
        this.getSinglePointQuarterReport_HeadResult = getSinglePointQuarterReport_HeadResult;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof GetSinglePointQuarterReport_HeadResponse)) return false;
        GetSinglePointQuarterReport_HeadResponse other = (GetSinglePointQuarterReport_HeadResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            ((this.getSinglePointQuarterReport_HeadResult==null && other.getGetSinglePointQuarterReport_HeadResult()==null) ||
             (this.getSinglePointQuarterReport_HeadResult!=null &&
              this.getSinglePointQuarterReport_HeadResult.equals(other.getGetSinglePointQuarterReport_HeadResult())));
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
        if (getGetSinglePointQuarterReport_HeadResult() != null) {
            _hashCode += getGetSinglePointQuarterReport_HeadResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetSinglePointQuarterReport_HeadResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">GetSinglePointQuarterReport_HeadResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("getSinglePointQuarterReport_HeadResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "GetSinglePointQuarterReport_HeadResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">>GetSinglePointQuarterReport_HeadResponse>GetSinglePointQuarterReport_HeadResult"));
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
