package com.cc.tools.paste.utils;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;

/**
 * 剪切板工具类
 * @author sangguangcan
 */
public class CuttingBoardUtil {

    /**
     * 判断剪切板中是否有文字数据
     * @return
     */
    public static boolean checkClipboardForText(){
        //获取到系统剪切板
        Clipboard systemClipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable contents = systemClipboard.getContents(null);
        if(contents != null && contents.isDataFlavorSupported(DataFlavor.stringFlavor)){
            return true;
        }
        return false;
    }


    /**
     * 获取剪切板中的数据
     * @return
     */
    public static String getClipboardForText(){
        //获取到系统剪切板
        try{
            Clipboard systemClipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            Transferable contents = systemClipboard.getContents(null);
            if(contents != null && contents.isDataFlavorSupported(DataFlavor.stringFlavor)){
                return (String) contents.getTransferData(DataFlavor.stringFlavor);
            }
            return "";
        }catch (Exception e){
            return "";
        }
    }
}
