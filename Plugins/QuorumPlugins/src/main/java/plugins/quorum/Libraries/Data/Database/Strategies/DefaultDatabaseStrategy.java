/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Data.Database.Strategies;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import plugins.quorum.Libraries.Data.Database.QueryResult;
import quorum.Libraries.Data.Database.Query_;
import quorum.Libraries.Data.Database.CreateTable_;
import quorum.Libraries.Data.Database.DropTable_;
import quorum.Libraries.Data.Database.EditTable_;
import quorum.Libraries.Data.Database.CreateView_;
import quorum.Libraries.Data.Database.QueryResult_;
import quorum.Libraries.Data.Database.Find_;
import quorum.Libraries.Data.Database.Insert_;
import quorum.Libraries.Data.Database.Update_;
import quorum.Libraries.Data.Database.Delete_;
import quorum.Libraries.Data.Database.Support.Parameter_;
import quorum.Libraries.Containers.Array_;
/**
 *
 * @author stefika
 */
public class DefaultDatabaseStrategy {
    public java.lang.Object me_ = null;
    Connection connection = null;
    int lastInsertedID = -1;
    
    public void ConnectNative(String value) throws SQLException {
        connection = DriverManager.getConnection(value);
    }
    
    public boolean GetAutoCommitNative() throws SQLException {
        if (connection != null) {
            return connection.getAutoCommit();
        }
        return false;
    }

    public void SetAutoCommitNative(boolean commit) throws SQLException {
        if(connection != null) {
            connection.setAutoCommit(commit);
        }
    }

    public void CommitNative() throws SQLException {
        if(connection != null) {
            connection.commit();
        }
    }

    public void RollbackNative() throws SQLException {
        if(connection != null) {
            connection.rollback();
        }
    }

    public quorum.Libraries.Data.Database.DatabaseMetaData_ GetDatabaseMetaData() throws SQLException {
        DatabaseMetaData meta = connection.getMetaData();
        if(meta == null) {
            return null;
        }

        quorum.Libraries.Data.Database.DatabaseMetaData dmd = new quorum.Libraries.Data.Database.DatabaseMetaData();
        dmd.plugin_.SetMeta(meta);
        return dmd;
    }
    
    public void CreateTableNative(CreateTable_ query) throws SQLException {
        String sql = query.ConvertToStructuredQueryLanguage();
        PreparedStatement statement = connection.prepareStatement(sql);
        int executeUpdate = statement.executeUpdate(); //ignore it, it just returns zero anyway
        statement.closeOnCompletion();
    }
    
    public void DropTableNative(DropTable_ query) throws SQLException {
        String sql = query.ConvertToStructuredQueryLanguage();
        PreparedStatement statement = connection.prepareStatement(sql);
        int executeUpdate = statement.executeUpdate(); //ignore it, it just returns zero anyway
        statement.closeOnCompletion();
    }

    public void EditTableNative(EditTable_ query) throws SQLException {
        String sql = query.ConvertToStructuredQueryLanguage();
        PreparedStatement statement = connection.prepareStatement(sql);
        int executeUpdate = statement.executeUpdate(); //ignore it, it just returns zero anyway
        statement.closeOnCompletion();
    }

    public void CreateViewNative(CreateView_ query) throws SQLException {
        String sql = query.ConvertToStructuredQueryLanguage();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement = SetParameters(statement, ((Query_)query).GetPreparedParameters());
        int executeUpdate = statement.executeUpdate(); //ignore it, it just returns zero anyway
        statement.closeOnCompletion();
    }
    
    public QueryResult_ FindNative(Find_ query) throws SQLException {
        String sql = query.ConvertToStructuredQueryLanguage();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement = SetParameters(statement, ((Query_)query).GetPreparedParameters());
        ResultSet results = statement.executeQuery();
        quorum.Libraries.Data.Database.QueryResult qr = new quorum.Libraries.Data.Database.QueryResult();
        QueryResult plugin_ = qr.plugin_;
        plugin_.setResults(results);
        return qr;
    }

    public int InsertNative(Insert_ query) throws SQLException {
        String sql = query.ConvertToStructuredQueryLanguage();
        PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        statement = SetParameters(statement, ((Query_)query).GetPreparedParameters());
        int rowsAffected = statement.executeUpdate();
        ResultSet rs = statement.getGeneratedKeys();
        if (rs.next()) {
            lastInsertedID = rs.getInt(1);
        } else {
            lastInsertedID = -1;
        }
        statement.close();
        return rowsAffected;
    }

    public int UpdateNative(Update_ query) throws SQLException {
        String sql = query.ConvertToStructuredQueryLanguage();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement = SetParameters(statement, ((Query_)query).GetPreparedParameters());
        int rowsAffected = statement.executeUpdate();
        statement.close();
        return rowsAffected;
    }

    public int GetLastInsertedIDNative() {
        return lastInsertedID;
    }

    public int DeleteNative(Delete_ query) throws SQLException {
        String sql = query.ConvertToStructuredQueryLanguage();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement = SetParameters(statement, ((Query_)query).GetPreparedParameters());
        int rowsAffected = statement.executeUpdate();
        statement.close();
        return rowsAffected;
    }
    
    private PreparedStatement SetParameters(PreparedStatement statement, Array_ params) throws SQLException {
        for(int i = 0; i < params.GetSize(); i++)
        {
            Parameter_ param = (Parameter_)params.Get(i);
            if (param.IsText()) {
                statement.setString(i+1, param.GetValueAsText());
            }
        }
        return statement;
    }

    public void CloseNative() throws SQLException {
        if(connection != null) {
            //follow the default recommendation, which is to commit before close
            //if not set to auto-commit
            if(!connection.getAutoCommit()) {
                connection.commit();
            }
            connection.close();
            connection = null;
        }
    }
}
