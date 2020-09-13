package com.brasilprev.customermanagement.commons.dataprovider.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Builder;
import lombok.Getter;

@Entity
@Table(name = "TBL_CLIENT")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Builder
public class ClientEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "COL_ID")
	private Long id;
	
	@Column(name = "COL_NAME", length = 197)
	private String name;
	
	@Column(name = "COL_IDENTITY_DOCUMENT", length = 11)
	private String identityDocument;
	
	@Embedded
	private AddressEntity address;
	
    @CreatedDate
    @Column(name = "COL_CREATED_DATE")
    private Date createdDate;
    
    @LastModifiedDate
    @Column(name = "COL_LAST_MODIFIED_DATE")
    private Date lastModifiedDate;
	
}
