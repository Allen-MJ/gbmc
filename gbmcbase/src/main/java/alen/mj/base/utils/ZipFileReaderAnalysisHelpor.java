package alen.mj.base.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.content.res.XmlResourceParser;
import android.util.Xml;

import alen.mj.base.entry.A01;
import alen.mj.base.entry.A02;
import alen.mj.base.entry.A05;
import alen.mj.base.entry.A06;
import alen.mj.base.entry.A08;
import alen.mj.base.entry.A14;
import alen.mj.base.entry.A15;
import alen.mj.base.entry.A36;
import alen.mj.base.entry.A57;
import alen.mj.base.entry.A99Z1;
import alen.mj.base.entry.B01;
import alen.mj.base.entry.M02;
import alen.mj.base.entry.M03;
import alen.mj.base.entry.MCSubEntry;
import alen.mj.base.entry.gwyinfo;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.util.CRCUtil;

/** read data and analysis it
 * Created by Administrator on 2017/11/28 0028.
 */
public class ZipFileReaderAnalysisHelpor implements Serializable {

	private static final long serialVersionUID = 1L;
    private static String encordChart = "utf-8";

    public ZipFileReaderAnalysisHelpor(){
    }

    /**
     * 读取文件为inputstream
     * @param foldPath
     * @param fileName
     * @return
     */
    public InputStream getReadFilePath(String foldPath, String fileName){
        File file = new File(foldPath + fileName+".xml");
        if(!file.exists()){
            return null;
        }
        try {
            InputStream is = new FileInputStream(file);
            return is;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 利用第三方的zip4j 来解压数据
     * @param srcPath 源文件 zip
     * @param destPath 解压后的 存放文件
     * @param password 加密密码，无密码可以不传递
     * @return
     */
    public boolean unzip(String srcPath, String destPath, String password){
        File srcZipFile = new File(srcPath);
        try {
            ZipFile zipFile = new ZipFile(srcZipFile) ;
            if(!zipFile.isValidZipFile()){
                throw new ZipException("zip file is not valid,maybe it is broken.");
            }
            if(zipFile.isEncrypted()){ // 判断是否需要密码
                zipFile.setPassword(password.toCharArray()); // 设置密码
            }
            // 将文件抽出到解压目录（解压）
            zipFile.extractAll(destPath);
        }catch (ZipException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * todo delete files
     * @param filePath
     */
    public void doDeleteFile(String filePath){
        File file = new File(filePath);
        if(file.exists()){
            file.delete();
        }
    }

    /**
     *  todo analysis basic gwyinfo table
     * @param inputStream
     * @return
     */
    public gwyinfo doGWYINFOAnalysisEvent(InputStream inputStream){
        if(inputStream == null){
            return null;
        }
        XmlPullParser xrp = Xml.newPullParser();
        try {
            xrp.setInput(inputStream, encordChart);
            gwyinfo mgwyinfo = new gwyinfo();

            // 直到文档的结尾处
            while (xrp.getEventType() != XmlResourceParser.END_DOCUMENT) {
                // 如果遇到了开始标签
                if (xrp.getEventType() == XmlResourceParser.START_TAG) {
                    doAnalysisNode(mgwyinfo, xrp);
                    doAnalysisTablelist(mgwyinfo ,xrp);
                }
                xrp.next();// 获取解析下一个事件
            }
            return mgwyinfo;
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    // todo analysis gwyinfo basic information
    private void doAnalysisNode(gwyinfo mgwyinfo,XmlPullParser xrp)
            throws XmlPullParserException, IOException {
        String tagName = xrp.getName();// 获取标签的名字
        if (tagName.equals("node")) {
            // 通过属性名来获取属性值
            mgwyinfo.setNodeMapKey2Value(xrp.getAttributeValue(null,"name"),
                    xrp.getAttributeValue(null,"value"));
        }
    }
    // todo analysis gwyinfo tables
    private void doAnalysisTablelist(gwyinfo mgwyinfo,XmlPullParser xrp)
            throws XmlPullParserException, IOException {
        String tagName = xrp.getName();// 获取标签的名字
        if (tagName.equals("tablelist") && xrp.getAttributeCount() > 0) {
        	String tabNames = xrp.getAttributeValue(null,"name");
//        	if("A01".equals(tabNames)){
//        		mgwyinfo.addFileName(0, tabNames);
//        	}else{
        		mgwyinfo.addFileName(tabNames);
//        	}
        }
    }
    
    /**
     * todo analysis A01
     * @param inputStream
     * @return
     */
    public List<A01> doA01AnalysisEvent(InputStream inputStream) {
        if(inputStream == null){
            return null;
        }
        XmlPullParser xrp = Xml.newPullParser();
        try {
            xrp.setInput(inputStream, encordChart);
            List<A01> list = new ArrayList<A01>();

            // 直到文档的结尾处
            while (xrp.getEventType() != XmlResourceParser.END_DOCUMENT) {
                // 如果遇到了开始标签
                if (xrp.getEventType() == XmlResourceParser.START_TAG) {
                    String tagName = xrp.getName();// 获取标签的名字
                    if (tagName.equals("row")) {
                        A01 course = new A01();
                        // 通过属性名来获取属性值
                        course.setA0000(xrp.getAttributeValue(null, "A0000"));
                        course.setA14Z101(xrp.getAttributeValue(null, "A14Z101"));
                        course.setA15Z101(xrp.getAttributeValue(null, "A15Z101"));
                        course.setA0101(xrp.getAttributeValue(null, "A0101"));
                        course.setA0102(xrp.getAttributeValue(null, "A0102"));
                        course.setA0104(xrp.getAttributeValue(null, "A0104"));
                        course.setA0107(xrp.getAttributeValue(null, "A0107"));
                        course.setA0111A(xrp.getAttributeValue(null, "A0111A"));
                        course.setA0114A(xrp.getAttributeValue(null, "A0114A"));
                        course.setA0115A(xrp.getAttributeValue(null, "A0115A"));
                        course.setA0117(xrp.getAttributeValue(null, "A0117"));
                        course.setA0120(xrp.getAttributeValue(null, "A0120"));
                        course.setA0121(xrp.getAttributeValue(null, "A0121"));
                        course.setA0122(xrp.getAttributeValue(null, "A0122"));
                        course.setA0128(xrp.getAttributeValue(null, "A0128"));
                        course.setA0134(xrp.getAttributeValue(null, "A0134"));
                        course.setA0140(xrp.getAttributeValue(null, "A0140"));
                        course.setA0141(xrp.getAttributeValue(null, "A0141"));
                        course.setA0144(xrp.getAttributeValue(null, "A0144"));
                        course.setA0160(xrp.getAttributeValue(null, "A0160"));
                        course.setA0163(xrp.getAttributeValue(null, "A0163"));
                        course.setA0165(xrp.getAttributeValue(null, "A0165"));
                        course.setA0180(xrp.getAttributeValue(null, "A0180"));
                        course.setA0184(xrp.getAttributeValue(null, "A0184"));
                        course.setA0187A(xrp.getAttributeValue(null, "A0187A"));
                        course.setA0192(xrp.getAttributeValue(null, "A0192"));
                        course.setA0192A(xrp.getAttributeValue(null, "A0192A"));
                        course.setA0192C(xrp.getAttributeValue(null, "A0192C"));
                        course.setA0192E(xrp.getAttributeValue(null, "A0192E"));
                        course.setA0195(xrp.getAttributeValue(null, "A0195"));
                        course.setA0196(xrp.getAttributeValue(null, "A0196"));
                        course.setA0197(xrp.getAttributeValue(null, "A0197"));
                        course.setA0221(xrp.getAttributeValue(null, "A0221"));
                        course.setA0288(xrp.getAttributeValue(null, "A0288"));
                        course.setA1701(xrp.getAttributeValue(null, "A1701"));
                        course.setA2949(xrp.getAttributeValue(null, "A2949"));
                        course.setA3921(xrp.getAttributeValue(null, "A3921"));
                        course.setA3927(xrp.getAttributeValue(null, "A3927"));
                        course.setQRZXL(xrp.getAttributeValue(null, "QRZXL"));
                        course.setQRZXLXX(xrp.getAttributeValue(null, "QRZXLXX"));
                        course.setQRZXW(xrp.getAttributeValue(null, "QRZXW"));
                        course.setQRZXWXX(xrp.getAttributeValue(null, "QRZXWXX"));
                        course.setZGXLXX(xrp.getAttributeValue(null, "ZGXLXX"));
                        course.setZGXL(xrp.getAttributeValue(null, "ZGXL"));
                        course.setZGXW(xrp.getAttributeValue(null, "ZGXW"));
                        course.setZGXWXX(xrp.getAttributeValue(null, "ZGXWXX"));
                        course.setZZXL(xrp.getAttributeValue(null, "ZZXL"));
                        course.setZZXLXX(xrp.getAttributeValue(null, "ZZXLXX"));
                        course.setZZXW(xrp.getAttributeValue(null, "ZZXW"));
                        course.setZZXWXX(xrp.getAttributeValue(null, "ZZXWXX"));
                        try { // 2017-12-25 add
                        	 course.setZZZS(xrp.getAttributeValue(null, "ZZZS"));
                             course.setQRZZS(xrp.getAttributeValue(null, "QRZZS"));
						} catch (Exception e) {
						}
                        //再处理course的子节点
                        list.add(course);
                    }
                }
                xrp.next();// 获取解析下一个事件
            }
            return list;
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *  todo analysis A02
     * @param inputStream
     * @return
     */
    public List<A02> doA02AnalysisEvent(InputStream inputStream) {
        if(inputStream == null){
            return null;
        }
        try {
            XmlPullParser xrp = Xml.newPullParser();
            xrp.setInput(inputStream, encordChart);
            List<A02> list = new ArrayList<A02>();
            // 直到文档的结尾处
            while (xrp.getEventType() != XmlResourceParser.END_DOCUMENT) {
                // 如果遇到了开始标签
                if (xrp.getEventType() == XmlResourceParser.START_TAG) {
                    String tagName = xrp.getName();// 获取标签的名字
                    if (tagName.equals("row")) {
                        A02 course = new A02();
                        // 通过属性名来获取属性值
                        course.setA0000(xrp.getAttributeValue(null, "A0000"));
                        course.setA0201A(xrp.getAttributeValue(null, "A0201A"));
                        course.setA0201B(xrp.getAttributeValue(null, "A0201B"));
                        course.setA0201D(xrp.getAttributeValue(null, "A0201D"));
                        course.setA0201E(xrp.getAttributeValue(null, "A0201E"));
                        course.setA0215A(xrp.getAttributeValue(null, "A0215A"));
                        course.setA0219(xrp.getAttributeValue(null, "A0219"));
                        course.setA0223(xrp.getAttributeValue(null, "A0223"));
                        course.setA0225(xrp.getAttributeValue(null, "A0225"));
                        course.setA0243(xrp.getAttributeValue(null, "A0243"));
                        course.setA0245(xrp.getAttributeValue(null, "A0245"));
                        course.setA0247(xrp.getAttributeValue(null, "A0247"));
                        course.setA0251B(xrp.getAttributeValue(null, "A0251B"));
                        course.setA0255(xrp.getAttributeValue(null, "A0255"));
                        course.setA0265(xrp.getAttributeValue(null, "A0265"));
                        course.setA0267(xrp.getAttributeValue(null, "A0267"));
                        course.setA0272(xrp.getAttributeValue(null, "A0272"));
                        course.setA0279(xrp.getAttributeValue(null, "A0279"));
                        course.setA0281(xrp.getAttributeValue(null, "A0281"));
                        try { // 原任职务    A0215B
                        	 course.setA0215B(xrp.getAttributeValue(null, "A0215B"));
						} catch (Exception e) {
						}
                        //再处理course的子节点
                        list.add(course);
                    }
                }
                xrp.next();// 获取解析下一个事件
            }
            return list;
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
       return null;
    }

    /**
     * todo analysis A05
     * @param inputStream
     * @return
     */
    public List<A05> doA05AnalysisEvent(InputStream inputStream) {
        if(inputStream == null){
            return null;
        }
        try {
            XmlPullParser xrp = Xml.newPullParser();
            xrp.setInput(inputStream, encordChart);
            List<A05> list = new ArrayList<A05>();
            // 直到文档的结尾处
            while (xrp.getEventType() != XmlResourceParser.END_DOCUMENT) {
                // 如果遇到了开始标签
                if (xrp.getEventType() == XmlResourceParser.START_TAG) {
                    String tagName = xrp.getName();// 获取标签的名字
                    if (tagName.equals("row")) {
                        A05 course = new A05();
                        // 通过属性名来获取属性值
                        course.setA0000(xrp.getAttributeValue(null, "A0000"));
                        course.setA0501B(xrp.getAttributeValue(null, "A0501B"));
                        course.setA0504(xrp.getAttributeValue(null, "A0504"));
                        course.setA0511(xrp.getAttributeValue(null, "A0511"));
                        course.setA0517(xrp.getAttributeValue(null, "A0517"));
                        course.setA0524(xrp.getAttributeValue(null, "A0524"));
                        course.setA0531(xrp.getAttributeValue(null, "A0531"));
                        //再处理course的子节点
                        list.add(course);
                    }
                }
                xrp.next();// 获取解析下一个事件
            }
            return list;
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * todo analysis A06
     * @param inputStream
     * @return
     */
    public List<A06> doA06AnalysisEvent(InputStream inputStream) {
        if(inputStream == null){
            return null;
        }
        try {
            XmlPullParser xrp = Xml.newPullParser();
            xrp.setInput(inputStream, encordChart);
            List<A06> list = new ArrayList<A06>();
            // 直到文档的结尾处
            while (xrp.getEventType() != XmlResourceParser.END_DOCUMENT) {
                // 如果遇到了开始标签
                if (xrp.getEventType() == XmlResourceParser.START_TAG) {
                    String tagName = xrp.getName();// 获取标签的名字
                    if (tagName.equals("row")) {
                        A06 course = new A06();
                        // 通过属性名来获取属性值
                        course.setA0000(xrp.getAttributeValue(null, "A0000"));
                        course.setA0601(xrp.getAttributeValue(null, "A0601"));
                        course.setA0602(xrp.getAttributeValue(null, "A0602"));
                        course.setA0604(xrp.getAttributeValue(null, "A0604"));
                        course.setA0607(xrp.getAttributeValue(null, "A0607"));
                        course.setA0611(xrp.getAttributeValue(null, "A0611"));
                        course.setA0699(xrp.getAttributeValue(null, "A0699"));
                        //再处理course的子节点
                        list.add(course);
                    }
                }
                xrp.next();// 获取解析下一个事件
            }
            return list;
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * todo analysis A08
     * @param inputStream
     * @return
     */
    public List<A08> doA08AnalysisEvent(InputStream inputStream) {
        if(inputStream == null){
            return null;
        }
        try {
            XmlPullParser xrp = Xml.newPullParser();
            xrp.setInput(inputStream, encordChart);
            List<A08> list = new ArrayList<A08>();
            // 直到文档的结尾处
            while (xrp.getEventType() != XmlResourceParser.END_DOCUMENT) {
                // 如果遇到了开始标签
                if (xrp.getEventType() == XmlResourceParser.START_TAG) {
                    String tagName = xrp.getName();// 获取标签的名字
                    if (tagName.equals("row")) {
                        A08 course = new A08();
                        // 通过属性名来获取属性值
                        course.setA0000(xrp.getAttributeValue(null, "A0000"));
                        course.setA0801A(xrp.getAttributeValue(null, "A0801A"));
                        course.setA0801B(xrp.getAttributeValue(null, "A0801B"));
                        course.setA0804(xrp.getAttributeValue(null, "A0804"));
                        course.setA0807(xrp.getAttributeValue(null, "A0807"));
                        course.setA0811(xrp.getAttributeValue(null, "A0811"));
                        course.setA0814(xrp.getAttributeValue(null, "A0814"));
                        course.setA0824(xrp.getAttributeValue(null, "A0824"));
                        course.setA0827(xrp.getAttributeValue(null, "A0827"));
                        course.setA0834(xrp.getAttributeValue(null, "A0834"));
                        course.setA0835(xrp.getAttributeValue(null, "A0835"));
                        course.setA0837(xrp.getAttributeValue(null, "A0837"));
                        course.setA0899(xrp.getAttributeValue(null, "A0899"));
                        course.setA0901A(xrp.getAttributeValue(null, "A0901A"));
                        course.setA0901B(xrp.getAttributeValue(null, "A0901B"));
                        course.setA0904(xrp.getAttributeValue(null, "A0904"));
                        //再处理course的子节点
                        list.add(course);
                    }
                }
                xrp.next();// 获取解析下一个事件
            }
            return list;
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * todo analysis A14
     * @param inputStream
     * @return
     */
    public List<A14> doA14AnalysisEvent(InputStream inputStream) {
        if(inputStream == null){
            return null;
        }
        try {
            XmlPullParser xrp = Xml.newPullParser();
            xrp.setInput(inputStream, encordChart);
            List<A14> list = new ArrayList<A14>();
            // 直到文档的结尾处
            while (xrp.getEventType() != XmlResourceParser.END_DOCUMENT) {
                // 如果遇到了开始标签
                if (xrp.getEventType() == XmlResourceParser.START_TAG) {
                    String tagName = xrp.getName();// 获取标签的名字
                    if (tagName.equals("row")) {
                        A14 course = new A14();
                        // 通过属性名来获取属性值
                        course.setA0000(xrp.getAttributeValue(null, "A0000"));
                        course.setA1404A(xrp.getAttributeValue(null, "A1404A"));
                        course.setA1404B(xrp.getAttributeValue(null, "A1404B"));
                        course.setA1407(xrp.getAttributeValue(null, "A1407"));
                        course.setA1411A(xrp.getAttributeValue(null, "A1411A"));
                        course.setA1414(xrp.getAttributeValue(null, "A1414"));
                        course.setA1415(xrp.getAttributeValue(null, "A1415"));
                        course.setA1424(xrp.getAttributeValue(null, "A1424"));
                        course.setA1428(xrp.getAttributeValue(null, "A1428"));
                        //再处理course的子节点
                        list.add(course);
                    }
                }
                xrp.next();// 获取解析下一个事件
            }
            return list;
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * todo analysis A15
     * @param inputStream
     * @return
     */
    public List<A15> doA15AnalysisEvent(InputStream inputStream) {
        if(inputStream == null){
            return null;
        }
        try {
            XmlPullParser xrp = Xml.newPullParser();
            xrp.setInput(inputStream, encordChart);
            List<A15> list = new ArrayList<A15>();
            // 直到文档的结尾处
            while (xrp.getEventType() != XmlResourceParser.END_DOCUMENT) {
                // 如果遇到了开始标签
                if (xrp.getEventType() == XmlResourceParser.START_TAG) {
                    String tagName = xrp.getName();// 获取标签的名字
                    if (tagName.equals("row")) {
                        A15 course = new A15();
                        // 通过属性名来获取属性值
                        course.setA0000(xrp.getAttributeValue(null, "A0000"));
                        course.setA1517(xrp.getAttributeValue(null, "A1517"));
                        course.setA1521(xrp.getAttributeValue(null, "A1521"));
                        //再处理course的子节点
                        list.add(course);
                    }
                }
                xrp.next();// 获取解析下一个事件
            }
            return list;
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * todo analysis A36
     * @param inputStream
     * @return
     */
    public List<A36> doA36AnalysisEvent(InputStream inputStream) {
        if(inputStream == null){
            return null;
        }
        try {
            XmlPullParser xrp = Xml.newPullParser();
            xrp.setInput(inputStream, encordChart);
            List<A36> list = new ArrayList<A36>();
            // 直到文档的结尾处
            while (xrp.getEventType() != XmlResourceParser.END_DOCUMENT) {
                // 如果遇到了开始标签
                if (xrp.getEventType() == XmlResourceParser.START_TAG) {
                    String tagName = xrp.getName();// 获取标签的名字
                    if (tagName.equals("row")) {
                        A36 course = new A36();
                        // 通过属性名来获取属性值
                        course.setA0000(xrp.getAttributeValue(null, "A0000"));
                        course.setA3601(xrp.getAttributeValue(null, "A3601"));
                        course.setA3604A(xrp.getAttributeValue(null, "A3604A"));
                        course.setA3607(xrp.getAttributeValue(null, "A3607"));
                        course.setA3611(xrp.getAttributeValue(null, "A3611"));
                        course.setA3627(xrp.getAttributeValue(null, "A3627"));
                        course.setSORTID(xrp.getAttributeValue(null, "SORTID"));
                        //再处理course的子节点
                        list.add(course);
                    }
                }
                xrp.next();// 获取解析下一个事件
            }
            return list;
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * todo analysis A57
     * @param inputStream
     * @return
     */
    public List<A57> doA57AnalysisEvent(InputStream inputStream) {
        if(inputStream == null){
            return null;
        }
        try {
            XmlPullParser xrp = Xml.newPullParser();
            xrp.setInput(inputStream, encordChart);
            List<A57> list = new ArrayList<A57>();
            // 直到文档的结尾处
            while (xrp.getEventType() != XmlResourceParser.END_DOCUMENT) {
                // 如果遇到了开始标签
                if (xrp.getEventType() == XmlResourceParser.START_TAG) {
                    String tagName = xrp.getName();// 获取标签的名字
                    if (tagName.equals("row")) {
                        A57 course = new A57();
                        // 通过属性名来获取属性值
                        course.setA0000(xrp.getAttributeValue(null, "A0000"));
                        course.setA5714(xrp.getAttributeValue(null, "A5714"));
                        //再处理course的子节点
                        list.add(course);
                    }
                }
                xrp.next();// 获取解析下一个事件
            }
            return list;
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /** is no data
     * todo analysis A99Z1
     * @param inputStream
     * @return
     */
    public List<A99Z1> doA99Z1AnalysisEvent(InputStream inputStream) {
        if(inputStream == null){
            return null;
        }
        return null;
    }

    /**
     * todo analysis B01
     * @param inputStream
     * @return
     */
    public List<B01> doB01AnalysisEvent(InputStream inputStream) {
        if(inputStream == null){
            return null;
        }
        try {
            XmlPullParser xrp = Xml.newPullParser();
            xrp.setInput(inputStream, encordChart);
            List<B01> list = new ArrayList<B01>();
            // 直到文档的结尾处
            while (xrp.getEventType() != XmlResourceParser.END_DOCUMENT) {
                // 如果遇到了开始标签
                if (xrp.getEventType() == XmlResourceParser.START_TAG) {
                    String tagName = xrp.getName();// 获取标签的名字
                    if (tagName.equals("row")) {
                        B01 course = new B01();
                        // 通过属性名来获取属性值
                        course.setB0100(xrp.getAttributeValue(null, "B0100"));
                        course.setB0101(xrp.getAttributeValue(null, "B0101"));
                        course.setB0104(xrp.getAttributeValue(null, "B0104"));
                        course.setB0107(xrp.getAttributeValue(null, "B0107"));
                        course.setB0111(xrp.getAttributeValue(null, "B0111"));
                        course.setB0114(xrp.getAttributeValue(null, "B0114"));
                        course.setB0117(xrp.getAttributeValue(null, "B0117"));
                        course.setB0121(xrp.getAttributeValue(null, "B0121"));
                        course.setB0124(xrp.getAttributeValue(null, "B0124"));
                        course.setB0127(xrp.getAttributeValue(null, "B0127"));
                        course.setB0131(xrp.getAttributeValue(null, "B0131"));
                        course.setB0150(xrp.getAttributeValue(null, "B0150"));
                        course.setB0183(xrp.getAttributeValue(null, "B0183"));
                        course.setB0185(xrp.getAttributeValue(null, "B0185"));
                        course.setB0194(xrp.getAttributeValue(null, "B0194"));
                        course.setB0227(xrp.getAttributeValue(null, "B0227"));
                        course.setB0232(xrp.getAttributeValue(null, "B0232"));
                        course.setB0233(xrp.getAttributeValue(null, "B0233"));
                        course.setB0234(xrp.getAttributeValue(null, "B0234"));
                        course.setB0236(xrp.getAttributeValue(null, "B0236"));
                        course.setB0238(xrp.getAttributeValue(null, "B0238"));
                        course.setB0239(xrp.getAttributeValue(null, "B0239"));
                        course.setSORTID(xrp.getAttributeValue(null, "SORTID"));
                        //再处理course的子节点
                        list.add(course);
                    }
                }
                xrp.next();// 获取解析下一个事件
            }
            return list;
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * todo analysis M01
     * @param inputStream
     * @return
     */
    public List<MCSubEntry> doM01AnalysisEvent(InputStream inputStream) {
        if(inputStream == null){
            return null;
        }
        try {
            XmlPullParser xrp = Xml.newPullParser();
            xrp.setInput(inputStream, encordChart);
            List<MCSubEntry> list = new ArrayList<MCSubEntry>();
            // 直到文档的结尾处
            while (xrp.getEventType() != XmlResourceParser.END_DOCUMENT) {
                // 如果遇到了开始标签
                if (xrp.getEventType() == XmlResourceParser.START_TAG) {
                    String tagName = xrp.getName();// 获取标签的名字
                    if (tagName.equals("row")) {
                    	MCSubEntry course = new MCSubEntry();
                        // 通过属性名来获取属性值
                        course.setM0111(xrp.getAttributeValue(null, "M0111"));
                        course.setM0101(xrp.getAttributeValue(null, "M0101"));
                        course.setSortID(xrp.getAttributeValue(null, "SortID"));
                        course.setM0105(xrp.getAttributeValue(null, "M0105"));
                        //再处理course的子节点
                        list.add(course);
                    }
                }
                xrp.next();// 获取解析下一个事件
            }
            return list;
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    
    
    /**
     * todo analysis M02
     * @param inputStream
     * @return
     */
    public List<M02> doM02AnalysisEvent(InputStream inputStream) {
        if(inputStream == null){
            return null;
        }
        try {
            XmlPullParser xrp = Xml.newPullParser();
            xrp.setInput(inputStream, encordChart);
            List<M02> list = new ArrayList<M02>();
            // 直到文档的结尾处
            while (xrp.getEventType() != XmlResourceParser.END_DOCUMENT) {
                // 如果遇到了开始标签
                if (xrp.getEventType() == XmlResourceParser.START_TAG) {
                    String tagName = xrp.getName();// 获取标签的名字
                    if (tagName.equals("row")) {
                    	M02 course = new M02();
                        // 通过属性名来获取属性值
                        course.setM0200(xrp.getAttributeValue(null, "M0200"));
                        course.setM0100(xrp.getAttributeValue(null, "M0100"));
                        course.setSortID(xrp.getAttributeValue(null, "SortID"));
                        course.setB0100(xrp.getAttributeValue(null, "B0100"));
                        course.setB0101(xrp.getAttributeValue(null, "B0101"));
                        course.setB0104(xrp.getAttributeValue(null, "B0104"));
                        //再处理course的子节点
                        list.add(course);
                    }
                }
                xrp.next();// 获取解析下一个事件
            }
            return list;
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    
    /**
     * todo analysis M03
     * @param inputStream
     * @return
     */
    public List<M03> doM03AnalysisEvent(InputStream inputStream) {
        if(inputStream == null){
            return null;
        }
        try {
            XmlPullParser xrp = Xml.newPullParser();
            xrp.setInput(inputStream, encordChart);
            List<M03> list = new ArrayList<M03>();
            // 直到文档的结尾处
            while (xrp.getEventType() != XmlResourceParser.END_DOCUMENT) {
                // 如果遇到了开始标签
                if (xrp.getEventType() == XmlResourceParser.START_TAG) {
                    String tagName = xrp.getName();// 获取标签的名字
                    if (tagName.equals("row")) {
                    	M03 course = new M03();
                        // 通过属性名来获取属性值
                    	 course.setM0300(xrp.getAttributeValue(null, "M0300"));
                         course.setM0111(xrp.getAttributeValue(null, "M0111"));
                         course.setSortID(xrp.getAttributeValue(null, "SortID"));
                         course.setB0100(xrp.getAttributeValue(null, "B0100"));
                         course.setA0000(xrp.getAttributeValue(null, "A0000"));
                        //再处理course的子节点
                        list.add(course);
                    }
                }
                xrp.next();// 获取解析下一个事件
            }
            return list;
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    
    
}
