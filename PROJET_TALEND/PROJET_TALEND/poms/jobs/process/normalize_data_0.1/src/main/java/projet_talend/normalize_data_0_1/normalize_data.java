// ============================================================================
//
// Copyright (c) 2006-2015, Talend SA
//
// Ce code source a été automatiquement généré par_Talend Open Studio for Big Data
// / Soumis à la Licence Apache, Version 2.0 (la "Licence") ;
// votre utilisation de ce fichier doit respecter les termes de la Licence.
// Vous pouvez obtenir une copie de la Licence sur
// http://www.apache.org/licenses/LICENSE-2.0
// 
// Sauf lorsqu'explicitement prévu par la loi en vigueur ou accepté par écrit, le logiciel
// distribué sous la Licence est distribué "TEL QUEL",
// SANS GARANTIE OU CONDITION D'AUCUNE SORTE, expresse ou implicite.
// Consultez la Licence pour connaître la terminologie spécifique régissant les autorisations et
// les limites prévues par la Licence.

package projet_talend.normalize_data_0_1;

import routines.Numeric;
import routines.DataOperation;
import routines.TalendDataGenerator;
import routines.TalendStringUtil;
import routines.TalendString;
import routines.StringHandling;
import routines.Relational;
import routines.TalendDate;
import routines.Mathematical;
import routines.system.*;
import routines.system.api.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.math.BigDecimal;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.util.Comparator;

@SuppressWarnings("unused")

/**
 * Job: normalize_data Purpose: <br>
 * Description: <br>
 * 
 * @author user@talend.com
 * @version 8.0.1.20211109_1610
 * @status
 */
public class normalize_data implements TalendJob {

	protected static void logIgnoredError(String message, Throwable cause) {
		System.err.println(message);
		if (cause != null) {
			cause.printStackTrace();
		}

	}

	public final Object obj = new Object();

	// for transmiting parameters purpose
	private Object valueObject = null;

	public Object getValueObject() {
		return this.valueObject;
	}

	public void setValueObject(Object valueObject) {
		this.valueObject = valueObject;
	}

	private final static String defaultCharset = java.nio.charset.Charset.defaultCharset().name();

	private final static String utf8Charset = "UTF-8";

	// contains type for every context property
	public class PropertiesWithType extends java.util.Properties {
		private static final long serialVersionUID = 1L;
		private java.util.Map<String, String> propertyTypes = new java.util.HashMap<>();

		public PropertiesWithType(java.util.Properties properties) {
			super(properties);
		}

		public PropertiesWithType() {
			super();
		}

		public void setContextType(String key, String type) {
			propertyTypes.put(key, type);
		}

		public String getContextType(String key) {
			return propertyTypes.get(key);
		}
	}

	// create and load default properties
	private java.util.Properties defaultProps = new java.util.Properties();

	// create application properties with default
	public class ContextProperties extends PropertiesWithType {

		private static final long serialVersionUID = 1L;

		public ContextProperties(java.util.Properties properties) {
			super(properties);
		}

		public ContextProperties() {
			super();
		}

		public void synchronizeContext() {

			if (input_file != null) {

				this.setProperty("input_file", input_file.toString());

			}

			if (output_file != null) {

				this.setProperty("output_file", output_file.toString());

			}

		}

		// if the stored or passed value is "<TALEND_NULL>" string, it mean null
		public String getStringValue(String key) {
			String origin_value = this.getProperty(key);
			if (NULL_VALUE_EXPRESSION_IN_COMMAND_STRING_FOR_CHILD_JOB_ONLY.equals(origin_value)) {
				return null;
			}
			return origin_value;
		}

		public String input_file;

		public String getInput_file() {
			return this.input_file;
		}

		public String output_file;

		public String getOutput_file() {
			return this.output_file;
		}

	}

	protected ContextProperties context = new ContextProperties(); // will be instanciated by MS.

	public ContextProperties getContext() {
		return this.context;
	}

	private final String jobVersion = "0.1";
	private final String jobName = "normalize_data";
	private final String projectName = "PROJET_TALEND";
	public Integer errorCode = null;
	private String currentComponent = "";

	private final java.util.Map<String, Object> globalMap = new java.util.HashMap<String, Object>();
	private final static java.util.Map<String, Object> junitGlobalMap = new java.util.HashMap<String, Object>();

	private final java.util.Map<String, Long> start_Hash = new java.util.HashMap<String, Long>();
	private final java.util.Map<String, Long> end_Hash = new java.util.HashMap<String, Long>();
	private final java.util.Map<String, Boolean> ok_Hash = new java.util.HashMap<String, Boolean>();
	public final java.util.List<String[]> globalBuffer = new java.util.ArrayList<String[]>();

	private RunStat runStat = new RunStat();

	// OSGi DataSource
	private final static String KEY_DB_DATASOURCES = "KEY_DB_DATASOURCES";

	private final static String KEY_DB_DATASOURCES_RAW = "KEY_DB_DATASOURCES_RAW";

	public void setDataSources(java.util.Map<String, javax.sql.DataSource> dataSources) {
		java.util.Map<String, routines.system.TalendDataSource> talendDataSources = new java.util.HashMap<String, routines.system.TalendDataSource>();
		for (java.util.Map.Entry<String, javax.sql.DataSource> dataSourceEntry : dataSources.entrySet()) {
			talendDataSources.put(dataSourceEntry.getKey(),
					new routines.system.TalendDataSource(dataSourceEntry.getValue()));
		}
		globalMap.put(KEY_DB_DATASOURCES, talendDataSources);
		globalMap.put(KEY_DB_DATASOURCES_RAW, new java.util.HashMap<String, javax.sql.DataSource>(dataSources));
	}

	public void setDataSourceReferences(List serviceReferences) throws Exception {

		java.util.Map<String, routines.system.TalendDataSource> talendDataSources = new java.util.HashMap<String, routines.system.TalendDataSource>();
		java.util.Map<String, javax.sql.DataSource> dataSources = new java.util.HashMap<String, javax.sql.DataSource>();

		for (java.util.Map.Entry<String, javax.sql.DataSource> entry : BundleUtils
				.getServices(serviceReferences, javax.sql.DataSource.class).entrySet()) {
			dataSources.put(entry.getKey(), entry.getValue());
			talendDataSources.put(entry.getKey(), new routines.system.TalendDataSource(entry.getValue()));
		}

		globalMap.put(KEY_DB_DATASOURCES, talendDataSources);
		globalMap.put(KEY_DB_DATASOURCES_RAW, new java.util.HashMap<String, javax.sql.DataSource>(dataSources));
	}

	private final java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
	private final java.io.PrintStream errorMessagePS = new java.io.PrintStream(new java.io.BufferedOutputStream(baos));

	public String getExceptionStackTrace() {
		if ("failure".equals(this.getStatus())) {
			errorMessagePS.flush();
			return baos.toString();
		}
		return null;
	}

	private Exception exception;

	public Exception getException() {
		if ("failure".equals(this.getStatus())) {
			return this.exception;
		}
		return null;
	}

	private class TalendException extends Exception {

		private static final long serialVersionUID = 1L;

		private java.util.Map<String, Object> globalMap = null;
		private Exception e = null;
		private String currentComponent = null;
		private String virtualComponentName = null;

		public void setVirtualComponentName(String virtualComponentName) {
			this.virtualComponentName = virtualComponentName;
		}

		private TalendException(Exception e, String errorComponent, final java.util.Map<String, Object> globalMap) {
			this.currentComponent = errorComponent;
			this.globalMap = globalMap;
			this.e = e;
		}

		public Exception getException() {
			return this.e;
		}

		public String getCurrentComponent() {
			return this.currentComponent;
		}

		public String getExceptionCauseMessage(Exception e) {
			Throwable cause = e;
			String message = null;
			int i = 10;
			while (null != cause && 0 < i--) {
				message = cause.getMessage();
				if (null == message) {
					cause = cause.getCause();
				} else {
					break;
				}
			}
			if (null == message) {
				message = e.getClass().getName();
			}
			return message;
		}

		@Override
		public void printStackTrace() {
			if (!(e instanceof TalendException || e instanceof TDieException)) {
				if (virtualComponentName != null && currentComponent.indexOf(virtualComponentName + "_") == 0) {
					globalMap.put(virtualComponentName + "_ERROR_MESSAGE", getExceptionCauseMessage(e));
				}
				globalMap.put(currentComponent + "_ERROR_MESSAGE", getExceptionCauseMessage(e));
				System.err.println("Exception in component " + currentComponent + " (" + jobName + ")");
			}
			if (!(e instanceof TDieException)) {
				if (e instanceof TalendException) {
					e.printStackTrace();
				} else {
					e.printStackTrace();
					e.printStackTrace(errorMessagePS);
					normalize_data.this.exception = e;
				}
			}
			if (!(e instanceof TalendException)) {
				try {
					for (java.lang.reflect.Method m : this.getClass().getEnclosingClass().getMethods()) {
						if (m.getName().compareTo(currentComponent + "_error") == 0) {
							m.invoke(normalize_data.this, new Object[] { e, currentComponent, globalMap });
							break;
						}
					}

					if (!(e instanceof TDieException)) {
					}
				} catch (Exception e) {
					this.e.printStackTrace();
				}
			}
		}
	}

