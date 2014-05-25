package org.ldw.service;


/*
 * 实体类转jSON数据时DATE类型转换辅助类
 */
import java.text.SimpleDateFormat;  
import java.util.Date;  
import java.util.Locale;  

import org.resource.objectclass.PdCity;
  
import net.sf.json.JsonConfig;  
import net.sf.json.processors.JsonValueProcessor;  
  
public class jsonDateHelper implements JsonValueProcessor {  
  
    private String format ="yyyy-MM-dd";  
      
    public Object processArrayValue(Object value, JsonConfig config) {  
        return process(value);  
    }  
  
    public Object processObjectValue(String key, Object value, JsonConfig config) {  
        return process(value);  
    }  
      
    private Object process(Object value){  
          
        if(value instanceof Date){  
            SimpleDateFormat sdf = new SimpleDateFormat(format,Locale.UK);  
            return sdf.format(value);  
        }
       
        return value == null ? "" : value.toString();  
    }  
}  