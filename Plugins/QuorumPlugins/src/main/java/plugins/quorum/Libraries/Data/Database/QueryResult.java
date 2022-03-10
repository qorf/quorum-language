/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Data.Database;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

import plugins.quorum.Libraries.System.DateTime;
import quorum.Libraries.Data.Database.QueryMetaData_;

/**
 *
 * @author stefika
 */
public class QueryResult {
    public java.lang.Object me_ = null;
    private ResultSet results = null;

    /**
     * @return the results
     */
    public ResultSet getResults() {
        return results;
    }

    /**
     * @param results the results to set
     */
    public void setResults(ResultSet results) {
        this.results = results;
    }

    public boolean HasNext() throws SQLException {
        
        if(results == null) {
            return false;
        }
        if(!results.isLast() && (results.getRow() != 0 || results.isBeforeFirst())) {
            return true;
        } else {
            return false;
        }
    }
    
    public void Next() throws SQLException {
        results.next();
    }
    
    public String GetText(String column) throws SQLException {
        return results.getString(column);
    }
    
    public String GetText(int column) throws SQLException {
        return results.getString(column + 1);
    }
    
    public int GetInteger(String column) throws SQLException {
        return results.getInt(column);
    }
    
    public int GetInteger(int column) throws SQLException {
        return results.getInt(column + 1);
    }

    public boolean GetBoolean(String column) throws SQLException {
        return results.getBoolean(column);
    }

    public boolean GetBoolean(int column) throws SQLException {
        return results.getBoolean(column + 1);
    }

    public double GetNumber(String column) throws SQLException {
        return results.getDouble(column);
    }

    public double GetNumber(int column) throws SQLException {
        return results.getDouble(column + 1);
    }

    public quorum.Libraries.System.DateTime_ GetDateTime(String column) throws SQLException {
        quorum.Libraries.System.DateTime_ date =  new quorum.Libraries.System.DateTime();
        quorum.Libraries.System.DateTime temp = (quorum.Libraries.System.DateTime) date;
        DateTime dt = temp.plugin_;

        ZonedDateTime zdt = ZonedDateTime.of((results.getTimestamp(column)).toLocalDateTime(), ZoneId.systemDefault());
        dt.zDateTime = zdt;
        dt.now = false;
        return date;
    }

    public quorum.Libraries.System.DateTime_ GetDateTime(int column) throws SQLException {
        quorum.Libraries.System.DateTime_ date =  new quorum.Libraries.System.DateTime();
        quorum.Libraries.System.DateTime temp = (quorum.Libraries.System.DateTime) date;
        DateTime dt = temp.plugin_;

        ZonedDateTime zdt = ZonedDateTime.of((results.getTimestamp(column + 1)).toLocalDateTime(), ZoneId.systemDefault());
        dt.zDateTime = zdt;
        dt.now = false;
        return date;
    }
    
    public QueryMetaData_ GetQueryMetaData() throws SQLException {
        if(results != null) {
            ResultSetMetaData metaData = results.getMetaData();
            if(metaData != null) {
                quorum.Libraries.Data.Database.QueryMetaData md = new quorum.Libraries.Data.Database.QueryMetaData();
                md.plugin_.setMetaData(metaData);
                return md;
            }
        }
        return null;
    }
    
    public void Close() throws SQLException {
        if(results != null) {
            results.close();
        }
    }
}
