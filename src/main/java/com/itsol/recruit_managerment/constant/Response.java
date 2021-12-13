package com.itsol.recruit_managerment.constant;

public class Response {
     private String type;
    private String code;
    private String message;
    private Object data;

    public Response(String type, String code, String message, Object data) {
        this.type = type;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Response(String type, String code, String message) {
        this.type = type;
        this.code = code;
        this.message = message;
    }

    public Response(String type, String code, Object data) {
        this.type = type;
        this.code = code;
        this.data = data;
    }

    public Response(String type, String code) {
        this.type = type;
        this.code = code;
    }

    public Response(String type) {
        this.type = type;
    }

    public Response() {
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the data
     */
    public Object getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(Object data) {
        this.data = data;
    }

    /**
     * @param data the data to set
     */
    public Response withData(Object data) {
        this.data = data;
        return this;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    public static Response success(String code) {
        return new Response(Constants.RESPONSE_TYPE.SUCCESS, code);
    }

    public static Response success() {
        return new Response(Constants.RESPONSE_TYPE.SUCCESS);
    }

    public static Response error(String code) {
        return new Response(Constants.RESPONSE_TYPE.ERROR, code);
    }

    public static Response error(String code, String message) {
        return new Response(Constants.RESPONSE_TYPE.ERROR, code, message);
    }

    public static Response warning(String code) {
        return new Response(Constants.RESPONSE_TYPE.WARNING, code);
    }

    public static Response invalidPermission() {
        return new Response(Constants.RESPONSE_TYPE.ERROR, "invalidPermission");
    }

    public static Response confirm(String code, String callback, Object data) {
        return new Response(Constants.RESPONSE_TYPE.CONFIRM, code, callback, data);
    }

    public static Response confirm(String code, String callback) {
        return new Response(Constants.RESPONSE_TYPE.CONFIRM, code, callback, null);
    }

    public static Response success(String code, String message) {
        return new Response(Constants.RESPONSE_TYPE.SUCCESS, code, message);
    }

    public static Response warning(String code, String message) {
        return new Response(Constants.RESPONSE_TYPE.WARNING, code, message);
    }
}
