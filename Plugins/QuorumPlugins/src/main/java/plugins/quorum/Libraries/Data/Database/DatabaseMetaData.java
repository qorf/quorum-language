/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Data.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import quorum.Libraries.Data.Database.DatabaseColumn;
import quorum.Libraries.Data.Database.DatabaseColumn_;
import quorum.Libraries.Data.Database.DatabaseMetaData_;
import quorum.Libraries.Data.Database.DatabaseTable;
import quorum.Libraries.Data.Database.DatabaseTable_;
import quorum.Libraries.Language.Types.Text;
/**
 *
 * @author stefika
 */
public class DatabaseMetaData {
    public java.lang.Object me_ = null;
    private java.sql.DatabaseMetaData meta = null;
    
    //tables
    public static final String TABLE = "TABLE";
    public static final String VIEW = "VIEW";
    public static final String TABLE_NAME = "TABLE_NAME";
    public static final String TABLE_TYPE = "TABLE_TYPE";

    //columns
    public static final String COLUMN_NAME = "COLUMN_NAME";
    public static final String DATA_TYPE = "DATA_TYPE";
    public static final String COLUMN_SIZE = "COLUMN_SIZE";
    public static final String DECIMAL_DIGITS = "DECIMAL_DIGITS";
    public static final String IS_NULLABLE = "IS_NULLABLE";
    public static final String IS_AUTO_INCREMENT = "IS_AUTOINCREMENT";
    
    /**
     * @return the meta
     */
    public java.sql.DatabaseMetaData GetMeta() {
        return meta;
    }

    /**
     * @param meta the meta to set
     */
    public void SetMeta(java.sql.DatabaseMetaData meta) {
        this.meta = meta;
    }
    
    public void Setup() {
        

    }
    
    public void QueryDatabaseForTables() throws SQLException {
        ResultSet resultSet = meta.getTables(null, null, null, new String[]{TABLE, VIEW});
        while(resultSet.next())
        {
            String name = resultSet.getString(TABLE_NAME);
            String type = resultSet.getString(TABLE_TYPE);
            DatabaseTable_ table = new DatabaseTable();
            table.SetName(name);
            table.SetType(type);

            Text text = new Text();
            text.SetValue(name);
            DatabaseMetaData_ data = (DatabaseMetaData_) me_;
            data.Add(table);

            ResultSet columns = meta.getColumns(null, null, name, null);

            while(columns.next())
            {
                DatabaseColumn_ column = new DatabaseColumn();
                //Set the information from JDBC, independent of the database.
                column.Set_Libraries_Data_Database_DatabaseColumn__name_(columns.getString(COLUMN_NAME));
                column.Set_Libraries_Data_Database_DatabaseColumn__type_(columns.getInt(DATA_TYPE));
                column.Set_Libraries_Data_Database_DatabaseColumn__columnSize_(columns.getInt(COLUMN_SIZE));
                column.Set_Libraries_Data_Database_DatabaseColumn__decimalDigits_(columns.getInt(DECIMAL_DIGITS));

                //For these, technically SQL allows for "unknown." We return true
                //only in the case that the database is certain that it is true. So false
                //means either it is not true or we do not know.
                String value = columns.getString(IS_NULLABLE);
                if(value == null || value.isEmpty() || value.compareTo("NO") == 0) {
                    column.Set_Libraries_Data_Database_DatabaseColumn__isNullable_(false);
                } else {
                    column.Set_Libraries_Data_Database_DatabaseColumn__isNullable_(true);
                }

                value = columns.getString(IS_AUTO_INCREMENT);
                if(value == null || value.isEmpty() || value.compareTo("NO") == 0) {
                    column.Set_Libraries_Data_Database_DatabaseColumn__isAutoIncrement_(false);
                } else {
                    column.Set_Libraries_Data_Database_DatabaseColumn__isAutoIncrement_(true);
                }
                table.Add(column);
            }
        }
    }
}