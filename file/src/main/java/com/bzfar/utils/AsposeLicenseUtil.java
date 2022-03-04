package com.bzfar.utils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * apose获取license
 * @author Administrator
 */
public class AsposeLicenseUtil {

    /**
     * pdf鉴权
     *
     * @return
     */
    public static boolean pdfLicense() {
        boolean result2 = false;
        try {
            String license2 = "<License>\n"
                    + "  <Data>\n"
                    + "    <Products>\n"
                    + "      <Product>Aspose.Total for Java</Product>\n"
                    + "      <Product>Aspose.Words for Java</Product>\n"
                    + "    </Products>\n"
                    + "    <EditionType>Enterprise</EditionType>\n"
                    + "    <SubscriptionExpiry>20991231</SubscriptionExpiry>\n"
                    + "    <LicenseExpiry>20991231</LicenseExpiry>\n"
                    + "    <SerialNumber>8bfe198c-7f0c-4ef8-8ff0-acc3237bf0d7</SerialNumber>\n"
                    + "  </Data>\n"
                    + "  <Signature>111</Signature>\n"
                    + "</License>";
            InputStream is2 = new ByteArrayInputStream(
                    license2.getBytes("UTF-8"));
            com.aspose.pdf.License asposeLic2 = new com.aspose.pdf.License();
            asposeLic2.setLicense(is2);
            result2 = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result2;
    }


    /**
     * pdf鉴权
     *
     * @return
     */
    public static boolean wordLicense() {
        boolean result2 = false;
        try {
            String license2 = "<License>\n" +
                    "  <Data>\n" +
                    "    <Products>\n" +
                    "      <Product>Aspose.Total for Java</Product>\n" +
                    "    </Products>\n" +
                    "    <EditionType>Enterprise</EditionType>\n" +
                    "    <SubscriptionExpiry>29991231</SubscriptionExpiry>\n" +
                    "    <LicenseExpiry>29991231</LicenseExpiry>\n" +
                    "    <SerialNumber>8bfe198c-7f0c-4ef8-8ff0-acc3237bf0d7</SerialNumber>\n" +
                    "  </Data>\n" +
                    "  <Signature>sNLLKGMUdF0r8O1kKilWAGdgfs2BvJb/2Xp8p5iuDVfZXmhppo+d0Ran1P9TKdjV4ABwAgKXxJ3jcQTqE/2IRfqwnPf8itN8aFZlV3TJPYeD3yWE7IT55Gz6EijUpC7aKeoohTb4w2fpox58wWoF3SNp6sK6jDfiAUGEHYJ9pjU=</Signature>\n" +
                    "</License>";
            InputStream is2 = new ByteArrayInputStream(
                    license2.getBytes("UTF-8"));
            com.aspose.words.License asposeLic2 = new com.aspose.words.License();
            asposeLic2.setLicense(is2);
            result2 = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result2;
    }

}
