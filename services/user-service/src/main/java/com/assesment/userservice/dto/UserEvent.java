package com.assesment.userservice.dto;

import com.assesment.userservice.entity.UserDetails;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserEvent<T> {
    private Integer eventId;
    private UserEventType userEventType;
    private T userDetails;
}
