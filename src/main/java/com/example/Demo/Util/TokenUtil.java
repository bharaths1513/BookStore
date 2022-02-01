package com.example.Demo.Util;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.auth0.jwt.interfaces.Verification;



@Component
public class TokenUtil {


	private static final String TOKEN_SECRET = "Ashutosh";


	public  String createToken(long l)   {
	       try {
	        //to set algorithm
	        Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
	 
	        String token = JWT.create()
	        .withClaim("user_id", l)
	        .sign(algorithm);
	        return token;
	        } catch (JWTCreationException exception) {
	        exception.printStackTrace();
	           //log Token Signing Failed
	        } catch (IllegalArgumentException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
	}
	       return null;
	 }


	/**
	* @param token
	* @return
	*/
	public long decodeToken(String token)
	 {
	 long userid;
	           //for verification algorithm
	           Verification verification = null;
	try {
	verification = JWT.require(Algorithm.HMAC256(TOKEN_SECRET));
	} catch (IllegalArgumentException  e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
	}
	           JWTVerifier jwtverifier=verification.build();
	           //to decode token
	           DecodedJWT decodedjwt=jwtverifier.verify(token);

	           Claim claim=decodedjwt.getClaim("user_id");
	           userid=claim.asInt();    
	           return userid;
	     
	 }

}

