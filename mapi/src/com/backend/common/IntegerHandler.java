package com.backend.common;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IntegerHandler implements TypeHandler<Integer> {

  @Override
  public Integer getResult(ResultSet rs, String columnName) throws SQLException {
    return rs.getInt(columnName);
  }

  @Override
  public Integer getResult(ResultSet rs, int columnIndex) throws SQLException {
    return rs.getInt(columnIndex);
  }

  @Override
  public Integer getResult(CallableStatement cs, int columnIndex) throws SQLException {
    return cs.getInt(columnIndex);
  }

  /**
   * 非空校验（防止insert null报错）
   */
  @Override
  public void setParameter(PreparedStatement pstmt, int index, Integer value, JdbcType jdbcType) throws SQLException {
    pstmt.setInt(index, value == null ? 0 : value);
  }
}
