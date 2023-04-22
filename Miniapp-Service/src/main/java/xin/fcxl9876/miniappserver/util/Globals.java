/*
 * $Id: Globals.java 471754 2006-11-06 14:55:09Z husted $
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package xin.fcxl9876.miniappserver.util;

import java.io.Serializable;

public class Globals implements Serializable {
	
	public static final Boolean devMode = true;
	
	public static final String systemName = "111";
	
	public static final String systemSuperUser = "root";
	
	public static final String defaultEncoding = "UTF-8";
	
	public static final String defaultLocale = "zh_CN";
	
	public static final Boolean defaultNocache = true;
	
	public static final String defaultEntityText = "Text";
	
	public static final String apache_inner_address = "http://localhost:8080/";
	
	public static final String domain_address = "";
	
	public static final String uploader_address = "f:/workspace/upload/";
	
	public static final String userbook_address = "";
   
	/**有效数据标识*/
	public static final String VALID_DATA_STATE = "('N')";
	/**已审核标识*/
	public static final String AUDIT_PASS_STATE = "(1,2,3)";
}
