package com.yabonza.dog.breed.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="dog_breed")
@Data
public class DogBreed {
	@Id
	@Column(name="id")
	private String id;
	@Column(name="name")
	private String name;
	@Column(name="uploaded_dt")
	private Date uploadedDt;
	@Column(name="s3_bucket_path")
	private String s3BucketPath;
}
