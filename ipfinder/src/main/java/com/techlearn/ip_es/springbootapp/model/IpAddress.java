package com.techlearn.ip_es.springbootapp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Document(indexName = "ipaddressindex")
public class IpAddress {

    @Field(type = FieldType.Text, name = "type")
    private String type;

    @Field(type = FieldType.Text, name = "value")
    @NotNull
    @Id
    private String value;

    @Field(type = FieldType.Date, name = "firstSeen")
    private Date firstSeen;

    @Field(type = FieldType.Integer, name = "totalCount")
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
