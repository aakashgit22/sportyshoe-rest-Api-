package com.simplilearn.sportyshoe.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simplilearn.sportyshoe.dto.ResponseDto;
import com.simplilearn.sportyshoe.dto.user.SignInDto;
import com.simplilearn.sportyshoe.dto.user.SignInResponseDto;
import com.simplilearn.sportyshoe.dto.user.SignupDto;
import com.simplilearn.sportyshoe.exceptions.AuthenticationFailException;
import com.simplilearn.sportyshoe.exceptions.CustomException;
import com.simplilearn.sportyshoe.model.AuthenticationToken;
import com.simplilearn.sportyshoe.model.User;
import com.simplilearn.sportyshoe.repository.UserRepository;

import javax.transaction.Transactional;
import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

@Service
public class UserService {
	 @Autowired
	    UserRepository userRepository;

	    @Autowired
	    AuthenticationService authenticationService;

	    @Transactional
	    public ResponseDto signUp(SignupDto signupDto) {
	        // check if user is already present
	        if (Objects.nonNull(userRepository.findByEmail(signupDto.getEmail()))) {
	            // we have an user
	            throw new CustomException("user already present");
	        }


	        // hash the password

	        String encryptedpassword = signupDto.getPassword();

	        try {
	            encryptedpassword = hashPassword(signupDto.getPassword());
	        } catch (NoSuchAlgorithmException e) {
	            e.printStackTrace();
	        }

	        User user = new User(signupDto.getFirstName(), signupDto.getLastName(),
	                signupDto.getEmail(), encryptedpassword);

	        userRepository.save(user);

	        // save the user

	        // create the token

	        final AuthenticationToken authenticationToken = new AuthenticationToken(user);

	        authenticationService.saveConfirmationToken(authenticationToken);

	        ResponseDto responseDto = new ResponseDto("success", "user created succesfully");
	        return responseDto;
	    }

	    private String hashPassword(String password) throws NoSuchAlgorithmException {
	        MessageDigest md = MessageDigest.getInstance("MD5");
	        md.update(password.getBytes());
	        byte[] digest = md.digest();
	        String hash = DatatypeConverter
	                .printHexBinary(digest).toUpperCase();
	        return hash;
	    }

	    public SignInResponseDto signIn(SignInDto signInDto) {
	        // find user by email

	        User user = userRepository.findByEmail(signInDto.getEmail());

	        if (Objects.isNull(user)) {
	            throw new AuthenticationFailException("user is not valid");
	        }

	        // hash the password

	        try {
	            if (!user.getPasswoprd().equals(hashPassword(signInDto.getPassword()))) {
	                throw new AuthenticationFailException("wrong password");
	            }
	        } catch (NoSuchAlgorithmException e) {
	            e.printStackTrace();
	        }

	    

	        AuthenticationToken token = authenticationService.getToken(user);

	
	        if (Objects.isNull(token)) {
	            throw new CustomException("token is not present");
	        }

	        return new SignInResponseDto("sucess", token.getToken());

	        // return response
	    }
}
