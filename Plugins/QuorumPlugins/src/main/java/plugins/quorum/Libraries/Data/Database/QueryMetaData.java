/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Data.Database;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 *
 * @author stefika
 */
public class QueryMetaData {
    public java.lang.Object me_ = null;
    private ResultSetMetaData metaData = null;

    /**
     * @return the metaData
     */
    public ResultSetMetaData getMetaData() {
        return metaData;
    }

    /**
     * @param metaData the metaData to set
     */
    public void setMetaData(ResultSetMetaData metaData) {
        this.metaData = metaData;
    }
    
    public int GetSize() throws SQLException {
        return metaData.getColumnCount();
    }
    
    public String GetName(int column) throws SQLException {
        return metaData.getColumnLabel(column + 1);
    }
    
    public int GetType(int column) throws SQLException {
        return metaData.getColumnType(column + 1);
    }
    
    public String GetTypeName(int column) throws SQLException {
        return metaData.getColumnTypeName(column + 1);
    }
}
