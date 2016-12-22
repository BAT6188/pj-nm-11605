package com.harmonywisdom.dshbcbp.utils;




import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 地图相关数据处理
 */
public class MapUtils {

    private static final Logger log = LoggerFactory.getLogger(MapUtils.class);
    /**
     * 获取地址转坐标字符串中的第一个坐标
     * @param CoordsString
     * @return
     */
    public static AddressCoord getFirstCoordFromGeoCodingJsonString(String CoordsString, boolean usingDistrictCoord){

        if(CoordsString == null || "".equals(CoordsString))
        {
            return null;
        }
        AddressCoord addressCoord = null;
        try
        {
            String addrName = "";
            JSONObject jsonObject = JSONObject.fromObject(CoordsString);
            if(jsonObject.has("list")) {
                JSONArray jsonArray = jsonObject.getJSONArray("list");
                if(jsonArray.size() > 0) {
                    jsonObject = jsonArray.getJSONObject(0);
                    if(jsonObject.has("nearhns"))
                    {
                        addrName = jsonObject.getString("name");
                        jsonArray = jsonObject.getJSONArray("nearhns");
                        if(jsonArray == null || jsonArray.size()==0)
                        {
                            return null;
                        }
                        jsonObject =jsonArray .getJSONObject(0);
                    }
                    // 涉及到区县、市、省的定位不处理
                    if(jsonObject.has("level")){
                        String level = jsonObject.getString("level");
                        if("GL_CITY".equals(level) || "GL_PROVINCE".equals(level)  || "GL_COUNTRY".equals(level)){
                            return null;
                        }
                        if(!usingDistrictCoord && "GL_DISTRICT".equals(level)){
                            return null;
                        }

                    }
                    addressCoord = new AddressCoord(addrName+jsonObject.getString("name"),jsonObject.getDouble("x"),jsonObject.getDouble("y"));
                }
            }
        }
        catch (Exception e)
        {
            log.error("解析地址错误！");
            log.error(CoordsString);
            log.error("地址解析",e);
        }

        return addressCoord;
    }


    /**
     * 获取地址转坐标字符串中的第一个POI坐标点，如果没有找到poi坐标点则返回
     * 现仅仅自动调度使用
     * @param CoordsString
     * @return
     */
    public static AddressCoord getFirstPoiCoordFromGeoCodingJsonString(String CoordsString){
        if(CoordsString == null || "".equals(CoordsString))
        {
            return null;
        }
        AddressCoord addressCoord = null;
        try
        {
            String addrName = "";
            JSONObject jsonObject = JSONObject.fromObject(CoordsString);
            if(jsonObject.has("list")) {
                JSONArray jsonArray = jsonObject.getJSONArray("list");
                if(jsonArray.size() > 0) {
                    jsonObject = jsonArray.getJSONObject(0);
                    if(jsonObject.has("nearhns"))
                    {
                        addrName = jsonObject.getString("name");
                        jsonArray = jsonObject.getJSONArray("nearhns");
                        if(jsonArray == null || jsonArray.size()==0)
                        {
                            return null;
                        }
                        jsonObject =jsonArray .getJSONObject(0);
                        addressCoord = new AddressCoord(addrName+jsonObject.getString("name"),jsonObject.getDouble("x"),jsonObject.getDouble("y"));
                    }
                }
            }
        }
        catch (Exception e)
        {
            log.error("解析地址错误！");
            log.error(CoordsString);
            log.error("地址解析",e);
        }
        return addressCoord;
    }


    /**
     * 获取地址转坐标字符串中的第一个POI坐标点，如果没有找到poi坐标点则返回
     * 现仅仅自动调度使用
     * @param CoordsString
     * @return
     */
    public static AddressCoord getFirstPoiCoordFromPOIJsonString(String CoordsString){
        AddressCoord addressCoord = null;
        try
        {
//            String addrName = "";
            JSONObject jsonObject = JSONObject.fromObject(CoordsString);
            if(!jsonObject.has("status") || !"E0".equals(jsonObject.getString("status")))
            {
                return null;
            }
            if(jsonObject.has("list") ) {
                if(jsonObject.get("list") instanceof JSONArray)
                {
                    JSONArray jsonArray = jsonObject.getJSONArray("list");
                    if(jsonArray.size() > 0) {
                        jsonObject = jsonArray.getJSONObject(0);
                        addressCoord = new AddressCoord(jsonObject.getString("name"),jsonObject.getDouble("x"),jsonObject.getDouble("y"));
                    }
                }
                else {
                    jsonObject = jsonObject.getJSONObject("list");
                    if(jsonObject.has("poi"))
                    {
                        jsonObject = jsonObject.getJSONObject("poi");
                        addressCoord = new AddressCoord(jsonObject.getString("name"),jsonObject.getDouble("x"),jsonObject.getDouble("y"));
                    }
                }
            }
        }
        catch (Exception e)
        {
            log.error("解析POI搜索结果错误！");
            log.error(CoordsString);
            log.error("error",e);
        }
        return addressCoord;
    }

