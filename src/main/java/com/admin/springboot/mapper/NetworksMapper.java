//package com.admin.springboot.mapper;
//
//import com.troila.klcloud.cloudNetwork.entity.VpcNetworkEntity;
//import com.troila.klcloud.cloudNetwork.model.DevNetworkVo;
//import com.troila.klcloud.cloudNetwork.model.DeviceNetwork;
//import com.troila.klcloud.cloudNetwork.model.VpcNetworkVo;
//import org.apache.ibatis.annotations.Mapper;
//import org.apache.ibatis.annotations.Param;
//
//import java.util.List;
//
//@Mapper
//public interface NetworksMapper {
//
//	//vpc网络
//	List<VpcNetworkEntity> queryVpcNetworksList(VpcNetworkVo vpcNetworkVo)throws Exception;
//
//	//裸金属公网网络
//	List<VpcNetworkEntity> queryDevPubNetworksList(VpcNetworkVo vpcNetworkVo)throws Exception;
//
//	//获取绑定/未绑定子网的网络列表
//	List<VpcNetworkEntity> queryBindVpcNetworks(VpcNetworkVo vpcNetworkVo)throws Exception;
//
//	//获取裸金属网络列表
//	List<VpcNetworkEntity> queryDeviceNetworkList(VpcNetworkVo vpcNetworkVo)throws Exception;
//
//	//校验vpc网络名称
//	List<VpcNetworkEntity> checkVpcNetworkName(VpcNetworkVo vpcNetworkVo);
//
//	//校验裸金属网络名称
//	List<VpcNetworkEntity> checkDevNetworkName(DevNetworkVo devNetworkVo);
//
//
//	//记录裸金属网络
//	void addDeviceNetwork(DeviceNetwork deviceNetwork)throws Exception;
//
//	//根据报警器code集合删除主机
//	void deleteDeviceNetworks(@Param(value = "list") List<String> list) throws Exception;
//}