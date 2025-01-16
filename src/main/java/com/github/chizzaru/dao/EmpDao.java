package com.github.chizzaru.dao;

import com.github.chizzaru.beans.Emp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@Repository
public class EmpDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public EmpDao(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public int save(Emp emp){
        String sql = "insert into emp_tbl(name,salary,designation)values('"+emp.getName()+"',"+emp.getSalary()+",'"+emp.getDesignation()+"')";
        return jdbcTemplate.update(sql);
    }

    public int update(Emp emp){
        String sql = "update emp_tbl set name='"+emp.getName()+"', salary="+emp.getSalary()+", designation='"+emp.getDesignation()+"' where id="+emp.getId();
        return jdbcTemplate.update(sql);
    }

    public int delete(int id){
        String sql = "delete from emp_tbl where id="+id;
        return jdbcTemplate.update(sql);
    }

    public Emp getEmpById(int id){
        String sql = "select * from emp_tbl where id=?";
        return jdbcTemplate.queryForObject(
                sql,
                new BeanPropertyRowMapper<>(Emp.class),
                id
        );
    }

    public List<Emp> getEmployees(){
        return jdbcTemplate.query(
                "select * from emp_tbl",
                (rs, rowNum) -> {
                    Emp e = new Emp();
                    e.setId(rs.getInt(1));
                    e.setName(rs.getString(2));
                    e.setSalary(rs.getFloat(3));
                    e.setDesignation(rs.getString(4));

                    return e;
                }
        );
    }


}