    /**
     * 计算距离
     * @param navDec 高德导航返回的字符串
     * @return
     */
    public static double getDistanceFromNavigateJsonString(String navDec)
    {
        if(navDec == null || "".equals(navDec))
        {
            return 0;
        }
        JSONObject jsonObject = JSONObject.fromObject(navDec);
        if(!jsonObject.has("list")){
            return 0;
        }
        JSONArray jsonArray = jsonObject.getJSONArray("list");
        double distance = 0;
        for(int i=0; i<jsonArray.size(); i++)
        {
            String strRoadLength = jsonArray.getJSONObject(i).getString("roadLength");
            if(strRoadLength.indexOf("千米")>0){
                distance += str2Double(strRoadLength.replace("千米",""))*1000;
            }else{
                distance += str2Double(strRoadLength.replace("米",""));
            }
        }
        // 老版本高德返回数据
//        JSONObject jsonObject = JSONObject.fromString(navDec);
//        if(!jsonObject.has("segmengList"))
//            return 0;
//        JSONArray jsonArray = jsonObject.getJSONArray("segmengList");
//        double distance = 0;
//        for(int i=0; i<jsonArray.length(); i++)
//        {
//            distance+=jsonArray.getJSONObject(i).getDouble("roadLength");
//        }
        return distance;
    }

    /**
     * 字符串转double
     * @param str
     * @return
     */
    private static double str2Double(String str)
    {
        try
        {
            return Double.parseDouble(str);
        }
        catch (Exception e)
        {
            log.error("异常:",e);
            return 0;
        }
    }


    /**
     * 米转换成经纬度
     * @param mx
     * @param my
     * @return
     */
    public static double[] metersToLonLat(double mx,double my)
    {
        double originShift = 2 * Math.PI * 6378137/2;
        double lon =(mx / originShift) * 180.0;
        double lat =(my / originShift) * 180.0;
        lat=180 / Math.PI * (2 * Math.atan(Math.exp(lat * Math.PI / 180.0)) - Math.PI / 2.0);
        return new double[]{lon,lat};
    }

    /**
     * 将经纬度坐标转换成以米的坐标
     * @param lon
     * @param lat
     * @return
     */
    public static double[] lonLatToMeters(double lon,double lat)
    {
        double originShift = 2 * Math.PI * 6378137/2;
        double mx = lon * originShift / 180;
        double my = Math.log(Math.tan((90 + lat) * Math.PI / 360)) / (Math.PI / 180);
        my=my * originShift / 180;
        return new double[]{mx, my};
    }

    /**
     * 计算两点间距离
     * @param point1
     * @param point2
     * @return
     */
    public static double calDistanceByTowPoint(double[] point1,double[] point2)
    {
        return Math.pow(Math.pow(point1[0]-point2[0],2)+Math.pow(point1[1]-point2[1],2),0.5);
    }

    /**
     * 将米的距离转换成经纬度距离
     * @param meterDistance
     * @return
     */
    public static double meterDistanceToDegreeDistance(double x,double y, double meterDistance)
    {
        double[] point = new double[]{x,y};
        // 将xy，转换成以米为单位的坐标
        double[] meterXY = lonLatToMeters(x,y);
        // 计算横坐标偏移点坐标
        double[] hOffsetCoord = metersToLonLat(meterXY[0]+meterDistance,meterXY[1]);
        // 计算纵坐标便宜点坐标
        double[] vOffsetCoord = metersToLonLat(meterXY[0],meterXY[1]+meterDistance);
        return (calDistanceByTowPoint(point,hOffsetCoord)+calDistanceByTowPoint(point,vOffsetCoord))/2;
    }

    /**
     * 通过经纬度获取距离
     * @param longitude
     * @param latitude
     * @param longitude1
     * @param latitude1
     * @return
     */
    public static double getDistanceFromDegreeCoords(double longitude, double latitude, Double longitude1, Double latitude1) {
        double[] point1 = lonLatToMeters(longitude,latitude);
        double[] point2 = lonLatToMeters(longitude1,latitude1);
        return calDistanceByTowPoint(point1,point2);
    }

    /**
     * 从地址中获取坐标
     * @param address
     * @return
     */
    public static AddressCoord getCoordByAddress(String address){
        AddressCoord addressCoord = null;
        String regexp = "\\[((\\d+\\.\\d+)|(\\d+)),((\\d+\\.\\d+)|(\\d+))\\]";
        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(address);
        if (matcher.find()) {
            String coordStr = matcher.group();
            String[] coordStrArr = coordStr.substring(1, coordStr.length() - 1).split(",");
            double[] coord = new double[2];
            addressCoord = new AddressCoord(address,Double.parseDouble(coordStrArr[0]),Double.parseDouble(coordStrArr[1]));
        }
        return addressCoord;
    }

    /**
     * 判断坐标是否有效
     * @param longitude
     * @param latitude
     * @return
     */
    public static boolean isValid(Double longitude, Double latitude){
        if (longitude == null || latitude == null) {
            return false;
        }
        if (longitude > 73.55 && longitude < 135.08333333333333333 && latitude > 3.85 && latitude < 53.55) {
            return true;
        }
        return false;
    }
}

