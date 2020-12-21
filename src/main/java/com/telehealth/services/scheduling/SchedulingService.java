package com.telehealth.services.scheduling;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.*;

import io.jsonwebtoken.security.Keys;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/schedulingService")
public class SchedulingService
{
    public Date addHoursToJavaUtilDate(Date date, int hours) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, hours);
        return calendar.getTime();
    }
    private String getJWT()
    {
        /*Date expiration = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.MINUTE, 10);
        dt = c.getTime();*/

        String API_KEY = "85pc2ehhSkSH-k2PKCZcsw";
        String API_SECRET = "ds3eLlRQyOh93oy3FpL8gCUVlAcljm53c97K";
        SecretKey key = Keys.hmacShaKeyFor(API_SECRET.getBytes(StandardCharsets.UTF_8));

        String jwts =   Jwts.builder()
                        .setHeaderParam("typ","JWT")
                        .setHeaderParam("alg","HS256")
                        .setIssuer(API_KEY) //this is the zoom API key
                        .setIssuedAt(new Date())
                        .setExpiration(addHoursToJavaUtilDate(new Date(),1))
                        .signWith(key,SignatureAlgorithm.HS256) //sign with the API_SECRET
                        .compact();

        /*String jwts = Jwts.builder()
                    .set
                    .setIssuer("85pc2ehhSkSH-k2PKCZcsw")  //the zoom account API key
                    .setSubject("ZOOM api call")
                    .claim("name", "Tele Health")
                    .claim("scope", "admins")
                    .setIssuedAt(new Date())
                    // Sat Jun 24 2116 15:33:42 GMT-0400 (EDT)
                    .setExpiration(addHoursToJavaUtilDate(new Date(),1))
                    .signWith(
                        SignatureAlgorithm.HS256,
                        //Keys.secretKeyFor(SignatureAlgorithm.HS256)
                        TextCodec.BASE64.decode("dGVzdCBzdHJpbmodGVzdCBzdHJpbmodGVzdCBzdHJpbmodGVzdCBzdHJpbmo")
                )
                .compact();*/
        return jwts;
    }
    @RequestMapping("/createMeeting")
    public void createMeeting()
    {
        final String url = "https://api.zoom.us/v2/users/{userId}/meetings";
        //final String url = "http://localhost:4444/v2/users/{userId}/meetings";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String jwtToken = getJWT();
        //jwtToken="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOm51bGwsImlzcyI6Ijg1cGMyZWhoU2tTSC1rMlBLQ1pjc3ciLCJleHAiOjE2MDkxODIyNDQsImlhdCI6MTYwODU3NzQ0NX0.-HMwjOvMYEEbwF2WsQrQHiXeilCDX3UWNKXoBfqnV0g";
        headers.set("authorization", "Bearer "+jwtToken);

        ZoomCreateMeetingRequest request =  new ZoomCreateMeetingRequest();
        request.setTopic("TEST MEETING");
        request.setStart_time(new Date());
        request.setType(new Integer(2));
        request.setDuration(Integer.valueOf(60));

        System.out.println("Sending request to.... "+url);
        HttpEntity<ZoomCreateMeetingRequest> httpEntity = new HttpEntity<ZoomCreateMeetingRequest>(request, headers);

        Map<String, String> params = new HashMap<String, String>();
        params.put("userId", "srinivas.sampige@gmail.com");


        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        ZoomCreateMeetingResponse result = restTemplate.postForObject(url, httpEntity, ZoomCreateMeetingResponse.class, params);
        System.out.println(result);
    }
}
