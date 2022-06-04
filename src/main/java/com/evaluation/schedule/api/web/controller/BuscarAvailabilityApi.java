package com.evaluation.schedule.api.web.controller;

import com.evaluation.schedule.api.web.service.ConnectService;
import com.evaluation.schedule.domain.model.Availability;
import com.evaluation.schedule.domain.model.Candidate;
import com.evaluation.schedule.domain.model.Exam;
import com.evaluation.schedule.domain.repository.CandidateRepository;
import com.evaluation.schedule.domain.service.CandidateService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import javax.annotation.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.primefaces.model.SelectableDataModel;
import org.primefaces.shaded.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;

import lombok.AllArgsConstructor;
import lombok.Getter;

import lombok.Setter;


@Setter
@Getter
@ManagedBean()
@SessionScoped
public class BuscarAvailabilityApi {
	
	@Autowired
	private ConnectService connectService;
	
	static String urlWebService = "http://localhost:8080/JAX-RS/exam/find";
	static String urlWebSecurityService = "http://localhost:8080/login";
	static final String user="roger3";
	static final String password="password";
    	
        
    public List<Exam> availabilityAll;
    
    public BuscarAvailabilityApi() throws Exception {
		super();
		
	}

             
    public List<Exam> availabilityApi() throws Exception {        

        try {        	
        	
        	HttpURLConnection conect = connectService.getconnection(urlWebSecurityService,urlWebService,user,password);        	
            
            BufferedReader response = new BufferedReader(new InputStreamReader((conect.getInputStream())));
            String jsonEmString = Util.converteJsonEmString(response);
            
            ObjectMapper mapper = JsonMapper.builder()
            		   .addModule(new JavaTimeModule())
            		   .build();
            
            availabilityAll = mapper.readValue(jsonEmString, new TypeReference<List<Exam>>() {});
                        
            return availabilityAll;
        } catch (Exception e) {
            throw new Exception("ERROR: " + e);
        }
    }

        
  
	
}
