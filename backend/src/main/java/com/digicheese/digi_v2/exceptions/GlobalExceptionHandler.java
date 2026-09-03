package com.digicheese.digi_v2.exceptions;

import com.digicheese.digi_v2.dtos.ErrorResponseDTO;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    private static final String AUTHENTICATION_REQUIRED = "Authentification requise";
    private static final String INVALID_CREDENTIALS = "Identifiants invalides";
    private static final String SESSION_EXPIRED = "Session expiree";
    private static final String ACCESS_DENIED = "Acces refuse";
    private static final String RESOURCE_NOT_FOUND = "Ressource introuvable";
    private static final String INVALID_REQUEST = "Requete invalide";
    private static final String RESOURCE_CONFLICT = "Cette valeur est deja utilisee";
    private static final String INTERNAL_ERROR = "Une erreur interne est survenue";

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ErrorResponseDTO> handleExpiredJwt(ExpiredJwtException exception,
                                                            HttpServletRequest request) {
        logger.warn("Token expire sur {} : {}", request.getRequestURI(), exception.getMessage());
        return build(HttpStatus.UNAUTHORIZED, SESSION_EXPIRED, request);
    }

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<ErrorResponseDTO> handleJwt(JwtException exception, HttpServletRequest request) {
        logger.warn("Token invalide sur {} : {}", request.getRequestURI(), exception.getMessage());
        return build(HttpStatus.UNAUTHORIZED, AUTHENTICATION_REQUIRED, request);
    }

    @ExceptionHandler({BadCredentialsException.class, UsernameNotFoundException.class})
    public ResponseEntity<ErrorResponseDTO> handleBadCredentials(AuthenticationException exception,
                                                                 HttpServletRequest request) {
        logger.warn("Echec d'authentification sur {} : {}", request.getRequestURI(), exception.getMessage());
        return build(HttpStatus.UNAUTHORIZED, INVALID_CREDENTIALS, request);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorResponseDTO> handleAuthentication(AuthenticationException exception,
                                                                 HttpServletRequest request) {
        logger.warn("Authentification refusee sur {} : {}", request.getRequestURI(), exception.getMessage());
        return build(HttpStatus.UNAUTHORIZED, AUTHENTICATION_REQUIRED, request);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponseDTO> handleAccessDenied(AccessDeniedException exception,
                                                               HttpServletRequest request) {
        logger.warn("Acces refuse sur {} : {}", request.getRequestURI(), exception.getMessage());
        return build(HttpStatus.FORBIDDEN, ACCESS_DENIED, request);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDTO> handleValidation(MethodArgumentNotValidException exception,
                                                             HttpServletRequest request) {
        Map<String, String> fieldErrors = new HashMap<>();
        exception.getBindingResult().getFieldErrors()
                .forEach(fieldError -> fieldErrors.put(fieldError.getField(), fieldError.getDefaultMessage()));

        logger.warn("Validation echouee sur {} : {}", request.getRequestURI(), fieldErrors);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseDTO(
                Instant.now(), HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(),
                INVALID_REQUEST, request.getRequestURI(), fieldErrors));
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleResourceNotFound(ResourceNotFoundException exception,
                                                                   HttpServletRequest request) {
        logger.warn("Ressource introuvable sur {} : {}", request.getRequestURI(), exception.getMessage());
        return build(HttpStatus.NOT_FOUND, RESOURCE_NOT_FOUND, request);
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ErrorResponseDTO> handleDuplicateResource(DuplicateResourceException exception,
                                                                    HttpServletRequest request) {
        logger.warn("Conflit de ressource sur {} : {}", request.getRequestURI(), exception.getMessage());
        return build(HttpStatus.CONFLICT, RESOURCE_CONFLICT, request);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponseDTO> handleUnreadableBody(HttpMessageNotReadableException exception,
                                                                 HttpServletRequest request) {
        logger.warn("Corps de requete illisible sur {} : {}", request.getRequestURI(), exception.getMessage());
        return build(HttpStatus.BAD_REQUEST, INVALID_REQUEST, request);
    }

    @ExceptionHandler({MissingServletRequestParameterException.class, MethodArgumentTypeMismatchException.class})
    public ResponseEntity<ErrorResponseDTO> handleInvalidParameter(Exception exception, HttpServletRequest request) {
        logger.warn("Parametre de requete invalide sur {} : {}", request.getRequestURI(), exception.getMessage());
        return build(HttpStatus.BAD_REQUEST, INVALID_REQUEST, request);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleNotFound(HttpServletRequest request) {
        logger.warn("Ressource introuvable : {}", request.getRequestURI());
        return build(HttpStatus.NOT_FOUND, RESOURCE_NOT_FOUND, request);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleUnexpected(Exception exception, HttpServletRequest request) {
        logger.error("Erreur inattendue sur {}", request.getRequestURI(), exception);
        return build(HttpStatus.INTERNAL_SERVER_ERROR, INTERNAL_ERROR, request);
    }

    private ResponseEntity<ErrorResponseDTO> build(HttpStatus status, String message, HttpServletRequest request) {
        return ResponseEntity.status(status).body(new ErrorResponseDTO(
                Instant.now(), status.value(), status.getReasonPhrase(), message, request.getRequestURI()));
    }
}
