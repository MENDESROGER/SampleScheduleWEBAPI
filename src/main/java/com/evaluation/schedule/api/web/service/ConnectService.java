package com.evaluation.schedule.api.web.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.stereotype.Service;

import com.evaluation.schedule.api.web.controller.Util;

import lombok.AllArgsConstructor;


@Service
public class ConnectService {

	static int codSucess = 200;

	public HttpURLConnection getconnection(String urlWebSecurityServiceString, String urlWebService, String user,
			String password) throws Exception {

		URL url = new URL(urlWebService);
		HttpURLConnection conect = (HttpURLConnection) url.openConnection();
		conect.setRequestProperty("Authorization",
				"Bearer " + securityHash(urlWebSecurityServiceString, user, password));
		conect.setRequestProperty("Content-Type", "application/json");

		if (conect.getResponseCode() != codSucess)
			throw new RuntimeException("HTTP error code : " + conect.getResponseCode());

		return conect;

	}

	public String securityHash(String urlWebSecurityService, String user, String password) throws Exception {

		String jsonInputCredencial = "{\"login\": \"" + user + "\", \"password\": \"" + password + "\"}";

		URL url = new URL(urlWebSecurityService);
		HttpURLConnection conect = (HttpURLConnection) url.openConnection();
		conect.setDoInput(true);
		conect.setDoOutput(true);
		conect.setRequestProperty("Content-Type", "application/json");
		conect.setRequestMethod("POST");

		try (OutputStream os = conect.getOutputStream()) {
			byte[] input = jsonInputCredencial.getBytes("utf-8");
			os.write(input, 0, input.length);
		}

		if (conect.getResponseCode() != codSucess)
			throw new RuntimeException("HTTP error code : " + conect.getResponseCode());

		BufferedReader response = new BufferedReader(new InputStreamReader((conect.getInputStream())));
		String jsonEmString = Util.converteJsonEmString(response);

		return jsonEmString;
	}

}
