/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package no.ntnu.osnap.social;

import android.util.Log;

import org.json.JSONObject;
import org.json.JSONException;

import java.util.HashMap;

/**
 * Represents a group.
 * @author Emanuele 'lemrey' Di Santo
 */
public class Group extends Model {
	
	private JSONObject jsonModel;
	
	public static final HashMap<String, String> Facebook =
	new HashMap<String, String>() {{
		put("name", "title");
		//put("","");
	}};
	
	public static enum REQUEST {
		GET_FULL,
		GET_MEMBERS,
		GET_FEED,
		POST_MESSAGE
	};
	
	/**
	 * Constructs an empty Group.
	 */
	public Group () {
		jsonModel = new JSONObject();
	}
	
	/** Constructs a Group from a source JSON text string.
	 * 
	 * @param json a JSON string, starting with { and ending with }.
	 * @throws JSONException if there's a syntax error or duplicated key.
	 */
	public Group(String json) throws JSONException {
		try {
			jsonModel = new JSONObject(json);
		} catch (JSONException ex) {
			Log.d(TAG, ex.toString());
			throw(ex);
		}
	}
	
	/** Constructs a Group from a {@code JSONObject} instance.
	 * 
	 * @param object a JSONObject
	 * @throws JSONException if there's a syntax error or duplicated key.
	 */	
	public Group(JSONObject object) throws JSONException {
		try {
			jsonModel = new JSONObject(object.toString());
		} catch (JSONException ex) {
			Log.d(TAG, ex.toString());
			throw(ex);
		}
	}
	
	/**
	 * 
	 * @param json
	 * @param transl
	 * @throws JSONException 
	 */
	public Group (String json, HashMap<String, String> transl)
			throws JSONException {
		this(json);
		translate(transl);		
	}
	
	/**
	 * 
	 * @param object
	 * @param transl
	 * @throws JSONException 
	 */
	public Group (JSONObject object, HashMap<String, String> transl)
			throws JSONException {
		this(object);
		translate(transl);
		
	}
	
	/** Returns the title of this group.
	 * 
	 * @return the value of the key 'title' as a string
	 * or an empty string if the key doesn't exist.
	 */
	public String getTitle() {
		String ret;
		ret = jsonModel.optString("title");
		if (ret.equals("")) {
			Log.d(TAG, "key 'title' doesn't exist.");
		}
		return ret;
	}
	
	/**
	 * Returns the description of this group.
	 * 
	 * @return the value of the key 'description' as a string
	 * or an empty string if the key doesn't exist.
	 */
	public String getDescription() {
		String ret;
		ret = jsonModel.optString("description");
		if (ret.equals("")) {
			Log.d(TAG, "key 'description' doesn't exist.");
		}
		return ret;
	}
}
