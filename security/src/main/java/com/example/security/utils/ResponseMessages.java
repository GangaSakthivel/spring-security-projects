package com.example.security.utils;

public class ResponseMessages {

    public static final String SUCCESS = "Success";

    public static final String BAD_REQUEST_MSG = "Mandatory fields should not be empty or incorrect";

    public static final String INVALID_REQUEST = "Invalid request";

    public static final ResponseError SUCCESS_RESPONSE = new ResponseError(200, "Success");

    public static final ResponseError CREATED_RESPONSE = new ResponseError(201, "Created Successfully");

    public static final ResponseError UPDATED_RESPONSE = new ResponseError(200, "Updated Successfully");

    public static final ResponseError DELETED_RESPONSE = new ResponseError(200, "Deleted Successfully");

    public static final ResponseError ID_NOT_FOUND = new ResponseError(404, "ID not found");

    public static final ResponseError EMPTY_DATA = new ResponseError(200, "Empty Data");

    public static final ResponseError FAILURE_RESPONSE = new ResponseError(500, "Failed");

    public static final ResponseError BAD_REQUEST = new ResponseError(400, "Bad Request");

    public static final ResponseError FOREIGN_KEY_CONSTRAINT_ISSUE = new ResponseError(409, "Referred Entity cannot be deleted");

    public static final ResponseError DUPLICATE_ENTRY = new ResponseError(409, "Duplicate Entry");

    public static final ResponseError CONFLICT = new ResponseError(409, "Conflict");

    public static final ResponseError UNAUTHORIZED = new ResponseError(401, "Invalid Credentials");

    public static final ResponseError INACTIVE = new ResponseError(200, "Inactivated Successfully");

    public static final ResponseError ACTIVE = new ResponseError(200, "Activated Successfully");

    public static final ResponseError REPORTED_RESPONSE = new ResponseError(200, "Reported Successfully");

    public static final ResponseError JOINED_ALREADY_RESPONSE = new ResponseError(200, "Already Joined");

    public static final ResponseError ACCESS_DENIED = new ResponseError(403, "Access Denied");
}
