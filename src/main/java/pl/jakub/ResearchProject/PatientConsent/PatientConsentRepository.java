package pl.jakub.ResearchProject.PatientConsent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pl.jakub.ResearchProject.LabTest.LabTest;

import java.util.List;

@Repository
public class PatientConsentRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<PatientConsent> getAll() {
        return jdbcTemplate.query("SELECT id,projectId,patientId,consent FROM patientconsent",
                BeanPropertyRowMapper.newInstance(PatientConsent.class));
    }

    public PatientConsent getById(int id) {
        return jdbcTemplate.queryForObject("SELECT id,projectId,patientId,consent FROM patientconsent WHERE id =?",
                BeanPropertyRowMapper.newInstance(PatientConsent.class) ,id);
    }

    public List<PatientConsent> getListById(int patientId) {
        return jdbcTemplate.query("SELECT id,projectId,patientId,consent FROM patientconsent WHERE patientId =?",
                BeanPropertyRowMapper.newInstance(PatientConsent.class), patientId);
    }

    public int save(List<PatientConsent> patientConsents) {
        patientConsents.forEach(patientConsent -> jdbcTemplate
                .update("INSERT INTO patientconsent(projectId, patientId, consent) VALUES (?, ?, ?)",
                        patientConsent.getProjectId(), patientConsent.getPatientId(), patientConsent.getConsent()));
        return 1;
    }

    public int update(PatientConsent patientConsent) {
        return jdbcTemplate.update("UPDATE patientconsent SET projectId=?, patientId=?, consent=? WHERE id=?",
                patientConsent.getProjectId(), patientConsent.getPatientId(), patientConsent.getConsent(), patientConsent.getId());
    }

    public int delete(int id) {
        return jdbcTemplate.update("DELETE FROM patientconsent WHERE id=?", id);
    }



}
