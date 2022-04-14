package pl.jakub.ResearchProject.PatientConsent;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientConsent {
    private int id;
    private int projectId;
    private int patientId;
    private int consent;
}
