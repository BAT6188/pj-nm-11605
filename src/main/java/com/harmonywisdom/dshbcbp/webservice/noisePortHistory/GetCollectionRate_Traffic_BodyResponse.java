/**
 * GetCollectionRate_Traffic_BodyResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.harmonywisdom.dshbcbp.webservice.noisePortHistory;

public class GetCollectionRate_Traffic_BodyResponse  implements java.io.Serializable {
    private GetCollectionRate_Traffic_BodyResponseGetCollectionRate_Traffic_BodyResult getCollectionRate_Traffic_BodyResult;

    public GetCollectionRate_Traffic_BodyResponse() {
    }

    public GetCollectionRate_Traffic_BodyResponse(
           GetCollectionRate_Traffic_BodyResponseGetCollectionRate_Traffic_BodyResult getCollectionRate_Traffic_BodyResult) {
           this.getCollectionRate_Traffic_BodyResult = getCollectionRate_Traffic_BodyResult;
    }


    /**
     * Gets the getCollectionRate_Traffic_BodyResult value for this GetCollectionRate_Traffic_BodyResponse.
     * 
     * @return getCollectionRate_Traffic_BodyResult
     */
    public GetCollectionRate_Traffic_BodyResponseGetCollectionRate_Traffic_BodyResult getGetCollectionRate_Traffic_BodyResult() {
        return getCollectionRate_Traffic_BodyResult;
    }


    /**
     * Sets the getCollectionRate_Traffic_BodyResult value for this GetCollectionRate_Traffic_BodyResponse.
     * 
     * @param getCollectionRate_Traffic_BodyResult
     */
    public void setGetCollectionRate_Traffic_BodyResult(GetCollectionRate_Traffic_BodyResponseGetCollectionRate_Traffic_BodyResult getCollectionRate_Traffic_BodyResult) {
        this.getCollectionRate_Traffic_BodyResult = getCollectionRate_Traffic_BodyResult;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof GetCollectionRate_Traffic_BodyResponse)) return false;
        GetCollectionRate_Traffic_BodyResponse other = (GetCollectionRate_Traffic_BodyResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            ((this.getCollectionRate_Traffic_BodyResult==null && other.getGetCollectionRate_Traffic_BodyResult()==null) ||
             (this.getCollectionRate_Traffic_BodyResult!=null &&
              this.getCollectionRate_Traffic_BodyResult.equals(other.getGetCollectionRate_Traffic_BodyResult())));
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
        if (getGetCollectionRate_Traffic_BodyResult() != null) {
            _hashCode += getGetCollectionRate_Traffic_BodyResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetCollectionRate_Traffic_BodyResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">GetCollectionRate_Traffic_BodyResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("getCollectionRate_Traffic_BodyResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "GetCollectionRate_Traffic_BodyResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">>GetCollectionRate_Traffic_BodyResponse>GetCollectionRate_Traffic_BodyResult"));
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
