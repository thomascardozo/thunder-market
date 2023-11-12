package com.tm.dto;

public record SimpleResponse(String status, Integer code, AuthUserResponse authUser) {
}
