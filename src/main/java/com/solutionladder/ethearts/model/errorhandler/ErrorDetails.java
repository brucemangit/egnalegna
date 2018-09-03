package com.solutionladder.ethearts.model.errorhandler;

import java.util.Date;
import java.util.List;

/**
 * Error handler model
 * @author Kaleb Woldearegay <kaleb@solutionladder.com>
 *
 */
public class ErrorDetails {
    private Date timestamp;
    private String message;
    private List<String> details;
    private String detail;

    public ErrorDetails(Date timestamp, String message, String detail) {
      super();
      this.timestamp = timestamp;
      this.message = message;
      //this.details = details;
      this.detail = detail;
    }
    
    public ErrorDetails(Date timestamp, String message, List<String> details) {
        super();
        this.timestamp = timestamp;
        this.message = message;
        //this.details = details;
        this.details = details;
      }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getDetails() {
        return details;
    }

    public void setDetails(List<String> details) {
        this.details = details;
    }
    
    public void setDetail(String detail) {
        this.detail = detail;
    }
    
    public String getDetail() {
        return this.detail;
    }
}