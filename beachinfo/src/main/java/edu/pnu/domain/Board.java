package edu.pnu.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Board {
	@Id 
	private Integer seq;
	public String username;
	private String beach;
	private String content;
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime createDate;
	 @PrePersist
	    protected void onCreate() {
	        createDate = LocalDateTime.now();
	    }
}
