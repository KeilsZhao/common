package com.bzfar.enums;

import lombok.Getter;

/**
 * @author Ethons
 * @date 2021-7-6 17:39
 */
@Getter
public enum FileOrigin {
    /** 物理地址 */
    ADDRESS,
    /** 网络地址 */
    URL,
    /** FTP */
    FTP
}
