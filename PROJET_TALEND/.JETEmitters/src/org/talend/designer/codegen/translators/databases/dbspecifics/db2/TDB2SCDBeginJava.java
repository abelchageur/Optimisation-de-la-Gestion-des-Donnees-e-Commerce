package org.talend.designer.codegen.translators.databases.dbspecifics.db2;

import java.util.List;

import org.talend.core.model.metadata.IMetadataColumn;

import org.talend.core.model.process.INode;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.designer.codegen.config.CodeGeneratorArgument;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.process.IConnectionCategory;
import org.talend.core.model.metadata.types.JavaTypesManager;
import org.talend.core.model.metadata.types.JavaType;
import org.talend.core.model.metadata.MetadataTalendType;
import org.talend.core.model.metadata.MappingTypeRetriever;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class TDB2SCDBeginJava {
  protected static String nl;
  public static synchronized TDB2SCDBeginJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TDB2SCDBeginJava result = new TDB2SCDBeginJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = NL + "        ";
  protected final String TEXT_3 = NL + "    ";
  protected final String TEXT_4 = NL + "    int nb_line_update_";
  protected final String TEXT_5 = " = 0;" + NL + "    int nb_line_inserted_";
  protected final String TEXT_6 = " = 0;" + NL + "    int nb_line_rejected_";
  protected final String TEXT_7 = " = 0;";
  protected final String TEXT_8 = NL + "        String schema_";
  protected final String TEXT_9 = " = (String) globalMap.get(\"tableschema_";
  protected final String TEXT_10 = "\");" + NL + "        java.sql.Connection connection_";
  protected final String TEXT_11 = " = (java.sql.Connection)globalMap.get(\"";
  protected final String TEXT_12 = "\");";
  protected final String TEXT_13 = NL + "\t\t    String connectionString_";
  protected final String TEXT_14 = " = \"jdbc:db2://\" + ";
  protected final String TEXT_15 = " + \":\" + ";
  protected final String TEXT_16 = " + \"/\" + ";
  protected final String TEXT_17 = ";    ";
  protected final String TEXT_18 = NL + "\t\t\tString connectionString_";
  protected final String TEXT_19 = ";";
  protected final String TEXT_20 = NL + "        java.lang.Class.forName(\"com.ibm.db2.jcc.DB2Driver\");" + NL + "        " + NL + "\t\t";
  protected final String TEXT_21 = NL + "\t\t" + NL + "\t\t";
  protected final String TEXT_22 = " " + NL + "\tfinal String decryptedPassword_";
  protected final String TEXT_23 = " = routines.system.PasswordEncryptUtil.decryptPassword(";
  protected final String TEXT_24 = ");";
  protected final String TEXT_25 = NL + "\tfinal String decryptedPassword_";
  protected final String TEXT_26 = " = ";
  protected final String TEXT_27 = "; ";
  protected final String TEXT_28 = NL + "\t   \t" + NL + "        java.sql.Connection connection_";
  protected final String TEXT_29 = " = java.sql.DriverManager.getConnection(connectionString_";
  protected final String TEXT_30 = ", ";
  protected final String TEXT_31 = ", decryptedPassword_";
  protected final String TEXT_32 = ");" + NL + "        String schema_";
  protected final String TEXT_33 = ";" + NL + "\t    ";
  protected final String TEXT_34 = "    " + NL + "\tString tableName_";
  protected final String TEXT_35 = " = null;" + NL + "    if(schema_";
  protected final String TEXT_36 = " == null || schema_";
  protected final String TEXT_37 = ".trim().length() == 0) {" + NL + "        tableName_";
  protected final String TEXT_38 = ";" + NL + "    } else {" + NL + "        tableName_";
  protected final String TEXT_39 = " = schema_";
  protected final String TEXT_40 = " + \"\\\".\\\"\" + ";
  protected final String TEXT_41 = ";" + NL + "    }" + NL + "    java.sql.Timestamp timestamp_";
  protected final String TEXT_42 = " = null;" + NL + "    String tmpValue_";
  protected final String TEXT_43 = " = null;    ";
  protected final String TEXT_44 = NL + "        String searching_";
  protected final String TEXT_45 = " = \"";
  protected final String TEXT_46 = "\";" + NL + "        java.sql.PreparedStatement searchingStmt_";
  protected final String TEXT_47 = " = connection_";
  protected final String TEXT_48 = ".prepareStatement(searching_";
  protected final String TEXT_49 = ");" + NL + "        java.sql.ResultSet searchingResult_";
  protected final String TEXT_50 = " = null;";
  protected final String TEXT_51 = NL + "        String search_";
  protected final String TEXT_52 = "\";" + NL + "        java.sql.Statement statement_";
  protected final String TEXT_53 = ".createStatement();" + NL + "        java.sql.ResultSet resultSet_";
  protected final String TEXT_54 = " = statement_";
  protected final String TEXT_55 = ".executeQuery(search_";
  protected final String TEXT_56 = ");" + NL + "        java.util.Map<SCDSK_";
  protected final String TEXT_57 = ", SCDStruct_";
  protected final String TEXT_58 = "> cache_";
  protected final String TEXT_59 = " = new java.util.HashMap<SCDSK_";
  protected final String TEXT_60 = ">();" + NL + "        while(resultSet_";
  protected final String TEXT_61 = ".next()) {" + NL + "            SCDSK_";
  protected final String TEXT_62 = " sk_";
  protected final String TEXT_63 = " = new SCDSK_";
  protected final String TEXT_64 = "();" + NL + "            SCDStruct_";
  protected final String TEXT_65 = " row_";
  protected final String TEXT_66 = " = new SCDStruct_";
  protected final String TEXT_67 = "();";
  protected final String TEXT_68 = NL + "                    tmpValue_";
  protected final String TEXT_69 = " = resultSet_";
  protected final String TEXT_70 = ".getString(";
  protected final String TEXT_71 = ");" + NL + "                    if (tmpValue_";
  protected final String TEXT_72 = " != null && tmpValue_";
  protected final String TEXT_73 = ".length() > 0) {";
  protected final String TEXT_74 = NL + "                        ";
  protected final String TEXT_75 = ".";
  protected final String TEXT_76 = " = tmpValue_";
  protected final String TEXT_77 = ".charAt(0);" + NL + "                    } else {";
  protected final String TEXT_78 = NL + "                            if(tmpValue_";
  protected final String TEXT_79 = " == null) {";
  protected final String TEXT_80 = NL + "                                ";
  protected final String TEXT_81 = " = null;" + NL + "                            } else {";
  protected final String TEXT_82 = " = '\\0';" + NL + "                            }";
  protected final String TEXT_83 = NL + "                            if((\"\").equals(tmpValue_";
  protected final String TEXT_84 = ")) {";
  protected final String TEXT_85 = " = '\\0';" + NL + "                            } else {" + NL + "                                throw new RuntimeException(\"Value is empty for column : '";
  protected final String TEXT_86 = "' in '";
  protected final String TEXT_87 = "' connection, value is invalid or this column should be nullable or have a default value.\");" + NL + "                            }";
  protected final String TEXT_88 = NL + "                    }";
  protected final String TEXT_89 = NL + "                    timestamp_";
  protected final String TEXT_90 = ".getTimestamp(";
  protected final String TEXT_91 = ");" + NL + "                    if(timestamp_";
  protected final String TEXT_92 = " != null) {";
  protected final String TEXT_93 = " = new java.util.Date(timestamp_";
  protected final String TEXT_94 = ".getTime());" + NL + "                    } else {";
  protected final String TEXT_95 = " = null;" + NL + "                    }";
  protected final String TEXT_96 = NL + "                    if(resultSet_";
  protected final String TEXT_97 = ".getObject(";
  protected final String TEXT_98 = ") != null) {";
  protected final String TEXT_99 = ".get";
  protected final String TEXT_100 = "(";
  protected final String TEXT_101 = ");" + NL + "                    }";
  protected final String TEXT_102 = NL + "            cache_";
  protected final String TEXT_103 = ".put(sk_";
  protected final String TEXT_104 = ", row_";
  protected final String TEXT_105 = ");" + NL + "        }" + NL + "        resultSet_";
  protected final String TEXT_106 = ".close();" + NL + "        statement_";
  protected final String TEXT_107 = ".close();";
  protected final String TEXT_108 = NL + "            System.out.println(\"[";
  protected final String TEXT_109 = "] comparison cache loaded\");";
  protected final String TEXT_110 = " nextSurrogateKey_";
  protected final String TEXT_111 = " = 1;";
  protected final String TEXT_112 = NL + "            if(cache_";
  protected final String TEXT_113 = ".size() > 0) {";
  protected final String TEXT_114 = NL + "        String tmpQuery_";
  protected final String TEXT_115 = " = \"SELECT MAX(\\\"";
  protected final String TEXT_116 = "\\\") FROM \\\"\" + tableName_";
  protected final String TEXT_117 = " + \"\\\"\";" + NL + "        java.sql.Statement tmpStmt_";
  protected final String TEXT_118 = ".createStatement();" + NL + "        java.sql.ResultSet tmpRS_";
  protected final String TEXT_119 = " = tmpStmt_";
  protected final String TEXT_120 = ".executeQuery(tmpQuery_";
  protected final String TEXT_121 = NL + "        if(tmpRS_";
  protected final String TEXT_122 = ".next() && tmpRS_";
  protected final String TEXT_123 = ".getObject(1) != null) {" + NL + "            nextSurrogateKey_";
  protected final String TEXT_124 = " = tmpRS_";
  protected final String TEXT_125 = "(1) + 1;" + NL + "        }" + NL + "        tmpRS_";
  protected final String TEXT_126 = ".close();" + NL + "        tmpStmt_";
  protected final String TEXT_127 = NL + "            }            ";
  protected final String TEXT_128 = NL + "    String insertionSQL_";
  protected final String TEXT_129 = "\";" + NL + "    java.sql.PreparedStatement insertionStatement_";
  protected final String TEXT_130 = ".prepareStatement(insertionSQL_";
  protected final String TEXT_131 = NL + "            insertionStatement_";
  protected final String TEXT_132 = ".setTimestamp(";
  protected final String TEXT_133 = ", new java.sql.Timestamp(start_Hash.get(\"";
  protected final String TEXT_134 = "\")));";
  protected final String TEXT_135 = ".setNull(";
  protected final String TEXT_136 = ", java.sql.Types.DATE);";
  protected final String TEXT_137 = NL + "            String endDateTimeDetail_";
  protected final String TEXT_138 = ";" + NL + "            String datePattern_";
  protected final String TEXT_139 = " = \"yyyy-MM-dd\";" + NL + "            if(endDateTimeDetail_";
  protected final String TEXT_140 = ".length()>0){" + NL + "            \tendDateTimeDetail_";
  protected final String TEXT_141 = " = \" \"+endDateTimeDetail_";
  protected final String TEXT_142 = ";" + NL + "            \tdatePattern_";
  protected final String TEXT_143 = " = \"yyyy-MM-dd HH:mm:ss\";" + NL + "            }" + NL + "            insertionStatement_";
  protected final String TEXT_144 = ", new java.sql.Timestamp(new java.text.SimpleDateFormat(datePattern_";
  protected final String TEXT_145 = ").parse(";
  protected final String TEXT_146 = " + \"-01-01\"+endDateTimeDetail_";
  protected final String TEXT_147 = ").getTime()));";
  protected final String TEXT_148 = NL + "        String updateSQLForType1_";
  protected final String TEXT_149 = "\";" + NL + "        java.sql.PreparedStatement updateForType1_";
  protected final String TEXT_150 = ".prepareStatement(updateSQLForType1_";
  protected final String TEXT_151 = ");        ";
  protected final String TEXT_152 = NL + "        String updateSQLForType2_";
  protected final String TEXT_153 = "\";" + NL + "        java.sql.PreparedStatement updateForType2_";
  protected final String TEXT_154 = ".prepareStatement(updateSQLForType2_";
  protected final String TEXT_155 = NL + "            updateForType2_";
  protected final String TEXT_156 = ".setTimestamp(1, new java.sql.Timestamp(start_Hash.get(\"";
  protected final String TEXT_157 = NL + "        String updateSQLForType3_";
  protected final String TEXT_158 = "\";" + NL + "        java.sql.PreparedStatement updateForType3_";
  protected final String TEXT_159 = ".prepareStatement(updateSQLForType3_";
  protected final String TEXT_160 = NL + "        SCDSK_";
  protected final String TEXT_161 = " lookUpKey_";
  protected final String TEXT_162 = " = null;        ";
  protected final String TEXT_163 = NL + "    SCDStruct_";
  protected final String TEXT_164 = " lookUpValue_";
  protected final String TEXT_165 = NL;

    public static final String DB2 = "DB2";
    public static final String INGRES = "INGRES";    
    public static final String MSSQL = "MSSQL";
    public static final String MYSQL = "MYSQL";
    public static final String NETEZZA = "NETEZZA";
    public static final String ORACLE = "ORACLE";
    public static final String POSTGRESQL = "POSTGRESQL";
    public static final String POSTGREPLUS = "POSTGREPLUS";
    public static final String SYBASE = "SYBASE";
    public static final String INFORMIX = "INFORMIX";
    public static final String VERTICA = "VERTICA";
    public static final String TERADATA = "TERADATA";
	private Map<String, SCDManager> managerMap = new HashMap<String, SCDManager>();
    public class Column {
        private String labelName;
        private String columnName;
        private String javaType;
        private String talendType;
        private String dbType;
        private boolean isNullable;
        private boolean isKey;
        public Column(String columnName, String javaType) {
            this.columnName = columnName;
            this.javaType = javaType;
        }
        public Column(IMetadataColumn column) {
            labelName = column.getLabel();
            columnName = column.getOriginalDbColumnName();
            javaType = JavaTypesManager.getTypeToGenerate(column.getTalendType(), column.isNullable());
            talendType = column.getTalendType();
            dbType = column.getType();
            isNullable = column.isNullable();
            isKey = column.isKey();
        }
        public String getLabelName() {
            return labelName;
        }
        public String getColumnName() {
            return columnName;
        }
        public String getJavaType() {
            return javaType;
        }
        public String getTalendType() {
            return talendType;
        }
        public String getDbType() {
            if(dbType==null){
                return "";
            }
            return dbType;
        }
        public boolean isNullable() {
            return isNullable;
        }
        public boolean isKey() {
            return isKey;
        }
    }
    
    public class InfromixSCDManager extends SCDManager {
    	
    	  public InfromixSCDManager(String cid) {
              super(cid);
          }
          protected String getDBMSId() {
              return "informix_id";
          }        
          protected String getLProtectedChar() {
              return "";
          }
          protected String getRProtectedChar() {
              return "";
          }
          protected String getSequence(String surrogateKeySequence) {
              return "+ \"" + surrogateKeySequence + "\" + \".nextval";
          }
          protected String getIncrement() {
              return "";
          }        
          protected String getDate(String endDateValue) {
              return "TO_DATE('\" + " + endDateValue + " + \"-01-01', 'yyyy-mm-dd')";
          }        
          protected String getYear(String endDateField) {
              return "EXTRACT(YEAR FROM " + getLProtectedChar() + endDateField + getRProtectedChar() + ")";
          }
          protected String getActiveFieldTrueValue(JavaType activeFieldType) {
              if(activeFieldType == JavaTypesManager.STRING) {
                  return "'true'";
              } else if(activeFieldType == JavaTypesManager.BOOLEAN) {
                  return "'t'";
              } else {
                  return "1";
              }            
          }
          protected String getActiveFieldFalseValue(JavaType activeFieldType) {
              if(activeFieldType == JavaTypesManager.STRING) {
                  return "'false'";
              } else if(activeFieldType == JavaTypesManager.BOOLEAN) {
                  return "'f'";
              } else {
                  return "0";
              }            
          }        
    	
    }
    
    public class SCDConfiger {
        private String surrogateKeyField;
        private String surrogateKeyFieldType;
        private String surrogateKeyValueType;
        private String surrogateKeySequence;
        private String surrogateKeyInputField;
        private String surrogateKeyRoutine;
        private List<Column> sourceKeys;
        private boolean isEnableType0;
        private boolean isEnableType1;
        private boolean isEnableType2;
        private boolean isEnableType3;
        private DimensionType0Configer dimensionType0Configer;
        private DimensionType1Configer dimensionType1Configer;
        private DimensionType2Configer dimensionType2Configer;
        private DimensionType3Configer dimensionType3Configer;
        public SCDConfiger(List<Column> sourceKeys) {
            this.sourceKeys = sourceKeys;
        }
        public void setSurrogateKeyFieldType(String surrogateKeyFieldType) {
            this.surrogateKeyFieldType = surrogateKeyFieldType;
        }
        public String getSurrogateKeyFieldType() {
            return surrogateKeyFieldType;
        }
        public void setSurrogateKeyField(String surrogateKeyField) {
            this.surrogateKeyField = surrogateKeyField;
        }
        public String getSurrogateKeyField() {
            return surrogateKeyField;
        }
        public void setSurrogateKeyValueType(String surrogateKeyValueType) {
            this.surrogateKeyValueType = surrogateKeyValueType;
        }
        public String getSurrogateKeyValueType() {
            return surrogateKeyValueType;
        }
        public void setSurrogateKeySequence(String surrogateKeySequence) {
            this.surrogateKeySequence = surrogateKeySequence;
        }
        public String getSurrogateKeySequence() {
            return surrogateKeySequence;
        }
        public void setSurrogateKeyInputField(String surrogateKeyInputField) {
            this.surrogateKeyInputField = surrogateKeyInputField;
        }
        public String getSurrogateKeyInputField() {
            return surrogateKeyInputField;
        }
        public void setSurrogateKeyRoutine(String surrogateKeyRoutine) {
            this.surrogateKeyRoutine = surrogateKeyRoutine;
        }
        public String getSurrogateKeyRoutine() {
            return surrogateKeyRoutine;
        }
        public void setEnableType0(boolean isEnableType0) {
            this.isEnableType0 = isEnableType0;
        }
        public boolean isEnableType0() {
            return isEnableType0;
        }
        public void setEnableType1(boolean isEnableType1) {
            this.isEnableType1 = isEnableType1;
        }
        public boolean isEnableType1() {
            return isEnableType1;
        }
        public void setEnableType2(boolean isEnableType2) {
            this.isEnableType2 = isEnableType2;
        }
        public boolean isEnableType2() {
            return isEnableType2;
        }
        public void setEnableType3(boolean isEnableType3) {
            this.isEnableType3 = isEnableType3;
        }
        public boolean isEnableType3() {
            return isEnableType3;
        }
        public void setDimensionType0Configer(DimensionType0Configer dimensionType0Configer) {
            this.dimensionType0Configer = dimensionType0Configer;
        }
        public DimensionType0Configer getDimensionType0Configer() {
            return dimensionType0Configer;
        }
        public void setDimensionType1Configer(DimensionType1Configer dimensionType1Configer) {
            this.dimensionType1Configer = dimensionType1Configer;
        }
        public DimensionType1Configer getDimensionType1Configer() {
            return dimensionType1Configer;
        }
        public void setDimensionType2Configer(DimensionType2Configer dimensionType2Configer) {
            this.dimensionType2Configer = dimensionType2Configer;
        }
        public DimensionType2Configer getDimensionType2Configer() {
            return dimensionType2Configer;
        }
        public void setDimensionType3Configer(DimensionType3Configer dimensionType3Configer) {
            this.dimensionType3Configer = dimensionType3Configer;
        }
        public DimensionType3Configer getDimensionType3Configer() {
            return dimensionType3Configer;
        }
        public List<Column> getSourceKeys() {
            return sourceKeys;
        }
    }
    public class DimensionType0Configer {
        private List<Column> dimensionAttributes;
        public DimensionType0Configer(List<Column> dimensionAttributes) {
            this.dimensionAttributes = dimensionAttributes;
        }
        public List<Column> getDimensionAttributes() {
            return dimensionAttributes;
        }
    }
    public class DimensionType1Configer {
        private List<Column> dimensionAttributes;
        private boolean isOnlyUpdateActive;
        public DimensionType1Configer(List<Column> dimensionAttributes) {
            this.dimensionAttributes = dimensionAttributes;
        }
        public List<Column> getDimensionAttributes() {
            return dimensionAttributes;
        }
        public void setOnlyUpdateActive(boolean isOnlyUpdateActive) {
            this.isOnlyUpdateActive = isOnlyUpdateActive;
        }
        public boolean isOnlyUpdateActive() {
            return isOnlyUpdateActive;
        }
    }
    public class DimensionType2Configer {
        private List<Column> dimensionAttributes;
        private String startDateField;
        private String startDateValueType;
        private String startDateValue;
        private String endDateField;
        private String endDateValueType;
        private String endDateValue;
        private boolean isEnableVersion;
        private String versionField;
        private String versionFieldType;
        private boolean isEnableActive;
        private String activeField;
        private JavaType activeFieldType;        
        public DimensionType2Configer(List<Column> dimensionAttributes) {
            this.dimensionAttributes = dimensionAttributes;
        }
        public void setStartDateField(String startDateField) {
            this.startDateField = startDateField;
        }
        public String getStartDateField() {
            return startDateField;
        }
        public void setStartDateValueType(String startDateValueType) {
            this.startDateValueType = startDateValueType;
        }
        public String getStartDateValueType() {
            return startDateValueType;
        }
        public void setStartDateValue(String startDateValue) {
            this.startDateValue = startDateValue;
        }
        public String getStartDateValue() {
            return startDateValue;
        }
        public void setEndDateField(String endDateField) {
            this.endDateField = endDateField;
        }
        public String getEndDateField() {
            return endDateField;
        }
        public void setEndDateValueType(String endDateValueType) {
            this.endDateValueType = endDateValueType;
        }
        public String getEndDateValueType() {
            return endDateValueType;
        }
        public void setEndDateValue(String endDateValue) {
            this.endDateValue = endDateValue;
        }
        public String getEndDateValue() {
            return endDateValue;
        }
        public void setEnableVersion(boolean isEnableVersion) {
            this.isEnableVersion = isEnableVersion;
        }
        public boolean isEnableVersion() {
            return isEnableVersion;
        }
        public void setVersionField(String versionField) {
            this.versionField = versionField;
        }
        public String getVersionField() {
            return versionField;
        }
        public void setVersionFieldType(String versionFieldType) {
            this.versionFieldType = versionFieldType;
        }
        public String getVersionFieldType() {
            return versionFieldType;
        }
        public void setEnableActive(boolean isEnableActive) {
            this.isEnableActive = isEnableActive;
        }
        public boolean isEnableActive() {
            return isEnableActive;
        }
        public void setActiveField(String activeField) {
            this.activeField = activeField;
        }
        public String getActiveField() {
            return activeField;
        }
        public void setActiveFieldType(JavaType activeFieldType) {
            this.activeFieldType = activeFieldType;
        }
        public JavaType getActiveFieldType() {
            return activeFieldType;
        }
        public List<Column> getDimensionAttributes() {
            return dimensionAttributes;
        }        
    }
    public class DimensionType3Configer {
        private List<DimensionType3Attribute> dimensionAttributes;
        public DimensionType3Configer(List<DimensionType3Attribute> dimensionAttributes) {
            this.dimensionAttributes = dimensionAttributes;
        }
        public List<DimensionType3Attribute> getDimensionAttributes() {
            return dimensionAttributes;
        }
    }
    public class DimensionType3Attribute {
        private Column currentColumn;
        private Column prevColumn;
        public DimensionType3Attribute(Column currentColumn, Column prevColumn) {
            this.currentColumn = currentColumn;
            this.prevColumn = prevColumn;
        }
        public Column getCurrentColumn() {
            return currentColumn;
        }
        public Column getPrevColumn() {
            return prevColumn;
        }
    }
    public Column getColumn(IMetadataColumn column) {
        return new Column(column);
    }
    public Column getColumn(String columnName, String javaType) {
        return new Column(columnName, javaType);
    }
    public List<IMetadataColumn> getInputSchema(INode node) {
        List<IMetadataColumn> inputSchema = null;
        List<? extends IConnection> incomingConnections = node.getIncomingConnections();
        if(incomingConnections != null && incomingConnections.size() > 0) {
            for(IConnection incomingConnection : incomingConnections) {
                if(incomingConnection.getLineStyle().hasConnectionCategory(IConnectionCategory.DATA)) {
                    IMetadataTable schemaTable = incomingConnection.getMetadataTable();
                    if(schemaTable != null) {
                        inputSchema = schemaTable.getListColumns();
                    }
                }
            }
        }
        return inputSchema;
    }
    public List<IMetadataColumn> getOutputSchema(INode node) {
        List<IMetadataColumn> outputSchema = null;
        List<IMetadataTable> schemaTables = node.getMetadataList();
        if(schemaTables != null && schemaTables.size() > 0) {
            IMetadataTable schemaTable = schemaTables.get(0);
            if(schemaTable != null) {
                outputSchema = schemaTable.getListColumns();
            }
        }
        return outputSchema;
    }
    public DimensionType0Configer getDimensionType0Configer(INode node) {
        DimensionType0Configer dimensionType0Configer = null;
        List<IMetadataColumn> inputSchema = getInputSchema(node);
        if(inputSchema != null && inputSchema.size() > 0) {
            List<Map<String, String>> dimensionType0s = (List<Map<String, String>>)ElementParameterParser.getObjectValue(node, "__L0_FIELDS__");
            List<Column> dimensionAttributes = new ArrayList<Column>();
            if(dimensionType0s != null && dimensionType0s.size() > 0) {
                for(Map<String, String> dimensionType0 : dimensionType0s) {
                    for(IMetadataColumn metadataColumn : inputSchema) {
                        if(dimensionType0.get("NAME").equals(metadataColumn.getLabel())) {
                            Column column = getColumn(metadataColumn);
                            dimensionAttributes.add(column);
                            break;
                        }
                    }
                }
            }
            dimensionType0Configer = new DimensionType0Configer(dimensionAttributes);
        }
        return dimensionType0Configer;
    }
    public DimensionType1Configer getDimensionType1Configer(INode node) {
        DimensionType1Configer dimensionType1Configer = null;
        List<IMetadataColumn> inputSchema = getInputSchema(node);
        if(inputSchema != null && inputSchema.size() > 0) {
            List<Map<String, String>> dimensionType1s = (List<Map<String, String>>)ElementParameterParser.getObjectValue(node, "__L1_FIELDS__");
            List<Column> dimensionAttributes = new ArrayList<Column>();
            if(dimensionType1s != null && dimensionType1s.size() > 0) {
                for(Map<String, String> dimensionType1 : dimensionType1s) {
                    for(IMetadataColumn metadataColumn : inputSchema) {
                        if(dimensionType1.get("NAME").equals(metadataColumn.getLabel())) {
                            Column column = getColumn(metadataColumn);
                            dimensionAttributes.add(column);
                            break;
                        }
                    }
                }
            }
            dimensionType1Configer = new DimensionType1Configer(dimensionAttributes);
            boolean isOnlyUpdateActive = ElementParameterParser.getValue(node, "__ONLY_UPDATE_ACTIVE__").equals("true");
            dimensionType1Configer.setOnlyUpdateActive(isOnlyUpdateActive);
        }
        return dimensionType1Configer;
    }
    public String getColumnNameByLabel(String labelName, List<IMetadataColumn> schema) {
        String columnName = "";
        for(IMetadataColumn metadataColumn : schema) {
            if(labelName.equals(metadataColumn.getLabel())) {
                columnName = metadataColumn.getOriginalDbColumnName();
                break;
            }
        }
        return columnName;
    }
    public JavaType getJavaType(String labelName, List<IMetadataColumn> schema) {
        JavaType javaType = null;
        for(IMetadataColumn metadataColumn : schema) {
            if(labelName.equals(metadataColumn.getLabel())) {
                javaType = JavaTypesManager.getJavaTypeFromId(metadataColumn.getTalendType());
                break;
            }
        }
        return javaType;
    }
    public String getGeneratedType(String labelName, List<IMetadataColumn> schema) {
        String generatedType = "";
        for(IMetadataColumn metadataColumn : schema) {
            if(labelName.equals(metadataColumn.getLabel())) {
                generatedType = JavaTypesManager.getTypeToGenerate(metadataColumn.getTalendType(), metadataColumn.isNullable());
                break;
            }
        }        
        return generatedType;
    }
    public boolean isEqualWithSK(String surrogateKeyField, List<Column> sourceKeys) {
        boolean isEqualWithSK = false;
        for(Column column : sourceKeys) {
            if(column.getColumnName().equals(surrogateKeyField)) {
                isEqualWithSK = true;
                break;
            }
        }
        return isEqualWithSK;
    }    
    public DimensionType2Configer getDimensionType2Configer(INode node) {
        DimensionType2Configer dimensionType2Configer = null;
        List<IMetadataColumn> inputSchema = getInputSchema(node);
        List<IMetadataColumn> outputSchema = getOutputSchema(node);
        if(inputSchema != null && inputSchema.size() > 0 && outputSchema != null && outputSchema.size() > 0) {
            List<Map<String, String>> dimensionType2s = (List<Map<String, String>>)ElementParameterParser.getObjectValue(node, "__L2_FIELDS__");
            List<Column> dimensionAttributes = new ArrayList<Column>();
            if(dimensionType2s != null && dimensionType2s.size() > 0) {
                for(Map<String, String> dimensionType2 : dimensionType2s) {
                    for(IMetadataColumn metadataColumn : inputSchema) {
                        if(dimensionType2.get("NAME").equals(metadataColumn.getLabel())) {
                            Column column = getColumn(metadataColumn);
                            dimensionAttributes.add(column);
                            break;
                        }
                    }
                }
            }
            dimensionType2Configer = new DimensionType2Configer(dimensionAttributes);
            String startDateField = getColumnNameByLabel(ElementParameterParser.getValue(node, "__L2_STARTDATE_FIELD__"), outputSchema);
            String startDateValueType = ElementParameterParser.getValue(node, "__L2_STARTDATE_VALUE__");
            String startDateValue = ElementParameterParser.getValue(node, "__L2_STARTDATE_INPUT_FIELD__");
            String endDateField = getColumnNameByLabel(ElementParameterParser.getValue(node, "__L2_ENDDATE_FIELD__"), outputSchema);
            String endDateValueType = ElementParameterParser.getValue(node, "__L2_ENDDATE_VALUE__");
            String endDateValue = ElementParameterParser.getValue(node, "__L2_ENDDATE_FIXED_VALUE__");
            boolean isEnableVersion = ElementParameterParser.getValue(node, "__USE_L2_VERSION__").equals("true");
            String versionField = getColumnNameByLabel(ElementParameterParser.getValue(node, "__L2_VERSION_FIELD__"), outputSchema);
            String versionFieldType = getGeneratedType(ElementParameterParser.getValue(node, "__L2_VERSION_FIELD__"), outputSchema);
            boolean isEnableActive = ElementParameterParser.getValue(node, "__USE_L2_ACTIVE__").equals("true");
            String activeField = getColumnNameByLabel(ElementParameterParser.getValue(node, "__L2_ACTIVE_FIELD__"), outputSchema);
            JavaType activeFieldType = getJavaType(ElementParameterParser.getValue(node, "__L2_ACTIVE_FIELD__"), outputSchema);
            dimensionType2Configer.setStartDateField(startDateField);
            dimensionType2Configer.setStartDateValueType(startDateValueType);
            dimensionType2Configer.setStartDateValue(startDateValue);
            dimensionType2Configer.setEndDateField(endDateField);
            dimensionType2Configer.setEndDateValueType(endDateValueType);
            dimensionType2Configer.setEndDateValue(endDateValue);
            dimensionType2Configer.setEnableVersion(isEnableVersion);
            dimensionType2Configer.setVersionField(versionField);
            dimensionType2Configer.setVersionFieldType(versionFieldType);
            dimensionType2Configer.setEnableActive(isEnableActive);
            dimensionType2Configer.setActiveField(activeField);
            dimensionType2Configer.setActiveFieldType(activeFieldType);
        }
        return dimensionType2Configer;
    }
    public DimensionType3Configer getDimensionType3Configer(INode node) {
        DimensionType3Configer dimensionType3Configer = null;
        List<IMetadataColumn> inputSchema = getInputSchema(node);
        List<IMetadataColumn> outputSchema = getOutputSchema(node);
        if(inputSchema != null && inputSchema.size() > 0 && outputSchema != null && outputSchema.size() > 0) {
            List<Map<String, String>> dimensionType3s = (List<Map<String, String>>) ElementParameterParser.getObjectValue(node, "__L3_FIELDS__");
            List<DimensionType3Attribute> dimensionAttributes = new ArrayList<DimensionType3Attribute>();
            if(dimensionType3s != null && dimensionType3s.size() > 0) {
                Column currentColumn = null;
                Column prevColumn = null;
                for(Map<String, String> dimensionType3 : dimensionType3s) {
                    for(IMetadataColumn metadataColumn : inputSchema) {
                        if(dimensionType3.get("CURRENT_VALUE").equals(metadataColumn.getLabel())) {
                            currentColumn = getColumn(metadataColumn);
                            break;
                        }
                    }
                    for(IMetadataColumn metadataColumn : outputSchema) {
                        if(dimensionType3.get("PREV_VALUE").equals(metadataColumn.getLabel())) {
                            prevColumn = getColumn(metadataColumn);
                            break;
                        }
                    }
                    DimensionType3Attribute dimensionType3Attribute = new DimensionType3Attribute(currentColumn, prevColumn);
                    dimensionAttributes.add(dimensionType3Attribute);
                }
            }
            dimensionType3Configer = new DimensionType3Configer(dimensionAttributes);
        }
        return dimensionType3Configer;
    }    
    public SCDConfiger getSCDConfiger(INode node) {
        SCDConfiger scdConfiger = null;
        List<IMetadataColumn> inputSchema = getInputSchema(node);
        List<IMetadataColumn> outputSchema = getOutputSchema(node);
        if(inputSchema != null && inputSchema.size() > 0 && outputSchema != null && outputSchema.size() > 0) {
            List<Map<String, String>> sourceKeyMaps = (List<Map<String, String>>)ElementParameterParser.getObjectValue(node, "__SOURCE_KEYS__");
            List<Column> sourceKeys = new ArrayList<Column>();
            if(sourceKeyMaps != null && sourceKeyMaps.size() > 0) {
                for(Map<String, String> sourceKeyMap : sourceKeyMaps) {
                    for(IMetadataColumn metadataColumn : inputSchema) {
                        if(sourceKeyMap.get("NAME").equals(metadataColumn.getLabel())) {
                            Column column = getColumn(metadataColumn);
                            sourceKeys.add(column);
                            break;
                        }
                    }
                }
            }
            scdConfiger = new SCDConfiger(sourceKeys);            
            String surrogateKeyField = getColumnNameByLabel(ElementParameterParser.getValue(node, "__SURROGATE_KEY__"), outputSchema);
            String surrogateKeyFieldType = getGeneratedType(ElementParameterParser.getValue(node, "__SURROGATE_KEY__"), outputSchema);
            String surrogateKeyValueType = ElementParameterParser.getValue(node, "__SK_CREATION__");
            String surrogateKeySequence = ElementParameterParser.getValue(node, "__SK_DB_SEQUENCE__");
            String surrogateKeyInputField = ElementParameterParser.getValue(node, "__SK_INPUT_FIELD__");
            String surrogateKeyRoutine = ElementParameterParser.getValue(node, "__SK_ROUTINE__");
            boolean isEnableType0 = ElementParameterParser.getValue(node, "__USE_L0__").equals("true");
            boolean isEnableType1 = ElementParameterParser.getValue(node, "__USE_L1__").equals("true");
            boolean isEnableType2 = ElementParameterParser.getValue(node, "__USE_L2__").equals("true");
            boolean isEnableType3 = ElementParameterParser.getValue(node, "__USE_L3__").equals("true");
            scdConfiger.setSurrogateKeyField(surrogateKeyField);
            scdConfiger.setSurrogateKeyFieldType(surrogateKeyFieldType);
            scdConfiger.setSurrogateKeyValueType(surrogateKeyValueType);
            scdConfiger.setSurrogateKeySequence(surrogateKeySequence);
            scdConfiger.setSurrogateKeyInputField(surrogateKeyInputField);
            scdConfiger.setSurrogateKeyRoutine(surrogateKeyRoutine);
            scdConfiger.setEnableType0(isEnableType0);
            scdConfiger.setEnableType1(isEnableType1);
            scdConfiger.setEnableType2(isEnableType2);
            scdConfiger.setEnableType3(isEnableType3);
            scdConfiger.setDimensionType0Configer(getDimensionType0Configer(node));
            scdConfiger.setDimensionType1Configer(getDimensionType1Configer(node));
            scdConfiger.setDimensionType2Configer(getDimensionType2Configer(node));
            scdConfiger.setDimensionType3Configer(getDimensionType3Configer(node));
        }
        return scdConfiger;
    }
    public String genSourceKeyClass(SCDConfiger scdConfiger, String cid) {       
        
        StringBuilder sourceKeyClass = new StringBuilder();
        
        sourceKeyClass.append("class SCDSK_" + cid + " {\r\n");
        sourceKeyClass.append("private int hashCode;\r\n");
        sourceKeyClass.append("public boolean hashCodeDirty = true;\r\n");        
        List<Column> sourceKeys = scdConfiger.getSourceKeys();
        for(Column sourceKey : sourceKeys) {
            sourceKeyClass.append(sourceKey.getJavaType() + " " + sourceKey.getColumnName() + ";\r\n");
        }
        
        //equals()
        sourceKeyClass.append("public boolean equals(Object obj) {\r\n");
        sourceKeyClass.append("if (this == obj) return true;\r\n");
        sourceKeyClass.append("if (obj == null) return false;\r\n");
        sourceKeyClass.append("if (getClass() != obj.getClass()) return false;\r\n");
        sourceKeyClass.append("final SCDSK_" + cid + " other = (SCDSK_" + cid + ") obj;\r\n");
        boolean isFirstSK = true;
        for(Column sourceKey : sourceKeys) { 
            //if (JavaTypesManager.isJavaPrimitiveType(sourceKey.getJavaType(), sourceKey.isNullable())) {
            if (sourceKey.getJavaType().equals("java.util.Date") || sourceKey.getJavaType().equals("String") 
            		|| sourceKey.getJavaType().equals("Object") || sourceKey.getJavaType().equals("BigDecimal")
            		|| sourceKey.isNullable()) {
                sourceKeyClass.append("if (this." + sourceKey.getColumnName() + " == null) {\r\n");
                sourceKeyClass.append("if (other." + sourceKey.getColumnName() + "!= null)\r\n");
                sourceKeyClass.append("return false;\r\n");
                sourceKeyClass.append("} else if (!this." + sourceKey.getColumnName() + ".equals(other." + sourceKey.getColumnName() + "))\r\n");
                sourceKeyClass.append("return false;\r\n");
            } else {                
                sourceKeyClass.append("if (this." + sourceKey.getColumnName() + "!= other." + sourceKey.getColumnName() + ")\r\n");
                sourceKeyClass.append("return false;\r\n");
            }
            sourceKeyClass.append("\r\n");
        }        
        sourceKeyClass.append("return true;\r\n");        
        sourceKeyClass.append("}\r\n");
        
        //hashCode()
        sourceKeyClass.append("public int hashCode() {\r\n");
        sourceKeyClass.append("if(hashCodeDirty) {\r\n");
        sourceKeyClass.append("int prime = 31;");
        for(Column sourceKey : sourceKeys) {
            if(sourceKey.getJavaType().equals("java.util.Date") || sourceKey.getJavaType().equals("String") 
            		|| sourceKey.getJavaType().equals("Object") || sourceKey.getJavaType().equals("BigDecimal") 
            		|| sourceKey.isNullable() || sourceKey.getJavaType().equals("byte[]")) {
                sourceKeyClass.append("hashCode = prime * hashCode + (" + sourceKey.getColumnName() + " == null ? 0 : " + sourceKey.getColumnName() + ".hashCode());\r\n");
            } else {
                if(sourceKey.getJavaType().equals("long")) {
                    sourceKeyClass.append("hashCode = prime * hashCode + (new Long(" + sourceKey.getColumnName() + ")).hashCode();\r\n");
                }else if(sourceKey.getJavaType().equals("double")) {
                    sourceKeyClass.append("hashCode = prime * hashCode + (new Double(" + sourceKey.getColumnName() + ")).hashCode();\r\n");
                }else if(sourceKey.getJavaType().equals("float")) {
                    sourceKeyClass.append("hashCode = prime * hashCode + (new Float(" + sourceKey.getColumnName() + ")).hashCode();\r\n");
                } else {
                    sourceKeyClass.append("hashCode = prime * hashCode + " + sourceKey.getColumnName() + ";\r\n");
                }
            }
        }
        sourceKeyClass.append("hashCodeDirty = false;\r\n");
        sourceKeyClass.append("}\r\n");
        sourceKeyClass.append("return hashCode;\r\n");
        sourceKeyClass.append("}\r\n");
        sourceKeyClass.append("}\r\n");
        return sourceKeyClass.toString();
    }
    public String genDimensionAttributeClass(SCDConfiger scdConfiger, String cid) {
        StringBuilder dimensionAttributeClass = new StringBuilder();
        dimensionAttributeClass.append("class SCDStruct_" + cid + " {\r\n");
        if(scdConfiger.isEnableType0()) {
            List<Column> type0Attributes = scdConfiger.getDimensionType0Configer().getDimensionAttributes();
            for(Column type0Attribute : type0Attributes) {
                dimensionAttributeClass.append("private " + type0Attribute.getJavaType() + " " + type0Attribute.getColumnName() + ";\r\n");
            }
        }
        if(scdConfiger.isEnableType1()) {
            List<Column> type1Attributes = scdConfiger.getDimensionType1Configer().getDimensionAttributes();
            for(Column type1Attribute : type1Attributes) {
                dimensionAttributeClass.append("private " + type1Attribute.getJavaType() + " " + type1Attribute.getColumnName() + ";\r\n");
            }
        }
        if(scdConfiger.isEnableType2()) {
            List<Column> type2Attributes = scdConfiger.getDimensionType2Configer().getDimensionAttributes();
            for(Column type2Attribute : type2Attributes) {
                dimensionAttributeClass.append("private " + type2Attribute.getJavaType() + " " + type2Attribute.getColumnName() + ";\r\n");
            }
        }
        if(scdConfiger.isEnableType3()) {
            List<DimensionType3Attribute> type3Attributes = scdConfiger.getDimensionType3Configer().getDimensionAttributes();
            for(DimensionType3Attribute type3Attribute : type3Attributes) {
                dimensionAttributeClass.append("private " + type3Attribute.getCurrentColumn().getJavaType() + " " + type3Attribute.getCurrentColumn().getColumnName() + ";\r\n");
                dimensionAttributeClass.append("private " + type3Attribute.getPrevColumn().getJavaType() + " " + type3Attribute.getPrevColumn().getColumnName() + ";\r\n");
            }
        }
        if(scdConfiger.isEnableType2() && scdConfiger.getDimensionType2Configer().isEnableVersion()) {
            String versionField = scdConfiger.getDimensionType2Configer().getVersionField();
            String versionFieldType = scdConfiger.getDimensionType2Configer().getVersionFieldType();
            dimensionAttributeClass.append("private " + versionFieldType + " " + versionField + ";\r\n");
        }        
        dimensionAttributeClass.append("}\r\n");
        return dimensionAttributeClass.toString();
    }
    public List<Column> getInsertionColumnList(SCDConfiger scdConfiger) {
        List<Column> insertionColumnList = new ArrayList<Column>();
        String surrogateKeyField = scdConfiger.getSurrogateKeyField();
        if(surrogateKeyField.length()>0 && !scdConfiger.getSurrogateKeyValueType().equals("AUTO_INCREMENT") && !scdConfiger.getSurrogateKeyValueType().equals("DB_SEQUENCE")) {
            String surrogateKeyFieldType = scdConfiger.getSurrogateKeyFieldType();
            insertionColumnList.add(getColumn(surrogateKeyField, surrogateKeyFieldType));
        }
        List<Column> sourceKeys = scdConfiger.getSourceKeys();
        boolean isEqualWithSK = isEqualWithSK(surrogateKeyField, sourceKeys);
        if(isEqualWithSK) {
            for(Column column : sourceKeys) {
                if(!column.getColumnName().equals(surrogateKeyField)) {
                    insertionColumnList.add(column);
                }
            }
        } else {
            for(Column column : sourceKeys) {
                insertionColumnList.add(column);
            }
        }
        if(scdConfiger.isEnableType0()) {
            List<Column> dimensionAttributes = scdConfiger.getDimensionType0Configer().getDimensionAttributes();
            for(Column column : dimensionAttributes) {
                insertionColumnList.add(column);
            }
        }
        if(scdConfiger.isEnableType1()) {
            List<Column> dimensionAttributes = scdConfiger.getDimensionType1Configer().getDimensionAttributes();
            for(Column column : dimensionAttributes) {
                insertionColumnList.add(column);
            }
        }
        if(scdConfiger.isEnableType2()) {
            List<Column> dimensionAttributes = scdConfiger.getDimensionType2Configer().getDimensionAttributes();
            for(Column column : dimensionAttributes) {
                insertionColumnList.add(column);
            }
            if(scdConfiger.getDimensionType2Configer().isEnableVersion()) {
                String versionField = scdConfiger.getDimensionType2Configer().getVersionField();
                String versionFieldType = scdConfiger.getDimensionType2Configer().getVersionFieldType();
                insertionColumnList.add(getColumn(versionField, versionFieldType));
            }
            String startDateField = scdConfiger.getDimensionType2Configer().getStartDateField();
            insertionColumnList.add(getColumn(startDateField, "java.util.Date"));
            String endDateField = scdConfiger.getDimensionType2Configer().getEndDateField();
            insertionColumnList.add(getColumn(endDateField, "java.util.Date"));            
        }
        if(scdConfiger.isEnableType3()) {
            List<DimensionType3Attribute> dimensionAttributes = scdConfiger.getDimensionType3Configer().getDimensionAttributes();
            for(DimensionType3Attribute dimensionType3Attribute : dimensionAttributes) {
                insertionColumnList.add(dimensionType3Attribute.getCurrentColumn());
            }
        }
        return insertionColumnList;
    }
    public List<Column> getSearchingColumnList(SCDConfiger scdConfiger) {
        List<Column> columnList = new ArrayList<Column>();
        if(scdConfiger.isEnableType0()) {
            List<Column> type0Attributes = scdConfiger.getDimensionType0Configer().getDimensionAttributes();
            for(Column type0Attribute : type0Attributes) {
                columnList.add(type0Attribute);
            }
        }
        if(scdConfiger.isEnableType1()) {
            List<Column> type1Attributes = scdConfiger.getDimensionType1Configer().getDimensionAttributes();
            for(Column type1Attribute : type1Attributes) {
                columnList.add(type1Attribute);
            }
        }
        if(scdConfiger.isEnableType2()) {
            List<Column> type2Attributes = scdConfiger.getDimensionType2Configer().getDimensionAttributes();
            for(Column type2Attribute : type2Attributes) {
                columnList.add(type2Attribute);
            }
        }
        if(scdConfiger.isEnableType3()) {
            List<DimensionType3Attribute> type3Attributes = scdConfiger.getDimensionType3Configer().getDimensionAttributes();
            for(DimensionType3Attribute type3Attribute : type3Attributes) {
                columnList.add(type3Attribute.getCurrentColumn());
                columnList.add(type3Attribute.getPrevColumn());
            }
        }
        if(scdConfiger.isEnableType2() && scdConfiger.getDimensionType2Configer().isEnableVersion()) {
            String versionField = scdConfiger.getDimensionType2Configer().getVersionField();
            String versionFieldType = scdConfiger.getDimensionType2Configer().getVersionFieldType();
            columnList.add(getColumn(versionField, versionFieldType));
        }        
        return columnList;
    }
    public List<Column> getSearchColumnList(SCDConfiger scdConfiger) {
        List<Column> columnList = new ArrayList<Column>();
        List<Column> sourceKeys = scdConfiger.getSourceKeys();
        for(Column sourceKey : sourceKeys) {
            columnList.add(sourceKey);
        }
        if(scdConfiger.isEnableType0()) {
            List<Column> type0Attributes = scdConfiger.getDimensionType0Configer().getDimensionAttributes();
            for(Column type0Attribute : type0Attributes) {
                columnList.add(type0Attribute);
            }
        }
        if(scdConfiger.isEnableType1()) {
            List<Column> type1Attributes = scdConfiger.getDimensionType1Configer().getDimensionAttributes();
            for(Column type1Attribute : type1Attributes) {
                columnList.add(type1Attribute);
            }
        }
        if(scdConfiger.isEnableType2()) {
            List<Column> type2Attributes = scdConfiger.getDimensionType2Configer().getDimensionAttributes();
            for(Column type2Attribute : type2Attributes) {
                columnList.add(type2Attribute);
            }
        }
        if(scdConfiger.isEnableType3()) {
            List<DimensionType3Attribute> type3Attributes = scdConfiger.getDimensionType3Configer().getDimensionAttributes();
            for(DimensionType3Attribute type3Attribute : type3Attributes) {
                columnList.add(type3Attribute.getCurrentColumn());
                columnList.add(type3Attribute.getPrevColumn());
            }
        }
        if(scdConfiger.isEnableType2() && scdConfiger.getDimensionType2Configer().isEnableVersion()) {
            String versionField = scdConfiger.getDimensionType2Configer().getVersionField();
            String versionFieldType = scdConfiger.getDimensionType2Configer().getVersionFieldType();
            columnList.add(getColumn(versionField, versionFieldType));
        }
        return columnList;
    }
    public abstract class SCDManager {
        protected String cid;
        public SCDManager(String cid) {
            this.cid = cid;
        }
        protected abstract String getDBMSId();
        protected abstract String getLProtectedChar();
        protected abstract String getRProtectedChar();
        protected abstract String getSequence(String surrogateKeySequence);
        protected abstract String getIncrement();
        protected abstract String getDate(String endDateValue);
        protected abstract String getYear(String endDateField);
        protected abstract String getActiveFieldTrueValue(JavaType activeFieldType);
        protected abstract String getActiveFieldFalseValue(JavaType activeFieldType);        
        public String createSearchingSQL(SCDConfiger scdConfiger) {
         	return createSearchingSQL(scdConfiger ,true);
        }
        public String createSearchingSQL(SCDConfiger scdConfiger ,boolean isSourceKeyEnableNull) {
            StringBuilder searchingSQL = new StringBuilder();
            boolean firstColumn = true;
            boolean firstKeyColumn = true;
            String separator = ", ";
            String keySeparator = " AND ";
            searchingSQL.append("SELECT ");
            List<Column> sourceKeys = scdConfiger.getSourceKeys();
            for(Column sourceKey : sourceKeys) {
                if(firstColumn) {
                    firstColumn = false;
                } else {
                    searchingSQL.append(separator);
                }
                searchingSQL.append(getLProtectedChar() + sourceKey.getColumnName() + getRProtectedChar());
            }
            if(scdConfiger.isEnableType0()) {
                List<Column> type0Attributes = scdConfiger.getDimensionType0Configer().getDimensionAttributes();
                for(Column type0Attribute : type0Attributes) {
                    if(firstColumn) {
                        firstColumn = false;
                    } else {
                        searchingSQL.append(separator);
                    }
                    searchingSQL.append(getLProtectedChar() + type0Attribute.getColumnName() + getRProtectedChar());
                }
            }
            if(scdConfiger.isEnableType1()) {
                List<Column> type1Attributes = scdConfiger.getDimensionType1Configer().getDimensionAttributes();
                for(Column type1Attribute : type1Attributes) {
                    if(firstColumn) {
                        firstColumn = false;
                    } else {
                        searchingSQL.append(separator);
                    }
                    searchingSQL.append(getLProtectedChar() + type1Attribute.getColumnName() + getRProtectedChar());
                }
            }
            if(scdConfiger.isEnableType2()) {
                List<Column> type2Attributes = scdConfiger.getDimensionType2Configer().getDimensionAttributes();
                for(Column type2Attribute : type2Attributes) {
                    if(firstColumn) {
                        firstColumn = false;
                    } else {
                        searchingSQL.append(separator);
                    }
                    searchingSQL.append(getLProtectedChar() + type2Attribute.getColumnName() + getRProtectedChar());
                }
            }
            if(scdConfiger.isEnableType3()) {
                List<DimensionType3Attribute> type3Attributes = scdConfiger.getDimensionType3Configer().getDimensionAttributes();
                for(DimensionType3Attribute type3Attribute : type3Attributes) {
                    if(firstColumn) {
                        firstColumn = false;
                    } else {
                        searchingSQL.append(separator);
                    }
                    searchingSQL.append(getLProtectedChar() + type3Attribute.getCurrentColumn().getColumnName() + getRProtectedChar());
                    searchingSQL.append(", " + getLProtectedChar() + type3Attribute.getPrevColumn().getColumnName() + getRProtectedChar());                    
                }
            }
            if(scdConfiger.isEnableType2() && scdConfiger.getDimensionType2Configer().isEnableVersion()) {
                String versionField = scdConfiger.getDimensionType2Configer().getVersionField();
                searchingSQL.append(separator + getLProtectedChar() + versionField + getRProtectedChar());                
            }
            searchingSQL.append(" FROM " + getLProtectedChar() + "\" + tableName_" + cid + " + \"" + getRProtectedChar() + " WHERE ");
            for(Column sourceKey : sourceKeys) {
                if(firstKeyColumn) {
                    firstKeyColumn = false;
                } else {
                    searchingSQL.append(keySeparator);
                }
                if(isSourceKeyEnableNull){
                	searchingSQL.append(" ("+getLProtectedChar() + sourceKey.getColumnName() + getRProtectedChar() + " = ? OR ((? IS NULL) AND ("+getLProtectedChar() + sourceKey.getColumnName() + getRProtectedChar() + " IS NULL ))) ");
                }else{
                	searchingSQL.append(getLProtectedChar() + sourceKey.getColumnName() + getRProtectedChar() + " = ?");
                }
            }
            if(scdConfiger.isEnableType2()) {
                searchingSQL.append(keySeparator);
                String endDateField = scdConfiger.getDimensionType2Configer().getEndDateField();
                if(scdConfiger.getDimensionType2Configer().getEndDateValueType().equals("NULL_VALUE")) {
                    searchingSQL.append(getLProtectedChar() + endDateField + getRProtectedChar() + " IS NULL");
                } else {
                    String endDateValue = scdConfiger.getDimensionType2Configer().getEndDateValue();
                    searchingSQL.append(getYear(endDateField) + " = \" + " + endDateValue + " + \"");
                }                
            }
            return searchingSQL.toString();
        }
        public String createSelectionSQL(SCDConfiger scdConfiger) {
            StringBuilder selectionSQL = new StringBuilder();
            selectionSQL.append("SELECT ");
            List<Column> sourceKeys = scdConfiger.getSourceKeys();
            String separator = ", ";
            boolean firstColumn = true;
            for(Column sourceKey : sourceKeys) {
                if(firstColumn) {
                    firstColumn = false;
                } else {
                    selectionSQL.append(separator);
                }
                selectionSQL.append(getLProtectedChar() + sourceKey.getColumnName() + getRProtectedChar());
            }
            if(scdConfiger.isEnableType0()) {
                List<Column> type0Attributes = scdConfiger.getDimensionType0Configer().getDimensionAttributes();
                for(Column type0Attribute : type0Attributes) {
                    if(firstColumn) {
                        firstColumn = false;
                    } else {
                        selectionSQL.append(separator);
                    }
                    selectionSQL.append(getLProtectedChar() + type0Attribute.getColumnName() + getRProtectedChar());
                }
            }
            if(scdConfiger.isEnableType1()) {
                List<Column> type1Attributes = scdConfiger.getDimensionType1Configer().getDimensionAttributes();
                for(Column type1Attribute : type1Attributes) {
                    if(firstColumn) {
                        firstColumn = false;
                    } else {
                        selectionSQL.append(separator);
                    }
                    selectionSQL.append(getLProtectedChar() + type1Attribute.getColumnName() + getRProtectedChar());
                }
            }
            if(scdConfiger.isEnableType2()) {
                List<Column> type2Attributes = scdConfiger.getDimensionType2Configer().getDimensionAttributes();
                for(Column type2Attribute : type2Attributes) {
                    if(firstColumn) {
                        firstColumn = false;
                    } else {
                        selectionSQL.append(separator);
                    }
                    selectionSQL.append(getLProtectedChar() + type2Attribute.getColumnName() + getRProtectedChar());                
                }
            }
            if(scdConfiger.isEnableType3()) {
                List<DimensionType3Attribute> type3Attributes = scdConfiger.getDimensionType3Configer().getDimensionAttributes();
                for(DimensionType3Attribute type3Attribute : type3Attributes) {
                    if(firstColumn) {
                        firstColumn = false;
                    } else {
                        selectionSQL.append(separator);
                    }
                    selectionSQL.append(getLProtectedChar() + type3Attribute.getCurrentColumn().getColumnName() + getRProtectedChar());
                    selectionSQL.append(", " + getLProtectedChar() + type3Attribute.getPrevColumn().getColumnName() + getRProtectedChar());
                }
            }
            if(scdConfiger.isEnableType2() && scdConfiger.getDimensionType2Configer().isEnableVersion()) {
                String versionField = scdConfiger.getDimensionType2Configer().getVersionField();
                selectionSQL.append(separator + getLProtectedChar() + versionField + getRProtectedChar());
            }
            selectionSQL.append(" FROM " + getLProtectedChar() + "\" + tableName_" + cid + " + \"" + getRProtectedChar());
            if(scdConfiger.isEnableType2()) {
                String endDateField = scdConfiger.getDimensionType2Configer().getEndDateField();
                if(scdConfiger.getDimensionType2Configer().getEndDateValueType().equals("NULL_VALUE")) {
                    selectionSQL.append(" WHERE " + getLProtectedChar() + endDateField + getRProtectedChar() + " IS NULL");
                } else {
                    String endDateValue = scdConfiger.getDimensionType2Configer().getEndDateValue();
                    selectionSQL.append(" WHERE " + getYear(endDateField) + " = \" + " + endDateValue + " + \"");
                }
            }
            return selectionSQL.toString();
        }
        protected String getDBSequenceWithSchemaPrefix (SCDConfiger scdConfiger) {
        	return "\" + ((schema_" + cid +"!= null && schema_" +cid+".trim().length()!=0)?schema_"+cid+" +\".\":\"\")" +     
            getSequence(scdConfiger.getSurrogateKeySequence());
        }
        public String createInsertionSQL(SCDConfiger scdConfiger) {
            StringBuilder insertionSQL = new StringBuilder();
            insertionSQL.append("INSERT INTO "  + getLProtectedChar() + "\" + tableName_" + cid + " + \"" + getRProtectedChar());
            String insertColumns = "";
            String insertValues = "";
            String separator = ", ";
            String surrogateKeyField = scdConfiger.getSurrogateKeyField();
            List<Column> sourceKeys = scdConfiger.getSourceKeys();
            boolean isEqualWithSK = isEqualWithSK(surrogateKeyField, sourceKeys);
            if(!scdConfiger.getSurrogateKeyValueType().equals("AUTO_INCREMENT") && !scdConfiger.getSurrogateKeyValueType().equals("DB_SEQUENCE")) {
                insertColumns = getLProtectedChar() + surrogateKeyField + getRProtectedChar();
                insertValues = "?";
            }
            if(surrogateKeyField.length()>0 && scdConfiger.getSurrogateKeyValueType().equals("DB_SEQUENCE")) {
                insertColumns = getLProtectedChar() + surrogateKeyField + getRProtectedChar();
                insertValues = getDBSequenceWithSchemaPrefix(scdConfiger);
            }
            boolean isAbsenceSK="".equals(insertColumns);
            int removedIndex = separator.length();
            if(insertColumns.equals(getLProtectedChar()+getRProtectedChar())){
            	isAbsenceSK = true;
            	removedIndex = separator.length()+ getLProtectedChar().length()+getRProtectedChar().length();
            }
            if(isEqualWithSK) {
                for(Column column : sourceKeys) {
                    if(!column.getColumnName().equals(surrogateKeyField)) {
                        insertColumns += separator + getLProtectedChar() + column.getColumnName() + getRProtectedChar();
                        insertValues += separator + "?";
                    }
                }
            } else {
                for(Column column : sourceKeys) {
                    insertColumns += separator + getLProtectedChar() + column.getColumnName() + getRProtectedChar();
                    insertValues += separator + "?";
                }
            }
            if(scdConfiger.isEnableType0()) {
                List<Column> dimensionAttributes = scdConfiger.getDimensionType0Configer().getDimensionAttributes();
                for(Column column : dimensionAttributes) {
                    insertColumns += separator + getLProtectedChar() + column.getColumnName() + getRProtectedChar();
                    insertValues += separator + "?";
                }
            }
            if(scdConfiger.isEnableType1()) {
                List<Column> dimensionAttributes = scdConfiger.getDimensionType1Configer().getDimensionAttributes();
                for(Column column : dimensionAttributes) {
                    insertColumns += separator + getLProtectedChar() + column.getColumnName() + getRProtectedChar();
                    insertValues += separator + "?";
                }
            }
            if(scdConfiger.isEnableType2()) {
                List<Column> dimensionAttributes = scdConfiger.getDimensionType2Configer().getDimensionAttributes();
                for(Column column : dimensionAttributes) {
                    insertColumns += separator + getLProtectedChar() + column.getColumnName() + getRProtectedChar();
                    insertValues += separator + "?";
                }
                if(scdConfiger.getDimensionType2Configer().isEnableActive()) {
                    insertColumns += separator + getLProtectedChar() + scdConfiger.getDimensionType2Configer().getActiveField() + getRProtectedChar();
                    insertValues += separator;
                    JavaType activeFieldType = scdConfiger.getDimensionType2Configer().getActiveFieldType();
                    insertValues += getActiveFieldTrueValue(activeFieldType);                    
                }
                if(scdConfiger.getDimensionType2Configer().isEnableVersion()) {
                    insertColumns += separator + getLProtectedChar() + scdConfiger.getDimensionType2Configer().getVersionField() + getRProtectedChar();
                    insertValues += separator + "?";
                }
                insertColumns += separator + getLProtectedChar() + scdConfiger.getDimensionType2Configer().getStartDateField() + getRProtectedChar();
                insertValues += separator + "?";
                insertColumns += separator + getLProtectedChar() + scdConfiger.getDimensionType2Configer().getEndDateField() + getRProtectedChar();
                insertValues += separator + "?";
            }
            if(scdConfiger.isEnableType3()) {
                List<DimensionType3Attribute> dimensionAttributes = scdConfiger.getDimensionType3Configer().getDimensionAttributes();
                for(DimensionType3Attribute dimensionType3Attribute : dimensionAttributes) {
                    insertColumns += separator + getLProtectedChar() + dimensionType3Attribute.getCurrentColumn().getColumnName() + getRProtectedChar();
                    insertValues += separator + "?";
                }
            }
            if(isAbsenceSK || scdConfiger.getSurrogateKeyValueType().equals("AUTO_INCREMENT")) {
                if(insertColumns.length() > removedIndex) {
                    insertColumns = insertColumns.substring(removedIndex);
                }
                if(insertValues.length() > separator.length()) {
                    insertValues = insertValues.substring(separator.length());
                }
            }
            insertionSQL.append("(" + insertColumns + ") VALUES(" + insertValues + ")");
            return insertionSQL.toString();
        }
        public String createUpdateSQLForType1(SCDConfiger scdConfiger) {
         	return createUpdateSQLForType1(scdConfiger ,true);
        }
        public String createUpdateSQLForType1(SCDConfiger scdConfiger ,boolean isSourceKeyEnableNull) {
            StringBuilder updateSQL = new StringBuilder();
            if(scdConfiger.isEnableType1()) {
                updateSQL.append("UPDATE " + getLProtectedChar() + "\" + tableName_" + cid + " + \"" + getRProtectedChar());
                String updateSetStmt = "";
                String updateWhereStmt = "";
                String separator = ", ";
                String keySeparator = " AND ";
                boolean firstKeyColumn = true;
                boolean firstColumn = true;
                List<Column> sourceKeys = scdConfiger.getSourceKeys();
                for(Column keyColumn : sourceKeys) {
                    if(firstKeyColumn) {
                        firstKeyColumn = false;
                    } else {
                        updateWhereStmt += keySeparator;
                    }
                    if(isSourceKeyEnableNull){
                    	updateWhereStmt += " ("+getLProtectedChar() + keyColumn.getColumnName() + getRProtectedChar() + " = ? OR ((? IS NULL) AND ("+getLProtectedChar() + keyColumn.getColumnName() + getRProtectedChar() + " IS NULL ))) ";
                    }else{
                    	updateWhereStmt += getLProtectedChar() + keyColumn.getColumnName() + getRProtectedChar() + " = ?";
                    }
                }
                if(scdConfiger.isEnableType2() && scdConfiger.getDimensionType1Configer().isOnlyUpdateActive()) {
                    String endDateField = scdConfiger.getDimensionType2Configer().getEndDateField();
                    if(scdConfiger.getDimensionType2Configer().getEndDateValueType().equals("NULL_VALUE")) {
                        updateWhereStmt += keySeparator + getLProtectedChar() + endDateField + getRProtectedChar() + " IS NULL";
                    } else {
                        String endDateValue = scdConfiger.getDimensionType2Configer().getEndDateValue();
                        updateWhereStmt += keySeparator + getYear(endDateField) + " = \" + " + endDateValue + " + \"";
                    }
                }
                List<Column> dimensionAttributes = scdConfiger.getDimensionType1Configer().getDimensionAttributes();
                for(Column dimensionAttribute : dimensionAttributes) {
                    if(firstColumn) {
                        firstColumn = false;
                    } else {
                        updateSetStmt += separator;
                    }
                    updateSetStmt += getLProtectedChar() + dimensionAttribute.getColumnName() + getRProtectedChar() + " = ?";
                }
                updateSQL.append(" SET " + updateSetStmt + " WHERE " + updateWhereStmt);
            }
            return updateSQL.toString();
        }
        public String createUpdateSQLForType2(SCDConfiger scdConfiger) {
         	return createUpdateSQLForType2(scdConfiger ,true);
        }
        public String createUpdateSQLForType2(SCDConfiger scdConfiger ,boolean isSourceKeyEnableNull) {
            StringBuilder updateSQL = new StringBuilder();
            if(scdConfiger.isEnableType2()) {
                updateSQL.append("UPDATE " + getLProtectedChar() + "\" + tableName_" + cid + " + \"" + getRProtectedChar());
                String updateSetStmt = "";
                String updateWhereStmt = "";
                String separator = ", ";
                String keySeparator = " AND ";
                boolean firstKeyColumn = true;
                boolean firstColumn = true;
                List<Column> sourceKeys = scdConfiger.getSourceKeys();
                for(Column keyColumn : sourceKeys) {
                    if(firstKeyColumn) {
                        firstKeyColumn = false;
                    } else {
                        updateWhereStmt += keySeparator;
                    }
                    if(isSourceKeyEnableNull){
                    	updateWhereStmt += "("+getLProtectedChar() + keyColumn.getColumnName() + getRProtectedChar() + " = ? OR ((? IS NULL) AND ("+getLProtectedChar() + keyColumn.getColumnName() + getRProtectedChar() + " IS NULL ))) ";
                    }else{
                        updateWhereStmt += getLProtectedChar() + keyColumn.getColumnName() + getRProtectedChar() + " = ?";
                    }
                }                
                String endDateField = scdConfiger.getDimensionType2Configer().getEndDateField();
                if(scdConfiger.getDimensionType2Configer().getEndDateValueType().equals("NULL_VALUE")) {
                    updateWhereStmt += keySeparator + getLProtectedChar() + endDateField + getRProtectedChar() + " IS NULL";
                } else {
                    String endDateValue = scdConfiger.getDimensionType2Configer().getEndDateValue();
                    updateWhereStmt += keySeparator + getYear(endDateField) + " = \" + " + endDateValue + " + \"";
                }
                updateSetStmt = getLProtectedChar() + endDateField + getRProtectedChar() + " = ?";
                if(scdConfiger.getDimensionType2Configer().isEnableActive()) {
                    String activeField = scdConfiger.getDimensionType2Configer().getActiveField();
                    JavaType activeFieldType = scdConfiger.getDimensionType2Configer().getActiveFieldType();
                    updateSetStmt += separator + getLProtectedChar() + activeField + getRProtectedChar() + " = " + getActiveFieldFalseValue(activeFieldType);
                }
                updateSQL.append(" SET " + updateSetStmt + " WHERE " + updateWhereStmt);
            }
            return updateSQL.toString();
        }
        public String createUpdateSQLForType3(SCDConfiger scdConfiger) {
         	return createUpdateSQLForType3(scdConfiger ,true);
        }
        public String createUpdateSQLForType3(SCDConfiger scdConfiger ,boolean isSourceKeyEnableNull) {
            StringBuilder updateSQL = new StringBuilder();
            if(scdConfiger.isEnableType3()) {
                updateSQL.append("UPDATE " + getLProtectedChar() + "\" + tableName_" + cid + " + \"" + getRProtectedChar());
                String updateSetStmt = "";
                String updateWhereStmt = "";
                String separator = ", ";
                String keySeparator = " AND ";
                boolean firstKeyColumn = true;
                boolean firstColumn = true;
                List<Column> sourceKeys = scdConfiger.getSourceKeys();
                for(Column keyColumn : sourceKeys) {
                    if(firstKeyColumn) {
                        firstKeyColumn = false;
                    } else {
                        updateWhereStmt += keySeparator;
                    }
                    if(isSourceKeyEnableNull){
                    	updateWhereStmt += "("+getLProtectedChar() + keyColumn.getColumnName() + getRProtectedChar() + " = ? OR ((? IS NULL) AND ("+getLProtectedChar() + keyColumn.getColumnName() + getRProtectedChar() + " IS NULL ))) ";
                    }else{
                    	updateWhereStmt += getLProtectedChar() + keyColumn.getColumnName() + getRProtectedChar() + " = ?";
                    }
                }
                if(scdConfiger.isEnableType2()) {
                    String endDateField = scdConfiger.getDimensionType2Configer().getEndDateField();
                    if(scdConfiger.getDimensionType2Configer().getEndDateValueType().equals("NULL_VALUE")) {
                        updateWhereStmt += keySeparator + getLProtectedChar() + endDateField + getRProtectedChar() + " IS NULL";
                    } else {
                        String endDateValue = scdConfiger.getDimensionType2Configer().getEndDateValue();
                        updateWhereStmt += keySeparator + getYear(endDateField) + " = \" + " + endDateValue + " + \"";
                    }
                }
                List<DimensionType3Attribute> dimensionAttributes = scdConfiger.getDimensionType3Configer().getDimensionAttributes();
                for(DimensionType3Attribute dimensionAttribute : dimensionAttributes) {
                    if(firstColumn) {
                        firstColumn = false;
                    } else {
                        updateSetStmt += separator;
                    }
                    updateSetStmt += getLProtectedChar() + dimensionAttribute.getCurrentColumn().getColumnName() + getRProtectedChar() + " = ?";
                    updateSetStmt += ", " + getLProtectedChar() + dimensionAttribute.getPrevColumn().getColumnName() + getRProtectedChar() + " = ?";
                }
                updateSQL.append(" SET " + updateSetStmt + " WHERE " + updateWhereStmt);
            }
            return updateSQL.toString();
        }
        public String getDeleteTableSQL() {
            StringBuilder deleteTableSQL = new StringBuilder();
            deleteTableSQL.append("DELETE FROM " + getLProtectedChar() + "\" + tableName_" + cid + " + \"" + getRProtectedChar());
            return deleteTableSQL.toString();
        }        
        public String getTruncateTableSQL() {
            StringBuilder truncateTableSQL = new StringBuilder();
            truncateTableSQL.append("TRUNCATE TABLE " + getLProtectedChar() + "\" + tableName_" + cid + " + \"" + getRProtectedChar());
            return truncateTableSQL.toString();
        }
        public String getDropTableSQL() {
            StringBuilder dropTableSQL = new StringBuilder();
            dropTableSQL.append("DROP TABLE " + getLProtectedChar() + "\" + tableName_" + cid + " + \"" + getRProtectedChar());
            return dropTableSQL.toString();
        }        
        public String getCreateTableSQL(List<IMetadataColumn> outputSchema, SCDConfiger scdConfiger) {
            MappingTypeRetriever mappingType = MetadataTalendType.getMappingTypeRetriever(getDBMSId());
            StringBuilder createTableSQL = new StringBuilder();
            createTableSQL.append("CREATE TABLE " + getLProtectedChar() + "\" + tableName_" + cid + " + \"" + getRProtectedChar() + "(");
            List<String> pkList = new ArrayList<String>();
            int count = 0;
            String ending = ",";
            String columnName = null;
            String surrogateKey = scdConfiger.getSurrogateKeyField();
            String surrogateKeyValueType = scdConfiger.getSurrogateKeyValueType();
            for(IMetadataColumn metadataColumn : outputSchema) {
                columnName = metadataColumn.getOriginalDbColumnName();
                if(columnName.equals(surrogateKey) && surrogateKeyValueType.equals("AUTO_INCREMENT")) {
                    pkList.add(getLProtectedChar() + columnName + getRProtectedChar());
                } else {
                    if(metadataColumn.isKey()) {
                        pkList.add(getLProtectedChar() + columnName + getRProtectedChar());
                    }                    
                }
                createTableSQL.append(getLProtectedChar() + columnName + getRProtectedChar() + " ");
                String dataType = null;
                if(!("vertica_id".equals(getDBMSId()) && columnName.equals(surrogateKey) && surrogateKeyValueType.equals("AUTO_INCREMENT"))){ 
	                if(metadataColumn.getType() == null || metadataColumn.getType().trim().length() == 0) {
	                    dataType = mappingType.getDefaultSelectedDbType(metadataColumn.getTalendType());
	                }else {
	                    dataType = metadataColumn.getType();
	                }
	                createTableSQL.append(dataType);
	                Integer length = metadataColumn.getLength() == null ? 0 : metadataColumn.getLength();
	                Integer precision = metadataColumn.getPrecision() == null ? 0 : metadataColumn.getPrecision();
	                boolean lengthIgnored = mappingType.isLengthIgnored(getDBMSId(), dataType);
	                boolean precisionIgnored = mappingType.isPrecisionIgnored(getDBMSId(), dataType);
	                String prefix = "";
	                String suffix = "";
	                String comma = "";
	                if(mappingType.isPreBeforeLength(getDBMSId(),dataType)) {
	                    if(!precisionIgnored) {
	                        prefix = "(";
	                        suffix = ") ";
	                        createTableSQL.append(prefix + precision);
	                    }
	                    if(!lengthIgnored) {
	                        prefix = (prefix.equals("") ? "(" : prefix);
	                        suffix = (suffix.equals("") ? ") " : suffix);
	                        if(precisionIgnored) {
	                            createTableSQL.append(prefix);
	                            comma = "";
	                        } else {
	                            comma = ",";
	                        }
	                        createTableSQL.append(comma + length);
	                    }
	                    createTableSQL.append(suffix);
	                } else {
	                    if(!lengthIgnored) {
	                        prefix = "(";
	                        suffix = ") ";
	                        createTableSQL.append(prefix + length);
	                    }
	                    if(!precisionIgnored) {
	                        prefix = (prefix.equals("") ? "(" : prefix);
	                        suffix = (suffix.equals("") ? ") " : suffix);
	                        if(lengthIgnored) {
	                            createTableSQL.append(prefix);
	                            comma = "";                        
	                        } else {
	                            comma = ",";
	                        }
	                        createTableSQL.append(comma + precision);
	                    }
	                    createTableSQL.append(suffix);
	                }
                }
                if(columnName.equals(surrogateKey) && surrogateKeyValueType.equals("AUTO_INCREMENT")) {
                    createTableSQL.append(getIncrement());
                    createTableSQL.append(setNullable(false));
                } else {
                    createTableSQL.append(getDefaultValueSQL(metadataColumn.getDefault()));            
                    createTableSQL.append(setNullable(metadataColumn.isNullable()));                    
                }            
                if(count == outputSchema.size() - 1 && pkList.size() == 0) {
                    ending = "";
                }
                createTableSQL.append(ending);
                count++;
            }
            if(pkList.size() > 0) {                
                createTableSQL.append("primary key(");                
                int i = 0;                
                for(String pk : pkList) {                    
                    createTableSQL.append(pk);                    
                    if(i != pkList.size() - 1) {                        
                        createTableSQL.append(",");                        
                    }                    
                    i++;                    
                }                
                createTableSQL.append(")");                
            }
            createTableSQL.append(")");
            return createTableSQL.toString();            
        }
        protected String setNullable(boolean nullable) {
            if(!nullable) {
                return " not null ";
            } else {
                return "";
            }
        }    
        protected String getDefaultValueSQL(String defaultValue) {
            if(defaultValue == null || defaultValue.equals("\"\"") || defaultValue.equals("")) {
                return " ";
            } else if((defaultValue.startsWith("\"") || defaultValue.startsWith("'")) && (defaultValue.endsWith("\"") || defaultValue.endsWith("'"))) {
                return " default '" + defaultValue.substring(1,defaultValue.length() - 1) + "' ";
            } else if(defaultValue.equalsIgnoreCase("null")) {
                return " default null ";
            } else {
                return " default " + defaultValue + " ";
            }            
        }     
        
        public String genSetStatement(String pstmt, String variable, int paramIndex, String generatedType) {
            return genSetStatement(pstmt, variable, paramIndex, generatedType, false);
        }
        
        public String genSetStatement(String pstmt, String variable, int paramIndex, String generatedType, boolean isSourceKeyEnableNull) {
            return genSetStatement(pstmt, variable, paramIndex, generatedType , isSourceKeyEnableNull, null);
        }
        
        public String genSetStatement(String pstmt, String variable, int paramIndex, String generatedType, String dbType) {
            return genSetStatement(pstmt, variable, paramIndex, generatedType, false, dbType);
        }
        
        public String genSetStatement(String pstmt, String variable, int paramIndex, String generatedType ,boolean isSourceKeyEnableNull, String dbType) {
            StringBuilder setStatement = new StringBuilder();
            boolean isObject = false;
            if(generatedType.equals("Character")) {
                isObject = true;
                if(!variable.equals("null")) {
                    setStatement.append("if(" + variable + " == null) {\r\n");
                }
                setStatement.append(pstmt + ".setNull(" + paramIndex + ", java.sql.Types.CHAR);\r\n");
                if(isSourceKeyEnableNull){
                    setStatement.append(pstmt + ".setNull(" + (paramIndex+1) + ", java.sql.Types.CHAR);\r\n");
                }
            } else if(generatedType.equals("java.util.Date")) {
                isObject = true;
                if(!variable.equals("null")) {
                    setStatement.append("if(" + variable + " == null) {\r\n");
                }
                setStatement.append(pstmt + ".setNull(" + paramIndex + ", java.sql.Types.DATE);\r\n");
                if(isSourceKeyEnableNull){
                    setStatement.append(pstmt + ".setNull(" + (paramIndex+1) + ", java.sql.Types.DATE);\r\n");
                }
            } else if(generatedType.equals("byte[]")) {
                isObject = true;
                if(!variable.equals("null")) {
                    setStatement.append("if(" + variable + " == null) {\r\n");
                }
                setStatement.append(pstmt + ".setNull(" + paramIndex + ", java.sql.Types.ARRAY);\r\n");
                if(isSourceKeyEnableNull){
                    setStatement.append(pstmt + ".setNull(" + (paramIndex+1) + ", java.sql.Types.ARRAY);\r\n");
                }
            } else if(generatedType.equals("Long") || generatedType.equals("Byte") || generatedType.equals("Integer") || generatedType.equals("Short")) {
                isObject = true;
                if(!variable.equals("null")) {
                    setStatement.append("if(" + variable + " == null) {\r\n");
                }
                setStatement.append(pstmt + ".setNull(" + paramIndex + ", java.sql.Types.INTEGER);\r\n");
                if(isSourceKeyEnableNull){
                    setStatement.append(pstmt + ".setNull(" + (paramIndex+1) + ", java.sql.Types.INTEGER);\r\n");
                }
            } else if(generatedType.equals("String")) {
                isObject = true;
                if(!variable.equals("null")) {
                    setStatement.append("if(" + variable + " == null) {\r\n");
                }
                setStatement.append(pstmt + ".setNull(" + paramIndex + ", java.sql.Types.VARCHAR);\r\n");
                if(isSourceKeyEnableNull){
                    setStatement.append(pstmt + ".setNull(" + (paramIndex+1) + ", java.sql.Types.VARCHAR);\r\n");
                }
            } else if(generatedType.equals("Object")) {
                isObject = true;
                if(!variable.equals("null")) {
                    setStatement.append("if(" + variable + " == null) {\r\n");
                }
                setStatement.append(pstmt + ".setNull(" + paramIndex + ", java.sql.Types.OTHER);\r\n");
                if(isSourceKeyEnableNull){
                    setStatement.append(pstmt + ".setNull(" + (paramIndex+1) + ", java.sql.Types.OTHER);\r\n");
                }
            } else if(generatedType.equals("Boolean")) {
                isObject = true;
                if(!variable.equals("null")) {
                    setStatement.append("if(" + variable + " == null) {\r\n");
                }
                setStatement.append(pstmt + ".setNull(" + paramIndex + ", java.sql.Types.BOOLEAN);\r\n");
                if(isSourceKeyEnableNull){
                    setStatement.append(pstmt + ".setNull(" + (paramIndex+1) + ", java.sql.Types.BOOLEAN);\r\n");
                }
            } else if(generatedType.equals("Double")) {
                isObject = true;
                if(!variable.equals("null")) {
                    setStatement.append("if(" + variable + " == null) {\r\n");
                }
                setStatement.append(pstmt + ".setNull(" + paramIndex + ", java.sql.Types.DOUBLE);\r\n");
                if(isSourceKeyEnableNull){
                    setStatement.append(pstmt + ".setNull(" + (paramIndex+1) + ", java.sql.Types.DOUBLE);\r\n");
                }
            } else if(generatedType.equals("Float")) {
                isObject = true;
                if(!variable.equals("null")) {
                    setStatement.append("if(" + variable + " == null) {\r\n");
                }
                setStatement.append(pstmt + ".setNull(" + paramIndex + ", java.sql.Types.FLOAT);\r\n");
                if(isSourceKeyEnableNull){
                    setStatement.append(pstmt + ".setNull(" + (paramIndex+1) + ", java.sql.Types.FLOAT);\r\n");
                }
            }
            if(!variable.equals("null")) {
                if(isObject) {
                    setStatement.append("} else {\r\n");
                }
                if(generatedType.equals("byte[]")) {
                    generatedType = "Bytes";
                } else if(generatedType.equals("java.util.Date")) {
                    generatedType = "Date";
                } else if(generatedType.equals("Integer")) {
                    generatedType = "Int";
                } else {
                    if(generatedType.length() >= 1) {
                        generatedType = generatedType.substring(0, 1).toUpperCase() + generatedType.substring(1);
                    }
                }
                if(generatedType.equals("Char") || generatedType.equals("Character")) {
                    if(isObject) {
                        setStatement.append("if(" + variable + " == null) {\r\n");
                    } else {
                        setStatement.append("if(String.valueOf(" + variable + ").toLowerCase().equals(\"null\")) {\r\n");
                    }
                    setStatement.append(pstmt + ".setNull(" + paramIndex + ", java.sql.Types.CHAR);\r\n");
                    if(isSourceKeyEnableNull){
                        setStatement.append(pstmt + ".setNull(" + (paramIndex+1) + ", java.sql.Types.CHAR);\r\n");
                    }
                    setStatement.append("} else if(" + variable + " == '\\0') {\r\n");
                    setStatement.append(pstmt + ".setString(" + paramIndex + ", \"\");\r\n");
                    if(isSourceKeyEnableNull){
                        setStatement.append(pstmt + ".setString(" + (paramIndex+1) + ", \"\");\r\n");
                    }
                    setStatement.append("} else {\r\n");
                    setStatement.append(pstmt + ".setString(" + paramIndex + ", String.valueOf(" + variable + "));\r\n");
                    if(isSourceKeyEnableNull){
                        setStatement.append(pstmt + ".setString(" + (paramIndex+1) + ", String.valueOf(" + variable + "));\r\n");
                    }
                    setStatement.append("}\r\n");
                } else if(generatedType.equals("Date")) {
                    setStatement.append("if(" + variable + " != null) {\r\n");
                    setStatement.append(pstmt + ".setTimestamp(" + paramIndex + ", new java.sql.Timestamp(" + variable + ".getTime()));\r\n");
                    if(isSourceKeyEnableNull){
                        setStatement.append(pstmt + ".setTimestamp(" + (paramIndex+1) + ", new java.sql.Timestamp(" + variable + ".getTime()));\r\n");
                    }
                    setStatement.append("} else {\r\n");
                    setStatement.append(pstmt + ".setNull(" + paramIndex + ", java.sql.Types.DATE);\r\n");
                    if(isSourceKeyEnableNull){
                        setStatement.append(pstmt + ".setNull(" + (paramIndex+1) + ", java.sql.Types.DATE);\r\n");
                    }
                    setStatement.append("}\r\n");
                } else {
                    setStatement.append(pstmt + ".set" + generatedType + "(" + paramIndex + ", " + variable + ");\r\n");
                    if(isSourceKeyEnableNull){
                        setStatement.append(pstmt + ".set" + generatedType + "(" + (paramIndex+1) + ", " + variable + ");\r\n");
                    }
                }
                if(isObject) {
                    setStatement.append("}\r\n");
                }
            }
            return setStatement.toString();
        }

    }
    public class DB2SCDManager extends SCDManager {
        public DB2SCDManager(String cid) {
            super(cid);
        }
        protected String getDBMSId() {
            return "ibmdb2_id";
        }
        protected String getLProtectedChar() {
            return "\\\"";
        }
        protected String getRProtectedChar() {
            return "\\\"";
        }
        protected String getSequence(String surrogateKeySequence) {
            return "+ \" nextval for \"+((schema_" + cid +"!= null && schema_" +cid+".trim().length()!=0)?schema_"+cid+" +\".\":\"\") +\"" + surrogateKeySequence + "\" + \"";
        }
        protected String getIncrement() {
            return "";
        }
        protected String getDate(String endDateValue) {
            return "'\" + " + endDateValue + " + \"-01-01'";
        }
        protected String getYear(String endDateField) {
            return "YEAR(" + getLProtectedChar() + endDateField + getRProtectedChar() + ")";
        }
        protected String getActiveFieldTrueValue(JavaType activeFieldType) {
            if(activeFieldType == JavaTypesManager.STRING) {
                return "'true'";
            } else if(activeFieldType == JavaTypesManager.BOOLEAN) {
                return "1";
            } else {
                return "1";
            }            
        }
        protected String getActiveFieldFalseValue(JavaType activeFieldType) {
            if(activeFieldType == JavaTypesManager.STRING) {
                return "'false'";
            } else if(activeFieldType == JavaTypesManager.BOOLEAN) {
                return "0";
            } else {
                return "0";
            }            
        }
        protected String getDBSequenceWithSchemaPrefix (SCDConfiger scdConfiger) {
        	return "\" " +getSequence(scdConfiger.getSurrogateKeySequence());
        }
    }
    public class IngresSCDManager extends SCDManager {
        public IngresSCDManager(String cid) {
            super(cid);
        }
        protected String getDBMSId() {
            return "ingres_id";
        }        
        protected String getLProtectedChar() {
            return "\\\"";
        }
        protected String getRProtectedChar() {
            return "\\\"";
        }
        protected String getSequence(String surrogateKeySequence) {
        	return "+ \"" + surrogateKeySequence + "\" + \".nextval";
        }
        protected String getIncrement() {
            return "";
        }        
        protected String getDate(String endDateValue) {
            return "'\" + " + endDateValue + " + \"-01-01'";
        }        
        protected String getYear(String endDateField) {
            return "DATE_PART('year', " + getLProtectedChar() + endDateField + getRProtectedChar() + ")";
        }
        protected String getActiveFieldTrueValue(JavaType activeFieldType) {
            if(activeFieldType == JavaTypesManager.STRING) {
                return "'true'";
            } else if(activeFieldType == JavaTypesManager.BOOLEAN) {
                return "1";
            } else {
                return "1";
            }            
        }
        protected String getActiveFieldFalseValue(JavaType activeFieldType) {
            if(activeFieldType == JavaTypesManager.STRING) {
                return "'false'";
            } else if(activeFieldType == JavaTypesManager.BOOLEAN) {
                return "0";
            } else {
                return "0";
            }            
        }        
    }
    public class MSSQLSCDManager extends SCDManager {
        public MSSQLSCDManager(String cid) {
            super(cid);
        }
        protected String getDBMSId() {
            return "id_MSSQL";
        }        
        protected String getLProtectedChar() {
            return "[";
        }
        protected String getRProtectedChar() {
            return "]";
        }
        protected String getSequence(String surrogateKeySequence) {
            return "\" + " + surrogateKeySequence + " + \".nextval";
        }
        protected String getIncrement() {
            return "";
        }        
        protected String getDate(String endDateValue) {
            return "'\" + " + endDateValue + " + \"-01-01'";
        }        
        protected String getYear(String endDateField) {
            return "YEAR(" + getLProtectedChar() + endDateField + getRProtectedChar() + ")";
        }
        protected String getActiveFieldTrueValue(JavaType activeFieldType) {
            if(activeFieldType == JavaTypesManager.STRING) {
                return "'true'";
            } else if(activeFieldType == JavaTypesManager.BOOLEAN) {
                return "1";
            } else {
                return "1";
            }            
        }
        protected String getActiveFieldFalseValue(JavaType activeFieldType) {
            if(activeFieldType == JavaTypesManager.STRING) {
                return "'false'";
            } else if(activeFieldType == JavaTypesManager.BOOLEAN) {
                return "0";
            } else {
                return "0";
            }            
        }        
    }
    
    public class TeradataSCDManager extends SCDManager {
        public TeradataSCDManager(String cid) {
            super(cid);
        }
        protected String getDBMSId() {
            return "teradata_id";
        }        
        protected String getLProtectedChar() {
            return "\\\"";
        }
        protected String getRProtectedChar() {
            return "\\\"";
        }
        protected String getSequence(String surrogateKeySequence) {
            return "";
        }
        protected String getIncrement() {
            return " GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1 MINVALUE 1 MAXVALUE 999999999 NO CYCLE) ";
        }        
        protected String getDate(String endDateValue) {
            return "'\" + " + endDateValue + " + \"-01-01'";
        }        
        protected String getYear(String endDateField) {
            return "EXTRACT(YEAR FROM " + getLProtectedChar() + endDateField + getRProtectedChar() + ")";
        }
        protected String getActiveFieldTrueValue(JavaType activeFieldType) {
            if(activeFieldType == JavaTypesManager.STRING) {
                return "'true'";
            } else if(activeFieldType == JavaTypesManager.BOOLEAN) {
                return "'1'";
            } else {
                return "1";
            }            
        }
        protected String getActiveFieldFalseValue(JavaType activeFieldType) {
            if(activeFieldType == JavaTypesManager.STRING) {
                return "'false'";
            } else if(activeFieldType == JavaTypesManager.BOOLEAN) {
                return "'0'";
            } else {
                return "0";
            }            
        }        
       
        public String genSetStatement(String pstmt, String variable, int paramIndex, String generatedType ,boolean isSourceKeyEnableNull) {

            StringBuilder setStatement = new StringBuilder();
            boolean isObject = false;
            if(generatedType.equals("Character")) {
                isObject = true;
                if(!variable.equals("null")) {
                    setStatement.append("if(" + variable + " == null) {\r\n");
                }
                setStatement.append(pstmt + ".setNull(" + paramIndex + ", java.sql.Types.CHAR);\r\n");
                if(isSourceKeyEnableNull){
                    setStatement.append(pstmt + ".setNull(" + (paramIndex+1) + ", java.sql.Types.CHAR);\r\n");
                }
            } else if(generatedType.equals("java.util.Date")) {
                isObject = true;
                if(!variable.equals("null")) {
                    setStatement.append("if(" + variable + " == null) {\r\n");
                }
                setStatement.append(pstmt + ".setNull(" + paramIndex + ", java.sql.Types.DATE);\r\n");
                if(isSourceKeyEnableNull){
                    setStatement.append(pstmt + ".setNull(" + (paramIndex+1) + ", java.sql.Types.DATE);\r\n");
                }
            } else if(generatedType.equals("byte[]")) {
                isObject = true;
                if(!variable.equals("null")) {
                    setStatement.append("if(" + variable + " == null) {\r\n");
                }
                setStatement.append(pstmt + ".setNull(" + paramIndex + ", java.sql.Types.ARRAY);\r\n");
                if(isSourceKeyEnableNull){
                    setStatement.append(pstmt + ".setNull(" + (paramIndex+1) + ", java.sql.Types.ARRAY);\r\n");
                }
            } else if(generatedType.equals("Long") || generatedType.equals("Byte") || generatedType.equals("Integer") || generatedType.equals("Short")) {
                isObject = true;
                if(!variable.equals("null")) {
                    setStatement.append("if(" + variable + " == null) {\r\n");
                }
                setStatement.append(pstmt + ".setNull(" + paramIndex + ", java.sql.Types.INTEGER);\r\n");
                if(isSourceKeyEnableNull){
                    setStatement.append(pstmt + ".setNull(" + (paramIndex+1) + ", java.sql.Types.INTEGER);\r\n");
                }
            } else if(generatedType.equals("String")) {
                isObject = true;
                if(!variable.equals("null")) {
                    setStatement.append("if(" + variable + " == null) {\r\n");
                }
                setStatement.append(pstmt + ".setNull(" + paramIndex + ", java.sql.Types.VARCHAR);\r\n");
                if(isSourceKeyEnableNull){
                    setStatement.append(pstmt + ".setNull(" + (paramIndex+1) + ", java.sql.Types.VARCHAR);\r\n");
                }
            } else if(generatedType.equals("Object")) {
                isObject = true;
                if(!variable.equals("null")) {
                    setStatement.append("if(" + variable + " == null) {\r\n");
                }
                setStatement.append(pstmt + ".setNull(" + paramIndex + ", java.sql.Types.OTHER);\r\n");
                if(isSourceKeyEnableNull){
                    setStatement.append(pstmt + ".setNull(" + (paramIndex+1) + ", java.sql.Types.OTHER);\r\n");
                }
            } else if(generatedType.equals("Boolean")) {
                isObject = true;
                if(!variable.equals("null")) {
                    setStatement.append("if(" + variable + " == null) {\r\n");
                }
                setStatement.append(pstmt + ".setNull(" + paramIndex + ", java.sql.Types.BOOLEAN);\r\n");
                if(isSourceKeyEnableNull){
                    setStatement.append(pstmt + ".setNull(" + (paramIndex+1) + ", java.sql.Types.BOOLEAN);\r\n");
                }
            } else if(generatedType.equals("Double")) {
                isObject = true;
                if(!variable.equals("null")) {
                    setStatement.append("if(" + variable + " == null) {\r\n");
                }
                setStatement.append(pstmt + ".setNull(" + paramIndex + ", java.sql.Types.DOUBLE);\r\n");
                if(isSourceKeyEnableNull){
                    setStatement.append(pstmt + ".setNull(" + (paramIndex+1) + ", java.sql.Types.DOUBLE);\r\n");
                }
            } else if(generatedType.equals("Float")) {
                isObject = true;
                if(!variable.equals("null")) {
                    setStatement.append("if(" + variable + " == null) {\r\n");
                }
                setStatement.append(pstmt + ".setNull(" + paramIndex + ", java.sql.Types.FLOAT);\r\n");
                if(isSourceKeyEnableNull){
                    setStatement.append(pstmt + ".setNull(" + (paramIndex+1) + ", java.sql.Types.FLOAT);\r\n");
                }
            }
            if(!variable.equals("null")) {
                if(isObject) {
                    setStatement.append("} else {\r\n");
                }
                if(generatedType.equals("byte[]")) {
                    generatedType = "Bytes";
                } else if(generatedType.equals("java.util.Date")) {
                    generatedType = "Date";
                } else if(generatedType.equals("Integer")) {
                    generatedType = "Int";
                } else {
                    if(generatedType.length() >= 1) {
                        generatedType = generatedType.substring(0, 1).toUpperCase() + generatedType.substring(1);
                    }
                }
                if(generatedType.equals("Char") || generatedType.equals("Character")) {
                    if(isObject) {
                        setStatement.append("if(" + variable + " == null) {\r\n");
                    } else {
                        setStatement.append("if(String.valueOf(" + variable + ").toLowerCase().equals(\"null\")) {\r\n");
                    }
                    setStatement.append(pstmt + ".setNull(" + paramIndex + ", java.sql.Types.CHAR);\r\n");
                    setStatement.append("} else if(" + variable + " == '\\0') {\r\n");
                    setStatement.append(pstmt + ".setString(" + paramIndex + ", \"\");\r\n");
                    if(isSourceKeyEnableNull){
                        setStatement.append(pstmt + ".setString(" + paramIndex + ", \"\");\r\n");
                    }
                    setStatement.append("} else {\r\n");
                    setStatement.append(pstmt + ".setString(" + paramIndex + ", String.valueOf(" + variable + "));\r\n");
                    if(isSourceKeyEnableNull){
                        setStatement.append(pstmt + ".setString(" + paramIndex + ", String.valueOf(" + variable + "));\r\n");
                    }
                    setStatement.append("}\r\n");
                } else if(generatedType.equals("Date")) {
                    setStatement.append("if(" + variable + " != null) {\r\n");
                    setStatement.append("date_" + cid + " = " + variable + ".getTime();\r\n");
                    setStatement.append("if(date_" + cid + " < year1_" + cid + " || date_" + cid + " >= year10000_" + cid + ") {\r\n");
                    setStatement.append(pstmt + ".setString(" + paramIndex + ", \"0000-00-00 00:00:00\");\r\n");
                    if(isSourceKeyEnableNull){
                        setStatement.append(pstmt + ".setString(" + (paramIndex+1) + ", \"0000-00-00 00:00:00\");\r\n");
                    }
                    setStatement.append("} else {\r\n");
                    setStatement.append(pstmt + ".setTimestamp(" + paramIndex + ", new java.sql.Timestamp(date_" + cid + "));\r\n");
                    if(isSourceKeyEnableNull){
                        setStatement.append(pstmt + ".setTimestamp(" + (paramIndex+1) + ", new java.sql.Timestamp(date_" + cid + "));\r\n");
                    }
                    setStatement.append("}\r\n");
                    setStatement.append("} else {\r\n");
                    setStatement.append(pstmt + ".setNull(" + paramIndex + ", java.sql.Types.DATE);\r\n");
                    if(isSourceKeyEnableNull){
                        setStatement.append(pstmt + ".setNull(" + (paramIndex+1) + ", java.sql.Types.DATE);\r\n");
                    }
                    setStatement.append("}\r\n");
                } else {
                    setStatement.append(pstmt + ".set" + generatedType + "(" + paramIndex + ", " + variable + ");\r\n");
                    if(isSourceKeyEnableNull){
                        setStatement.append(pstmt + ".set" + generatedType + "(" + (paramIndex+1) + ", " + variable + ");\r\n");
                    }
                }
                if(isObject) {
                    setStatement.append("}\r\n");
                }
            }
            return setStatement.toString();
        }
    }
    
    public class MysqlSCDManager extends SCDManager {
        public MysqlSCDManager(String cid) {
            super(cid);
        }
        protected String getDBMSId() {
            return "mysql_id";
        }        
        protected String getLProtectedChar() {
            return "`";
        }
        protected String getRProtectedChar() {
            return "`";
        }
        protected String getSequence(String surrogateKeySequence) {
            return "\" + " + surrogateKeySequence + " + \".nextval";
        }
        protected String getIncrement() {
            return " AUTO_INCREMENT ";
        }        
        protected String getDate(String endDateValue) {
            return "'\" + " + endDateValue + " + \"-01-01'";
        }        
        protected String getYear(String endDateField) {
            return "YEAR(" + getLProtectedChar() + endDateField + getRProtectedChar() + ")";
        }
        protected String getActiveFieldTrueValue(JavaType activeFieldType) {
            if(activeFieldType == JavaTypesManager.STRING) {
                return "'true'";
            } else if(activeFieldType == JavaTypesManager.BOOLEAN) {
                return "b'1'";
            } else {
                return "1";
            }            
        }
        protected String getActiveFieldFalseValue(JavaType activeFieldType) {
            if(activeFieldType == JavaTypesManager.STRING) {
                return "'false'";
            } else if(activeFieldType == JavaTypesManager.BOOLEAN) {
                return "b'0'";
            } else {
                return "0";
            }            
        }        
       
        public String genSetStatement(String pstmt, String variable, int paramIndex, String generatedType ,boolean isSourceKeyEnableNull) {

            StringBuilder setStatement = new StringBuilder();
            boolean isObject = false;
            if(generatedType.equals("Character")) {
                isObject = true;
                if(!variable.equals("null")) {
                    setStatement.append("if(" + variable + " == null) {\r\n");
                }
                setStatement.append(pstmt + ".setNull(" + paramIndex + ", java.sql.Types.CHAR);\r\n");
                if(isSourceKeyEnableNull){
                    setStatement.append(pstmt + ".setNull(" + (paramIndex+1) + ", java.sql.Types.CHAR);\r\n");
                }
            } else if(generatedType.equals("java.util.Date")) {
                isObject = true;
                if(!variable.equals("null")) {
                    setStatement.append("if(" + variable + " == null) {\r\n");
                }
                setStatement.append(pstmt + ".setNull(" + paramIndex + ", java.sql.Types.DATE);\r\n");
                if(isSourceKeyEnableNull){
                    setStatement.append(pstmt + ".setNull(" + (paramIndex+1) + ", java.sql.Types.DATE);\r\n");
                }
            } else if(generatedType.equals("byte[]")) {
                isObject = true;
                if(!variable.equals("null")) {
                    setStatement.append("if(" + variable + " == null) {\r\n");
                }
                setStatement.append(pstmt + ".setNull(" + paramIndex + ", java.sql.Types.ARRAY);\r\n");
                if(isSourceKeyEnableNull){
                    setStatement.append(pstmt + ".setNull(" + (paramIndex+1) + ", java.sql.Types.ARRAY);\r\n");
                }
            } else if(generatedType.equals("Long") || generatedType.equals("Byte") || generatedType.equals("Integer") || generatedType.equals("Short")) {
                isObject = true;
                if(!variable.equals("null")) {
                    setStatement.append("if(" + variable + " == null) {\r\n");
                }
                setStatement.append(pstmt + ".setNull(" + paramIndex + ", java.sql.Types.INTEGER);\r\n");
                if(isSourceKeyEnableNull){
                    setStatement.append(pstmt + ".setNull(" + (paramIndex+1) + ", java.sql.Types.INTEGER);\r\n");
                }
            } else if(generatedType.equals("String")) {
                isObject = true;
                if(!variable.equals("null")) {
                    setStatement.append("if(" + variable + " == null) {\r\n");
                }
                setStatement.append(pstmt + ".setNull(" + paramIndex + ", java.sql.Types.VARCHAR);\r\n");
                if(isSourceKeyEnableNull){
                    setStatement.append(pstmt + ".setNull(" + (paramIndex+1) + ", java.sql.Types.VARCHAR);\r\n");
                }
            } else if(generatedType.equals("Object")) {
                isObject = true;
                if(!variable.equals("null")) {
                    setStatement.append("if(" + variable + " == null) {\r\n");
                }
                setStatement.append(pstmt + ".setNull(" + paramIndex + ", java.sql.Types.OTHER);\r\n");
                if(isSourceKeyEnableNull){
                    setStatement.append(pstmt + ".setNull(" + (paramIndex+1) + ", java.sql.Types.OTHER);\r\n");
                }
            } else if(generatedType.equals("Boolean")) {
                isObject = true;
                if(!variable.equals("null")) {
                    setStatement.append("if(" + variable + " == null) {\r\n");
                }
                setStatement.append(pstmt + ".setNull(" + paramIndex + ", java.sql.Types.BOOLEAN);\r\n");
                if(isSourceKeyEnableNull){
                    setStatement.append(pstmt + ".setNull(" + (paramIndex+1) + ", java.sql.Types.BOOLEAN);\r\n");
                }
            } else if(generatedType.equals("Double")) {
                isObject = true;
                if(!variable.equals("null")) {
                    setStatement.append("if(" + variable + " == null) {\r\n");
                }
                setStatement.append(pstmt + ".setNull(" + paramIndex + ", java.sql.Types.DOUBLE);\r\n");
                if(isSourceKeyEnableNull){
                    setStatement.append(pstmt + ".setNull(" + (paramIndex+1) + ", java.sql.Types.DOUBLE);\r\n");
                }
            } else if(generatedType.equals("Float")) {
                isObject = true;
                if(!variable.equals("null")) {
                    setStatement.append("if(" + variable + " == null) {\r\n");
                }
                setStatement.append(pstmt + ".setNull(" + paramIndex + ", java.sql.Types.FLOAT);\r\n");
                if(isSourceKeyEnableNull){
                    setStatement.append(pstmt + ".setNull(" + (paramIndex+1) + ", java.sql.Types.FLOAT);\r\n");
                }
            }
            if(!variable.equals("null")) {
                if(isObject) {
                    setStatement.append("} else {\r\n");
                }
                if(generatedType.equals("byte[]")) {
                    generatedType = "Bytes";
                } else if(generatedType.equals("java.util.Date")) {
                    generatedType = "Date";
                } else if(generatedType.equals("Integer")) {
                    generatedType = "Int";
                } else {
                    if(generatedType.length() >= 1) {
                        generatedType = generatedType.substring(0, 1).toUpperCase() + generatedType.substring(1);
                    }
                }
                if(generatedType.equals("Char") || generatedType.equals("Character")) {
                    if(isObject) {
                        setStatement.append("if(" + variable + " == null) {\r\n");
                    } else {
                        setStatement.append("if(String.valueOf(" + variable + ").toLowerCase().equals(\"null\")) {\r\n");
                    }
                    setStatement.append(pstmt + ".setNull(" + paramIndex + ", java.sql.Types.CHAR);\r\n");
                    setStatement.append("} else if(" + variable + " == '\\0') {\r\n");
                    setStatement.append(pstmt + ".setString(" + paramIndex + ", \"\");\r\n");
                    if(isSourceKeyEnableNull){
                        setStatement.append(pstmt + ".setString(" + paramIndex + ", \"\");\r\n");
                    }
                    setStatement.append("} else {\r\n");
                    setStatement.append(pstmt + ".setString(" + paramIndex + ", String.valueOf(" + variable + "));\r\n");
                    if(isSourceKeyEnableNull){
                        setStatement.append(pstmt + ".setString(" + paramIndex + ", String.valueOf(" + variable + "));\r\n");
                    }
                    setStatement.append("}\r\n");
                } else if(generatedType.equals("Date")) {
                    setStatement.append("if(" + variable + " != null) {\r\n");
                    setStatement.append("date_" + cid + " = " + variable + ".getTime();\r\n");
                    setStatement.append("if(date_" + cid + " < year1_" + cid + " || date_" + cid + " >= year10000_" + cid + ") {\r\n");
                    setStatement.append(pstmt + ".setString(" + paramIndex + ", \"0000-00-00 00:00:00\");\r\n");
                    if(isSourceKeyEnableNull){
                        setStatement.append(pstmt + ".setString(" + (paramIndex+1) + ", \"0000-00-00 00:00:00\");\r\n");
                    }
                    setStatement.append("} else {\r\n");
                    setStatement.append(pstmt + ".setTimestamp(" + paramIndex + ", new java.sql.Timestamp(date_" + cid + "));\r\n");
                    if(isSourceKeyEnableNull){
                        setStatement.append(pstmt + ".setTimestamp(" + (paramIndex+1) + ", new java.sql.Timestamp(date_" + cid + "));\r\n");
                    }
                    setStatement.append("}\r\n");
                    setStatement.append("} else {\r\n");
                    setStatement.append(pstmt + ".setNull(" + paramIndex + ", java.sql.Types.DATE);\r\n");
                    if(isSourceKeyEnableNull){
                        setStatement.append(pstmt + ".setNull(" + (paramIndex+1) + ", java.sql.Types.DATE);\r\n");
                    }
                    setStatement.append("}\r\n");
                } else {
                    setStatement.append(pstmt + ".set" + generatedType + "(" + paramIndex + ", " + variable + ");\r\n");
                    if(isSourceKeyEnableNull){
                        setStatement.append(pstmt + ".set" + generatedType + "(" + (paramIndex+1) + ", " + variable + ");\r\n");
                    }
                }
                if(isObject) {
                    setStatement.append("}\r\n");
                }
            }
            return setStatement.toString();
        }
    }
    
    public class NetezzaSCDManager extends SCDManager {
        public NetezzaSCDManager(String cid) {
            super(cid);
        }
        protected String getDBMSId() {
            return "netezza_id";
        }        
        protected String getLProtectedChar() {
            return "";
        }
        protected String getRProtectedChar() {
            return "";
        }
        protected String getSequence(String surrogateKeySequence) {
            return "+ \" next value for " + surrogateKeySequence + "\" + \"";
        }
        protected String getIncrement() {
            return "";
        }        
        protected String getDate(String endDateValue) {
            return "'\" + " + endDateValue + " + \"-01-01'";
        }        
        protected String getYear(String endDateField) {
            return "YEAR(" + getLProtectedChar() + endDateField + getRProtectedChar() + ")";
        }
        protected String getActiveFieldTrueValue(JavaType activeFieldType) {
            if(activeFieldType == JavaTypesManager.STRING) {
                return "'true'";
            } else if(activeFieldType == JavaTypesManager.BOOLEAN) {
                return "'1'";
            } else {
                return "1";
            }            
        }
        protected String getActiveFieldFalseValue(JavaType activeFieldType) {
            if(activeFieldType == JavaTypesManager.STRING) {
                return "'false'";
            } else if(activeFieldType == JavaTypesManager.BOOLEAN) {
                return "'0'";
            } else {
                return "0";
            }            
        } 
        protected String getDBSequenceWithSchemaPrefix (SCDConfiger scdConfiger) {
        	return "\" " +getSequence(scdConfiger.getSurrogateKeySequence());
        }
    }
    
    public class OracleSCDManager extends SCDManager {
        public OracleSCDManager(String cid) {
            super(cid);
        }
        protected String getDBMSId() {
            return "oracle_id";
        }        
        protected String getLProtectedChar() {
            return "";
        }
        protected String getRProtectedChar() {
            return "";
        }
        protected String getSequence(String surrogateKeySequence) {
            return "+ \"" + surrogateKeySequence + "\" + \".nextval";
        }
        protected String getIncrement() {
            return "";
        }        
        protected String getDate(String endDateValue) {
            return "TO_DATE('\" + " + endDateValue + " + \"-01-01', 'yyyy-mm-dd')";
        }        
        protected String getYear(String endDateField) {
            return "EXTRACT(YEAR FROM " + getLProtectedChar() + endDateField + getRProtectedChar() + ")";
        }
        protected String getActiveFieldTrueValue(JavaType activeFieldType) {
            if(activeFieldType == JavaTypesManager.STRING) {
                return "'true'";
            } else if(activeFieldType == JavaTypesManager.BOOLEAN) {
                return "1";
            } else {
                return "1";
            }            
        }
        protected String getActiveFieldFalseValue(JavaType activeFieldType) {
            if(activeFieldType == JavaTypesManager.STRING) {
                return "'false'";
            } else if(activeFieldType == JavaTypesManager.BOOLEAN) {
                return "0";
            } else {
                return "0";
            }            
        } 
        private String dbVersion = "";
        public void setDbVersion(String dbVersion){
            this.dbVersion = dbVersion;  
        }
        public String genSetStatement(String pstmt, String variable, int paramIndex, String generatedType ,boolean isSourceKeyEnableNull, String dbType) {
            StringBuilder setStatement = new StringBuilder();
            boolean isObject = false;
            if(generatedType.equals("Character")) {
                isObject = true;
                if(!variable.equals("null")) {
                    setStatement.append("if(" + variable + " == null) {\r\n");
                }
                setStatement.append(pstmt + ".setNull(" + paramIndex + ", java.sql.Types.CHAR);\r\n");
                if(isSourceKeyEnableNull){
                    setStatement.append(pstmt + ".setNull(" + (paramIndex+1) + ", java.sql.Types.CHAR);\r\n");
                }
            } else if(generatedType.equals("java.util.Date")) {
                isObject = true;
                if(!variable.equals("null")) {
                    setStatement.append("if(" + variable + " == null) {\r\n");
                }
                setStatement.append(pstmt + ".setNull(" + paramIndex + ", java.sql.Types.DATE);\r\n");
                if(isSourceKeyEnableNull){
                    setStatement.append(pstmt + ".setNull(" + (paramIndex+1) + ", java.sql.Types.DATE);\r\n");
                }
            } else if(generatedType.equals("byte[]")) {
                isObject = true;
                if(!variable.equals("null")) {
                    setStatement.append("if(" + variable + " == null) {\r\n");
                }
                setStatement.append(pstmt + ".setNull(" + paramIndex + ", java.sql.Types.ARRAY);\r\n");
                if(isSourceKeyEnableNull){
                    setStatement.append(pstmt + ".setNull(" + (paramIndex+1) + ", java.sql.Types.ARRAY);\r\n");
                }
            } else if(generatedType.equals("Long") || generatedType.equals("Byte") || generatedType.equals("Integer") || generatedType.equals("Short")) {
                isObject = true;
                if(!variable.equals("null")) {
                    setStatement.append("if(" + variable + " == null) {\r\n");
                }
                setStatement.append(pstmt + ".setNull(" + paramIndex + ", java.sql.Types.INTEGER);\r\n");
                if(isSourceKeyEnableNull){
                    setStatement.append(pstmt + ".setNull(" + (paramIndex+1) + ", java.sql.Types.INTEGER);\r\n");
                }
            } else if(generatedType.equals("String")) {
                isObject = true;
                if(!variable.equals("null")) {
                    setStatement.append("if(" + variable + " == null) {\r\n");
                }
                setStatement.append(pstmt + ".setNull(" + paramIndex + ", java.sql.Types.VARCHAR);\r\n");
                if(isSourceKeyEnableNull){
                    setStatement.append(pstmt + ".setNull(" + (paramIndex+1) + ", java.sql.Types.VARCHAR);\r\n");
                }
            } else if(generatedType.equals("Object")) {
                isObject = true;
                if(!variable.equals("null")) {
                    setStatement.append("if(" + variable + " == null) {\r\n");
                }
                setStatement.append(pstmt + ".setNull(" + paramIndex + ", java.sql.Types.OTHER);\r\n");
                if(isSourceKeyEnableNull){
                    setStatement.append(pstmt + ".setNull(" + (paramIndex+1) + ", java.sql.Types.OTHER);\r\n");
                }
            } else if(generatedType.equals("Boolean")) {
                isObject = true;
                if(!variable.equals("null")) {
                    setStatement.append("if(" + variable + " == null) {\r\n");
                }
                setStatement.append(pstmt + ".setNull(" + paramIndex + ", java.sql.Types.BOOLEAN);\r\n");
                if(isSourceKeyEnableNull){
                    setStatement.append(pstmt + ".setNull(" + (paramIndex+1) + ", java.sql.Types.BOOLEAN);\r\n");
                }
            } else if(generatedType.equals("Double")) {
                isObject = true;
                if(!variable.equals("null")) {
                    setStatement.append("if(" + variable + " == null) {\r\n");
                }
                setStatement.append(pstmt + ".setNull(" + paramIndex + ", java.sql.Types.DOUBLE);\r\n");
                if(isSourceKeyEnableNull){
                    setStatement.append(pstmt + ".setNull(" + (paramIndex+1) + ", java.sql.Types.DOUBLE);\r\n");
                }
            } else if(generatedType.equals("Float")) {
                isObject = true;
                if(!variable.equals("null")) {
                    setStatement.append("if(" + variable + " == null) {\r\n");
                }
                setStatement.append(pstmt + ".setNull(" + paramIndex + ", java.sql.Types.FLOAT);\r\n");
                if(isSourceKeyEnableNull){
                    setStatement.append(pstmt + ".setNull(" + (paramIndex+1) + ", java.sql.Types.FLOAT);\r\n");
                }
            }
            if(!variable.equals("null")) {
                if(isObject) {
                    setStatement.append("} else {\r\n");
                }
                if(generatedType.equals("byte[]")) {
                    generatedType = "Bytes";
                } else if(generatedType.equals("java.util.Date")) {
                    generatedType = "Date";
                } else if(generatedType.equals("Integer")) {
                    generatedType = "Int";
                } else {
                    if(generatedType.length() >= 1) {
                        generatedType = generatedType.substring(0, 1).toUpperCase() + generatedType.substring(1);
                    }
                }
                if(generatedType.equals("Char") || generatedType.equals("Character")) {
                    if(isObject) {
                        setStatement.append("if(" + variable + " == null) {\r\n");
                    } else {
                        setStatement.append("if(String.valueOf(" + variable + ").toLowerCase().equals(\"null\")) {\r\n");
                    }
                    setStatement.append(pstmt + ".setNull(" + paramIndex + ", java.sql.Types.CHAR);\r\n");
                    if(isSourceKeyEnableNull){
                        setStatement.append(pstmt + ".setNull(" + (paramIndex+1) + ", java.sql.Types.CHAR);\r\n");
                    }
                    setStatement.append("} else if(" + variable + " == '\\0') {\r\n");
                    setStatement.append(pstmt + ".setString(" + paramIndex + ", \"\");\r\n");
                    if(isSourceKeyEnableNull){
                        setStatement.append(pstmt + ".setString(" + (paramIndex+1) + ", \"\");\r\n");
                    }
                    setStatement.append("} else {\r\n");
                    setStatement.append(pstmt + ".setString(" + paramIndex + ", String.valueOf(" + variable + "));\r\n");
                    if(isSourceKeyEnableNull){
                        setStatement.append(pstmt + ".setString(" + (paramIndex+1) + ", String.valueOf(" + variable + "));\r\n");
                    }
                    setStatement.append("}\r\n");
                } else if(generatedType.equals("Date")) {
                    setStatement.append("if(" + variable + " != null) {\r\n");
                    boolean isTSWithTZ = false; 
                    if(dbType!=null)
                        isTSWithTZ = dbType.matches("TIMESTAMP WITH TIME ZONE");
                    if(isTSWithTZ && ("ORACLE_10".equals(dbVersion) || "ORACLE_11".equals(dbVersion) || "ORACLE_12".equals(dbVersion) || "ORACLE_18".equals(dbVersion) )){
                        setStatement.append("if("+ variable + " instanceof TalendTimestampWithTZ){\r\n");
                        setStatement.append("((oracle.jdbc.OraclePreparedStatement)" + pstmt + ").setTIMESTAMPTZ(" + paramIndex + ", new oracle.sql.TIMESTAMPTZ(connection_" + cid + ", " +
                                "((TalendTimestampWithTZ)"+ variable +").getTimestamp(),((TalendTimestampWithTZ)"+ variable +").getCalendar()));\r\n");
                        if(isSourceKeyEnableNull){
                            setStatement.append("((oracle.jdbc.OraclePreparedStatement)" + pstmt + ").setTIMESTAMPTZ(" + (paramIndex+1) + ", new oracle.sql.TIMESTAMPTZ(connection_" + cid + ", " +
                                    "((TalendTimestampWithTZ)"+ variable +").getTimestamp(),((TalendTimestampWithTZ)"+ variable +").getCalendar()));\r\n");
                        }
                        setStatement.append("}else{");
                        setStatement.append(pstmt + ".setTimestamp(" + paramIndex + ", new java.sql.Timestamp(" + variable + ".getTime()));\r\n");
                        if(isSourceKeyEnableNull){
                            setStatement.append(pstmt + ".setTimestamp(" + (paramIndex+1) + ", new java.sql.Timestamp(" + variable + ".getTime()));\r\n");
                        }
                        setStatement.append("}");
                    }else{
                        setStatement.append(pstmt + ".setTimestamp(" + paramIndex + ", new java.sql.Timestamp(" + variable + ".getTime()));\r\n");
                        if(isSourceKeyEnableNull){
                            setStatement.append(pstmt + ".setTimestamp(" + (paramIndex+1) + ", new java.sql.Timestamp(" + variable + ".getTime()));\r\n");
                        }
                    }
                    setStatement.append("} else {\r\n");
                    setStatement.append(pstmt + ".setNull(" + paramIndex + ", java.sql.Types.DATE);\r\n");
                    if(isSourceKeyEnableNull){
                        setStatement.append(pstmt + ".setNull(" + (paramIndex+1) + ", java.sql.Types.DATE);\r\n");
                    }
                    setStatement.append("}\r\n");
                } else {
                    setStatement.append(pstmt + ".set" + generatedType + "(" + paramIndex + ", " + variable + ");\r\n");
                    if(isSourceKeyEnableNull){
                        setStatement.append(pstmt + ".set" + generatedType + "(" + (paramIndex+1) + ", " + variable + ");\r\n");
                    }
                }
                if(isObject) {
                    setStatement.append("}\r\n");
                }
            }
            return setStatement.toString();
        }
    }
    public class PostgresqlSCDManager extends SCDManager {
        public PostgresqlSCDManager(String cid) {
            super(cid);
        }
        protected String getDBMSId() {
            return "postgres_id";
        }        
        protected String getLProtectedChar() {
            return "\\\"";
        }
        protected String getRProtectedChar() {
            return "\\\"";
        }
        protected String getSequence(String surrogateKeySequence) {
        	return "+ \"nextval('\"+((dbSchema_" + cid +"!= null && dbSchema_" +cid+".trim().length()!=0)?dbSchema_"+cid+" +\".\":\"\") +\"" + surrogateKeySequence + "\"+ \"')\" + \"";
        }
        protected String getIncrement() {
            return "";
        }        
        protected String getDate(String endDateValue) {
            return "'\" + " + endDateValue + " + \"-01-01'";
        }        
        protected String getYear(String endDateField) {
            return "EXTRACT(YEAR FROM " + getLProtectedChar() + endDateField + getRProtectedChar() + ")";
        }
        protected String getActiveFieldTrueValue(JavaType activeFieldType) {
            if(activeFieldType == JavaTypesManager.STRING) {
                return "'true'";
            } else if(activeFieldType == JavaTypesManager.BOOLEAN) {
                return "TRUE";
            } else {
                return "1";
            }            
        }
        protected String getActiveFieldFalseValue(JavaType activeFieldType) {
            if(activeFieldType == JavaTypesManager.STRING) {
                return "'false'";
            } else if(activeFieldType == JavaTypesManager.BOOLEAN) {
                return "FALSE";
            } else {
                return "0";
            }            
        }        
        public String genSetStatement(String pstmt, String variable, int paramIndex, String generatedType ,boolean isSourceKeyEnableNull) {
            StringBuilder setStatement = new StringBuilder();
            boolean isObject = false;
            if(generatedType.equals("Character")) {
                isObject = true;
                if(!variable.equals("null")) {
                    setStatement.append("if(" + variable + " == null) {\r\n");
                }
                setStatement.append(pstmt + ".setNull(" + paramIndex + ", java.sql.Types.CHAR);\r\n");
                if(isSourceKeyEnableNull){
                	setStatement.append(pstmt + ".setNull(" + (paramIndex+1) + ", java.sql.Types.CHAR);\r\n");
                }
            } else if(generatedType.equals("java.util.Date")) {
                isObject = true;
                if(!variable.equals("null")) {
                    setStatement.append("if(" + variable + " == null) {\r\n");
                }
                setStatement.append(pstmt + ".setNull(" + paramIndex + ", java.sql.Types.TIMESTAMP);\r\n");
                if(isSourceKeyEnableNull){
                	setStatement.append(pstmt + ".setNull(" + (paramIndex+1) + ", java.sql.Types.TIMESTAMP);\r\n");
                }
            } else if(generatedType.equals("byte[]")) {
                isObject = true;
                if(!variable.equals("null")) {
                    setStatement.append("if(" + variable + " == null) {\r\n");
                }
                setStatement.append(pstmt + ".setNull(" + paramIndex + ", java.sql.Types.ARRAY);\r\n");
                if(isSourceKeyEnableNull){
                	setStatement.append(pstmt + ".setNull(" + (paramIndex+1) + ", java.sql.Types.ARRAY);\r\n");
                }
            } else if(generatedType.equals("Long") || generatedType.equals("Byte") || generatedType.equals("Integer") || generatedType.equals("Short")) {
                isObject = true;
                if(!variable.equals("null")) {
                    setStatement.append("if(" + variable + " == null) {\r\n");
                }
                setStatement.append(pstmt + ".setNull(" + paramIndex + ", java.sql.Types.INTEGER);\r\n");
                if(isSourceKeyEnableNull){
                	setStatement.append(pstmt + ".setNull(" + (paramIndex+1) + ", java.sql.Types.INTEGER);\r\n");
                }
            } else if(generatedType.equals("String")) {
                isObject = true;
                if(!variable.equals("null")) {
                    setStatement.append("if(" + variable + " == null) {\r\n");
                }
                setStatement.append(pstmt + ".setNull(" + paramIndex + ", java.sql.Types.VARCHAR);\r\n");
                if(isSourceKeyEnableNull){
                	setStatement.append(pstmt + ".setNull(" + (paramIndex+1) + ", java.sql.Types.VARCHAR);\r\n");
                }
            } else if(generatedType.equals("Object")) {
                isObject = true;
                if(!variable.equals("null")) {
                    setStatement.append("if(" + variable + " == null) {\r\n");
                }
                setStatement.append(pstmt + ".setNull(" + paramIndex + ", java.sql.Types.OTHER);\r\n");
                if(isSourceKeyEnableNull){
                	setStatement.append(pstmt + ".setNull(" + (paramIndex+1) + ", java.sql.Types.OTHER);\r\n");
                }
            } else if(generatedType.equals("Boolean")) {
                isObject = true;
                if(!variable.equals("null")) {
                    setStatement.append("if(" + variable + " == null) {\r\n");
                }
                setStatement.append(pstmt + ".setNull(" + paramIndex + ", java.sql.Types.BOOLEAN);\r\n");
                if(isSourceKeyEnableNull){
                	setStatement.append(pstmt + ".setNull(" + (paramIndex+1) + ", java.sql.Types.BOOLEAN);\r\n");
                }
            } else if(generatedType.equals("Double")) {
                isObject = true;
                if(!variable.equals("null")) {
                    setStatement.append("if(" + variable + " == null) {\r\n");
                }
                setStatement.append(pstmt + ".setNull(" + paramIndex + ", java.sql.Types.DOUBLE);\r\n");
                if(isSourceKeyEnableNull){
                	setStatement.append(pstmt + ".setNull(" + (paramIndex+1) + ", java.sql.Types.DOUBLE);\r\n");
                }
            } else if(generatedType.equals("Float")) {
                isObject = true;
                if(!variable.equals("null")) {
                    setStatement.append("if(" + variable + " == null) {\r\n");
                }
                setStatement.append(pstmt + ".setNull(" + paramIndex + ", java.sql.Types.FLOAT);\r\n");
                if(isSourceKeyEnableNull){
                	setStatement.append(pstmt + ".setNull(" + (paramIndex+1) + ", java.sql.Types.FLOAT);\r\n");
                }
            }
            if(!variable.equals("null")) {
                if(isObject) {
                    setStatement.append("} else {\r\n");
                }
                if(generatedType.equals("byte[]")) {
                    generatedType = "Bytes";
                } else if(generatedType.equals("java.util.Date")) {
                    generatedType = "Date";
                } else if(generatedType.equals("Integer")) {
                    generatedType = "Int";
                } else {
                    if(generatedType.length() >= 1) {
                        generatedType = generatedType.substring(0, 1).toUpperCase() + generatedType.substring(1);
                    }
                }
                if(generatedType.equals("Char") || generatedType.equals("Character")) {
                    if(isObject) {
                        setStatement.append("if(" + variable + " == null) {\r\n");
                    } else {
                        setStatement.append("if(String.valueOf(" + variable + ").toLowerCase().equals(\"null\")) {\r\n");
                    }
                    setStatement.append(pstmt + ".setNull(" + paramIndex + ", java.sql.Types.CHAR);\r\n");
                    if(isSourceKeyEnableNull){
                    	setStatement.append(pstmt + ".setNull(" + (paramIndex+1) + ", java.sql.Types.CHAR);\r\n");
                    }
                    setStatement.append("} else if(" + variable + " == '\\0') {\r\n");
                    setStatement.append(pstmt + ".setString(" + paramIndex + ", \"\");\r\n");
                    if(isSourceKeyEnableNull){
                    	setStatement.append(pstmt + ".setString(" + (paramIndex+1) + ", \"\");\r\n");
                    }
                    setStatement.append("} else {\r\n");
                    setStatement.append(pstmt + ".setString(" + paramIndex + ", String.valueOf(" + variable + "));\r\n");
                    if(isSourceKeyEnableNull){
                    	setStatement.append(pstmt + ".setString(" + (paramIndex+1) + ", String.valueOf(" + variable + "));\r\n");
                    }
                    setStatement.append("}\r\n");
                } else if(generatedType.equals("Date")) {
                    setStatement.append("if(" + variable + " != null) {\r\n");
                    setStatement.append(pstmt + ".setTimestamp(" + paramIndex + ", new java.sql.Timestamp(" + variable + ".getTime()));\r\n");
                    if(isSourceKeyEnableNull){
                    	setStatement.append(pstmt + ".setTimestamp(" + (paramIndex+1) + ", new java.sql.Timestamp(" + variable + ".getTime()));\r\n");
                    }
                    setStatement.append("} else {\r\n");
                    setStatement.append(pstmt + ".setNull(" + paramIndex + ", java.sql.Types.TIMESTAMP);\r\n");
                    if(isSourceKeyEnableNull){
                    	setStatement.append(pstmt + ".setNull(" + (paramIndex+1) + ", java.sql.Types.TIMESTAMP);\r\n");
                    }
                    setStatement.append("}\r\n");
                } else {
                    setStatement.append(pstmt + "."+"set" + generatedType + "(" + paramIndex + ", " + variable + ");\r\n");
                    if(isSourceKeyEnableNull){
                    	setStatement.append(pstmt + "."+"set" + generatedType + "(" + (paramIndex+1) + ", " + variable + ");\r\n");
                    }
                }
                if(isObject) {
                    setStatement.append("}\r\n");
                }
            }
            return setStatement.toString();
        }
        protected String getDBSequenceWithSchemaPrefix (SCDConfiger scdConfiger) {
        	return "\" " +getSequence(scdConfiger.getSurrogateKeySequence());
        }
    }
    public class PostgrePlusSCDManager extends SCDManager {
        public PostgrePlusSCDManager(String cid) {
            super(cid);
        }
        protected String getDBMSId() {
            return "postgresplus_id";
        }        
        protected String getLProtectedChar() {
            return "\\\"";
        }
        protected String getRProtectedChar() {
            return "\\\"";
        }
        protected String getSequence(String surrogateKeySequence) {
        	return "+ \"nextval('\"+((dbSchema_" + cid +"!= null && dbSchema_" +cid+".trim().length()!=0)?dbSchema_"+cid+" +\".\":\"\") +\"" + surrogateKeySequence + "\"+ \"')\" + \"";
        }
        protected String getIncrement() {
            return "";
        }        
        protected String getDate(String endDateValue) {
            return "'\" + " + endDateValue + " + \"-01-01'";
        }        
        protected String getYear(String endDateField) {
            return "EXTRACT(YEAR FROM " + getLProtectedChar() + endDateField + getRProtectedChar() + ")";
        }
        protected String getActiveFieldTrueValue(JavaType activeFieldType) {
            if(activeFieldType == JavaTypesManager.STRING) {
                return "'true'";
            } else if(activeFieldType == JavaTypesManager.BOOLEAN) {
                return "TRUE";
            } else {
                return "1";
            }            
        }
        protected String getActiveFieldFalseValue(JavaType activeFieldType) {
            if(activeFieldType == JavaTypesManager.STRING) {
                return "'false'";
            } else if(activeFieldType == JavaTypesManager.BOOLEAN) {
                return "FALSE";
            } else {
                return "0";
            }            
        }
		protected String getDBSequenceWithSchemaPrefix (SCDConfiger scdConfiger) {
        	return "\" " +getSequence(scdConfiger.getSurrogateKeySequence());
        }
    }
    public class SybaseSCDManager extends SCDManager {
        public SybaseSCDManager(String cid) {
            super(cid);
        }
        protected String getDBMSId() {
            return "sybase_id";
        }        
        protected String getLProtectedChar() {
            return "";
        }
        protected String getRProtectedChar() {
            return "";
        }
        protected String getSequence(String surrogateKeySequence) {
            return "\" + " + surrogateKeySequence + " + \".nextval";
        }
        protected String getIncrement() {
            return "";
        }        
        protected String getDate(String endDateValue) {
            return "'\" + " + endDateValue + " + \"-01-01'";
        }        
        protected String getYear(String endDateField) {
            return "YEAR(" + getLProtectedChar() + endDateField + getRProtectedChar() + ")";
        }
        protected String getActiveFieldTrueValue(JavaType activeFieldType) {
            if(activeFieldType == JavaTypesManager.STRING) {
                return "'true'";
            } else if(activeFieldType == JavaTypesManager.BOOLEAN) {
                return "1";
            } else {
                return "1";
            }            
        }
        protected String getActiveFieldFalseValue(JavaType activeFieldType) {
            if(activeFieldType == JavaTypesManager.STRING) {
                return "'false'";
            } else if(activeFieldType == JavaTypesManager.BOOLEAN) {
                return "0";
            } else {
                return "0";
            }            
        }        
    }
    
    public class VerticaSCDManager extends SCDManager {
        public VerticaSCDManager(String cid) {
            super(cid);
        }
        protected String getDBMSId() {
            return "vertica_id";
        }        
        protected String getLProtectedChar() {
            return "";
        }
        protected String getRProtectedChar() {
            return "";
        }
        protected String getSequence(String surrogateKeySequence) {
        	return "+ \"nextval('\"+((dbschema_" + cid +"!= null && dbschema_" +cid+".trim().length()!=0)?dbschema_"+cid+" +\".\":\"\") +\"" + surrogateKeySequence + "\"+ \"')\" + \"";
        }
        protected String getIncrement() {
        	return " auto_increment ";
        }        
        protected String getDate(String endDateValue) {
            return "TO_DATE('\" + " + endDateValue + " + \"-01-01', 'YYYY-MM-DD')";
        }        
        protected String getYear(String endDateField) {
            return "EXTRACT(YEAR FROM " + getLProtectedChar() + endDateField + getRProtectedChar() + ")";
        }
        protected String getActiveFieldTrueValue(JavaType activeFieldType) {
            if(activeFieldType == JavaTypesManager.STRING) {
                return "'true'";
            } else if(activeFieldType == JavaTypesManager.BOOLEAN) {
                return "'1'";
            } else {
                return "1";
            }            
        }
        protected String getActiveFieldFalseValue(JavaType activeFieldType) {
            if(activeFieldType == JavaTypesManager.STRING) {
                return "'false'";
            } else if(activeFieldType == JavaTypesManager.BOOLEAN) {
                return "'0'";
            } else {
                return "0";
            }            
        }
        protected String getDBSequenceWithSchemaPrefix (SCDConfiger scdConfiger) {
        	return "\" " +getSequence(scdConfiger.getSurrogateKeySequence());
        }
    }
    public SCDManager getSCDManager(String cid, String dbType) {
        SCDManager manager = managerMap.get(cid);
        if(manager == null) {
            if(dbType.equals(DB2)) {
                manager = new DB2SCDManager(cid);
            } else if(dbType.equals(INGRES)) {
                manager = new IngresSCDManager(cid);
            } else if(dbType.equals(MSSQL)) {
                manager = new MSSQLSCDManager(cid);
            } else if(dbType.equals(MYSQL)) {
                manager = new MysqlSCDManager(cid);
            } else if(dbType.equals(ORACLE)) {
                manager = new OracleSCDManager(cid);
            } else if(dbType.equals(POSTGREPLUS)) {
                manager = new PostgrePlusSCDManager(cid);
            } else if(dbType.equals(POSTGRESQL)) {
                manager = new PostgresqlSCDManager(cid);
            } else if(dbType.equals(SYBASE)) {
                manager = new SybaseSCDManager(cid);
            } else if(dbType.equals(INFORMIX)) {
                manager = new InfromixSCDManager(cid);
            } else if(dbType.equals(NETEZZA)) {
                manager = new NetezzaSCDManager(cid);
            } else if (dbType.equals(VERTICA)) {
                manager = new VerticaSCDManager(cid);
            } else if (dbType.equals(TERADATA)) {
                manager = new TeradataSCDManager(cid);
            }
            managerMap.put(cid, manager);
        }
        return manager;
    }
    public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(TEXT_1);
    
CodeGeneratorArgument codeGenArgument = (CodeGeneratorArgument) argument;
INode node = (INode) codeGenArgument.getArgument();
String cid = node.getUniqueName();
List<IMetadataColumn> inputSchema = getInputSchema(node);
List<IMetadataColumn> outputSchema = getOutputSchema(node);

String incomingConnName = null;
List<? extends IConnection> incomingConnections = node.getIncomingConnections();
if(incomingConnections != null && incomingConnections.size() > 0) {
    for(IConnection incomingConnection : incomingConnections) {
        if(incomingConnection.getLineStyle().hasConnectionCategory(IConnectionCategory.DATA)) {
            incomingConnName = incomingConnection.getName();
        }
    }
}
if(inputSchema != null && inputSchema.size() > 0 && outputSchema != null && outputSchema.size() > 0) {
    String dbhost = ElementParameterParser.getValue(node, "__HOST__");
    String dbport = ElementParameterParser.getValue(node, "__PORT__");
    String dbname= ElementParameterParser.getValue(node, "__DBNAME__");
    String dbschema = ElementParameterParser.getValue(node, "__TABLESCHEMA__");
    String dbuser= ElementParameterParser.getValue(node, "__USER__");

    String dbtable = ElementParameterParser.getValue(node, "__TABLE__");
    boolean debug = ("true").equals(ElementParameterParser.getValue(node, "__DEBUG__"));
    boolean isEnableSaveMemMode = ("true").equals(ElementParameterParser.getValue(node, "__USE_SAVE_MEM_MODE__"));
    String dbType = DB2;
    SCDConfiger scdConfiger = getSCDConfiger(node);
    SCDManager scdManager = getSCDManager(cid, dbType);
    
    
    if(!isEnableSaveMemMode) {    
        
    stringBuffer.append(TEXT_2);
    stringBuffer.append(genSourceKeyClass(scdConfiger, cid));
    
    }
    
    stringBuffer.append(TEXT_3);
    stringBuffer.append(genDimensionAttributeClass(scdConfiger, cid));
    stringBuffer.append(TEXT_4);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_7);
    
    String useExistingConn = ElementParameterParser.getValue(node,"__USE_EXISTING_CONNECTION__");
    if(("true").equals(useExistingConn)) {
        String connection = ElementParameterParser.getValue(node,"__CONNECTION__");
        String conn = "conn_" + connection;
        
    stringBuffer.append(TEXT_8);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_9);
    stringBuffer.append(connection);
    stringBuffer.append(TEXT_10);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_11);
    stringBuffer.append(conn);
    stringBuffer.append(TEXT_12);
    
    } else {       
		String dbproperties = ElementParameterParser.getValue(node, "__PROPERTIES__");
		if(dbproperties == null || ("\"\"").equals(dbproperties) || ("").equals(dbproperties)) {

    stringBuffer.append(TEXT_13);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_14);
    stringBuffer.append(dbhost);
    stringBuffer.append(TEXT_15);
    stringBuffer.append(dbport);
    stringBuffer.append(TEXT_16);
    stringBuffer.append(dbname);
    stringBuffer.append(TEXT_17);
    
		} else {

    stringBuffer.append(TEXT_18);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_14);
    stringBuffer.append(dbhost);
    stringBuffer.append(TEXT_15);
    stringBuffer.append(dbport);
    stringBuffer.append(TEXT_16);
    stringBuffer.append(dbname);
    stringBuffer.append(TEXT_15);
    stringBuffer.append(dbproperties);
    stringBuffer.append(TEXT_19);
    
		}

    stringBuffer.append(TEXT_20);
    
		String passwordFieldName = "__PASS__";
		
    stringBuffer.append(TEXT_21);
    if (ElementParameterParser.canEncrypt(node, passwordFieldName)) {
    stringBuffer.append(TEXT_22);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_23);
    stringBuffer.append(ElementParameterParser.getEncryptedValue(node, passwordFieldName));
    stringBuffer.append(TEXT_24);
    } else {
    stringBuffer.append(TEXT_25);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_26);
    stringBuffer.append( ElementParameterParser.getValue(node, passwordFieldName));
    stringBuffer.append(TEXT_27);
    }
    stringBuffer.append(TEXT_28);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_29);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_30);
    stringBuffer.append(dbuser);
    stringBuffer.append(TEXT_31);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_32);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_26);
    stringBuffer.append(dbschema);
    stringBuffer.append(TEXT_33);
    
	}
    
    stringBuffer.append(TEXT_34);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_35);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_36);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_37);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_26);
    stringBuffer.append(dbtable);
    stringBuffer.append(TEXT_38);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_39);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_40);
    stringBuffer.append(dbtable);
    stringBuffer.append(TEXT_41);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_42);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_43);
    
    if(isEnableSaveMemMode) {
        
    stringBuffer.append(TEXT_44);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_45);
    stringBuffer.append(scdManager.createSearchingSQL(scdConfiger,false));
    stringBuffer.append(TEXT_46);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_47);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_48);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_49);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_50);
            
    } else {
        List<Column> searchColumns = getSearchColumnList(scdConfiger);
        List<Column> sourceKeys = scdConfiger.getSourceKeys();
        
    stringBuffer.append(TEXT_51);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_45);
    stringBuffer.append(scdManager.createSelectionSQL(scdConfiger));
    stringBuffer.append(TEXT_52);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_47);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_53);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_54);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_55);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_56);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_57);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_58);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_59);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_57);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_60);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_61);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_62);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_63);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_64);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_65);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_66);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_67);
    
            int index = 1;
            for(Column searchColumn : searchColumns) {
                boolean isSourceKey = false;
                for(Column sourceKey : sourceKeys) {
                    if(searchColumn.getColumnName().equals(sourceKey.getColumnName())) {
                        isSourceKey = true;
                        break;
                    }
                }
                String generatedType = searchColumn.getJavaType();
                String struct = (isSourceKey ? "sk_" : "row_") + cid;
                if(("byte[]").equals(generatedType)) {
                    generatedType = "Bytes";
                } else if(("java.util.Date").equals(generatedType)) {
                    generatedType = "Timestamp";
                } else if(("Integer").equals(generatedType)) {
                    generatedType = "Int";
                } else {
                    generatedType = generatedType.substring(0, 1).toUpperCase() + generatedType.substring(1);
                }
                if(("Char").equals(generatedType) || ("Character").equals(generatedType)) {
                    
    stringBuffer.append(TEXT_68);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_69);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_70);
    stringBuffer.append(index);
    stringBuffer.append(TEXT_71);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_72);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_73);
    stringBuffer.append(TEXT_74);
    stringBuffer.append(struct);
    stringBuffer.append(TEXT_75);
    stringBuffer.append(searchColumn.getColumnName());
    stringBuffer.append(TEXT_76);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_77);
    
                        if(("Character").equals(generatedType)) {
                            
    stringBuffer.append(TEXT_78);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_79);
    stringBuffer.append(TEXT_80);
    stringBuffer.append(struct);
    stringBuffer.append(TEXT_75);
    stringBuffer.append(searchColumn.getColumnName());
    stringBuffer.append(TEXT_81);
    stringBuffer.append(TEXT_80);
    stringBuffer.append(struct);
    stringBuffer.append(TEXT_75);
    stringBuffer.append(searchColumn.getColumnName());
    stringBuffer.append(TEXT_82);
    
                        } else {
                            
    stringBuffer.append(TEXT_83);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_84);
    stringBuffer.append(TEXT_80);
    stringBuffer.append(struct);
    stringBuffer.append(TEXT_75);
    stringBuffer.append(searchColumn.getColumnName());
    stringBuffer.append(TEXT_85);
    stringBuffer.append(searchColumn.getColumnName());
    stringBuffer.append(TEXT_86);
    stringBuffer.append(incomingConnName);
    stringBuffer.append(TEXT_87);
    
                        }
                        
    stringBuffer.append(TEXT_88);
    
                } else if(("Timestamp").equals(generatedType)) {
                    
    stringBuffer.append(TEXT_89);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_69);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_90);
    stringBuffer.append(index);
    stringBuffer.append(TEXT_91);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_92);
    stringBuffer.append(TEXT_74);
    stringBuffer.append(struct);
    stringBuffer.append(TEXT_75);
    stringBuffer.append(searchColumn.getColumnName());
    stringBuffer.append(TEXT_93);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_94);
    stringBuffer.append(TEXT_74);
    stringBuffer.append(struct);
    stringBuffer.append(TEXT_75);
    stringBuffer.append(searchColumn.getColumnName());
    stringBuffer.append(TEXT_95);
    
                } else {
                    
    stringBuffer.append(TEXT_96);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_97);
    stringBuffer.append(index);
    stringBuffer.append(TEXT_98);
    stringBuffer.append(TEXT_74);
    stringBuffer.append(struct);
    stringBuffer.append(TEXT_75);
    stringBuffer.append(searchColumn.getColumnName());
    stringBuffer.append(TEXT_69);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_99);
    stringBuffer.append(generatedType);
    stringBuffer.append(TEXT_100);
    stringBuffer.append(index);
    stringBuffer.append(TEXT_101);
    
                }
                index++;
            }
            
    stringBuffer.append(TEXT_102);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_103);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_104);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_105);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_106);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_107);
    
        if(debug) {
            
    stringBuffer.append(TEXT_108);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_109);
    
        }        
    }
    if(("TABLE_MAX").equals(scdConfiger.getSurrogateKeyValueType())) {
        
    stringBuffer.append(TEXT_2);
    stringBuffer.append(scdConfiger.getSurrogateKeyFieldType());
    stringBuffer.append(TEXT_110);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_111);
    
        if(!isEnableSaveMemMode) {
            
    stringBuffer.append(TEXT_112);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_113);
    
        }
        
    stringBuffer.append(TEXT_114);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_115);
    stringBuffer.append(scdConfiger.getSurrogateKeyField());
    stringBuffer.append(TEXT_116);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_117);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_47);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_118);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_119);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_120);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_24);
    
        String tmpType = null;        
        if(("Integer").equals(scdConfiger.getSurrogateKeyFieldType())) {
            tmpType = "Int";
        } else {
            tmpType = scdConfiger.getSurrogateKeyFieldType().substring(0, 1).toUpperCase() + scdConfiger.getSurrogateKeyFieldType().substring(1);
        }
        
    stringBuffer.append(TEXT_121);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_122);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_123);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_124);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_99);
    stringBuffer.append(tmpType);
    stringBuffer.append(TEXT_125);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_126);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_107);
    
        if(!isEnableSaveMemMode) {
            
    stringBuffer.append(TEXT_127);
    
        }
    }
    
    stringBuffer.append(TEXT_128);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_45);
    stringBuffer.append(scdManager.createInsertionSQL(scdConfiger));
    stringBuffer.append(TEXT_129);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_47);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_130);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_24);
    
    if(scdConfiger.isEnableType2()) {
        int paramIndex = 0;
        List<Column> insertionColumnList = getInsertionColumnList(scdConfiger);
        if(scdConfiger.isEnableType3()) {
            paramIndex = insertionColumnList.size() - scdConfiger.getDimensionType3Configer().getDimensionAttributes().size() - 1;
        } else {
            paramIndex = insertionColumnList.size() - 1;
        }       
        if(("JOB_START_TIME").equals(scdConfiger.getDimensionType2Configer().getStartDateValueType())) {
            
    stringBuffer.append(TEXT_131);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_132);
    stringBuffer.append(paramIndex);
    stringBuffer.append(TEXT_133);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_134);
    
        }
        if(("NULL_VALUE").equals(scdConfiger.getDimensionType2Configer().getEndDateValueType())) {
            
    stringBuffer.append(TEXT_131);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_135);
    stringBuffer.append(paramIndex + 1);
    stringBuffer.append(TEXT_136);
    
        } else {
        	String endDateTimeString = ElementParameterParser.getValue(node, "__L2_ENDDATE_TIME_DETAILS__");
            
    stringBuffer.append(TEXT_137);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_26);
    stringBuffer.append(endDateTimeString.length()>0?endDateTimeString:"\"\"");
    stringBuffer.append(TEXT_138);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_139);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_140);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_141);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_142);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_143);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_132);
    stringBuffer.append(paramIndex + 1);
    stringBuffer.append(TEXT_144);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_145);
    stringBuffer.append(scdConfiger.getDimensionType2Configer().getEndDateValue());
    stringBuffer.append(TEXT_146);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_147);
    
        }
    }
    if(scdConfiger.isEnableType1()) {
        
    stringBuffer.append(TEXT_148);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_45);
    stringBuffer.append(scdManager.createUpdateSQLForType1(scdConfiger,false));
    stringBuffer.append(TEXT_149);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_47);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_150);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_151);
    
    }
    if(scdConfiger.isEnableType2()) {
        
    stringBuffer.append(TEXT_152);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_45);
    stringBuffer.append(scdManager.createUpdateSQLForType2(scdConfiger,false));
    stringBuffer.append(TEXT_153);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_47);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_154);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_24);
    
        if(("JOB_START_TIME").equals(scdConfiger.getDimensionType2Configer().getStartDateValueType())) {
            
    stringBuffer.append(TEXT_155);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_156);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_134);
    
        }
    }
    if(scdConfiger.isEnableType3()) {
        
    stringBuffer.append(TEXT_157);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_45);
    stringBuffer.append(scdManager.createUpdateSQLForType3(scdConfiger,false));
    stringBuffer.append(TEXT_158);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_47);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_159);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_24);
    
    }
    
    
    if(!isEnableSaveMemMode) {
        
    stringBuffer.append(TEXT_160);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_161);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_162);
    
    }
    
    stringBuffer.append(TEXT_163);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_164);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_50);
    
}

    stringBuffer.append(TEXT_165);
    return stringBuffer.toString();
  }
}