	public void tFileInputDelimited_2_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tMap_8_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tMap_9_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBOutput_6_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBInput_2_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBInput_3_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBInput_4_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBInput_5_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tAdvancedHash_row7_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tAdvancedHash_row8_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tAdvancedHash_row9_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tAdvancedHash_row10_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileInputDelimited_2_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public static class DimensionFact1Struct implements routines.system.IPersistableRow<DimensionFact1Struct> {
		final static byte[] commonByteArrayLock_PROJET_TALEND_normalize_data = new byte[0];
		static byte[] commonByteArray_PROJET_TALEND_normalize_data = new byte[0];

		public Integer ProductKey;

		public Integer getProductKey() {
			return this.ProductKey;
		}

		public Integer CustomerKey;

		public Integer getCustomerKey() {
			return this.CustomerKey;
		}

		public Integer SupplierKey;

		public Integer getSupplierKey() {
			return this.SupplierKey;
		}

		public Integer Shipperkey;

		public Integer getShipperkey() {
			return this.Shipperkey;
		}

		public String QuantitySold;

		public String getQuantitySold() {
			return this.QuantitySold;
		}

		public String TotalAmount;

		public String getTotalAmount() {
			return this.TotalAmount;
		}

		public String DiscountAmount;

		public String getDiscountAmount() {
			return this.DiscountAmount;
		}

		public Float NetAmount;

		public Float getNetAmount() {
			return this.NetAmount;
		}

		public Integer Saleskey;

		public Integer getSaleskey() {
			return this.Saleskey;
		}

		public Integer id;

		public Integer getId() {
			return this.id;
		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_PROJET_TALEND_normalize_data.length) {
					if (length < 1024 && commonByteArray_PROJET_TALEND_normalize_data.length == 0) {
						commonByteArray_PROJET_TALEND_normalize_data = new byte[1024];
					} else {
						commonByteArray_PROJET_TALEND_normalize_data = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_PROJET_TALEND_normalize_data, 0, length);
				strReturn = new String(commonByteArray_PROJET_TALEND_normalize_data, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_PROJET_TALEND_normalize_data.length) {
					if (length < 1024 && commonByteArray_PROJET_TALEND_normalize_data.length == 0) {
						commonByteArray_PROJET_TALEND_normalize_data = new byte[1024];
					} else {
						commonByteArray_PROJET_TALEND_normalize_data = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_PROJET_TALEND_normalize_data, 0, length);
				strReturn = new String(commonByteArray_PROJET_TALEND_normalize_data, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_PROJET_TALEND_normalize_data) {

				try {

					int length = 0;

					this.ProductKey = readInteger(dis);

					this.CustomerKey = readInteger(dis);

					this.SupplierKey = readInteger(dis);

					this.Shipperkey = readInteger(dis);

					this.QuantitySold = readString(dis);

					this.TotalAmount = readString(dis);

					this.DiscountAmount = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.NetAmount = null;
					} else {
						this.NetAmount = dis.readFloat();
					}

					this.Saleskey = readInteger(dis);

					this.id = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_PROJET_TALEND_normalize_data) {

				try {

					int length = 0;

					this.ProductKey = readInteger(dis);

					this.CustomerKey = readInteger(dis);

					this.SupplierKey = readInteger(dis);

					this.Shipperkey = readInteger(dis);

					this.QuantitySold = readString(dis);

					this.TotalAmount = readString(dis);

					this.DiscountAmount = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.NetAmount = null;
					} else {
						this.NetAmount = dis.readFloat();
					}

					this.Saleskey = readInteger(dis);

					this.id = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.ProductKey, dos);

				// Integer

				writeInteger(this.CustomerKey, dos);

				// Integer

				writeInteger(this.SupplierKey, dos);

				// Integer

				writeInteger(this.Shipperkey, dos);

				// String

				writeString(this.QuantitySold, dos);

				// String

				writeString(this.TotalAmount, dos);

				// String

				writeString(this.DiscountAmount, dos);

				// Float

				if (this.NetAmount == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.NetAmount);
				}

				// Integer

				writeInteger(this.Saleskey, dos);

				// Integer

				writeInteger(this.id, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Integer

				writeInteger(this.ProductKey, dos);

				// Integer

				writeInteger(this.CustomerKey, dos);

				// Integer

				writeInteger(this.SupplierKey, dos);

				// Integer

				writeInteger(this.Shipperkey, dos);

				// String

				writeString(this.QuantitySold, dos);

				// String

				writeString(this.TotalAmount, dos);

				// String

				writeString(this.DiscountAmount, dos);

				// Float

				if (this.NetAmount == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.NetAmount);
				}

				// Integer

				writeInteger(this.Saleskey, dos);

				// Integer

				writeInteger(this.id, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("ProductKey=" + String.valueOf(ProductKey));
			sb.append(",CustomerKey=" + String.valueOf(CustomerKey));
			sb.append(",SupplierKey=" + String.valueOf(SupplierKey));
			sb.append(",Shipperkey=" + String.valueOf(Shipperkey));
			sb.append(",QuantitySold=" + QuantitySold);
			sb.append(",TotalAmount=" + TotalAmount);
			sb.append(",DiscountAmount=" + DiscountAmount);
			sb.append(",NetAmount=" + String.valueOf(NetAmount));
			sb.append(",Saleskey=" + String.valueOf(Saleskey));
			sb.append(",id=" + String.valueOf(id));
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(DimensionFact1Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class out8Struct implements routines.system.IPersistableRow<out8Struct> {
		final static byte[] commonByteArrayLock_PROJET_TALEND_normalize_data = new byte[0];
		static byte[] commonByteArray_PROJET_TALEND_normalize_data = new byte[0];

		public Integer ProductKey;

		public Integer getProductKey() {
			return this.ProductKey;
		}

		public Integer CustomerKey;

		public Integer getCustomerKey() {
			return this.CustomerKey;
		}

		public Integer SupplierKey;

		public Integer getSupplierKey() {
			return this.SupplierKey;
		}

		public Integer Shipperkey;

		public Integer getShipperkey() {
			return this.Shipperkey;
		}

		public String QuantitySold;

		public String getQuantitySold() {
			return this.QuantitySold;
		}

		public String TotalAmount;

		public String getTotalAmount() {
			return this.TotalAmount;
		}

		public String DiscountAmount;

		public String getDiscountAmount() {
			return this.DiscountAmount;
		}

		public Float NetAmount;

		public Float getNetAmount() {
			return this.NetAmount;
		}

		public Integer Saleskey;

		public Integer getSaleskey() {
			return this.Saleskey;
		}

		public Integer id;

		public Integer getId() {
			return this.id;
		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_PROJET_TALEND_normalize_data.length) {
					if (length < 1024 && commonByteArray_PROJET_TALEND_normalize_data.length == 0) {
						commonByteArray_PROJET_TALEND_normalize_data = new byte[1024];
					} else {
						commonByteArray_PROJET_TALEND_normalize_data = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_PROJET_TALEND_normalize_data, 0, length);
				strReturn = new String(commonByteArray_PROJET_TALEND_normalize_data, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_PROJET_TALEND_normalize_data.length) {
					if (length < 1024 && commonByteArray_PROJET_TALEND_normalize_data.length == 0) {
						commonByteArray_PROJET_TALEND_normalize_data = new byte[1024];
					} else {
						commonByteArray_PROJET_TALEND_normalize_data = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_PROJET_TALEND_normalize_data, 0, length);
				strReturn = new String(commonByteArray_PROJET_TALEND_normalize_data, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_PROJET_TALEND_normalize_data) {

				try {

					int length = 0;

					this.ProductKey = readInteger(dis);

					this.CustomerKey = readInteger(dis);

					this.SupplierKey = readInteger(dis);

					this.Shipperkey = readInteger(dis);

					this.QuantitySold = readString(dis);

					this.TotalAmount = readString(dis);

					this.DiscountAmount = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.NetAmount = null;
					} else {
						this.NetAmount = dis.readFloat();
					}

					this.Saleskey = readInteger(dis);

					this.id = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_PROJET_TALEND_normalize_data) {

				try {

					int length = 0;

					this.ProductKey = readInteger(dis);

					this.CustomerKey = readInteger(dis);

					this.SupplierKey = readInteger(dis);

					this.Shipperkey = readInteger(dis);

					this.QuantitySold = readString(dis);

					this.TotalAmount = readString(dis);

					this.DiscountAmount = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.NetAmount = null;
					} else {
						this.NetAmount = dis.readFloat();
					}

					this.Saleskey = readInteger(dis);

					this.id = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.ProductKey, dos);

				// Integer

				writeInteger(this.CustomerKey, dos);

				// Integer

				writeInteger(this.SupplierKey, dos);

				// Integer

				writeInteger(this.Shipperkey, dos);

				// String

				writeString(this.QuantitySold, dos);

				// String

				writeString(this.TotalAmount, dos);

				// String

				writeString(this.DiscountAmount, dos);

				// Float

				if (this.NetAmount == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.NetAmount);
				}

				// Integer

				writeInteger(this.Saleskey, dos);

				// Integer

				writeInteger(this.id, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Integer

				writeInteger(this.ProductKey, dos);

				// Integer

				writeInteger(this.CustomerKey, dos);

				// Integer

				writeInteger(this.SupplierKey, dos);

				// Integer

				writeInteger(this.Shipperkey, dos);

				// String

				writeString(this.QuantitySold, dos);

				// String

				writeString(this.TotalAmount, dos);

				// String

				writeString(this.DiscountAmount, dos);

				// Float

				if (this.NetAmount == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.NetAmount);
				}

				// Integer

				writeInteger(this.Saleskey, dos);

				// Integer

				writeInteger(this.id, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("ProductKey=" + String.valueOf(ProductKey));
			sb.append(",CustomerKey=" + String.valueOf(CustomerKey));
			sb.append(",SupplierKey=" + String.valueOf(SupplierKey));
			sb.append(",Shipperkey=" + String.valueOf(Shipperkey));
			sb.append(",QuantitySold=" + QuantitySold);
			sb.append(",TotalAmount=" + TotalAmount);
			sb.append(",DiscountAmount=" + DiscountAmount);
			sb.append(",NetAmount=" + String.valueOf(NetAmount));
			sb.append(",Saleskey=" + String.valueOf(Saleskey));
			sb.append(",id=" + String.valueOf(id));
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(out8Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row13Struct implements routines.system.IPersistableRow<row13Struct> {
		final static byte[] commonByteArrayLock_PROJET_TALEND_normalize_data = new byte[0];
		static byte[] commonByteArray_PROJET_TALEND_normalize_data = new byte[0];

		public Integer ProductKey;

		public Integer getProductKey() {
			return this.ProductKey;
		}

		public Integer CustomerKey;

		public Integer getCustomerKey() {
			return this.CustomerKey;
		}

		public Integer SupplierKey;

		public Integer getSupplierKey() {
			return this.SupplierKey;
		}

		public Integer Shipperkey;

		public Integer getShipperkey() {
			return this.Shipperkey;
		}

		public String QuantitySold;

		public String getQuantitySold() {
			return this.QuantitySold;
		}

		public String TotalAmount;

		public String getTotalAmount() {
			return this.TotalAmount;
		}

		public String DiscountAmount;

		public String getDiscountAmount() {
			return this.DiscountAmount;
		}

		public Float NetAmount;

		public Float getNetAmount() {
			return this.NetAmount;
		}

		public Integer Saleskey;

		public Integer getSaleskey() {
			return this.Saleskey;
		}

		public Integer id;

		public Integer getId() {
			return this.id;
		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_PROJET_TALEND_normalize_data.length) {
					if (length < 1024 && commonByteArray_PROJET_TALEND_normalize_data.length == 0) {
						commonByteArray_PROJET_TALEND_normalize_data = new byte[1024];
					} else {
						commonByteArray_PROJET_TALEND_normalize_data = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_PROJET_TALEND_normalize_data, 0, length);
				strReturn = new String(commonByteArray_PROJET_TALEND_normalize_data, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_PROJET_TALEND_normalize_data.length) {
					if (length < 1024 && commonByteArray_PROJET_TALEND_normalize_data.length == 0) {
						commonByteArray_PROJET_TALEND_normalize_data = new byte[1024];
					} else {
						commonByteArray_PROJET_TALEND_normalize_data = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_PROJET_TALEND_normalize_data, 0, length);
				strReturn = new String(commonByteArray_PROJET_TALEND_normalize_data, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_PROJET_TALEND_normalize_data) {

				try {

					int length = 0;

					this.ProductKey = readInteger(dis);

					this.CustomerKey = readInteger(dis);

					this.SupplierKey = readInteger(dis);

					this.Shipperkey = readInteger(dis);

					this.QuantitySold = readString(dis);

					this.TotalAmount = readString(dis);

					this.DiscountAmount = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.NetAmount = null;
					} else {
						this.NetAmount = dis.readFloat();
					}

					this.Saleskey = readInteger(dis);

					this.id = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_PROJET_TALEND_normalize_data) {

				try {

					int length = 0;

					this.ProductKey = readInteger(dis);

					this.CustomerKey = readInteger(dis);

					this.SupplierKey = readInteger(dis);

					this.Shipperkey = readInteger(dis);

					this.QuantitySold = readString(dis);

					this.TotalAmount = readString(dis);

					this.DiscountAmount = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.NetAmount = null;
					} else {
						this.NetAmount = dis.readFloat();
					}

					this.Saleskey = readInteger(dis);

					this.id = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.ProductKey, dos);

				// Integer

				writeInteger(this.CustomerKey, dos);

				// Integer

				writeInteger(this.SupplierKey, dos);

				// Integer

				writeInteger(this.Shipperkey, dos);

				// String

				writeString(this.QuantitySold, dos);

				// String

				writeString(this.TotalAmount, dos);

				// String

				writeString(this.DiscountAmount, dos);

				// Float

				if (this.NetAmount == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.NetAmount);
				}

				// Integer

				writeInteger(this.Saleskey, dos);

				// Integer

				writeInteger(this.id, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Integer

				writeInteger(this.ProductKey, dos);

				// Integer

				writeInteger(this.CustomerKey, dos);

				// Integer

				writeInteger(this.SupplierKey, dos);

				// Integer

				writeInteger(this.Shipperkey, dos);

				// String

				writeString(this.QuantitySold, dos);

				// String

				writeString(this.TotalAmount, dos);

				// String

				writeString(this.DiscountAmount, dos);

				// Float

				if (this.NetAmount == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.NetAmount);
				}

				// Integer

				writeInteger(this.Saleskey, dos);

				// Integer

				writeInteger(this.id, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("ProductKey=" + String.valueOf(ProductKey));
			sb.append(",CustomerKey=" + String.valueOf(CustomerKey));
			sb.append(",SupplierKey=" + String.valueOf(SupplierKey));
			sb.append(",Shipperkey=" + String.valueOf(Shipperkey));
			sb.append(",QuantitySold=" + QuantitySold);
			sb.append(",TotalAmount=" + TotalAmount);
			sb.append(",DiscountAmount=" + DiscountAmount);
			sb.append(",NetAmount=" + String.valueOf(NetAmount));
			sb.append(",Saleskey=" + String.valueOf(Saleskey));
			sb.append(",id=" + String.valueOf(id));
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row13Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class after_tFileInputDelimited_2Struct
			implements routines.system.IPersistableRow<after_tFileInputDelimited_2Struct> {
		final static byte[] commonByteArrayLock_PROJET_TALEND_normalize_data = new byte[0];
		static byte[] commonByteArray_PROJET_TALEND_normalize_data = new byte[0];

		public Integer ProductKey;

		public Integer getProductKey() {
			return this.ProductKey;
		}

		public Integer CustomerKey;

		public Integer getCustomerKey() {
			return this.CustomerKey;
		}

		public Integer SupplierKey;

		public Integer getSupplierKey() {
			return this.SupplierKey;
		}

		public Integer Shipperkey;

		public Integer getShipperkey() {
			return this.Shipperkey;
		}

		public String QuantitySold;

		public String getQuantitySold() {
			return this.QuantitySold;
		}

		public String TotalAmount;

		public String getTotalAmount() {
			return this.TotalAmount;
		}

		public String DiscountAmount;

		public String getDiscountAmount() {
			return this.DiscountAmount;
		}

		public Float NetAmount;

		public Float getNetAmount() {
			return this.NetAmount;
		}

		public Integer Saleskey;

		public Integer getSaleskey() {
			return this.Saleskey;
		}

		public Integer id;

		public Integer getId() {
			return this.id;
		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_PROJET_TALEND_normalize_data.length) {
					if (length < 1024 && commonByteArray_PROJET_TALEND_normalize_data.length == 0) {
						commonByteArray_PROJET_TALEND_normalize_data = new byte[1024];
					} else {
						commonByteArray_PROJET_TALEND_normalize_data = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_PROJET_TALEND_normalize_data, 0, length);
				strReturn = new String(commonByteArray_PROJET_TALEND_normalize_data, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_PROJET_TALEND_normalize_data.length) {
					if (length < 1024 && commonByteArray_PROJET_TALEND_normalize_data.length == 0) {
						commonByteArray_PROJET_TALEND_normalize_data = new byte[1024];
					} else {
						commonByteArray_PROJET_TALEND_normalize_data = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_PROJET_TALEND_normalize_data, 0, length);
				strReturn = new String(commonByteArray_PROJET_TALEND_normalize_data, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_PROJET_TALEND_normalize_data) {

				try {

					int length = 0;

					this.ProductKey = readInteger(dis);

					this.CustomerKey = readInteger(dis);

					this.SupplierKey = readInteger(dis);

					this.Shipperkey = readInteger(dis);

					this.QuantitySold = readString(dis);

					this.TotalAmount = readString(dis);

					this.DiscountAmount = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.NetAmount = null;
					} else {
						this.NetAmount = dis.readFloat();
					}

					this.Saleskey = readInteger(dis);

					this.id = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_PROJET_TALEND_normalize_data) {

				try {

					int length = 0;

					this.ProductKey = readInteger(dis);

					this.CustomerKey = readInteger(dis);

					this.SupplierKey = readInteger(dis);

					this.Shipperkey = readInteger(dis);

					this.QuantitySold = readString(dis);

					this.TotalAmount = readString(dis);

					this.DiscountAmount = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.NetAmount = null;
					} else {
						this.NetAmount = dis.readFloat();
					}

					this.Saleskey = readInteger(dis);

					this.id = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.ProductKey, dos);

				// Integer

				writeInteger(this.CustomerKey, dos);

				// Integer

				writeInteger(this.SupplierKey, dos);

				// Integer

				writeInteger(this.Shipperkey, dos);

				// String

				writeString(this.QuantitySold, dos);

				// String

				writeString(this.TotalAmount, dos);

				// String

				writeString(this.DiscountAmount, dos);

				// Float

				if (this.NetAmount == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.NetAmount);
				}

				// Integer

				writeInteger(this.Saleskey, dos);

				// Integer

				writeInteger(this.id, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Integer

				writeInteger(this.ProductKey, dos);

				// Integer

				writeInteger(this.CustomerKey, dos);

				// Integer

				writeInteger(this.SupplierKey, dos);

				// Integer

				writeInteger(this.Shipperkey, dos);

				// String

				writeString(this.QuantitySold, dos);

				// String

				writeString(this.TotalAmount, dos);

				// String

				writeString(this.DiscountAmount, dos);

				// Float

				if (this.NetAmount == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.NetAmount);
				}

				// Integer

				writeInteger(this.Saleskey, dos);

				// Integer

				writeInteger(this.id, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("ProductKey=" + String.valueOf(ProductKey));
			sb.append(",CustomerKey=" + String.valueOf(CustomerKey));
			sb.append(",SupplierKey=" + String.valueOf(SupplierKey));
			sb.append(",Shipperkey=" + String.valueOf(Shipperkey));
			sb.append(",QuantitySold=" + QuantitySold);
			sb.append(",TotalAmount=" + TotalAmount);
			sb.append(",DiscountAmount=" + DiscountAmount);
			sb.append(",NetAmount=" + String.valueOf(NetAmount));
			sb.append(",Saleskey=" + String.valueOf(Saleskey));
			sb.append(",id=" + String.valueOf(id));
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(after_tFileInputDelimited_2Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public void tFileInputDelimited_2Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tFileInputDelimited_2_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		String iterateId = "";

		String currentComponent = "";
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				tDBInput_2Process(globalMap);
				tDBInput_3Process(globalMap);
				tDBInput_4Process(globalMap);
				tDBInput_5Process(globalMap);

				row13Struct row13 = new row13Struct();
				out8Struct out8 = new out8Struct();
				DimensionFact1Struct DimensionFact1 = new DimensionFact1Struct();

				/**
				 * [tDBOutput_6 begin ] start
				 */

				ok_Hash.put("tDBOutput_6", false);
				start_Hash.put("tDBOutput_6", System.currentTimeMillis());

				currentComponent = "tDBOutput_6";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "DimensionFact1");
				}

				int tos_count_tDBOutput_6 = 0;

				int nb_line_tDBOutput_6 = 0;
				int nb_line_update_tDBOutput_6 = 0;
				int nb_line_inserted_tDBOutput_6 = 0;
				int nb_line_deleted_tDBOutput_6 = 0;
				int nb_line_rejected_tDBOutput_6 = 0;

				int deletedCount_tDBOutput_6 = 0;
				int updatedCount_tDBOutput_6 = 0;
				int insertedCount_tDBOutput_6 = 0;
				int rowsToCommitCount_tDBOutput_6 = 0;
				int rejectedCount_tDBOutput_6 = 0;
				String dbschema_tDBOutput_6 = null;
				String tableName_tDBOutput_6 = null;
				boolean whetherReject_tDBOutput_6 = false;

				java.util.Calendar calendar_tDBOutput_6 = java.util.Calendar.getInstance();
				long year1_tDBOutput_6 = TalendDate.parseDate("yyyy-MM-dd", "0001-01-01").getTime();
				long year2_tDBOutput_6 = TalendDate.parseDate("yyyy-MM-dd", "1753-01-01").getTime();
				long year10000_tDBOutput_6 = TalendDate.parseDate("yyyy-MM-dd HH:mm:ss", "9999-12-31 24:00:00")
						.getTime();
				long date_tDBOutput_6;

				java.util.Calendar calendar_datetimeoffset_tDBOutput_6 = java.util.Calendar
						.getInstance(java.util.TimeZone.getTimeZone("UTC"));

				java.sql.Connection conn_tDBOutput_6 = null;
				String dbUser_tDBOutput_6 = null;
				dbschema_tDBOutput_6 = "dbo";
				String driverClass_tDBOutput_6 = "net.sourceforge.jtds.jdbc.Driver";

				java.lang.Class.forName(driverClass_tDBOutput_6);
				String port_tDBOutput_6 = "1433";
				String dbname_tDBOutput_6 = "med_database";
				String url_tDBOutput_6 = "jdbc:jtds:sqlserver://" + "localhost";
				if (!"".equals(port_tDBOutput_6)) {
					url_tDBOutput_6 += ":" + "1433";
				}
				if (!"".equals(dbname_tDBOutput_6)) {
					url_tDBOutput_6 += "//" + "med_database";

				}
				url_tDBOutput_6 += ";appName=" + projectName + ";" + "";
				dbUser_tDBOutput_6 = "sa";

				final String decryptedPassword_tDBOutput_6 = routines.system.PasswordEncryptUtil.decryptPassword(
						"enc:routine.encryption.key.v1:n9GydAM5caR1EdxJY29KPXziWxOPVAaq5M/Nm/AWe1n7Tvk=");

				String dbPwd_tDBOutput_6 = decryptedPassword_tDBOutput_6;
				conn_tDBOutput_6 = java.sql.DriverManager.getConnection(url_tDBOutput_6, dbUser_tDBOutput_6,
						dbPwd_tDBOutput_6);

				resourceMap.put("conn_tDBOutput_6", conn_tDBOutput_6);

				conn_tDBOutput_6.setAutoCommit(false);
				int commitEvery_tDBOutput_6 = 10000;
				int commitCounter_tDBOutput_6 = 0;

				int batchSize_tDBOutput_6 = 10000;
				int batchSizeCounter_tDBOutput_6 = 0;

				if (dbschema_tDBOutput_6 == null || dbschema_tDBOutput_6.trim().length() == 0) {
					tableName_tDBOutput_6 = "SalesFact";
				} else {
					tableName_tDBOutput_6 = dbschema_tDBOutput_6 + "].[" + "SalesFact";
				}
				int count_tDBOutput_6 = 0;

				boolean whetherExist_tDBOutput_6 = false;
				try (java.sql.Statement isExistStmt_tDBOutput_6 = conn_tDBOutput_6.createStatement()) {
					try {
						isExistStmt_tDBOutput_6.execute("SELECT TOP 1 1 FROM [" + tableName_tDBOutput_6 + "]");
						whetherExist_tDBOutput_6 = true;
					} catch (java.lang.Exception e) {
						globalMap.put("tDBOutput_6_ERROR_MESSAGE", e.getMessage());
						whetherExist_tDBOutput_6 = false;
					}
				}
				if (whetherExist_tDBOutput_6) {
					try (java.sql.Statement stmtDrop_tDBOutput_6 = conn_tDBOutput_6.createStatement()) {
						stmtDrop_tDBOutput_6.execute("DROP TABLE [" + tableName_tDBOutput_6 + "]");
					}
				}
				try (java.sql.Statement stmtCreate_tDBOutput_6 = conn_tDBOutput_6.createStatement()) {
					stmtCreate_tDBOutput_6.execute("CREATE TABLE [" + tableName_tDBOutput_6
							+ "]([ProductKey] INT ,[CustomerKey] INT ,[SupplierKey] INT ,[Shipperkey] INT ,[QuantitySold] VARCHAR(70)  ,[TotalAmount] VARCHAR(70)  ,[DiscountAmount] VARCHAR(70)  ,[NetAmount] REAL ,[Saleskey] INT )");
				}
				String insert_tDBOutput_6 = "INSERT INTO [" + tableName_tDBOutput_6
						+ "] ([ProductKey],[CustomerKey],[SupplierKey],[Shipperkey],[QuantitySold],[TotalAmount],[DiscountAmount],[NetAmount],[Saleskey]) VALUES (?,?,?,?,?,?,?,?,?)";
				java.sql.PreparedStatement pstmt_tDBOutput_6 = conn_tDBOutput_6.prepareStatement(insert_tDBOutput_6);
				resourceMap.put("pstmt_tDBOutput_6", pstmt_tDBOutput_6);

				/**
				 * [tDBOutput_6 begin ] stop
				 */

				/**
				 * [tMap_9 begin ] start
				 */

				ok_Hash.put("tMap_9", false);
				start_Hash.put("tMap_9", System.currentTimeMillis());

				currentComponent = "tMap_9";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "out8");
				}

				int tos_count_tMap_9 = 0;

// ###############################
// # Lookup's keys initialization

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row7Struct> tHash_Lookup_row7 = (org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row7Struct>) ((org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row7Struct>) globalMap
						.get("tHash_Lookup_row7"));

				row7Struct row7HashKey = new row7Struct();
				row7Struct row7Default = new row7Struct();

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row8Struct> tHash_Lookup_row8 = (org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row8Struct>) ((org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row8Struct>) globalMap
						.get("tHash_Lookup_row8"));

				row8Struct row8HashKey = new row8Struct();
				row8Struct row8Default = new row8Struct();

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row9Struct> tHash_Lookup_row9 = (org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row9Struct>) ((org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row9Struct>) globalMap
						.get("tHash_Lookup_row9"));

				row9Struct row9HashKey = new row9Struct();
				row9Struct row9Default = new row9Struct();

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row10Struct> tHash_Lookup_row10 = (org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row10Struct>) ((org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row10Struct>) globalMap
						.get("tHash_Lookup_row10"));

				row10Struct row10HashKey = new row10Struct();
				row10Struct row10Default = new row10Struct();
// ###############################        

// ###############################
// # Vars initialization
				class Var__tMap_9__Struct {
				}
				Var__tMap_9__Struct Var__tMap_9 = new Var__tMap_9__Struct();
// ###############################

// ###############################
// # Outputs initialization
				DimensionFact1Struct DimensionFact1_tmp = new DimensionFact1Struct();
// ###############################

				/**
				 * [tMap_9 begin ] stop
				 */

				/**
				 * [tMap_8 begin ] start
				 */

				ok_Hash.put("tMap_8", false);
				start_Hash.put("tMap_8", System.currentTimeMillis());

				currentComponent = "tMap_8";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row13");
				}

				int tos_count_tMap_8 = 0;

// ###############################
// # Lookup's keys initialization
// ###############################        

// ###############################
// # Vars initialization
				class Var__tMap_8__Struct {
				}
				Var__tMap_8__Struct Var__tMap_8 = new Var__tMap_8__Struct();
// ###############################

// ###############################
// # Outputs initialization
				out8Struct out8_tmp = new out8Struct();
// ###############################

				/**
				 * [tMap_8 begin ] stop
				 */

				/**
				 * [tFileInputDelimited_2 begin ] start
				 */

				ok_Hash.put("tFileInputDelimited_2", false);
				start_Hash.put("tFileInputDelimited_2", System.currentTimeMillis());

				currentComponent = "tFileInputDelimited_2";

				int tos_count_tFileInputDelimited_2 = 0;

				final routines.system.RowState rowstate_tFileInputDelimited_2 = new routines.system.RowState();

				int nb_line_tFileInputDelimited_2 = 0;
				org.talend.fileprocess.FileInputDelimited fid_tFileInputDelimited_2 = null;
				int limit_tFileInputDelimited_2 = -1;
				try {

					Object filename_tFileInputDelimited_2 = "C:/Users/Youcode/Desktop/projet_2025/workspace/newandnew/output/salesfact.csv";
					if (filename_tFileInputDelimited_2 instanceof java.io.InputStream) {

						int footer_value_tFileInputDelimited_2 = 0, random_value_tFileInputDelimited_2 = -1;
						if (footer_value_tFileInputDelimited_2 > 0 || random_value_tFileInputDelimited_2 > 0) {
							throw new java.lang.Exception(
									"When the input source is a stream,footer and random shouldn't be bigger than 0.");
						}

					}
					try {
						fid_tFileInputDelimited_2 = new org.talend.fileprocess.FileInputDelimited(
								"C:/Users/Youcode/Desktop/projet_2025/workspace/newandnew/output/salesfact.csv",
								"ISO-8859-15", ";", "\n", true, 0, 0, limit_tFileInputDelimited_2, -1, false);
					} catch (java.lang.Exception e) {
						globalMap.put("tFileInputDelimited_2_ERROR_MESSAGE", e.getMessage());

						System.err.println(e.getMessage());

					}

					while (fid_tFileInputDelimited_2 != null && fid_tFileInputDelimited_2.nextRecord()) {
						rowstate_tFileInputDelimited_2.reset();

						row13 = null;

						boolean whetherReject_tFileInputDelimited_2 = false;
						row13 = new row13Struct();
						try {

							int columnIndexWithD_tFileInputDelimited_2 = 0;

							String temp = "";

							columnIndexWithD_tFileInputDelimited_2 = 0;

							temp = fid_tFileInputDelimited_2.get(columnIndexWithD_tFileInputDelimited_2);
							if (temp.length() > 0) {

								try {

									row13.ProductKey = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_2) {
									globalMap.put("tFileInputDelimited_2_ERROR_MESSAGE",
											ex_tFileInputDelimited_2.getMessage());
									rowstate_tFileInputDelimited_2.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"ProductKey", "row13", temp, ex_tFileInputDelimited_2),
											ex_tFileInputDelimited_2));
								}

							} else {

								row13.ProductKey = null;

							}

							columnIndexWithD_tFileInputDelimited_2 = 1;

							temp = fid_tFileInputDelimited_2.get(columnIndexWithD_tFileInputDelimited_2);
							if (temp.length() > 0) {

								try {

									row13.CustomerKey = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_2) {
									globalMap.put("tFileInputDelimited_2_ERROR_MESSAGE",
											ex_tFileInputDelimited_2.getMessage());
									rowstate_tFileInputDelimited_2.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"CustomerKey", "row13", temp, ex_tFileInputDelimited_2),
											ex_tFileInputDelimited_2));
								}

							} else {

								row13.CustomerKey = null;

							}

							columnIndexWithD_tFileInputDelimited_2 = 2;

							temp = fid_tFileInputDelimited_2.get(columnIndexWithD_tFileInputDelimited_2);
							if (temp.length() > 0) {

								try {

									row13.SupplierKey = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_2) {
									globalMap.put("tFileInputDelimited_2_ERROR_MESSAGE",
											ex_tFileInputDelimited_2.getMessage());
									rowstate_tFileInputDelimited_2.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"SupplierKey", "row13", temp, ex_tFileInputDelimited_2),
											ex_tFileInputDelimited_2));
								}

							} else {

								row13.SupplierKey = null;

							}

							columnIndexWithD_tFileInputDelimited_2 = 3;

							temp = fid_tFileInputDelimited_2.get(columnIndexWithD_tFileInputDelimited_2);
							if (temp.length() > 0) {

								try {

									row13.Shipperkey = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_2) {
									globalMap.put("tFileInputDelimited_2_ERROR_MESSAGE",
											ex_tFileInputDelimited_2.getMessage());
									rowstate_tFileInputDelimited_2.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"Shipperkey", "row13", temp, ex_tFileInputDelimited_2),
											ex_tFileInputDelimited_2));
								}

							} else {

								row13.Shipperkey = null;

							}

							columnIndexWithD_tFileInputDelimited_2 = 4;

							row13.QuantitySold = fid_tFileInputDelimited_2.get(columnIndexWithD_tFileInputDelimited_2);

							columnIndexWithD_tFileInputDelimited_2 = 5;

							row13.TotalAmount = fid_tFileInputDelimited_2.get(columnIndexWithD_tFileInputDelimited_2);

							columnIndexWithD_tFileInputDelimited_2 = 6;

							row13.DiscountAmount = fid_tFileInputDelimited_2
									.get(columnIndexWithD_tFileInputDelimited_2);

							columnIndexWithD_tFileInputDelimited_2 = 7;

							temp = fid_tFileInputDelimited_2.get(columnIndexWithD_tFileInputDelimited_2);
							if (temp.length() > 0) {

								try {

									row13.NetAmount = ParserUtils.parseTo_Float(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_2) {
									globalMap.put("tFileInputDelimited_2_ERROR_MESSAGE",
											ex_tFileInputDelimited_2.getMessage());
									rowstate_tFileInputDelimited_2.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"NetAmount", "row13", temp, ex_tFileInputDelimited_2),
											ex_tFileInputDelimited_2));
								}

							} else {

								row13.NetAmount = null;

							}

							columnIndexWithD_tFileInputDelimited_2 = 8;

							temp = fid_tFileInputDelimited_2.get(columnIndexWithD_tFileInputDelimited_2);
							if (temp.length() > 0) {

								try {

									row13.Saleskey = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_2) {
									globalMap.put("tFileInputDelimited_2_ERROR_MESSAGE",
											ex_tFileInputDelimited_2.getMessage());
									rowstate_tFileInputDelimited_2.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"Saleskey", "row13", temp, ex_tFileInputDelimited_2),
											ex_tFileInputDelimited_2));
								}

							} else {

								row13.Saleskey = null;

							}

							columnIndexWithD_tFileInputDelimited_2 = 9;

							temp = fid_tFileInputDelimited_2.get(columnIndexWithD_tFileInputDelimited_2);
							if (temp.length() > 0) {

								try {

									row13.id = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_2) {
									globalMap.put("tFileInputDelimited_2_ERROR_MESSAGE",
											ex_tFileInputDelimited_2.getMessage());
									rowstate_tFileInputDelimited_2.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"id", "row13", temp, ex_tFileInputDelimited_2), ex_tFileInputDelimited_2));
								}

							} else {

								row13.id = null;

							}

							if (rowstate_tFileInputDelimited_2.getException() != null) {
								throw rowstate_tFileInputDelimited_2.getException();
							}

						} catch (java.lang.Exception e) {
							globalMap.put("tFileInputDelimited_2_ERROR_MESSAGE", e.getMessage());
							whetherReject_tFileInputDelimited_2 = true;

							System.err.println(e.getMessage());
							row13 = null;

						}

						/**
						 * [tFileInputDelimited_2 begin ] stop
						 */

						/**
						 * [tFileInputDelimited_2 main ] start
						 */

						currentComponent = "tFileInputDelimited_2";

						tos_count_tFileInputDelimited_2++;

						/**
						 * [tFileInputDelimited_2 main ] stop
						 */

						/**
						 * [tFileInputDelimited_2 process_data_begin ] start
						 */

						currentComponent = "tFileInputDelimited_2";

						/**
						 * [tFileInputDelimited_2 process_data_begin ] stop
						 */
