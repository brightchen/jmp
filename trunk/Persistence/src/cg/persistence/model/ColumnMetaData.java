package cg.persistence.model;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class ColumnMetaData
{
  //the fully class name of the java class
  private String className;
  private int sqlType;   //the SQL type see java.sql.Types
  private String name;
  private String label;   //the title for display
  
  public ColumnMetaData(){}
  
  //the column index should begin with 1 instead of 0
  public ColumnMetaData( ResultSetMetaData metaData, int columnIndex ) throws SQLException
  {
    setClassName( metaData.getColumnClassName( columnIndex ) );
    setSqlType( metaData.getColumnType( columnIndex ) );
    setName( metaData.getColumnName( columnIndex ) );
    setLabel( metaData.getColumnLabel( columnIndex ) );
  }
  
  public String getClassName()
  {
    return className;
  }
  public void setClassName( String className )
  {
    this.className = className;
  }
  public int getSqlType()
  {
    return sqlType;
  }
  public void setSqlType( int sqlType )
  {
    this.sqlType = sqlType;
  }
  public String getSqlTypeName()
  {
    Field[] fields = java.sql.Types.class.getFields();
    for( Field field : fields )
    {
      try
      {
      //it must be public
      if( !Modifier.isPublic( field.getModifiers() ) || !Modifier.isStatic( field.getModifiers() ) )
        continue;
      //the type is int, the int encoding is "I", see Class.getName()
      if( !field.getType().getName().equals( "I" ) )
        continue;
      if( field.getInt( null ) == sqlType )
        return field.getName();
      }
      catch( Exception e )
      {
        e.printStackTrace();
      }
    }
    return null;
  }
  public String getName()
  {
    return name;
  }
  public void setName( String name )
  {
    this.name = name;
  }
  public String getLabel()
  {
    return label;
  }
  public void setLabel( String label )
  {
    this.label = label;
  }
 
  
}
