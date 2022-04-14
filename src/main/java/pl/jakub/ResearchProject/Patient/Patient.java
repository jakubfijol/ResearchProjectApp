package pl.jakub.ResearchProject.Patient;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Patient {
    private int id;
    private String firstname;
    private String lastname;
    private String pesel;
    private String phonenumber;
}
