package com.main.Entity;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Stream")
public class Stream implements Serializable{		
			@Id
			@GeneratedValue(strategy = GenerationType.IDENTITY)
			private int Id_Stream;
			private String value;
			
			public Stream(String value) {
				this.value = value;
			}
}
