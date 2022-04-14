package pl.jakub.ResearchProject.LabTest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LabTest {
    private int id;
    private int projectId;
    private int patientId;
    private String date;
    private String type;
    private String result;

}
