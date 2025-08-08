package com.poc.dynamic_metadata.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Map;

@Data
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "users")
public class User {

    @Id
    @Column(name = "uid", length = 32, nullable = false, updatable = false)
    @ColumnDefault("upper(replace(gen_random_uuid()::text,'-'::text,''::text))")
    @Generated(GenerationTime.INSERT)
    private String uid;

    @Column(name = "username", length = 255)
    private String username;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "user_metadata", columnDefinition = "jsonb")
    private Map<String,Object> userMetadata;

    public User(){}
}
