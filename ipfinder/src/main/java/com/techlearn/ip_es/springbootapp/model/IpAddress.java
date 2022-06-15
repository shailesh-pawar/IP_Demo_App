package com.techlearn.ip_es.springbootapp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Document(indexName = "ipaddressindex")
public class IpAddress {

    @Field(type = FieldType.Text, name = "type")
    private String type;

    @Field(type = FieldType.Text, name = "value")
    @NotBlank(message = "IP Address value should not be null")
    @Id
    private String value;

    @Field(type = FieldType.Date, name = "firstSeen")
    @DateTimeFormat
    private Date firstSeen;

    @Field(type = FieldType.Integer, name = "totalCount")
    @Min(8)
    @Max(100)
    private int totalCount;

    public IpAddress() {

    }

    public IpAddress(String type, String value, Date firstSeen, int totalCount) {

        this.type = type;
        this.value = value;
        this.firstSeen = firstSeen;
        this.totalCount = totalCount;
    }

    @Override
    public String toString() {
        return "IpAddress{" +
                "value='" + value + '\'' +
                ", type='" + type + '\'' +
                ", firstSeen='" + firstSeen + '\'' +
                ", totalCount'" + totalCount + '\'' +
                '}';
    }
}
