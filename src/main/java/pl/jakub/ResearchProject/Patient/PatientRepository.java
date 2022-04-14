package pl.jakub.ResearchProject.Patient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PatientRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Patient> getAll() {
        return jdbcTemplate.query("SELECT id,firstname,lastname,pesel,phonenumber FROM patient",
                BeanPropertyRowMapper.newInstance(Patient.class));
    }

    public Patient getById(int id) {
        return jdbcTemplate.queryForObject("SELECT id,firstname,lastname,pesel,phonenumber FROM patient WHERE id = ?",
                BeanPropertyRowMapper.newInstance(Patient.class), id);
    }

    public int save(List<Patient> patients) {
        patients.forEach(patient -> jdbcTemplate
                .update("INSERT INTO patient(firstname, lastname, pesel, phonenumber) VALUES (?, ?, ?, ?)",
                        patient.getFirstname(), patient.getLastname(), patient.getPesel(), patient.getPhonenumber()));

        return 1;
    }

    public int update(Patient patient) {
        return jdbcTemplate.update("UPDATE patient SET firstname=?, lastname=?, pesel=?, phonenumber=? WHERE id=?",
                patient.getFirstname(), patient.getLastname(), patient.getPesel(), patient.getPhonenumber(), patient.getId());
    }

    public int delete(int id) {
        return jdbcTemplate.update("DELETE FROM patient WHERE id=?", id);
    }

}
