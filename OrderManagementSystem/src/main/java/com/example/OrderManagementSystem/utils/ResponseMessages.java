package com.example.OrderManagementSystem.utils;

public class ResponseMessages {

    public static final String SUCCESS = "Success";

    public static final String BAD_REQUEST_MSG = "Mandatory fields should not be empty or incorrect";

    public static final String INVALID_REQUEST = "Invalid request";

    public static final String NOT_FOUND = "Resource not found";

    public static final ErrorResponse SUCCESS_RESPONSE = new ErrorResponse(200, "Success");

    public static final ErrorResponse CREATED_RESPONSE = new ErrorResponse(201, "Created Successfully");

    public static final ErrorResponse UPDATED_RESPONSE = new ErrorResponse(200, "Updated Successfully");

    public static final ErrorResponse DELETED_RESPONSE = new ErrorResponse(200, "Deleted Successfully");

    public static final ErrorResponse ID_NOT_FOUND = new ErrorResponse(404, "ID not found");

    public static final ErrorResponse EMPTY_DATA = new ErrorResponse(200, "Empty Data");

    public static final ErrorResponse FAILURE_RESPONSE = new ErrorResponse(500, "Failed");

    public static final ErrorResponse BAD_REQUEST = new ErrorResponse(400, "Bad Request");

    public static final ErrorResponse FOREIGN_KEY_CONSTRAINT_ISSUE = new ErrorResponse(409, "Referred Entity cannot be deleted");

    public static final ErrorResponse DUPLICATE_ENTRY = new ErrorResponse(409, "Duplicate Entry");

    public static final ErrorResponse CONFLICT = new ErrorResponse(409, "Conflict");

    public static final ErrorResponse UNAUTHORIZED = new ErrorResponse(401, "Invalid Credentials");

    public static final ErrorResponse INACTIVE = new ErrorResponse(200, "Inactivated Successfully");

    public static final ErrorResponse ACTIVE = new ErrorResponse(200, "Activated Successfully");

    public static final ErrorResponse REPORTED_RESPONSE = new ErrorResponse(200, "Reported Successfully");

    public static final ErrorResponse JOINED_ALREADY_RESPONSE = new ErrorResponse(200, "Already Joined");

    public static final ErrorResponse ACCESS_DENIED = new ErrorResponse(403, "Access Denied");

    public static final String UPDATE_SUCCESS_MSG = "User updated successfully!";

}
