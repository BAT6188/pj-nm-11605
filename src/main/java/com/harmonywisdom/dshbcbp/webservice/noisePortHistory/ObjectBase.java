/**
 * ObjectBase.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.harmonywisdom.dshbcbp.webservice.noisePortHistory;

public class ObjectBase  implements java.io.Serializable {
    private String SORT_TYPE;

    private String SORT_FIELD;

    private String ROWNO;

    public ObjectBase() {
    }

    public ObjectBase(
           String SORT_TYPE,
           String SORT_FIELD,
           String ROWNO) {
           this.SORT_TYPE = SORT_TYPE;
           this.SORT_FIELD = SORT_FIELD;
           this.ROWNO = ROWNO;
    }


    /**
     * Gets the SORT_TYPE value for this ObjectBase.
     *
     * @return SORT_TYPE
     */
    public String getSORT_TYPE() {
        return SORT_TYPE;
    }


    /**
     * Sets the SORT_TYPE value for this ObjectBase.
     *
     * @param SORT_TYPE
     */
    public void setSORT_TYPE(String SORT_TYPE) {
        this.SORT_TYPE = SORT_TYPE;
    }


    /**
     * Gets the SORT_FIELD value for this ObjectBase.
     *
     * @return SORT_FIELD
     */
    public String getSORT_FIELD() {
        return SORT_FIELD;
    }


    /**
     * Sets the SORT_FIELD value for this ObjectBase.
     *
     * @param SORT_FIELD
     */
    public void setSORT_FIELD(String SORT_FIELD) {
        this.SORT_FIELD = SORT_FIELD;
    }


    /**
     * Gets the ROWNO value for this ObjectBase.
     *
     * @return ROWNO
     */
    public String getROWNO() {
        return ROWNO;
    }


    /**
     * Sets the ROWNO value for this ObjectBase.
     *
     * @param ROWNO
     */
    public void setROWNO(String ROWNO) {
        this.ROWNO = ROWNO;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof ObjectBase)) return false;
        ObjectBase other = (ObjectBase) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            ((this.SORT_TYPE==null && other.getSORT_TYPE()==null) ||
             (this.SORT_TYPE!=null &&
              this.SORT_TYPE.equals(other.getSORT_TYPE()))) &&
            ((this.SORT_FIELD==null && other.getSORT_FIELD()==null) ||
             (this.SORT_FIELD!=null &&
              this.SORT_FIELD.equals(other.getSORT_FIELD()))) &&
            ((this.ROWNO==null && other.getROWNO()==null) ||
             (this.ROWNO!=null &&
              this.ROWNO.equals(other.getROWNO())));
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
        if (getSORT_TYPE() != null) {
            _hashCode += getSORT_TYPE().hashCode();
        }
        if (getSORT_FIELD() != null) {
            _hashCode += getSORT_FIELD().hashCode();
        }
        if (getROWNO() != null) {
            _hashCode += getROWNO().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ObjectBase.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", "ObjectBase"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SORT_TYPE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "SORT_TYPE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SORT_FIELD");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "SORT_FIELD"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ROWNO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "ROWNO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
