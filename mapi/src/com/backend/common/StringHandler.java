package com.backend.common;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StringHandler implements TypeHandler<String> {

  @Override
  public String getResult(ResultSet rs, String columnName) throws SQLException {
    return rs.getString(columnName);
  }

  @Override
  public String getResult(ResultSet rs, int columnIndex) throws SQLException {
    return rs.getString(columnIndex);
  }

  @Override
  public String getResult(CallableStatement cs, int columnIndex) throws SQLException {
    return cs.getString(columnIndex);
  }

  /**
   * 非空校验（防止insert null报错）
   */
  @Override
  public void setParameter(PreparedStatement pstmt, int index, String value, JdbcType jdbcType) throws SQLException {
    pstmt.setString(index, value == null ? "" : value);
  }
}
