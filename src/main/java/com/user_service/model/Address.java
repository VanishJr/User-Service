package com.user_service.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Address {
    @Column(name = "no")
    private String no;
    @Column(name = "first_line")
    private String firstLine;
    @Column(name = "second_line")
    private String secondLine;
    @Column(name = "province")
    private String province;
    @Column(name = "zip_code")
    private String zipCode;
    @Column(name = "mobile_no")
    private String mobileNo;
}
