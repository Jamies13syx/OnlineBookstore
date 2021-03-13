package bookstore.dao.impl;

import bookstore.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public abstract class BaseDao {

    private QueryRunner queryRunner = new QueryRunner();

    public int update(String sql, Object ... args){
        Connection connection = JdbcUtils.getConnection();
        try {
            return queryRunner.update(connection, sql,args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException(throwables);
        }
    }

    public <T> T queryForOne(Class<T> type, String sql, Object ... args){
        Connection connection = JdbcUtils.getConnection();
        try {
            return queryRunner.query(connection,sql, new BeanHandler<>(type), args);
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException(throwables);
        }
    }

    public <T> List<T> queryForList(Class<T> type, String sql, Object ... args){
        Connection connection = JdbcUtils.getConnection();
        try {
            return queryRunner.query(connection,sql, new BeanListHandler<>(type), args);
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException(throwables);
        }

    }

    public Object queryForSingleValue(String sql, Object ... args){
        Connection connection = JdbcUtils.getConnection();
        try {
            return queryRunner.query(connection, sql, new ScalarHandler(), args);
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException(throwables);
        }
    }
}
