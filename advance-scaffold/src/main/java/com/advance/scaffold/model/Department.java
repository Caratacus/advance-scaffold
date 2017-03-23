package com.advance.scaffold.model;

import java.util.Date;

/*
数据库建表语句在末尾 测试数据插入  默认数据库是mysql  可根据实际情况修改
 */
public class Department implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    // Fields
    private String depart_id;//唯一id
    private String parentid;//父id
    private String depart_name;
    private String depart_type;
    private Integer depart_order;
    private String status;
    private String address;
    private String depart_phone;
    private String organ_id;
    private String icon;
    private Date del_time;
    private Date create_time;
    private String name; //为了实现ztree增加该字段

    public Department() {
    }

    public Department(String depart_id, String depart_name, String depart_type, Integer depart_order, String status, String parentid, String address, String depart_phone, String organ_id, Date del_time, Date create_time,String icon) {
        this.depart_id = depart_id;
        this.depart_name = depart_name;
        this.depart_type = depart_type;
        this.depart_order = depart_order;
        this.status = status;
        this.parentid = parentid;
        this.address = address;
        this.depart_phone = depart_phone;
        this.organ_id = organ_id;
        this.del_time = del_time;
        this.create_time = create_time;
        this.name = depart_name;
        this.icon = icon;
    }

    public String getDepart_id() {
        return depart_id;
    }

    public void setDepart_id(String depart_id) {
        this.depart_id = depart_id;
    }

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

    public String getDepart_name() {
        return depart_name;
    }

    public void setDepart_name(String depart_name) {
        this.depart_name = depart_name;
    }

    public String getDepart_type() {
        return depart_type;
    }

    public void setDepart_type(String depart_type) {
        this.depart_type = depart_type;
    }

    public Integer getDepart_order() {
        return depart_order;
    }

    public void setDepart_order(Integer depart_order) {
        this.depart_order = depart_order;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDepart_phone() {
        return depart_phone;
    }

    public void setDepart_phone(String depart_phone) {
        this.depart_phone = depart_phone;
    }

    public String getOrgan_id() {
        return organ_id;
    }

    public void setOrgan_id(String organ_id) {
        this.organ_id = organ_id;
    }

    public Date getDel_time() {
        return del_time;
    }

    public void setDel_time(Date del_time) {
        this.del_time = del_time;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = this.depart_name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return "Department{" + "depart_id=" + depart_id + ", parentid=" + parentid + ", depart_name=" + depart_name + ", depart_type=" + depart_type + ", depart_order=" + depart_order + ", status=" + status + ", address=" + address + ",icon="+icon+", depart_phone=" + depart_phone + ", organ_id=" + organ_id + ", del_time=" + del_time + ", create_time=" + create_time + ", name=" + name + '}';
    }

}
/*
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `department_info`
-- ----------------------------
DROP TABLE IF EXISTS `department_info`;
CREATE TABLE `department_info` (
  `depart_id` varchar(32) NOT NULL COMMENT '部门id',
  `depart_name` varchar(50) DEFAULT NULL COMMENT '部门名称',
  `parentid` varchar(32) DEFAULT NULL COMMENT '部门父id',
  `depart_type` int(11) DEFAULT '1' COMMENT '类型(1 联社 2部门 3网点)',
  `depart_order` int(11) DEFAULT NULL COMMENT '排序号',
  `del_time` datetime DEFAULT NULL COMMENT '删除时间',
  `address` varchar(500) DEFAULT NULL,
  `depart_phone` varchar(11) DEFAULT NULL,
  `organ_id` varchar(32) DEFAULT NULL COMMENT '机构ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `status` int(11) DEFAULT '1' COMMENT '状态（1：可见，2：隐藏）',
  PRIMARY KEY (`depart_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of department_info
-- ----------------------------
INSERT INTO `department_info` VALUES ('0c0a656e48ac4070bc9d815c7d941fc5', '办公室', null, '1', null, null, null, null, null, '2016-07-05 14:59:50', '1');
INSERT INTO `department_info` VALUES ('0e655c7c298a4efdb3fafeeac2c8a049', '昆明北京路营业厅', 'ffadacc1bd964999a666b87642ea4813', '3', null, null, '昆明北京路', '123456', null, null, '1');
INSERT INTO `department_info` VALUES ('1bdf8270e8654e40866f2a95f3a3b37d', '信贷部', null, '1', null, null, null, null, null, '2016-07-10 15:42:05', '1');
INSERT INTO `department_info` VALUES ('689786b7253a405aa94008f81e222512', '昆明信贷', '1bdf8270e8654e40866f2a95f3a3b37d', '2', null, null, '昆明', '123456', null, null, '1');
INSERT INTO `department_info` VALUES ('7dba664830e8480cb5dbf142bf668262', '玉溪信贷部', '1bdf8270e8654e40866f2a95f3a3b37d', '2', null, null, '玉溪', '123456', null, null, '1');
INSERT INTO `department_info` VALUES ('9213d0c55fa44185bb7559eeed7ceea1', '营业部', null, '1', null, null, null, null, null, '2016-07-05 15:00:06', '1');
INSERT INTO `department_info` VALUES ('b28c8b7074db49b1b835beb894feace7', '党建部', null, '1', null, null, null, null, null, '2016-07-10 15:41:04', '1');
INSERT INTO `department_info` VALUES ('b436cb4faef7456aa1e169c7fa4cfffa', '昆明拓东营业厅', 'ffadacc1bd964999a666b87642ea4813', '3', null, null, '昆明拓东', '123456', null, null, '1');
INSERT INTO `department_info` VALUES ('c30287fd04f943bebe2e2d441dcaab9d', '玉溪信贷部', '1bdf8270e8654e40866f2a95f3a3b37d', '1', null, '2016-07-10 15:54:02', '玉溪', '11', 'f7b9d748061948e693b54d86c9344a81', null, '1');
INSERT INTO `department_info` VALUES ('c54f9a0d66434ffa87054004cb1dca01', '昆明办公室', '0c0a656e48ac4070bc9d815c7d941fc5', '2', null, null, '昆明', '123456', null, null, '1');
INSERT INTO `department_info` VALUES ('d0f5d72cb64c40f3be0a510141467253', '玉溪营业厅', '9213d0c55fa44185bb7559eeed7ceea1', '3', null, null, '玉溪', '123456', null, null, '1');
INSERT INTO `department_info` VALUES ('e11fdd8b107647bda4fbef1b8ebb015c', '玉溪办公室', '0c0a656e48ac4070bc9d815c7d941fc5', '2', null, null, '玉溪', '123456', null, null, '1');
INSERT INTO `department_info` VALUES ('ec3d13c0c6db4e2084470eda5f8b294e', '科技部', null, '1', null, null, null, null, null, '2016-07-10 15:41:34', '1');
INSERT INTO `department_info` VALUES ('ffadacc1bd964999a666b87642ea4813', '昆明营业厅tqm', 'c54f9a0d66434ffa87054004cb1dca01', '2', null, null, '昆明', '123456', null, null, '1');

 */
