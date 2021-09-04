package org.josua.web.common;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

public abstract class CommonController {

	protected Map<String, Object> parseJsonToMap(String strJson) {
    	if(null == strJson || strJson.isEmpty()){
    		return null;
    	}
    	
    	return new JSONDeserializer<Map<String, Object>>().use(null, HashMap.class).deserialize(strJson);
    }
	
	/**
     * Common Json Http Header
     * @author 
	 * @since 
     * @return HttpHeaders
     */
    protected HttpHeaders getHttpHeaders() throws Exception {
    	return getHttpHeaders(null);
    }
    
    /**
     * Common Json Http Header
     * @author 
	 * @since  
     * @param headerMap
     * @return HttpHeaders
     */
    protected HttpHeaders getHttpHeaders(Map<String, String> headerMap) throws Exception {
    	HttpHeaders httpHeaders = null;
    	
    	try {
	    	httpHeaders = new HttpHeaders();
			httpHeaders.add("Content-Type", "application/json; charset=utf-8");
			
			if(null != headerMap && !headerMap.isEmpty()) {
				for(String strMapKey : headerMap.keySet()) {
					httpHeaders.add(strMapKey, headerMap.get(strMapKey));
				}
			}
			
	    	return httpHeaders;
    	} catch(Exception e) {
    		throw e;
    	} finally {
    		if(null != httpHeaders){ httpHeaders = null; }
    	}
    }
    
    /**
     * Common Json getResponseEntity
     * @author 
	 * @since 
     * @param httpStatus
     * @return ResponseEntity
     */
    protected ResponseEntity<String> getResponseEntity(HttpStatus httpStatus) throws Exception {
    	return getResponseEntity(httpStatus, null);
    }
    
    /**
     * Common Json getResponseEntity
     * @author 
	 * @since 
     * @param httpStatus
     * @param httpHeaders
     * @return ResponseEntity
     */
    protected ResponseEntity<String> getResponseEntity(HttpStatus httpStatus, HttpHeaders httpHeaders) throws Exception {
    	return getResponseEntity(httpStatus, httpHeaders, "");
    }
    
    /**
     * common Json getResponseEntity
     * @author 
     * @since 
     * @param httpStatus
     * @param httpHeaders
     * @param responseObj
     * @return
     */
    protected ResponseEntity<String> getResponseEntity(HttpStatus httpStatus, HttpHeaders httpHeaders, Object responseObj) throws Exception {
    	return this.getResponseEntity(httpStatus, httpHeaders, null != responseObj ? new JSONSerializer().rootName("").exclude("*.class", "*.version").deepSerialize(responseObj) : null);
    }
    
    /**
     * common Json getResponseEntity
     * @author 
     * @since 
     * @param httpStatus
     * @param httpHeaders
     * @param strJson
     * @return
     */
    protected ResponseEntity<String> getResponseEntity(HttpStatus httpStatus, HttpHeaders httpHeaders, String strJson) throws Exception {
    	if(null == httpStatus) {
    		throw new Exception("HttpStatus is Invalid");
    	}
    	
    	if(null == httpHeaders) {
    		return new ResponseEntity<String>(httpStatus); 
    	}
    	
    	if(null == strJson || strJson.isEmpty()) {
    		return new ResponseEntity<String>(httpHeaders, httpStatus);
    	}
    	
    	return new ResponseEntity<String>(strJson, httpHeaders, httpStatus);
    }
    
    /**
     * 
     * @param httpStatus
     * @param httpHeaders
     * @param e
     * @return
     * @throws Exception
     */
    protected ResponseEntity<String> getResponseEntity(HttpStatus httpStatus, HttpHeaders httpHeaders, Exception e) throws Exception {
    	Map<String, Object> resMap = null;
    	
    	try {
	    	resMap = new HashMap<String, Object>();
	    	resMap.put("err_msg", e.getMessage());
	    	
	    	return this.getResponseEntity(httpStatus, httpHeaders, resMap);
    	} catch(Exception e1) {
    		throw e1;
    	} finally {
    		if(null != resMap) { resMap = null; }
    	}
    }
}
