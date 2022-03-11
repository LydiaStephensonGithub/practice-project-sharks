package com.example.sharks.exception;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Shark with that ID does not exist")
public class SharkNotFoundException extends EntityNotFoundException {
	
}
