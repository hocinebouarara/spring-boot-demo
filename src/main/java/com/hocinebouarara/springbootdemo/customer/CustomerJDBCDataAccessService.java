package com.hocinebouarara.springbootdemo.customer;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("jdbc")
public class CustomerJDBCDataAccessService implements CustomerDao {

    private final JdbcTemplate jdbcTemplate;
    private final CustomerRowMapper customerRowMapper;

    public CustomerJDBCDataAccessService(
            JdbcTemplate jdbcTemplate,
            CustomerRowMapper customerRowMapper) {

        this.jdbcTemplate = jdbcTemplate;
        this.customerRowMapper = customerRowMapper;
    }


    @Override
    public List<Customer> selectAllCustomers() {

        var sql = """
                SELECT id, name, email, age
                FROM customer
                """;

        return jdbcTemplate.query(sql,customerRowMapper);
    }

    @Override
    public Optional<Customer> selectCustomerById(Integer id) {
        var sql = """
                SELECT id, name, email, age
                FROM customer
                """;

        return jdbcTemplate.query(sql,customerRowMapper,id)
                .stream().
                findFirst();
    }

    @Override
    public void insertCustomer(Customer customer) {

        var sql = """
                INSERT INTO customer (name,email,age)
                values (?,?,?)
                """;
        int update = jdbcTemplate.update(
                sql,
                customer.getEmail(),
                customer.getEmail(),
                customer.getAge()
        );

        System.out.println("jdbc Template.update = "+ update);

    }

    @Override
    public boolean existPersonWithEmail(String email) {
        var sql = """
                SELECT count(id)
                FROM customer
                WHERE email = ?
                """;
        Integer count = jdbcTemplate.queryForObject(sql,Integer.class,email);
        return count != null && count > 0;
    }

    @Override
    public boolean existsPersonWithId(Integer id) {
        var sql = """
                SELECT count(id)
                FROM customer
                WHERE id = ?
                """;
        Integer count = jdbcTemplate.queryForObject(sql,Integer.class,id);
        return count != null && count > 0;
    }

    @Override
    public void deleteCustomerById(Integer customerId) {
        var sql = """
                  DELETE 
                  FROM customer
                  WHERE id =? 
                  """;

        int result = jdbcTemplate.update(sql,customerId);
        System.out.println("delete customer with id = " +customerId);

    }

    @Override
    public void updateCustomer(Customer update) {
        if(update.getName() != null){
            var sql ="UPDATE customer SET name = ? WHERE id = ?";
            int result = jdbcTemplate.update(
                    sql,
                    update.getName(),
                    update.getId()
            );
        }if(update.getAge() != null){
            var sql ="UPDATE customer SET age = ? WHERE id = ?";
            int result = jdbcTemplate.update(
                    sql,
                    update.getAge(),
                    update.getId()
            );
            System.out.println("update customer name result = "+result);
        }
        if(update.getEmail() !=null){
            var sql ="UPDATE customer SET email = ?,age=? WHERE id = ?";
            int result = jdbcTemplate.update(
                    sql,
                    update.getEmail(),
                    update.getId()
            );
            System.out.println("update customer name result = "+result);
        }

    }
}
