/**
 * GetCollectionRate_Weather_BodyResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.harmonywisdom.dshbcbp.webservice.noisePortHistory;

public class GetCollectionRate_Weather_BodyResponse  implements java.io.Serializable {
    private GetCollectionRate_Weather_BodyResponseGetCollectionRate_Weather_BodyResult getCollectionRate_Weather_BodyResult;

    public GetCollectionRate_Weather_BodyResponse() {
    }

    public GetCollectionRate_Weather_BodyResponse(
           GetCollectionRate_Weather_BodyResponseGetCollectionRate_Weather_BodyResult getCollectionRate_Weather_BodyResult) {
           this.getCollectionRate_Weather_BodyResult = getCollectionRate_Weather_BodyResult;
    }


    /**
     * Gets the getCollectionRate_Weather_BodyResult value for this GetCollectionRate_Weather_BodyResponse.
     * 
     * @return getCollectionRate_Weather_BodyResult
     */
    public GetCollectionRate_Weather_BodyResponseGetCollectionRate_Weather_BodyResult getGetCollectionRate_Weather_BodyResult() {
        return getCollectionRate_Weather_BodyResult;
    }


    /**
     * Sets the getCollectionRate_Weather_BodyResult value for this GetCollectionRate_Weather_BodyResponse.
     * 
     * @param getCollectionRate_Weather_BodyResult
     */
    public void setGetCollectionRate_Weather_BodyResult(GetCollectionRate_Weather_BodyResponseGetCollectionRate_Weather_BodyResult getCollectionRate_Weather_BodyResult) {
        this.getCollectionRate_Weather_BodyResult = getCollectionRate_Weather_BodyResult;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof GetCollectionRate_Weather_BodyResponse)) return false;
        GetCollectionRate_Weather_BodyResponse other = (GetCollectionRate_Weather_BodyResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            ((this.getCollectionRate_Weather_BodyResult==null && other.getGetCollectionRate_Weather_BodyResult()==null) ||
             (this.getCollectionRate_Weather_BodyResult!=null &&
              this.getCollectionRate_Weather_BodyResult.equals(other.getGetCollectionRate_Weather_BodyResult())));
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
        if (getGetCollectionRate_Weather_BodyResult() != null) {
            _hashCode += getGetCollectionRate_Weather_BodyResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetCollectionRate_Weather_BodyResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">GetCollectionRate_Weather_BodyResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("getCollectionRate_Weather_BodyResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "GetCollectionRate_Weather_BodyResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">>GetCollectionRate_Weather_BodyResponse>GetCollectionRate_Weather_BodyResult"));
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
