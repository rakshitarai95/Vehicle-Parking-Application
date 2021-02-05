package com.test.controller;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.response.Response;
import com.test.util.Constants;

public class BaseController {
	private static final Logger LOG = Logger.getLogger(BaseController.class);

	public void setResponse(Response response, Object responseData, String status, String statusCode, String message) {
		long startTime = System.currentTimeMillis();
		response.setResponseData(responseData);
		response.setStatus(status);
		response.setStatusCode(statusCode);
		response.setMessage(message);
		if (LOG.isDebugEnabled()) {
			ObjectMapper mapper = new ObjectMapper();
			try {
				LOG.debug(mapper.writeValueAsString(response));
			} catch (JsonProcessingException e) {
				LOG.error("Error in writing object as String : " + e);
			}
		}
		LOG.info("TOTAL_PPROCESS_TIME=BaseController Controller:" + (System.currentTimeMillis() - startTime));
	}

	public ResponseEntity<? extends Response> restResponse(Response res, String method) {
		ResponseEntity<? extends Response> responseEntity = new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
		if (Constants.SUCCESS_CODE.equals(res.getStatusCode())) {
			responseEntity = setStatusAndStatusCode(res, method);
			return responseEntity;
		}
		return responseEntity;
	}

	private ResponseEntity<? extends Response> setStatusAndStatusCode(Response res, String method) {
		ResponseEntity<? extends Response> result = new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
		if (method == null || "GET".equals(method) || "PUT".equals(method)) {
			res.setStatus(Constants.STATUS_SUCCESS);
			res.setStatusCode(HttpStatus.OK.toString());
			result = new ResponseEntity<>(res, HttpStatus.OK);
		} else if ("POST".equals(method)) {
			res.setStatus(Constants.STATUS_SUCCESS);
			res.setStatusCode(HttpStatus.CREATED.toString());
			result = new ResponseEntity<>(res, HttpStatus.CREATED);
		} else if ("DELETE".equals(method)) {
			res.setStatus(Constants.STATUS_SUCCESS);
			res.setStatusCode(HttpStatus.NO_CONTENT.toString());
			result = new ResponseEntity<>(res, HttpStatus.OK);
		}
		return result;
	}

}
