package pl.jakub.ResearchProject.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int id;
    @NonNull
    private String login;
    @NonNull
    private String pass;
    private String status;
    private int patientId;
}
