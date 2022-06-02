package first_project.dao;

import first_project.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PersonDaoImpl implements PersonDao {

    RowMapper<Person> personRowMapper = (ResultSet resultSet, int rowNum) ->
            new Person(resultSet.getInt("id"),
                    resultSet.getString("full_name"),
                    resultSet.getInt("year_of_birthday"));

    private final JdbcTemplate jdbcTemplate;


    @Autowired
    public PersonDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Person getPersonById(int id) {
        String sqlQuery = "select * from postgres.project_1.person where id=?";
        return jdbcTemplate.queryForObject(sqlQuery, new Object[]{id}, personRowMapper);
    }

    @Override
    public List<Person> getAllPeople() {
        List<Person> personList = new ArrayList<>();
        String sqlQuery = "select * from postgres.project_1.person ";
        return jdbcTemplate.query(sqlQuery, personRowMapper);
    }

    @Override
    public void addPerson(Person person) {
        String sqlQuery = "insert into postgres.project_1.person (full_name, year_of_birthday)  values (?,?)";
        jdbcTemplate.update(sqlQuery, person.getFullName(), person.getYearOfBirthday());

    }

    @Override
    public void updatePerson(int id, Person updatedPerson) {
        String sqlQuery = "update postgres.project_1.person set full_name=?, set year_of_birthday=? where id=?";
        jdbcTemplate.update(sqlQuery, updatedPerson.getFullName(), updatedPerson.getYearOfBirthday());

    }

    @Override
    public void deletePerson(int id) {
        String sqlQuery = " delete from postgres.project_1.person where id=?";
        jdbcTemplate.update(sqlQuery, id);
    }


}
