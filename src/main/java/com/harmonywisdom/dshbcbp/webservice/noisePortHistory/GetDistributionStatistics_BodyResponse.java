/**
 * GetDistributionStatistics_BodyResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.harmonywisdom.dshbcbp.webservice.noisePortHistory;

public class GetDistributionStatistics_BodyResponse  implements java.io.Serializable {
    private GetDistributionStatistics_BodyResponseGetDistributionStatistics_BodyResult getDistributionStatistics_BodyResult;

    public GetDistributionStatistics_BodyResponse() {
    }

    public GetDistributionStatistics_BodyResponse(
           GetDistributionStatistics_BodyResponseGetDistributionStatistics_BodyResult getDistributionStatistics_BodyResult) {
           this.getDistributionStatistics_BodyResult = getDistributionStatistics_BodyResult;
    }


    /**
     * Gets the getDistributionStatistics_BodyResult value for this GetDistributionStatistics_BodyResponse.
     * 
     * @return getDistributionStatistics_BodyResult
     */
    public GetDistributionStatistics_BodyResponseGetDistributionStatistics_BodyResult getGetDistributionStatistics_BodyResult() {
        return getDistributionStatistics_BodyResult;
    }


    /**
     * Sets the getDistributionStatistics_BodyResult value for this GetDistributionStatistics_BodyResponse.
     * 
     * @param getDistributionStatistics_BodyResult
     */
    public void setGetDistributionStatistics_BodyResult(GetDistributionStatistics_BodyResponseGetDistributionStatistics_BodyResult getDistributionStatistics_BodyResult) {
        this.getDistributionStatistics_BodyResult = getDistributionStatistics_BodyResult;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof GetDistributionStatistics_BodyResponse)) return false;
        GetDistributionStatistics_BodyResponse other = (GetDistributionStatistics_BodyResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            ((this.getDistributionStatistics_BodyResult==null && other.getGetDistributionStatistics_BodyResult()==null) ||
             (this.getDistributionStatistics_BodyResult!=null &&
              this.getDistributionStatistics_BodyResult.equals(other.getGetDistributionStatistics_BodyResult())));
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
        if (getGetDistributionStatistics_BodyResult() != null) {
            _hashCode += getGetDistributionStatistics_BodyResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetDistributionStatistics_BodyResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">GetDistributionStatistics_BodyResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("getDistributionStatistics_BodyResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "GetDistributionStatistics_BodyResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">>GetDistributionStatistics_BodyResponse>GetDistributionStatistics_BodyResult"));
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