// Start of branch "row13"
						if (row13 != null) {

							/**
							 * [tMap_8 main ] start
							 */

							currentComponent = "tMap_8";

							if (execStat) {
								runStat.updateStatOnConnection(iterateId, 1, 1

										, "row13"

								);
							}

							boolean hasCasePrimitiveKeyWithNull_tMap_8 = false;

							// ###############################
							// # Input tables (lookups)
							boolean rejectedInnerJoin_tMap_8 = false;
							boolean mainRowRejected_tMap_8 = false;

							// ###############################
							{ // start of Var scope

								// ###############################
								// # Vars tables

								Var__tMap_8__Struct Var = Var__tMap_8;// ###############################
								// ###############################
								// # Output tables

								out8 = null;

// # Output table : 'out8'
								out8_tmp.ProductKey = row13.ProductKey;
								out8_tmp.CustomerKey = row13.CustomerKey;
								out8_tmp.SupplierKey = row13.SupplierKey;
								out8_tmp.Shipperkey = row13.Shipperkey;
								out8_tmp.QuantitySold = row13.QuantitySold;
								out8_tmp.TotalAmount = row13.TotalAmount;
								out8_tmp.DiscountAmount = row13.DiscountAmount;
								out8_tmp.NetAmount = row13.NetAmount;
								out8_tmp.Saleskey = row13.Saleskey;
								out8_tmp.id = row13.id;
								out8 = out8_tmp;
// ###############################

							} // end of Var scope

							rejectedInnerJoin_tMap_8 = false;

							tos_count_tMap_8++;

							/**
							 * [tMap_8 main ] stop
							 */

							/**
							 * [tMap_8 process_data_begin ] start
							 */

							currentComponent = "tMap_8";

							/**
							 * [tMap_8 process_data_begin ] stop
							 */
// Start of branch "out8"
							if (out8 != null) {

								/**
								 * [tMap_9 main ] start
								 */

								currentComponent = "tMap_9";

								if (execStat) {
									runStat.updateStatOnConnection(iterateId, 1, 1

											, "out8"

									);
								}

								boolean hasCasePrimitiveKeyWithNull_tMap_9 = false;

								// ###############################
								// # Input tables (lookups)
								boolean rejectedInnerJoin_tMap_9 = false;
								boolean mainRowRejected_tMap_9 = false;

								///////////////////////////////////////////////
								// Starting Lookup Table "row7"
								///////////////////////////////////////////////

								boolean forceLooprow7 = false;

								row7Struct row7ObjectFromLookup = null;

								if (!rejectedInnerJoin_tMap_9) { // G_TM_M_020

									hasCasePrimitiveKeyWithNull_tMap_9 = false;

									row7HashKey.ShipperKey = out8.Shipperkey;

									row7HashKey.hashCodeDirty = true;

									tHash_Lookup_row7.lookup(row7HashKey);

								} // G_TM_M_020

								if (tHash_Lookup_row7 != null && tHash_Lookup_row7.getCount(row7HashKey) > 1) { // G 071

									// System.out.println("WARNING: UNIQUE MATCH is configured for the lookup 'row7'
									// and it contains more one result from keys : row7.ShipperKey = '" +
									// row7HashKey.ShipperKey + "'");
								} // G 071

								row7Struct row7 = null;

								row7Struct fromLookup_row7 = null;
								row7 = row7Default;

								if (tHash_Lookup_row7 != null && tHash_Lookup_row7.hasNext()) { // G 099

									fromLookup_row7 = tHash_Lookup_row7.next();

								} // G 099

								if (fromLookup_row7 != null) {
									row7 = fromLookup_row7;
								}

								///////////////////////////////////////////////
								// Starting Lookup Table "row8"
								///////////////////////////////////////////////

								boolean forceLooprow8 = false;

								row8Struct row8ObjectFromLookup = null;

								if (!rejectedInnerJoin_tMap_9) { // G_TM_M_020

									hasCasePrimitiveKeyWithNull_tMap_9 = false;

									row8HashKey.ProductKey = out8.ProductKey;

									row8HashKey.hashCodeDirty = true;

									tHash_Lookup_row8.lookup(row8HashKey);

								} // G_TM_M_020

								if (tHash_Lookup_row8 != null && tHash_Lookup_row8.getCount(row8HashKey) > 1) { // G 071

									// System.out.println("WARNING: UNIQUE MATCH is configured for the lookup 'row8'
									// and it contains more one result from keys : row8.ProductKey = '" +
									// row8HashKey.ProductKey + "'");
								} // G 071

								row8Struct row8 = null;

								row8Struct fromLookup_row8 = null;
								row8 = row8Default;

								if (tHash_Lookup_row8 != null && tHash_Lookup_row8.hasNext()) { // G 099

									fromLookup_row8 = tHash_Lookup_row8.next();

								} // G 099

								if (fromLookup_row8 != null) {
									row8 = fromLookup_row8;
								}

								///////////////////////////////////////////////
								// Starting Lookup Table "row9"
								///////////////////////////////////////////////

								boolean forceLooprow9 = false;

								row9Struct row9ObjectFromLookup = null;

								if (!rejectedInnerJoin_tMap_9) { // G_TM_M_020

									hasCasePrimitiveKeyWithNull_tMap_9 = false;

									row9HashKey.CustomerKey = out8.CustomerKey;

									row9HashKey.hashCodeDirty = true;

									tHash_Lookup_row9.lookup(row9HashKey);

								} // G_TM_M_020

								if (tHash_Lookup_row9 != null && tHash_Lookup_row9.getCount(row9HashKey) > 1) { // G 071

									// System.out.println("WARNING: UNIQUE MATCH is configured for the lookup 'row9'
									// and it contains more one result from keys : row9.CustomerKey = '" +
									// row9HashKey.CustomerKey + "'");
								} // G 071

								row9Struct row9 = null;

								row9Struct fromLookup_row9 = null;
								row9 = row9Default;

								if (tHash_Lookup_row9 != null && tHash_Lookup_row9.hasNext()) { // G 099

									fromLookup_row9 = tHash_Lookup_row9.next();

								} // G 099

								if (fromLookup_row9 != null) {
									row9 = fromLookup_row9;
								}

								///////////////////////////////////////////////
								// Starting Lookup Table "row10"
								///////////////////////////////////////////////

								boolean forceLooprow10 = false;

								row10Struct row10ObjectFromLookup = null;

								if (!rejectedInnerJoin_tMap_9) { // G_TM_M_020

									hasCasePrimitiveKeyWithNull_tMap_9 = false;

									row10HashKey.SupplierKey = out8.SupplierKey;

									row10HashKey.hashCodeDirty = true;

									tHash_Lookup_row10.lookup(row10HashKey);

								} // G_TM_M_020

								if (tHash_Lookup_row10 != null && tHash_Lookup_row10.getCount(row10HashKey) > 1) { // G
																													// 071

									// System.out.println("WARNING: UNIQUE MATCH is configured for the lookup
									// 'row10' and it contains more one result from keys : row10.SupplierKey = '" +
									// row10HashKey.SupplierKey + "'");
								} // G 071

								row10Struct row10 = null;

								row10Struct fromLookup_row10 = null;
								row10 = row10Default;

								if (tHash_Lookup_row10 != null && tHash_Lookup_row10.hasNext()) { // G 099

									fromLookup_row10 = tHash_Lookup_row10.next();

								} // G 099

								if (fromLookup_row10 != null) {
									row10 = fromLookup_row10;
								}

								// ###############################
								{ // start of Var scope

									// ###############################
									// # Vars tables

									Var__tMap_9__Struct Var = Var__tMap_9;// ###############################
									// ###############################
									// # Output tables

									DimensionFact1 = null;

// # Output table : 'DimensionFact1'
									DimensionFact1_tmp.ProductKey = out8.ProductKey;
									DimensionFact1_tmp.CustomerKey = out8.CustomerKey;
									DimensionFact1_tmp.SupplierKey = out8.SupplierKey;
									DimensionFact1_tmp.Shipperkey = out8.Shipperkey;
									DimensionFact1_tmp.QuantitySold = out8.QuantitySold;
									DimensionFact1_tmp.TotalAmount = out8.TotalAmount;
									DimensionFact1_tmp.DiscountAmount = out8.DiscountAmount;
									DimensionFact1_tmp.NetAmount = out8.NetAmount;
									DimensionFact1_tmp.Saleskey = out8.Saleskey;
									DimensionFact1_tmp.id = out8.id;
									DimensionFact1 = DimensionFact1_tmp;
// ###############################

								} // end of Var scope

								rejectedInnerJoin_tMap_9 = false;

								tos_count_tMap_9++;

								/**
								 * [tMap_9 main ] stop
								 */

								/**
								 * [tMap_9 process_data_begin ] start
								 */

								currentComponent = "tMap_9";

								/**
								 * [tMap_9 process_data_begin ] stop
								 */
// Start of branch "DimensionFact1"
								if (DimensionFact1 != null) {

									/**
									 * [tDBOutput_6 main ] start
									 */

									currentComponent = "tDBOutput_6";

									if (execStat) {
										runStat.updateStatOnConnection(iterateId, 1, 1

												, "DimensionFact1"

										);
									}

									whetherReject_tDBOutput_6 = false;
									if (DimensionFact1.ProductKey == null) {
										pstmt_tDBOutput_6.setNull(1, java.sql.Types.INTEGER);
									} else {
										pstmt_tDBOutput_6.setInt(1, DimensionFact1.ProductKey);
									}

									if (DimensionFact1.CustomerKey == null) {
										pstmt_tDBOutput_6.setNull(2, java.sql.Types.INTEGER);
									} else {
										pstmt_tDBOutput_6.setInt(2, DimensionFact1.CustomerKey);
									}

									if (DimensionFact1.SupplierKey == null) {
										pstmt_tDBOutput_6.setNull(3, java.sql.Types.INTEGER);
									} else {
										pstmt_tDBOutput_6.setInt(3, DimensionFact1.SupplierKey);
									}

									if (DimensionFact1.Shipperkey == null) {
										pstmt_tDBOutput_6.setNull(4, java.sql.Types.INTEGER);
									} else {
										pstmt_tDBOutput_6.setInt(4, DimensionFact1.Shipperkey);
									}

									if (DimensionFact1.QuantitySold == null) {
										pstmt_tDBOutput_6.setNull(5, java.sql.Types.VARCHAR);
									} else {
										pstmt_tDBOutput_6.setString(5, DimensionFact1.QuantitySold);
									}

									if (DimensionFact1.TotalAmount == null) {
										pstmt_tDBOutput_6.setNull(6, java.sql.Types.VARCHAR);
									} else {
										pstmt_tDBOutput_6.setString(6, DimensionFact1.TotalAmount);
									}

									if (DimensionFact1.DiscountAmount == null) {
										pstmt_tDBOutput_6.setNull(7, java.sql.Types.VARCHAR);
									} else {
										pstmt_tDBOutput_6.setString(7, DimensionFact1.DiscountAmount);
									}

									if (DimensionFact1.NetAmount == null) {
										pstmt_tDBOutput_6.setNull(8, java.sql.Types.FLOAT);
									} else {
										pstmt_tDBOutput_6.setFloat(8, DimensionFact1.NetAmount);
									}

									if (DimensionFact1.Saleskey == null) {
										pstmt_tDBOutput_6.setNull(9, java.sql.Types.INTEGER);
									} else {
										pstmt_tDBOutput_6.setInt(9, DimensionFact1.Saleskey);
									}

									pstmt_tDBOutput_6.addBatch();
									nb_line_tDBOutput_6++;

									batchSizeCounter_tDBOutput_6++;

									////////// batch execute by batch size///////
									class LimitBytesHelper_tDBOutput_6 {
										public int limitBytePart1(int counter,
												java.sql.PreparedStatement pstmt_tDBOutput_6) throws Exception {
											try {

												for (int countEach_tDBOutput_6 : pstmt_tDBOutput_6.executeBatch()) {
													if (countEach_tDBOutput_6 == -2 || countEach_tDBOutput_6 == -3) {
														break;
													}
													counter += countEach_tDBOutput_6;
												}

											} catch (java.sql.BatchUpdateException e) {
												globalMap.put("tDBOutput_6_ERROR_MESSAGE", e.getMessage());

												int countSum_tDBOutput_6 = 0;
												for (int countEach_tDBOutput_6 : e.getUpdateCounts()) {
													counter += (countEach_tDBOutput_6 < 0 ? 0 : countEach_tDBOutput_6);
												}

												System.err.println(e.getMessage());

											}
											return counter;
										}

										public int limitBytePart2(int counter,
												java.sql.PreparedStatement pstmt_tDBOutput_6) throws Exception {
											try {

												for (int countEach_tDBOutput_6 : pstmt_tDBOutput_6.executeBatch()) {
													if (countEach_tDBOutput_6 == -2 || countEach_tDBOutput_6 == -3) {
														break;
													}
													counter += countEach_tDBOutput_6;
												}

											} catch (java.sql.BatchUpdateException e) {
												globalMap.put("tDBOutput_6_ERROR_MESSAGE", e.getMessage());

												for (int countEach_tDBOutput_6 : e.getUpdateCounts()) {
													counter += (countEach_tDBOutput_6 < 0 ? 0 : countEach_tDBOutput_6);
												}

												System.err.println(e.getMessage());

											}
											return counter;
										}
									}
									if ((batchSize_tDBOutput_6 > 0)
											&& (batchSize_tDBOutput_6 <= batchSizeCounter_tDBOutput_6)) {

										insertedCount_tDBOutput_6 = new LimitBytesHelper_tDBOutput_6()
												.limitBytePart1(insertedCount_tDBOutput_6, pstmt_tDBOutput_6);
										rowsToCommitCount_tDBOutput_6 = insertedCount_tDBOutput_6;

										batchSizeCounter_tDBOutput_6 = 0;
									}

									//////////// commit every////////////

									commitCounter_tDBOutput_6++;
									if (commitEvery_tDBOutput_6 <= commitCounter_tDBOutput_6) {
										if ((batchSize_tDBOutput_6 > 0) && (batchSizeCounter_tDBOutput_6 > 0)) {

											insertedCount_tDBOutput_6 = new LimitBytesHelper_tDBOutput_6()
													.limitBytePart1(insertedCount_tDBOutput_6, pstmt_tDBOutput_6);

											batchSizeCounter_tDBOutput_6 = 0;
										}
										if (rowsToCommitCount_tDBOutput_6 != 0) {

										}
										conn_tDBOutput_6.commit();
										if (rowsToCommitCount_tDBOutput_6 != 0) {

											rowsToCommitCount_tDBOutput_6 = 0;
										}
										commitCounter_tDBOutput_6 = 0;
									}

									tos_count_tDBOutput_6++;

									/**
									 * [tDBOutput_6 main ] stop
									 */

									/**
									 * [tDBOutput_6 process_data_begin ] start
									 */

									currentComponent = "tDBOutput_6";

									/**
									 * [tDBOutput_6 process_data_begin ] stop
									 */

									/**
									 * [tDBOutput_6 process_data_end ] start
									 */

									currentComponent = "tDBOutput_6";

									/**
									 * [tDBOutput_6 process_data_end ] stop
									 */

								} // End of branch "DimensionFact1"

								/**
								 * [tMap_9 process_data_end ] start
								 */

								currentComponent = "tMap_9";

								/**
								 * [tMap_9 process_data_end ] stop
								 */

							} // End of branch "out8"

							/**
							 * [tMap_8 process_data_end ] start
							 */

							currentComponent = "tMap_8";

							/**
							 * [tMap_8 process_data_end ] stop
							 */

						} // End of branch "row13"

						/**
						 * [tFileInputDelimited_2 process_data_end ] start
						 */

						currentComponent = "tFileInputDelimited_2";

						/**
						 * [tFileInputDelimited_2 process_data_end ] stop
						 */

						/**
						 * [tFileInputDelimited_2 end ] start
						 */

						currentComponent = "tFileInputDelimited_2";

					}
				} finally {
					if (!((Object) ("C:/Users/Youcode/Desktop/projet_2025/workspace/newandnew/output/salesfact.csv") instanceof java.io.InputStream)) {
						if (fid_tFileInputDelimited_2 != null) {
							fid_tFileInputDelimited_2.close();
						}
					}
					if (fid_tFileInputDelimited_2 != null) {
						globalMap.put("tFileInputDelimited_2_NB_LINE", fid_tFileInputDelimited_2.getRowNumber());

					}
				}

				ok_Hash.put("tFileInputDelimited_2", true);
				end_Hash.put("tFileInputDelimited_2", System.currentTimeMillis());

				/**
				 * [tFileInputDelimited_2 end ] stop
				 */

				/**
				 * [tMap_8 end ] start
				 */

				currentComponent = "tMap_8";

