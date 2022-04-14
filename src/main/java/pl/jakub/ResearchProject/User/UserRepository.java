package pl.jakub.ResearchProject.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {


    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<User> getAll() {
        return jdbcTemplate.query("SELECT id,login,pass,status FROM user",
                BeanPropertyRowMapper.newInstance(User.class));
    }

    public User getByLogin(String login) {
        return jdbcTemplate.queryForObject("SELECT id,login,pass,status,patientId FROM user WHERE login = ?",
                BeanPropertyRowMapper.newInstance(User.class), login);
    }

    public int save(User user) {
        jdbcTemplate.update("INSERT INTO user(login,pass,status,patientId) VALUES (?, ?, ?, ?)",
                user.getLogin(), user.getPass(), user.getStatus(), user.getPatientId());
        return 1;
    }

    public int update(User user) {
        return jdbcTemplate.update("UPDATE user SET pass=? WHERE login=?",
               user.getPass(), user.getLogin());
    }

    public int delete(String login) {
        return jdbcTemplate.update("DELETE FROM user WHERE login=?", login);
    }


}
