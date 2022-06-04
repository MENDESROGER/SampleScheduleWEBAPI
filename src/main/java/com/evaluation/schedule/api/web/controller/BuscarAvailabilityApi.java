package com.evaluation.schedule.api.web.controller;

import com.evaluation.schedule.api.model.dto.CandidateDTO;
import com.evaluation.schedule.domain.model.Availability;
import com.evaluation.schedule.domain.model.Candidate;
import com.evaluation.schedule.domain.repository.CandidateRepository;
import com.evaluation.schedule.domain.service.CandidateService;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.primefaces.model.SelectableDataModel;
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
	private CandidateService candidateService;
	
	static String webService = "http://localhost:8080/availability";
    
	static int codSucess = 200;
    
    public List<CandidateDTO> candidateAll;
    
    public BuscarAvailabilityApi() {
		super();
		candidateAll = new ArrayList<>();
	}

           
    public static <T> List<T> getList(String jsonArray, Class<T> clazz) {
        Type typeOfT = TypeToken.getParameterized(List.class, clazz).getType();
        return new Gson().fromJson(jsonArray, typeOfT);
    }

    public List<CandidateDTO> getAvailabilityApi() throws Exception {
        String urlWebService = "webService";

        try {
            URL url = new URL(urlWebService);
            HttpURLConnection conect = (HttpURLConnection) url.openConnection();

            if (conect.getResponseCode() != codSucess)
                throw new RuntimeException("HTTP error code : " + conect.getResponseCode());

            BufferedReader response = new BufferedReader(new InputStreamReader((conect.getInputStream())));
            String jsonEmString = Util.converteJsonEmString(response);

           
            candidateAll = getList(jsonEmString,CandidateDTO.class);

            return candidateAll;
        } catch (Exception e) {
            throw new Exception("ERROR: " + e);
        }
    }

        
  
	
}
