package com.javafree.cloud.admin.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @version V1.0
 * @Description: 为前端树形组件提供数据
 * @Author gwz  gwz126@126.com
 * @Date 2022/5/18 21:20
 */

public class TreeNode  implements Comparable<TreeNode> ,Serializable {

    /**
     * 标题
     */
    private String title;

    /**
     * 短标题
     */
    private String slotTitle;

    /**
     * 用于显示节点统计数据
     */
    private Integer count;

    /**
     * 排序号
     */
    private Integer order;


    /**
     * 当树为 checkable 时，设置独立节点是否展示
     */
    private boolean checkable;
    /**
     * 禁掉 checkbox
     */
    private boolean disableCheckbox;
    /**
     * 禁掉响应
     */
    private boolean disabled;
    /**
     * 自定义图标。可接收组件
     */
    private String icon;
    /**
     * 设置为叶子节点 (设置了 loadData 时有效)。为 false 时会强制将其作为父节点
     */
    @JsonProperty(value = "isLeaf")
    private boolean isLeaf;
    /**
     * 整个树范围内的所有节点的 key 值不能重复
     */
    private String key;
    /**
     * 设置节点是否可被选中
     */
    private boolean selectable;
    /**
     * 子节点
     */
    private List<TreeNode> children;

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getSlotTitle() {
        return slotTitle;
    }

    public void setSlotTitle(String slotTitle) {
        this.slotTitle = slotTitle;
    }

    public boolean isCheckable() {
        return checkable;
    }

    public void setCheckable(boolean checkable) {
        this.checkable = checkable;
    }

    public boolean isDisableCheckbox() {
        return disableCheckbox;
    }

    public void setDisableCheckbox(boolean disableCheckbox) {
        this.disableCheckbox = disableCheckbox;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public boolean getIsLeaf() {
        return isLeaf;
    }

    public void setIsLeaf(boolean leaf) {
        isLeaf = leaf;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public boolean isSelectable() {
        return selectable;
    }

    public void setSelectable(boolean selectable) {
        this.selectable = selectable;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }


    @Override
    public int compareTo(TreeNode o) {
        return this.order.compareTo(o.getOrder());
    }
}
