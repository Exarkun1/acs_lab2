package org.exarkun.acs_lab2.converters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.stereotype.Component;

@Component
public class XmlTransformer {
    private final XmlMapper xmlMapper = new XmlMapper();

    public String transformToXml(Object obj, String rootName, String xslPath) {
        try {
            return addXslTag(xmlMapper.writer().withRootName(rootName).writeValueAsString(obj), xslPath);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("XML convert exception", e);
        }
    }

    private String addXslTag(String xmlBody, String xslHref) {
        String xmlDecl = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
        String tag = "<?xml-stylesheet type=\"text/xsl\" href=\"" + xslHref + "\"?>\n";
        String trimmed = xmlBody.trim();
        if (trimmed.startsWith("<?xml")) {
            int end = trimmed.indexOf("?>");
            if (end != -1) {
                String first = trimmed.substring(0, end + 2);
                String rest = trimmed.substring(end + 2).trim();
                return first + "\n" + tag + rest;
            }
        }
        return xmlDecl + tag + trimmed;
    }
}
