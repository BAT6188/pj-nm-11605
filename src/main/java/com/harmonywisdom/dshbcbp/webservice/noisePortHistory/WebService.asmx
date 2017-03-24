<?xml version="1.0" encoding="utf-8"?>
<wsdl:definitions xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" xmlns:tm="http://microsoft.com/wsdl/mime/textMatching/" xmlns:soapenc="http://schemas.xmlsoap.org/soap/encoding/" xmlns:mime="http://schemas.xmlsoap.org/wsdl/mime/" xmlns:tns="http://tempuri.org/" xmlns:s="http://www.w3.org/2001/XMLSchema" xmlns:soap12="http://schemas.xmlsoap.org/wsdl/soap12/" xmlns:http="http://schemas.xmlsoap.org/wsdl/http/" targetNamespace="http://tempuri.org/" xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">
  <wsdl:types>
    <s:schema elementFormDefault="qualified" targetNamespace="http://tempuri.org/">
      <s:element name="CheckUser">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="strUserName" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="strUserPwd" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="CheckUserResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="1" maxOccurs="1" name="CheckUserResult" type="s:boolean" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="GetAreaPointData">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="strUserName" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="strUserPwd" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="GetAreaPointDataResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="GetAreaPointDataResult">
              <s:complexType>
                <s:sequence>
                  <s:element ref="s:schema" />
                  <s:any />
                </s:sequence>
              </s:complexType>
            </s:element>
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="SavePwd">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="strUserName" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="strOldPwd" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="strNewPwd" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="SavePwdResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="1" maxOccurs="1" name="SavePwdResult" type="s:boolean" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="GetUserInfoData">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="strUserName" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="strUserPwd" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="GetUserInfoDataResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="GetUserInfoDataResult" type="tns:TSysUserVo" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:complexType name="TSysUserVo">
        <s:complexContent mixed="false">
          <s:extension base="tns:ObjectBase">
            <s:sequence>
              <s:element minOccurs="0" maxOccurs="1" name="ID" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="USER_NAME" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="REAL_NAME" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="ORDER_ID" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="USER_TYPE" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="USER_PWD" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="UNITS_CODE" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="REGION_CODE" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="DUTY_CODE" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="BUSINESS_CODE" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="SEX" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="BIRTHDAY" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="EMAIL" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="ADDRESS" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="POSTCODE" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="PHONE_MOBILE" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="PHONE_HOME" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="PHONE_OFFICE" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="ALLOW_IP" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="IS_USE" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="IS_DEL" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="CREATE_ID" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="CREATE_TIME" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="REMARK" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="REMARK1" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="REMARK2" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="REMARK3" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="REMARK4" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="REMARK5" type="s:string" />
            </s:sequence>
          </s:extension>
        </s:complexContent>
      </s:complexType>
      <s:complexType name="ObjectBase">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="1" name="SORT_TYPE" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="SORT_FIELD" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="ROWNO" type="s:string" />
        </s:sequence>
      </s:complexType>
      <s:element name="GetPointInfoData">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="strPointCode" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="strUserName" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="strUserPwd" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="GetPointInfoDataResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="GetPointInfoDataResult" type="tns:TSysStationPointVo" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:complexType name="TSysStationPointVo">
        <s:complexContent mixed="false">
          <s:extension base="tns:ObjectBase">
            <s:sequence>
              <s:element minOccurs="0" maxOccurs="1" name="ID" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="POINT_CODE" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="POINT_NAME" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="POINT_ALIAS" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="DYNA_ATTR_TYPE" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="DEPT_CODE" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="AREA_CODE" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="SERVICE_CODE" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="POINT_TYPE" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="POINT_CATEGORY" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="CONTROL_LEVEL" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="MONITOR_STANDARD" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="ENTERID" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="ADDRESS" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="LONGITUDE" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="LATITUDE" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="ENGINEER_ID" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="CREATE_ID" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="CREATE_TIME" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="DESCRIPTION" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="REMARK" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="REMARK1" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="REMARK2" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="REMARK3" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="REMARK4" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="REMARK5" type="s:string" />
            </s:sequence>
          </s:extension>
        </s:complexContent>
      </s:complexType>
      <s:element name="GetAllPointStata">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="strUserName" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="strUserPwd" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="GetAllPointStataResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="GetAllPointStataResult">
              <s:complexType>
                <s:sequence>
                  <s:element ref="s:schema" />
                  <s:any />
                </s:sequence>
              </s:complexType>
            </s:element>
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="GetNoiseRealTime">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="strUserName" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="strUserPwd" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="GetNoiseRealTimeResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="GetNoiseRealTimeResult">
              <s:complexType>
                <s:sequence>
                  <s:element ref="s:schema" />
                  <s:any />
                </s:sequence>
              </s:complexType>
            </s:element>
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="GetWeatherRealTime">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="strUserName" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="strUserPwd" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="GetWeatherRealTimeResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="GetWeatherRealTimeResult">
              <s:complexType>
                <s:sequence>
                  <s:element ref="s:schema" />
                  <s:any />
                </s:sequence>
              </s:complexType>
            </s:element>
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="GetTrafficRealTime">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="strUserName" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="strUserPwd" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="GetTrafficRealTimeResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="GetTrafficRealTimeResult">
              <s:complexType>
                <s:sequence>
                  <s:element ref="s:schema" />
                  <s:any />
                </s:sequence>
              </s:complexType>
            </s:element>
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="GetSinglePointDayReport_Head">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="strPointCode" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="strDayTime" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="strUserName" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="strUserPwd" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="GetSinglePointDayReport_HeadResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="GetSinglePointDayReport_HeadResult">
              <s:complexType>
                <s:sequence>
                  <s:element ref="s:schema" />
                  <s:any />
                </s:sequence>
              </s:complexType>
            </s:element>
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="GetSinglePointDayReport_Body">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="strPointCode" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="strDayTime" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="strUserName" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="strUserPwd" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="GetSinglePointDayReport_BodyResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="GetSinglePointDayReport_BodyResult">
              <s:complexType>
                <s:sequence>
                  <s:element ref="s:schema" />
                  <s:any />
                </s:sequence>
              </s:complexType>
            </s:element>
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="GetSinglePointMonthReport_Head">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="strPointCode" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="strDayTime" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="strUserName" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="strUserPwd" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="GetSinglePointMonthReport_HeadResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="GetSinglePointMonthReport_HeadResult">
              <s:complexType>
                <s:sequence>
                  <s:element ref="s:schema" />
                  <s:any />
                </s:sequence>
              </s:complexType>
            </s:element>
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="GetSinglePointMonthReport_Body">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="strPointCode" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="strDayTime" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="strUserName" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="strUserPwd" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="GetSinglePointMonthReport_BodyResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="GetSinglePointMonthReport_BodyResult">
              <s:complexType>
                <s:sequence>
                  <s:element ref="s:schema" />
                  <s:any />
                </s:sequence>
              </s:complexType>
            </s:element>
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="GetSinglePointQuarterReport_Head">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="strPointCode" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="strDayTime" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="strUserName" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="strUserPwd" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="GetSinglePointQuarterReport_HeadResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="GetSinglePointQuarterReport_HeadResult">
              <s:complexType>
                <s:sequence>
                  <s:element ref="s:schema" />
                  <s:any />
                </s:sequence>
              </s:complexType>
            </s:element>
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="GetSinglePointQuarterReport_Body">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="strPointCode" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="strDayTime" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="strUserName" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="strUserPwd" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="GetSinglePointQuarterReport_BodyResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="GetSinglePointQuarterReport_BodyResult">
              <s:complexType>
                <s:sequence>
                  <s:element ref="s:schema" />
                  <s:any />
                </s:sequence>
              </s:complexType>
            </s:element>
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="GetDistributionStatistics_Body">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="strPointCode" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="strDayTime" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="strUserName" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="strUserPwd" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="GetDistributionStatistics_BodyResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="GetDistributionStatistics_BodyResult">
              <s:complexType>
                <s:sequence>
                  <s:element ref="s:schema" />
                  <s:any />
                </s:sequence>
              </s:complexType>
            </s:element>
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="GetDynamicAnalysis_Body">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="strPointCode" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="strDayTimeStrat" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="strDayTimeEnd" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="strHour" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="strUserName" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="strUserPwd" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="GetDynamicAnalysis_BodyResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="GetDynamicAnalysis_BodyResult">
              <s:complexType>
                <s:sequence>
                  <s:element ref="s:schema" />
                  <s:any />
                </s:sequence>
              </s:complexType>
            </s:element>
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="GetRelativityData_Body">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="strPointCode" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="strDayTime" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="strUserName" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="strUserPwd" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="GetRelativityData_BodyResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="GetRelativityData_BodyResult">
              <s:complexType>
                <s:sequence>
                  <s:element ref="s:schema" />
                  <s:any />
                </s:sequence>
              </s:complexType>
            </s:element>
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="GetCollectionRate_Noise_Body">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="strPointCodes" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="strDayTime" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="strUserName" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="strUserPwd" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="GetCollectionRate_Noise_BodyResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="GetCollectionRate_Noise_BodyResult">
              <s:complexType>
                <s:sequence>
                  <s:element ref="s:schema" />
                  <s:any />
                </s:sequence>
              </s:complexType>
            </s:element>
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="GetCollectionRate_Weather_Body">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="strPointCodes" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="strDayTime" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="strUserName" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="strUserPwd" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="GetCollectionRate_Weather_BodyResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="GetCollectionRate_Weather_BodyResult">
              <s:complexType>
                <s:sequence>
                  <s:element ref="s:schema" />
                  <s:any />
                </s:sequence>
              </s:complexType>
            </s:element>
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="GetCollectionRate_Traffic_Body">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="strPointCodes" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="strDayTime" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="strUserName" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="strUserPwd" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="GetCollectionRate_Traffic_BodyResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="GetCollectionRate_Traffic_BodyResult">
              <s:complexType>
                <s:sequence>
                  <s:element ref="s:schema" />
                  <s:any />
                </s:sequence>
              </s:complexType>
            </s:element>
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="GetInitMapInfo">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="strUserName" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="strUserPwd" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="GetInitMapInfoResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="GetInitMapInfoResult" type="tns:TSysAreaVo" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:complexType name="TSysAreaVo">
        <s:complexContent mixed="false">
          <s:extension base="tns:ObjectBase">
            <s:sequence>
              <s:element minOccurs="0" maxOccurs="1" name="ID" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="AREA_TEXT" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="AREA_CODE" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="PARENT_CODE" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="RELATION_TYPE" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="ORDER_ID" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="REMARK" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="REMARK1" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="REMARK2" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="REMARK3" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="GIS3D_SERVICE" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="GIS3D_LEVEL" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="ARCGIS_SERVICE" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="ARCGIS_TSERVICE" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="ARCGIS_LEVEL" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="PICGIS_SERVICE" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="PICGIS_ARCSERVICE" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="PICGIS_LEVEL" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="PICGIS_STYLE" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="SERVICE_EXTENT" type="s:string" />
            </s:sequence>
          </s:extension>
        </s:complexContent>
      </s:complexType>
      <s:element name="GetInitPointInfo">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="strUserName" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="strUserPwd" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="GetInitPointInfoResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="GetInitPointInfoResult">
              <s:complexType>
                <s:sequence>
                  <s:element ref="s:schema" />
                  <s:any />
                </s:sequence>
              </s:complexType>
            </s:element>
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="GetInitPointInfoAll">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="strUserName" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="strUserPwd" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="strPtName" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="strPtCateGory" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="strPtService" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="GetInitPointInfoAllResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="GetInitPointInfoAllResult">
              <s:complexType>
                <s:sequence>
                  <s:element ref="s:schema" />
                  <s:any />
                </s:sequence>
              </s:complexType>
            </s:element>
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="GetPointWarningInfoNum">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="strUserName" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="strUserPwd" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="GetPointWarningInfoNumResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="1" maxOccurs="1" name="GetPointWarningInfoNumResult" type="s:int" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="GetPointWarningInfoNumNew">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="strUserName" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="strUserPwd" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="GetPointWarningInfoNumNewResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="1" maxOccurs="1" name="GetPointWarningInfoNumNewResult" type="s:int" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="GetPointWarningInfoSeachNum">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="strPointCodes" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="strDayTimeStrat" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="strDayTimeEnd" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="strUserName" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="strUserPwd" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="GetPointWarningInfoSeachNumResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="1" maxOccurs="1" name="GetPointWarningInfoSeachNumResult" type="s:int" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="GetPointWarningInfoSeachNumNew">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="strPointCodes" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="strDayTimeStrat" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="strDayTimeEnd" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="strUserName" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="strUserPwd" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="GetPointWarningInfoSeachNumNewResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="1" maxOccurs="1" name="GetPointWarningInfoSeachNumNewResult" type="s:int" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="GetPointWarningInfo">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="1" maxOccurs="1" name="iPage" type="s:int" />
            <s:element minOccurs="1" maxOccurs="1" name="iRows" type="s:int" />
            <s:element minOccurs="0" maxOccurs="1" name="strUserName" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="strUserPwd" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="GetPointWarningInfoResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="GetPointWarningInfoResult">
              <s:complexType>
                <s:sequence>
                  <s:element ref="s:schema" />
                  <s:any />
                </s:sequence>
              </s:complexType>
            </s:element>
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="GetPointWarningInfoNew">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="1" maxOccurs="1" name="iPage" type="s:int" />
            <s:element minOccurs="1" maxOccurs="1" name="iRows" type="s:int" />
            <s:element minOccurs="0" maxOccurs="1" name="strUserName" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="strUserPwd" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="GetPointWarningInfoNewResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="GetPointWarningInfoNewResult">
              <s:complexType>
                <s:sequence>
                  <s:element ref="s:schema" />
                  <s:any />
                </s:sequence>
              </s:complexType>
            </s:element>
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="GetPointWarningInfoSeach">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="strPointCodes" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="strDayTimeStrat" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="strDayTimeEnd" type="s:string" />
            <s:element minOccurs="1" maxOccurs="1" name="iPage" type="s:int" />
            <s:element minOccurs="1" maxOccurs="1" name="iRows" type="s:int" />
            <s:element minOccurs="0" maxOccurs="1" name="strUserName" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="strUserPwd" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="GetPointWarningInfoSeachResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="GetPointWarningInfoSeachResult">
              <s:complexType>
                <s:sequence>
                  <s:element ref="s:schema" />
                  <s:any />
                </s:sequence>
              </s:complexType>
            </s:element>
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="GetPointWarningInfoSeachNew">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="strPointCodes" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="strDayTimeStrat" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="strDayTimeEnd" type="s:string" />
            <s:element minOccurs="1" maxOccurs="1" name="iPage" type="s:int" />
            <s:element minOccurs="1" maxOccurs="1" name="iRows" type="s:int" />
            <s:element minOccurs="0" maxOccurs="1" name="strUserName" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="strUserPwd" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="GetPointWarningInfoSeachNewResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="GetPointWarningInfoSeachNewResult">
              <s:complexType>
                <s:sequence>
                  <s:element ref="s:schema" />
                  <s:any />
                </s:sequence>
              </s:complexType>
            </s:element>
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="GetCDicts">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="strUserName" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="strUserPwd" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="strDictCode" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="GetCDictsResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="GetCDictsResult" type="tns:ArrayOfTSysDictVo" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:complexType name="ArrayOfTSysDictVo">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="unbounded" name="TSysDictVo" nillable="true" type="tns:TSysDictVo" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="TSysDictVo">
        <s:complexContent mixed="false">
          <s:extension base="tns:ObjectBase">
            <s:sequence>
              <s:element minOccurs="0" maxOccurs="1" name="ID" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="DICT_TYPE" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="DICT_TEXT" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="DICT_CODE" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="DICT_GROUP" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="PARENT_TYPE" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="PARENT_CODE" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="RELATION_TYPE" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="GROUP_CODE" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="ORDER_ID" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="AUTO_LOAD" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="EXTENDION" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="EXTENDION_CODE" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="REMARK" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="REMARK1" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="REMARK2" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="REMARK3" type="s:string" />
            </s:sequence>
          </s:extension>
        </s:complexContent>
      </s:complexType>
      <s:element name="boolean" type="s:boolean" />
      <s:element name="DataSet" nillable="true">
        <s:complexType>
          <s:sequence>
            <s:element ref="s:schema" />
            <s:any />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="TSysUserVo" nillable="true" type="tns:TSysUserVo" />
      <s:element name="TSysStationPointVo" nillable="true" type="tns:TSysStationPointVo" />
      <s:element name="TSysAreaVo" nillable="true" type="tns:TSysAreaVo" />
      <s:element name="int" type="s:int" />
      <s:element name="ArrayOfTSysDictVo" nillable="true" type="tns:ArrayOfTSysDictVo" />
    </s:schema>
  </wsdl:types>
  <wsdl:message name="CheckUserSoapIn">
    <wsdl:part name="parameters" element="tns:CheckUser" />
  </wsdl:message>
  <wsdl:message name="CheckUserSoapOut">
    <wsdl:part name="parameters" element="tns:CheckUserResponse" />
  </wsdl:message>
  <wsdl:message name="GetAreaPointDataSoapIn">
    <wsdl:part name="parameters" element="tns:GetAreaPointData" />
  </wsdl:message>
  <wsdl:message name="GetAreaPointDataSoapOut">
    <wsdl:part name="parameters" element="tns:GetAreaPointDataResponse" />
  </wsdl:message>
  <wsdl:message name="SavePwdSoapIn">
    <wsdl:part name="parameters" element="tns:SavePwd" />
  </wsdl:message>
  <wsdl:message name="SavePwdSoapOut">
    <wsdl:part name="parameters" element="tns:SavePwdResponse" />
  </wsdl:message>
  <wsdl:message name="GetUserInfoDataSoapIn">
    <wsdl:part name="parameters" element="tns:GetUserInfoData" />
  </wsdl:message>
  <wsdl:message name="GetUserInfoDataSoapOut">
    <wsdl:part name="parameters" element="tns:GetUserInfoDataResponse" />
  </wsdl:message>
  <wsdl:message name="GetPointInfoDataSoapIn">
    <wsdl:part name="parameters" element="tns:GetPointInfoData" />
  </wsdl:message>
  <wsdl:message name="GetPointInfoDataSoapOut">
    <wsdl:part name="parameters" element="tns:GetPointInfoDataResponse" />
  </wsdl:message>
  <wsdl:message name="GetAllPointStataSoapIn">
    <wsdl:part name="parameters" element="tns:GetAllPointStata" />
  </wsdl:message>
  <wsdl:message name="GetAllPointStataSoapOut">
    <wsdl:part name="parameters" element="tns:GetAllPointStataResponse" />
  </wsdl:message>
  <wsdl:message name="GetNoiseRealTimeSoapIn">
    <wsdl:part name="parameters" element="tns:GetNoiseRealTime" />
  </wsdl:message>
  <wsdl:message name="GetNoiseRealTimeSoapOut">
    <wsdl:part name="parameters" element="tns:GetNoiseRealTimeResponse" />
  </wsdl:message>
  <wsdl:message name="GetWeatherRealTimeSoapIn">
    <wsdl:part name="parameters" element="tns:GetWeatherRealTime" />
  </wsdl:message>
  <wsdl:message name="GetWeatherRealTimeSoapOut">
    <wsdl:part name="parameters" element="tns:GetWeatherRealTimeResponse" />
  </wsdl:message>
  <wsdl:message name="GetTrafficRealTimeSoapIn">
    <wsdl:part name="parameters" element="tns:GetTrafficRealTime" />
  </wsdl:message>
  <wsdl:message name="GetTrafficRealTimeSoapOut">
    <wsdl:part name="parameters" element="tns:GetTrafficRealTimeResponse" />
  </wsdl:message>
  <wsdl:message name="GetSinglePointDayReport_HeadSoapIn">
    <wsdl:part name="parameters" element="tns:GetSinglePointDayReport_Head" />
  </wsdl:message>
  <wsdl:message name="GetSinglePointDayReport_HeadSoapOut">
    <wsdl:part name="parameters" element="tns:GetSinglePointDayReport_HeadResponse" />
  </wsdl:message>
  <wsdl:message name="GetSinglePointDayReport_BodySoapIn">
    <wsdl:part name="parameters" element="tns:GetSinglePointDayReport_Body" />
  </wsdl:message>
  <wsdl:message name="GetSinglePointDayReport_BodySoapOut">
    <wsdl:part name="parameters" element="tns:GetSinglePointDayReport_BodyResponse" />
  </wsdl:message>
  <wsdl:message name="GetSinglePointMonthReport_HeadSoapIn">
    <wsdl:part name="parameters" element="tns:GetSinglePointMonthReport_Head" />
  </wsdl:message>
  <wsdl:message name="GetSinglePointMonthReport_HeadSoapOut">
    <wsdl:part name="parameters" element="tns:GetSinglePointMonthReport_HeadResponse" />
  </wsdl:message>
  <wsdl:message name="GetSinglePointMonthReport_BodySoapIn">
    <wsdl:part name="parameters" element="tns:GetSinglePointMonthReport_Body" />
  </wsdl:message>
  <wsdl:message name="GetSinglePointMonthReport_BodySoapOut">
    <wsdl:part name="parameters" element="tns:GetSinglePointMonthReport_BodyResponse" />
  </wsdl:message>
  <wsdl:message name="GetSinglePointQuarterReport_HeadSoapIn">
    <wsdl:part name="parameters" element="tns:GetSinglePointQuarterReport_Head" />
  </wsdl:message>
  <wsdl:message name="GetSinglePointQuarterReport_HeadSoapOut">
    <wsdl:part name="parameters" element="tns:GetSinglePointQuarterReport_HeadResponse" />
  </wsdl:message>
  <wsdl:message name="GetSinglePointQuarterReport_BodySoapIn">
    <wsdl:part name="parameters" element="tns:GetSinglePointQuarterReport_Body" />
  </wsdl:message>
  <wsdl:message name="GetSinglePointQuarterReport_BodySoapOut">
    <wsdl:part name="parameters" element="tns:GetSinglePointQuarterReport_BodyResponse" />
  </wsdl:message>
  <wsdl:message name="GetDistributionStatistics_BodySoapIn">
    <wsdl:part name="parameters" element="tns:GetDistributionStatistics_Body" />
  </wsdl:message>
  <wsdl:message name="GetDistributionStatistics_BodySoapOut">
    <wsdl:part name="parameters" element="tns:GetDistributionStatistics_BodyResponse" />
  </wsdl:message>
  <wsdl:message name="GetDynamicAnalysis_BodySoapIn">
    <wsdl:part name="parameters" element="tns:GetDynamicAnalysis_Body" />
  </wsdl:message>
  <wsdl:message name="GetDynamicAnalysis_BodySoapOut">
    <wsdl:part name="parameters" element="tns:GetDynamicAnalysis_BodyResponse" />
  </wsdl:message>
  <wsdl:message name="GetRelativityData_BodySoapIn">
    <wsdl:part name="parameters" element="tns:GetRelativityData_Body" />
  </wsdl:message>
  <wsdl:message name="GetRelativityData_BodySoapOut">
    <wsdl:part name="parameters" element="tns:GetRelativityData_BodyResponse" />
  </wsdl:message>
  <wsdl:message name="GetCollectionRate_Noise_BodySoapIn">
    <wsdl:part name="parameters" element="tns:GetCollectionRate_Noise_Body" />
  </wsdl:message>
  <wsdl:message name="GetCollectionRate_Noise_BodySoapOut">
    <wsdl:part name="parameters" element="tns:GetCollectionRate_Noise_BodyResponse" />
  </wsdl:message>
  <wsdl:message name="GetCollectionRate_Weather_BodySoapIn">
    <wsdl:part name="parameters" element="tns:GetCollectionRate_Weather_Body" />
  </wsdl:message>
  <wsdl:message name="GetCollectionRate_Weather_BodySoapOut">
    <wsdl:part name="parameters" element="tns:GetCollectionRate_Weather_BodyResponse" />
  </wsdl:message>
  <wsdl:message name="GetCollectionRate_Traffic_BodySoapIn">
    <wsdl:part name="parameters" element="tns:GetCollectionRate_Traffic_Body" />
  </wsdl:message>
  <wsdl:message name="GetCollectionRate_Traffic_BodySoapOut">
    <wsdl:part name="parameters" element="tns:GetCollectionRate_Traffic_BodyResponse" />
  </wsdl:message>
  <wsdl:message name="GetInitMapInfoSoapIn">
    <wsdl:part name="parameters" element="tns:GetInitMapInfo" />
  </wsdl:message>
  <wsdl:message name="GetInitMapInfoSoapOut">
    <wsdl:part name="parameters" element="tns:GetInitMapInfoResponse" />
  </wsdl:message>
  <wsdl:message name="GetInitPointInfoSoapIn">
    <wsdl:part name="parameters" element="tns:GetInitPointInfo" />
  </wsdl:message>
  <wsdl:message name="GetInitPointInfoSoapOut">
    <wsdl:part name="parameters" element="tns:GetInitPointInfoResponse" />
  </wsdl:message>
  <wsdl:message name="GetInitPointInfoAllSoapIn">
    <wsdl:part name="parameters" element="tns:GetInitPointInfoAll" />
  </wsdl:message>
  <wsdl:message name="GetInitPointInfoAllSoapOut">
    <wsdl:part name="parameters" element="tns:GetInitPointInfoAllResponse" />
  </wsdl:message>
  <wsdl:message name="GetPointWarningInfoNumSoapIn">
    <wsdl:part name="parameters" element="tns:GetPointWarningInfoNum" />
  </wsdl:message>
  <wsdl:message name="GetPointWarningInfoNumSoapOut">
    <wsdl:part name="parameters" element="tns:GetPointWarningInfoNumResponse" />
  </wsdl:message>
  <wsdl:message name="GetPointWarningInfoNumNewSoapIn">
    <wsdl:part name="parameters" element="tns:GetPointWarningInfoNumNew" />
  </wsdl:message>
  <wsdl:message name="GetPointWarningInfoNumNewSoapOut">
    <wsdl:part name="parameters" element="tns:GetPointWarningInfoNumNewResponse" />
  </wsdl:message>
  <wsdl:message name="GetPointWarningInfoSeachNumSoapIn">
    <wsdl:part name="parameters" element="tns:GetPointWarningInfoSeachNum" />
  </wsdl:message>
  <wsdl:message name="GetPointWarningInfoSeachNumSoapOut">
    <wsdl:part name="parameters" element="tns:GetPointWarningInfoSeachNumResponse" />
  </wsdl:message>
  <wsdl:message name="GetPointWarningInfoSeachNumNewSoapIn">
    <wsdl:part name="parameters" element="tns:GetPointWarningInfoSeachNumNew" />
  </wsdl:message>
  <wsdl:message name="GetPointWarningInfoSeachNumNewSoapOut">
    <wsdl:part name="parameters" element="tns:GetPointWarningInfoSeachNumNewResponse" />
  </wsdl:message>
  <wsdl:message name="GetPointWarningInfoSoapIn">
    <wsdl:part name="parameters" element="tns:GetPointWarningInfo" />
  </wsdl:message>
  <wsdl:message name="GetPointWarningInfoSoapOut">
    <wsdl:part name="parameters" element="tns:GetPointWarningInfoResponse" />
  </wsdl:message>
  <wsdl:message name="GetPointWarningInfoNewSoapIn">
    <wsdl:part name="parameters" element="tns:GetPointWarningInfoNew" />
  </wsdl:message>
  <wsdl:message name="GetPointWarningInfoNewSoapOut">
    <wsdl:part name="parameters" element="tns:GetPointWarningInfoNewResponse" />
  </wsdl:message>
  <wsdl:message name="GetPointWarningInfoSeachSoapIn">
    <wsdl:part name="parameters" element="tns:GetPointWarningInfoSeach" />
  </wsdl:message>
  <wsdl:message name="GetPointWarningInfoSeachSoapOut">
    <wsdl:part name="parameters" element="tns:GetPointWarningInfoSeachResponse" />
  </wsdl:message>
  <wsdl:message name="GetPointWarningInfoSeachNewSoapIn">
    <wsdl:part name="parameters" element="tns:GetPointWarningInfoSeachNew" />
  </wsdl:message>
  <wsdl:message name="GetPointWarningInfoSeachNewSoapOut">
    <wsdl:part name="parameters" element="tns:GetPointWarningInfoSeachNewResponse" />
  </wsdl:message>
  <wsdl:message name="GetCDictsSoapIn">
    <wsdl:part name="parameters" element="tns:GetCDicts" />
  </wsdl:message>
  <wsdl:message name="GetCDictsSoapOut">
    <wsdl:part name="parameters" element="tns:GetCDictsResponse" />
  </wsdl:message>
  <wsdl:message name="CheckUserHttpGetIn">
    <wsdl:part name="strUserName" type="s:string" />
    <wsdl:part name="strUserPwd" type="s:string" />
  </wsdl:message>
  <wsdl:message name="CheckUserHttpGetOut">
    <wsdl:part name="Body" element="tns:boolean" />
  </wsdl:message>
  <wsdl:message name="GetAreaPointDataHttpGetIn">
    <wsdl:part name="strUserName" type="s:string" />
    <wsdl:part name="strUserPwd" type="s:string" />
  </wsdl:message>
  <wsdl:message name="GetAreaPointDataHttpGetOut">
    <wsdl:part name="Body" element="tns:DataSet" />
  </wsdl:message>
  <wsdl:message name="SavePwdHttpGetIn">
    <wsdl:part name="strUserName" type="s:string" />
    <wsdl:part name="strOldPwd" type="s:string" />
    <wsdl:part name="strNewPwd" type="s:string" />
  </wsdl:message>
  <wsdl:message name="SavePwdHttpGetOut">
    <wsdl:part name="Body" element="tns:boolean" />
  </wsdl:message>
  <wsdl:message name="GetUserInfoDataHttpGetIn">
    <wsdl:part name="strUserName" type="s:string" />
    <wsdl:part name="strUserPwd" type="s:string" />
  </wsdl:message>
  <wsdl:message name="GetUserInfoDataHttpGetOut">
    <wsdl:part name="Body" element="tns:TSysUserVo" />
  </wsdl:message>
  <wsdl:message name="GetPointInfoDataHttpGetIn">
    <wsdl:part name="strPointCode" type="s:string" />
    <wsdl:part name="strUserName" type="s:string" />
    <wsdl:part name="strUserPwd" type="s:string" />
  </wsdl:message>
  <wsdl:message name="GetPointInfoDataHttpGetOut">
    <wsdl:part name="Body" element="tns:TSysStationPointVo" />
  </wsdl:message>
  <wsdl:message name="GetAllPointStataHttpGetIn">
    <wsdl:part name="strUserName" type="s:string" />
    <wsdl:part name="strUserPwd" type="s:string" />
  </wsdl:message>
  <wsdl:message name="GetAllPointStataHttpGetOut">
    <wsdl:part name="Body" element="tns:DataSet" />
  </wsdl:message>
  <wsdl:message name="GetNoiseRealTimeHttpGetIn">
    <wsdl:part name="strUserName" type="s:string" />
    <wsdl:part name="strUserPwd" type="s:string" />
  </wsdl:message>
  <wsdl:message name="GetNoiseRealTimeHttpGetOut">
    <wsdl:part name="Body" element="tns:DataSet" />
  </wsdl:message>
  <wsdl:message name="GetWeatherRealTimeHttpGetIn">
    <wsdl:part name="strUserName" type="s:string" />
    <wsdl:part name="strUserPwd" type="s:string" />
  </wsdl:message>
  <wsdl:message name="GetWeatherRealTimeHttpGetOut">
    <wsdl:part name="Body" element="tns:DataSet" />
  </wsdl:message>
  <wsdl:message name="GetTrafficRealTimeHttpGetIn">
    <wsdl:part name="strUserName" type="s:string" />
    <wsdl:part name="strUserPwd" type="s:string" />
  </wsdl:message>
  <wsdl:message name="GetTrafficRealTimeHttpGetOut">
    <wsdl:part name="Body" element="tns:DataSet" />
  </wsdl:message>
  <wsdl:message name="GetSinglePointDayReport_HeadHttpGetIn">
    <wsdl:part name="strPointCode" type="s:string" />
    <wsdl:part name="strDayTime" type="s:string" />
    <wsdl:part name="strUserName" type="s:string" />
    <wsdl:part name="strUserPwd" type="s:string" />
  </wsdl:message>
  <wsdl:message name="GetSinglePointDayReport_HeadHttpGetOut">
    <wsdl:part name="Body" element="tns:DataSet" />
  </wsdl:message>
  <wsdl:message name="GetSinglePointDayReport_BodyHttpGetIn">
    <wsdl:part name="strPointCode" type="s:string" />
    <wsdl:part name="strDayTime" type="s:string" />
    <wsdl:part name="strUserName" type="s:string" />
    <wsdl:part name="strUserPwd" type="s:string" />
  </wsdl:message>
  <wsdl:message name="GetSinglePointDayReport_BodyHttpGetOut">
    <wsdl:part name="Body" element="tns:DataSet" />
  </wsdl:message>
  <wsdl:message name="GetSinglePointMonthReport_HeadHttpGetIn">
    <wsdl:part name="strPointCode" type="s:string" />
    <wsdl:part name="strDayTime" type="s:string" />
    <wsdl:part name="strUserName" type="s:string" />
    <wsdl:part name="strUserPwd" type="s:string" />
  </wsdl:message>
  <wsdl:message name="GetSinglePointMonthReport_HeadHttpGetOut">
    <wsdl:part name="Body" element="tns:DataSet" />
  </wsdl:message>
  <wsdl:message name="GetSinglePointMonthReport_BodyHttpGetIn">
    <wsdl:part name="strPointCode" type="s:string" />
    <wsdl:part name="strDayTime" type="s:string" />
    <wsdl:part name="strUserName" type="s:string" />
    <wsdl:part name="strUserPwd" type="s:string" />
  </wsdl:message>
  <wsdl:message name="GetSinglePointMonthReport_BodyHttpGetOut">
    <wsdl:part name="Body" element="tns:DataSet" />
  </wsdl:message>
  <wsdl:message name="GetSinglePointQuarterReport_HeadHttpGetIn">
    <wsdl:part name="strPointCode" type="s:string" />
    <wsdl:part name="strDayTime" type="s:string" />
    <wsdl:part name="strUserName" type="s:string" />
    <wsdl:part name="strUserPwd" type="s:string" />
  </wsdl:message>
  <wsdl:message name="GetSinglePointQuarterReport_HeadHttpGetOut">
    <wsdl:part name="Body" element="tns:DataSet" />
  </wsdl:message>
  <wsdl:message name="GetSinglePointQuarterReport_BodyHttpGetIn">
    <wsdl:part name="strPointCode" type="s:string" />
    <wsdl:part name="strDayTime" type="s:string" />
    <wsdl:part name="strUserName" type="s:string" />
    <wsdl:part name="strUserPwd" type="s:string" />
  </wsdl:message>
  <wsdl:message name="GetSinglePointQuarterReport_BodyHttpGetOut">
    <wsdl:part name="Body" element="tns:DataSet" />
  </wsdl:message>
  <wsdl:message name="GetDistributionStatistics_BodyHttpGetIn">
    <wsdl:part name="strPointCode" type="s:string" />
    <wsdl:part name="strDayTime" type="s:string" />
    <wsdl:part name="strUserName" type="s:string" />
    <wsdl:part name="strUserPwd" type="s:string" />
  </wsdl:message>
  <wsdl:message name="GetDistributionStatistics_BodyHttpGetOut">
    <wsdl:part name="Body" element="tns:DataSet" />
  </wsdl:message>
  <wsdl:message name="GetDynamicAnalysis_BodyHttpGetIn">
    <wsdl:part name="strPointCode" type="s:string" />
    <wsdl:part name="strDayTimeStrat" type="s:string" />
    <wsdl:part name="strDayTimeEnd" type="s:string" />
    <wsdl:part name="strHour" type="s:string" />
    <wsdl:part name="strUserName" type="s:string" />
    <wsdl:part name="strUserPwd" type="s:string" />
  </wsdl:message>
  <wsdl:message name="GetDynamicAnalysis_BodyHttpGetOut">
    <wsdl:part name="Body" element="tns:DataSet" />
  </wsdl:message>
  <wsdl:message name="GetRelativityData_BodyHttpGetIn">
    <wsdl:part name="strPointCode" type="s:string" />
    <wsdl:part name="strDayTime" type="s:string" />
    <wsdl:part name="strUserName" type="s:string" />
    <wsdl:part name="strUserPwd" type="s:string" />
  </wsdl:message>
  <wsdl:message name="GetRelativityData_BodyHttpGetOut">
    <wsdl:part name="Body" element="tns:DataSet" />
  </wsdl:message>
  <wsdl:message name="GetCollectionRate_Noise_BodyHttpGetIn">
    <wsdl:part name="strPointCodes" type="s:string" />
    <wsdl:part name="strDayTime" type="s:string" />
    <wsdl:part name="strUserName" type="s:string" />
    <wsdl:part name="strUserPwd" type="s:string" />
  </wsdl:message>
  <wsdl:message name="GetCollectionRate_Noise_BodyHttpGetOut">
    <wsdl:part name="Body" element="tns:DataSet" />
  </wsdl:message>
  <wsdl:message name="GetCollectionRate_Weather_BodyHttpGetIn">
    <wsdl:part name="strPointCodes" type="s:string" />
    <wsdl:part name="strDayTime" type="s:string" />
    <wsdl:part name="strUserName" type="s:string" />
    <wsdl:part name="strUserPwd" type="s:string" />
  </wsdl:message>
  <wsdl:message name="GetCollectionRate_Weather_BodyHttpGetOut">
    <wsdl:part name="Body" element="tns:DataSet" />
  </wsdl:message>
  <wsdl:message name="GetCollectionRate_Traffic_BodyHttpGetIn">
    <wsdl:part name="strPointCodes" type="s:string" />
    <wsdl:part name="strDayTime" type="s:string" />
    <wsdl:part name="strUserName" type="s:string" />
    <wsdl:part name="strUserPwd" type="s:string" />
  </wsdl:message>
  <wsdl:message name="GetCollectionRate_Traffic_BodyHttpGetOut">
    <wsdl:part name="Body" element="tns:DataSet" />
  </wsdl:message>
  <wsdl:message name="GetInitMapInfoHttpGetIn">
    <wsdl:part name="strUserName" type="s:string" />
    <wsdl:part name="strUserPwd" type="s:string" />
  </wsdl:message>
  <wsdl:message name="GetInitMapInfoHttpGetOut">
    <wsdl:part name="Body" element="tns:TSysAreaVo" />
  </wsdl:message>
  <wsdl:message name="GetInitPointInfoHttpGetIn">
    <wsdl:part name="strUserName" type="s:string" />
    <wsdl:part name="strUserPwd" type="s:string" />
  </wsdl:message>
  <wsdl:message name="GetInitPointInfoHttpGetOut">
    <wsdl:part name="Body" element="tns:DataSet" />
  </wsdl:message>
  <wsdl:message name="GetInitPointInfoAllHttpGetIn">
    <wsdl:part name="strUserName" type="s:string" />
    <wsdl:part name="strUserPwd" type="s:string" />
    <wsdl:part name="strPtName" type="s:string" />
    <wsdl:part name="strPtCateGory" type="s:string" />
    <wsdl:part name="strPtService" type="s:string" />
  </wsdl:message>
  <wsdl:message name="GetInitPointInfoAllHttpGetOut">
    <wsdl:part name="Body" element="tns:DataSet" />
  </wsdl:message>
  <wsdl:message name="GetPointWarningInfoNumHttpGetIn">
    <wsdl:part name="strUserName" type="s:string" />
    <wsdl:part name="strUserPwd" type="s:string" />
  </wsdl:message>
  <wsdl:message name="GetPointWarningInfoNumHttpGetOut">
    <wsdl:part name="Body" element="tns:int" />
  </wsdl:message>
  <wsdl:message name="GetPointWarningInfoNumNewHttpGetIn">
    <wsdl:part name="strUserName" type="s:string" />
    <wsdl:part name="strUserPwd" type="s:string" />
  </wsdl:message>
  <wsdl:message name="GetPointWarningInfoNumNewHttpGetOut">
    <wsdl:part name="Body" element="tns:int" />
  </wsdl:message>
  <wsdl:message name="GetPointWarningInfoSeachNumHttpGetIn">
    <wsdl:part name="strPointCodes" type="s:string" />
    <wsdl:part name="strDayTimeStrat" type="s:string" />
    <wsdl:part name="strDayTimeEnd" type="s:string" />
    <wsdl:part name="strUserName" type="s:string" />
    <wsdl:part name="strUserPwd" type="s:string" />
  </wsdl:message>
  <wsdl:message name="GetPointWarningInfoSeachNumHttpGetOut">
    <wsdl:part name="Body" element="tns:int" />
  </wsdl:message>
  <wsdl:message name="GetPointWarningInfoSeachNumNewHttpGetIn">
    <wsdl:part name="strPointCodes" type="s:string" />
    <wsdl:part name="strDayTimeStrat" type="s:string" />
    <wsdl:part name="strDayTimeEnd" type="s:string" />
    <wsdl:part name="strUserName" type="s:string" />
    <wsdl:part name="strUserPwd" type="s:string" />
  </wsdl:message>
  <wsdl:message name="GetPointWarningInfoSeachNumNewHttpGetOut">
    <wsdl:part name="Body" element="tns:int" />
  </wsdl:message>
  <wsdl:message name="GetPointWarningInfoHttpGetIn">
    <wsdl:part name="iPage" type="s:string" />
    <wsdl:part name="iRows" type="s:string" />
    <wsdl:part name="strUserName" type="s:string" />
    <wsdl:part name="strUserPwd" type="s:string" />
  </wsdl:message>
  <wsdl:message name="GetPointWarningInfoHttpGetOut">
    <wsdl:part name="Body" element="tns:DataSet" />
  </wsdl:message>
  <wsdl:message name="GetPointWarningInfoNewHttpGetIn">
    <wsdl:part name="iPage" type="s:string" />
    <wsdl:part name="iRows" type="s:string" />
    <wsdl:part name="strUserName" type="s:string" />
    <wsdl:part name="strUserPwd" type="s:string" />
  </wsdl:message>
  <wsdl:message name="GetPointWarningInfoNewHttpGetOut">
    <wsdl:part name="Body" element="tns:DataSet" />
  </wsdl:message>
  <wsdl:message name="GetPointWarningInfoSeachHttpGetIn">
    <wsdl:part name="strPointCodes" type="s:string" />
    <wsdl:part name="strDayTimeStrat" type="s:string" />
    <wsdl:part name="strDayTimeEnd" type="s:string" />
    <wsdl:part name="iPage" type="s:string" />
    <wsdl:part name="iRows" type="s:string" />
    <wsdl:part name="strUserName" type="s:string" />
    <wsdl:part name="strUserPwd" type="s:string" />
  </wsdl:message>
  <wsdl:message name="GetPointWarningInfoSeachHttpGetOut">
    <wsdl:part name="Body" element="tns:DataSet" />
  </wsdl:message>
  <wsdl:message name="GetPointWarningInfoSeachNewHttpGetIn">
    <wsdl:part name="strPointCodes" type="s:string" />
    <wsdl:part name="strDayTimeStrat" type="s:string" />
    <wsdl:part name="strDayTimeEnd" type="s:string" />
    <wsdl:part name="iPage" type="s:string" />
    <wsdl:part name="iRows" type="s:string" />
    <wsdl:part name="strUserName" type="s:string" />
    <wsdl:part name="strUserPwd" type="s:string" />
  </wsdl:message>
  <wsdl:message name="GetPointWarningInfoSeachNewHttpGetOut">
    <wsdl:part name="Body" element="tns:DataSet" />
  </wsdl:message>
  <wsdl:message name="GetCDictsHttpGetIn">
    <wsdl:part name="strUserName" type="s:string" />
    <wsdl:part name="strUserPwd" type="s:string" />
    <wsdl:part name="strDictCode" type="s:string" />
  </wsdl:message>
  <wsdl:message name="GetCDictsHttpGetOut">
    <wsdl:part name="Body" element="tns:ArrayOfTSysDictVo" />
  </wsdl:message>
  <wsdl:message name="CheckUserHttpPostIn">
    <wsdl:part name="strUserName" type="s:string" />
    <wsdl:part name="strUserPwd" type="s:string" />
  </wsdl:message>
  <wsdl:message name="CheckUserHttpPostOut">
    <wsdl:part name="Body" element="tns:boolean" />
  </wsdl:message>
  <wsdl:message name="GetAreaPointDataHttpPostIn">
    <wsdl:part name="strUserName" type="s:string" />
    <wsdl:part name="strUserPwd" type="s:string" />
  </wsdl:message>
  <wsdl:message name="GetAreaPointDataHttpPostOut">
    <wsdl:part name="Body" element="tns:DataSet" />
  </wsdl:message>
  <wsdl:message name="SavePwdHttpPostIn">
    <wsdl:part name="strUserName" type="s:string" />
    <wsdl:part name="strOldPwd" type="s:string" />
    <wsdl:part name="strNewPwd" type="s:string" />
  </wsdl:message>
  <wsdl:message name="SavePwdHttpPostOut">
    <wsdl:part name="Body" element="tns:boolean" />
  </wsdl:message>
  <wsdl:message name="GetUserInfoDataHttpPostIn">
    <wsdl:part name="strUserName" type="s:string" />
    <wsdl:part name="strUserPwd" type="s:string" />
  </wsdl:message>
  <wsdl:message name="GetUserInfoDataHttpPostOut">
    <wsdl:part name="Body" element="tns:TSysUserVo" />
  </wsdl:message>
  <wsdl:message name="GetPointInfoDataHttpPostIn">
    <wsdl:part name="strPointCode" type="s:string" />
    <wsdl:part name="strUserName" type="s:string" />
    <wsdl:part name="strUserPwd" type="s:string" />
  </wsdl:message>
  <wsdl:message name="GetPointInfoDataHttpPostOut">
    <wsdl:part name="Body" element="tns:TSysStationPointVo" />
  </wsdl:message>
  <wsdl:message name="GetAllPointStataHttpPostIn">
    <wsdl:part name="strUserName" type="s:string" />
    <wsdl:part name="strUserPwd" type="s:string" />
  </wsdl:message>
  <wsdl:message name="GetAllPointStataHttpPostOut">
    <wsdl:part name="Body" element="tns:DataSet" />
  </wsdl:message>
  <wsdl:message name="GetNoiseRealTimeHttpPostIn">
    <wsdl:part name="strUserName" type="s:string" />
    <wsdl:part name="strUserPwd" type="s:string" />
  </wsdl:message>
  <wsdl:message name="GetNoiseRealTimeHttpPostOut">
    <wsdl:part name="Body" element="tns:DataSet" />
  </wsdl:message>
  <wsdl:message name="GetWeatherRealTimeHttpPostIn">
    <wsdl:part name="strUserName" type="s:string" />
    <wsdl:part name="strUserPwd" type="s:string" />
  </wsdl:message>
  <wsdl:message name="GetWeatherRealTimeHttpPostOut">
    <wsdl:part name="Body" element="tns:DataSet" />
  </wsdl:message>
  <wsdl:message name="GetTrafficRealTimeHttpPostIn">
    <wsdl:part name="strUserName" type="s:string" />
    <wsdl:part name="strUserPwd" type="s:string" />
  </wsdl:message>
  <wsdl:message name="GetTrafficRealTimeHttpPostOut">
    <wsdl:part name="Body" element="tns:DataSet" />
  </wsdl:message>
  <wsdl:message name="GetSinglePointDayReport_HeadHttpPostIn">
    <wsdl:part name="strPointCode" type="s:string" />
    <wsdl:part name="strDayTime" type="s:string" />
    <wsdl:part name="strUserName" type="s:string" />
    <wsdl:part name="strUserPwd" type="s:string" />
  </wsdl:message>
  <wsdl:message name="GetSinglePointDayReport_HeadHttpPostOut">
    <wsdl:part name="Body" element="tns:DataSet" />
  </wsdl:message>
  <wsdl:message name="GetSinglePointDayReport_BodyHttpPostIn">
    <wsdl:part name="strPointCode" type="s:string" />
    <wsdl:part name="strDayTime" type="s:string" />
    <wsdl:part name="strUserName" type="s:string" />
    <wsdl:part name="strUserPwd" type="s:string" />
  </wsdl:message>
  <wsdl:message name="GetSinglePointDayReport_BodyHttpPostOut">
    <wsdl:part name="Body" element="tns:DataSet" />
  </wsdl:message>
  <wsdl:message name="GetSinglePointMonthReport_HeadHttpPostIn">
    <wsdl:part name="strPointCode" type="s:string" />
    <wsdl:part name="strDayTime" type="s:string" />
    <wsdl:part name="strUserName" type="s:string" />
    <wsdl:part name="strUserPwd" type="s:string" />
  </wsdl:message>
  <wsdl:message name="GetSinglePointMonthReport_HeadHttpPostOut">
    <wsdl:part name="Body" element="tns:DataSet" />
  </wsdl:message>
  <wsdl:message name="GetSinglePointMonthReport_BodyHttpPostIn">
    <wsdl:part name="strPointCode" type="s:string" />
    <wsdl:part name="strDayTime" type="s:string" />
    <wsdl:part name="strUserName" type="s:string" />
    <wsdl:part name="strUserPwd" type="s:string" />
  </wsdl:message>
  <wsdl:message name="GetSinglePointMonthReport_BodyHttpPostOut">
    <wsdl:part name="Body" element="tns:DataSet" />
  </wsdl:message>
  <wsdl:message name="GetSinglePointQuarterReport_HeadHttpPostIn">
    <wsdl:part name="strPointCode" type="s:string" />
    <wsdl:part name="strDayTime" type="s:string" />
    <wsdl:part name="strUserName" type="s:string" />
    <wsdl:part name="strUserPwd" type="s:string" />
  </wsdl:message>
  <wsdl:message name="GetSinglePointQuarterReport_HeadHttpPostOut">
    <wsdl:part name="Body" element="tns:DataSet" />
  </wsdl:message>
  <wsdl:message name="GetSinglePointQuarterReport_BodyHttpPostIn">
    <wsdl:part name="strPointCode" type="s:string" />
    <wsdl:part name="strDayTime" type="s:string" />
    <wsdl:part name="strUserName" type="s:string" />
    <wsdl:part name="strUserPwd" type="s:string" />
  </wsdl:message>
  <wsdl:message name="GetSinglePointQuarterReport_BodyHttpPostOut">
    <wsdl:part name="Body" element="tns:DataSet" />
  </wsdl:message>
  <wsdl:message name="GetDistributionStatistics_BodyHttpPostIn">
    <wsdl:part name="strPointCode" type="s:string" />
    <wsdl:part name="strDayTime" type="s:string" />
    <wsdl:part name="strUserName" type="s:string" />
    <wsdl:part name="strUserPwd" type="s:string" />
  </wsdl:message>
  <wsdl:message name="GetDistributionStatistics_BodyHttpPostOut">
    <wsdl:part name="Body" element="tns:DataSet" />
  </wsdl:message>
  <wsdl:message name="GetDynamicAnalysis_BodyHttpPostIn">
    <wsdl:part name="strPointCode" type="s:string" />
    <wsdl:part name="strDayTimeStrat" type="s:string" />
    <wsdl:part name="strDayTimeEnd" type="s:string" />
    <wsdl:part name="strHour" type="s:string" />
    <wsdl:part name="strUserName" type="s:string" />
    <wsdl:part name="strUserPwd" type="s:string" />
  </wsdl:message>
  <wsdl:message name="GetDynamicAnalysis_BodyHttpPostOut">
    <wsdl:part name="Body" element="tns:DataSet" />
  </wsdl:message>
  <wsdl:message name="GetRelativityData_BodyHttpPostIn">
    <wsdl:part name="strPointCode" type="s:string" />
    <wsdl:part name="strDayTime" type="s:string" />
    <wsdl:part name="strUserName" type="s:string" />
    <wsdl:part name="strUserPwd" type="s:string" />
  </wsdl:message>
  <wsdl:message name="GetRelativityData_BodyHttpPostOut">
    <wsdl:part name="Body" element="tns:DataSet" />
  </wsdl:message>
  <wsdl:message name="GetCollectionRate_Noise_BodyHttpPostIn">
    <wsdl:part name="strPointCodes" type="s:string" />
    <wsdl:part name="strDayTime" type="s:string" />
    <wsdl:part name="strUserName" type="s:string" />
    <wsdl:part name="strUserPwd" type="s:string" />
  </wsdl:message>
  <wsdl:message name="GetCollectionRate_Noise_BodyHttpPostOut">
    <wsdl:part name="Body" element="tns:DataSet" />
  </wsdl:message>
  <wsdl:message name="GetCollectionRate_Weather_BodyHttpPostIn">
    <wsdl:part name="strPointCodes" type="s:string" />
    <wsdl:part name="strDayTime" type="s:string" />
    <wsdl:part name="strUserName" type="s:string" />
    <wsdl:part name="strUserPwd" type="s:string" />
  </wsdl:message>
  <wsdl:message name="GetCollectionRate_Weather_BodyHttpPostOut">
    <wsdl:part name="Body" element="tns:DataSet" />
  </wsdl:message>
  <wsdl:message name="GetCollectionRate_Traffic_BodyHttpPostIn">
    <wsdl:part name="strPointCodes" type="s:string" />
    <wsdl:part name="strDayTime" type="s:string" />
    <wsdl:part name="strUserName" type="s:string" />
    <wsdl:part name="strUserPwd" type="s:string" />
  </wsdl:message>
  <wsdl:message name="GetCollectionRate_Traffic_BodyHttpPostOut">
    <wsdl:part name="Body" element="tns:DataSet" />
  </wsdl:message>
  <wsdl:message name="GetInitMapInfoHttpPostIn">
    <wsdl:part name="strUserName" type="s:string" />
    <wsdl:part name="strUserPwd" type="s:string" />
  </wsdl:message>
  <wsdl:message name="GetInitMapInfoHttpPostOut">
    <wsdl:part name="Body" element="tns:TSysAreaVo" />
  </wsdl:message>
  <wsdl:message name="GetInitPointInfoHttpPostIn">
    <wsdl:part name="strUserName" type="s:string" />
    <wsdl:part name="strUserPwd" type="s:string" />
  </wsdl:message>
  <wsdl:message name="GetInitPointInfoHttpPostOut">
    <wsdl:part name="Body" element="tns:DataSet" />
  </wsdl:message>
  <wsdl:message name="GetInitPointInfoAllHttpPostIn">
    <wsdl:part name="strUserName" type="s:string" />
    <wsdl:part name="strUserPwd" type="s:string" />
    <wsdl:part name="strPtName" type="s:string" />
    <wsdl:part name="strPtCateGory" type="s:string" />
    <wsdl:part name="strPtService" type="s:string" />
  </wsdl:message>
  <wsdl:message name="GetInitPointInfoAllHttpPostOut">
    <wsdl:part name="Body" element="tns:DataSet" />
  </wsdl:message>
  <wsdl:message name="GetPointWarningInfoNumHttpPostIn">
    <wsdl:part name="strUserName" type="s:string" />
    <wsdl:part name="strUserPwd" type="s:string" />
  </wsdl:message>
  <wsdl:message name="GetPointWarningInfoNumHttpPostOut">
    <wsdl:part name="Body" element="tns:int" />
  </wsdl:message>
  <wsdl:message name="GetPointWarningInfoNumNewHttpPostIn">
    <wsdl:part name="strUserName" type="s:string" />
    <wsdl:part name="strUserPwd" type="s:string" />
  </wsdl:message>
  <wsdl:message name="GetPointWarningInfoNumNewHttpPostOut">
    <wsdl:part name="Body" element="tns:int" />
  </wsdl:message>
  <wsdl:message name="GetPointWarningInfoSeachNumHttpPostIn">
    <wsdl:part name="strPointCodes" type="s:string" />
    <wsdl:part name="strDayTimeStrat" type="s:string" />
    <wsdl:part name="strDayTimeEnd" type="s:string" />
    <wsdl:part name="strUserName" type="s:string" />
    <wsdl:part name="strUserPwd" type="s:string" />
  </wsdl:message>
  <wsdl:message name="GetPointWarningInfoSeachNumHttpPostOut">
    <wsdl:part name="Body" element="tns:int" />
  </wsdl:message>
  <wsdl:message name="GetPointWarningInfoSeachNumNewHttpPostIn">
    <wsdl:part name="strPointCodes" type="s:string" />
    <wsdl:part name="strDayTimeStrat" type="s:string" />
    <wsdl:part name="strDayTimeEnd" type="s:string" />
    <wsdl:part name="strUserName" type="s:string" />
    <wsdl:part name="strUserPwd" type="s:string" />
  </wsdl:message>
  <wsdl:message name="GetPointWarningInfoSeachNumNewHttpPostOut">
    <wsdl:part name="Body" element="tns:int" />
  </wsdl:message>
  <wsdl:message name="GetPointWarningInfoHttpPostIn">
    <wsdl:part name="iPage" type="s:string" />
    <wsdl:part name="iRows" type="s:string" />
    <wsdl:part name="strUserName" type="s:string" />
    <wsdl:part name="strUserPwd" type="s:string" />
  </wsdl:message>
  <wsdl:message name="GetPointWarningInfoHttpPostOut">
    <wsdl:part name="Body" element="tns:DataSet" />
  </wsdl:message>
  <wsdl:message name="GetPointWarningInfoNewHttpPostIn">
    <wsdl:part name="iPage" type="s:string" />
    <wsdl:part name="iRows" type="s:string" />
    <wsdl:part name="strUserName" type="s:string" />
    <wsdl:part name="strUserPwd" type="s:string" />
  </wsdl:message>
  <wsdl:message name="GetPointWarningInfoNewHttpPostOut">
    <wsdl:part name="Body" element="tns:DataSet" />
  </wsdl:message>
  <wsdl:message name="GetPointWarningInfoSeachHttpPostIn">
    <wsdl:part name="strPointCodes" type="s:string" />
    <wsdl:part name="strDayTimeStrat" type="s:string" />
    <wsdl:part name="strDayTimeEnd" type="s:string" />
    <wsdl:part name="iPage" type="s:string" />
    <wsdl:part name="iRows" type="s:string" />
    <wsdl:part name="strUserName" type="s:string" />
    <wsdl:part name="strUserPwd" type="s:string" />
  </wsdl:message>
  <wsdl:message name="GetPointWarningInfoSeachHttpPostOut">
    <wsdl:part name="Body" element="tns:DataSet" />
  </wsdl:message>
  <wsdl:message name="GetPointWarningInfoSeachNewHttpPostIn">
    <wsdl:part name="strPointCodes" type="s:string" />
    <wsdl:part name="strDayTimeStrat" type="s:string" />
    <wsdl:part name="strDayTimeEnd" type="s:string" />
    <wsdl:part name="iPage" type="s:string" />
    <wsdl:part name="iRows" type="s:string" />
    <wsdl:part name="strUserName" type="s:string" />
    <wsdl:part name="strUserPwd" type="s:string" />
  </wsdl:message>
  <wsdl:message name="GetPointWarningInfoSeachNewHttpPostOut">
    <wsdl:part name="Body" element="tns:DataSet" />
  </wsdl:message>
  <wsdl:message name="GetCDictsHttpPostIn">
    <wsdl:part name="strUserName" type="s:string" />
    <wsdl:part name="strUserPwd" type="s:string" />
    <wsdl:part name="strDictCode" type="s:string" />
  </wsdl:message>
  <wsdl:message name="GetCDictsHttpPostOut">
    <wsdl:part name="Body" element="tns:ArrayOfTSysDictVo" />
  </wsdl:message>
  <wsdl:portType name="WebServiceSoap">
    <wsdl:operation name="CheckUser">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">登录验证</wsdl:documentation>
      <wsdl:input message="tns:CheckUserSoapIn" />
      <wsdl:output message="tns:CheckUserSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="GetAreaPointData">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">获取用户有权限的监测点和区域</wsdl:documentation>
      <wsdl:input message="tns:GetAreaPointDataSoapIn" />
      <wsdl:output message="tns:GetAreaPointDataSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="SavePwd">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">保存密码</wsdl:documentation>
      <wsdl:input message="tns:SavePwdSoapIn" />
      <wsdl:output message="tns:SavePwdSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="GetUserInfoData">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">获取用户详细信息</wsdl:documentation>
      <wsdl:input message="tns:GetUserInfoDataSoapIn" />
      <wsdl:output message="tns:GetUserInfoDataSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="GetPointInfoData">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">获取监测点详细信息</wsdl:documentation>
      <wsdl:input message="tns:GetPointInfoDataSoapIn" />
      <wsdl:output message="tns:GetPointInfoDataSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="GetAllPointStata">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">获取所有监测点状态</wsdl:documentation>
      <wsdl:input message="tns:GetAllPointStataSoapIn" />
      <wsdl:output message="tns:GetAllPointStataSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="GetNoiseRealTime">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">获取噪声实时数据</wsdl:documentation>
      <wsdl:input message="tns:GetNoiseRealTimeSoapIn" />
      <wsdl:output message="tns:GetNoiseRealTimeSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="GetWeatherRealTime">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">获取气象实时数据</wsdl:documentation>
      <wsdl:input message="tns:GetWeatherRealTimeSoapIn" />
      <wsdl:output message="tns:GetWeatherRealTimeSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="GetTrafficRealTime">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">获取车流量实时数据</wsdl:documentation>
      <wsdl:input message="tns:GetTrafficRealTimeSoapIn" />
      <wsdl:output message="tns:GetTrafficRealTimeSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="GetSinglePointDayReport_Head">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">获取噪声单点日报表头数据</wsdl:documentation>
      <wsdl:input message="tns:GetSinglePointDayReport_HeadSoapIn" />
      <wsdl:output message="tns:GetSinglePointDayReport_HeadSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="GetSinglePointDayReport_Body">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">获取噪声单点日报表数据</wsdl:documentation>
      <wsdl:input message="tns:GetSinglePointDayReport_BodySoapIn" />
      <wsdl:output message="tns:GetSinglePointDayReport_BodySoapOut" />
    </wsdl:operation>
    <wsdl:operation name="GetSinglePointMonthReport_Head">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">获取噪声单点月报表头数据</wsdl:documentation>
      <wsdl:input message="tns:GetSinglePointMonthReport_HeadSoapIn" />
      <wsdl:output message="tns:GetSinglePointMonthReport_HeadSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="GetSinglePointMonthReport_Body">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">获取噪声单点月报表数据</wsdl:documentation>
      <wsdl:input message="tns:GetSinglePointMonthReport_BodySoapIn" />
      <wsdl:output message="tns:GetSinglePointMonthReport_BodySoapOut" />
    </wsdl:operation>
    <wsdl:operation name="GetSinglePointQuarterReport_Head">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">获取噪声单点季报表头数据</wsdl:documentation>
      <wsdl:input message="tns:GetSinglePointQuarterReport_HeadSoapIn" />
      <wsdl:output message="tns:GetSinglePointQuarterReport_HeadSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="GetSinglePointQuarterReport_Body">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">获取噪声单点季报表数据</wsdl:documentation>
      <wsdl:input message="tns:GetSinglePointQuarterReport_BodySoapIn" />
      <wsdl:output message="tns:GetSinglePointQuarterReport_BodySoapOut" />
    </wsdl:operation>
    <wsdl:operation name="GetDistributionStatistics_Body">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">获取分布统计数据</wsdl:documentation>
      <wsdl:input message="tns:GetDistributionStatistics_BodySoapIn" />
      <wsdl:output message="tns:GetDistributionStatistics_BodySoapOut" />
    </wsdl:operation>
    <wsdl:operation name="GetDynamicAnalysis_Body">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">获取动态分析数据</wsdl:documentation>
      <wsdl:input message="tns:GetDynamicAnalysis_BodySoapIn" />
      <wsdl:output message="tns:GetDynamicAnalysis_BodySoapOut" />
    </wsdl:operation>
    <wsdl:operation name="GetRelativityData_Body">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">获取相关性统计数据</wsdl:documentation>
      <wsdl:input message="tns:GetRelativityData_BodySoapIn" />
      <wsdl:output message="tns:GetRelativityData_BodySoapOut" />
    </wsdl:operation>
    <wsdl:operation name="GetCollectionRate_Noise_Body">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">获取噪声分钟采集率</wsdl:documentation>
      <wsdl:input message="tns:GetCollectionRate_Noise_BodySoapIn" />
      <wsdl:output message="tns:GetCollectionRate_Noise_BodySoapOut" />
    </wsdl:operation>
    <wsdl:operation name="GetCollectionRate_Weather_Body">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">获取气象分钟采集率</wsdl:documentation>
      <wsdl:input message="tns:GetCollectionRate_Weather_BodySoapIn" />
      <wsdl:output message="tns:GetCollectionRate_Weather_BodySoapOut" />
    </wsdl:operation>
    <wsdl:operation name="GetCollectionRate_Traffic_Body">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">获取车流量分钟采集率</wsdl:documentation>
      <wsdl:input message="tns:GetCollectionRate_Traffic_BodySoapIn" />
      <wsdl:output message="tns:GetCollectionRate_Traffic_BodySoapOut" />
    </wsdl:operation>
    <wsdl:operation name="GetInitMapInfo">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">获取用户所在区域的地图基础数据</wsdl:documentation>
      <wsdl:input message="tns:GetInitMapInfoSoapIn" />
      <wsdl:output message="tns:GetInitMapInfoSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="GetInitPointInfo">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">获取用户有权查看的监测点信息</wsdl:documentation>
      <wsdl:input message="tns:GetInitPointInfoSoapIn" />
      <wsdl:output message="tns:GetInitPointInfoSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="GetInitPointInfoAll">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">获取用户有权查看的监测点信息,从给定值</wsdl:documentation>
      <wsdl:input message="tns:GetInitPointInfoAllSoapIn" />
      <wsdl:output message="tns:GetInitPointInfoAllSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="GetPointWarningInfoNum">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">获取告警信息表中的个数</wsdl:documentation>
      <wsdl:input message="tns:GetPointWarningInfoNumSoapIn" />
      <wsdl:output message="tns:GetPointWarningInfoNumSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="GetPointWarningInfoNumNew">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">获取告警信息表中的个数(新方式)</wsdl:documentation>
      <wsdl:input message="tns:GetPointWarningInfoNumNewSoapIn" />
      <wsdl:output message="tns:GetPointWarningInfoNumNewSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="GetPointWarningInfoSeachNum">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">获取告警信息表中的指定数据个数</wsdl:documentation>
      <wsdl:input message="tns:GetPointWarningInfoSeachNumSoapIn" />
      <wsdl:output message="tns:GetPointWarningInfoSeachNumSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="GetPointWarningInfoSeachNumNew">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">获取告警信息表中的指定数据个数（新方式）</wsdl:documentation>
      <wsdl:input message="tns:GetPointWarningInfoSeachNumNewSoapIn" />
      <wsdl:output message="tns:GetPointWarningInfoSeachNumNewSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="GetPointWarningInfo">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">获取告警信息表中的指定数据</wsdl:documentation>
      <wsdl:input message="tns:GetPointWarningInfoSoapIn" />
      <wsdl:output message="tns:GetPointWarningInfoSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="GetPointWarningInfoNew">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">获取告警信息表中的指定数据（新方式）</wsdl:documentation>
      <wsdl:input message="tns:GetPointWarningInfoNewSoapIn" />
      <wsdl:output message="tns:GetPointWarningInfoNewSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="GetPointWarningInfoSeach">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">获取告警信息表中的指定数据</wsdl:documentation>
      <wsdl:input message="tns:GetPointWarningInfoSeachSoapIn" />
      <wsdl:output message="tns:GetPointWarningInfoSeachSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="GetPointWarningInfoSeachNew">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">获取告警信息表中的指定数据（新方式）</wsdl:documentation>
      <wsdl:input message="tns:GetPointWarningInfoSeachNewSoapIn" />
      <wsdl:output message="tns:GetPointWarningInfoSeachNewSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="GetCDicts">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">获取子字典项</wsdl:documentation>
      <wsdl:input message="tns:GetCDictsSoapIn" />
      <wsdl:output message="tns:GetCDictsSoapOut" />
    </wsdl:operation>
  </wsdl:portType>
  <wsdl:portType name="WebServiceHttpGet">
    <wsdl:operation name="CheckUser">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">登录验证</wsdl:documentation>
      <wsdl:input message="tns:CheckUserHttpGetIn" />
      <wsdl:output message="tns:CheckUserHttpGetOut" />
    </wsdl:operation>
    <wsdl:operation name="GetAreaPointData">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">获取用户有权限的监测点和区域</wsdl:documentation>
      <wsdl:input message="tns:GetAreaPointDataHttpGetIn" />
      <wsdl:output message="tns:GetAreaPointDataHttpGetOut" />
    </wsdl:operation>
    <wsdl:operation name="SavePwd">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">保存密码</wsdl:documentation>
      <wsdl:input message="tns:SavePwdHttpGetIn" />
      <wsdl:output message="tns:SavePwdHttpGetOut" />
    </wsdl:operation>
    <wsdl:operation name="GetUserInfoData">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">获取用户详细信息</wsdl:documentation>
      <wsdl:input message="tns:GetUserInfoDataHttpGetIn" />
      <wsdl:output message="tns:GetUserInfoDataHttpGetOut" />
    </wsdl:operation>
    <wsdl:operation name="GetPointInfoData">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">获取监测点详细信息</wsdl:documentation>
      <wsdl:input message="tns:GetPointInfoDataHttpGetIn" />
      <wsdl:output message="tns:GetPointInfoDataHttpGetOut" />
    </wsdl:operation>
    <wsdl:operation name="GetAllPointStata">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">获取所有监测点状态</wsdl:documentation>
      <wsdl:input message="tns:GetAllPointStataHttpGetIn" />
      <wsdl:output message="tns:GetAllPointStataHttpGetOut" />
    </wsdl:operation>
    <wsdl:operation name="GetNoiseRealTime">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">获取噪声实时数据</wsdl:documentation>
      <wsdl:input message="tns:GetNoiseRealTimeHttpGetIn" />
      <wsdl:output message="tns:GetNoiseRealTimeHttpGetOut" />
    </wsdl:operation>
    <wsdl:operation name="GetWeatherRealTime">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">获取气象实时数据</wsdl:documentation>
      <wsdl:input message="tns:GetWeatherRealTimeHttpGetIn" />
      <wsdl:output message="tns:GetWeatherRealTimeHttpGetOut" />
    </wsdl:operation>
    <wsdl:operation name="GetTrafficRealTime">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">获取车流量实时数据</wsdl:documentation>
      <wsdl:input message="tns:GetTrafficRealTimeHttpGetIn" />
      <wsdl:output message="tns:GetTrafficRealTimeHttpGetOut" />
    </wsdl:operation>
    <wsdl:operation name="GetSinglePointDayReport_Head">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">获取噪声单点日报表头数据</wsdl:documentation>
      <wsdl:input message="tns:GetSinglePointDayReport_HeadHttpGetIn" />
      <wsdl:output message="tns:GetSinglePointDayReport_HeadHttpGetOut" />
    </wsdl:operation>
    <wsdl:operation name="GetSinglePointDayReport_Body">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">获取噪声单点日报表数据</wsdl:documentation>
      <wsdl:input message="tns:GetSinglePointDayReport_BodyHttpGetIn" />
      <wsdl:output message="tns:GetSinglePointDayReport_BodyHttpGetOut" />
    </wsdl:operation>
    <wsdl:operation name="GetSinglePointMonthReport_Head">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">获取噪声单点月报表头数据</wsdl:documentation>
      <wsdl:input message="tns:GetSinglePointMonthReport_HeadHttpGetIn" />
      <wsdl:output message="tns:GetSinglePointMonthReport_HeadHttpGetOut" />
    </wsdl:operation>
    <wsdl:operation name="GetSinglePointMonthReport_Body">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">获取噪声单点月报表数据</wsdl:documentation>
      <wsdl:input message="tns:GetSinglePointMonthReport_BodyHttpGetIn" />
      <wsdl:output message="tns:GetSinglePointMonthReport_BodyHttpGetOut" />
    </wsdl:operation>
    <wsdl:operation name="GetSinglePointQuarterReport_Head">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">获取噪声单点季报表头数据</wsdl:documentation>
      <wsdl:input message="tns:GetSinglePointQuarterReport_HeadHttpGetIn" />
      <wsdl:output message="tns:GetSinglePointQuarterReport_HeadHttpGetOut" />
    </wsdl:operation>
    <wsdl:operation name="GetSinglePointQuarterReport_Body">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">获取噪声单点季报表数据</wsdl:documentation>
      <wsdl:input message="tns:GetSinglePointQuarterReport_BodyHttpGetIn" />
      <wsdl:output message="tns:GetSinglePointQuarterReport_BodyHttpGetOut" />
    </wsdl:operation>
    <wsdl:operation name="GetDistributionStatistics_Body">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">获取分布统计数据</wsdl:documentation>
      <wsdl:input message="tns:GetDistributionStatistics_BodyHttpGetIn" />
      <wsdl:output message="tns:GetDistributionStatistics_BodyHttpGetOut" />
    </wsdl:operation>
    <wsdl:operation name="GetDynamicAnalysis_Body">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">获取动态分析数据</wsdl:documentation>
      <wsdl:input message="tns:GetDynamicAnalysis_BodyHttpGetIn" />
      <wsdl:output message="tns:GetDynamicAnalysis_BodyHttpGetOut" />
    </wsdl:operation>
    <wsdl:operation name="GetRelativityData_Body">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">获取相关性统计数据</wsdl:documentation>
      <wsdl:input message="tns:GetRelativityData_BodyHttpGetIn" />
      <wsdl:output message="tns:GetRelativityData_BodyHttpGetOut" />
    </wsdl:operation>
    <wsdl:operation name="GetCollectionRate_Noise_Body">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">获取噪声分钟采集率</wsdl:documentation>
      <wsdl:input message="tns:GetCollectionRate_Noise_BodyHttpGetIn" />
      <wsdl:output message="tns:GetCollectionRate_Noise_BodyHttpGetOut" />
    </wsdl:operation>
    <wsdl:operation name="GetCollectionRate_Weather_Body">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">获取气象分钟采集率</wsdl:documentation>
      <wsdl:input message="tns:GetCollectionRate_Weather_BodyHttpGetIn" />
      <wsdl:output message="tns:GetCollectionRate_Weather_BodyHttpGetOut" />
    </wsdl:operation>
    <wsdl:operation name="GetCollectionRate_Traffic_Body">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">获取车流量分钟采集率</wsdl:documentation>
      <wsdl:input message="tns:GetCollectionRate_Traffic_BodyHttpGetIn" />
      <wsdl:output message="tns:GetCollectionRate_Traffic_BodyHttpGetOut" />
    </wsdl:operation>
    <wsdl:operation name="GetInitMapInfo">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">获取用户所在区域的地图基础数据</wsdl:documentation>
      <wsdl:input message="tns:GetInitMapInfoHttpGetIn" />
      <wsdl:output message="tns:GetInitMapInfoHttpGetOut" />
    </wsdl:operation>
    <wsdl:operation name="GetInitPointInfo">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">获取用户有权查看的监测点信息</wsdl:documentation>
      <wsdl:input message="tns:GetInitPointInfoHttpGetIn" />
      <wsdl:output message="tns:GetInitPointInfoHttpGetOut" />
    </wsdl:operation>
    <wsdl:operation name="GetInitPointInfoAll">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">获取用户有权查看的监测点信息,从给定值</wsdl:documentation>
      <wsdl:input message="tns:GetInitPointInfoAllHttpGetIn" />
      <wsdl:output message="tns:GetInitPointInfoAllHttpGetOut" />
    </wsdl:operation>
    <wsdl:operation name="GetPointWarningInfoNum">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">获取告警信息表中的个数</wsdl:documentation>
      <wsdl:input message="tns:GetPointWarningInfoNumHttpGetIn" />
      <wsdl:output message="tns:GetPointWarningInfoNumHttpGetOut" />
    </wsdl:operation>
    <wsdl:operation name="GetPointWarningInfoNumNew">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">获取告警信息表中的个数(新方式)</wsdl:documentation>
      <wsdl:input message="tns:GetPointWarningInfoNumNewHttpGetIn" />
      <wsdl:output message="tns:GetPointWarningInfoNumNewHttpGetOut" />
    </wsdl:operation>
    <wsdl:operation name="GetPointWarningInfoSeachNum">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">获取告警信息表中的指定数据个数</wsdl:documentation>
      <wsdl:input message="tns:GetPointWarningInfoSeachNumHttpGetIn" />
      <wsdl:output message="tns:GetPointWarningInfoSeachNumHttpGetOut" />
    </wsdl:operation>
    <wsdl:operation name="GetPointWarningInfoSeachNumNew">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">获取告警信息表中的指定数据个数（新方式）</wsdl:documentation>
      <wsdl:input message="tns:GetPointWarningInfoSeachNumNewHttpGetIn" />
      <wsdl:output message="tns:GetPointWarningInfoSeachNumNewHttpGetOut" />
    </wsdl:operation>
    <wsdl:operation name="GetPointWarningInfo">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">获取告警信息表中的指定数据</wsdl:documentation>
      <wsdl:input message="tns:GetPointWarningInfoHttpGetIn" />
      <wsdl:output message="tns:GetPointWarningInfoHttpGetOut" />
    </wsdl:operation>
    <wsdl:operation name="GetPointWarningInfoNew">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">获取告警信息表中的指定数据（新方式）</wsdl:documentation>
      <wsdl:input message="tns:GetPointWarningInfoNewHttpGetIn" />
      <wsdl:output message="tns:GetPointWarningInfoNewHttpGetOut" />
    </wsdl:operation>
    <wsdl:operation name="GetPointWarningInfoSeach">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">获取告警信息表中的指定数据</wsdl:documentation>
      <wsdl:input message="tns:GetPointWarningInfoSeachHttpGetIn" />
      <wsdl:output message="tns:GetPointWarningInfoSeachHttpGetOut" />
    </wsdl:operation>
    <wsdl:operation name="GetPointWarningInfoSeachNew">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">获取告警信息表中的指定数据（新方式）</wsdl:documentation>
      <wsdl:input message="tns:GetPointWarningInfoSeachNewHttpGetIn" />
      <wsdl:output message="tns:GetPointWarningInfoSeachNewHttpGetOut" />
    </wsdl:operation>
    <wsdl:operation name="GetCDicts">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">获取子字典项</wsdl:documentation>
      <wsdl:input message="tns:GetCDictsHttpGetIn" />
      <wsdl:output message="tns:GetCDictsHttpGetOut" />
    </wsdl:operation>
  </wsdl:portType>
  <wsdl:portType name="WebServiceHttpPost">
    <wsdl:operation name="CheckUser">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">登录验证</wsdl:documentation>
      <wsdl:input message="tns:CheckUserHttpPostIn" />
      <wsdl:output message="tns:CheckUserHttpPostOut" />
    </wsdl:operation>
    <wsdl:operation name="GetAreaPointData">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">获取用户有权限的监测点和区域</wsdl:documentation>
      <wsdl:input message="tns:GetAreaPointDataHttpPostIn" />
      <wsdl:output message="tns:GetAreaPointDataHttpPostOut" />
    </wsdl:operation>
    <wsdl:operation name="SavePwd">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">保存密码</wsdl:documentation>
      <wsdl:input message="tns:SavePwdHttpPostIn" />
      <wsdl:output message="tns:SavePwdHttpPostOut" />
    </wsdl:operation>
    <wsdl:operation name="GetUserInfoData">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">获取用户详细信息</wsdl:documentation>
      <wsdl:input message="tns:GetUserInfoDataHttpPostIn" />
      <wsdl:output message="tns:GetUserInfoDataHttpPostOut" />
    </wsdl:operation>
    <wsdl:operation name="GetPointInfoData">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">获取监测点详细信息</wsdl:documentation>
      <wsdl:input message="tns:GetPointInfoDataHttpPostIn" />
      <wsdl:output message="tns:GetPointInfoDataHttpPostOut" />
    </wsdl:operation>
    <wsdl:operation name="GetAllPointStata">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">获取所有监测点状态</wsdl:documentation>
      <wsdl:input message="tns:GetAllPointStataHttpPostIn" />
      <wsdl:output message="tns:GetAllPointStataHttpPostOut" />
    </wsdl:operation>
    <wsdl:operation name="GetNoiseRealTime">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">获取噪声实时数据</wsdl:documentation>
      <wsdl:input message="tns:GetNoiseRealTimeHttpPostIn" />
      <wsdl:output message="tns:GetNoiseRealTimeHttpPostOut" />
    </wsdl:operation>
    <wsdl:operation name="GetWeatherRealTime">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">获取气象实时数据</wsdl:documentation>
      <wsdl:input message="tns:GetWeatherRealTimeHttpPostIn" />
      <wsdl:output message="tns:GetWeatherRealTimeHttpPostOut" />
    </wsdl:operation>
    <wsdl:operation name="GetTrafficRealTime">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">获取车流量实时数据</wsdl:documentation>
      <wsdl:input message="tns:GetTrafficRealTimeHttpPostIn" />
      <wsdl:output message="tns:GetTrafficRealTimeHttpPostOut" />
    </wsdl:operation>
    <wsdl:operation name="GetSinglePointDayReport_Head">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">获取噪声单点日报表头数据</wsdl:documentation>
      <wsdl:input message="tns:GetSinglePointDayReport_HeadHttpPostIn" />
      <wsdl:output message="tns:GetSinglePointDayReport_HeadHttpPostOut" />
    </wsdl:operation>
    <wsdl:operation name="GetSinglePointDayReport_Body">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">获取噪声单点日报表数据</wsdl:documentation>
      <wsdl:input message="tns:GetSinglePointDayReport_BodyHttpPostIn" />
      <wsdl:output message="tns:GetSinglePointDayReport_BodyHttpPostOut" />
    </wsdl:operation>
    <wsdl:operation name="GetSinglePointMonthReport_Head">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">获取噪声单点月报表头数据</wsdl:documentation>
      <wsdl:input message="tns:GetSinglePointMonthReport_HeadHttpPostIn" />
      <wsdl:output message="tns:GetSinglePointMonthReport_HeadHttpPostOut" />
    </wsdl:operation>
    <wsdl:operation name="GetSinglePointMonthReport_Body">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">获取噪声单点月报表数据</wsdl:documentation>
      <wsdl:input message="tns:GetSinglePointMonthReport_BodyHttpPostIn" />
      <wsdl:output message="tns:GetSinglePointMonthReport_BodyHttpPostOut" />
    </wsdl:operation>
    <wsdl:operation name="GetSinglePointQuarterReport_Head">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">获取噪声单点季报表头数据</wsdl:documentation>
      <wsdl:input message="tns:GetSinglePointQuarterReport_HeadHttpPostIn" />
      <wsdl:output message="tns:GetSinglePointQuarterReport_HeadHttpPostOut" />
    </wsdl:operation>
    <wsdl:operation name="GetSinglePointQuarterReport_Body">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">获取噪声单点季报表数据</wsdl:documentation>
      <wsdl:input message="tns:GetSinglePointQuarterReport_BodyHttpPostIn" />
      <wsdl:output message="tns:GetSinglePointQuarterReport_BodyHttpPostOut" />
    </wsdl:operation>
    <wsdl:operation name="GetDistributionStatistics_Body">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">获取分布统计数据</wsdl:documentation>
      <wsdl:input message="tns:GetDistributionStatistics_BodyHttpPostIn" />
      <wsdl:output message="tns:GetDistributionStatistics_BodyHttpPostOut" />
    </wsdl:operation>
    <wsdl:operation name="GetDynamicAnalysis_Body">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">获取动态分析数据</wsdl:documentation>
      <wsdl:input message="tns:GetDynamicAnalysis_BodyHttpPostIn" />
      <wsdl:output message="tns:GetDynamicAnalysis_BodyHttpPostOut" />
    </wsdl:operation>
    <wsdl:operation name="GetRelativityData_Body">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">获取相关性统计数据</wsdl:documentation>
      <wsdl:input message="tns:GetRelativityData_BodyHttpPostIn" />
      <wsdl:output message="tns:GetRelativityData_BodyHttpPostOut" />
    </wsdl:operation>
    <wsdl:operation name="GetCollectionRate_Noise_Body">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">获取噪声分钟采集率</wsdl:documentation>
      <wsdl:input message="tns:GetCollectionRate_Noise_BodyHttpPostIn" />
      <wsdl:output message="tns:GetCollectionRate_Noise_BodyHttpPostOut" />
    </wsdl:operation>
    <wsdl:operation name="GetCollectionRate_Weather_Body">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">获取气象分钟采集率</wsdl:documentation>
      <wsdl:input message="tns:GetCollectionRate_Weather_BodyHttpPostIn" />
      <wsdl:output message="tns:GetCollectionRate_Weather_BodyHttpPostOut" />
    </wsdl:operation>
    <wsdl:operation name="GetCollectionRate_Traffic_Body">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">获取车流量分钟采集率</wsdl:documentation>
      <wsdl:input message="tns:GetCollectionRate_Traffic_BodyHttpPostIn" />
      <wsdl:output message="tns:GetCollectionRate_Traffic_BodyHttpPostOut" />
    </wsdl:operation>
    <wsdl:operation name="GetInitMapInfo">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">获取用户所在区域的地图基础数据</wsdl:documentation>
      <wsdl:input message="tns:GetInitMapInfoHttpPostIn" />
      <wsdl:output message="tns:GetInitMapInfoHttpPostOut" />
    </wsdl:operation>
    <wsdl:operation name="GetInitPointInfo">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">获取用户有权查看的监测点信息</wsdl:documentation>
      <wsdl:input message="tns:GetInitPointInfoHttpPostIn" />
      <wsdl:output message="tns:GetInitPointInfoHttpPostOut" />
    </wsdl:operation>
    <wsdl:operation name="GetInitPointInfoAll">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">获取用户有权查看的监测点信息,从给定值</wsdl:documentation>
      <wsdl:input message="tns:GetInitPointInfoAllHttpPostIn" />
      <wsdl:output message="tns:GetInitPointInfoAllHttpPostOut" />
    </wsdl:operation>
    <wsdl:operation name="GetPointWarningInfoNum">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">获取告警信息表中的个数</wsdl:documentation>
      <wsdl:input message="tns:GetPointWarningInfoNumHttpPostIn" />
      <wsdl:output message="tns:GetPointWarningInfoNumHttpPostOut" />
    </wsdl:operation>
    <wsdl:operation name="GetPointWarningInfoNumNew">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">获取告警信息表中的个数(新方式)</wsdl:documentation>
      <wsdl:input message="tns:GetPointWarningInfoNumNewHttpPostIn" />
      <wsdl:output message="tns:GetPointWarningInfoNumNewHttpPostOut" />
    </wsdl:operation>
    <wsdl:operation name="GetPointWarningInfoSeachNum">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">获取告警信息表中的指定数据个数</wsdl:documentation>
      <wsdl:input message="tns:GetPointWarningInfoSeachNumHttpPostIn" />
      <wsdl:output message="tns:GetPointWarningInfoSeachNumHttpPostOut" />
    </wsdl:operation>
    <wsdl:operation name="GetPointWarningInfoSeachNumNew">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">获取告警信息表中的指定数据个数（新方式）</wsdl:documentation>
      <wsdl:input message="tns:GetPointWarningInfoSeachNumNewHttpPostIn" />
      <wsdl:output message="tns:GetPointWarningInfoSeachNumNewHttpPostOut" />
    </wsdl:operation>
    <wsdl:operation name="GetPointWarningInfo">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">获取告警信息表中的指定数据</wsdl:documentation>
      <wsdl:input message="tns:GetPointWarningInfoHttpPostIn" />
      <wsdl:output message="tns:GetPointWarningInfoHttpPostOut" />
    </wsdl:operation>
    <wsdl:operation name="GetPointWarningInfoNew">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">获取告警信息表中的指定数据（新方式）</wsdl:documentation>
      <wsdl:input message="tns:GetPointWarningInfoNewHttpPostIn" />
      <wsdl:output message="tns:GetPointWarningInfoNewHttpPostOut" />
    </wsdl:operation>
    <wsdl:operation name="GetPointWarningInfoSeach">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">获取告警信息表中的指定数据</wsdl:documentation>
      <wsdl:input message="tns:GetPointWarningInfoSeachHttpPostIn" />
      <wsdl:output message="tns:GetPointWarningInfoSeachHttpPostOut" />
    </wsdl:operation>
    <wsdl:operation name="GetPointWarningInfoSeachNew">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">获取告警信息表中的指定数据（新方式）</wsdl:documentation>
      <wsdl:input message="tns:GetPointWarningInfoSeachNewHttpPostIn" />
      <wsdl:output message="tns:GetPointWarningInfoSeachNewHttpPostOut" />
    </wsdl:operation>
    <wsdl:operation name="GetCDicts">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">获取子字典项</wsdl:documentation>
      <wsdl:input message="tns:GetCDictsHttpPostIn" />
      <wsdl:output message="tns:GetCDictsHttpPostOut" />
    </wsdl:operation>
  </wsdl:portType>
  <wsdl:binding name="WebServiceSoap" type="tns:WebServiceSoap">
    <soap:binding transport="http://schemas.xmlsoap.org/soap/http" />
    <wsdl:operation name="CheckUser">
      <soap:operation soapAction="http://tempuri.org/CheckUser" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetAreaPointData">
      <soap:operation soapAction="http://tempuri.org/GetAreaPointData" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="SavePwd">
      <soap:operation soapAction="http://tempuri.org/SavePwd" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetUserInfoData">
      <soap:operation soapAction="http://tempuri.org/GetUserInfoData" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetPointInfoData">
      <soap:operation soapAction="http://tempuri.org/GetPointInfoData" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetAllPointStata">
      <soap:operation soapAction="http://tempuri.org/GetAllPointStata" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetNoiseRealTime">
      <soap:operation soapAction="http://tempuri.org/GetNoiseRealTime" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetWeatherRealTime">
      <soap:operation soapAction="http://tempuri.org/GetWeatherRealTime" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetTrafficRealTime">
      <soap:operation soapAction="http://tempuri.org/GetTrafficRealTime" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetSinglePointDayReport_Head">
      <soap:operation soapAction="http://tempuri.org/GetSinglePointDayReport_Head" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetSinglePointDayReport_Body">
      <soap:operation soapAction="http://tempuri.org/GetSinglePointDayReport_Body" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetSinglePointMonthReport_Head">
      <soap:operation soapAction="http://tempuri.org/GetSinglePointMonthReport_Head" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetSinglePointMonthReport_Body">
      <soap:operation soapAction="http://tempuri.org/GetSinglePointMonthReport_Body" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetSinglePointQuarterReport_Head">
      <soap:operation soapAction="http://tempuri.org/GetSinglePointQuarterReport_Head" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetSinglePointQuarterReport_Body">
      <soap:operation soapAction="http://tempuri.org/GetSinglePointQuarterReport_Body" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetDistributionStatistics_Body">
      <soap:operation soapAction="http://tempuri.org/GetDistributionStatistics_Body" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetDynamicAnalysis_Body">
      <soap:operation soapAction="http://tempuri.org/GetDynamicAnalysis_Body" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetRelativityData_Body">
      <soap:operation soapAction="http://tempuri.org/GetRelativityData_Body" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetCollectionRate_Noise_Body">
      <soap:operation soapAction="http://tempuri.org/GetCollectionRate_Noise_Body" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetCollectionRate_Weather_Body">
      <soap:operation soapAction="http://tempuri.org/GetCollectionRate_Weather_Body" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetCollectionRate_Traffic_Body">
      <soap:operation soapAction="http://tempuri.org/GetCollectionRate_Traffic_Body" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetInitMapInfo">
      <soap:operation soapAction="http://tempuri.org/GetInitMapInfo" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetInitPointInfo">
      <soap:operation soapAction="http://tempuri.org/GetInitPointInfo" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetInitPointInfoAll">
      <soap:operation soapAction="http://tempuri.org/GetInitPointInfoAll" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetPointWarningInfoNum">
      <soap:operation soapAction="http://tempuri.org/GetPointWarningInfoNum" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetPointWarningInfoNumNew">
      <soap:operation soapAction="http://tempuri.org/GetPointWarningInfoNumNew" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetPointWarningInfoSeachNum">
      <soap:operation soapAction="http://tempuri.org/GetPointWarningInfoSeachNum" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetPointWarningInfoSeachNumNew">
      <soap:operation soapAction="http://tempuri.org/GetPointWarningInfoSeachNumNew" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetPointWarningInfo">
      <soap:operation soapAction="http://tempuri.org/GetPointWarningInfo" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetPointWarningInfoNew">
      <soap:operation soapAction="http://tempuri.org/GetPointWarningInfoNew" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetPointWarningInfoSeach">
      <soap:operation soapAction="http://tempuri.org/GetPointWarningInfoSeach" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetPointWarningInfoSeachNew">
      <soap:operation soapAction="http://tempuri.org/GetPointWarningInfoSeachNew" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetCDicts">
      <soap:operation soapAction="http://tempuri.org/GetCDicts" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
  </wsdl:binding>
  <wsdl:binding name="WebServiceSoap12" type="tns:WebServiceSoap">
    <soap12:binding transport="http://schemas.xmlsoap.org/soap/http" />
    <wsdl:operation name="CheckUser">
      <soap12:operation soapAction="http://tempuri.org/CheckUser" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetAreaPointData">
      <soap12:operation soapAction="http://tempuri.org/GetAreaPointData" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="SavePwd">
      <soap12:operation soapAction="http://tempuri.org/SavePwd" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetUserInfoData">
      <soap12:operation soapAction="http://tempuri.org/GetUserInfoData" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetPointInfoData">
      <soap12:operation soapAction="http://tempuri.org/GetPointInfoData" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetAllPointStata">
      <soap12:operation soapAction="http://tempuri.org/GetAllPointStata" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetNoiseRealTime">
      <soap12:operation soapAction="http://tempuri.org/GetNoiseRealTime" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetWeatherRealTime">
      <soap12:operation soapAction="http://tempuri.org/GetWeatherRealTime" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetTrafficRealTime">
      <soap12:operation soapAction="http://tempuri.org/GetTrafficRealTime" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetSinglePointDayReport_Head">
      <soap12:operation soapAction="http://tempuri.org/GetSinglePointDayReport_Head" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetSinglePointDayReport_Body">
      <soap12:operation soapAction="http://tempuri.org/GetSinglePointDayReport_Body" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetSinglePointMonthReport_Head">
      <soap12:operation soapAction="http://tempuri.org/GetSinglePointMonthReport_Head" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetSinglePointMonthReport_Body">
      <soap12:operation soapAction="http://tempuri.org/GetSinglePointMonthReport_Body" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetSinglePointQuarterReport_Head">
      <soap12:operation soapAction="http://tempuri.org/GetSinglePointQuarterReport_Head" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetSinglePointQuarterReport_Body">
      <soap12:operation soapAction="http://tempuri.org/GetSinglePointQuarterReport_Body" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetDistributionStatistics_Body">
      <soap12:operation soapAction="http://tempuri.org/GetDistributionStatistics_Body" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetDynamicAnalysis_Body">
      <soap12:operation soapAction="http://tempuri.org/GetDynamicAnalysis_Body" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetRelativityData_Body">
      <soap12:operation soapAction="http://tempuri.org/GetRelativityData_Body" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetCollectionRate_Noise_Body">
      <soap12:operation soapAction="http://tempuri.org/GetCollectionRate_Noise_Body" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetCollectionRate_Weather_Body">
      <soap12:operation soapAction="http://tempuri.org/GetCollectionRate_Weather_Body" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetCollectionRate_Traffic_Body">
      <soap12:operation soapAction="http://tempuri.org/GetCollectionRate_Traffic_Body" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetInitMapInfo">
      <soap12:operation soapAction="http://tempuri.org/GetInitMapInfo" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetInitPointInfo">
      <soap12:operation soapAction="http://tempuri.org/GetInitPointInfo" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetInitPointInfoAll">
      <soap12:operation soapAction="http://tempuri.org/GetInitPointInfoAll" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetPointWarningInfoNum">
      <soap12:operation soapAction="http://tempuri.org/GetPointWarningInfoNum" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetPointWarningInfoNumNew">
      <soap12:operation soapAction="http://tempuri.org/GetPointWarningInfoNumNew" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetPointWarningInfoSeachNum">
      <soap12:operation soapAction="http://tempuri.org/GetPointWarningInfoSeachNum" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetPointWarningInfoSeachNumNew">
      <soap12:operation soapAction="http://tempuri.org/GetPointWarningInfoSeachNumNew" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetPointWarningInfo">
      <soap12:operation soapAction="http://tempuri.org/GetPointWarningInfo" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetPointWarningInfoNew">
      <soap12:operation soapAction="http://tempuri.org/GetPointWarningInfoNew" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetPointWarningInfoSeach">
      <soap12:operation soapAction="http://tempuri.org/GetPointWarningInfoSeach" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetPointWarningInfoSeachNew">
      <soap12:operation soapAction="http://tempuri.org/GetPointWarningInfoSeachNew" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetCDicts">
      <soap12:operation soapAction="http://tempuri.org/GetCDicts" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
  </wsdl:binding>
  <wsdl:binding name="WebServiceHttpGet" type="tns:WebServiceHttpGet">
    <http:binding verb="GET" />
    <wsdl:operation name="CheckUser">
      <http:operation location="/CheckUser" />
      <wsdl:input>
        <http:urlEncoded />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetAreaPointData">
      <http:operation location="/GetAreaPointData" />
      <wsdl:input>
        <http:urlEncoded />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="SavePwd">
      <http:operation location="/SavePwd" />
      <wsdl:input>
        <http:urlEncoded />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetUserInfoData">
      <http:operation location="/GetUserInfoData" />
      <wsdl:input>
        <http:urlEncoded />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetPointInfoData">
      <http:operation location="/GetPointInfoData" />
      <wsdl:input>
        <http:urlEncoded />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetAllPointStata">
      <http:operation location="/GetAllPointStata" />
      <wsdl:input>
        <http:urlEncoded />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetNoiseRealTime">
      <http:operation location="/GetNoiseRealTime" />
      <wsdl:input>
        <http:urlEncoded />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetWeatherRealTime">
      <http:operation location="/GetWeatherRealTime" />
      <wsdl:input>
        <http:urlEncoded />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetTrafficRealTime">
      <http:operation location="/GetTrafficRealTime" />
      <wsdl:input>
        <http:urlEncoded />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetSinglePointDayReport_Head">
      <http:operation location="/GetSinglePointDayReport_Head" />
      <wsdl:input>
        <http:urlEncoded />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetSinglePointDayReport_Body">
      <http:operation location="/GetSinglePointDayReport_Body" />
      <wsdl:input>
        <http:urlEncoded />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetSinglePointMonthReport_Head">
      <http:operation location="/GetSinglePointMonthReport_Head" />
      <wsdl:input>
        <http:urlEncoded />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetSinglePointMonthReport_Body">
      <http:operation location="/GetSinglePointMonthReport_Body" />
      <wsdl:input>
        <http:urlEncoded />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetSinglePointQuarterReport_Head">
      <http:operation location="/GetSinglePointQuarterReport_Head" />
      <wsdl:input>
        <http:urlEncoded />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetSinglePointQuarterReport_Body">
      <http:operation location="/GetSinglePointQuarterReport_Body" />
      <wsdl:input>
        <http:urlEncoded />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetDistributionStatistics_Body">
      <http:operation location="/GetDistributionStatistics_Body" />
      <wsdl:input>
        <http:urlEncoded />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetDynamicAnalysis_Body">
      <http:operation location="/GetDynamicAnalysis_Body" />
      <wsdl:input>
        <http:urlEncoded />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetRelativityData_Body">
      <http:operation location="/GetRelativityData_Body" />
      <wsdl:input>
        <http:urlEncoded />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetCollectionRate_Noise_Body">
      <http:operation location="/GetCollectionRate_Noise_Body" />
      <wsdl:input>
        <http:urlEncoded />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetCollectionRate_Weather_Body">
      <http:operation location="/GetCollectionRate_Weather_Body" />
      <wsdl:input>
        <http:urlEncoded />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetCollectionRate_Traffic_Body">
      <http:operation location="/GetCollectionRate_Traffic_Body" />
      <wsdl:input>
        <http:urlEncoded />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetInitMapInfo">
      <http:operation location="/GetInitMapInfo" />
      <wsdl:input>
        <http:urlEncoded />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetInitPointInfo">
      <http:operation location="/GetInitPointInfo" />
      <wsdl:input>
        <http:urlEncoded />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetInitPointInfoAll">
      <http:operation location="/GetInitPointInfoAll" />
      <wsdl:input>
        <http:urlEncoded />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetPointWarningInfoNum">
      <http:operation location="/GetPointWarningInfoNum" />
      <wsdl:input>
        <http:urlEncoded />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetPointWarningInfoNumNew">
      <http:operation location="/GetPointWarningInfoNumNew" />
      <wsdl:input>
        <http:urlEncoded />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetPointWarningInfoSeachNum">
      <http:operation location="/GetPointWarningInfoSeachNum" />
      <wsdl:input>
        <http:urlEncoded />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetPointWarningInfoSeachNumNew">
      <http:operation location="/GetPointWarningInfoSeachNumNew" />
      <wsdl:input>
        <http:urlEncoded />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetPointWarningInfo">
      <http:operation location="/GetPointWarningInfo" />
      <wsdl:input>
        <http:urlEncoded />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetPointWarningInfoNew">
      <http:operation location="/GetPointWarningInfoNew" />
      <wsdl:input>
        <http:urlEncoded />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetPointWarningInfoSeach">
      <http:operation location="/GetPointWarningInfoSeach" />
      <wsdl:input>
        <http:urlEncoded />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetPointWarningInfoSeachNew">
      <http:operation location="/GetPointWarningInfoSeachNew" />
      <wsdl:input>
        <http:urlEncoded />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetCDicts">
      <http:operation location="/GetCDicts" />
      <wsdl:input>
        <http:urlEncoded />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
  </wsdl:binding>
  <wsdl:binding name="WebServiceHttpPost" type="tns:WebServiceHttpPost">
    <http:binding verb="POST" />
    <wsdl:operation name="CheckUser">
      <http:operation location="/CheckUser" />
      <wsdl:input>
        <mime:content type="application/x-www-form-urlencoded" />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetAreaPointData">
      <http:operation location="/GetAreaPointData" />
      <wsdl:input>
        <mime:content type="application/x-www-form-urlencoded" />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="SavePwd">
      <http:operation location="/SavePwd" />
      <wsdl:input>
        <mime:content type="application/x-www-form-urlencoded" />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetUserInfoData">
      <http:operation location="/GetUserInfoData" />
      <wsdl:input>
        <mime:content type="application/x-www-form-urlencoded" />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetPointInfoData">
      <http:operation location="/GetPointInfoData" />
      <wsdl:input>
        <mime:content type="application/x-www-form-urlencoded" />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetAllPointStata">
      <http:operation location="/GetAllPointStata" />
      <wsdl:input>
        <mime:content type="application/x-www-form-urlencoded" />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetNoiseRealTime">
      <http:operation location="/GetNoiseRealTime" />
      <wsdl:input>
        <mime:content type="application/x-www-form-urlencoded" />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetWeatherRealTime">
      <http:operation location="/GetWeatherRealTime" />
      <wsdl:input>
        <mime:content type="application/x-www-form-urlencoded" />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetTrafficRealTime">
      <http:operation location="/GetTrafficRealTime" />
      <wsdl:input>
        <mime:content type="application/x-www-form-urlencoded" />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetSinglePointDayReport_Head">
      <http:operation location="/GetSinglePointDayReport_Head" />
      <wsdl:input>
        <mime:content type="application/x-www-form-urlencoded" />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetSinglePointDayReport_Body">
      <http:operation location="/GetSinglePointDayReport_Body" />
      <wsdl:input>
        <mime:content type="application/x-www-form-urlencoded" />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetSinglePointMonthReport_Head">
      <http:operation location="/GetSinglePointMonthReport_Head" />
      <wsdl:input>
        <mime:content type="application/x-www-form-urlencoded" />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetSinglePointMonthReport_Body">
      <http:operation location="/GetSinglePointMonthReport_Body" />
      <wsdl:input>
        <mime:content type="application/x-www-form-urlencoded" />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetSinglePointQuarterReport_Head">
      <http:operation location="/GetSinglePointQuarterReport_Head" />
      <wsdl:input>
        <mime:content type="application/x-www-form-urlencoded" />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetSinglePointQuarterReport_Body">
      <http:operation location="/GetSinglePointQuarterReport_Body" />
      <wsdl:input>
        <mime:content type="application/x-www-form-urlencoded" />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetDistributionStatistics_Body">
      <http:operation location="/GetDistributionStatistics_Body" />
      <wsdl:input>
        <mime:content type="application/x-www-form-urlencoded" />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetDynamicAnalysis_Body">
      <http:operation location="/GetDynamicAnalysis_Body" />
      <wsdl:input>
        <mime:content type="application/x-www-form-urlencoded" />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetRelativityData_Body">
      <http:operation location="/GetRelativityData_Body" />
      <wsdl:input>
        <mime:content type="application/x-www-form-urlencoded" />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetCollectionRate_Noise_Body">
      <http:operation location="/GetCollectionRate_Noise_Body" />
      <wsdl:input>
        <mime:content type="application/x-www-form-urlencoded" />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetCollectionRate_Weather_Body">
      <http:operation location="/GetCollectionRate_Weather_Body" />
      <wsdl:input>
        <mime:content type="application/x-www-form-urlencoded" />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetCollectionRate_Traffic_Body">
      <http:operation location="/GetCollectionRate_Traffic_Body" />
      <wsdl:input>
        <mime:content type="application/x-www-form-urlencoded" />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetInitMapInfo">
      <http:operation location="/GetInitMapInfo" />
      <wsdl:input>
        <mime:content type="application/x-www-form-urlencoded" />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetInitPointInfo">
      <http:operation location="/GetInitPointInfo" />
      <wsdl:input>
        <mime:content type="application/x-www-form-urlencoded" />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetInitPointInfoAll">
      <http:operation location="/GetInitPointInfoAll" />
      <wsdl:input>
        <mime:content type="application/x-www-form-urlencoded" />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetPointWarningInfoNum">
      <http:operation location="/GetPointWarningInfoNum" />
      <wsdl:input>
        <mime:content type="application/x-www-form-urlencoded" />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetPointWarningInfoNumNew">
      <http:operation location="/GetPointWarningInfoNumNew" />
      <wsdl:input>
        <mime:content type="application/x-www-form-urlencoded" />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetPointWarningInfoSeachNum">
      <http:operation location="/GetPointWarningInfoSeachNum" />
      <wsdl:input>
        <mime:content type="application/x-www-form-urlencoded" />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetPointWarningInfoSeachNumNew">
      <http:operation location="/GetPointWarningInfoSeachNumNew" />
      <wsdl:input>
        <mime:content type="application/x-www-form-urlencoded" />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetPointWarningInfo">
      <http:operation location="/GetPointWarningInfo" />
      <wsdl:input>
        <mime:content type="application/x-www-form-urlencoded" />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetPointWarningInfoNew">
      <http:operation location="/GetPointWarningInfoNew" />
      <wsdl:input>
        <mime:content type="application/x-www-form-urlencoded" />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetPointWarningInfoSeach">
      <http:operation location="/GetPointWarningInfoSeach" />
      <wsdl:input>
        <mime:content type="application/x-www-form-urlencoded" />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetPointWarningInfoSeachNew">
      <http:operation location="/GetPointWarningInfoSeachNew" />
      <wsdl:input>
        <mime:content type="application/x-www-form-urlencoded" />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetCDicts">
      <http:operation location="/GetCDicts" />
      <wsdl:input>
        <mime:content type="application/x-www-form-urlencoded" />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
  </wsdl:binding>
  <wsdl:service name="WebService">
    <wsdl:port name="WebServiceSoap" binding="tns:WebServiceSoap">
      <soap:address location="http://110.19.109.58:6789/Services/WebService.asmx" />
    </wsdl:port>
    <wsdl:port name="WebServiceSoap12" binding="tns:WebServiceSoap12">
      <soap12:address location="http://110.19.109.58:6789/Services/WebService.asmx" />
    </wsdl:port>
    <wsdl:port name="WebServiceHttpGet" binding="tns:WebServiceHttpGet">
      <http:address location="http://110.19.109.58:6789/Services/WebService.asmx" />
    </wsdl:port>
    <wsdl:port name="WebServiceHttpPost" binding="tns:WebServiceHttpPost">
      <http:address location="http://110.19.109.58:6789/Services/WebService.asmx" />
    </wsdl:port>
  </wsdl:service>
</wsdl:definitions>