package com.advantage.common.dto;

/**
 * Describes the product attribute in the ProductDTO
 */
public class AttributeItem {
    private String attributeName;
    private String attributeValue;

    public AttributeItem() {
    }

    public AttributeItem(String attributeName, String attributeValue) {
        this.attributeName = attributeName;
        this.attributeValue = attributeValue;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public String getAttributeValue() {
        return attributeValue;
    }

    public void setAttributeValue(String attributeValue) {
        this.attributeValue = attributeValue;
    }
}
