package com.ly.art.framework.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Property配置
 */
@Component
@Data
public class PropertyConfig {

    @Value("${siteName}")
    private String siteName;

    @Value("${site.copyright}")
    private String siteCopyright;

    @Value("${asset.domain}")
    private String assetDomain;

    @Value("${asset.version}")
    private String assetVersion;
}
