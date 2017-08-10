package com.mar.iotools.configuration;

import java.io.File;
import java.util.Vector;

import com.mar.iotools.read.Read;
import com.mar.iotools.write.Writer;

/**
 * Configuration utility. Used to create a configuration file to write and read
 * configuration values.
 * 
 * @author renauld
 *
 */

public class Configuration {
	
	public static final String TRUE = "true";
	public static final String FALSE = "false";
	
	/** Path of the config file. */
	private String path;
	private Vector<String> parameters = new Vector<String>(16);
	private Vector<String> paramValues = new Vector<String>(16);
	
	/**
	 * Constructs a new Configuration object, used to write/read info to/from the config
	 * file.
	 * 
	 * @param path
	 */
	public Configuration( String path ) {
		this.path = path;
		if( new File(path).exists() == false )
			return;
		
		String[] text = Read.readText(path);
		for( int i = 0; i < text.length; ++i ) {
			String[] splitted = text[i].split("[=]");
			if( splitted.length != 2 )
				continue;
			parameters.add(splitted[0]);
			paramValues.add(splitted[1]);
		}
	}
	
	/**
	 * Saves the current values of the configuration parameters to the file.
	 */
	public void saveConfig() {
		Writer w = new Writer(path);
		for( int i = 0; i < parameters.size(); ++i ) {
			String s = parameters.get(i) + "=";
			if( paramValues.get(i) == null )
				s += "\r\n";
			else
				s += paramValues.get(i) + "\r\n";
			w.write(s, false);
		}
		w.close();
	}
	
	/**
	 * Returns the specified parameter value as a String. Returns an empty String if
	 * no value was found for this parameter.
	 * 
	 * @param param_name
	 * @return
	 */
	public String getParamValue( String param_name ) {
		for( int i = 0; i < parameters.size(); ++i )
			if( parameters.get(i).equals(param_name) )
				return paramValues.get(i);
		return "";
	}
	
	/**
	 * Returns the specified parameter value as an integer. Returns 0 if
	 * no value was found for this parameter.
	 * 
	 * @param param_name
	 * @return
	 */
	public int getParamValueInt( String param_name ) {
		for( int i = 0; i < parameters.size(); ++i )
			if( parameters.get(i).equals(param_name) )
				return Integer.parseInt(paramValues.get(i));
		return 0;
	}
	
	/**
	 * Returns the specified parameter value as a boolean. Returns false if
	 * no value was found for this parameter.
	 * 
	 * @param param_name
	 * @return
	 */
	public boolean getParamValueBoolean( String param_name ) {
		for( int i = 0; i < parameters.size(); ++i ) {
			if( parameters.get(i).equals(param_name) ) {
				if( paramValues.get(i).equals(TRUE) )
					return true;
				else
					return false;
			}
		}
		return false;
	}
	
	/**
	 * Sets the value of the specified parameter to the specified String value.
	 * 
	 * @param param_name
	 * @param value
	 */
	public void setParamValue( String param_name, String value ) {
		for( int i = 0; i < parameters.size(); ++i ) {
			if( parameters.get(i).equals(param_name) ) {
				paramValues.set(i, value);
				return;
			}
		}
		
		parameters.add(param_name);
		paramValues.add(value);
	}
	
	/**
	 * Sets the value of the specified parameter to the specified int value.
	 * 
	 * @param param_name
	 * @param value
	 */
	public void setParamValue( String param_name, int value ) {
		for( int i = 0; i < parameters.size(); ++i ) {
			if( parameters.get(i).equals(param_name) ) {
				paramValues.set(i, Integer.toString(value));
				return;
			}
		}
		
		parameters.add(param_name);
		paramValues.add(Integer.toString(value));
	}
	
	/**
	 * Sets the value of the specified parameter to the specified boolean value.
	 * 
	 * @param param_name
	 * @param value
	 */
	public void setParamValue( String param_name, boolean value ) {
		for( int i = 0; i < parameters.size(); ++i ) {
			if( parameters.get(i).equals(param_name) ) {
				if( value == true )
					paramValues.set(i, TRUE);
				else
					paramValues.set(i, FALSE);
				return;
			}
		}
		
		parameters.add(param_name);
		if( value == true )
			paramValues.add(TRUE);
		else
			paramValues.add(FALSE);
	}
}