// ###############################
// # Lookup hashes releasing
// ###############################      

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row13");
				}

				ok_Hash.put("tMap_8", true);
				end_Hash.put("tMap_8", System.currentTimeMillis());

				/**
				 * [tMap_8 end ] stop
				 */

				/**
				 * [tMap_9 end ] start
				 */

				currentComponent = "tMap_9";

// ###############################
// # Lookup hashes releasing
				if (tHash_Lookup_row7 != null) {
					tHash_Lookup_row7.endGet();
				}
				globalMap.remove("tHash_Lookup_row7");

				if (tHash_Lookup_row8 != null) {
					tHash_Lookup_row8.endGet();
				}
				globalMap.remove("tHash_Lookup_row8");

				if (tHash_Lookup_row9 != null) {
					tHash_Lookup_row9.endGet();
				}
				globalMap.remove("tHash_Lookup_row9");

				if (tHash_Lookup_row10 != null) {
					tHash_Lookup_row10.endGet();
				}
				globalMap.remove("tHash_Lookup_row10");

// ###############################      

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "out8");
				}

				ok_Hash.put("tMap_9", true);
				end_Hash.put("tMap_9", System.currentTimeMillis());

				/**
				 * [tMap_9 end ] stop
				 */

				/**
				 * [tDBOutput_6 end ] start
				 */

				currentComponent = "tDBOutput_6";

				try {
					int countSum_tDBOutput_6 = 0;
					if (pstmt_tDBOutput_6 != null && batchSizeCounter_tDBOutput_6 > 0) {

						for (int countEach_tDBOutput_6 : pstmt_tDBOutput_6.executeBatch()) {
							if (countEach_tDBOutput_6 == -2 || countEach_tDBOutput_6 == -3) {
								break;
							}
							countSum_tDBOutput_6 += countEach_tDBOutput_6;
						}
						rowsToCommitCount_tDBOutput_6 += countSum_tDBOutput_6;

					}

					insertedCount_tDBOutput_6 += countSum_tDBOutput_6;

				} catch (java.sql.BatchUpdateException e) {
					globalMap.put("tDBOutput_6_ERROR_MESSAGE", e.getMessage());

					int countSum_tDBOutput_6 = 0;
					for (int countEach_tDBOutput_6 : e.getUpdateCounts()) {
						countSum_tDBOutput_6 += (countEach_tDBOutput_6 < 0 ? 0 : countEach_tDBOutput_6);
					}
					rowsToCommitCount_tDBOutput_6 += countSum_tDBOutput_6;

					insertedCount_tDBOutput_6 += countSum_tDBOutput_6;

					System.err.println(e.getMessage());

				}
				if (pstmt_tDBOutput_6 != null) {

					pstmt_tDBOutput_6.close();
					resourceMap.remove("pstmt_tDBOutput_6");

				}
				resourceMap.put("statementClosed_tDBOutput_6", true);
				if (rowsToCommitCount_tDBOutput_6 != 0) {

				}
				conn_tDBOutput_6.commit();
				if (rowsToCommitCount_tDBOutput_6 != 0) {

					rowsToCommitCount_tDBOutput_6 = 0;
				}
				commitCounter_tDBOutput_6 = 0;
				conn_tDBOutput_6.close();
				resourceMap.put("finish_tDBOutput_6", true);

				nb_line_deleted_tDBOutput_6 = nb_line_deleted_tDBOutput_6 + deletedCount_tDBOutput_6;
				nb_line_update_tDBOutput_6 = nb_line_update_tDBOutput_6 + updatedCount_tDBOutput_6;
				nb_line_inserted_tDBOutput_6 = nb_line_inserted_tDBOutput_6 + insertedCount_tDBOutput_6;
				nb_line_rejected_tDBOutput_6 = nb_line_rejected_tDBOutput_6 + rejectedCount_tDBOutput_6;

				globalMap.put("tDBOutput_6_NB_LINE", nb_line_tDBOutput_6);
				globalMap.put("tDBOutput_6_NB_LINE_UPDATED", nb_line_update_tDBOutput_6);
				globalMap.put("tDBOutput_6_NB_LINE_INSERTED", nb_line_inserted_tDBOutput_6);
				globalMap.put("tDBOutput_6_NB_LINE_DELETED", nb_line_deleted_tDBOutput_6);
				globalMap.put("tDBOutput_6_NB_LINE_REJECTED", nb_line_rejected_tDBOutput_6);

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "DimensionFact1");
				}

				ok_Hash.put("tDBOutput_6", true);
				end_Hash.put("tDBOutput_6", System.currentTimeMillis());

				/**
				 * [tDBOutput_6 end ] stop
				 */

			} // end the resume

		} catch (java.lang.Exception e) {

			TalendException te = new TalendException(e, currentComponent, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			// free memory for "tMap_9"
			globalMap.remove("tHash_Lookup_row7");

			// free memory for "tMap_9"
			globalMap.remove("tHash_Lookup_row8");

			// free memory for "tMap_9"
			globalMap.remove("tHash_Lookup_row9");

			// free memory for "tMap_9"
			globalMap.remove("tHash_Lookup_row10");

			try {

				/**
				 * [tFileInputDelimited_2 finally ] start
				 */

				currentComponent = "tFileInputDelimited_2";

				/**
				 * [tFileInputDelimited_2 finally ] stop
				 */

				/**
				 * [tMap_8 finally ] start
				 */

				currentComponent = "tMap_8";

				/**
				 * [tMap_8 finally ] stop
				 */

				/**
				 * [tMap_9 finally ] start
				 */

				currentComponent = "tMap_9";

				/**
				 * [tMap_9 finally ] stop
				 */

				/**
				 * [tDBOutput_6 finally ] start
				 */

				currentComponent = "tDBOutput_6";

				try {
					if (resourceMap.get("statementClosed_tDBOutput_6") == null) {
						java.sql.PreparedStatement pstmtToClose_tDBOutput_6 = null;
						if ((pstmtToClose_tDBOutput_6 = (java.sql.PreparedStatement) resourceMap
								.remove("pstmt_tDBOutput_6")) != null) {
							pstmtToClose_tDBOutput_6.close();
						}
					}
				} finally {
					if (resourceMap.get("finish_tDBOutput_6") == null) {
						java.sql.Connection ctn_tDBOutput_6 = null;
						if ((ctn_tDBOutput_6 = (java.sql.Connection) resourceMap.get("conn_tDBOutput_6")) != null) {
							try {
								ctn_tDBOutput_6.close();
							} catch (java.sql.SQLException sqlEx_tDBOutput_6) {
								String errorMessage_tDBOutput_6 = "failed to close the connection in tDBOutput_6 :"
										+ sqlEx_tDBOutput_6.getMessage();
								System.err.println(errorMessage_tDBOutput_6);
							}
						}
					}
				}

				/**
				 * [tDBOutput_6 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tFileInputDelimited_2_SUBPROCESS_STATE", 1);
	}

	public static class row7Struct implements routines.system.IPersistableComparableLookupRow<row7Struct> {
		final static byte[] commonByteArrayLock_PROJET_TALEND_normalize_data = new byte[0];
		static byte[] commonByteArray_PROJET_TALEND_normalize_data = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public Integer ShipperKey;

		public Integer getShipperKey() {
			return this.ShipperKey;
		}

		public Integer shipping_id;

		public Integer getShipping_id() {
			return this.shipping_id;
		}

		public String ShipperName;

		public String getShipperName() {
			return this.ShipperName;
		}

		public String ShippingMethod;

		public String getShippingMethod() {
			return this.ShippingMethod;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.ShipperKey == null) ? 0 : this.ShipperKey.hashCode());

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final row7Struct other = (row7Struct) obj;

			if (this.ShipperKey == null) {
				if (other.ShipperKey != null)
					return false;

			} else if (!this.ShipperKey.equals(other.ShipperKey))

				return false;

			return true;
		}

		public void copyDataTo(row7Struct other) {

			other.ShipperKey = this.ShipperKey;
			other.shipping_id = this.shipping_id;
			other.ShipperName = this.ShipperName;
			other.ShippingMethod = this.ShippingMethod;

		}

		public void copyKeysDataTo(row7Struct other) {

			other.ShipperKey = this.ShipperKey;

		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		private Integer readInteger(DataInputStream dis, ObjectInputStream ois) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(DataInputStream dis, org.jboss.marshalling.Unmarshaller unmarshaller)
				throws IOException {
			Integer intReturn;
			int length = 0;
			length = unmarshaller.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = unmarshaller.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, DataOutputStream dos, ObjectOutputStream oos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, DataOutputStream dos, org.jboss.marshalling.Marshaller marshaller)
				throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		private String readString(DataInputStream dis, ObjectInputStream ois) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				byte[] byteArray = new byte[length];
				dis.read(byteArray);
				strReturn = new String(byteArray, utf8Charset);
			}
			return strReturn;
		}

		private String readString(DataInputStream dis, org.jboss.marshalling.Unmarshaller unmarshaller)
				throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				byte[] byteArray = new byte[length];
				unmarshaller.read(byteArray);
				strReturn = new String(byteArray, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, DataOutputStream dos, org.jboss.marshalling.Marshaller marshaller)
				throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		private void writeString(String str, DataOutputStream dos, ObjectOutputStream oos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		public void readKeysData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_PROJET_TALEND_normalize_data) {

				try {

					int length = 0;

					this.ShipperKey = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readKeysData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_PROJET_TALEND_normalize_data) {

				try {

					int length = 0;

					this.ShipperKey = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeKeysData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.ShipperKey, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeKeysData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Integer

				writeInteger(this.ShipperKey, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		/**
		 * Fill Values data by reading ObjectInputStream.
		 */
		public void readValuesData(DataInputStream dis, ObjectInputStream ois) {
			try {

				int length = 0;

				this.shipping_id = readInteger(dis, ois);

				this.ShipperName = readString(dis, ois);

				this.ShippingMethod = readString(dis, ois);

			} catch (IOException e) {
				throw new RuntimeException(e);

			}

		}

		public void readValuesData(DataInputStream dis, org.jboss.marshalling.Unmarshaller objectIn) {
			try {
				int length = 0;

				this.shipping_id = readInteger(dis, objectIn);

				this.ShipperName = readString(dis, objectIn);

				this.ShippingMethod = readString(dis, objectIn);

			} catch (IOException e) {
				throw new RuntimeException(e);

			}

		}

		/**
		 * Return a byte array which represents Values data.
		 */
		public void writeValuesData(DataOutputStream dos, ObjectOutputStream oos) {
			try {

				writeInteger(this.shipping_id, dos, oos);

				writeString(this.ShipperName, dos, oos);

				writeString(this.ShippingMethod, dos, oos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeValuesData(DataOutputStream dos, org.jboss.marshalling.Marshaller objectOut) {
			try {

				writeInteger(this.shipping_id, dos, objectOut);

				writeString(this.ShipperName, dos, objectOut);

				writeString(this.ShippingMethod, dos, objectOut);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		public boolean supportMarshaller() {
			return true;
		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("ShipperKey=" + String.valueOf(ShipperKey));
			sb.append(",shipping_id=" + String.valueOf(shipping_id));
			sb.append(",ShipperName=" + ShipperName);
			sb.append(",ShippingMethod=" + ShippingMethod);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row7Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.ShipperKey, other.ShipperKey);
			if (returnValue != 0) {
				return returnValue;
			}

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public void tDBInput_2Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tDBInput_2_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		String iterateId = "";

		String currentComponent = "";
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				row7Struct row7 = new row7Struct();

				/**
				 * [tAdvancedHash_row7 begin ] start
				 */

				ok_Hash.put("tAdvancedHash_row7", false);
				start_Hash.put("tAdvancedHash_row7", System.currentTimeMillis());

				currentComponent = "tAdvancedHash_row7";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row7");
				}

				int tos_count_tAdvancedHash_row7 = 0;

				// connection name:row7
				// source node:tDBInput_2 - inputs:(after_tFileInputDelimited_2)
				// outputs:(row7,row7) | target node:tAdvancedHash_row7 - inputs:(row7)
				// outputs:()
				// linked node: tMap_9 - inputs:(out8,row7,row8,row9,row10)
				// outputs:(DimensionFact1)

				org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE matchingModeEnum_row7 = org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE.UNIQUE_MATCH;

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row7Struct> tHash_Lookup_row7 = org.talend.designer.components.lookup.memory.AdvancedMemoryLookup
						.<row7Struct>getLookup(matchingModeEnum_row7);

				globalMap.put("tHash_Lookup_row7", tHash_Lookup_row7);

				/**
				 * [tAdvancedHash_row7 begin ] stop
				 */

				/**
				 * [tDBInput_2 begin ] start
				 */

				ok_Hash.put("tDBInput_2", false);
				start_Hash.put("tDBInput_2", System.currentTimeMillis());

				currentComponent = "tDBInput_2";

				int tos_count_tDBInput_2 = 0;

				org.talend.designer.components.util.mssql.MSSqlGenerateTimestampUtil mssqlGTU_tDBInput_2 = org.talend.designer.components.util.mssql.MSSqlUtilFactory
						.getMSSqlGenerateTimestampUtil();

				java.util.List<String> talendToDBList_tDBInput_2 = new java.util.ArrayList();
				String[] talendToDBArray_tDBInput_2 = new String[] { "FLOAT", "NUMERIC", "NUMERIC IDENTITY", "DECIMAL",
						"DECIMAL IDENTITY", "REAL" };
				java.util.Collections.addAll(talendToDBList_tDBInput_2, talendToDBArray_tDBInput_2);
				int nb_line_tDBInput_2 = 0;
				java.sql.Connection conn_tDBInput_2 = null;
				String driverClass_tDBInput_2 = "net.sourceforge.jtds.jdbc.Driver";
				java.lang.Class jdbcclazz_tDBInput_2 = java.lang.Class.forName(driverClass_tDBInput_2);
				String dbUser_tDBInput_2 = "sa";

				final String decryptedPassword_tDBInput_2 = routines.system.PasswordEncryptUtil.decryptPassword(
						"enc:routine.encryption.key.v1:dIqSz8nRPWHUkgQPOHXV9EYCJBrCdyoQiMy+snkpiqVmppg=");

				String dbPwd_tDBInput_2 = decryptedPassword_tDBInput_2;

				String port_tDBInput_2 = "1433";
				String dbname_tDBInput_2 = "med_database";
				String url_tDBInput_2 = "jdbc:jtds:sqlserver://" + "localhost";
				if (!"".equals(port_tDBInput_2)) {
					url_tDBInput_2 += ":" + "1433";
				}
				if (!"".equals(dbname_tDBInput_2)) {
					url_tDBInput_2 += "//" + "med_database";
				}
				url_tDBInput_2 += ";appName=" + projectName + ";" + "";
				String dbschema_tDBInput_2 = "dbo";

				conn_tDBInput_2 = java.sql.DriverManager.getConnection(url_tDBInput_2, dbUser_tDBInput_2,
						dbPwd_tDBInput_2);

				java.sql.Statement stmt_tDBInput_2 = conn_tDBInput_2.createStatement();

				String dbquery_tDBInput_2 = "SELECT dbo.ShipperDimension.shipping_id,\n		dbo.ShipperDimension.ShipperKey,\n		dbo.ShipperDimension.ShipperName,\n		dbo.S"
						+ "hipperDimension.ShippingMethod\nFROM	dbo.ShipperDimension";

				globalMap.put("tDBInput_2_QUERY", dbquery_tDBInput_2);
				java.sql.ResultSet rs_tDBInput_2 = null;

				try {
					rs_tDBInput_2 = stmt_tDBInput_2.executeQuery(dbquery_tDBInput_2);
					java.sql.ResultSetMetaData rsmd_tDBInput_2 = rs_tDBInput_2.getMetaData();
					int colQtyInRs_tDBInput_2 = rsmd_tDBInput_2.getColumnCount();

					String tmpContent_tDBInput_2 = null;

					while (rs_tDBInput_2.next()) {
						nb_line_tDBInput_2++;

						if (colQtyInRs_tDBInput_2 < 1) {
							row7.ShipperKey = null;
						} else {

							row7.ShipperKey = rs_tDBInput_2.getInt(1);
							if (rs_tDBInput_2.wasNull()) {
								row7.ShipperKey = null;
							}
						}
						if (colQtyInRs_tDBInput_2 < 2) {
							row7.shipping_id = null;
						} else {

							row7.shipping_id = rs_tDBInput_2.getInt(2);
							if (rs_tDBInput_2.wasNull()) {
								row7.shipping_id = null;
							}
						}
						if (colQtyInRs_tDBInput_2 < 3) {
							row7.ShipperName = null;
						} else {

							tmpContent_tDBInput_2 = rs_tDBInput_2.getString(3);
							if (tmpContent_tDBInput_2 != null) {
								if (talendToDBList_tDBInput_2.contains(
										rsmd_tDBInput_2.getColumnTypeName(3).toUpperCase(java.util.Locale.ENGLISH))) {
									row7.ShipperName = FormatterUtils.formatUnwithE(tmpContent_tDBInput_2);
								} else {
									row7.ShipperName = tmpContent_tDBInput_2;
								}
							} else {
								row7.ShipperName = null;
							}
						}
						if (colQtyInRs_tDBInput_2 < 4) {
							row7.ShippingMethod = null;
						} else {

							tmpContent_tDBInput_2 = rs_tDBInput_2.getString(4);
							if (tmpContent_tDBInput_2 != null) {
								if (talendToDBList_tDBInput_2.contains(
										rsmd_tDBInput_2.getColumnTypeName(4).toUpperCase(java.util.Locale.ENGLISH))) {
									row7.ShippingMethod = FormatterUtils.formatUnwithE(tmpContent_tDBInput_2);
								} else {
									row7.ShippingMethod = tmpContent_tDBInput_2;
								}
							} else {
								row7.ShippingMethod = null;
							}
						}

						/**
						 * [tDBInput_2 begin ] stop
						 */

						/**
						 * [tDBInput_2 main ] start
						 */

						currentComponent = "tDBInput_2";

						tos_count_tDBInput_2++;

						/**
						 * [tDBInput_2 main ] stop
						 */

						/**
						 * [tDBInput_2 process_data_begin ] start
						 */

						currentComponent = "tDBInput_2";

						/**
						 * [tDBInput_2 process_data_begin ] stop
						 */

						/**
						 * [tAdvancedHash_row7 main ] start
						 */

						currentComponent = "tAdvancedHash_row7";

						if (execStat) {
							runStat.updateStatOnConnection(iterateId, 1, 1

									, "row7"

							);
						}

						row7Struct row7_HashRow = new row7Struct();

						row7_HashRow.ShipperKey = row7.ShipperKey;

						row7_HashRow.shipping_id = row7.shipping_id;

						row7_HashRow.ShipperName = row7.ShipperName;

						row7_HashRow.ShippingMethod = row7.ShippingMethod;

						tHash_Lookup_row7.put(row7_HashRow);

						tos_count_tAdvancedHash_row7++;

						/**
						 * [tAdvancedHash_row7 main ] stop
						 */

						/**
						 * [tAdvancedHash_row7 process_data_begin ] start
						 */

						currentComponent = "tAdvancedHash_row7";

						/**
						 * [tAdvancedHash_row7 process_data_begin ] stop
						 */

						/**
						 * [tAdvancedHash_row7 process_data_end ] start
						 */

						currentComponent = "tAdvancedHash_row7";

						/**
						 * [tAdvancedHash_row7 process_data_end ] stop
						 */

						/**
						 * [tDBInput_2 process_data_end ] start
						 */

						currentComponent = "tDBInput_2";

						/**
						 * [tDBInput_2 process_data_end ] stop
						 */

						/**
						 * [tDBInput_2 end ] start
						 */

						currentComponent = "tDBInput_2";

					}
				} finally {
					if (rs_tDBInput_2 != null) {
						rs_tDBInput_2.close();
					}
					if (stmt_tDBInput_2 != null) {
						stmt_tDBInput_2.close();
					}
					if (conn_tDBInput_2 != null && !conn_tDBInput_2.isClosed()) {

						conn_tDBInput_2.close();

						if ("com.mysql.cj.jdbc.Driver".equals((String) globalMap.get("driverClass_"))
								&& routines.system.BundleUtils.inOSGi()) {
							Class.forName("com.mysql.cj.jdbc.AbandonedConnectionCleanupThread")
									.getMethod("checkedShutdown").invoke(null, (Object[]) null);
						}

					}
				}
				globalMap.put("tDBInput_2_NB_LINE", nb_line_tDBInput_2);

				ok_Hash.put("tDBInput_2", true);
				end_Hash.put("tDBInput_2", System.currentTimeMillis());

				/**
				 * [tDBInput_2 end ] stop
				 */

				/**
				 * [tAdvancedHash_row7 end ] start
				 */

				currentComponent = "tAdvancedHash_row7";

				tHash_Lookup_row7.endPut();

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row7");
				}

				ok_Hash.put("tAdvancedHash_row7", true);
				end_Hash.put("tAdvancedHash_row7", System.currentTimeMillis());

				/**
				 * [tAdvancedHash_row7 end ] stop
				 */

			} // end the resume

		} catch (java.lang.Exception e) {

			TalendException te = new TalendException(e, currentComponent, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			try {

				/**
				 * [tDBInput_2 finally ] start
				 */

				currentComponent = "tDBInput_2";

				/**
				 * [tDBInput_2 finally ] stop
				 */

				/**
				 * [tAdvancedHash_row7 finally ] start
				 */

				currentComponent = "tAdvancedHash_row7";

				/**
				 * [tAdvancedHash_row7 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tDBInput_2_SUBPROCESS_STATE", 1);
	}

	public static class row8Struct implements routines.system.IPersistableComparableLookupRow<row8Struct> {
		final static byte[] commonByteArrayLock_PROJET_TALEND_normalize_data = new byte[0];
		static byte[] commonByteArray_PROJET_TALEND_normalize_data = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public Integer ProductKey;

		public Integer getProductKey() {
			return this.ProductKey;
		}

		public Integer ProductID;

		public Integer getProductID() {
			return this.ProductID;
		}

		public String ProductName;

		public String getProductName() {
			return this.ProductName;
		}

		public String ProductCategory;

		public String getProductCategory() {
			return this.ProductCategory;
		}

		public String ProductSubCategory;

		public String getProductSubCategory() {
			return this.ProductSubCategory;
		}

		public String ProductPrice;

		public String getProductPrice() {
			return this.ProductPrice;
		}

		public java.util.Date StartDate;

		public java.util.Date getStartDate() {
			return this.StartDate;
		}

		public java.util.Date EndDate;

		public java.util.Date getEndDate() {
			return this.EndDate;
		}

		public Integer IsActive;

		public Integer getIsActive() {
			return this.IsActive;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.ProductKey == null) ? 0 : this.ProductKey.hashCode());

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final row8Struct other = (row8Struct) obj;

			if (this.ProductKey == null) {
				if (other.ProductKey != null)
					return false;

			} else if (!this.ProductKey.equals(other.ProductKey))

				return false;

			return true;
		}

		public void copyDataTo(row8Struct other) {

			other.ProductKey = this.ProductKey;
			other.ProductID = this.ProductID;
			other.ProductName = this.ProductName;
			other.ProductCategory = this.ProductCategory;
			other.ProductSubCategory = this.ProductSubCategory;
			other.ProductPrice = this.ProductPrice;
			other.StartDate = this.StartDate;
			other.EndDate = this.EndDate;
			other.IsActive = this.IsActive;

		}

		public void copyKeysDataTo(row8Struct other) {

			other.ProductKey = this.ProductKey;

		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		private Integer readInteger(DataInputStream dis, ObjectInputStream ois) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(DataInputStream dis, org.jboss.marshalling.Unmarshaller unmarshaller)
				throws IOException {
			Integer intReturn;
			int length = 0;
			length = unmarshaller.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = unmarshaller.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, DataOutputStream dos, ObjectOutputStream oos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, DataOutputStream dos, org.jboss.marshalling.Marshaller marshaller)
				throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		private String readString(DataInputStream dis, ObjectInputStream ois) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				byte[] byteArray = new byte[length];
				dis.read(byteArray);
				strReturn = new String(byteArray, utf8Charset);
			}
			return strReturn;
		}

		private String readString(DataInputStream dis, org.jboss.marshalling.Unmarshaller unmarshaller)
				throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				byte[] byteArray = new byte[length];
				unmarshaller.read(byteArray);
				strReturn = new String(byteArray, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, DataOutputStream dos, org.jboss.marshalling.Marshaller marshaller)
				throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		private void writeString(String str, DataOutputStream dos, ObjectOutputStream oos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private java.util.Date readDate(DataInputStream dis, ObjectInputStream ois) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(dis.readLong());
			}
			return dateReturn;
		}

		private java.util.Date readDate(DataInputStream dis, org.jboss.marshalling.Unmarshaller unmarshaller)
				throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = unmarshaller.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(unmarshaller.readLong());
			}
			return dateReturn;
		}

		private void writeDate(java.util.Date date1, DataOutputStream dos, ObjectOutputStream oos) throws IOException {
			if (date1 == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeLong(date1.getTime());
			}
		}

		private void writeDate(java.util.Date date1, DataOutputStream dos, org.jboss.marshalling.Marshaller marshaller)
				throws IOException {
			if (date1 == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeLong(date1.getTime());
			}
		}

		public void readKeysData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_PROJET_TALEND_normalize_data) {

				try {

					int length = 0;

					this.ProductKey = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readKeysData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_PROJET_TALEND_normalize_data) {

				try {

					int length = 0;

					this.ProductKey = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeKeysData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.ProductKey, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeKeysData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Integer

				writeInteger(this.ProductKey, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		/**
		 * Fill Values data by reading ObjectInputStream.
		 */
		public void readValuesData(DataInputStream dis, ObjectInputStream ois) {
			try {

				int length = 0;

				this.ProductID = readInteger(dis, ois);

				this.ProductName = readString(dis, ois);

				this.ProductCategory = readString(dis, ois);

				this.ProductSubCategory = readString(dis, ois);

				this.ProductPrice = readString(dis, ois);

				this.StartDate = readDate(dis, ois);

				this.EndDate = readDate(dis, ois);

				this.IsActive = readInteger(dis, ois);

			} catch (IOException e) {
				throw new RuntimeException(e);

			}

		}

		public void readValuesData(DataInputStream dis, org.jboss.marshalling.Unmarshaller objectIn) {
			try {
				int length = 0;

				this.ProductID = readInteger(dis, objectIn);

				this.ProductName = readString(dis, objectIn);

				this.ProductCategory = readString(dis, objectIn);

				this.ProductSubCategory = readString(dis, objectIn);

				this.ProductPrice = readString(dis, objectIn);

				this.StartDate = readDate(dis, objectIn);

				this.EndDate = readDate(dis, objectIn);

				this.IsActive = readInteger(dis, objectIn);

			} catch (IOException e) {
				throw new RuntimeException(e);

			}

		}

		/**
		 * Return a byte array which represents Values data.
		 */
		public void writeValuesData(DataOutputStream dos, ObjectOutputStream oos) {
			try {

				writeInteger(this.ProductID, dos, oos);

				writeString(this.ProductName, dos, oos);

				writeString(this.ProductCategory, dos, oos);

				writeString(this.ProductSubCategory, dos, oos);

				writeString(this.ProductPrice, dos, oos);

				writeDate(this.StartDate, dos, oos);

				writeDate(this.EndDate, dos, oos);

				writeInteger(this.IsActive, dos, oos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeValuesData(DataOutputStream dos, org.jboss.marshalling.Marshaller objectOut) {
			try {

				writeInteger(this.ProductID, dos, objectOut);

				writeString(this.ProductName, dos, objectOut);

				writeString(this.ProductCategory, dos, objectOut);

				writeString(this.ProductSubCategory, dos, objectOut);

				writeString(this.ProductPrice, dos, objectOut);

				writeDate(this.StartDate, dos, objectOut);

				writeDate(this.EndDate, dos, objectOut);

				writeInteger(this.IsActive, dos, objectOut);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		public boolean supportMarshaller() {
			return true;
		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("ProductKey=" + String.valueOf(ProductKey));
			sb.append(",ProductID=" + String.valueOf(ProductID));
			sb.append(",ProductName=" + ProductName);
			sb.append(",ProductCategory=" + ProductCategory);
			sb.append(",ProductSubCategory=" + ProductSubCategory);
			sb.append(",ProductPrice=" + ProductPrice);
			sb.append(",StartDate=" + String.valueOf(StartDate));
			sb.append(",EndDate=" + String.valueOf(EndDate));
			sb.append(",IsActive=" + String.valueOf(IsActive));
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row8Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.ProductKey, other.ProductKey);
			if (returnValue != 0) {
				return returnValue;
			}

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public void tDBInput_3Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tDBInput_3_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		String iterateId = "";

		String currentComponent = "";
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				row8Struct row8 = new row8Struct();

				/**
				 * [tAdvancedHash_row8 begin ] start
				 */

				ok_Hash.put("tAdvancedHash_row8", false);
				start_Hash.put("tAdvancedHash_row8", System.currentTimeMillis());

				currentComponent = "tAdvancedHash_row8";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row8");
				}

				int tos_count_tAdvancedHash_row8 = 0;

				// connection name:row8
				// source node:tDBInput_3 - inputs:(after_tFileInputDelimited_2)
				// outputs:(row8,row8) | target node:tAdvancedHash_row8 - inputs:(row8)
				// outputs:()
				// linked node: tMap_9 - inputs:(out8,row7,row8,row9,row10)
				// outputs:(DimensionFact1)

				org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE matchingModeEnum_row8 = org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE.UNIQUE_MATCH;

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row8Struct> tHash_Lookup_row8 = org.talend.designer.components.lookup.memory.AdvancedMemoryLookup
						.<row8Struct>getLookup(matchingModeEnum_row8);

				globalMap.put("tHash_Lookup_row8", tHash_Lookup_row8);

				/**
				 * [tAdvancedHash_row8 begin ] stop
				 */

				/**
				 * [tDBInput_3 begin ] start
				 */

				ok_Hash.put("tDBInput_3", false);
				start_Hash.put("tDBInput_3", System.currentTimeMillis());

				currentComponent = "tDBInput_3";

				int tos_count_tDBInput_3 = 0;

				org.talend.designer.components.util.mssql.MSSqlGenerateTimestampUtil mssqlGTU_tDBInput_3 = org.talend.designer.components.util.mssql.MSSqlUtilFactory
						.getMSSqlGenerateTimestampUtil();

				java.util.List<String> talendToDBList_tDBInput_3 = new java.util.ArrayList();
				String[] talendToDBArray_tDBInput_3 = new String[] { "FLOAT", "NUMERIC", "NUMERIC IDENTITY", "DECIMAL",
						"DECIMAL IDENTITY", "REAL" };
				java.util.Collections.addAll(talendToDBList_tDBInput_3, talendToDBArray_tDBInput_3);
				int nb_line_tDBInput_3 = 0;
				java.sql.Connection conn_tDBInput_3 = null;
				String driverClass_tDBInput_3 = "net.sourceforge.jtds.jdbc.Driver";
				java.lang.Class jdbcclazz_tDBInput_3 = java.lang.Class.forName(driverClass_tDBInput_3);
				String dbUser_tDBInput_3 = "sa";

				final String decryptedPassword_tDBInput_3 = routines.system.PasswordEncryptUtil.decryptPassword(
						"enc:routine.encryption.key.v1:ZZlW8VL5eOwfC+3XAZQJ7egk9wLKVRHy8E8j3TxWgDJC9Vw=");

				String dbPwd_tDBInput_3 = decryptedPassword_tDBInput_3;

				String port_tDBInput_3 = "1433";
				String dbname_tDBInput_3 = "med_database";
				String url_tDBInput_3 = "jdbc:jtds:sqlserver://" + "localhost";
				if (!"".equals(port_tDBInput_3)) {
					url_tDBInput_3 += ":" + "1433";
				}
				if (!"".equals(dbname_tDBInput_3)) {
					url_tDBInput_3 += "//" + "med_database";
				}
				url_tDBInput_3 += ";appName=" + projectName + ";" + "";
				String dbschema_tDBInput_3 = "dbo";

				conn_tDBInput_3 = java.sql.DriverManager.getConnection(url_tDBInput_3, dbUser_tDBInput_3,
						dbPwd_tDBInput_3);

				java.sql.Statement stmt_tDBInput_3 = conn_tDBInput_3.createStatement();

				String dbquery_tDBInput_3 = "SELECT dbo.ProductDimension.ProductKey,\n		dbo.ProductDimension.ProductID,\n		dbo.ProductDimension.ProductName,\n		dbo.Pro"
						+ "ductDimension.ProductCategory,\n		dbo.ProductDimension.ProductSubCategory,\n		dbo.ProductDimension.ProductPrice,\n		dbo.Pro"
						+ "ductDimension.StartDate,\n		dbo.ProductDimension.EndDate,\n		dbo.ProductDimension.IsActive\nFROM	dbo.ProductDimension";

				globalMap.put("tDBInput_3_QUERY", dbquery_tDBInput_3);
				java.sql.ResultSet rs_tDBInput_3 = null;

				try {
					rs_tDBInput_3 = stmt_tDBInput_3.executeQuery(dbquery_tDBInput_3);
					java.sql.ResultSetMetaData rsmd_tDBInput_3 = rs_tDBInput_3.getMetaData();
					int colQtyInRs_tDBInput_3 = rsmd_tDBInput_3.getColumnCount();

					String tmpContent_tDBInput_3 = null;

					while (rs_tDBInput_3.next()) {
						nb_line_tDBInput_3++;

						if (colQtyInRs_tDBInput_3 < 1) {
							row8.ProductKey = null;
						} else {

							row8.ProductKey = rs_tDBInput_3.getInt(1);
							if (rs_tDBInput_3.wasNull()) {
								row8.ProductKey = null;
							}
						}
						if (colQtyInRs_tDBInput_3 < 2) {
							row8.ProductID = null;
						} else {

							row8.ProductID = rs_tDBInput_3.getInt(2);
							if (rs_tDBInput_3.wasNull()) {
								row8.ProductID = null;
							}
						}
						if (colQtyInRs_tDBInput_3 < 3) {
							row8.ProductName = null;
						} else {

							tmpContent_tDBInput_3 = rs_tDBInput_3.getString(3);
							if (tmpContent_tDBInput_3 != null) {
								if (talendToDBList_tDBInput_3.contains(
										rsmd_tDBInput_3.getColumnTypeName(3).toUpperCase(java.util.Locale.ENGLISH))) {
									row8.ProductName = FormatterUtils.formatUnwithE(tmpContent_tDBInput_3);
								} else {
									row8.ProductName = tmpContent_tDBInput_3;
								}
							} else {
								row8.ProductName = null;
							}
						}
						if (colQtyInRs_tDBInput_3 < 4) {
							row8.ProductCategory = null;
						} else {

							tmpContent_tDBInput_3 = rs_tDBInput_3.getString(4);
							if (tmpContent_tDBInput_3 != null) {
								if (talendToDBList_tDBInput_3.contains(
										rsmd_tDBInput_3.getColumnTypeName(4).toUpperCase(java.util.Locale.ENGLISH))) {
									row8.ProductCategory = FormatterUtils.formatUnwithE(tmpContent_tDBInput_3);
								} else {
									row8.ProductCategory = tmpContent_tDBInput_3;
								}
							} else {
								row8.ProductCategory = null;
							}
						}
						if (colQtyInRs_tDBInput_3 < 5) {
							row8.ProductSubCategory = null;
						} else {

							tmpContent_tDBInput_3 = rs_tDBInput_3.getString(5);
							if (tmpContent_tDBInput_3 != null) {
								if (talendToDBList_tDBInput_3.contains(
										rsmd_tDBInput_3.getColumnTypeName(5).toUpperCase(java.util.Locale.ENGLISH))) {
									row8.ProductSubCategory = FormatterUtils.formatUnwithE(tmpContent_tDBInput_3);
								} else {
									row8.ProductSubCategory = tmpContent_tDBInput_3;
								}
							} else {
								row8.ProductSubCategory = null;
							}
						}
						if (colQtyInRs_tDBInput_3 < 6) {
							row8.ProductPrice = null;
						} else {

							tmpContent_tDBInput_3 = rs_tDBInput_3.getString(6);
							if (tmpContent_tDBInput_3 != null) {
								if (talendToDBList_tDBInput_3.contains(
										rsmd_tDBInput_3.getColumnTypeName(6).toUpperCase(java.util.Locale.ENGLISH))) {
									row8.ProductPrice = FormatterUtils.formatUnwithE(tmpContent_tDBInput_3);
								} else {
									row8.ProductPrice = tmpContent_tDBInput_3;
								}
							} else {
								row8.ProductPrice = null;
							}
						}
						if (colQtyInRs_tDBInput_3 < 7) {
							row8.StartDate = null;
						} else {

							row8.StartDate = mssqlGTU_tDBInput_3.getDate(rsmd_tDBInput_3, rs_tDBInput_3, 7);

						}
						if (colQtyInRs_tDBInput_3 < 8) {
							row8.EndDate = null;
						} else {

							row8.EndDate = mssqlGTU_tDBInput_3.getDate(rsmd_tDBInput_3, rs_tDBInput_3, 8);

						}
						if (colQtyInRs_tDBInput_3 < 9) {
							row8.IsActive = null;
						} else {

							row8.IsActive = rs_tDBInput_3.getInt(9);
							if (rs_tDBInput_3.wasNull()) {
								row8.IsActive = null;
							}
						}

						/**
						 * [tDBInput_3 begin ] stop
						 */

						/**
						 * [tDBInput_3 main ] start
						 */

						currentComponent = "tDBInput_3";

						tos_count_tDBInput_3++;

						/**
						 * [tDBInput_3 main ] stop
						 */

						/**
						 * [tDBInput_3 process_data_begin ] start
						 */

						currentComponent = "tDBInput_3";

						/**
						 * [tDBInput_3 process_data_begin ] stop
						 */

						/**
						 * [tAdvancedHash_row8 main ] start
						 */

						currentComponent = "tAdvancedHash_row8";

						if (execStat) {
							runStat.updateStatOnConnection(iterateId, 1, 1

									, "row8"

							);
						}

						row8Struct row8_HashRow = new row8Struct();

						row8_HashRow.ProductKey = row8.ProductKey;

						row8_HashRow.ProductID = row8.ProductID;

						row8_HashRow.ProductName = row8.ProductName;

						row8_HashRow.ProductCategory = row8.ProductCategory;

						row8_HashRow.ProductSubCategory = row8.ProductSubCategory;

						row8_HashRow.ProductPrice = row8.ProductPrice;

						row8_HashRow.StartDate = row8.StartDate;

						row8_HashRow.EndDate = row8.EndDate;

						row8_HashRow.IsActive = row8.IsActive;

						tHash_Lookup_row8.put(row8_HashRow);

						tos_count_tAdvancedHash_row8++;

						/**
						 * [tAdvancedHash_row8 main ] stop
						 */

						/**
						 * [tAdvancedHash_row8 process_data_begin ] start
						 */

						currentComponent = "tAdvancedHash_row8";

						/**
						 * [tAdvancedHash_row8 process_data_begin ] stop
						 */

						/**
						 * [tAdvancedHash_row8 process_data_end ] start
						 */

						currentComponent = "tAdvancedHash_row8";

						/**
						 * [tAdvancedHash_row8 process_data_end ] stop
						 */

						/**
						 * [tDBInput_3 process_data_end ] start
						 */

						currentComponent = "tDBInput_3";

						/**
						 * [tDBInput_3 process_data_end ] stop
						 */

						/**
						 * [tDBInput_3 end ] start
						 */

						currentComponent = "tDBInput_3";

					}
				} finally {
					if (rs_tDBInput_3 != null) {
						rs_tDBInput_3.close();
					}
					if (stmt_tDBInput_3 != null) {
						stmt_tDBInput_3.close();
					}
					if (conn_tDBInput_3 != null && !conn_tDBInput_3.isClosed()) {

						conn_tDBInput_3.close();

						if ("com.mysql.cj.jdbc.Driver".equals((String) globalMap.get("driverClass_"))
								&& routines.system.BundleUtils.inOSGi()) {
							Class.forName("com.mysql.cj.jdbc.AbandonedConnectionCleanupThread")
									.getMethod("checkedShutdown").invoke(null, (Object[]) null);
						}

					}
				}
				globalMap.put("tDBInput_3_NB_LINE", nb_line_tDBInput_3);

				ok_Hash.put("tDBInput_3", true);
				end_Hash.put("tDBInput_3", System.currentTimeMillis());

				/**
				 * [tDBInput_3 end ] stop
				 */

				/**
				 * [tAdvancedHash_row8 end ] start
				 */

				currentComponent = "tAdvancedHash_row8";

				tHash_Lookup_row8.endPut();

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row8");
				}

				ok_Hash.put("tAdvancedHash_row8", true);
				end_Hash.put("tAdvancedHash_row8", System.currentTimeMillis());

				/**
				 * [tAdvancedHash_row8 end ] stop
				 */

			} // end the resume

		} catch (java.lang.Exception e) {

			TalendException te = new TalendException(e, currentComponent, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			try {

				/**
				 * [tDBInput_3 finally ] start
				 */

				currentComponent = "tDBInput_3";

				/**
				 * [tDBInput_3 finally ] stop
				 */

				/**
				 * [tAdvancedHash_row8 finally ] start
				 */

				currentComponent = "tAdvancedHash_row8";

				/**
				 * [tAdvancedHash_row8 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tDBInput_3_SUBPROCESS_STATE", 1);
	}

	public static class row9Struct implements routines.system.IPersistableComparableLookupRow<row9Struct> {
		final static byte[] commonByteArrayLock_PROJET_TALEND_normalize_data = new byte[0];
		static byte[] commonByteArray_PROJET_TALEND_normalize_data = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public Integer CustomerKey;

		public Integer getCustomerKey() {
			return this.CustomerKey;
		}

		public Integer CustomerID;

		public Integer getCustomerID() {
			return this.CustomerID;
		}

		public String CustomerName;

		public String getCustomerName() {
			return this.CustomerName;
		}

		public String CustomerEmail;

		public String getCustomerEmail() {
			return this.CustomerEmail;
		}

		public String CustomerAddress;

		public String getCustomerAddress() {
			return this.CustomerAddress;
		}

		public String CustomerPhone;

		public String getCustomerPhone() {
			return this.CustomerPhone;
		}

		public String CustomerSegment;

		public String getCustomerSegment() {
			return this.CustomerSegment;
		}

		public java.util.Date StartDate;

		public java.util.Date getStartDate() {
			return this.StartDate;
		}

		public java.util.Date EndDate;

		public java.util.Date getEndDate() {
			return this.EndDate;
		}

		public Integer IsActive;

		public Integer getIsActive() {
			return this.IsActive;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.CustomerKey == null) ? 0 : this.CustomerKey.hashCode());

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final row9Struct other = (row9Struct) obj;

			if (this.CustomerKey == null) {
				if (other.CustomerKey != null)
					return false;

			} else if (!this.CustomerKey.equals(other.CustomerKey))

				return false;

			return true;
		}

		public void copyDataTo(row9Struct other) {

			other.CustomerKey = this.CustomerKey;
			other.CustomerID = this.CustomerID;
			other.CustomerName = this.CustomerName;
			other.CustomerEmail = this.CustomerEmail;
			other.CustomerAddress = this.CustomerAddress;
			other.CustomerPhone = this.CustomerPhone;
			other.CustomerSegment = this.CustomerSegment;
			other.StartDate = this.StartDate;
			other.EndDate = this.EndDate;
			other.IsActive = this.IsActive;

		}

		public void copyKeysDataTo(row9Struct other) {

			other.CustomerKey = this.CustomerKey;

		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		private Integer readInteger(DataInputStream dis, ObjectInputStream ois) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(DataInputStream dis, org.jboss.marshalling.Unmarshaller unmarshaller)
				throws IOException {
			Integer intReturn;
			int length = 0;
			length = unmarshaller.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = unmarshaller.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, DataOutputStream dos, ObjectOutputStream oos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, DataOutputStream dos, org.jboss.marshalling.Marshaller marshaller)
				throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		private String readString(DataInputStream dis, ObjectInputStream ois) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				byte[] byteArray = new byte[length];
				dis.read(byteArray);
				strReturn = new String(byteArray, utf8Charset);
			}
			return strReturn;
		}

		private String readString(DataInputStream dis, org.jboss.marshalling.Unmarshaller unmarshaller)
				throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				byte[] byteArray = new byte[length];
				unmarshaller.read(byteArray);
				strReturn = new String(byteArray, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, DataOutputStream dos, org.jboss.marshalling.Marshaller marshaller)
				throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		private void writeString(String str, DataOutputStream dos, ObjectOutputStream oos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private java.util.Date readDate(DataInputStream dis, ObjectInputStream ois) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(dis.readLong());
			}
			return dateReturn;
		}

		private java.util.Date readDate(DataInputStream dis, org.jboss.marshalling.Unmarshaller unmarshaller)
				throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = unmarshaller.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(unmarshaller.readLong());
			}
			return dateReturn;
		}

		private void writeDate(java.util.Date date1, DataOutputStream dos, ObjectOutputStream oos) throws IOException {
			if (date1 == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeLong(date1.getTime());
			}
		}

		private void writeDate(java.util.Date date1, DataOutputStream dos, org.jboss.marshalling.Marshaller marshaller)
				throws IOException {
			if (date1 == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeLong(date1.getTime());
			}
		}

		public void readKeysData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_PROJET_TALEND_normalize_data) {

				try {

					int length = 0;

					this.CustomerKey = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readKeysData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_PROJET_TALEND_normalize_data) {

				try {

					int length = 0;

					this.CustomerKey = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeKeysData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.CustomerKey, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeKeysData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Integer

				writeInteger(this.CustomerKey, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		/**
		 * Fill Values data by reading ObjectInputStream.
		 */
		public void readValuesData(DataInputStream dis, ObjectInputStream ois) {
			try {

				int length = 0;

				this.CustomerID = readInteger(dis, ois);

				this.CustomerName = readString(dis, ois);

				this.CustomerEmail = readString(dis, ois);

				this.CustomerAddress = readString(dis, ois);

				this.CustomerPhone = readString(dis, ois);

				this.CustomerSegment = readString(dis, ois);

				this.StartDate = readDate(dis, ois);

				this.EndDate = readDate(dis, ois);

				this.IsActive = readInteger(dis, ois);

			} catch (IOException e) {
				throw new RuntimeException(e);

			}

		}

		public void readValuesData(DataInputStream dis, org.jboss.marshalling.Unmarshaller objectIn) {
			try {
				int length = 0;

				this.CustomerID = readInteger(dis, objectIn);

				this.CustomerName = readString(dis, objectIn);

				this.CustomerEmail = readString(dis, objectIn);

				this.CustomerAddress = readString(dis, objectIn);

				this.CustomerPhone = readString(dis, objectIn);

				this.CustomerSegment = readString(dis, objectIn);

				this.StartDate = readDate(dis, objectIn);

				this.EndDate = readDate(dis, objectIn);

				this.IsActive = readInteger(dis, objectIn);

			} catch (IOException e) {
				throw new RuntimeException(e);

			}

		}

		/**
		 * Return a byte array which represents Values data.
		 */
		public void writeValuesData(DataOutputStream dos, ObjectOutputStream oos) {
			try {

				writeInteger(this.CustomerID, dos, oos);

				writeString(this.CustomerName, dos, oos);

				writeString(this.CustomerEmail, dos, oos);

				writeString(this.CustomerAddress, dos, oos);

				writeString(this.CustomerPhone, dos, oos);

				writeString(this.CustomerSegment, dos, oos);

				writeDate(this.StartDate, dos, oos);

				writeDate(this.EndDate, dos, oos);

				writeInteger(this.IsActive, dos, oos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeValuesData(DataOutputStream dos, org.jboss.marshalling.Marshaller objectOut) {
			try {

				writeInteger(this.CustomerID, dos, objectOut);

				writeString(this.CustomerName, dos, objectOut);

				writeString(this.CustomerEmail, dos, objectOut);

				writeString(this.CustomerAddress, dos, objectOut);

				writeString(this.CustomerPhone, dos, objectOut);

				writeString(this.CustomerSegment, dos, objectOut);

				writeDate(this.StartDate, dos, objectOut);

				writeDate(this.EndDate, dos, objectOut);

				writeInteger(this.IsActive, dos, objectOut);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		public boolean supportMarshaller() {
			return true;
		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("CustomerKey=" + String.valueOf(CustomerKey));
			sb.append(",CustomerID=" + String.valueOf(CustomerID));
			sb.append(",CustomerName=" + CustomerName);
			sb.append(",CustomerEmail=" + CustomerEmail);
			sb.append(",CustomerAddress=" + CustomerAddress);
			sb.append(",CustomerPhone=" + CustomerPhone);
			sb.append(",CustomerSegment=" + CustomerSegment);
			sb.append(",StartDate=" + String.valueOf(StartDate));
			sb.append(",EndDate=" + String.valueOf(EndDate));
			sb.append(",IsActive=" + String.valueOf(IsActive));
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row9Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.CustomerKey, other.CustomerKey);
			if (returnValue != 0) {
				return returnValue;
			}

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public void tDBInput_4Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tDBInput_4_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		String iterateId = "";

		String currentComponent = "";
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				row9Struct row9 = new row9Struct();

				/**
				 * [tAdvancedHash_row9 begin ] start
				 */

				ok_Hash.put("tAdvancedHash_row9", false);
				start_Hash.put("tAdvancedHash_row9", System.currentTimeMillis());

				currentComponent = "tAdvancedHash_row9";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row9");
				}

				int tos_count_tAdvancedHash_row9 = 0;

				// connection name:row9
				// source node:tDBInput_4 - inputs:(after_tFileInputDelimited_2)
				// outputs:(row9,row9) | target node:tAdvancedHash_row9 - inputs:(row9)
				// outputs:()
				// linked node: tMap_9 - inputs:(out8,row7,row8,row9,row10)
				// outputs:(DimensionFact1)

				org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE matchingModeEnum_row9 = org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE.UNIQUE_MATCH;

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row9Struct> tHash_Lookup_row9 = org.talend.designer.components.lookup.memory.AdvancedMemoryLookup
						.<row9Struct>getLookup(matchingModeEnum_row9);

				globalMap.put("tHash_Lookup_row9", tHash_Lookup_row9);

				/**
				 * [tAdvancedHash_row9 begin ] stop
				 */

				/**
				 * [tDBInput_4 begin ] start
				 */

				ok_Hash.put("tDBInput_4", false);
				start_Hash.put("tDBInput_4", System.currentTimeMillis());

				currentComponent = "tDBInput_4";

				int tos_count_tDBInput_4 = 0;

				org.talend.designer.components.util.mssql.MSSqlGenerateTimestampUtil mssqlGTU_tDBInput_4 = org.talend.designer.components.util.mssql.MSSqlUtilFactory
						.getMSSqlGenerateTimestampUtil();

				java.util.List<String> talendToDBList_tDBInput_4 = new java.util.ArrayList();
				String[] talendToDBArray_tDBInput_4 = new String[] { "FLOAT", "NUMERIC", "NUMERIC IDENTITY", "DECIMAL",
						"DECIMAL IDENTITY", "REAL" };
				java.util.Collections.addAll(talendToDBList_tDBInput_4, talendToDBArray_tDBInput_4);
				int nb_line_tDBInput_4 = 0;
				java.sql.Connection conn_tDBInput_4 = null;
				String driverClass_tDBInput_4 = "net.sourceforge.jtds.jdbc.Driver";
				java.lang.Class jdbcclazz_tDBInput_4 = java.lang.Class.forName(driverClass_tDBInput_4);
				String dbUser_tDBInput_4 = "sa";

				final String decryptedPassword_tDBInput_4 = routines.system.PasswordEncryptUtil.decryptPassword(
						"enc:routine.encryption.key.v1:R77TgApOXUc0Jhse6odZW/lSgoJzMH97LZaIw53UG1vNTZ8=");

				String dbPwd_tDBInput_4 = decryptedPassword_tDBInput_4;

				String port_tDBInput_4 = "1433";
				String dbname_tDBInput_4 = "med_database";
				String url_tDBInput_4 = "jdbc:jtds:sqlserver://" + "localhost";
				if (!"".equals(port_tDBInput_4)) {
					url_tDBInput_4 += ":" + "1433";
				}
				if (!"".equals(dbname_tDBInput_4)) {
					url_tDBInput_4 += "//" + "med_database";
				}
				url_tDBInput_4 += ";appName=" + projectName + ";" + "";
				String dbschema_tDBInput_4 = "dbo";

				conn_tDBInput_4 = java.sql.DriverManager.getConnection(url_tDBInput_4, dbUser_tDBInput_4,
						dbPwd_tDBInput_4);

				java.sql.Statement stmt_tDBInput_4 = conn_tDBInput_4.createStatement();

				String dbquery_tDBInput_4 = "SELECT dbo.CustomerDimension.customerkey,\n		dbo.CustomerDimension.CustomerID,\n		dbo.CustomerDimension.CustomerName,\n		d"
						+ "bo.CustomerDimension.CustomerEmail,\n		dbo.CustomerDimension.CustomerAddress,\n		dbo.CustomerDimension.CustomerPhone,\n		db"
						+ "o.CustomerDimension.CustomerSegment,\n		dbo.CustomerDimension.StartDate,\n		dbo.CustomerDimension.EndDate,\n		dbo.CustomerD"
						+ "imension.IsActive\nFROM	dbo.CustomerDimension";

				globalMap.put("tDBInput_4_QUERY", dbquery_tDBInput_4);
				java.sql.ResultSet rs_tDBInput_4 = null;

				try {
					rs_tDBInput_4 = stmt_tDBInput_4.executeQuery(dbquery_tDBInput_4);
					java.sql.ResultSetMetaData rsmd_tDBInput_4 = rs_tDBInput_4.getMetaData();
					int colQtyInRs_tDBInput_4 = rsmd_tDBInput_4.getColumnCount();

					String tmpContent_tDBInput_4 = null;

					while (rs_tDBInput_4.next()) {
						nb_line_tDBInput_4++;

						if (colQtyInRs_tDBInput_4 < 1) {
							row9.CustomerKey = null;
						} else {

							row9.CustomerKey = rs_tDBInput_4.getInt(1);
							if (rs_tDBInput_4.wasNull()) {
								row9.CustomerKey = null;
							}
						}
						if (colQtyInRs_tDBInput_4 < 2) {
							row9.CustomerID = null;
						} else {

							row9.CustomerID = rs_tDBInput_4.getInt(2);
							if (rs_tDBInput_4.wasNull()) {
								row9.CustomerID = null;
							}
						}
						if (colQtyInRs_tDBInput_4 < 3) {
							row9.CustomerName = null;
						} else {

							tmpContent_tDBInput_4 = rs_tDBInput_4.getString(3);
							if (tmpContent_tDBInput_4 != null) {
								if (talendToDBList_tDBInput_4.contains(
										rsmd_tDBInput_4.getColumnTypeName(3).toUpperCase(java.util.Locale.ENGLISH))) {
									row9.CustomerName = FormatterUtils.formatUnwithE(tmpContent_tDBInput_4);
								} else {
									row9.CustomerName = tmpContent_tDBInput_4;
								}
							} else {
								row9.CustomerName = null;
							}
						}
						if (colQtyInRs_tDBInput_4 < 4) {
							row9.CustomerEmail = null;
						} else {

							tmpContent_tDBInput_4 = rs_tDBInput_4.getString(4);
							if (tmpContent_tDBInput_4 != null) {
								if (talendToDBList_tDBInput_4.contains(
										rsmd_tDBInput_4.getColumnTypeName(4).toUpperCase(java.util.Locale.ENGLISH))) {
									row9.CustomerEmail = FormatterUtils.formatUnwithE(tmpContent_tDBInput_4);
								} else {
									row9.CustomerEmail = tmpContent_tDBInput_4;
								}
							} else {
								row9.CustomerEmail = null;
							}
						}
						if (colQtyInRs_tDBInput_4 < 5) {
							row9.CustomerAddress = null;
						} else {

							tmpContent_tDBInput_4 = rs_tDBInput_4.getString(5);
							if (tmpContent_tDBInput_4 != null) {
								if (talendToDBList_tDBInput_4.contains(
										rsmd_tDBInput_4.getColumnTypeName(5).toUpperCase(java.util.Locale.ENGLISH))) {
									row9.CustomerAddress = FormatterUtils.formatUnwithE(tmpContent_tDBInput_4);
								} else {
									row9.CustomerAddress = tmpContent_tDBInput_4;
								}
							} else {
								row9.CustomerAddress = null;
							}
						}
						if (colQtyInRs_tDBInput_4 < 6) {
							row9.CustomerPhone = null;
						} else {

							tmpContent_tDBInput_4 = rs_tDBInput_4.getString(6);
							if (tmpContent_tDBInput_4 != null) {
								if (talendToDBList_tDBInput_4.contains(
										rsmd_tDBInput_4.getColumnTypeName(6).toUpperCase(java.util.Locale.ENGLISH))) {
									row9.CustomerPhone = FormatterUtils.formatUnwithE(tmpContent_tDBInput_4);
								} else {
									row9.CustomerPhone = tmpContent_tDBInput_4;
								}
							} else {
								row9.CustomerPhone = null;
							}
						}
						if (colQtyInRs_tDBInput_4 < 7) {
							row9.CustomerSegment = null;
						} else {

							tmpContent_tDBInput_4 = rs_tDBInput_4.getString(7);
							if (tmpContent_tDBInput_4 != null) {
								if (talendToDBList_tDBInput_4.contains(
										rsmd_tDBInput_4.getColumnTypeName(7).toUpperCase(java.util.Locale.ENGLISH))) {
									row9.CustomerSegment = FormatterUtils.formatUnwithE(tmpContent_tDBInput_4);
								} else {
									row9.CustomerSegment = tmpContent_tDBInput_4;
								}
							} else {
								row9.CustomerSegment = null;
							}
						}
						if (colQtyInRs_tDBInput_4 < 8) {
							row9.StartDate = null;
						} else {

							row9.StartDate = mssqlGTU_tDBInput_4.getDate(rsmd_tDBInput_4, rs_tDBInput_4, 8);

						}
						if (colQtyInRs_tDBInput_4 < 9) {
							row9.EndDate = null;
						} else {

							row9.EndDate = mssqlGTU_tDBInput_4.getDate(rsmd_tDBInput_4, rs_tDBInput_4, 9);

						}
						if (colQtyInRs_tDBInput_4 < 10) {
							row9.IsActive = null;
						} else {

							row9.IsActive = rs_tDBInput_4.getInt(10);
							if (rs_tDBInput_4.wasNull()) {
								row9.IsActive = null;
							}
						}

						/**
						 * [tDBInput_4 begin ] stop
						 */

						/**
						 * [tDBInput_4 main ] start
						 */

						currentComponent = "tDBInput_4";

						tos_count_tDBInput_4++;

						/**
						 * [tDBInput_4 main ] stop
						 */

						/**
						 * [tDBInput_4 process_data_begin ] start
						 */

						currentComponent = "tDBInput_4";

						/**
						 * [tDBInput_4 process_data_begin ] stop
						 */

						/**
						 * [tAdvancedHash_row9 main ] start
						 */

						currentComponent = "tAdvancedHash_row9";

						if (execStat) {
							runStat.updateStatOnConnection(iterateId, 1, 1

									, "row9"

							);
						}

						row9Struct row9_HashRow = new row9Struct();

						row9_HashRow.CustomerKey = row9.CustomerKey;

						row9_HashRow.CustomerID = row9.CustomerID;

						row9_HashRow.CustomerName = row9.CustomerName;

						row9_HashRow.CustomerEmail = row9.CustomerEmail;

						row9_HashRow.CustomerAddress = row9.CustomerAddress;

						row9_HashRow.CustomerPhone = row9.CustomerPhone;

						row9_HashRow.CustomerSegment = row9.CustomerSegment;

						row9_HashRow.StartDate = row9.StartDate;

						row9_HashRow.EndDate = row9.EndDate;

						row9_HashRow.IsActive = row9.IsActive;

						tHash_Lookup_row9.put(row9_HashRow);

						tos_count_tAdvancedHash_row9++;

						/**
						 * [tAdvancedHash_row9 main ] stop
						 */

						/**
						 * [tAdvancedHash_row9 process_data_begin ] start
						 */

						currentComponent = "tAdvancedHash_row9";

						/**
						 * [tAdvancedHash_row9 process_data_begin ] stop
						 */

						/**
						 * [tAdvancedHash_row9 process_data_end ] start
						 */

						currentComponent = "tAdvancedHash_row9";

						/**
						 * [tAdvancedHash_row9 process_data_end ] stop
						 */

						/**
						 * [tDBInput_4 process_data_end ] start
						 */

						currentComponent = "tDBInput_4";

						/**
						 * [tDBInput_4 process_data_end ] stop
						 */

						/**
						 * [tDBInput_4 end ] start
						 */

						currentComponent = "tDBInput_4";

					}
				} finally {
					if (rs_tDBInput_4 != null) {
						rs_tDBInput_4.close();
					}
					if (stmt_tDBInput_4 != null) {
						stmt_tDBInput_4.close();
					}
					if (conn_tDBInput_4 != null && !conn_tDBInput_4.isClosed()) {

						conn_tDBInput_4.close();

						if ("com.mysql.cj.jdbc.Driver".equals((String) globalMap.get("driverClass_"))
								&& routines.system.BundleUtils.inOSGi()) {
							Class.forName("com.mysql.cj.jdbc.AbandonedConnectionCleanupThread")
									.getMethod("checkedShutdown").invoke(null, (Object[]) null);
						}

					}
				}
				globalMap.put("tDBInput_4_NB_LINE", nb_line_tDBInput_4);

				ok_Hash.put("tDBInput_4", true);
				end_Hash.put("tDBInput_4", System.currentTimeMillis());

				/**
				 * [tDBInput_4 end ] stop
				 */

				/**
				 * [tAdvancedHash_row9 end ] start
				 */

				currentComponent = "tAdvancedHash_row9";

				tHash_Lookup_row9.endPut();

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row9");
				}

				ok_Hash.put("tAdvancedHash_row9", true);
				end_Hash.put("tAdvancedHash_row9", System.currentTimeMillis());

				/**
				 * [tAdvancedHash_row9 end ] stop
				 */

			} // end the resume

		} catch (java.lang.Exception e) {

			TalendException te = new TalendException(e, currentComponent, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			try {

				/**
				 * [tDBInput_4 finally ] start
				 */

				currentComponent = "tDBInput_4";

				/**
				 * [tDBInput_4 finally ] stop
				 */

				/**
				 * [tAdvancedHash_row9 finally ] start
				 */

				currentComponent = "tAdvancedHash_row9";

				/**
				 * [tAdvancedHash_row9 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tDBInput_4_SUBPROCESS_STATE", 1);
	}

	public static class row10Struct implements routines.system.IPersistableComparableLookupRow<row10Struct> {
		final static byte[] commonByteArrayLock_PROJET_TALEND_normalize_data = new byte[0];
		static byte[] commonByteArray_PROJET_TALEND_normalize_data = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public Integer SupplierKey;

		public Integer getSupplierKey() {
			return this.SupplierKey;
		}

		public String SupplierName;

		public String getSupplierName() {
			return this.SupplierName;
		}

		public String SupplierLocation;

		public String getSupplierLocation() {
			return this.SupplierLocation;
		}

		public String SupplierContact;

		public String getSupplierContact() {
			return this.SupplierContact;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.SupplierKey == null) ? 0 : this.SupplierKey.hashCode());

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final row10Struct other = (row10Struct) obj;

			if (this.SupplierKey == null) {
				if (other.SupplierKey != null)
					return false;

			} else if (!this.SupplierKey.equals(other.SupplierKey))

				return false;

			return true;
		}

		public void copyDataTo(row10Struct other) {

			other.SupplierKey = this.SupplierKey;
			other.SupplierName = this.SupplierName;
			other.SupplierLocation = this.SupplierLocation;
			other.SupplierContact = this.SupplierContact;

		}

		public void copyKeysDataTo(row10Struct other) {

			other.SupplierKey = this.SupplierKey;

		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		private String readString(DataInputStream dis, ObjectInputStream ois) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				byte[] byteArray = new byte[length];
				dis.read(byteArray);
				strReturn = new String(byteArray, utf8Charset);
			}
			return strReturn;
		}

		private String readString(DataInputStream dis, org.jboss.marshalling.Unmarshaller unmarshaller)
				throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				byte[] byteArray = new byte[length];
				unmarshaller.read(byteArray);
				strReturn = new String(byteArray, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, DataOutputStream dos, org.jboss.marshalling.Marshaller marshaller)
				throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		private void writeString(String str, DataOutputStream dos, ObjectOutputStream oos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		public void readKeysData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_PROJET_TALEND_normalize_data) {

				try {

					int length = 0;

					this.SupplierKey = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readKeysData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_PROJET_TALEND_normalize_data) {

				try {

					int length = 0;

					this.SupplierKey = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeKeysData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.SupplierKey, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeKeysData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Integer

				writeInteger(this.SupplierKey, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		/**
		 * Fill Values data by reading ObjectInputStream.
		 */
		public void readValuesData(DataInputStream dis, ObjectInputStream ois) {
			try {

				int length = 0;

				this.SupplierName = readString(dis, ois);

				this.SupplierLocation = readString(dis, ois);

				this.SupplierContact = readString(dis, ois);

			} catch (IOException e) {
				throw new RuntimeException(e);

			}

		}

		public void readValuesData(DataInputStream dis, org.jboss.marshalling.Unmarshaller objectIn) {
			try {
				int length = 0;

				this.SupplierName = readString(dis, objectIn);

				this.SupplierLocation = readString(dis, objectIn);

				this.SupplierContact = readString(dis, objectIn);

			} catch (IOException e) {
				throw new RuntimeException(e);

			}

		}

		/**
		 * Return a byte array which represents Values data.
		 */
		public void writeValuesData(DataOutputStream dos, ObjectOutputStream oos) {
			try {

				writeString(this.SupplierName, dos, oos);

				writeString(this.SupplierLocation, dos, oos);

				writeString(this.SupplierContact, dos, oos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeValuesData(DataOutputStream dos, org.jboss.marshalling.Marshaller objectOut) {
			try {

				writeString(this.SupplierName, dos, objectOut);

				writeString(this.SupplierLocation, dos, objectOut);

				writeString(this.SupplierContact, dos, objectOut);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		public boolean supportMarshaller() {
			return true;
		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("SupplierKey=" + String.valueOf(SupplierKey));
			sb.append(",SupplierName=" + SupplierName);
			sb.append(",SupplierLocation=" + SupplierLocation);
			sb.append(",SupplierContact=" + SupplierContact);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row10Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.SupplierKey, other.SupplierKey);
			if (returnValue != 0) {
				return returnValue;
			}

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public void tDBInput_5Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tDBInput_5_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		String iterateId = "";

		String currentComponent = "";
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				row10Struct row10 = new row10Struct();

				/**
				 * [tAdvancedHash_row10 begin ] start
				 */

				ok_Hash.put("tAdvancedHash_row10", false);
				start_Hash.put("tAdvancedHash_row10", System.currentTimeMillis());

				currentComponent = "tAdvancedHash_row10";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row10");
				}

				int tos_count_tAdvancedHash_row10 = 0;

				// connection name:row10
				// source node:tDBInput_5 - inputs:(after_tFileInputDelimited_2)
				// outputs:(row10,row10) | target node:tAdvancedHash_row10 - inputs:(row10)
				// outputs:()
				// linked node: tMap_9 - inputs:(out8,row7,row8,row9,row10)
				// outputs:(DimensionFact1)

				org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE matchingModeEnum_row10 = org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE.UNIQUE_MATCH;

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row10Struct> tHash_Lookup_row10 = org.talend.designer.components.lookup.memory.AdvancedMemoryLookup
						.<row10Struct>getLookup(matchingModeEnum_row10);

				globalMap.put("tHash_Lookup_row10", tHash_Lookup_row10);

				/**
				 * [tAdvancedHash_row10 begin ] stop
				 */

				/**
				 * [tDBInput_5 begin ] start
				 */

				ok_Hash.put("tDBInput_5", false);
				start_Hash.put("tDBInput_5", System.currentTimeMillis());

				currentComponent = "tDBInput_5";

				int tos_count_tDBInput_5 = 0;

				org.talend.designer.components.util.mssql.MSSqlGenerateTimestampUtil mssqlGTU_tDBInput_5 = org.talend.designer.components.util.mssql.MSSqlUtilFactory
						.getMSSqlGenerateTimestampUtil();

				java.util.List<String> talendToDBList_tDBInput_5 = new java.util.ArrayList();
				String[] talendToDBArray_tDBInput_5 = new String[] { "FLOAT", "NUMERIC", "NUMERIC IDENTITY", "DECIMAL",
						"DECIMAL IDENTITY", "REAL" };
				java.util.Collections.addAll(talendToDBList_tDBInput_5, talendToDBArray_tDBInput_5);
				int nb_line_tDBInput_5 = 0;
				java.sql.Connection conn_tDBInput_5 = null;
				String driverClass_tDBInput_5 = "net.sourceforge.jtds.jdbc.Driver";
				java.lang.Class jdbcclazz_tDBInput_5 = java.lang.Class.forName(driverClass_tDBInput_5);
				String dbUser_tDBInput_5 = "sa";

				final String decryptedPassword_tDBInput_5 = routines.system.PasswordEncryptUtil.decryptPassword(
						"enc:routine.encryption.key.v1:f+PdrBJlciyzyfJ4hcWhDYNCr+H0vRfwNipRR+z8N2kvpI8=");

				String dbPwd_tDBInput_5 = decryptedPassword_tDBInput_5;

				String port_tDBInput_5 = "1433";
				String dbname_tDBInput_5 = "med_database";
				String url_tDBInput_5 = "jdbc:jtds:sqlserver://" + "localhost";
				if (!"".equals(port_tDBInput_5)) {
					url_tDBInput_5 += ":" + "1433";
				}
				if (!"".equals(dbname_tDBInput_5)) {
					url_tDBInput_5 += "//" + "med_database";
				}
				url_tDBInput_5 += ";appName=" + projectName + ";" + "";
				String dbschema_tDBInput_5 = "dbo";

				conn_tDBInput_5 = java.sql.DriverManager.getConnection(url_tDBInput_5, dbUser_tDBInput_5,
						dbPwd_tDBInput_5);

				java.sql.Statement stmt_tDBInput_5 = conn_tDBInput_5.createStatement();

				String dbquery_tDBInput_5 = "SELECT dbo.SupplierDimension.SupplierKey,\n		dbo.SupplierDimension.SupplierName,\n		dbo.SupplierDimension.SupplierLocatio"
						+ "n,\n		dbo.SupplierDimension.SupplierContact\nFROM	dbo.SupplierDimension";

				globalMap.put("tDBInput_5_QUERY", dbquery_tDBInput_5);
				java.sql.ResultSet rs_tDBInput_5 = null;

				try {
					rs_tDBInput_5 = stmt_tDBInput_5.executeQuery(dbquery_tDBInput_5);
					java.sql.ResultSetMetaData rsmd_tDBInput_5 = rs_tDBInput_5.getMetaData();
					int colQtyInRs_tDBInput_5 = rsmd_tDBInput_5.getColumnCount();

					String tmpContent_tDBInput_5 = null;

					while (rs_tDBInput_5.next()) {
						nb_line_tDBInput_5++;

						if (colQtyInRs_tDBInput_5 < 1) {
							row10.SupplierKey = null;
						} else {

							row10.SupplierKey = rs_tDBInput_5.getInt(1);
							if (rs_tDBInput_5.wasNull()) {
								row10.SupplierKey = null;
							}
						}
						if (colQtyInRs_tDBInput_5 < 2) {
							row10.SupplierName = null;
						} else {

							tmpContent_tDBInput_5 = rs_tDBInput_5.getString(2);
							if (tmpContent_tDBInput_5 != null) {
								if (talendToDBList_tDBInput_5.contains(
										rsmd_tDBInput_5.getColumnTypeName(2).toUpperCase(java.util.Locale.ENGLISH))) {
									row10.SupplierName = FormatterUtils.formatUnwithE(tmpContent_tDBInput_5);
								} else {
									row10.SupplierName = tmpContent_tDBInput_5;
								}
							} else {
								row10.SupplierName = null;
							}
						}
						if (colQtyInRs_tDBInput_5 < 3) {
							row10.SupplierLocation = null;
						} else {

							tmpContent_tDBInput_5 = rs_tDBInput_5.getString(3);
							if (tmpContent_tDBInput_5 != null) {
								if (talendToDBList_tDBInput_5.contains(
										rsmd_tDBInput_5.getColumnTypeName(3).toUpperCase(java.util.Locale.ENGLISH))) {
									row10.SupplierLocation = FormatterUtils.formatUnwithE(tmpContent_tDBInput_5);
								} else {
									row10.SupplierLocation = tmpContent_tDBInput_5;
								}
							} else {
								row10.SupplierLocation = null;
							}
						}
						if (colQtyInRs_tDBInput_5 < 4) {
							row10.SupplierContact = null;
						} else {

							tmpContent_tDBInput_5 = rs_tDBInput_5.getString(4);
							if (tmpContent_tDBInput_5 != null) {
								if (talendToDBList_tDBInput_5.contains(
										rsmd_tDBInput_5.getColumnTypeName(4).toUpperCase(java.util.Locale.ENGLISH))) {
									row10.SupplierContact = FormatterUtils.formatUnwithE(tmpContent_tDBInput_5);
								} else {
									row10.SupplierContact = tmpContent_tDBInput_5;
								}
							} else {
								row10.SupplierContact = null;
							}
						}

						/**
						 * [tDBInput_5 begin ] stop
						 */

						/**
						 * [tDBInput_5 main ] start
						 */

						currentComponent = "tDBInput_5";

						tos_count_tDBInput_5++;

						/**
						 * [tDBInput_5 main ] stop
						 */

						/**
						 * [tDBInput_5 process_data_begin ] start
						 */

						currentComponent = "tDBInput_5";

						/**
						 * [tDBInput_5 process_data_begin ] stop
						 */

						/**
						 * [tAdvancedHash_row10 main ] start
						 */

						currentComponent = "tAdvancedHash_row10";

						if (execStat) {
							runStat.updateStatOnConnection(iterateId, 1, 1

									, "row10"

							);
						}

						row10Struct row10_HashRow = new row10Struct();

						row10_HashRow.SupplierKey = row10.SupplierKey;

						row10_HashRow.SupplierName = row10.SupplierName;

						row10_HashRow.SupplierLocation = row10.SupplierLocation;

						row10_HashRow.SupplierContact = row10.SupplierContact;

						tHash_Lookup_row10.put(row10_HashRow);

						tos_count_tAdvancedHash_row10++;

						/**
						 * [tAdvancedHash_row10 main ] stop
						 */

						/**
						 * [tAdvancedHash_row10 process_data_begin ] start
						 */

						currentComponent = "tAdvancedHash_row10";

						/**
						 * [tAdvancedHash_row10 process_data_begin ] stop
						 */

						/**
						 * [tAdvancedHash_row10 process_data_end ] start
						 */

						currentComponent = "tAdvancedHash_row10";

						/**
						 * [tAdvancedHash_row10 process_data_end ] stop
						 */

						/**
						 * [tDBInput_5 process_data_end ] start
						 */

						currentComponent = "tDBInput_5";

						/**
						 * [tDBInput_5 process_data_end ] stop
						 */

						/**
						 * [tDBInput_5 end ] start
						 */

						currentComponent = "tDBInput_5";

					}
				} finally {
					if (rs_tDBInput_5 != null) {
						rs_tDBInput_5.close();
					}
					if (stmt_tDBInput_5 != null) {
						stmt_tDBInput_5.close();
					}
					if (conn_tDBInput_5 != null && !conn_tDBInput_5.isClosed()) {

						conn_tDBInput_5.close();

						if ("com.mysql.cj.jdbc.Driver".equals((String) globalMap.get("driverClass_"))
								&& routines.system.BundleUtils.inOSGi()) {
							Class.forName("com.mysql.cj.jdbc.AbandonedConnectionCleanupThread")
									.getMethod("checkedShutdown").invoke(null, (Object[]) null);
						}

					}
				}
				globalMap.put("tDBInput_5_NB_LINE", nb_line_tDBInput_5);

				ok_Hash.put("tDBInput_5", true);
				end_Hash.put("tDBInput_5", System.currentTimeMillis());

				/**
				 * [tDBInput_5 end ] stop
				 */

				/**
				 * [tAdvancedHash_row10 end ] start
				 */

				currentComponent = "tAdvancedHash_row10";

				tHash_Lookup_row10.endPut();

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row10");
				}

				ok_Hash.put("tAdvancedHash_row10", true);
				end_Hash.put("tAdvancedHash_row10", System.currentTimeMillis());

				/**
				 * [tAdvancedHash_row10 end ] stop
				 */

			} // end the resume

		} catch (java.lang.Exception e) {

			TalendException te = new TalendException(e, currentComponent, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			try {

				/**
				 * [tDBInput_5 finally ] start
				 */

				currentComponent = "tDBInput_5";

				/**
				 * [tDBInput_5 finally ] stop
				 */

				/**
				 * [tAdvancedHash_row10 finally ] start
				 */

				currentComponent = "tAdvancedHash_row10";

				/**
				 * [tAdvancedHash_row10 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tDBInput_5_SUBPROCESS_STATE", 1);
	}

	public String resuming_logs_dir_path = null;
	public String resuming_checkpoint_path = null;
	public String parent_part_launcher = null;
	private String resumeEntryMethodName = null;
	private boolean globalResumeTicket = false;

	public boolean watch = false;
	// portStats is null, it means don't execute the statistics
	public Integer portStats = null;
	public int portTraces = 4334;
	public String clientHost;
	public String defaultClientHost = "localhost";
	public String contextStr = "Default";
	public boolean isDefaultContext = true;
	public String pid = "0";
	public String rootPid = null;
	public String fatherPid = null;
	public String fatherNode = null;
	public long startTime = 0;
	public boolean isChildJob = false;
	public String log4jLevel = "";

	private boolean enableLogStash;

	private boolean execStat = true;

	private ThreadLocal<java.util.Map<String, String>> threadLocal = new ThreadLocal<java.util.Map<String, String>>() {
		protected java.util.Map<String, String> initialValue() {
			java.util.Map<String, String> threadRunResultMap = new java.util.HashMap<String, String>();
			threadRunResultMap.put("errorCode", null);
			threadRunResultMap.put("status", "");
			return threadRunResultMap;
		};
	};

	protected PropertiesWithType context_param = new PropertiesWithType();
	public java.util.Map<String, Object> parentContextMap = new java.util.HashMap<String, Object>();

	public String status = "";

	public static void main(String[] args) {
		final normalize_data normalize_dataClass = new normalize_data();

		int exitCode = normalize_dataClass.runJobInTOS(args);

		System.exit(exitCode);
	}

	public String[][] runJob(String[] args) {

		int exitCode = runJobInTOS(args);
		String[][] bufferValue = new String[][] { { Integer.toString(exitCode) } };

		return bufferValue;
	}

	public boolean hastBufferOutputComponent() {
		boolean hastBufferOutput = false;

		return hastBufferOutput;
	}

	public int runJobInTOS(String[] args) {
		// reset status
		status = "";

		String lastStr = "";
		for (String arg : args) {
			if (arg.equalsIgnoreCase("--context_param")) {
				lastStr = arg;
			} else if (lastStr.equals("")) {
				evalParam(arg);
			} else {
				evalParam(lastStr + " " + arg);
				lastStr = "";
			}
		}
		enableLogStash = "true".equalsIgnoreCase(System.getProperty("audit.enabled"));

		if (clientHost == null) {
			clientHost = defaultClientHost;
		}

		if (pid == null || "0".equals(pid)) {
			pid = TalendString.getAsciiRandomString(6);
		}

		if (rootPid == null) {
			rootPid = pid;
		}
		if (fatherPid == null) {
			fatherPid = pid;
		} else {
			isChildJob = true;
		}

		if (portStats != null) {
			// portStats = -1; //for testing
			if (portStats < 0 || portStats > 65535) {
				// issue:10869, the portStats is invalid, so this client socket can't open
				System.err.println("The statistics socket port " + portStats + " is invalid.");
				execStat = false;
			}
		} else {
			execStat = false;
		}
		boolean inOSGi = routines.system.BundleUtils.inOSGi();

		if (inOSGi) {
			java.util.Dictionary<String, Object> jobProperties = routines.system.BundleUtils.getJobProperties(jobName);

			if (jobProperties != null && jobProperties.get("context") != null) {
				contextStr = (String) jobProperties.get("context");
			}
		}

		try {
			// call job/subjob with an existing context, like: --context=production. if
			// without this parameter, there will use the default context instead.
			java.io.InputStream inContext = normalize_data.class.getClassLoader()
					.getResourceAsStream("projet_talend/normalize_data_0_1/contexts/" + contextStr + ".properties");
			if (inContext == null) {
				inContext = normalize_data.class.getClassLoader()
						.getResourceAsStream("config/contexts/" + contextStr + ".properties");
			}
			if (inContext != null) {
				try {
					// defaultProps is in order to keep the original context value
					if (context != null && context.isEmpty()) {
						defaultProps.load(inContext);
						context = new ContextProperties(defaultProps);
					}
				} finally {
					inContext.close();
				}
			} else if (!isDefaultContext) {
				// print info and job continue to run, for case: context_param is not empty.
				System.err.println("Could not find the context " + contextStr);
			}

			if (!context_param.isEmpty()) {
				context.putAll(context_param);
				// set types for params from parentJobs
				for (Object key : context_param.keySet()) {
					String context_key = key.toString();
					String context_type = context_param.getContextType(context_key);
					context.setContextType(context_key, context_type);

				}
			}
			class ContextProcessing {
				private void processContext_0() {
					context.setContextType("input_file", "id_Directory");
					if (context.getStringValue("input_file") == null) {
						context.input_file = null;
					} else {
						context.input_file = (String) context.getProperty("input_file");
					}
					context.setContextType("output_file", "id_Directory");
					if (context.getStringValue("output_file") == null) {
						context.output_file = null;
					} else {
						context.output_file = (String) context.getProperty("output_file");
					}
				}

				public void processAllContext() {
					processContext_0();
				}
			}

			new ContextProcessing().processAllContext();
		} catch (java.io.IOException ie) {
			System.err.println("Could not load context " + contextStr);
			ie.printStackTrace();
		}

		// get context value from parent directly
		if (parentContextMap != null && !parentContextMap.isEmpty()) {
			if (parentContextMap.containsKey("input_file")) {
				context.input_file = (String) parentContextMap.get("input_file");
			}
			if (parentContextMap.containsKey("output_file")) {
				context.output_file = (String) parentContextMap.get("output_file");
			}
		}

		// Resume: init the resumeUtil
		resumeEntryMethodName = ResumeUtil.getResumeEntryMethodName(resuming_checkpoint_path);
		resumeUtil = new ResumeUtil(resuming_logs_dir_path, isChildJob, rootPid);
		resumeUtil.initCommonInfo(pid, rootPid, fatherPid, projectName, jobName, contextStr, jobVersion);

		List<String> parametersToEncrypt = new java.util.ArrayList<String>();
		// Resume: jobStart
		resumeUtil.addLog("JOB_STARTED", "JOB:" + jobName, parent_part_launcher, Thread.currentThread().getId() + "",
				"", "", "", "", resumeUtil.convertToJsonText(context, parametersToEncrypt));

		if (execStat) {
			try {
				runStat.openSocket(!isChildJob);
				runStat.setAllPID(rootPid, fatherPid, pid, jobName);
				runStat.startThreadStat(clientHost, portStats);
				runStat.updateStatOnJob(RunStat.JOBSTART, fatherNode);
			} catch (java.io.IOException ioException) {
				ioException.printStackTrace();
			}
		}

		java.util.concurrent.ConcurrentHashMap<Object, Object> concurrentHashMap = new java.util.concurrent.ConcurrentHashMap<Object, Object>();
		globalMap.put("concurrentHashMap", concurrentHashMap);

		long startUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		long endUsedMemory = 0;
		long end = 0;

		startTime = System.currentTimeMillis();

		this.globalResumeTicket = true;// to run tPreJob

		this.globalResumeTicket = false;// to run others jobs

		try {
			errorCode = null;
			tFileInputDelimited_2Process(globalMap);
			if (!"failure".equals(status)) {
				status = "end";
			}
		} catch (TalendException e_tFileInputDelimited_2) {
			globalMap.put("tFileInputDelimited_2_SUBPROCESS_STATE", -1);

			e_tFileInputDelimited_2.printStackTrace();

		}

		this.globalResumeTicket = true;// to run tPostJob

		end = System.currentTimeMillis();

		if (watch) {
			System.out.println((end - startTime) + " milliseconds");
		}

		endUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		if (false) {
			System.out.println(
					(endUsedMemory - startUsedMemory) + " bytes memory increase when running : normalize_data");
		}

		if (execStat) {
			runStat.updateStatOnJob(RunStat.JOBEND, fatherNode);
			runStat.stopThreadStat();
		}
		int returnCode = 0;

		if (errorCode == null) {
			returnCode = status != null && status.equals("failure") ? 1 : 0;
		} else {
			returnCode = errorCode.intValue();
		}
		resumeUtil.addLog("JOB_ENDED", "JOB:" + jobName, parent_part_launcher, Thread.currentThread().getId() + "", "",
				"" + returnCode, "", "", "");

		return returnCode;

	}

	// only for OSGi env
	public void destroy() {

	}

	private java.util.Map<String, Object> getSharedConnections4REST() {
		java.util.Map<String, Object> connections = new java.util.HashMap<String, Object>();

		return connections;
	}

	private void evalParam(String arg) {
		if (arg.startsWith("--resuming_logs_dir_path")) {
			resuming_logs_dir_path = arg.substring(25);
		} else if (arg.startsWith("--resuming_checkpoint_path")) {
			resuming_checkpoint_path = arg.substring(27);
		} else if (arg.startsWith("--parent_part_launcher")) {
			parent_part_launcher = arg.substring(23);
		} else if (arg.startsWith("--watch")) {
			watch = true;
		} else if (arg.startsWith("--stat_port=")) {
			String portStatsStr = arg.substring(12);
			if (portStatsStr != null && !portStatsStr.equals("null")) {
				portStats = Integer.parseInt(portStatsStr);
			}
		} else if (arg.startsWith("--trace_port=")) {
			portTraces = Integer.parseInt(arg.substring(13));
		} else if (arg.startsWith("--client_host=")) {
			clientHost = arg.substring(14);
		} else if (arg.startsWith("--context=")) {
			contextStr = arg.substring(10);
			isDefaultContext = false;
		} else if (arg.startsWith("--father_pid=")) {
			fatherPid = arg.substring(13);
		} else if (arg.startsWith("--root_pid=")) {
			rootPid = arg.substring(11);
		} else if (arg.startsWith("--father_node=")) {
			fatherNode = arg.substring(14);
		} else if (arg.startsWith("--pid=")) {
			pid = arg.substring(6);
		} else if (arg.startsWith("--context_type")) {
			String keyValue = arg.substring(15);
			int index = -1;
			if (keyValue != null && (index = keyValue.indexOf('=')) > -1) {
				if (fatherPid == null) {
					context_param.setContextType(keyValue.substring(0, index),
							replaceEscapeChars(keyValue.substring(index + 1)));
				} else { // the subjob won't escape the especial chars
					context_param.setContextType(keyValue.substring(0, index), keyValue.substring(index + 1));
				}

			}

		} else if (arg.startsWith("--context_param")) {
			String keyValue = arg.substring(16);
			int index = -1;
			if (keyValue != null && (index = keyValue.indexOf('=')) > -1) {
				if (fatherPid == null) {
					context_param.put(keyValue.substring(0, index), replaceEscapeChars(keyValue.substring(index + 1)));
				} else { // the subjob won't escape the especial chars
					context_param.put(keyValue.substring(0, index), keyValue.substring(index + 1));
				}
			}
		} else if (arg.startsWith("--log4jLevel=")) {
			log4jLevel = arg.substring(13);
		} else if (arg.startsWith("--audit.enabled") && arg.contains("=")) {// for trunjob call
			final int equal = arg.indexOf('=');
			final String key = arg.substring("--".length(), equal);
			System.setProperty(key, arg.substring(equal + 1));
		}
	}

	private static final String NULL_VALUE_EXPRESSION_IN_COMMAND_STRING_FOR_CHILD_JOB_ONLY = "<TALEND_NULL>";

	private final String[][] escapeChars = { { "\\\\", "\\" }, { "\\n", "\n" }, { "\\'", "\'" }, { "\\r", "\r" },
			{ "\\f", "\f" }, { "\\b", "\b" }, { "\\t", "\t" } };

	private String replaceEscapeChars(String keyValue) {

		if (keyValue == null || ("").equals(keyValue.trim())) {
			return keyValue;
		}

		StringBuilder result = new StringBuilder();
		int currIndex = 0;
		while (currIndex < keyValue.length()) {
			int index = -1;
			// judege if the left string includes escape chars
			for (String[] strArray : escapeChars) {
				index = keyValue.indexOf(strArray[0], currIndex);
				if (index >= 0) {

					result.append(keyValue.substring(currIndex, index + strArray[0].length()).replace(strArray[0],
							strArray[1]));
					currIndex = index + strArray[0].length();
					break;
				}
			}
			// if the left string doesn't include escape chars, append the left into the
			// result
			if (index < 0) {
				result.append(keyValue.substring(currIndex));
				currIndex = currIndex + keyValue.length();
			}
		}

		return result.toString();
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public String getStatus() {
		return status;
	}

	ResumeUtil resumeUtil = null;
}
/************************************************************************************************
 * 213022 characters generated by Talend Open Studio for Big Data on the 25
 * janvier 2025, 20:50:17 CET
 ************************************************************************************************/