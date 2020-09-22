package com.admin.web.websocket;

import com.corundumstudio.socketio.SocketIOClient;
import lombok.Data;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@Data
public class MonitorItemVO implements Serializable {

	private static final long serialVersionUID = 111L;

	private SocketIOClient client;
	
	private String inititem;// cpu初始化监控项

	private String item;// 监控项

	private List<String> itemList;	//监控项集合

	private String subitem[];// 监控项子项

	private String startTime; // 开始时间

	private String endTime;// 截止时间

	private String lastTime; // 请求时间

	private String time; // 请求时间

	private Integer intervalTime;// 时间间隔

	private String hostName;// 主机名称

	private String hostType;// 主机类型

	private String requestType;// 请求数据类型

	private String ip;// 主机ip

	private String code; // 主机code值

	private String unit; // 单位名称

	private String ding;// 监控项

	private String regionCode;//多数据源code

	private String projectId;   //项目id

	private String projectName;   //项目名称

	/* *******************************
	 * 以下是工具
	 ******************************* */

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("{");
		sb.append("\"inititem\":");
		if (null == inititem) sb.append("null");
		else sb.append('\"').append(inititem).append('\"');
		sb.append(",\"item\":");
		if (null == item) sb.append("null");
		else sb.append('\"').append(item).append('\"');
		sb.append(",\"itemList\":").append(itemList);
		sb.append(",\"subitem\":").append(Arrays.toString(subitem));
		sb.append(",\"startTime\":");
		if (null == startTime) sb.append("null");
		else sb.append('\"').append(startTime).append('\"');
		sb.append(",\"endTime\":");
		if (null == endTime) sb.append("null");
		else sb.append('\"').append(endTime).append('\"');
		sb.append(",\"lastTime\":");
		if (null == lastTime) sb.append("null");
		else sb.append('\"').append(lastTime).append('\"');
		sb.append(",\"time\":");
		if (null == time) sb.append("null");
		else sb.append('\"').append(time).append('\"');
		sb.append(",\"intervalTime\":").append(intervalTime);
		sb.append(",\"hostName\":");
		if (null == hostName) sb.append("null");
		else sb.append('\"').append(hostName).append('\"');
		sb.append(",\"hostType\":");
		if (null == hostType) sb.append("null");
		else sb.append('\"').append(hostType).append('\"');
		sb.append(",\"requestType\":");
		if (null == requestType) sb.append("null");
		else sb.append('\"').append(requestType).append('\"');
		sb.append(",\"ip\":");
		if (null == ip) sb.append("null");
		else sb.append('\"').append(ip).append('\"');
		sb.append(",\"code\":");
		if (null == code) sb.append("null");
		else sb.append('\"').append(code).append('\"');
		sb.append(",\"unit\":");
		if (null == unit) sb.append("null");
		else sb.append('\"').append(unit).append('\"');
		sb.append(",\"ding\":");
		if (null == ding) sb.append("null");
		else sb.append('\"').append(ding).append('\"');
		sb.append(",\"regionCode\":");
		if (null == regionCode) sb.append("null");
		else sb.append('\"').append(regionCode).append('\"');
		sb.append(",\"projectId\":");
		if (null == projectId) sb.append("null");
		else sb.append('\"').append(projectId).append('\"');
		sb.append(",\"projectName\":");
		if (null == projectName) sb.append("null");
		else sb.append('\"').append(projectName).append('\"');
		sb.append('}');
		return sb.toString();
	}
}
