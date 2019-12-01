package com.shkila.quizapi.exception

import org.springframework.http.HttpStatus.NOT_FOUND
import org.springframework.web.bind.annotation.ResponseStatus
import java.lang.String.format

@ResponseStatus(NOT_FOUND)
class ResourceNotFoundException(
        resourceName: String,
        fieldName: String,
        fieldValue: Any
) : RuntimeException(
        format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue)
)
