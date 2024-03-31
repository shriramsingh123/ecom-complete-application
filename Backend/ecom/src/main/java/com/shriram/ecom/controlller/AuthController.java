package com.shriram.ecom.controlller;

import com.shriram.ecom.dto.AuthenticationRequest;
import com.shriram.ecom.dto.AuthenticationResponse;
import com.shriram.ecom.dto.SignupRequest;
import com.shriram.ecom.dto.UserDto;
import com.shriram.ecom.entity.User;
import com.shriram.ecom.repository.UserRepository;
import com.shriram.ecom.services.auth.AuthService;
import com.shriram.ecom.utils.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    private final AuthService authService;

//    @PostMapping("/authenticate")
//    public void createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest,
//                                          HttpServletResponse response) throws IOException, JSONException {
//        System.out.println("authenticate users***********************************");
//
//        try {
//            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
//                    authenticationRequest.getPassword()));
//        }catch (BadCredentialsException e){
//            throw new BadCredentialsException("Incorrect Username or Password");
//        }
//
//        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
//        Optional<User> optionalUser = userRepository.findFirstByEmail(userDetails.getUsername());
//        final String jwt = jwtUtil.generateToken(userDetails.getUsername());
//
//        if (optionalUser.isPresent()){
//            response.getWriter().write(new JSONObject()
//                    .put("userId",optionalUser.get().getId())
//                    .put("role",optionalUser.get().getRole())
//                    .toString()
//            );
//
//            response.addHeader("Access-Control-Expose-Headers","Authorization");
//            response.addHeader("Access-Control-Allow-Headers","Authorization , X-PINGOTHER , Origin ," +
//                            "X-Requested-With, Content-Type, X-Custom-header");
//            response.addHeader(HEADER_STRING,TOKEN_PREFIX + jwt);
//            System.out.println("Backend Work well");
//        }
//    }

    @PostMapping("/authenticate")
    public AuthenticationResponse authenticateAndGateToken(@RequestBody AuthenticationRequest authRequest) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            Optional<User> optionalUser = userRepository.findFirstByEmail(authRequest.getUsername());
            AuthenticationResponse authenticationResponse = new AuthenticationResponse();
            authenticationResponse.setJwt(jwtUtil.generateToken(optionalUser.get().getEmail()));
            authenticationResponse.setUserId(optionalUser.get().getId());
            authenticationResponse.setUserRole(optionalUser.get().getRole());
            return authenticationResponse;
        } else {
            throw new UsernameNotFoundException("invalid user request ! ");
        }

    }

    @PostMapping("/sign-up")
    public ResponseEntity<?> signupUser(@RequestBody SignupRequest signupRequest){
        if (authService.hasUserWithEmail(signupRequest.getEmail())){
            return new ResponseEntity<>("User Already Exist", HttpStatus.NOT_ACCEPTABLE);
        }
        UserDto userDto = authService.createUser(signupRequest);
        return new ResponseEntity<>(userDto,HttpStatus.OK);
    }

    @GetMapping("/welcome")
    public String message(){
        return "Your Auth Controller work properly";
    }
}
