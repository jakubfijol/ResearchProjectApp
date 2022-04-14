package pl.jakub.ResearchProject.LabTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LabTestRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<LabTest> getAll() {
        return jdbcTemplate.query("SELECT id,projectId,patientId,date,type,result FROM labTest",
                BeanPropertyRowMapper.newInstance(LabTest.class));
    }

    public LabTest getById(int id) {
        return jdbcTemplate.queryForObject("SELECT id,projectId,patientId,date,type,result FROM labtest WHERE id =?",
                BeanPropertyRowMapper.newInstance(LabTest.class), id);
    }

    public List<LabTest> getListById(int patientId) {
        return jdbcTemplate.query("SELECT id,projectId,patientId,date,type,result FROM labtest WHERE patientId =?",
                BeanPropertyRowMapper.newInstance(LabTest.class), patientId);
    }

    public int save(List<LabTest> labTests) {
        labTests.forEach(labTest -> jdbcTemplate
                .update("INSERT INTO labtest(projectId, patientId,date,type,result) VALUES (?, ?, ?, ?, ?)",
                        labTest.getProjectId(), labTest.getPatientId(), labTest.getDate(), labTest.getType(), labTest.getResult()));
        return 1;
    }

    public int update(LabTest labTest) {
        return jdbcTemplate.update("UPDATE labtest SET projectId=?, patientId=?, date=?, type=?, result=? WHERE id=?",
                labTest.getProjectId(), labTest.getPatientId(), labTest.getDate(), labTest.getType(), labTest.getResult(), labTest.getId());
    }

    public int delete(int id) {
        return jdbcTemplate.update("DELETE FROM labtest WHERE id=?", id);
    }

}
