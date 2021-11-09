package ir.mahdi.universityservice.service.dto;

import ir.mahdi.universityservice.base.BaseDTO;

public class UserSearchDTO extends BaseDTO<Long> {

    private String username;

    private boolean isConfirmed;

    private String userType;
}
