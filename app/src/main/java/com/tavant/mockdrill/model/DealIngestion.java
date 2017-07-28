package com.tavant.mockdrill.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "DEAL_INGESTION")
public class DealIngestion implements Serializable{

	public enum Source {
	    TAPE,
	    EDI
	}
	
	public enum Status {
	    YES,
	    NO,
	    NOT_SURE
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "deal_ingestion_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="source")
	@Enumerated(EnumType.STRING)
    private Source source;
	
	@Column(name = "filelocation")
	private String fileLocation;
	
	@Column(name = "file_type")
	private String fileType;
	
	@Column(name="status")
	@Enumerated(EnumType.STRING)
    private Status status;
	
	@Column(name = "source_data")
	private String sourceData;
	
	/*@OneToMany(mappedBy = "dealIngestion", cascade = CascadeType.ALL)
	private List<LoanData> loanRowDataList;*/

	
	/*public List<LoanData> getLoanRowDataList() {
		return loanRowDataList;
	}

	public void setLoanRowDataList(List<LoanData> loanRowDataList) {
		this.loanRowDataList = loanRowDataList;
	}*/

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Source getSource() {
		return source;
	}

	public void setSource(Source source) {
		this.source = source;
	}

	public String getFileLocation() {
		return fileLocation;
	}

	public void setFileLocation(String fileLocation) {
		this.fileLocation = fileLocation;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getSourceData() {
		return sourceData;
	}

	public void setSourceData(String sourceData) {
		this.sourceData = sourceData;
	}
	
	
}




