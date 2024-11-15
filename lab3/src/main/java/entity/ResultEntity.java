package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "results", schema ="s370529")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResultEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "result_id_seq")
    @SequenceGenerator(name = "result_id_seq", sequenceName = "result_id_seq", allocationSize = 1)
    private long id;
    private float x;
    private float y;
    private float r;
    private boolean result;

}
